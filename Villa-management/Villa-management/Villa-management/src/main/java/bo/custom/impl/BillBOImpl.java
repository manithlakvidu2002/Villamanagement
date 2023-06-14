package bo.custom.impl;

import bo.custom.BillBO;
import dao.DaoFactory;
import dao.custom.BillDAO;
import dao.custom.CustomerDAO;
import dao.custom.Impl.BillDAOImpl;
import dao.custom.Impl.CustomerDAOImpl;
import dao.custom.Impl.QuaryDAOImpl;
import dao.custom.QuaryDAO;
import dto.*;

import entity.Bill;

import entity.CustomEntity;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class BillBOImpl implements BillBO {
    BillDAO billDAO = DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.BILL);
    CustomerDAO customerDAO = DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.CUSTOMER);
    QuaryDAO quaryDAO = DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.QUARY_DAO);

    public ArrayList<CustomEntityDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<CustomEntity> bill = quaryDAO.getAllBill();
        ArrayList<CustomEntityDTO> dto = new ArrayList<>();
        for (CustomEntity b: bill) {
            dto.add(new CustomEntityDTO(b.getCusId(),b.getName(),b.getContact(),b.getSafaryType(),b.getRoomId(),b.getRoomType(),b.getRoomPrice(),b.getPayment(),b.getBill(),b.getCash(),b.getBalance()));
        }
        return dto;
    }
    public  ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> cus = customerDAO.getAll();
        ArrayList<CustomerDTO> dto = new ArrayList<>();
        for (Customer c: cus) {
            dto.add(new CustomerDTO(c.getCusId(),c.getName(),c.getAddress(),c.getDob(),c.getNic(),c.getContact(),c.getSex(),c.getSafaryId(),c.getSafaryType()));
        }
        return dto;
    }
    public boolean delete(String cusId) throws SQLException, ClassNotFoundException {
        return billDAO.delete(cusId);
    }
    public boolean save(BillDTO dto) throws SQLException, ClassNotFoundException {
        return billDAO.save(new Bill(dto.getCusId(), dto.getBill(),dto.getCash(),dto.getBalance()));
    }
    public boolean update(BillDTO dto) throws SQLException, ClassNotFoundException {
        return billDAO.update(new Bill(dto.getCusId(), dto.getBill(),dto.getCash(),dto.getBalance()));

    }
    public CustomEntityDTO search(String id) throws SQLException, ClassNotFoundException {
        CustomEntity c = (CustomEntity) quaryDAO.search(id);
        if(c != null) {
            return new CustomEntityDTO(c.getCusId(),c.getName(),c.getContact(),c.getSafaryType(),c.getRoomId(),c.getRoomType(),c.getRoomPrice(),c.getPayment(),c.getBill(),c.getCash(),c.getBalance());
        }
        return null;
    }
    public CustomEntityDTO searchCust(String id) throws SQLException, ClassNotFoundException {
        CustomEntity c = quaryDAO.searchCust(id);
        if(c != null) {
            return new CustomEntityDTO(c.getName(),c.getContact(),c.getRoomId(),c.getRoomType(),c.getRoomPrice());
        }
        return null;
    }
}
