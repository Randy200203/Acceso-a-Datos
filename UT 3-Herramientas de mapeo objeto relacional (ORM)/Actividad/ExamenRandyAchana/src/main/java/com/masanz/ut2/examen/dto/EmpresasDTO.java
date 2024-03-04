package com.masanz.ut2.examen.dto;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "empresas", schema = "bdexamenut5hiber", catalog = "")
public class EmpresasDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "sector")
    private String sector;
    @Basic
    @Column(name = "capital")
    private Integer capital;
    @Basic
    @Column(name = "numEmpleados")
    private Integer numEmpleados;

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

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Integer getCapital() {
        return capital;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    public Integer getNumEmpleados() {
        return numEmpleados;
    }

    public void setNumEmpleados(Integer numEmpleados) {
        this.numEmpleados = numEmpleados;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpresasDTO that = (EmpresasDTO) o;
        return id == that.id && Objects.equals(nombre, that.nombre) && Objects.equals(sector, that.sector) && Objects.equals(capital, that.capital) && Objects.equals(numEmpleados, that.numEmpleados);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, sector, capital, numEmpleados);
    }
}
