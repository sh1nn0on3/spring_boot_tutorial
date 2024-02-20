package com.example.Spring.Boot.Tutorial.controller;

import com.example.Spring.Boot.Tutorial.model.Product;
import com.example.Spring.Boot.Tutorial.model.ResponseObject;
import com.example.Spring.Boot.Tutorial.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/product")
public class ProductController {
    // DI
    @Autowired
    private ProductRepository repository;

//    @GetMapping("/test")
//    List<Product> test1(){
//        return List.of(
//            new Product("Macbook pro 16" , 2020 , 2400.0 , ""),
//            new Product("Macbook pro 14" , 2021 , 599.0 , "")
//        );
//    }

    @GetMapping("/getAllProducts")
    List<Product> GetAllProducts(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getProductById(@PathVariable Long id){
        Optional<Product> foundProduct = repository.findById(id);
        if(foundProduct.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok" , "Query product successfully" , foundProduct)
            );
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("Fail" , "Cannot find product with id = " + id , "")
            );
        }
    }

    @PostMapping("/insertProduct")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok" , "Insert Product SuccessFully" , repository.save(newProduct))
        );
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@PathVariable Long id , @RequestBody Product newProduct){
        Product updateProduct = repository.findById(id).map(product -> {
            product.setProductName(newProduct.getProductName());
            product.setProductYear(newProduct.getProductYear());
            product.setPrice(newProduct.getPrice());
            product.setUrl(newProduct.getUrl());
            return repository.save(product);
        }).orElseGet(()-> {
            newProduct.setId(id);
            return  repository.save(newProduct);
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok" , "Update Product Successfully" , repository.save(updateProduct))
        );
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
        boolean exists = repository.existsById(id);
        if(exists){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK" , "Delete Product Successfully" , "")
            );
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("Fail" , "Cannot find Product to delete" , "")
            );
        }
    }

}
