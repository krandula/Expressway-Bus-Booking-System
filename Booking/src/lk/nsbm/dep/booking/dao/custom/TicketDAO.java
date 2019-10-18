package lk.nsbm.dep.booking.dao.custom;

import lk.nsbm.dep.booking.dao.CrudDAO;
import lk.nsbm.dep.booking.entity.Ticket;
import lk.nsbm.dep.booking.entity.TicketPK;

public interface TicketDAO extends CrudDAO<Ticket, TicketPK> {

    String getLasticketId() throws Exception;

    boolean existByBusId(String ticketId) throws Exception;
}
