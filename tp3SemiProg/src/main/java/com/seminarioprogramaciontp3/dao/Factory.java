
package com.seminarioprogramaciontp3.dao;

public class Factory extends FactoryDAO {

    @Override
    public VehiculoDAO getVehiculoDao() {
        
        return new VehiculoEclipseLink();
    }

    @Override
    public TitularDAO getTitularDao() {
        return new TitularEclipseLink();
    }
    
    @Override
    public EspecialidadDAO getEspecialidadDao() {
        return new EspecialidadSQL();
    }
    
    @Override
    public ReparacionesDAO getServicioDao() {
        return new ReparacionesSQL();
    }

    @Override
    public MecanicoDAO getMecanicoDao() {
       return new MecanicoEclipseLink();
    }

    @Override
    public HorarioDAO getHorarioDao() {
        return new HorarioSQL();
    }
    
    @Override
    public TurnoDAO getTurnoDao() {
        return new TurnoSQL();
    }

    @Override
    public AseguradoraDAO getAseguradoraDao() {
        return new AseguradoraEclipseLink();
    }
}
