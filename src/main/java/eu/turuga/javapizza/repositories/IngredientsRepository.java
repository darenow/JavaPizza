package eu.turuga.javapizza.repositories;

import eu.turuga.javapizza.models.Ingredient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientsRepository {
    public Optional<Ingredient> findByName(String name);

    public boolean existsByName(String name);

    public Iterable<Ingredient> findAll();

    public long count();

}
