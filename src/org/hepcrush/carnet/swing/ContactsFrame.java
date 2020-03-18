package org.hepcrush.carnet.swing;

import org.hepcrush.carnet.bo.Contact;
import org.hepcrush.carnet.bo.User;
import org.hepcrush.carnet.dal.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Set;

public class ContactsFrame extends JFrame {
    Container container= getContentPane();
    String[] columnName = {"Prénom","Nom","Email","Phone","Type"};
    public ContactsFrame(User user) throws SQLException, ClassNotFoundException {
        addComponentsToContainer(user);
    }

    public Object[][] getJtable(User user) throws SQLException, ClassNotFoundException {

        UserDAO userDAO = new UserDAO();
        Set<Contact> contactsList = userDAO.getContacts(user);
        int i= (int) contactsList.stream().count();
        Object[][] list = new Object[i][5];
        i =0;
        for(Contact item: contactsList){
            list[i][0]= item.getFirstname();
            list[i][1]= item.getLastname();
            list[i][2]= item.getEmail();
            list[i][3]= item.getPhone();
            list[i][4]= item.getContacttype();
            i++;
        }
        return list;
    }


    public void addComponentsToContainer(User user) throws SQLException, ClassNotFoundException {
        JLabel userLabel = new JLabel("Bienvenu "+user.getName());
        JTable jt = new JTable(getJtable(user),columnName);
        container.add(userLabel);
        container.add(jt.getTableHeader(), BorderLayout.NORTH);
        container.add(jt,BorderLayout.CENTER);
    }
}
