package lk.nsbm.dep.booking.dao.custom.impl;

import lk.nsbm.dep.booking.dao.CrudUtil;
import lk.nsbm.dep.booking.dao.custom.DriverDAO;
import lk.nsbm.dep.booking.entity.Driver;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DriverDAOImpl implements DriverDAO {
    @Override
    public List<Driver> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM driver");
        List<Driver> driver = new ArrayList<>();
        while (rst.next()) {
            driver.add(new Driver(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)));
        }
        return driver;
    }

    @Override
    public Driver find(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM driver WHERE drvId=?", s);
        if (rst.next()) {
            return new Driver(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4));
        }
        return null;
    }

    @Override
    public boolean save(Driver entity) throws Exception {
        return CrudUtil.execute("INSERT INTO driver VALUES (?,?,?,?)",
                entity.getDrvId(),
                entity.getDrvName(),
                entity.getDrvNic(),
                entity.getDrvConNum());
    }

    @Override
    public boolean update(Driver driver) throws Exception {
        return CrudUtil.execute("UPDATE driver SET drvName=?, drvNic=?, drvConNum=?  WHERE drvId=?",
                driver.getDrvId(),
                driver.getDrvName(),
                driver.getDrvNic(),
                driver.getDrvConNum());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM driver WHERE drvId=?", s);
    }

    @Override
    public String getLastdrvId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT drvId FROM driver ORDER BY drvId DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }
}
