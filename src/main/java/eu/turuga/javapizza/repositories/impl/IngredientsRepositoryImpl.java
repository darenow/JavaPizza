package eu.turuga.javapizza.repositories.impl;

import eu.turuga.javapizza.models.Ingredient;
import eu.turuga.javapizza.repositories.IngredientsRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class IngredientsRepositoryImpl implements IngredientsRepository {

    private List<Ingredient> ingredientList;

    public IngredientsRepositoryImpl() {
        ingredientList =
                Arrays.asList(
                        Ingredient.builder()
                                .name("mozzarella")
                                .price(0.0)
                                .build(),
                        Ingredient.builder()
                                .name("marinara sauce")
                                .price(0.0)
                                .build(),
                        Ingredient.builder()
                                .name("basil")
                                .price(0.5)
                                .build(),
                        Ingredient.builder()
                                .name("double pepperoni")
                                .price(2.0)
                                .build(),
                        Ingredient.builder()
                                .name("chicken")
                                .price(1.0)
                                .build(),
                        Ingredient.builder()
                                .name("buffalo sauce")
                                .price(0.0)
                                .build(),
                        Ingredient.builder()
                                .name("chedar")
                                .price(0.5)
                                .build(),
                        Ingredient.builder()
                                .name("red onions")
                                .price(0.5)
                                .build()
                );

    }

    @Override
    public Optional<Ingredient> findByName(String name) {
        return ingredientList.stream().filter(ingredient -> ingredient.getName().equalsIgnoreCase(name)).findFirst();
    }

    @Override
    public boolean existsByName(String name) {
        return ingredientList.stream().anyMatch(ingredient -> ingredient.getName().equalsIgnoreCase(name));
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return ingredientList;
    }

    @Override
    public long count() {
        return ingredientList.size();
    }
}
