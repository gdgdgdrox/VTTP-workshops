package day24.purchase.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import day24.purchase.model.Order;

@Repository
public class OrderRepository {

    // public static final String SQL_INSERT_INTO_ORDERS = """
    //     INSERT INTO orders (customer_name, ship_address, order_date, notes)
    //     VALUES (?,?,?,?)
    //     """;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertOrder(Order order){
        System.out.printf("INSERT INTO ORDERS %s, %s, %s, %s", order.getCustomerName(), order.getAddress(), order.getOrderDate(), order.getNotes());
        int added = jdbcTemplate.update(Queries.SQL_INSERT_INTO_ORDERS, order.getCustomerName(), order.getAddress(), order.getOrderDate(), order.getNotes());
        return added;
    }

    
}
