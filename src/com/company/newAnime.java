package com.company;

import com.db4o.ObjectContainer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class newAnime extends JFrame {
    private JPanel panel1;
    private JTextField txtTitle;
    private JTextField txtAuthor;
    private JTextField txtStudio;
    private JTextField txtDemo;
    private JTextField txtEpisodes;
    private JButton btnOk;
    private JButton btnCancel;

    private ObjectContainer db = DataConnection.getInstance();

    public newAnime(JTable table1) {
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
                Anime a = new Anime(txtTitle.getText(),txtAuthor.getText(),txtStudio.getText(),txtDemo.getText(),Integer.parseInt(txtEpisodes.getText()));
                db.store(a);
                table1.setModel(animes.setModel());
                dispose();

            }
        });

    }

}
