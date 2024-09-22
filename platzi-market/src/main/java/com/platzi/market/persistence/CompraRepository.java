package com.platzi.market.persistence;

import com.platzi.market.domain.Purchase;
import com.platzi.market.domain.repository.PurchaseRepository;
import com.platzi.market.persistence.crud.CompraCrudRepository;
import com.platzi.market.persistence.entity.Compra;
import com.platzi.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired // Vamos a inyectarlo con la anotacion
    private CompraCrudRepository compraCrudRepository;

    @Autowired // Tambien se le inyecta el mapper
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras)); // El ".map" nos sirve para operar con lo que sea que venga dentro del opcional (si es que viene), si no hay nada dentro del opcional simplemente no se ejecuta
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));// Toda esta informacion se va a guardar en cascada, le estamos diciendo a los productos a que compra pertenecen
        return mapper.toPurchase(compraCrudRepository.save(compra)); // Le pasamos la compra con la que estabamos trabajando en la linea 36 37
    }
}
