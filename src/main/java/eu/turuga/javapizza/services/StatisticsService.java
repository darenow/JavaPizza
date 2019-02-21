package eu.turuga.javapizza.services;


import eu.turuga.javapizza.dtos.StatisticsOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class StatisticsService implements Statistics {

    @Autowired
    PizzaService pizzaService;

    @Override
    public StatisticsOrderDTO getAverageOrder() {
        return StatisticsOrderDTO.builder()
                .value(pizzaService.getAllOrders()
                        .stream()
                        .collect(Collectors.averagingDouble(orderDTO->orderDTO.getPrice()))
                )
                .info("Average order")
                .build();
    }

    @Override
    public StatisticsOrderDTO getMostExpensiveOrder() {
        return StatisticsOrderDTO.builder()
                .value(pizzaService.getAllOrders()
                        .stream()
                        .collect(Collectors.summarizingDouble(orderDTO->orderDTO.getPrice()))
                        .getMax()
                )
                .info("Most expensive order")
                .build();
    }

    @Override
    public StatisticsOrderDTO getCheapestOrder() {
        return StatisticsOrderDTO.builder()
                .value(pizzaService.getAllOrders()
                        .stream()
                        .collect(Collectors.summarizingDouble(orderDTO->orderDTO.getPrice()))
                        .getMin()
                )
                .info("Cheapest order")
                .build();
    }
}
