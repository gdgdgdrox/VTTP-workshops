package com.example.server.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.Utils;
import com.example.server.models.Order;
import com.example.server.models.OrderSummary;
import com.example.server.services.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
@RequestMapping(path="/api")
@CrossOrigin(origins = "*")
public class OrderRestController {

    @Autowired
    private OrderService orderSvc;

    @PostMapping(path="/order", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveOrder(@RequestBody String payload){
        System.out.println(" IN ORDER CONTROLLER");
        System.out.println("PAYLOAD " + payload);
        String response = "";
        try {
            ObjectMapper objMapper = new ObjectMapper();
            Order o = objMapper.readValue(payload, Order.class);
            orderSvc.createOrder(o);
            response = Utils.createResponse("success", "order created").toString();
            return ResponseEntity.status(201).body(response);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            response = e.getMessage();
            return ResponseEntity.status(500).body(response);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            response = e.getMessage();
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping(path="/order/{email}/all")
    public ResponseEntity<String> getOrders(@PathVariable String email){
        System.out.println("IN GET ALL ORDERS CONTROLLER");
        System.out.println("EMAIL > " + email);
        List<OrderSummary> orderSummary = orderSvc.getOrdersByEmail(email);
        String response = Utils.createResponse(email, Utils.orderSummaryArray(orderSummary)).toString();
        return ResponseEntity.ok(response);
    }

    
}
