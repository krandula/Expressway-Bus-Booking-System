package lk.nsbm.dep.booking.dao.custom.impl;

import lk.nsbm.dep.booking.dao.CrudUtil;
import lk.nsbm.dep.booking.dao.custom.PassengerDAO;
import lk.nsbm.dep.booking.entity.Passenger;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PassengerDAOImpl implements PassengerDAO {
    @Override
    public List<Passenger> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM passenger");
        List<Passenger> passenger = new ArrayList<>();
        while (rst.next()) {
            passenger.add(new Passenger(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)));
        }
        return passenger;
    }

    @Override
    public Passenger find(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM passenger WHERE passId=?", s);
        if (rst.next()) {
            return new Passenger(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4));
        }
        return null;
    }

    @Override
    public boolean save(Passenger entity) throws Exception {
        return CrudUtil.execute("INSERT INTO passenger VALUES (?,?,?,?)",
                entity.getPassId(),
                entity.getPassName(),
                entity.getPassNic(),
                entity.getPassConNum());
    }

    @Override
    public boolean update(Passenger passenge) throws Exception {
        return CrudUtil.execute("UPDATE passenger SET passName=?, passNic=?, conConNum=? WHERE passId=?",
                passenge.getPassId(),
                passenge.getPassName(),
                passenge.getPassNic(),
                passenge.getPassConNum());

    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM passenger WHERE conId=?", s);
    }

    @Override
    public String getLastpassId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT passId FROM passenger ORDER BY passId DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }
}
