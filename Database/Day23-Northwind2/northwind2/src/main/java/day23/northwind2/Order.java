package day23.northwind2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int orderId;
    private String orderDate;
    private int customerId;
    private float totalCost;
    private float costPrice;
    private double discount;
}
