package bo.custom;

import bo.SuperBO;
import dto.CustomEntityDTO;
import dto.DriverDTO;
import dto.SafaryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SafaryBO extends SuperBO {
     DriverDTO searchDriver(String id) throws SQLException, ClassNotFoundException;
     ArrayList<DriverDTO> getAllDriver() throws SQLException, ClassNotFoundException;
     ArrayList<SafaryDTO> searchAllSafary(String type) throws SQLException, ClassNotFoundException;
     SafaryDTO search(String id) throws SQLException, ClassNotFoundException;
     boolean delete(String id) throws SQLException, ClassNotFoundException;
     boolean save(SafaryDTO dto) throws SQLException, ClassNotFoundException;
     boolean update(SafaryDTO dto) throws SQLException, ClassNotFoundException;
     boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
     ArrayList<CustomEntityDTO> getAll() throws SQLException, ClassNotFoundException;
}
