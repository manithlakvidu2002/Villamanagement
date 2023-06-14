package dao.custom.Impl;

import dao.custom.EmployeeDAO;
import dao.custom.Impl.util.CrudUtil;
import db.DBConnection;
import entity.Employee;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public boolean save(Employee dto) throws SQLException, ClassNotFoundException {
       return CrudUtil.executeUpdate("INSERT INTO employee VALUES (?,?,?,?,?,?,?,?,?)",
                dto.getEmpId(),
                dto.getName(),
                dto.getAddress(),
                dto.getAge(),
                dto.getNic(),
                dto.getContact(),
                dto.getSalary(),
                dto.getUserName(),
                dto.getPassword()
       );
    }
    @Override
    public boolean update(Employee dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE employee SET name=?, address=?,age=?,nic=?,contact=?,salary=?, WHERE empId=?",
                dto.getName(),
                dto.getAddress(),
                dto.getAge(),
                dto.getNic(),
                dto.getContact(),
                dto.getSalary(),
                dto.getEmpId()
                );
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM employee WHERE empid=?", id);
    }
    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("select*from employee where empId=?", id);
        if (rst.next()) {
            return new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9)
            );
        }
        return null;
    }
    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> all = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("select*from employee");
        while (rst.next()) {
            all.add(new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            ));
        }
        return all;
    }

    //----------------------existCustomer--------------------------------------------------------------
    @Override
    public  boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT empId FROM employee WHERE empId=?");
        pstm.setString(1, id);
        return pstm.executeQuery().next();
    }
    @Override
    public boolean existEmployee(String name,String psw) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("select*from employee where userName=? and password=?");
        pstm.setString(1, name);
        pstm.setString(2,psw);
        return pstm.executeQuery().next();
    }
}