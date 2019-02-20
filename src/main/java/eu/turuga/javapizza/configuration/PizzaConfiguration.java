package eu.turuga.javapizza.configuration;

import eu.turuga.javapizza.repositories.IngredientsRepository;
import eu.turuga.javapizza.repositories.OrdersRepository;
import eu.turuga.javapizza.repositories.impl.IngredientsRepositoryImpl;
import eu.turuga.javapizza.repositories.PizzaRepository;
import eu.turuga.javapizza.repositories.impl.OrdersRepositoryImpl;
import eu.turuga.javapizza.repositories.impl.PizzaRepositoryImpl;
import eu.turuga.javapizza.services.Statistics;
import eu.turuga.javapizza.services.StatisticsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("eu.turuga.javapizza.repositories")
public class PizzaConfiguration {

    @Bean
    public IngredientsRepository ingredientsRepository(){
        return new IngredientsRepositoryImpl();
    }

    @Bean
    public PizzaRepository pizzaRepository(){
        return new PizzaRepositoryImpl(ingredientsRepository());
    }

    @Bean
    public OrdersRepository ordersRepository(){
        return new OrdersRepositoryImpl();
    }

    @Bean
    public Statistics statistics(){
        return new StatisticsImpl();
    }
}
