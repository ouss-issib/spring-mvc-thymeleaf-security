package ma.enset.springmvcthymeleafsecurityapp;

import ma.enset.springmvcthymeleafsecurityapp.entities.Product;
import ma.enset.springmvcthymeleafsecurityapp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringMvcThymeleafSecurityAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcThymeleafSecurityAppApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository pr) {
        return args -> {
            pr.save(Product.builder()
                    .name("Hp Laptop")
                    .price(10000)
                    .quantity(4)
                    .build());
            pr.save(Product.builder()
                    .name("Iphone")
                    .price(4500)
                    .quantity(6)
                    .build());
            pr.save(Product.builder()
                    .name("Monitor LG")
                    .price(9000)
                    .quantity(42)
                    .build());

            pr.findAll().forEach(p->System.out.println(p.toString()));
        };
    }
}
