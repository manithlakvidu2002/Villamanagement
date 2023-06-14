package bo.custom;

import bo.SuperBO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    public boolean existEmployee(String name,String psw) throws SQLException, ClassNotFoundException;
}
