package day24.order.repository;

public class Queries {
    public static final String SQL_INSERT_INTO_PURCHASE_ORDER = "insert into purchase_order (order_id, name, order_date) values (?,?,?)";
    public static final String SQL_INSERT_INTO_ORDER_ITEM = "insert into order_item (description, quantity, order_id) values (?,?,?)";

}
