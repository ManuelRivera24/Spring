package com.platzi.market.domain.repository;

import com.platzi.market.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    List<Purchase> getAll();
    Optional<List<Purchase>> getByClient(String clientId); // Es posible que en ocasiones estemos consltando un cliente que no tenga compras, para es vamos a enerrar esta lista dentro de un Opcional
    Purchase save(Purchase purchase);
}
