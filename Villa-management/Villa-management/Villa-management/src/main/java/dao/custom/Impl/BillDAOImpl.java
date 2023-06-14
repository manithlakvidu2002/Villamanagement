package dao.custom.Impl;

import dao.custom.BillDAO;
import entity.Bill;
import dao.custom.Impl.util.CrudUtil;

import java.sql.SQLException;

public class BillDAOImpl implements BillDAO {

    public boolean save(Bill dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO bill VALUES (?,?,?,?)",
                dto.getCusId(),
                dto.getBill(),
                dto.getCash(),
                dto.getBalance()
        );
    }

    public boolean update(Bill dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE bill SET bill=?, cash=?, balance=? WHERE cusId=?",
                dto.getBill(),
                dto.getCash(),
                dto.getBalance(),
                dto.getCusId());
    }

    @Override
    public Bill search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean delete(String cusId) throws SQLException, ClassNotFoundException {
       return CrudUtil.executeUpdate("DELETE FROM bill WHERE cusId=?", cusId);

    }

}
