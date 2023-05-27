package day24.order.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PurchaseOrder {
    private String orderId;
    private String name;
    private Date orderDate;
    private List<OrderItem> orderItems = new ArrayList<>();
}
