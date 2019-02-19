package eu.turuga.javapizza.repositories.impl;

import eu.turuga.javapizza.models.Pizza;
import eu.turuga.javapizza.repositories.IngredientsRepository;
import eu.turuga.javapizza.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PizzaRepositoryImpl implements PizzaRepository {

    private IngredientsRepository ingredientsRepository;
    private List<Pizza> pizzaList;

    public PizzaRepositoryImpl(@Autowired IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
        pizzaList = Arrays.asList(
                Pizza.builder()
                        .name("Cheese")
                        .ingredients(Arrays.asList(
                                ingredientsRepository.findByName("mozzarella").get(),
                                ingredientsRepository.findByName("marinara sauce").get(),
                                ingredientsRepository.findByName("basil").get()
                                )
                        )
                        .unitPrice(9.0)
                        .build(),
                Pizza.builder()
                        .name("Pepperoni")
                        .ingredients(Arrays.asList(
                                ingredientsRepository.findByName("double pepperoni").get(),
                                ingredientsRepository.findByName("mozzarella").get(),
                                ingredientsRepository.findByName("marinara sauce").get(),
                                ingredientsRepository.findByName("basil").get()
                                )
                        )
                        .unitPrice(11.00)
                        .build(),
                Pizza.builder()
                        .name("Buffalo Chicken")
                        .ingredients(Arrays.asList(
                                ingredientsRepository.findByName("chicken").get(),
                                ingredientsRepository.findByName("buffalo sauce").get(),
                                ingredientsRepository.findByName("mozzarella").get(),
                                ingredientsRepository.findByName("chedar").get(),
                                ingredientsRepository.findByName("red onions").get()
                                )
                        )
                        .unitPrice(13.00)
                        .build());

    }

    @Override
    public Optional<Pizza> findByName(String name) {
        return pizzaList.stream().filter(pizza -> pizza.getName().equalsIgnoreCase(name)).findFirst();
    }

    @Override
    public boolean existsByName(String name) {
        return pizzaList.stream().anyMatch(pizza -> pizza.getName().equalsIgnoreCase(name));
    }

    @Override
    public Iterable<Pizza> findAll() {
        return pizzaList;
    }

    @Override
    public long count() {
        return pizzaList.size();
    }

}