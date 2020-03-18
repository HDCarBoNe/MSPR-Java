package org.hepcrush.carnet.swing;

import org.hepcrush.carnet.bo.User;
import org.hepcrush.carnet.dal.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ContactsFrame extends JFrame {
    Container container= getContentPane();
    public ContactsFrame(User user) throws SQLException, ClassNotFoundException {
        addComponentsToContainer(user);
    }

    public void Jtable(User user) throws SQLException, ClassNotFoundException {
        String[] columnName = {"Prénom","Nom","Email","Phone","Type"};
        UserDAO userDAO = new UserDAO();
        Object data = userDAO.getContacts(user);

    }

    public void addComponentsToContainer(User user) throws SQLException, ClassNotFoundException {
        JLabel userLabel = new JLabel("Bienvenu "+user.getName());
        container.add(userLabel);
        container.add(Jtable(user));
    }
}
