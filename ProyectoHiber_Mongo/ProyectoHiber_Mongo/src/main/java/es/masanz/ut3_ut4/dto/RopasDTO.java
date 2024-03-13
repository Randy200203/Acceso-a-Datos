package es.masanz.ut3_ut4.dto;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "ropas", schema = "actividad_hiber_mongo", catalog = "")
public class RopasDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "marca")
    private String marca;
    @Basic
    @Column(name = "talla")
    private String talla;
    @Basic
    @Column(name = "color")
    private String color;
    @Basic
    @Column(name = "precio")
    private BigDecimal precio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RopasDTO ropasDTO = (RopasDTO) o;
        return id == ropasDTO.id && Objects.equals(nombre, ropasDTO.nombre) && Objects.equals(marca, ropasDTO.marca) && Objects.equals(talla, ropasDTO.talla) && Objects.equals(color, ropasDTO.color) && Objects.equals(precio, ropasDTO.precio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, marca, talla, color, precio);
    }
}
