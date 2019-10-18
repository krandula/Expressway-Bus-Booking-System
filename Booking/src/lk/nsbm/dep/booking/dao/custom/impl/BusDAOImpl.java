package lk.nsbm.dep.booking.dao.custom.impl;

import lk.nsbm.dep.booking.dao.CrudUtil;
import lk.nsbm.dep.booking.dao.custom.BusDAO;
import lk.nsbm.dep.booking.entity.Bus;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BusDAOImpl implements BusDAO {
    @Override
    public List<Bus> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM bus");
        List<Bus> bus = new ArrayList<>();
        while (rst.next()) {
            bus.add(new Bus(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)));
        }
        return bus;
    }

    @Override
    public Bus find(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM bus WHERE busId=?", s);
        if (rst.next()) {
            return new Bus(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6));
        }
        return null;
    }

    @Override
    public boolean save(Bus entity) throws Exception {
        return CrudUtil.execute("INSERT INTO bus VALUES (?,?,?,?,?,?)",
                entity.getBusId(),
                entity.getBusNo(),
                entity.getSeat(),
                entity.getRoute(),
                entity.getDrvID(),
                entity.getConId());
    }

    @Override
    public boolean update(Bus bus) throws Exception {
        return CrudUtil.execute("UPDATE bus SET busNo=?, seat=?, route=?, drvID=?, conId=? WHERE busId=?",
                bus.getBusId(),
                bus.getBusNo(),
                bus.getSeat(),
                bus.getRoute(),
                bus.getDrvID(),
                bus.getConId());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM bus WHERE busId=?", s);
    }

    @Override
    public String getLastbusId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT busId FROM bus ORDER BY busId DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }
}
