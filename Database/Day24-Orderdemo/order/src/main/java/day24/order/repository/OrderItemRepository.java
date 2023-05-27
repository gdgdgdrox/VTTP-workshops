package day24.order.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import day24.order.model.OrderItem;
import day24.order.model.PurchaseOrder;

@Repository
public class OrderItemRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertOrderItem(OrderItem oi, String orderId){
        //description, quantity, order_id
        int added = jdbcTemplate.update(Queries.SQL_INSERT_INTO_ORDER_ITEM, oi.getDescription(), oi.getQuantity(), orderId);
        return added;
    }
    

    //OrderItem[] item;
    public int[] batchInsertOrderItems(List<OrderItem> items, String orderId){
        List<Object[]> data = new ArrayList<>();

        //option 1
        for (OrderItem item : items){
            data.add(new Object[]{ item.getDescription(), item.getQuantity(), orderId });
        }
        int[] added = jdbcTemplate.batchUpdate(Queries.SQL_INSERT_INTO_ORDER_ITEM, data);
        return added;

        //option 2
        // List<Object[]> data2 = items.stream()
        //     .map(item -> new Object[]{item.getDescription(), item.getQuantity(), orderId})
        //     .toList(); //Stream.toList() returns immutable list. Collectors.toList returns mutable list.

        // int[] added = jdbcTemplate.batchUpdate(Queries.SQL_INSERT_INTO_ORDER_ITEM, data);
        

        //option 3
        // OrderItem[] itemsArray = new OrderItem[items.size()];
        // for (int i = 0; i < items.size(); i++){
        //     itemsArray[i] = items.get(i);
        // }

    }
}
