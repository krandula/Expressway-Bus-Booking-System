package lk.nsbm.dep.booking.dao.custom.impl;

import lk.nsbm.dep.booking.dao.CrudUtil;
import lk.nsbm.dep.booking.dao.custom.LogtableDAO;
import lk.nsbm.dep.booking.entity.Logtable;

import java.sql.ResultSet;
import java.util.List;

public class LogtableDAOImpl implements LogtableDAO {
    @Override
    public Logtable checkLoginCredential(String userName, String passWord) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM logtable WHERE userName=? AND pasWord=?", userName, passWord);
        if (resultSet.next()) {
            Logtable logtable = new Logtable(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3));
            return logtable;
        }
        return null;
    }

    @Override
    public String getLastuserId() throws Exception {
        ResultSet execute = CrudUtil.execute("SELECT userId FROM logtable ORDER BY userId DESC LIMIT 1");

        if (execute.next()) {
            return execute.getString(1);
        }
        return null;
    }

    @Override
    public List<Logtable> findAll() throws Exception {
        return null;
    }

    @Override
    public Logtable find(String s) throws Exception {
        return null;
    }

    @Override
    public boolean save(Logtable entity) throws Exception {
        return CrudUtil.execute("INSERT INTO logtable VALUE (?,?,?)",
                entity.getUserID(), entity.getUserName(), entity.getPasWord());
    }

    @Override
    public boolean update(Logtable entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }
}


