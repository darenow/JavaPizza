package eu.turuga.javapizza.convertors;

import eu.turuga.javapizza.models.Ingredient;

import java.util.List;
import java.util.stream.Collectors;

public class IngredientsConvertor {
    public static String toIngredientsText(List<Ingredient> ingredientList){
        return ingredientList.stream().map(i->i.getName()).collect(Collectors.joining(","));
    }

}
