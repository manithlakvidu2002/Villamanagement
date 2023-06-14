package dao.custom;

import dao.CrudDAO;
import entity.Safary;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SafaryDAO extends CrudDAO<Safary,String> {

     boolean delete(String id) throws SQLException, ClassNotFoundException ;

     ArrayList<Safary> searchAllSafary(String type) throws SQLException, ClassNotFoundException ;

     int getCount() throws SQLException, ClassNotFoundException;

}
