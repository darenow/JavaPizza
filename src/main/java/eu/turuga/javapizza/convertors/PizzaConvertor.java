package eu.turuga.javapizza.convertors;

import eu.turuga.javapizza.dtos.PizzaDTO;
import eu.turuga.javapizza.models.Pizza;

import java.util.List;
import java.util.stream.Collectors;

public class PizzaConvertor {

    public static List<PizzaDTO> toPizzaDTOList(List<Pizza> pizzaList){
        return pizzaList.stream()
                .map(pizza->PizzaDTO
                        .builder()
                        .name(pizza.getName())
                        .ingredients(IngredientsConvertor.toIngredientsText(pizza.getIngredients()))
                        .quantity(pizza.getQuantity())
                        .unitPrice(pizza.getUnitPrice())
                        .build()
                )
                .collect(Collectors.toList());

    }
}
