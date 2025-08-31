package team.rest.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProductsResponse {
    private String insertedAt;
    @JsonProperty("last_qty_changed")
    private String lastQtyChanged;
    private Object price;
    private int qty;
    private String name;
    private String currency;
    private String id;
    private String category;
    private String article;
}
