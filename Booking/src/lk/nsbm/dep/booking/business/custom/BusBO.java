package lk.nsbm.dep.booking.business.custom;

import lk.nsbm.dep.booking.business.SuperBO;
import lk.nsbm.dep.booking.dto.BusDTO;

import java.util.List;

public interface BusBO extends SuperBO {

    boolean saveBus(BusDTO bus) throws Exception;

    boolean updateBus(BusDTO bus) throws Exception;

    boolean deleteBus(String busId) throws Exception;

    List<BusDTO> findAllbus() throws Exception;

    String getLastBusId() throws Exception;

    BusDTO findBus(String busId) throws Exception;

    List<String> getAllBusIDs() throws Exception;
}
