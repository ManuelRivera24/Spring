package com.platzi.market.persistence;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import com.platzi.market.persistence.crud.productoCrudRepository;
import com.platzi.market.persistence.entity.Producto;
import com.platzi.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // Con esta anotacion indicamos que esta clase va a interactuar con la base de datos (es una buena practica ponerla)
public class productoRepository implements ProductRepository {

    @Autowired // Cuando se utiliza esta anotacion le vamos a explicar a spring que los objetos que reciban esa anotacion le van a seder el control a spring para que crees esas instancias (para no crear objetos manualmente)
    private productoCrudRepository productoCrudRepository;

    @Autowired // Cuando se vaya a utilizar esta anotacion se debe estar muy seguro que el objeto o el atributo que se vaya a inyectar sea un componente de spring
    private ProductMapper mapper;

    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos); // Para obtener todos los productos que tengo en mi supermercado
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product); // Vamos a convertir al rev'es de product a producto
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    public Optional<Producto> getProducto(int idProducto){
        return productoCrudRepository.findById(idProducto);
    }

    @Override
    public void delete(int productId){
        productoCrudRepository.deleteById(productId);
    }
}
