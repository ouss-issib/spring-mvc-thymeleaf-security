package ma.enset.springmvcthymeleafsecurityapp.web;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import ma.enset.springmvcthymeleafsecurityapp.entities.Product;
import ma.enset.springmvcthymeleafsecurityapp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/")
    public String home(Model model) {
        String currentUri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
        model.addAttribute("currentUri", currentUri);
        return "home";
    }

    @GetMapping("/user/index")
    public String index(Model model) {
        String currentUri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
        model.addAttribute("currentUri", currentUri);
        model.addAttribute("productList" ,productRepository.findAll());
        return "products";
    }

    @PostMapping("/admin/delete")
    public String delete(@RequestParam(name = "id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/admin/newProduct")
    public String newProduct(Model model){
        String currentUri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
        model.addAttribute("currentUri", currentUri);
        model.addAttribute("product", new Product());
        return "new-product";
    }

    @GetMapping("/admin/searchProduct")
    public String searchProduct(@RequestParam(name = "q") String q,Model model){
        String currentUri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
        model.addAttribute("currentUri", currentUri);
        List<Product> productsByName = productRepository.findProductsByNameContainingIgnoreCase(q);
        model.addAttribute("query",q);
        model.addAttribute("productList", productsByName);
        return "products";
    }


    @PostMapping("/admin/saveProduct")
    public String saveProduct(@Valid Product product, BindingResult bindingResult,Model model) {
        if(bindingResult.hasErrors()) {
            return "new-product";
        }
        productRepository.save(product);
        return "redirect:/user/index";
    }

    @GetMapping("/notAuthorized")
    public String notAuthorized() {
        return "notAuthorized";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "login";
    }

    @GetMapping("/admin/updateProduct")
    public String updateProduct(@RequestParam(name = "id") Long id, Model model){
        String currentUri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
        model.addAttribute("currentUri", currentUri);
        Product product = productRepository.findById(id).get();
        model.addAttribute("product",product);
        return "edit-product";
    }

    @PostMapping("/admin/edit")
    public String edit(@ModelAttribute("product") @Valid Product product,BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            return "edit-product";
        }
        productRepository.save(product);
        return "redirect:/user/index";
    }

}
