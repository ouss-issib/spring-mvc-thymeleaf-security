package ma.enset.springmvcthymeleafsecurityapp.repositories;

import ma.enset.springmvcthymeleafsecurityapp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
