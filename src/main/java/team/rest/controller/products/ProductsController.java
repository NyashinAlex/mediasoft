package team.rest.controller.products;

import io.restassured.common.mapper.TypeRef;
import org.springframework.stereotype.Component;
import team.rest.controller.RestController;
import team.rest.pojo.CreateProductsErrorResponse;
import team.rest.pojo.CreateProductsRequest;
import team.rest.pojo.CreateProductsResponse;

import java.util.List;

@Component
public class ProductsController extends RestController {

    private final static String PRODUCTS_PRODUCTS = "/products/products";
    private final static String PRODUCTS = "/products";

    public CreateProductsResponse createProductRest(CreateProductsRequest body) {
        return postMethod(PRODUCTS_PRODUCTS, body, CreateProductsResponse.class);
    }

    public CreateProductsResponse patchProductRest(CreateProductsRequest body) {
        return patchMethod(PRODUCTS, body, CreateProductsResponse.class);
    }

    public void deleteProductRest(String id) {
        deleteMethod(PRODUCTS + "/", id);
    }

    public CreateProductsResponse getProductRest(String id) {
        return getMethod(PRODUCTS + "/", id, CreateProductsResponse.class);
    }

    public List<CreateProductsResponse> getAllProductsRest() {
        return getMethod(PRODUCTS, new TypeRef<List<CreateProductsResponse>>() {
        });
    }

    public CreateProductsErrorResponse getProductErrorRest(String id) {
        return getMethod(PRODUCTS + "/", id, CreateProductsErrorResponse.class);
    }
}
