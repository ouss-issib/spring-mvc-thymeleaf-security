package ma.enset.springmvcthymeleafsecurityapp.web;

import ma.enset.springmvcthymeleafsecurityapp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("productList" ,productRepository.findAll());
        return "products";
    }
}
