package es.masanz.ut3_ut4.dto;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "smartphones", schema = "actividad_hiber_mongo", catalog = "")
public class SmartphonesDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "modelo")
    private String modelo;
    @Basic
    @Column(name = "sistema_operativo")
    private String sistemaOperativo;
    @Basic
    @Column(name = "precio")
    private BigDecimal precio;
    @Basic
    @Column(name = "memoria_ram")
    private Integer memoriaRam;

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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getMemoriaRam() {
        return memoriaRam;
    }

    public void setMemoriaRam(Integer memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartphonesDTO that = (SmartphonesDTO) o;
        return id == that.id && Objects.equals(nombre, that.nombre) && Objects.equals(modelo, that.modelo) && Objects.equals(sistemaOperativo, that.sistemaOperativo) && Objects.equals(precio, that.precio) && Objects.equals(memoriaRam, that.memoriaRam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, modelo, sistemaOperativo, precio, memoriaRam);
    }
}
