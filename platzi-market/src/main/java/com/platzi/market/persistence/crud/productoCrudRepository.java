package com.platzi.market.persistence.crud;

import com.platzi.market.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface productoCrudRepository extends CrudRepository<Producto, Integer> { // Resibe dos parametros (la tabla y el tipo de la llave primaria)
    //@Query(value = "select * from productos where id_categoria = ?", nativeQuery = true) // Esta es otra forma de hacer una consulta, como se hace comunmente en una base de datos
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria); // Primer Query method (primer ejemplo de la estructura de un Query method)

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}

