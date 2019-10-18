package lk.nsbm.dep.booking.business.custom;

import lk.nsbm.dep.booking.business.SuperBO;
import lk.nsbm.dep.booking.dto.ConductorDTO;
import lk.nsbm.dep.booking.dto.DriverDTO;

import java.util.List;

public interface ConductorBO extends SuperBO {
    boolean saveConductor(ConductorDTO conductor) throws Exception;

    boolean updateConductor(ConductorDTO conductor) throws Exception;

    boolean deleteConductor(String conId) throws Exception;

    List<ConductorDTO> findAllConductor() throws Exception;

    String getLastConId() throws Exception;

    DriverDTO findConductor(String conId) throws Exception;

    List<String> getAllConductorIDs() throws Exception;
}
