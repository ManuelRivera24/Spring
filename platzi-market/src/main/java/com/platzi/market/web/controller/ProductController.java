package com.platzi.market.web.controller;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Esta anotacion garantiza a esta clase es que va a ser un controlador de una api rest
@RequestMapping("/products") // Esta anotacion lleva como parametro que peticion o en que path va a recibir las peticiones que le hagamos
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all") // Porque estamos obteniendo informacion (se le incluye el path que va a tener)
    @ApiOperation("Get all supermarket products")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK); // El segundo parametro indica que la peticion respondio de manera adecuada cuando fue llamada
    }

    @GetMapping("{id}") // Es necesario que tenga las llaves porque al parametro donde esta recibiendo el metodo tambien se le debe a;adir una anotacion
    // Estas ultimas anotaciones sirven para mejorar la documentacion utilizando swagger
    @ApiOperation("Search a product whith an ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity<Product> getProduct(@ApiParam(value = "The id of the product", required = true, example = "7") @PathVariable("id") int productId){
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{categoryId}") // Se le agrega el "category para que spring sepa diferenciar los dos metodos"
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId){
        return productService.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product){ // El producto al ser parte de la peticion se le pone la anotacion RequestBody
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}") // Lo que este dentro de las llaves se reemplaza por el valor de la variable que tiene el @PathVariable
    public ResponseEntity delete(@PathVariable("id") int productId){
        if(productService.delete(productId)){
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
