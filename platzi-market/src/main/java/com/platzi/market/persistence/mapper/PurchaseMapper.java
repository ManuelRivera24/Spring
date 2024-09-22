package com.platzi.market.persistence.mapper;

import com.platzi.market.domain.Purchase;
import com.platzi.market.persistence.entity.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class}) // Esto porque internamente vamos a mapear dentro de la compra todos sus productos
public interface PurchaseMapper {

    @Mappings({
            @Mapping(source = "idCompra", target = "purchaseId"),
            @Mapping(source = "idCliente", target = "clienteId"),
            @Mapping(source = "fecha", target = "date"),
            @Mapping(source = "medioPago", target = "paymentMethod"),
            @Mapping(source = "comentario", target = "comment"),
            @Mapping(source = "estado", target = "state"),
            @Mapping(source = "productos", target = "items") // Este mapping es el que usa PurchaseItemMapper para luego conervtir los productos uno a uno
    })
    Purchase toPurchase(Compra compra);
    List<Purchase> toPurchases(List<Compra> compras); // Arriba era para una sola compra, esta es para una lista de compras (ya no es necesario mappear atributo po atributo porque el toma la configuracion que ya se hizo arriba)

    @InheritInverseConfiguration
    @Mapping(target = "cliente", ignore = true) // Siempre en la clase de destino debe tener todos los mapeos, si no tiene todos los mapeos debemos ignorarlos explicitamente
    Compra toCompra(Purchase purchase);
}
