package com.masanz.ut2.examen.dto;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "stock", schema = "bdexamenut5hiber", catalog = "")
public class StockDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "idEmpresa")
    private Integer idEmpresa;
    @Basic
    @Column(name = "idProducto")
    private Integer idProducto;
    @Basic
    @Column(name = "cantidad")
    private Integer cantidad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockDTO stockDTO = (StockDTO) o;
        return id == stockDTO.id && Objects.equals(idEmpresa, stockDTO.idEmpresa) && Objects.equals(idProducto, stockDTO.idProducto) && Objects.equals(cantidad, stockDTO.cantidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idEmpresa, idProducto, cantidad);
    }
}
