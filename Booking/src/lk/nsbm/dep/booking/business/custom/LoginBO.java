package lk.nsbm.dep.booking.business.custom;

import lk.nsbm.dep.booking.business.SuperBO;
import lk.nsbm.dep.booking.dto.LogtableDTO;

public interface LoginBO extends SuperBO {

    LogtableDTO checkLoginCredential(String userName, String passWord) throws Exception;

    String getLastuserId() throws Exception;

    boolean saveLogin(LogtableDTO log) throws Exception;

}
