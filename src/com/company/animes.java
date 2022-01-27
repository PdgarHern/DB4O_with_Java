package com.company;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class animes extends JFrame {
    private JPanel panel1;
    private JTable table1;
    private JButton btnNew;
    private JButton btnUpdate;
    private JButton btnDelete;



    public animes() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(panel1);
        setBounds(600, 300, 800, 450);
        panel1.setBorder(new EmptyBorder(5, 5, 5, 5));

        table1.setModel(this.setModel());

        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newAnime n = new newAnime(table1);
                n.setVisible(true);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();

                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Nada seleccionado");

                } else {
                    ObjectContainer db = DataConnection.getInstance();
                    DefaultTableModel m = (DefaultTableModel) table1.getModel();

                    String title = (String) m.getValueAt(row, 0);

                    List<Anime> animes = db.query(new Predicate<Anime>() {
                        @Override
                        public boolean match(Anime anime) {
                            return anime.getTitle().equals(title);
                        }
                    });

                    if (animes.size() > 0) {
                        updateAnime u = new updateAnime(table1);
                        u.setTxtTitle(animes.get(0).getTitle());
                        u.setTxtAuthor(animes.get(0).getAuthor());
                        u.setTxtStudio(animes.get(0).getStudio());
                        u.setTxtDemo(animes.get(0).getDemographic());
                        u.setTxtEpisodes("" + animes.get(0).getEpisodes());
                        db.delete(animes.get(0));
                        u.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
                    }

                }
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();

                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Nada seleccionado");

                } else {
                    int resp = JOptionPane.showConfirmDialog(null, "¿Seguro?","Eliminar",JOptionPane.YES_NO_OPTION);

                    if (resp == JOptionPane.YES_OPTION) {
                        ObjectContainer db = DataConnection.getInstance();
                        DefaultTableModel m = (DefaultTableModel) table1.getModel();

                        String title = (String) m.getValueAt(row, 0);

                        List<Anime> animes = db.query(new Predicate<Anime>() {
                            @Override
                            public boolean match(Anime anime) {
                                return anime.getTitle().equals(title);
                            }
                        });

                        if (animes.size() > 0) {
                            db.delete(animes.get(0));
                            table1.setModel(setModel());
                        } else {
                            JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
                        }

                    }

                }
            }
        });

    }

    static DefaultTableModel setModel() {
        ObjectContainer db = DataConnection.getInstance();
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
