package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class admin extends JFrame {
    private JPanel panel1;
    private JLabel txtAdmin;
    private JButton btnUsers;
    private JButton btnAnimes;

    public admin() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(panel1);
        setBounds(700,400,450,300);
        panel1.setBorder(new EmptyBorder(5, 5, 5, 5));

        btnAnimes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animes a = new animes();
                a.setVisible(true);
            }
        });
        btnUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user u = new user();
                u.setVisible(true);
            }
        });

    }

}
