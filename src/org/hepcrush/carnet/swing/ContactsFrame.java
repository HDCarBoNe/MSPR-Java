package org.hepcrush.carnet.swing;

import javafx.scene.layout.HBox;
import org.hepcrush.carnet.bo.Contact;
import org.hepcrush.carnet.bo.ContactType;
import org.hepcrush.carnet.bo.User;
import org.hepcrush.carnet.dal.ContactDAO;
import org.hepcrush.carnet.dal.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Set;

public class ContactsFrame extends JFrame {
    private JTable tableau;
    private User userlog;
    String[] columnName = {"Prénom","Nom","Email","Phone","Type","Modifier","Supprimer"};

    public ContactsFrame(User user) throws SQLException, ClassNotFoundException {
        userlog = user;
        this.setLocationRelativeTo(null);
        //this.getDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Contacts");
        this.setSize(700,800);
        Object[][] data = getJtable(user);
        JComboBox combo = new JComboBox(ContactType.values());
        ZModel zModel = new ZModel(data,columnName);
        this.tableau = new JTable(zModel);
        this.tableau.setDefaultRenderer(JButton.class, new TableComponent());
        this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);

        JButton ajouter = new JButton("Ajouter un contact");
        ajouter.addActionListener(new MoreListener());
        this.getContentPane().add(ajouter, BorderLayout.SOUTH);
    }

    private Object[][] getJtable(User user) throws SQLException, ClassNotFoundException {

        UserDAO userDAO = new UserDAO();
        Set<Contact> contactsList = userDAO.getContacts(user);
        int i= (int) contactsList.stream().count();
        Object[][] list = new Object[i][7];
        i =0;
        for(Contact item: contactsList){
            list[i][0]= item.getFirstname();
            list[i][1]= item.getLastname();
            list[i][2]= item.getEmail();
            list[i][3]= item.getPhone();
            list[i][4]= item.getContacttype();
            list[i][5]= new JButton("modifier");
            list[i][6]= new JButton("supprimer");
            i++;
        }
        return list;
    }

    public class MoreListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            Contact c =new Contact("CTRLJ","CTRLJ","CTRLJ","CTRLJ","CTRLJ");
            Object[] data = c.toArray();
            ContactDAO contactDAO = new ContactDAO();
            try {
                contactDAO.create(c, userlog);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            ((ZModel)tableau.getModel()).addRow(data);
        }
    }
}
