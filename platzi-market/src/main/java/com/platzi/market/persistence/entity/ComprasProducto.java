package com.platzi.market.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "compras_productos")
public class ComprasProducto {

    // Las llaves primarias compuestas se sacan en otra clase y se llaman en esta
    @EmbeddedId
    private ComprasProductoPK id;

    private Integer cantidad;

    // Los tipos de datos siempre en mayuscula
    private Double total;

    private Boolean estado;

    @ManyToOne
    // Cuando ComprasCoducto se vaya a guardar en cascada vamos a ver a que llave primaria pertenece cada producto que esta en una compra
    @MapsId("idCompra") // Se le indica el nombre de la cloavce primaria con la que queremos que se enlace (el nombre de la clave primaria), todo esto para que guarde en cascada los productos dentro de la compra
    @JoinColumn(name = "id_compra", insertable = false, updatable = false) // Siempre debe de ponerse el insertable = false, updatable = false
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;

    public ComprasProductoPK getId() {
        return id;
    }

    public void setId(ComprasProductoPK id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
