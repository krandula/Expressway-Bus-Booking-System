package lk.nsbm.dep.booking.business.custom;

import lk.nsbm.dep.booking.business.SuperBO;
import lk.nsbm.dep.booking.dto.DriverDTO;

import java.util.List;

public interface DriverBO extends SuperBO {
    boolean saveDriver(DriverDTO driver) throws Exception;

    boolean updateDriver(DriverDTO driver) throws Exception;

    boolean deleteDriver(String driverId) throws Exception;

    List<DriverDTO> findAllDriver() throws Exception;

    String getLastDriverId() throws Exception;

    DriverDTO findDriver(String driverId) throws Exception;

    List<String> getAllDriverIDs() throws Exception;

}
