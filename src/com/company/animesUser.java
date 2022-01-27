package com.company;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class animesUser extends JFrame {
    private JPanel panel1;
    private JTable table1;

    private ObjectContainer db = DataConnection.getInstance();

    public animesUser() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(panel1);
        setBounds(600, 300, 800, 450);
        panel1.setBorder(new EmptyBorder(5, 5, 5, 5));

        this.getTable1().setModel(this.setModel());

    }

    DefaultTableModel setModel() {
        String[] titles = {"Title", "Author", "Studio", "Demographic", "Episodes"};
        DefaultTableModel m = new DefaultTableModel(null, titles);

        try {
            List<Anime> animes = db.query(new Predicate<Anime>() {
                @Override
                public boolean match(Anime anime) {
                    return true;
                }
            });

            String[] row = new String[5];

            for (Anime a: animes) {
                row[0] = a.getTitle();
                row[1] = a.getAuthor();
                row[2] = a.getStudio();
                row[3] = a.getDemographic();
                row[4] = "" + a.getEpisodes();

                m.addRow(row);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return m;

    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

}
