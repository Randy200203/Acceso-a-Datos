package es.masanz.ut3.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "compras", schema = "acda", catalog = "")
public class ComprasEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "objetoId")
    private int objetoId;
    @Basic
    @Column(name = "usuarioC")
    private int usuarioC;
    @Basic
    @Column(name = "usuarioV")
    private int usuarioV;
    @Basic
    @Column(name = "fechaCompra")
    private Timestamp fechaCompra;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getObjetoId() {
        return objetoId;
    }

    public void setObjetoId(int objetoId) {
        this.objetoId = objetoId;
    }

    public int getUsuarioC() {
        return usuarioC;
    }

    public void setUsuarioC(int usuarioC) {
        this.usuarioC = usuarioC;
    }

    public int getUsuarioV() {
        return usuarioV;
    }

    public void setUsuarioV(int usuarioV) {
        this.usuarioV = usuarioV;
    }

    public Timestamp getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Timestamp fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComprasEntity that = (ComprasEntity) o;
        return id == that.id && objetoId == that.objetoId && usuarioC == that.usuarioC && usuarioV == that.usuarioV && Objects.equals(fechaCompra, that.fechaCompra);
    }

    @Override
    public String toString() {
        return "ComprasEntity{" +
                "id=" + id +
                ", objetoId=" + objetoId +
                ", usuarioC=" + usuarioC +
                ", usuarioV=" + usuarioV +
                ", fechaCompra=" + fechaCompra +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, objetoId, usuarioC, usuarioV, fechaCompra);
    }
}
