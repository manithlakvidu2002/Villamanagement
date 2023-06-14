package bo.custom;

import bo.SuperBO;

import java.sql.SQLException;

public interface HomeBO extends SuperBO {
     int getRoomCount() throws SQLException, ClassNotFoundException;
     int getDriverCount() throws SQLException, ClassNotFoundException;
     int getSafaryCount() throws SQLException, ClassNotFoundException;
     int getCoustomerCount() throws SQLException, ClassNotFoundException;
}
