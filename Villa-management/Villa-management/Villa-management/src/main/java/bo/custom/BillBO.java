package bo.custom;

import bo.SuperBO;
import dto.BillDTO;
import dto.CustomEntityDTO;
import dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BillBO extends SuperBO {

     public ArrayList<CustomEntityDTO> getAll() throws SQLException, ClassNotFoundException;
     public  ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException ;
     public boolean delete(String cusId) throws SQLException, ClassNotFoundException;
     public boolean save(BillDTO dto) throws SQLException, ClassNotFoundException;
     public boolean update(BillDTO dto) throws SQLException, ClassNotFoundException;
     public CustomEntityDTO search(String id) throws SQLException, ClassNotFoundException;
     public CustomEntityDTO searchCust(String id) throws SQLException, ClassNotFoundException;

}
