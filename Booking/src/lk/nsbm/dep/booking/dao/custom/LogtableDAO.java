package lk.nsbm.dep.booking.dao.custom;

import lk.nsbm.dep.booking.dao.CrudDAO;
import lk.nsbm.dep.booking.entity.Logtable;

public interface LogtableDAO extends CrudDAO<Logtable, String> {

    Logtable checkLoginCredential(String userName, String passWord) throws Exception;

    String getLastuserId() throws Exception;
}
