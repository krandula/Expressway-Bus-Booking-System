package lk.nsbm.dep.booking.dao.custom;

import lk.nsbm.dep.booking.dao.CrudDAO;
import lk.nsbm.dep.booking.entity.Bus;

public interface BusDAO extends CrudDAO<Bus, String> {

    String getLastbusId() throws Exception;

}
