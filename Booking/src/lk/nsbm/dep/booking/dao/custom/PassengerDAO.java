package lk.nsbm.dep.booking.dao.custom;

import lk.nsbm.dep.booking.dao.CrudDAO;
import lk.nsbm.dep.booking.entity.Passenger;

public interface PassengerDAO extends CrudDAO<Passenger, String> {
    String getLastpassId() throws Exception;
}
