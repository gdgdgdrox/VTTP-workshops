package day24.purchase.repository;

public class Queries {
    
    public static final String SQL_INSERT_INTO_ORDERS = """
            INSERT INTO orders (customer_name, ship_address, order_date, notes)
            VALUES (?,?,?,?)
            """;

    public static final String SQL_INSERT_INTO_ORDER_DETAILS = """
        INSERT INTO order_details (product, quantity, order_id)
        VALUES (?,?,?)
        """;
}
