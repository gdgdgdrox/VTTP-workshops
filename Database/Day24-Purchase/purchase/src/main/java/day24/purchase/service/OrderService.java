package day24.purchase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import day24.purchase.model.Order;
import day24.purchase.repository.OrderDetailRepository;
import day24.purchase.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderDetailRepository orderDetailRepo;

    // @Transactional
    public void insertOrderAndOrderDetail(Order order){
        System.out.println("ORDER ID -> " + order.getOrderId());
        int addedOrderRepo = orderRepo.insertOrder(order);
        System.out.println("ADDED TO ORDER REPO? " + addedOrderRepo);
        int addedOrderDetail = orderDetailRepo.insertOrderDetails(order.getOrderId(), order.getOrderDetails());
        // int[] batchAddedOrderDetailRepo = orderDetailRepo.batchInsertOrderDetails(order.getOrderId(), order.getOrderDetails());

    }
}
