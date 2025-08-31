package test;

import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.rest.ProductsRestSteps;
import team.rest.pojo.CreateProductsErrorResponse;
import team.rest.pojo.CreateProductsResponse;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static team.utils.GenerationData.getArticle;
import static team.utils.GenerationData.getDictionary;
import static team.utils.GenerationData.getNameProduct;

@Slf4j
public class DeleteProductsTests {

    private final ProductsRestSteps productsRestSteps = new ProductsRestSteps();
    private final Faker faker = new Faker();
    private String name, article, dictionary, category;
    private CreateProductsResponse createProductsResponse;
    private CreateProductsErrorResponse createProductsErrorResponse;

    @BeforeEach
    @DisplayName("Генерация тестовых данных")
    void beforeEach() {
        name = getNameProduct();
        article = getArticle();
        dictionary = getDictionary();
        category = faker.options().option("VEGETABLES", "FRUITS");

        createProductsResponse = productsRestSteps.createNewProduct(
                name, article, category, dictionary, 100.0, 10);
    }

    @Test
    @DisplayName("Удаление существующего товара")
    void deleteProduct() {
        var id = createProductsResponse.getId();
        createProductsResponse = productsRestSteps.getProduct(id);

        step("Проверка, что товар существует", () -> {
            log.info("Check product with id = {}", id);
            assertAll(
                    () -> assertEquals(name, createProductsResponse.getName()),
                    () -> assertEquals(article, createProductsResponse.getArticle()),
                    () -> assertEquals(category, createProductsResponse.getCategory()),
                    () -> assertEquals(100.0, createProductsResponse.getPrice()),
                    () -> assertEquals(10, createProductsResponse.getQty()),
                    () -> assertEquals("RUB", createProductsResponse.getCurrency()),
                    () -> assertNull(createProductsResponse.getLastQtyChanged()));
        });

        productsRestSteps.deleteProductRest(id);
        createProductsErrorResponse = productsRestSteps.getProductError(id);
        step("Проверка, что товар удален", () -> {
            log.info("Check delete product with id = {}", id);
            assertNotNull(createProductsErrorResponse.getMessage());
        });
    }
}
