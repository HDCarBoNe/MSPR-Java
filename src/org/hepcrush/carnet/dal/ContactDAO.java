package org.hepcrush.carnet.dal;

import org.hepcrush.carnet.bo.Contact;
import org.hepcrush.carnet.bo.User;

import java.sql.*;

public class ContactDAO {
    private static final String CREATE_QUERY = "INSERT INTO t_contacts (firstname, lastname, email, phone, contacttype) VALUES(?,?,?,?,?)";
    private static final String CREATE_USER_CONTACT_QUERY = "INSERT INTO t_user_contact (id_contact, id_user) VALUES (?,?)";
    private static final String UPDATE_QUERY = "UPDATE INTO T_CONTACT SET firstname=?, lastname=?, email=?, phone=?, contacttype=?";
    private static final String DELETE_QUERY = "DELETE FROM T_CONTACT WHERE id=?";
    private static final String SELECT_QUERY = "SELECT * FROM T_CONTACT";

    public void create(Contact contact, User user) throws SQLException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if(null != connection){
            try(PreparedStatement pst = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                pst.setString(1, contact.getFirstname());
                pst.setString(2, contact.getLastname());
                pst.setString(3, contact.getEmail());
                pst.setString(4, contact.getPhone());
                pst.setString(5, contact.getContacttype());
                pst.executeUpdate();
                try (ResultSet rs = pst.getGeneratedKeys()){
                    if (rs.next()){
                        int contactid= rs.getInt(1);
                        try (PreparedStatement pst2= connection.prepareStatement(CREATE_USER_CONTACT_QUERY)){
                            pst2.setInt(1,contactid);
                            pst2.setInt(2,user.getId());
                            pst2.executeUpdate();
                        }
                    }
                }
            }
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
