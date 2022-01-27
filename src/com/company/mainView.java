package com.company;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class mainView extends JFrame {
    private JPanel panel1;
    private JLabel txtNom;
    private JLabel txtPass;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton btnLogin;
    private JButton btnRegistro;

    private ObjectContainer db = DataConnection.getInstance();

    public mainView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setBounds(700,400,450,300);
        panel1.setBorder(new EmptyBorder(5, 5, 5, 5));

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        btnRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register r = new register();
                r.setVisible(true);

            }
        });

    }

    void login() {
        List<Persona> personas = db.query(new Predicate<Persona>() {
            @Override
            public boolean match(Persona p) {
                return p.getNombre().equals(textField1.getText()) && p.getPassword().equals(new String(passwordField1.getText()));
            }

        });

        if (personas.size() > 0) {
            if (personas.get(0).isAdmin()) {
                //admin
                admin a = new admin();
                a.setVisible(true);

            } else {
                //user
                animesUser u = new animesUser();
                u.setVisible(true);

            }

        } else {
            JOptionPane.showMessageDialog(panel1, "Login incorrecto");
        }

    }
}
