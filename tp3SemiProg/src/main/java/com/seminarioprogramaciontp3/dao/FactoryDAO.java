
package com.seminarioprogramaciontp3.dao;

import java.lang.reflect.InvocationTargetException;


public abstract class FactoryDAO {

    public abstract TitularDAO getTitularDao();
    public abstract EspecialidadDAO getEspecialidadDao();
    public abstract ReparacionesDAO getServicioDao();
    public abstract VehiculoDAO getVehiculoDao();
    public abstract MecanicoDAO getMecanicoDao();
    public abstract HorarioDAO getHorarioDao();
    public abstract TurnoDAO getTurnoDao();
    public abstract AseguradoraDAO getAseguradoraDao();


    public static FactoryDAO getFactory(String nombreClase){
        try {            
            return (FactoryDAO) Class.forName(FactoryDAO.class.getPackage().getName() + "." + nombreClase).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            System.err.println(e);
            throw new IllegalArgumentException();
        }
    }
}
