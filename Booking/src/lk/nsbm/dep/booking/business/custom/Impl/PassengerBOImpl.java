package lk.nsbm.dep.booking.business.custom.Impl;

import lk.nsbm.dep.booking.business.custom.PassengerBO;
import lk.nsbm.dep.booking.dao.DAOFactory;
import lk.nsbm.dep.booking.dao.DAOTypes;
import lk.nsbm.dep.booking.dao.custom.PassengerDAO;
import lk.nsbm.dep.booking.dao.custom.TicketDAO;
import lk.nsbm.dep.booking.dto.PassengerDTO;
import lk.nsbm.dep.booking.entity.Passenger;

import java.util.ArrayList;
import java.util.List;

public class PassengerBOImpl implements PassengerBO {

    private PassengerDAO passengerDAO = DAOFactory.getInstance().getDAO(DAOTypes.PASSENGER);
    private TicketDAO ticketDAO = DAOFactory.getInstance().getDAO(DAOTypes.TICKET);

    @Override
    public boolean savePassenger(PassengerDTO passenger) throws Exception {
        return passengerDAO.save(new Passenger(passenger.getPassId(), passenger.getPassName(), passenger.getPassNic(), passenger.getPassConNum()));
    }

    @Override
    public boolean updatePassenger(PassengerDTO passenger) throws Exception {
        return passengerDAO.update(new Passenger(passenger.getPassId(), passenger.getPassName(), passenger.getPassNic(), passenger.getPassConNum()));
    }

    @Override
    public boolean deletePassenger(String PassengerId) throws Exception {
        return passengerDAO.delete(PassengerId);
    }

    @Override
    public List<PassengerDTO> findAllPassenger() throws Exception {
        List<Passenger> allPass = passengerDAO.findAll();
        List<PassengerDTO> passengerDTOS = new ArrayList<>();
        for (Passenger passenger : allPass) {
            passengerDTOS.add(new PassengerDTO(passenger.getPassId(), passenger.getPassName(), passenger.getPassNic(), passenger.getPassConNum()));
        }
        return passengerDTOS;
    }

    @Override
    public String getLastPassengerId() throws Exception {
        return passengerDAO.getLastpassId();
    }

    @Override
    public PassengerDTO findPassenger(String PassengerId) throws Exception {
        Passenger passenger = passengerDAO.find(PassengerId);
        return new PassengerDTO(passenger.getPassId(), passenger.getPassName(), passenger.getPassNic(), passenger.getPassConNum());
    }

    @Override
    public List<String> getAllPassengerIDs() throws Exception {
        List<Passenger> passengers = passengerDAO.findAll();
        List<String> ids = new ArrayList<>();
        for (Passenger passenger : passengers) {
            ids.add(passenger.getPassId());
        }
        return ids;
    }
}
