package dao.custom.Impl;

import dao.custom.Impl.util.SQLUtil;
import dao.custom.SafaryDAO;
import entity.Safary;
import dao.custom.Impl.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SafaryDAOImpl implements SafaryDAO {
    public boolean save(Safary dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO safary VALUES (?,?,?,?,?)",
                dto.getSafaryId(),
                dto.getType(),
                dto.getDate(),
                dto.getTime(),
                dto.getDriverId()
                );
    }

    public boolean update(Safary dto) throws SQLException, ClassNotFoundException {
      return CrudUtil.executeUpdate("UPDATE safary SET type=?, date=?,time=?,driverId=? WHERE safaryId=?",
               dto.getType(),
               dto.getDate(),
               dto.getTime(),
               dto.getDriverId(),
               dto.getSafaryId()
               );
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM safary WHERE safaryId=?",id);
    }

    public Safary search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("select*from safary where safaryId=?", id);
        if (rst.next()){
           return new Safary(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
        }
        return null;
    }

    public ArrayList<Safary> searchAllSafary(String type) throws SQLException, ClassNotFoundException {
        ArrayList<Safary> all = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("select * from safary where type=?",type);
        while (rst.next()) {
            all.add(new Safary(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return all;
    }

    public int getCount() throws SQLException, ClassNotFoundException {
        int count=0;
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM safary");
        while (rst.next()){
            count++;
        }
        return count;
    }
}
