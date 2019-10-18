package lk.nsbm.dep.booking.dao;

import lk.nsbm.dep.booking.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? (daoFactory = new DAOFactory()) : daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoType) {
        switch (daoType) {
            case BUS:
                return (T) new BusDAOImpl();
            case LOGIN:
                return (T) new LogtableDAOImpl();
            case CONDUCTOR:
                return (T) new ConductorDAOImpl();
            case DRIVER:
                return (T) new DriverDAOImpl();
            case PASSENGER:
                return (T) new PassengerDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            case TICKET:
                return (T) new TicketDAOImpl();
            default:
                return null;
        }
    }
}
