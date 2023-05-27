package com.shoppingcart.cart;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/shoppingPage")
public class ShoppingCartController {

    @Autowired
    RedisTemplate<String, User> redisTemplate;

    @GetMapping
    public String showMainPage(Model model){
        model.addAttribute("user", new User());
        return "shoppingPage";
    }

    @PostMapping
    public String showShoppingPage(@ModelAttribute("user") User user, Model model)
    {   
        String userName = user.getName();
        String addedItem = user.getItem().toLowerCase();
        if (redisTemplate.hasKey(userName)){
            User existingUser = redisTemplate.opsForValue().get(userName);
            existingUser.updateUserCart(addedItem);
            redisTemplate.opsForValue().set(existingUser.getName(), existingUser);
            model.addAttribute("user", existingUser);
        }
        else{
            user.updateUserCart(addedItem);
            redisTemplate.opsForValue().set(user.getName(), user);
            model.addAttribute("user", user);
        }
        return "shoppingPage";
    }

    @DeleteMapping("/{userName}/{itemToBeDeleted}")
    public String showShoppingPage(Model model,
                                @PathVariable String userName,
                                 @PathVariable String itemToBeDeleted){
        User existingUser = redisTemplate.opsForValue().get(userName);
        existingUser.getCart().remove(itemToBeDeleted);
        redisTemplate.opsForValue().set(userName, existingUser);
        model.addAttribute("user", existingUser);
        return "shoppingPage";
    }


        
    
}
