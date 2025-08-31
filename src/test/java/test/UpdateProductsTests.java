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

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static team.utils.GenerationData.getArticle;
import static team.utils.GenerationData.getDictionary;
import static team.utils.GenerationData.getNameProduct;

@Slf4j
public class UpdateProductsTests {

    private final ProductsRestSteps productsRestSteps = new ProductsRestSteps();
    private final Faker faker = new Faker();
    private String id, name, name2, article, dictionary, category;
    private CreateProductsResponse createProductsResponse;

    @BeforeEach
    @Description("Генерация тестовых данных")
    void beforeEach() {
        name = getNameProduct();
        name2 = getNameProduct();
        article = getArticle();
        dictionary = getDictionary();
        category = faker.options().option("VEGETABLES", "FRUITS");

        createProductsResponse = productsRestSteps.createNewProduct(
                name, article, category, dictionary, 100.0, 10);

        id = createProductsResponse.getId();
    }

    @Test
    @DisplayName("Обновление наименование существующего товара")
    void patchProduct() {

        productsRestSteps.updateProduct(
                id, name2, article, category, dictionary, 100.0, 10);
        createProductsResponse = productsRestSteps.getProduct(id);

        step("Проверка, что наименование товара обновлено", () -> {
            log.info("Check product with id = {}", id);
            assertAll(
                    () -> assertEquals(name2, createProductsResponse.getName()),
                    () -> assertEquals(article, createProductsResponse.getArticle()),
                    () -> assertEquals(category, createProductsResponse.getCategory()),
                    () -> assertEquals(100.0, createProductsResponse.getPrice()),
                    () -> assertEquals(10, createProductsResponse.getQty()),
                    () -> assertEquals("RUB", createProductsResponse.getCurrency()),
                    () -> assertNull(createProductsResponse.getLastQtyChanged()));
        });
    }

    @AfterEach
    void afterEach() {
        productsRestSteps.deleteProduct(createProductsResponse.getId());
    }
}
