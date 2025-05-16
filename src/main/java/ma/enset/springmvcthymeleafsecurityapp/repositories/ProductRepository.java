package ma.enset.springmvcthymeleafsecurityapp.repositories;

import ma.enset.springmvcthymeleafsecurityapp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
     List<Product> findProductsByNameContainingIgnoreCase(String name);
}
