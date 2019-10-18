package lk.nsbm.dep.booking.business.custom;

import lk.nsbm.dep.booking.business.SuperBO;
import lk.nsbm.dep.booking.dto.TicketDTO;

import java.util.List;

public interface TicketBO extends SuperBO {

    boolean saveTicket(TicketDTO ticket) throws Exception;

    boolean updateTicket(TicketDTO ticket) throws Exception;

    boolean deleteTicket(String TicketId) throws Exception;

    List<TicketDTO> findAllTicket() throws Exception;

    String getLastTicketId() throws Exception;

    TicketDTO findTicket(String PassengerId) throws Exception;

    List<String> getAllTicketIDs() throws Exception;
}
