package eu.turuga.javapizza.services;

import eu.turuga.javapizza.convertors.IngredientsConvertor;
import eu.turuga.javapizza.convertors.PizzaConvertor;
import eu.turuga.javapizza.dtos.MenuDTO;
import eu.turuga.javapizza.dtos.OrderDTO;
import eu.turuga.javapizza.dtos.PizzaRequestDTO;
import eu.turuga.javapizza.exceptions.OrderNotFoundException;
import eu.turuga.javapizza.models.Ingredient;
import eu.turuga.javapizza.models.Order;
import eu.turuga.javapizza.models.Pizza;
import eu.turuga.javapizza.repositories.IngredientsRepository;
import eu.turuga.javapizza.repositories.OrdersRepository;
import eu.turuga.javapizza.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private IngredientsRepository ingredientsRepository;

    public List<MenuDTO> getMenu() {
        List<Pizza> pizzaList = new ArrayList<>();
        pizzaRepository.findAll().forEach(pizzaList::add);

        List<MenuDTO> menuDTOList = new ArrayList<>();
        pizzaList
                .forEach(pizza -> menuDTOList.add(
                        MenuDTO.builder()
                                .name(pizza.getName())
                                .price(pizza.getUnitPrice())
                                .ingredients(IngredientsConvertor.toIngredientsText(pizza.getIngredients()))
                                .build()
                        )
                );
        return menuDTOList;
    }

    public OrderDTO postOrder(List<PizzaRequestDTO> pizzaRequestDTOList) {

        // standard pizza
        List<Pizza> pizzaList = pizzaRequestDTOList
                .stream()
                .filter(pizzaRequestDTO -> pizzaRepository.findByName(pizzaRequestDTO.getName()).isPresent())
                .map(pizzaRequestDTO -> {
                    Pizza pizza = pizzaRepository.findByName(pizzaRequestDTO.getName()).get();
                    pizza.setQuantity(pizzaRequestDTO.getQuantity());
                    return pizza;
                })
                .collect(Collectors.toList());

        // custom pizza
        pizzaList.addAll(pizzaRequestDTOList
                .stream()
                .filter(pizzaRequestDTO -> pizzaRequestDTO.getName().equalsIgnoreCase("custom"))
                .map(pizzaRequestDTO -> {
                    Pizza pizza=Pizza.builder()
                            .name("custom")
                            .ingredients(
                                    Stream.of(pizzaRequestDTO.getIngredients().split(","))
                                            .filter(ingredient->ingredientsRepository.findByName(ingredient).isPresent())
                                            .map(ingredient->ingredientsRepository.findByName(ingredient).get())
                                            .collect(Collectors.toList()))
                            .quantity(pizzaRequestDTO.getQuantity())
                            .build();
                    // base price for custom pizza is 8.0
                    pizza.setUnitPrice(8.0+pizza.getIngredients().stream().mapToDouble(ingredient->ingredient.getPrice()).sum());
                    return pizza;
                })
                .collect(Collectors.toList()));

        Double price = pizzaList
                .stream()
                .mapToDouble(pizza -> pizza.getUnitPrice() * pizza.getQuantity())
                .sum();

        Order order = Order.builder()
                .pizzaList(pizzaList)
                .price(price)
                .build();
        order = ordersRepository.save(order);

        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .pizzaList(PizzaConvertor.toPizzaDTOList(order.getPizzaList()))
                .price(order.getPrice())
                .build();
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orderList = new ArrayList<>();
        ordersRepository.findAll().forEach(orderList::add);

        return orderList.stream()
                .map(order -> OrderDTO.builder()
                        .orderId(order.getOrderId())
                        .pizzaList(PizzaConvertor.toPizzaDTOList(order.getPizzaList()))
                        .price(order.getPrice())
                        .build())
                .collect(Collectors.toList());

    }

    public OrderDTO getOrder(Integer orderId) {
        Optional<Order> order=ordersRepository.findById(orderId);

        if (order.isPresent()) {
            return OrderDTO.builder()
                    .orderId(order.get().getOrderId())
                    .pizzaList(PizzaConvertor.toPizzaDTOList(order.get().getPizzaList()))
                    .price(order.get().getPrice())
                    .build();
        } else {
            throw new OrderNotFoundException();
        }
    }
}
