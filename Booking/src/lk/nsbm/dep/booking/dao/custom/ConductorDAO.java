package lk.nsbm.dep.booking.dao.custom;

import lk.nsbm.dep.booking.dao.CrudDAO;
import lk.nsbm.dep.booking.entity.Conductor;

public interface ConductorDAO extends CrudDAO<Conductor, String> {
    String getLastconId() throws Exception;
}
