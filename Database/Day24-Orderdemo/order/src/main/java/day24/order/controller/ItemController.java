package day24.order.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ItemController {
    
    @PostMapping(path="/cart")
    public String added(@RequestBody MultiValueMap<String,String> body, HttpSession sess){
        String item = body.getFirst("item");
        int quantity = Integer.parseInt(body.getFirst("quantity"));
        sess.setAttribute("item", item);
        sess.setAttribute("quantity", quantity);
        return "added";
    }
}
