package test;

import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
public class CreateProductsTests {

    private final ProductsRestSteps productsRestSteps = new ProductsRestSteps();
    private String name, article, dictionary;
    private CreateProductsResponse createProductsResponse;

    @BeforeEach
    @Description("Генерация тестовых данных")
    void beforeEach() {
        name = getNameProduct();
        article = getArticle();
        dictionary = getDictionary();
    }

    @ParameterizedTest
    @ValueSource(strings = {"VEGETABLES", "FRUITS"})
    @DisplayName("Создание нового товара")
    void createNewProduct(String category) {
        createProductsResponse = productsRestSteps.createNewProduct(
                name, article, category, dictionary, 100.0, 10);

        step("Проверка, что новый товар создан", () -> {
            log.info("Check create new product with id = {}", createProductsResponse.getId());
            assertAll(
                    () -> assertEquals(name, createProductsResponse.getName()),
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
