package org.hepcrush.carnet.swing;

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

/*    private void reload(){
        System.out.println("Reload en cours");
        super.invalidate();
        super.validate();
        super.repaint();
    }
*/
    private class AddContact extends AbstractAction{
        private AddContact(){
            super("Ajouter");
        }
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Contact michmich = new Contact("test","test","test@test.fr","06060606","Pro");
            try {
                new ContactDAO().create(michmich, userlog);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void addComponentsToContainer(User user) throws SQLException, ClassNotFoundException {
        JLabel userLabel = new JLabel("Bienvenu "+user.getName());
        jt = new JTable(getJtable(user),columnName);
        JPanel jp = new JPanel();
        jp.add(new JButton(new AddContact()));
        container.add(userLabel);
        container.add(jt.getTableHeader(), BorderLayout.NORTH);
        container.add(jt,BorderLayout.CENTER);
        container.add(jp,BorderLayout.SOUTH);
    }
}
