package lk.nsbm.dep.booking.business;

import lk.nsbm.dep.booking.business.custom.Impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstance() {
        return (boFactory == null) ? (boFactory = new BOFactory()) : boFactory;
    }

    public <T extends SuperBO> T getBO(BOType boTypes) {
        switch (boTypes) {
            case BUS:
                return (T) new BusBOImpl();
            case LOGIN:
                return (T) new LoginBOImpl();
            case DRIVER:
                return (T) new DriverBOImpl();
            case PASSENGER:
                return (T) new PassengerBOImpl();
            case TICKET:
                return (T) new TicketBOImpl();
            case CONDUCTOR:
                return (T) new ConductorBOImpl();
            default:
                return null;
        }
    }
}
