package com.company;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class user extends JFrame {
    private JPanel panel1;
    private JTable table1;
    private JButton btnNew;
    private JButton btnUpdate;
    private JButton btnDelete;

    public user() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(panel1);
        setBounds(700,400,450,300);
        panel1.setBorder(new EmptyBorder(5, 5, 5, 5));

        table1.setModel(setModel());

        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newUser u = new newUser(table1);
                u.setVisible(true);
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

                    String user = (String) m.getValueAt(row, 0);

                    List<Persona> personas = db.query(new Predicate<Persona>() {
                        @Override
                        public boolean match(Persona persona) {
                            return persona.getNombre().equals(user);
                        }
                    });

                    if (personas.size() > 0) {
                        updateUser u = new updateUser(table1);
                        u.setTxtName(personas.get(0).getNombre());
                        u.setTxtPass(personas.get(0).getPassword());
                        u.setBtnAdmin(personas.get(0).isAdmin());
                        db.delete(personas.get(0));
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
                    int resp = JOptionPane.showConfirmDialog(null, "¿Seguro?", "Eliminar", JOptionPane.YES_NO_OPTION);

                    if (resp == JOptionPane.YES_OPTION) {
                        ObjectContainer db = DataConnection.getInstance();
                        DefaultTableModel m = (DefaultTableModel) table1.getModel();

                        String name = (String) m.getValueAt(row, 0);

                        List<Persona> personas = db.query(new Predicate<Persona>() {
                            @Override
                            public boolean match(Persona persona) {
                                return persona.getNombre().equals(name);
                            }
                        });

                        if (personas.size() > 0) {
                            db.delete(personas.get(0));
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
        String[] titles = {"Nombre", "Es Admin"};
        DefaultTableModel m = new DefaultTableModel(null, titles);

        try {
            List<Persona> personas = db.query(new Predicate<Persona>() {
                @Override
                public boolean match(Persona persona) {
                    return true;
                }
            });

            String[] row = new String[2];

            for (Persona p: personas) {
                row[0] = p.getNombre();
                if (p.isAdmin()) {
                    row[1] = "Sí";
                } else {
                    row[1] = "No";
                }

                m.addRow(row);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return m;

    }

}
