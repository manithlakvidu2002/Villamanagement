package bo.custom;

import bo.SuperBO;

import java.sql.SQLException;

public interface AdminBO extends SuperBO {
     int getRoomCount() throws SQLException, ClassNotFoundException;
     int getDriverCount() throws SQLException, ClassNotFoundException;
     int getSuffariCount() throws SQLException, ClassNotFoundException;
     int getCoustomerCount() throws SQLException, ClassNotFoundException;
}
