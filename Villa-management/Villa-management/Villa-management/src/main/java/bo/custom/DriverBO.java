package bo.custom;

import bo.SuperBO;
import dto.DriverDTO;
import entity.Driver;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DriverBO extends SuperBO {
    public ArrayList<DriverDTO> LoadAllDriver() throws SQLException, ClassNotFoundException;

    public boolean DriverSave(DriverDTO dto) throws SQLException, ClassNotFoundException;

    public boolean DriverUpdate(DriverDTO dto) throws SQLException, ClassNotFoundException;

    public boolean DriverDelete(String id) throws SQLException, ClassNotFoundException;

    public DriverDTO SearchDriver(String id) throws SQLException, ClassNotFoundException;

    public  boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
}
