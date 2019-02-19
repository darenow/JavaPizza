package eu.turuga.javapizza.repositories.impl;


import eu.turuga.javapizza.models.Order;
import eu.turuga.javapizza.repositories.OrdersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrdersRepositoryImpl implements OrdersRepository {
    private List<Order> orderList;


    public OrdersRepositoryImpl(){
        orderList=new ArrayList<>();
    }

    @Override
    public Optional<Order> findById(Integer orderId) {
        return orderList.stream().filter(order -> order.getOrderId()==orderId).findFirst();
    }

    @Override
    public Order save(Order order) {
        order.setOrderId(orderList.size());
        orderList.add(order);
        return findById(order.getOrderId()).get();
    }

    @Override
    public Iterable<Order> findAll() {
        return orderList;
    }

    @Override
    public long count() {
        return orderList.size();
    }
}
