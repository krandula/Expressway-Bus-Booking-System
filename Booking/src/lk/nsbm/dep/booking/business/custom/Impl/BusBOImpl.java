package lk.nsbm.dep.booking.business.custom.Impl;

import lk.nsbm.dep.booking.business.custom.BusBO;
import lk.nsbm.dep.booking.dao.DAOFactory;
import lk.nsbm.dep.booking.dao.DAOTypes;
import lk.nsbm.dep.booking.dao.custom.BusDAO;
import lk.nsbm.dep.booking.dao.custom.TicketDAO;
import lk.nsbm.dep.booking.dto.BusDTO;
import lk.nsbm.dep.booking.entity.Bus;

import java.util.ArrayList;
import java.util.List;

public class BusBOImpl implements BusBO {

    private BusDAO busDAO = DAOFactory.getInstance().getDAO(DAOTypes.BUS);
    private TicketDAO ticketDAO = DAOFactory.getInstance().getDAO(DAOTypes.TICKET);

    @Override
    public boolean saveBus(BusDTO bus) throws Exception {
        return busDAO.save(new Bus(bus.getBusId(), bus.getBusNo(), bus.getSeat(), bus.getRoute(), bus.getDrvID(), bus.getConId()));
    }

    @Override
    public boolean updateBus(BusDTO bus) throws Exception {
        return busDAO.update(new Bus(bus.getBusId(), bus.getBusNo(), bus.getSeat(), bus.getRoute(), bus.getDrvID(), bus.getConId()));
    }

    @Override
    public boolean deleteBus(String busId) throws Exception {
        return busDAO.delete(busId);
    }

    @Override
    public List<BusDTO> findAllbus() throws Exception {
        List<Bus> alDrivers = busDAO.findAll();
        List<BusDTO> busDTOS = new ArrayList<>();
        for (Bus bus : alDrivers) {
            busDTOS.add(new BusDTO(bus.getBusId(), bus.getBusNo(), bus.getSeat(), bus.getRoute(), bus.getDrvID(), bus.getConId()));
        }
        return busDTOS;
    }

    @Override
    public String getLastBusId() throws Exception {
        return busDAO.getLastbusId();
    }

    @Override
    public BusDTO findBus(String busId) throws Exception {
        Bus bus = busDAO.find(busId);
        return new BusDTO(bus.getBusId(), bus.getBusNo(), bus.getSeat(), bus.getRoute(), bus.getDrvID(), bus.getConId());
    }

    @Override
    public List<String> getAllBusIDs() throws Exception {
        List<Bus> buses = busDAO.findAll();
        List<String> ids = new ArrayList<>();
        for (Bus bus : buses) {
            ids.add(bus.getBusId());
        }
        return ids;
    }
}
