package day24.purchase.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import day24.purchase.model.OrderDetail;

@Repository
public class OrderDetailRepository {

//     public static final String SQL_INSERT_INTO_ORDER_DETAILS = """
//         INSERT INTO orders (product, quantity, order_id)
//         VALUES (?,?,?)
//         """;
// }
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertOrderDetails(Integer orderId, List<OrderDetail> orderDetails){
        // orderDetails.forEach(od -> System.out.println(od));
        for (OrderDetail od : orderDetails){
            System.out.printf("INSERTING %s, %d, %d \n", od.getProduct(), od.getQuantity(), orderId);
            jdbcTemplate.update(Queries.SQL_INSERT_INTO_ORDER_DETAILS, od.getProduct(), od.getQuantity(), orderId);
            System.out.println("DONE INSERT");
        }
        return 1;
    }

    public int[] batchInsertOrderDetails(Integer orderId, List<OrderDetail> orderDetails){
        System.out.println("LAST ORDER_ID " + orderId);
        List<Object[]> params = orderDetails.stream()
                    .map(o -> new Object[]{o.getProduct(), o.getQuantity(), orderId})
                    .collect(Collectors.toList());
        String[] obj1 = (String[])params.get(0);
        for (String s : obj1){
            System.out.println(obj1);
        }
        // int[] added = jdbcTemplate.batchUpdate(Queries.SQL_INSERT_INTO_ORDER_DETAILS, params);
        return null;
    }
}
