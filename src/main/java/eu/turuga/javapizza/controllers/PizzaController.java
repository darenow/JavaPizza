package eu.turuga.javapizza.controllers;

import eu.turuga.javapizza.dtos.MenuDTO;
import eu.turuga.javapizza.dtos.OrderDTO;
import eu.turuga.javapizza.dtos.PizzaRequestDTO;
import eu.turuga.javapizza.services.PizzaService;
import eu.turuga.javapizza.services.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pizza", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private Statistics statistics;

	@GetMapping(value = "/menu")
	public ResponseEntity<List<MenuDTO>> getMenu() {
		return new ResponseEntity<>(pizzaService.getMenu(), HttpStatus.OK);
	}

	@PostMapping(value = "/order")
	public ResponseEntity<OrderDTO> postOrder(@RequestBody List<PizzaRequestDTO> pizzaRequestDTOList){
		return new ResponseEntity<>(pizzaService.postOrder(pizzaRequestDTOList),HttpStatus.OK);
	}

	@GetMapping(value = "/order/all")
	public ResponseEntity<List<OrderDTO>> getAllOrders(){
		return new ResponseEntity<>(pizzaService.getAllOrders(),HttpStatus.OK);
	}

	@GetMapping(value = "/order/{orderId}")
	public ResponseEntity<OrderDTO> getOrder(@PathVariable Integer orderId){
		return new ResponseEntity<>(pizzaService.getOrder(orderId), HttpStatus.OK);
	}
}
