package eu.turuga.javapizza.controllers;

import eu.turuga.javapizza.dtos.*;
import eu.turuga.javapizza.services.PizzaService;
import eu.turuga.javapizza.services.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pizza/statistics", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class StatisticsController {

	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private Statistics statistics;


	@GetMapping(value="/mostOrderedPizza")
	public ResponseEntity<StatisticsPizzaDTO> getMostOrderedPizza(){
		return new ResponseEntity<>(statistics.getMostOrderedPizza(pizzaService.getAllOrders()),HttpStatus.OK);
	}

	@GetMapping(value="/lessOrderedPizza")
	public ResponseEntity<StatisticsPizzaDTO> getLessOrderedPizza(){
		return new ResponseEntity<>(statistics.getLessOrderedPizza(pizzaService.getAllOrders()),HttpStatus.OK);
	}

	@GetMapping(value="/averageOrder")
	public ResponseEntity<StatisticsOrderDTO> getAverageOrder(){
		return new ResponseEntity<>(statistics.getAverageOrder(),HttpStatus.OK);
	}

	@GetMapping(value="/mostExpensiveOrder")
	public ResponseEntity<StatisticsOrderDTO> getMostExpensiveOrder(){
		return new ResponseEntity<>(statistics.getMostExpensiveOrder(),HttpStatus.OK);
	}

	@GetMapping(value="/cheapestOrder")
	public ResponseEntity<StatisticsOrderDTO> getCheapestOrder(){
		return new ResponseEntity<>(statistics.getCheapestOrder(),HttpStatus.OK);
	}
}
