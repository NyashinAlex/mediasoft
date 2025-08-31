package team.rest.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProductsRequest {
    private String id;
    private String dictionary;
    private double price;
    private int qty;
    private String name;
    private String category;
    private String article;
}
