package ma.enset.springmvcthymeleafsecurityapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class Product {
    @Id @GeneratedValue
    private Long id;
    @Size(min = 2, max = 50)
    @NotEmpty
    private String name;
    @Min(0)
    private double price;
    @Min(1)
    private int quantity;
}
