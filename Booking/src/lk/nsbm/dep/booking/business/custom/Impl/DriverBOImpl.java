package lk.nsbm.dep.booking.business.custom.Impl;

import lk.nsbm.dep.booking.business.custom.DriverBO;
import lk.nsbm.dep.booking.dao.DAOFactory;
import lk.nsbm.dep.booking.dao.DAOTypes;
import lk.nsbm.dep.booking.dao.custom.BusDAO;
import lk.nsbm.dep.booking.dao.custom.DriverDAO;
import lk.nsbm.dep.booking.dao.custom.TicketDAO;
import lk.nsbm.dep.booking.dto.DriverDTO;
import lk.nsbm.dep.booking.entity.Driver;

import java.util.ArrayList;
import java.util.List;

public class DriverBOImpl implements DriverBO {
    private DriverDAO driverDAO = DAOFactory.getInstance().getDAO(DAOTypes.DRIVER);
    private TicketDAO ticketDAO = DAOFactory.getInstance().getDAO(DAOTypes.TICKET);
    private BusDAO busDAO = DAOFactory.getInstance().getDAO(DAOTypes.BUS);


    @Override
    public boolean saveDriver(DriverDTO driver) throws Exception {
        return driverDAO.save(new Driver(driver.getDrvId(), driver.getDrvName(), driver.getDrvNic(), driver.getDrvConNum()));
    }

    @Override
    public boolean updateDriver(DriverDTO driver) throws Exception {
        return driverDAO.update(new Driver(driver.getDrvId(), driver.getDrvName(), driver.getDrvNic(), driver.getDrvConNum()));
    }

    @Override
    public boolean deleteDriver(String driverId) throws Exception {
        return driverDAO.delete(driverId);
    }

    @Override
    public List<DriverDTO> findAllDriver() throws Exception {
        List<Driver> alDrivers = driverDAO.findAll();
        List<DriverDTO> dtos = new ArrayList<>();
        for (Driver driver : alDrivers) {
            dtos.add(new DriverDTO(driver.getDrvId(), driver.getDrvName(), driver.getDrvNic(), driver.getDrvConNum()));
        }
        return dtos;
    }

    @Override
    public String getLastDriverId() throws Exception {
        return driverDAO.getLastdrvId();
    }

    @Override
    public DriverDTO findDriver(String driverId) throws Exception {
        Driver driver = driverDAO.find(driverId);
        return new DriverDTO(driver.getDrvId(), driver.getDrvName(), driver.getDrvNic(), driver.getDrvConNum());
    }

    @Override
    public List<String> getAllDriverIDs() throws Exception {
        List<Driver> drivers = driverDAO.findAll();
        List<String> ids = new ArrayList<>();
        for (Driver driver : drivers) {
            ids.add(driver.getDrvId());
        }
        return ids;
    }
}
