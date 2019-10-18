package lk.nsbm.dep.booking.business.custom.Impl;

import lk.nsbm.dep.booking.business.custom.LoginBO;
import lk.nsbm.dep.booking.dao.DAOFactory;
import lk.nsbm.dep.booking.dao.DAOTypes;
import lk.nsbm.dep.booking.dao.custom.LogtableDAO;
import lk.nsbm.dep.booking.dto.LogtableDTO;
import lk.nsbm.dep.booking.entity.Logtable;

public class LoginBOImpl implements LoginBO {
    LogtableDAO logtable = DAOFactory.getInstance().getDAO(DAOTypes.LOGIN);

    @Override
    public LogtableDTO checkLoginCredential(String userName, String passWord) throws Exception {
        Logtable login = logtable.checkLoginCredential(userName, passWord);

        if (login != null) {
            return new LogtableDTO(login.getUserID(), login.getUserName(), login.getPasWord());
        }
        return null;
    }

    @Override
    public String getLastuserId() throws Exception {
        return logtable.getLastuserId();
    }

    @Override
    public boolean saveLogin(LogtableDTO log) throws Exception {
        return logtable.save(new Logtable(log.getUserID(), log.getUserName(), log.getPasWord()));
    }
}
