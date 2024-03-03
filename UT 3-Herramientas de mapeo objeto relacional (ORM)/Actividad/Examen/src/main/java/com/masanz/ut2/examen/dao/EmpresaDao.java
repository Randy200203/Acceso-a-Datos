package com.masanz.ut2.examen.dao;

import com.masanz.ut2.examen.database.ConectionManager;
import com.masanz.ut2.examen.dto.EmpresasDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;

public class EmpresaDao {

    private EntityManagerFactory emf;

    public EmpresaDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EmpresasDTO buscarEmpresa(String nombreEmpresa, String sector) {
//        String sql = "SELECT * FROM empresas WHERE nombre = ? AND sector = ? LIMIT 1";
//        Object[] params = {nombreEmpresa, sector};
//        Object[][] resultado = ConectionManager.ejecutarSelectSQL(sql, params);
//        List<Empresa> empresas = procesarResultado(resultado);
//        if(empresas!=null && empresas.size()==1){
//            return empresas.get(0);
//        }
//        return null;
        EntityManager em = emf.createEntityManager();
        EmpresasDTO empresasDTO = null;
        try {
            Query query = em.createQuery("SELECT u FROM EmpresasDTO u WHERE u.nombre = :nombre AND u.sector = :sector");
            query.setParameter("nombre", nombreEmpresa);
            query.setParameter("sector", sector);
            empresasDTO = (EmpresasDTO) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Empresa no encontrada");
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        } finally {
            em.close();
        }
        return empresasDTO;

//        EntityManager em = emf.createEntityManager();
//        EmpresasDTO empresasDTO = null;
//        try {
//            empresasDTO = em.find(EmpresasDTO.class, nombreEmpresa , sector);
//        } catch (Exception e) {
//            System.out.println("ERROR: " + e);
//            em.close();
//        }
//        return empresasDTO;

    }

//    private List<Empresa> procesarResultado(Object[][] resultado){
//        List<Empresa> empresas = null;
//        if (resultado != null) {
//            empresas = new ArrayList<>();
//            for (Object[] fila : resultado) {
//                Empresa empresa = new Empresa();
//                empresa.setId((Integer) fila[0]);
//                empresa.setNombre((String) fila[1]);
//                empresa.setSector((String) fila[2]);
//                empresa.setCapital((Integer) fila[3]);
//                empresa.setNumEmpleados((Integer) fila[4]);
//                empresas.add(empresa);
//            }
//        } else {
//            System.out.println("El resultado es nulo.");
//        }
//        return empresas;
//    }

    public List<EmpresasDTO> obtenerEmpresas() {
//        String sql = "SELECT * FROM empresas";
//        Object[] params = {};
//        Object[][] resultado = ConectionManager.ejecutarSelectSQL(sql, params);
//        List<Empresa> empresas = procesarResultado(resultado);
//        if(empresas==null){
//            empresas = new ArrayList<>();
//        }
//        return empresas;

        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT u FROM EmpresasDTO u";
        List<EmpresasDTO> empresasDTOS = em.createQuery(jpql,EmpresasDTO.class).getResultList();
        em.close();
        return empresasDTOS;
    }

    public EmpresasDTO obtenerEmpresa(int id) {
//        String sql = "SELECT * FROM empresas WHERE id = ? LIMIT 1";
//        Object[] params = {id};
//        Object[][] resultado = ConectionManager.ejecutarSelectSQL(sql, params);
//        List<Empresa> empresas = procesarResultado(resultado);
//        if(empresas!=null && empresas.size()==1){
//            return empresas.get(0);
//        }
//        return null;

        EntityManager em = emf.createEntityManager();
        EmpresasDTO empresasDTO = null;
        try {
            empresasDTO = em.find(EmpresasDTO.class, id);
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        } finally {
            em.close();
        }
        return empresasDTO;
    }

    public boolean eliminarEmpresa(int id) {
//        boolean borradoExitoso = false;
//        String sql = "DELETE FROM empresas WHERE id = ?";
//        Object[] params = {id};
//        int empresasEliminados = ConectionManager.ejecutarUpdateSQL(sql, params);
//        if (empresasEliminados > 0) {
//            borradoExitoso = true;
//        }
//        return borradoExitoso;

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            EmpresasDTO borrarUser = em.find(EmpresasDTO.class, id);
            em.remove(borrarUser);
        } catch (Exception e) {
            System.out.println("ERROR" + e);
            em.close();
            return false;
        }
        System.out.println("CORRECTO");
        em.close();
        em.getTransaction().commit();
        return true;
    }

    public void crearEmpresa(int id, String nombre, String sector, int capital, int numEmpleados) {
//        String sql = "INSERT INTO empresas (id, nombre, sector, capital, numEmpleados) VALUES (?, ?, ?, ?, ?)";
//        Object[] params = {id, nombre, sector, capital, numEmpleados};
//        ConectionManager.ejecutarUpdateSQL(sql, params);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        EmpresasDTO empresasDTO = new EmpresasDTO();
        empresasDTO.setNombre(nombre);
        empresasDTO.setSector(sector);
        empresasDTO.setCapital(capital);
        empresasDTO.setNumEmpleados(numEmpleados);
        em.persist(empresasDTO);

        em.getTransaction().commit();
        em.close();
    }


    public void editarEmpresa(int id, String nombre, String sector, int capital, int numEmpleados) {
//        String sql = "UPDATE empresas SET nombre = ?, sector = ?, capital = ? , numEmpleados = ? WHERE id = ?";
//        Object[] params = {nombre, sector, capital, numEmpleados, id};
//        ConectionManager.ejecutarUpdateSQL(sql, params);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        EmpresasDTO actualizarUser = em.find(EmpresasDTO.class, id);
        actualizarUser.setNombre(nombre);
        actualizarUser.setSector(sector);
        actualizarUser.setCapital(capital);
        actualizarUser.setNumEmpleados(numEmpleados);
        em.merge(actualizarUser);
        System.out.println("CORRECTO");
        em.getTransaction().commit();
        em.close();
    }
}
