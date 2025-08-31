package steps.rest;

import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import team.rest.controller.products.ProductsController;
import team.rest.pojo.CreateProductsErrorResponse;
import team.rest.pojo.CreateProductsRequest;
import team.rest.pojo.CreateProductsResponse;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductsRestSteps extends ProductsController {

    @Step("Создание нового товара с name = {name}")
    public CreateProductsResponse createNewProduct(
            String name, String article, String category, String dictionary, double price, int qty) {

        CreateProductsRequest request = CreateProductsRequest.builder()
                .name(name)
                .article(article)
                .category(category)
                .dictionary(dictionary)
                .price(price)
                .qty(qty)
                .build();
        log.info("Create new products: {}", request);

        return createProductRest(request);
    }

    @Step("Обновление товара с id = {id}")
    public CreateProductsResponse updateProduct(
            String id, String name, String article, String category, String dictionary, double price, int qty) {

        CreateProductsRequest request = CreateProductsRequest.builder()
                .id(id)
                .name(name)
                .article(article)
                .category(category)
                .dictionary(dictionary)
                .price(price)
                .qty(qty)
                .build();
        log.info("Update products: {}", request);

        return patchProductRest(request);
    }

    @Step("Удаление товара с id = {id}")
    public void deleteProduct(String id) {
        log.info("Delete product with id = {}", id);
        deleteProductRest(id);
    }

    @Step("Поиск существующего товара с id = {id}")
    public CreateProductsResponse getProduct(String id) {
        log.info("Get product with id = {}", id);
        return getProductRest(id);
    }

    @Step("Поиск всех существующих товаров")
    public List<CreateProductsResponse> getAllProducts() {
        log.info("Get all products");
        return getAllProductsRest();
    }

    @Step("Поиск несуществующего товара с id = {id}")
    public CreateProductsErrorResponse getProductError(String id) {
        log.info("Get non-existent product with id = {}", id);
        return getProductErrorRest(id);
    }
}
