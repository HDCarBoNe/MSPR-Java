package org.hepcrush.carnet.dal;

import org.hepcrush.carnet.bo.User;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class UserDAO {
    private static final String CREATE_QUERY = "INSERT INTO T_USER (name, login, password) VALUES(?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE T_USER SET name=? ,password=? WHERE id=?";
    //private static final String DELETE_QUERY = "INSERT INTO T_USER (name, login, password) VALUES(?,?,?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM T_USER";
    private static final String FIND_LOG_AND_PASS = "SELECT * FROM T_USER WHERE login = ? AND password = ?";
    private static final String FIND_BY_ID = "SELECT * FROM T_USER WHERE id = ?";

    public void create(User user) throws SQLException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (null != connection){
            try (PreparedStatement pst = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)){
                pst.setString(1, user.getName());
                pst.setString(2,user.getLogin());
                pst.setString(3,user.getPassword());
                pst.executeUpdate();
                try (ResultSet rs = pst.getGeneratedKeys()){
                    if (rs.next()){
                        user.setId(rs.getInt(1));
                    }
                }
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

    public User login(String login, String pwd){
        //TODO
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

    public void remove(User user){

    }

}
