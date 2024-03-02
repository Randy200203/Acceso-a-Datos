package com.masanz.ut2.examen.dao;

import com.masanz.ut2.examen.database.ConectionManager;

import java.util.ArrayList;
import java.util.List;

public class EmpresaDao {

    public Empresa buscarEmpresa(String nombreEmpresa, String sector) {
        String sql = "SELECT * FROM empresas WHERE nombre = ? AND sector = ? LIMIT 1";
        Object[] params = {nombreEmpresa, sector};
        Object[][] resultado = ConectionManager.ejecutarSelectSQL(sql, params);
        List<Empresa> empresas = procesarResultado(resultado);
        if(empresas!=null && empresas.size()==1){
            return empresas.get(0);
        }
        return null;

    }

    private List<Empresa> procesarResultado(Object[][] resultado){
        List<Empresa> empresas = null;
        if (resultado != null) {
            empresas = new ArrayList<>();
            for (Object[] fila : resultado) {
                Empresa empresa = new Empresa();
                empresa.setId((Integer) fila[0]);
                empresa.setNombre((String) fila[1]);
                empresa.setSector((String) fila[2]);
                empresa.setCapital((Integer) fila[3]);
                empresa.setNumEmpleados((Integer) fila[4]);
                empresas.add(empresa);
            }
        } else {
            System.out.println("El resultado es nulo.");
        }
        return empresas;
    }

    public List<Empresa> obtenerEmpresas() {
        String sql = "SELECT * FROM empresas";
        Object[] params = {};
        Object[][] resultado = ConectionManager.ejecutarSelectSQL(sql, params);
        List<Empresa> empresas = procesarResultado(resultado);
        if(empresas==null){
            empresas = new ArrayList<>();
        }
        return empresas;

    }

    public Empresa obtenerEmpresa(int id) {
        String sql = "SELECT * FROM empresas WHERE id = ? LIMIT 1";
        Object[] params = {id};
        Object[][] resultado = ConectionManager.ejecutarSelectSQL(sql, params);
        List<Empresa> empresas = procesarResultado(resultado);
        if(empresas!=null && empresas.size()==1){
            return empresas.get(0);
        }
        return null;
    }

    public boolean eliminarEmpresa(int id) {
        boolean borradoExitoso = false;
        String sql = "DELETE FROM empresas WHERE id = ?";
        Object[] params = {id};
        int empresasEliminados = ConectionManager.ejecutarUpdateSQL(sql, params);
        if (empresasEliminados > 0) {
            borradoExitoso = true;
        }
        return borradoExitoso;
    }

    public void crearEmpresa(int id, String nombre, String sector, int capital, int numEmpleados) {
        String sql = "INSERT INTO empresas (id, nombre, sector, capital, numEmpleados) VALUES (?, ?, ?, ?, ?)";
        Object[] params = {id, nombre, sector, capital, numEmpleados};
        ConectionManager.ejecutarUpdateSQL(sql, params);
    }


    public void editarEmpresa(int id, String nombre, String sector, int capital, int numEmpleados) {
        String sql = "UPDATE empresas SET nombre = ?, sector = ?, capital = ? , numEmpleados = ? WHERE id = ?";
        Object[] params = {nombre, sector, capital, numEmpleados, id};
        ConectionManager.ejecutarUpdateSQL(sql, params);
    }
}
