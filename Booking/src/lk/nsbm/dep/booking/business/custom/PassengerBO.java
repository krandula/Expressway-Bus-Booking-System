package lk.nsbm.dep.booking.business.custom;

import lk.nsbm.dep.booking.business.SuperBO;
import lk.nsbm.dep.booking.dto.PassengerDTO;

import java.util.List;

public interface PassengerBO extends SuperBO {

    boolean savePassenger(PassengerDTO passenger) throws Exception;

    boolean updatePassenger(PassengerDTO passenger) throws Exception;

    boolean deletePassenger(String PassengerId) throws Exception;

    List<PassengerDTO> findAllPassenger() throws Exception;

    String getLastPassengerId() throws Exception;

    PassengerDTO findPassenger(String PassengerId) throws Exception;

    List<String> getAllPassengerIDs() throws Exception;
}
