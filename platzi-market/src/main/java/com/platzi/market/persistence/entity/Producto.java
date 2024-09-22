package com.platzi.market.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "productos") // Se coloca el mismo nombre de la tabla que esta en la base de datos
public class Producto {
    @Id // Se coloca esta anotacion indicando que este atributo es la llave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Esta anotacion es para que se genere la llave primaria automaticamente cuando se agregue un producto
    @Column(name = "id_producto") // Se coloca esto porque le cambiamos el nombre a la tabla (como aparece en la base de datos)
    private Integer idProducto;

    private String nombre;

    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "codigo_barras")
    private String codigoBarras;

    @Column(name = "precio_venta")
    private Double precioVenta;

    @Column(name = "cantidad_stock")
    private Integer cantidadStock;

    private Boolean estado;

    // Empiezo donde se encuentre la llave foranea en el diagrama modelo relacional, y aqui es donde se coloca la anotacion "JoinColumn" con esos atributos dentro
    @ManyToOne
    @JoinColumn(name = "id_categoria", insertable = false, updatable = false) // Significa que a traves de esta relacion no vamos a borrar, ni vamos a actualizar, ni a insertar una nueva categoria (para hacerlo se debe hacer directamente en el Entity Categoria).
    private Categoria categoria;

//    @OneToMany(mappedBy = "producto")
//    private List<ComprasProducto> productos;

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(Integer cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
