package day24.purchase.model;

import java.util.List;

import org.springframework.util.MultiValueMap;

public class OrderDetail {
    private String product;
    private Integer quantity;

    public String getProduct() {
        return this.product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "{" +
            " product='" + getProduct() + "'" +
            ", quantity='" + getQuantity() + "'" +
            "}";
    }

}
