package day24.order.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import day24.order.model.OrderItem;
import day24.order.model.PurchaseOrder;
import day24.order.repository.OrderItemRepository;
import day24.order.repository.PurchaseOrderRepository;

@Service
public class OrderService {
    
    @Autowired
    private PurchaseOrderRepository poRepo;

    @Autowired
    private OrderItemRepository oiRepo;

    @Transactional(rollbackFor = IOException.class)
    public void createNewOrder(PurchaseOrder po, OrderItem oi){
        System.out.println("commit start");
        String orderId = UUID.randomUUID().toString().substring(0,8);
        System.out.println("ORDER ID " + orderId);
        po.setOrderId(orderId);
        poRepo.insertPurchaseOrder(po);
        oiRepo.insertOrderItem(oi, po.getOrderId());
        // throw new IOException();
        // throw new IllegalArgumentException();
        // System.out.println("commit end");
    }
}
