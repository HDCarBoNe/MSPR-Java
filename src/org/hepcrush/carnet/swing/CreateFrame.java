package org.hepcrush.carnet.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateFrame extends JFrame implements ActionListener{
    
    Container container = getContentPane();

    JLabel lastNameLabel = new JLabel("Nom :");
    JLabel FirstNameLabel = new JLabel("Prénom :");
    JLabel userLabel = new JLabel("UserName :");
    JLabel passwordLabel = new JLabel("Mot de passe :");

    JTextField lnTextField = new JTextField();
    JTextField fnTextField  = new JTextField();
    JTextField userTextField  = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton updateButton = new JButton("S'inscrire");

    public CreateFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    private void addActionEvent() {
        updateButton.addActionListener(this);
    }

    private void addComponentsToContainer() {
        /** Nom **/
        container.add(lastNameLabel);
        /** Prénom **/
        container.add(FirstNameLabel);
        /** Username **/
        container.add(userLabel);
        /** Mot de passe **/
        container.add(passwordLabel);
        container.add(passwordField);
        /** Modifier/Ajouter **/
        container.add(updateButton);

        container.add(lnTextField);
        container.add(fnTextField);
        container.add(userTextField);
        container.add(passwordField);

    }

    private void setLocationAndSize() {
        lastNameLabel.setBounds(100, 100, 100, 30);
        FirstNameLabel.setBounds(100, 150, 100, 30);
        userLabel.setBounds(100, 200, 100, 30);
        passwordLabel.setBounds(100, 250, 100, 30);

        lnTextField.setBounds(200, 100, 150, 30);
        fnTextField.setBounds(200, 150, 150, 30);
        userTextField.setBounds(200, 200, 150, 30);
        passwordField.setBounds(200, 250, 150, 30);

        updateButton.setBounds(125, 300, 200, 30);
    }

    private void setLayoutManager() {
        container.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Update bouton :)
        if (e.getSource() == updateButton) {

            String userLastName;
            String userFirstName;
            String userUsername;
            String userPwd;


            userLastName = lnTextField.getText();
            userFirstName = fnTextField.getText();
            userUsername = userTextField.getText();
            userPwd = passwordField.getText();

            if (userLastName.equals("") && userFirstName.equals("") && userUsername.equals("") && userPwd.equals("")) {
                JOptionPane.showMessageDialog(this, "Vous n'avez pas rempli toutes les cases demandées.");
            } else {
                JOptionPane.showMessageDialog(this, "Modification réussi avec succès.");
            }

        }
        }
}

