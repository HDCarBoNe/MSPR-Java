package org.hepcrush.carnet.swing;

import javafx.scene.layout.HBox;
import org.hepcrush.carnet.bo.Contact;
import org.hepcrush.carnet.bo.User;
import org.hepcrush.carnet.dal.ContactDAO;
import org.hepcrush.carnet.dal.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Set;

public class ContactsFrame extends JFrame {
    Container container= getContentPane();
    String[] columnName = {"Prénom","Nom","Email","Phone","Type"};
    JButton editButton = new JButton("Editer");
    JButton displayButton = new JButton("Afficher");
    JTable jt = new JTable();
    User userlog;

    public ContactsFrame(User user) throws SQLException, ClassNotFoundException {
        addComponentsToContainer(user);
        userlog = user;
    }

    public Object[][] getJtable(User user) throws SQLException, ClassNotFoundException {

        UserDAO userDAO = new UserDAO();
        Set<Contact> contactsList = userDAO.getContacts(user);
        int i= (int) contactsList.stream().count();
        Object[][] list = new Object[i+1][6];
        i =0;
        for(Contact item: contactsList){
            list[i][0]= item.getFirstname();
            list[i][1]= item.getLastname();
            list[i][2]= item.getEmail();
            list[i][3]= item.getPhone();
            list[i][4]= item.getContacttype();
            list[i][5]=
            i++;
        }
        return list;
    }

    private class addContact extends AbstractAction{
        private addContact(){
            super("Ajouter");
        }
        @Override
        public void actionPerformed(ActionEvent actionEvent){

        }
    }

    private class editContact extends AbstractAction{
        private editContact(){
            super("Modifier");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Id :"+ e.getID());
            System.out.println("object :"+ e.getSource());
        }
    }

    private class deleteContact extends AbstractAction{
        private deleteContact(){
            super("Supprimer");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Id :"+ e.getID());
            System.out.println("object :"+ e.getSource());
        }
    }

    public void addComponentsToContainer(User user) throws SQLException, ClassNotFoundException {
        JLabel userLabel = new JLabel("Bienvenu "+user.getName());
        jt = new JTable(getJtable(user),columnName);
        JPanel jp = new JPanel();
        jp.add(new JButton(new addContact()));
        jp.add(new JButton(new editContact()));
        jp.add(new JButton(new deleteContact()));
        JPanel jp2 = new JPanel();
        JScrollPane scrollPane = new JScrollPane(jt);
        jp2.add(scrollPane);
        container.add(userLabel);
        container.add(jp2, BorderLayout.NORTH);
        container.add(jp,BorderLayout.SOUTH);
    }
    
}
