package org.hepcrush.carnet.dal;

import org.hepcrush.carnet.bo.Contact;
import org.hepcrush.carnet.bo.User;
import org.hepcrush.carnet.swing.ContactsFrame;

import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class UserDAO {
    private static final String CREATE_QUERY = "INSERT INTO t_user (name, login, password) VALUES(?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE t_user SET name=? ,password=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM t_user WHERE id=?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM t_user";
    private static final String FIND_LOG_AND_PASS = "SELECT * FROM t_user WHERE login = ? AND password = ?";
    private static final String FIND_BY_ID = "SELECT * FROM t_user WHERE id = ?";
    private static final String FIND_ALL_CONTACTS = "SELECT id,firstname,lastname,email,phone,contacttype FROM t_contacts INNER JOIN t_user_contact ON t_contacts.id = t_user_contact.id_contact WHERE t_user_contact.id_user=?";

    private String cryptPwd(String pwd){
        MessageDigest md = null;
        try {
            md = md.getInstance("SHA-1");
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return new String(md.digest(pwd.getBytes()));
    }

    public void create(User user) throws SQLException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (null != connection){
            try (PreparedStatement pst = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)){
                pst.setString(1, user.getName());
                pst.setString(2, user.getLogin());
                pst.setString(3, cryptPwd(user.getPassword()));
                pst.executeUpdate();
            }
        }
    }

    public void update(User user) throws SQLException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (null != connection){
            try (PreparedStatement pst = connection.prepareStatement(UPDATE_QUERY);){
                pst.setString(1,user.getName());
                pst.setString(2,user.getPassword());
                pst.setInt(3,user.getId());
                pst.executeUpdate();
            }
        }
    }

    public User findById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (null != connection){
            try(PreparedStatement pst = connection.prepareStatement(FIND_BY_ID)) {
                pst.setInt(1, id);
                ResultSet rs = pst.executeQuery();
                if (rs.next()){
                    User user = new User(id,rs.getString("name"),rs.getString("login"),rs.getString("password"));
                    return user;
                }
            }
        }
        return null;
    }

    public User login(String login, String pwd) throws SQLException, ClassNotFoundException{
        //TODO
        Connection connection = PersistenceManager.getConnection();
        if (null != connection){
            try(PreparedStatement pst = connection.prepareStatement(FIND_LOG_AND_PASS)) {
                pst.setString(1, login);
                pst.setString(2, cryptPwd(pwd));
                ResultSet rs = pst.executeQuery();
                if (rs.next()){
                    return new User(rs.getInt("id"),rs.getString("name"),rs.getString("login"),rs.getString("password"));
                }
            }
        }
        return null;
    }

    public Set<User> findAll() throws SQLException, ClassNotFoundException {
        Set<User> usersList = new HashSet<>();
        Connection connection = PersistenceManager.getConnection();
        if (null != connection){
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SELECT_ALL_QUERY);
            while (rs.next()){
                User user = new User(rs.getInt("id"),rs.getString("name"),rs.getString("login"),rs.getString("password"));
                usersList.add(user);
            }
            st.close();
            rs.close();
        }
        return usersList;
    }

    public Set<Contact> getContacts(User user) throws SQLException, ClassNotFoundException {
        Set<Contact> contactslist = new HashSet<>();
        Connection connection = PersistenceManager.getConnection();
        if (null != connection){
            try (PreparedStatement pst = connection.prepareStatement(FIND_ALL_CONTACTS)){
                pst.setInt(1,user.getId());
                ResultSet rs = pst.executeQuery();
                while (rs.next()){
                    Contact contact = new Contact(rs.getInt("id"),rs.getString("firstname"),rs.getString("lastname"),rs.getString("email"),rs.getString("phone"),rs.getString("contacttype"));
                    contactslist.add(contact);
                }
                pst.close();
                rs.close();
            }
        }
        return contactslist;
    }

    public void remove(User user) throws SQLException, ClassNotFoundException{
        Connection connection = PersistenceManager.getConnection();
        if (null != connection){
            try(PreparedStatement pst = connection.prepareStatement(DELETE_QUERY)) {
                pst.setInt(1, user.getId());
                ResultSet rs = pst.executeQuery();
            }
        }
    }

}
