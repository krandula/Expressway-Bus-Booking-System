package lk.nsbm.dep.booking.dao.custom;

import lk.nsbm.dep.booking.dao.CrudDAO;
import lk.nsbm.dep.booking.entity.Driver;

public interface DriverDAO extends CrudDAO<Driver, String> {
    String getLastdrvId() throws Exception;
}
