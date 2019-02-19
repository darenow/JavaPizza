package eu.turuga.javapizza.repositories;

import eu.turuga.javapizza.models.Pizza;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PizzaRepository {

    public Optional<Pizza> findByName(String name);

    public boolean existsByName(String name);

    public Iterable<Pizza> findAll();

    public long count();

}
