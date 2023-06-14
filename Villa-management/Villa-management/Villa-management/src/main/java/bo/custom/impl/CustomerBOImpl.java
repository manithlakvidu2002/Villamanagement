package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.DaoFactory;
import dao.custom.CustomerDAO;
import dao.custom.Impl.CustomerDAOImpl;
import dao.custom.Impl.SafaryDAOImpl;
import dao.custom.SafaryDAO;
import dto.CustomerDTO;
import dto.SafaryDTO;
import entity.Customer;
import entity.Safary;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.CUSTOMER);
    SafaryDAO safaryDAO =DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.SAFARY);
    public boolean CustomerSave(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getCusId(),dto.getName(),dto.getAddress(),dto.getDob(),dto.getNic(),dto.getContact(),dto.getSex(),dto.getSafaryId(), dto.getSafaryType()));
    }
    public boolean CustomerUpdate(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getCusId(),dto.getName(),dto.getAddress(),dto.getDob(),dto.getNic(),dto.getContact(),dto.getSex(),dto.getSafaryId(), dto.getSafaryType()));
    }
    public boolean CustomerDelete(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }
    public CustomerDTO SearchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer c = customerDAO.search(id);
        if(c != null) {
            return new CustomerDTO(c.getCusId(),c.getName(),c.getAddress(),c.getDob(),c.getNic(),c.getContact(),c.getSex(),c.getSafaryId(),c.getSafaryType());
        }
        return null;
    }
    public ArrayList<CustomerDTO> LoadAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> alls = customerDAO.getAll();
        ArrayList<CustomerDTO> dto = new ArrayList<>();
        for (Customer c: alls) {
            dto.add(new CustomerDTO(c.getCusId(),c.getName(),c.getAddress(),c.getDob(),c.getNic(),c.getContact(),c.getSex(),c.getSafaryId(),c.getSafaryType()));
        }
        return dto;
    }

    // Not Completed
    public ArrayList<SafaryDTO> SearchAllSafary(String newValue) throws SQLException, ClassNotFoundException {
        ArrayList<Safary> safary = safaryDAO.searchAllSafary(newValue);
        ArrayList<SafaryDTO> dto = new ArrayList<>();
        for (Safary s: safary) {
            dto.add(new SafaryDTO(s.getSafaryId(),s.getType(),s.getDate(),s.getTime(),s.getDriverId()));
        }
        return dto;
    }
    public  boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.existCustomer(id);
    }
}
