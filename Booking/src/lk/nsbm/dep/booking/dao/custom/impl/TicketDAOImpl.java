package lk.nsbm.dep.booking.dao.custom.impl;

import lk.nsbm.dep.booking.dao.CrudUtil;
import lk.nsbm.dep.booking.dao.custom.TicketDAO;
import lk.nsbm.dep.booking.entity.Ticket;
import lk.nsbm.dep.booking.entity.TicketPK;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOImpl implements TicketDAO {
    @Override
    public List<Ticket> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM ticket");
        List<Ticket> ticket = new ArrayList<>();
        while (rst.next()) {
            ticket.add(new Ticket(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDate(4),
                    rst.getString(5),
                    rst.getInt(6),
                    rst.getInt(7)));
        }
        return ticket;
    }

    @Override
    public Ticket find(TicketPK ticketPK) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM ticket WHERE  passId=? AND busId=?", ticketPK);
        if (rst.next()) {
            return new Ticket(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDate(4),
                    rst.getString(5),
                    rst.getInt(6),
                    rst.getInt(7));
        }
        return null;
    }

    @Override
    public boolean save(Ticket ticket) throws Exception {
        return CrudUtil.execute("INSERT INTO ticket VALUES (?,?,?,?,?,?,?)",
                ticket.getTripNo(),
                ticket.getTicketPK().getPassId(),
                ticket.getTicketPK().getBusId(),
                ticket.getDate(),
                ticket.getTripNo(),
                ticket.getSeatNo(),
                ticket.getPrice());
    }

    @Override
    public boolean update(Ticket ticket) throws Exception {
        return CrudUtil.execute("UPDATE ticket SET ticketNo=?, passId=?, drvId=?, date=?, tripNo=?, seatNo=?, price=? WHERE passId=? AND passId=? AND busId",
                ticket.getTripNo(),
                ticket.getTicketPK().getPassId(),
                ticket.getTicketPK().getBusId(),
                ticket.getDate(),
                ticket.getTripNo(),
                ticket.getSeatNo(),
                ticket.getPrice());
    }

    @Override
    public boolean delete(TicketPK ticketPK) throws Exception {
        return CrudUtil.execute("DELETE FROM passenger WHERE passId=? AND busId=?", ticketPK);
    }

    @Override
    public String getLasticketId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT passId=? AND busId FROM ticket ORDER BY conId DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean existByBusId(String ticketId) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Ticket WHERE busId=?", ticketId);
        return rst.next();
    }
}
