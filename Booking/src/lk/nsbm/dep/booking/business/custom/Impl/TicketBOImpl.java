package lk.nsbm.dep.booking.business.custom.Impl;

import lk.nsbm.dep.booking.business.custom.TicketBO;
import lk.nsbm.dep.booking.dao.DAOFactory;
import lk.nsbm.dep.booking.dao.DAOTypes;
import lk.nsbm.dep.booking.dao.custom.TicketDAO;
import lk.nsbm.dep.booking.dto.TicketDTO;

import java.util.List;

public class TicketBOImpl implements TicketBO {

    private TicketDAO ticketDAO = DAOFactory.getInstance().getDAO(DAOTypes.TICKET);

    @Override
    public boolean saveTicket(TicketDTO ticket) throws Exception {
//        return ticketDAO.save(new Ticket(ticket.getTicketId(),ticket.getDate(),ticket.getTripNo(),ticket.getSeatNo(),ticket.getPrice()()));
        return false;
    }

    @Override
    public boolean updateTicket(TicketDTO ticket) throws Exception {
        return false;
    }

    @Override
    public boolean deleteTicket(String TicketId) throws Exception {
        return false;
    }

    @Override
    public List<TicketDTO> findAllTicket() throws Exception {
        return null;
    }

    @Override
    public String getLastTicketId() throws Exception {
        return null;
    }

    @Override
    public TicketDTO findTicket(String PassengerId) throws Exception {
        return null;
    }

    @Override
    public List<String> getAllTicketIDs() throws Exception {
        return null;
    }
}
