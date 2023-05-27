package day24.order.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderItem {
    private int itemId;
    private String description;
    private int quantity;


}
