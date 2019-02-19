package eu.turuga.javapizza.repositories;

import eu.turuga.javapizza.models.Order;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersRepository {

    public Optional<Order> findById(Integer orderId);

    public Order save(Order order);

    public Iterable<Order> findAll();

    public long count();
}
