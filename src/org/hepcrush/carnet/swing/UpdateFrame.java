package org.hepcrush.carnet.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateFrame extends JFrame implements ActionListener{

    Container container = getContentPane();

    JLabel lastNameLabel = new JLabel("Nom :");
    JLabel FirstNameLabel = new JLabel("Prénom :");
    JLabel phoneLabel = new JLabel("Téléphone :");
    JLabel mailLabel = new JLabel("Téléphone :");
    JLabel typeLabel = new JLabel("Type :");

    JTextField lnTextField = new JTextField();
    JTextField fnTextField  = new JTextField();
    JTextField phoneTextField  = new JTextField();
    JTextField mailTextField  = new JTextField();
    JTextField typeTextField = new JTextField();
    JButton updateButton = new JButton("Modifier");

    public UpdateFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    private void addActionEvent() {
        updateButton.addActionListener(this);
    }

    private void setLayoutManager() {
        container.setLayout(null);
    }

    private void addComponentsToContainer() {
        /** Nom **/
        container.add(lastNameLabel);
        /** Prénom **/
        container.add(FirstNameLabel);
        /** Téléphone **/
        container.add(phoneLabel);
        /** Mail **/
        container.add(mailLabel);
        /** Type **/
        container.add(typeLabel);
        /** Modifier **/
        container.add(updateButton);

        container.add(lnTextField);
        container.add(fnTextField);
        container.add(phoneTextField);
        container.add(mailTextField);
        container.add(typeTextField);


    }

    private void setLocationAndSize() {
        lastNameLabel.setBounds(100, 100, 100, 30);
        FirstNameLabel.setBounds(100, 150, 100, 30);
        phoneLabel.setBounds(100, 200, 100, 30);
        mailLabel.setBounds(100, 250, 100, 30);
        typeLabel.setBounds(100, 300, 100, 30);


        lnTextField.setBounds(200, 100, 150, 30);
        fnTextField.setBounds(200, 150, 150, 30);
        phoneTextField.setBounds(200, 200, 150, 30);
        mailTextField.setBounds(200, 250, 150, 30);
        typeTextField.setBounds(200, 300, 150, 30);


        updateButton.setBounds(125, 350, 200, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Update bouton :)
        if (e.getSource() == updateButton) {

            String userLastName;
            String userFirstName;
            String userPhone;
            String userMail;
            String userType;


            userLastName = lnTextField.getText();
            userFirstName = fnTextField.getText();
            userPhone = phoneTextField.getText();
            userMail = mailTextField.getText();
            userType = typeTextField.getText();

            if (userLastName.equals("") && userFirstName.equals("") && userPhone.equals("") && userMail.equals("") && userType.equals("")) {
                JOptionPane.showMessageDialog(this, "Vous n'avez pas rempli toutes les cases demandées.");
            } else {
                JOptionPane.showMessageDialog(this, "Modification réussi avec succès.");
            }

        }
    }
}
