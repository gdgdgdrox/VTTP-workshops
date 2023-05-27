package day24.purchase.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import day24.purchase.model.Order;
import day24.purchase.model.OrderDetail;
import day24.purchase.service.OrderService;

@Controller
public class OrderController {

    @Autowired
    private OrderService svc;

    //This page only allows user to add 5 OrderDetail
    @GetMapping("/homecopy")
    public String homePage(Model model){
        Order order = new Order();
        //currently the capacity is 3 and size is 0. size is 0 because we havent add any OrderDetail yet. 
        //the list is only initialized upon adding the first OrderDetail object (i.e. lazy initialization);
        List<OrderDetail> orderDetails = order.getOrderDetails();
        for (int i = 0; i < 3; i++){
            orderDetails.add(new OrderDetail());
        }
        System.out.println("ORDER ID " + order.getOrderId());
        System.out.println("SIZE" + orderDetails.size());
        model.addAttribute("order", order);
        return "homecopy";
    }
    
    @PostMapping("/order")
    public String confirmOrder(@ModelAttribute Order order, Model model){
        System.out.println("ORDER ID " + order.getOrderId());
        System.out.println(order.toString());
        svc.insertOrderAndOrderDetail(order);
        return "blank";
    }
}
