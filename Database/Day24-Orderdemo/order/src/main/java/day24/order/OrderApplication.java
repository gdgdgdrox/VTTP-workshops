package day24.order;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import day24.order.model.OrderItem;
import day24.order.model.PurchaseOrder;
import day24.order.service.OrderService;

@SpringBootApplication
public class OrderApplication implements CommandLineRunner{

	@Autowired
	private OrderService svc;
	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PurchaseOrder po = new PurchaseOrder();
		po.setName("fruits");
		po.setOrderDate(new Date());
		// po.setOrderItems(null);
		OrderItem oi = new OrderItem();
		oi.setDescription("fresh apple from New Zealand");
		oi.setQuantity(5);
		svc.createNewOrder(po,oi);		
	}

}
