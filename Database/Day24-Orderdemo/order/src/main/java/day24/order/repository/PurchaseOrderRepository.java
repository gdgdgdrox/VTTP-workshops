package day24.order.repository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import day24.order.model.PurchaseOrder;

@Repository
public class PurchaseOrderRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //order_id, name, order_date
    public int insertPurchaseOrder(PurchaseOrder po){
        int added = jdbcTemplate.update(Queries.SQL_INSERT_INTO_PURCHASE_ORDER, po.getOrderId(), po.getName(), po.getOrderDate());
        return added;
    }



}
