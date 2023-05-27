package day23.northwind2;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderController {
    
    @Autowired
    private OrderRepository repo;

    @GetMapping(path = "/order/total/{orderId}")
    public String returnOrderDetails(@PathVariable int orderId, Model model){
        Optional<Order> order = repo.getOrderDetails(orderId);
        if (order.isEmpty()){
            model.addAttribute("order", null);
        }
        else{
            model.addAttribute("order", order.get());
        }
        return "result";
    }
}
