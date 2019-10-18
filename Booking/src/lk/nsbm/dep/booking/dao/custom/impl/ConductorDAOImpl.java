package lk.nsbm.dep.booking.dao.custom.impl;

import lk.nsbm.dep.booking.dao.CrudUtil;
import lk.nsbm.dep.booking.dao.custom.ConductorDAO;
import lk.nsbm.dep.booking.entity.Conductor;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ConductorDAOImpl implements ConductorDAO {
    @Override
    public List<Conductor> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM conductor");
        List<Conductor> conductors = new ArrayList<>();
        while (rst.next()) {
            conductors.add(new Conductor(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)));
        }
        return conductors;
    }

    @Override
    public Conductor find(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM conductor WHERE conId=?", s);
        if (rst.next()) {
            return new Conductor(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4));
        }
        return null;
    }

    @Override
    public boolean save(Conductor entity) throws Exception {
        return CrudUtil.execute("INSERT INTO conductor VALUES (?,?,?,?)",
                entity.getConId(),
                entity.getConName(),
                entity.getConNic(),
                entity.getConConNum());
    }

    @Override
    public boolean update(Conductor conductor) throws Exception {
        return CrudUtil.execute("UPDATE conductor SET conName=?, conNic=?, conConNum=? WHERE conId=?",
                conductor.getConId(),
                conductor.getConName(),
                conductor.getConNic(),
                conductor.getConConNum());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM conductor WHERE conId=?", s);
    }

    @Override
    public String getLastconId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT conId FROM conductor ORDER BY conId DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }
}
