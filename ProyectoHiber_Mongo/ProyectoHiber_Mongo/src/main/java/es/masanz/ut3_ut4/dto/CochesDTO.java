package es.masanz.ut3_ut4.dto;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "coches", schema = "actividad_hiber_mongo", catalog = "")
public class CochesDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "precio")
    private BigDecimal precio;
    @Basic
    @Column(name = "modelo")
    private String modelo;
    @Basic
    @Column(name = "kilometraje")
    private Integer kilometraje;
    @Basic
    @Column(name = "color")
    private String color;

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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(Integer kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CochesDTO cochesDTO = (CochesDTO) o;
        return id == cochesDTO.id && Objects.equals(nombre, cochesDTO.nombre) && Objects.equals(precio, cochesDTO.precio) && Objects.equals(modelo, cochesDTO.modelo) && Objects.equals(kilometraje, cochesDTO.kilometraje) && Objects.equals(color, cochesDTO.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, precio, modelo, kilometraje, color);
    }
}
