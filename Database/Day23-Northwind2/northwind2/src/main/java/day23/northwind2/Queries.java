package day23.northwind2;

public class Queries {
    public static final String SQL_GET_ORDER_DETAILS = """
        select orders.id as order_id, order_date, customer_id, 
        SUM(quantity * unit_price) as total_cost, SUM(quantity * standard_cost) as cost_price, discount
        from orders
        join order_details
        on orders.id = order_details.order_id
        join products
        on order_details.product_id = products.id
        where orders.id = ?
        group by order_id;
        """;
}
