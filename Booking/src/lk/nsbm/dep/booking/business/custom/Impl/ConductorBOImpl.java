package lk.nsbm.dep.booking.business.custom.Impl;

import lk.nsbm.dep.booking.business.custom.ConductorBO;
import lk.nsbm.dep.booking.dao.DAOFactory;
import lk.nsbm.dep.booking.dao.DAOTypes;
import lk.nsbm.dep.booking.dao.custom.BusDAO;
import lk.nsbm.dep.booking.dao.custom.ConductorDAO;
import lk.nsbm.dep.booking.dao.custom.TicketDAO;
import lk.nsbm.dep.booking.dto.ConductorDTO;
import lk.nsbm.dep.booking.dto.DriverDTO;
import lk.nsbm.dep.booking.entity.Conductor;

import java.util.ArrayList;
import java.util.List;

public class ConductorBOImpl implements ConductorBO {

    private ConductorDAO conductorDAO = DAOFactory.getInstance().getDAO(DAOTypes.CONDUCTOR);
    private TicketDAO ticketDAO = DAOFactory.getInstance().getDAO(DAOTypes.TICKET);
    private BusDAO busDAO = DAOFactory.getInstance().getDAO(DAOTypes.BUS);

    @Override
    public boolean saveConductor(ConductorDTO conductor) throws Exception {
        return conductorDAO.save(new Conductor(conductor.getConId(), conductor.getConName(), conductor.getConNic(), conductor.getConConNum()));
    }

    @Override
    public boolean updateConductor(ConductorDTO conductor) throws Exception {
        return conductorDAO.update(new Conductor(conductor.getConId(), conductor.getConName(), conductor.getConNic(), conductor.getConConNum()));
    }

    @Override
    public boolean deleteConductor(String conId) throws Exception {
        return conductorDAO.delete(conId);
    }

    @Override
    public List<ConductorDTO> findAllConductor() throws Exception {
        List<Conductor> conductorDAOAll = conductorDAO.findAll();
        List<ConductorDTO> conductorDTOS = new ArrayList<>();
        for (Conductor conductor : conductorDAOAll) {
            conductorDTOS.add(new ConductorDTO(conductor.getConId(), conductor.getConName(), conductor.getConNic(), conductor.getConConNum()));
        }
        return conductorDTOS;
    }

    @Override
    public String getLastConId() throws Exception {
        return conductorDAO.getLastconId();
    }

    @Override
    public DriverDTO findConductor(String conId) throws Exception {
        Conductor conductor = conductorDAO.find(conId);
        return new DriverDTO(conductor.getConId(), conductor.getConName(), conductor.getConNic(), conductor.getConConNum());
    }

    @Override
    public List<String> getAllConductorIDs() throws Exception {
        List<Conductor> drivers = conductorDAO.findAll();
        List<String> ids = new ArrayList<>();
        for (Conductor conductor : drivers) {
            ids.add(conductor.getConId());
        }
        return ids;
    }
}
