package org.hepcrush.carnet.dal;

import org.hepcrush.carnet.bo.Contact;

import java.sql.Connection;
import java.sql.SQLException;

public class ContactDAO {
    private static final String CREATE_QUERY = "INSERT INTO T_CONTACT (firstname, lastname, email, phone, contacttype) VALUES(?,?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE INTO T_CONTACT SET firstname=?, lastname=?, email=?, phone=?, contacttype=?";
    private static final String DELETE_QUERY = "DELETE FROM T_CONTACT WHERE id=?";
    private static final String SELECT_QUERY = "SELECT * FROM T_CONTACT";

    public void create(Contact contact) throws SQLException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if(null != connection){
            //TODO
        }
    }

    public void update(Contact contact) throws SQLException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (null != connection){
            //TODO
        }
    }

    public void delete(Contact contact) throws SQLException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (null != connection){
            //TODO
        }
    }

    public void findAll(Contact contact) throws SQLException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (null != connection){
            //TODO
        }
    }
}
