package com.platzi.market.persistence.mapper;

import com.platzi.market.domain.Product;
import com.platzi.market.persistence.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class}) // Con este ultimo parametro se esta indicando que cuando cuando vaya a convertir "categoria" en "category" tiene que usa "CategoryMapper"
public interface ProductMapper {
    @Mappings({
            @Mapping(source = "idProducto", target = "productId"), // Siempre va a tener esta forma (indica cual es mi fuente y cual es mi destino)
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            //@Mapping(source = "codigoBarras", target = ""),
            @Mapping(source = "precioVenta", target = "price"),
            @Mapping(source = "cantidadStock", target = "stock"),
            @Mapping(source = "estado", target = "active"),
            @Mapping(source = "categoria", target = "category")

    })
    Product toProduct(Producto producto);
    List<Product> toProducts(List<Producto> productos); // Tambien se puede tener la lista de productos (arriba esta en singular, y aqui esta en plural)

    @InheritInverseConfiguration // Aqui se hace el mapeo de manera inversa a como esta arriba
    @Mapping(target = "codigoBarras", ignore = true)
    Producto toProducto(Product product);
}
