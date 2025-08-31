package test;

import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.rest.ProductsRestSteps;
import team.rest.pojo.CreateProductsResponse;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static team.utils.GenerationData.getArticle;
import static team.utils.GenerationData.getDictionary;
import static team.utils.GenerationData.getNameProduct;

@Slf4j
public class GetAllProductsTests {

    private final ProductsRestSteps productsRestSteps = new ProductsRestSteps();
    private final Faker faker = new Faker();
    private String id, id2;

    @BeforeEach
    @Description("Генерация тестовых данных")
    void beforeEach() {
        String category = faker.options().option("VEGETABLES", "FRUITS");

        id = productsRestSteps.createNewProduct(
                getNameProduct(), getArticle(), category, getDictionary(), 100.0, 10).getId();
        id2 = productsRestSteps.createNewProduct(
                getNameProduct(), getArticle(), category, getDictionary(), 100.0, 10).getId();
    }

    @Test
    @DisplayName("Поиск всех существующих товаров")
    void getAllProducts() {
        List<CreateProductsResponse> allProducts = productsRestSteps.getAllProducts();
        step("Проверка, что все товары получены", () -> {
            assertEquals(2, allProducts.size());
        });
    }

    @AfterEach
    void afterEach() {
        productsRestSteps.deleteProduct(id);
        productsRestSteps.deleteProduct(id2);
    }
}
