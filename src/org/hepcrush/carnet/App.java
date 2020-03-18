package org.hepcrush.carnet;

import org.hepcrush.carnet.bo.User;
import org.hepcrush.carnet.dal.UserDAO;
import org.hepcrush.carnet.swing.*;

import javax.swing.*;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {
        LoginFrame frame = new LoginFrame();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}
