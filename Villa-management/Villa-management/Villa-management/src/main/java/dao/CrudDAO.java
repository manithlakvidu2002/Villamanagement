package dao;

import java.sql.SQLException;

public interface CrudDAO<T,ID> extends SuperDAO {
    public boolean save(T dto) throws SQLException, ClassNotFoundException;

    public boolean update(T dto) throws SQLException, ClassNotFoundException;


    public T search(ID id) throws SQLException, ClassNotFoundException ;

    //public ArrayList<T> getAll() throws SQLException, ClassNotFoundException ;

}
