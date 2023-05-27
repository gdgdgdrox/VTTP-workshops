package day23.northwind2;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<Order> getOrderDetails(int orderId){
        SqlRowSet rs = jdbcTemplate.queryForRowSet(Queries.SQL_GET_ORDER_DETAILS, orderId);
        if (rs.next()){
            Order order = new Order(
                rs.getInt("order_id"),
                rs.getString("order_date"),
                rs.getInt("customer_id"),
                rs.getFloat("total_cost"),
                rs.getFloat("cost_price"),
                rs.getDouble("discount")
            );
            return Optional.of(order);
        }
        else{
            return Optional.empty();
        }
    }

}
