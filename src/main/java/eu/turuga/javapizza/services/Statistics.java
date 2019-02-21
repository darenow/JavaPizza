package eu.turuga.javapizza.services;

import eu.turuga.javapizza.dtos.OrderDTO;
import eu.turuga.javapizza.dtos.PizzaDTO;
import eu.turuga.javapizza.dtos.StatisticsOrderDTO;
import eu.turuga.javapizza.dtos.StatisticsPizzaDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface Statistics {

    default StatisticsPizzaDTO getMostOrderedPizza(Iterable<OrderDTO> orders){


        return StreamSupport.stream(orders.spliterator(),false)
                .map(OrderDTO::getPizzaList)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(PizzaDTO::getName,Collectors.summingInt(PizzaDTO::getQuantity)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(entry->StatisticsPizzaDTO.builder()
                        .name(entry.getKey())
                        .quantity(entry.getValue())
                        .build())
                .orElse(StatisticsPizzaDTO.builder()
                        .name("")
                        .quantity(0)
                        .build());

    }

    default StatisticsPizzaDTO getLessOrderedPizza(Iterable<OrderDTO> orders){


        return StreamSupport.stream(orders.spliterator(),false)
                .map(OrderDTO::getPizzaList)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(PizzaDTO::getName,Collectors.summingInt(PizzaDTO::getQuantity)))
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .map(entry->StatisticsPizzaDTO.builder()
                        .name(entry.getKey())
                        .quantity(entry.getValue())
                        .build())
                .orElse(StatisticsPizzaDTO.builder()
                        .name("")
                        .quantity(0)
                        .build());

    }

    StatisticsOrderDTO getAverageOrder();
    StatisticsOrderDTO getMostExpensiveOrder();
    StatisticsOrderDTO getCheapestOrder();
}
