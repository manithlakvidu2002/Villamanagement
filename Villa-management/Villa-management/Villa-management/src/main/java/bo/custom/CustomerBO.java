package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;
import dto.SafaryDTO;
import entity.Customer;
import entity.Safary;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
     boolean CustomerSave(CustomerDTO dto) throws SQLException, ClassNotFoundException;
     boolean CustomerUpdate(CustomerDTO dto) throws SQLException, ClassNotFoundException;
     boolean CustomerDelete(String id) throws SQLException, ClassNotFoundException;
     CustomerDTO SearchCustomer(String id) throws SQLException, ClassNotFoundException;
     ArrayList<CustomerDTO> LoadAllCustomer() throws SQLException, ClassNotFoundException;
     ArrayList<SafaryDTO> SearchAllSafary(String newValue) throws SQLException, ClassNotFoundException;
     boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
}
