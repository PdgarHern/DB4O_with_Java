package com.company;

import com.db4o.ObjectContainer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class newUser extends JFrame {
    private JPanel panel1;
    private JTextField txtNombre;
    private JPasswordField txtPass;
    private JRadioButton btnAdmin;
    private JButton btnOk;
    private JButton btnCancel;

    private ObjectContainer db = DataConnection.getInstance();

    public newUser(JTable table1) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(panel1);
        setBounds(700, 400, 450, 300);
        panel1.setBorder(new EmptyBorder(5, 5, 5, 5));

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Persona p = new Persona(txtNombre.getText(), new String(txtPass.getText()), btnAdmin.isSelected());
                db.store(p);
                table1.setModel(user.setModel());
                dispose();

            }
        });

    }

}
