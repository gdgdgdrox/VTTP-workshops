package day24.purchase.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.MultiValueMap;

public class Order {

    private Integer orderId;
    private String customerName;
    private String orderDate;
    private String address;
    private String notes;
    private List<OrderDetail> orderDetails = new ArrayList<>(3);

    public Order (){
    }


    public Order(Integer orderId, String customerName, String orderDate, String address, String notes, List<OrderDetail> orderDetails) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.address = address;
        this.notes = notes;
        this.orderDetails = orderDetails;
    }


    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<OrderDetail> getOrderDetails() {
        return this.orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "{" +
            " orderId='" + getOrderId() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            ", orderDate='" + getOrderDate() + "'" +
            ", address='" + getAddress() + "'" +
            ", notes='" + getNotes() + "'" +
            ", orderDetails='" + getOrderDetails() + "'" +
            "}";
    }

}
