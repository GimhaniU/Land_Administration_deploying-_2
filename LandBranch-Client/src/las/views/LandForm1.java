/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package las.views;

import SeverConnector.Connector;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import las.common_classes.GUIitemsValidator;
import las.common_classes.IdGenerator;
import las.common_classes.PatternChecker;
import las.controller.GramaNiladariDivisionController;
import las.controller.LandController;
import las.controller.LotController;
import las.models.GramaNiladariDivision;
import las.models.Land;
import las.models.Lot;

/**
 *
 * @author Gimhani Uthpala
 */
public class LandForm1 extends javax.swing.JInternalFrame {

    LotController LotController;
    LandController LandController;
    GramaNiladariDivisionController GramaNiladariDivisionController;
    /**
     * Creates new form LandForm1
     */
    public LandForm1() {
        initComponents();
        
        try {
            Connector sConnector = Connector.getSConnector();
            LotController = sConnector.getlotController();
            LandController = sConnector.getLandController();
            GramaNiladariDivisionController = sConnector.getGramaNiladariDivisionController();
        } catch (RemoteException | SQLException | NotBoundException | MalformedURLException | ClassNotFoundException ex) {
            Logger.getLogger(ApplicantForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        landnotvalidlabel.setVisible(false);
        acresnotvalidlabel.setVisible(false);
        perchesnotvalidlabel.setVisible(false);
        roodsnotvalidlabel.setVisible(false);
        gnd_name_test.setEditable(false);
        land_north_test.setVisible(false);
        land_east_test.setVisible(false);
        land_west_test.setVisible(false);
        land_south_test.setVisible(false);
        land_south_test.setVisible(false);
        land_west_test.setVisible(false);
        boundednorthnotvalidlabel.setVisible(false);
        bounedeastnotvalidlabel.setVisible(false);
        bounedwestnotvalidlabel.setVisible(false);
        bounedsouthnotvalidlabel.setVisible(false);
        editlandnamenotvalidlabel.setVisible(false);
        add_lot_buttun.setEnabled(false);
        updateButton.setEnabled(true);

        try {
            Lot lastAddedLot = LotController.getLastAddedLot();
            lot_number_test.setText(IdGenerator.generateNextLotNumber(lastAddedLot.getLotNumber()));
        } catch (ClassNotFoundException | SQLException | RemoteException ex) {
            Logger.getLogger(LandForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //search combo

            //update cobo ekata
            search_planNumber_combo.setEditable(true);
            JTextComponent editorSearchPlanNumberCombo = (JTextComponent) search_planNumber_combo.getEditor().getEditorComponent();
            editorSearchPlanNumberCombo.addKeyListener(new KeyAdapter() {

                @Override
                public void keyReleased(KeyEvent e) {
                    String item = (String) search_planNumber_combo.getEditor().getItem();
                    ArrayList<Object> list = new ArrayList();
                    try {

                        ArrayList<Land> simmilarPlanNumbers = LandController.getSimmilarPlanNumbers(item);
                        for (int i = 0; i < simmilarPlanNumbers.size(); i++) {
                            list.add(simmilarPlanNumbers.get(i).getPlanNumber());
                        }
                        GUIitemsValidator.addItemToCombo(list, search_planNumber_combo);

                    } catch (ClassNotFoundException | SQLException | RemoteException ex) {
                        Logger.getLogger(LandForm.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            });

            //update cobo ekata
            update_planNumber_combo.setEditable(true);
            JTextComponent editorPlanNumberCombo = (JTextComponent) update_planNumber_combo.getEditor().getEditorComponent();
            editorPlanNumberCombo.addKeyListener(new KeyAdapter() {

                @Override
                public void keyReleased(KeyEvent e) {
                    String item = (String) update_planNumber_combo.getEditor().getItem();
                    ArrayList<Object> list = new ArrayList();
                    try {

                        ArrayList<Land> simmilarPlanNumbers = LandController.getSimmilarPlanNumbers(item);
                        for (int i = 0; i < simmilarPlanNumbers.size(); i++) {
                            list.add(simmilarPlanNumbers.get(i).getPlanNumber());
                        }
                        GUIitemsValidator.addItemToCombo(list, update_planNumber_combo);

                    } catch (ClassNotFoundException | SQLException | RemoteException ex) {
                        Logger.getLogger(LandForm.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            });
            //update gnd combo 
            JTextComponent editorUpdateGndCombo = (JTextComponent) update_gnd_combo.getEditor().getEditorComponent();
            update_gnd_combo.setEditable(true);
            editorUpdateGndCombo.addKeyListener(new KeyAdapter() {

                @Override
                public void keyReleased(KeyEvent e) {
                    String item = (String) update_gnd_combo.getEditor().getItem();
                    ArrayList<Object> list = new ArrayList();
                    try {

                        ArrayList<GramaNiladariDivision> simmilarGndDivisionNumbers = GramaNiladariDivisionController.getSimmilarGndDivisionNumbers(item);
                        for (int i = 0; i < simmilarGndDivisionNumbers.size(); i++) {
                            list.add(simmilarGndDivisionNumbers.get(i).getDivisionNumber());
                        }
                        GUIitemsValidator.addItemToCombo(list, update_gnd_combo);

                    } catch (ClassNotFoundException | SQLException | RemoteException ex) {
                        Logger.getLogger(LandForm.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            });
            Land lastAddedLand = LandController.getLastAddedLand();
            String planNumber = lastAddedLand.getPlanNumber();
            String generateNextPlanNumber = IdGenerator.generateNextPlanNumber(planNumber);
            JTextComponent editor = (JTextComponent) gnd_number_combo.getEditor().getEditorComponent();
            plan_nummber_test.setText(generateNextPlanNumber);
            gnd_number_combo.setEditable(true);
            editor.addKeyListener(new KeyAdapter() {

                @Override
                public void keyReleased(KeyEvent e) {
                    String item = (String) gnd_number_combo.getEditor().getItem();
                    ArrayList<Object> list = new ArrayList();
                    try {

                        ArrayList<GramaNiladariDivision> simmilarGndDivisionNumbers = GramaNiladariDivisionController.getSimmilarGndDivisionNumbers(item);
                        for (int i = 0; i < simmilarGndDivisionNumbers.size(); i++) {
                            list.add(simmilarGndDivisionNumbers.get(i).getDivisionNumber());
                        }
                        GUIitemsValidator.addItemToCombo(list, gnd_number_combo);

                    } catch (ClassNotFoundException | SQLException | RemoteException ex) {
                        Logger.getLogger(LandForm.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            });

        } catch (ClassNotFoundException | SQLException | RemoteException ex) {
            Logger.getLogger(LandForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        update_planNumber_combo = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        update_land_name = new javax.swing.JTextField();
        editlandnamenotvalidlabel = new javax.swing.JLabel();
        update_gnd_combo = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        update_north_test = new javax.swing.JTextField();
        update_east_test = new javax.swing.JTextField();
        update_west_test1 = new javax.swing.JTextField();
        update_south_test1 = new javax.swing.JTextField();
        editbounednorthnotvalidlabel1 = new javax.swing.JLabel();
        editbounedeastnotvalidlabel1 = new javax.swing.JLabel();
        editbounedwestnotvalidlabel1 = new javax.swing.JLabel();
        editbounedsouthnotvalidlabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        update_lot_table = new javax.swing.JTable();
        updateButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        AddNewLand2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        search_land_name = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        search_north_test = new javax.swing.JTextField();
        search_east_test1 = new javax.swing.JTextField();
        search_west_test = new javax.swing.JTextField();
        search_south_test = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        search_lot_table = new javax.swing.JTable();
        search_planNumber_combo = new javax.swing.JComboBox();
        search_gnd_test = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        view_all_table = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        view_all_load_buttun = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        plannolabel = new javax.swing.JLabel();
        plan_nummber_test = new javax.swing.JTextField();
        landnamelabel = new javax.swing.JLabel();
        land_name_test = new javax.swing.JTextField();
        landnotvalidlabel = new javax.swing.JLabel();
        gndnolabel = new javax.swing.JLabel();
        gnd_number_combo = new javax.swing.JComboBox();
        gndlabel = new javax.swing.JLabel();
        gnd_name_test = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        lotnolabel = new javax.swing.JLabel();
        lot_number_test = new javax.swing.JTextField();
        extentlabel = new javax.swing.JLabel();
        acre_test = new javax.swing.JTextField();
        perches_test = new javax.swing.JTextField();
        rood_test = new javax.swing.JTextField();
        acreorhectarelabel = new javax.swing.JLabel();
        percheslabel = new javax.swing.JLabel();
        roodslabel = new javax.swing.JLabel();
        roodsnotvalidlabel = new javax.swing.JLabel();
        perchesnotvalidlabel = new javax.swing.JLabel();
        acresnotvalidlabel = new javax.swing.JLabel();
        add_lot_buttun = new javax.swing.JToggleButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lot_table = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        land_save_buttun = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        land_north_test = new javax.swing.JTextField();
        land_east_test = new javax.swing.JTextField();
        land_west_test = new javax.swing.JTextField();
        land_south_test = new javax.swing.JTextField();
        boundednorthnotvalidlabel = new javax.swing.JLabel();
        bounedeastnotvalidlabel = new javax.swing.JLabel();
        bounedwestnotvalidlabel = new javax.swing.JLabel();
        bounedsouthnotvalidlabel = new javax.swing.JLabel();

        jLabel14.setText("Plan Number:");

        update_planNumber_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                update_planNumber_comboItemStateChanged(evt);
            }
        });
        update_planNumber_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_planNumber_comboActionPerformed(evt);
            }
        });
        update_planNumber_combo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                update_planNumber_comboKeyReleased(evt);
            }
        });

        jLabel21.setText("Land Name:");

        update_land_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                update_land_nameKeyReleased(evt);
            }
        });

        editlandnamenotvalidlabel.setBackground(new java.awt.Color(204, 0, 0));
        editlandnamenotvalidlabel.setForeground(new java.awt.Color(204, 0, 0));
        editlandnamenotvalidlabel.setText("Not Valid");

        update_gnd_combo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                update_gnd_comboKeyReleased(evt);
            }
        });

        jLabel15.setText("Grama Niladhari Division:");

        jLabel27.setText("Bounded -");

        jLabel28.setText("On North By");

        jLabel29.setText("On East By");

        jLabel30.setText("On West By");

        jLabel31.setText("On South By");

        update_north_test.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                update_north_testKeyReleased(evt);
            }
        });

        update_east_test.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                update_east_testKeyReleased(evt);
            }
        });

        update_west_test1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                update_west_test1KeyReleased(evt);
            }
        });

        update_south_test1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                update_south_test1KeyReleased(evt);
            }
        });

        editbounednorthnotvalidlabel1.setBackground(new java.awt.Color(204, 0, 0));
        editbounednorthnotvalidlabel1.setForeground(new java.awt.Color(204, 0, 0));
        editbounednorthnotvalidlabel1.setText("Not Valid");

        editbounedeastnotvalidlabel1.setBackground(new java.awt.Color(204, 0, 0));
        editbounedeastnotvalidlabel1.setForeground(new java.awt.Color(204, 0, 0));
        editbounedeastnotvalidlabel1.setText("Not Valid");

        editbounedwestnotvalidlabel1.setBackground(new java.awt.Color(204, 0, 0));
        editbounedwestnotvalidlabel1.setForeground(new java.awt.Color(204, 0, 0));
        editbounedwestnotvalidlabel1.setText("Not Valid");

        editbounedsouthnotvalidlabel1.setBackground(new java.awt.Color(204, 0, 0));
        editbounedsouthnotvalidlabel1.setForeground(new java.awt.Color(204, 0, 0));
        editbounedsouthnotvalidlabel1.setText("Not Valid");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(13, 13, 13)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jLabel28))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(update_west_test1)
                            .addComponent(update_east_test)
                            .addComponent(update_south_test1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(editbounedeastnotvalidlabel1)
                            .addComponent(editbounedwestnotvalidlabel1)
                            .addComponent(editbounedsouthnotvalidlabel1))
                        .addGap(185, 185, 185))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(update_north_test, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editbounednorthnotvalidlabel1)
                        .addContainerGap(264, Short.MAX_VALUE))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel28))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(update_north_test, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editbounednorthnotvalidlabel1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(update_east_test, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editbounedeastnotvalidlabel1)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(update_west_test1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editbounedwestnotvalidlabel1)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(update_south_test1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editbounedsouthnotvalidlabel1)
                    .addComponent(jLabel31))
                .addContainerGap())
        );

        update_lot_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "LotNo", "Extent (Acres/Hectare)", "Extent (Perches)", "Extent (Roods)"
            }
        ));
        jScrollPane4.setViewportView(update_lot_table);

        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });
        updateButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                updateButtonKeyReleased(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        cancelButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cancelButtonKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(update_land_name, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(update_gnd_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editlandnamenotvalidlabel))
                            .addComponent(update_planNumber_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(updateButton)
                        .addGap(18, 18, 18)
                        .addComponent(cancelButton)
                        .addGap(16, 16, 16)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(update_planNumber_combo, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editlandnamenotvalidlabel))
                        .addGap(16, 16, 16))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(update_land_name)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(update_gnd_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateButton)
                    .addComponent(cancelButton))
                .addGap(231, 231, 231))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(190, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Edit Land Details", jPanel4);

        jLabel16.setText("planNumber");

        jLabel17.setText("Grama Niladhari Division");

        jLabel32.setText("Land Name");

        jLabel33.setText("Bounded -");

        jLabel34.setText("On North By");

        jLabel35.setText("On East By");

        jLabel36.setText("On West By");

        jLabel37.setText("On South By");

        search_east_test1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_east_test1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addGap(18, 18, 18)
                        .addComponent(search_west_test, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(9, 9, 9)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addGap(18, 18, 18)
                                .addComponent(search_south_test, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35)
                                    .addComponent(jLabel34))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(search_north_test, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(search_east_test1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 387, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(search_north_test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_east_test1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_west_test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_south_test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jButton4.setText("Cancel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        search_lot_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "LotNo", "Extent (Acres/Hectare)", "Extent (Perches)", "Extent (Roods)"
            }
        ));
        jScrollPane5.setViewportView(search_lot_table);

        search_planNumber_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                search_planNumber_comboItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout AddNewLand2Layout = new javax.swing.GroupLayout(AddNewLand2);
        AddNewLand2.setLayout(AddNewLand2Layout);
        AddNewLand2Layout.setHorizontalGroup(
            AddNewLand2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddNewLand2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AddNewLand2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddNewLand2Layout.createSequentialGroup()
                        .addGroup(AddNewLand2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(AddNewLand2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(search_land_name)
                            .addComponent(search_planNumber_combo, 0, 115, Short.MAX_VALUE)
                            .addComponent(search_gnd_test)))
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AddNewLand2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton4)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        AddNewLand2Layout.setVerticalGroup(
            AddNewLand2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddNewLand2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AddNewLand2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_planNumber_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddNewLand2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_land_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addGroup(AddNewLand2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(search_gnd_test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(540, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(AddNewLand2);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 916, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Search Land", jPanel5);

        view_all_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PlanNumber", "DivisionNumber", "LandName", "WestBound", "EastBound", "NorthBound", "SouthBound", "NumberOfLot"
            }
        ));
        jScrollPane6.setViewportView(view_all_table);

        view_all_load_buttun.setText("Load");
        view_all_load_buttun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_all_load_buttunActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(view_all_load_buttun, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(view_all_load_buttun)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(591, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("View all lands", jPanel3);

        plannolabel.setText("Plan No:");

        landnamelabel.setText("Land Name:");

        land_name_test.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                land_name_testKeyReleased(evt);
            }
        });

        landnotvalidlabel.setBackground(new java.awt.Color(204, 0, 0));
        landnotvalidlabel.setForeground(new java.awt.Color(204, 0, 0));
        landnotvalidlabel.setText("Not Valid");

        gndnolabel.setText("G.N.D No:");

        gnd_number_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                gnd_number_comboItemStateChanged(evt);
            }
        });
        gnd_number_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gnd_number_comboActionPerformed(evt);
            }
        });
        gnd_number_combo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                gnd_number_comboKeyReleased(evt);
            }
        });

        gndlabel.setText("Grama Niladhari Division:");

        gnd_name_test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gnd_name_testActionPerformed(evt);
            }
        });
        gnd_name_test.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                gnd_name_testKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gndnolabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(landnamelabel)
                    .addComponent(plannolabel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gndlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(land_name_test, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(landnotvalidlabel))
                    .addComponent(plan_nummber_test, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gnd_number_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gnd_name_test, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(195, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(plannolabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(plan_nummber_test, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(landnamelabel)
                    .addComponent(land_name_test, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(landnotvalidlabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gndnolabel)
                    .addComponent(gnd_number_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gndlabel)
                    .addComponent(gnd_name_test, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lotnolabel.setText("Lot No");

        extentlabel.setText("Expected Extent");

        acre_test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acre_testActionPerformed(evt);
            }
        });
        acre_test.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                acre_testKeyReleased(evt);
            }
        });

        perches_test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                perches_testActionPerformed(evt);
            }
        });
        perches_test.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                perches_testKeyReleased(evt);
            }
        });

        rood_test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rood_testActionPerformed(evt);
            }
        });
        rood_test.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rood_testKeyReleased(evt);
            }
        });

        acreorhectarelabel.setText("Acre / Hectare");

        percheslabel.setText("Perches");

        roodslabel.setText("Roods");

        roodsnotvalidlabel.setBackground(new java.awt.Color(204, 0, 0));
        roodsnotvalidlabel.setForeground(new java.awt.Color(204, 0, 0));
        roodsnotvalidlabel.setText("Not Valid");

        perchesnotvalidlabel.setBackground(new java.awt.Color(204, 0, 0));
        perchesnotvalidlabel.setForeground(new java.awt.Color(204, 0, 0));
        perchesnotvalidlabel.setText("Not Valid");

        acresnotvalidlabel.setBackground(new java.awt.Color(204, 0, 0));
        acresnotvalidlabel.setForeground(new java.awt.Color(204, 0, 0));
        acresnotvalidlabel.setText("Not Valid");

        add_lot_buttun.setText("Add Lot");
        add_lot_buttun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_lot_buttunActionPerformed(evt);
            }
        });
        add_lot_buttun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                add_lot_buttunKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lotnolabel)
                    .addComponent(extentlabel))
                .addGap(49, 49, 49)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lot_number_test, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(479, 479, 479))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(acre_test)
                                    .addComponent(perches_test)
                                    .addComponent(rood_test))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(acreorhectarelabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(acresnotvalidlabel))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(percheslabel)
                                            .addComponent(roodslabel))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(perchesnotvalidlabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(roodsnotvalidlabel, javax.swing.GroupLayout.Alignment.TRAILING)))))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(0, 260, Short.MAX_VALUE)
                                .addComponent(add_lot_buttun)
                                .addGap(92, 92, 92)))
                        .addGap(247, 247, 247))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(extentlabel))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lot_number_test, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lotnolabel))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(acre_test, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acreorhectarelabel)
                            .addComponent(acresnotvalidlabel))))
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(perches_test, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(percheslabel)
                    .addComponent(perchesnotvalidlabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rood_test, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roodslabel)
                    .addComponent(roodsnotvalidlabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(add_lot_buttun))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Added Lots"));

        lot_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "LotNo", "Extent (Acres/Hectare)", "Extent (Perches)", "Extent (Roods)"
            }
        ));
        jScrollPane2.setViewportView(lot_table);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        land_save_buttun.setText("Save");
        land_save_buttun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                land_save_buttunActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(land_save_buttun)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(land_save_buttun)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel48.setText("Bounded -");

        jLabel49.setText("On North By");

        jLabel50.setText("On East By");

        jLabel51.setText("On West By");

        jLabel52.setText("On South By");

        land_north_test.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                update_north_test1KeyReleased(evt);
            }
        });

        land_east_test.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                update_east_test1KeyReleased(evt);
            }
        });

        land_west_test.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                update_west_test2KeyReleased(evt);
            }
        });

        land_south_test.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                update_south_test2KeyReleased(evt);
            }
        });

        boundednorthnotvalidlabel.setBackground(new java.awt.Color(204, 0, 0));
        boundednorthnotvalidlabel.setForeground(new java.awt.Color(204, 0, 0));
        boundednorthnotvalidlabel.setText("Not Valid");

        bounedeastnotvalidlabel.setBackground(new java.awt.Color(204, 0, 0));
        bounedeastnotvalidlabel.setForeground(new java.awt.Color(204, 0, 0));
        bounedeastnotvalidlabel.setText("Not Valid");

        bounedwestnotvalidlabel.setBackground(new java.awt.Color(204, 0, 0));
        bounedwestnotvalidlabel.setForeground(new java.awt.Color(204, 0, 0));
        bounedwestnotvalidlabel.setText("Not Valid");

        bounedsouthnotvalidlabel.setBackground(new java.awt.Color(204, 0, 0));
        bounedsouthnotvalidlabel.setForeground(new java.awt.Color(204, 0, 0));
        bounedsouthnotvalidlabel.setText("Not Valid");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addGap(13, 13, 13)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50)
                            .addComponent(jLabel49))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(land_west_test, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                    .addComponent(land_east_test, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(land_south_test)
                    .addComponent(land_north_test))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bounedeastnotvalidlabel)
                    .addComponent(bounedwestnotvalidlabel)
                    .addComponent(bounedsouthnotvalidlabel)
                    .addComponent(boundednorthnotvalidlabel))
                .addGap(185, 185, 185))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel48)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel49))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(land_north_test, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boundednorthnotvalidlabel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(land_east_test, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bounedeastnotvalidlabel)
                    .addComponent(jLabel50))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(land_west_test, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bounedwestnotvalidlabel)
                    .addComponent(jLabel51))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(land_south_test, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bounedsouthnotvalidlabel)
                    .addComponent(jLabel52))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 49, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel7);

        jTabbedPane1.addTab("Add New Land", jScrollPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void land_save_buttunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_land_save_buttunActionPerformed
        DefaultTableModel dm1 = (DefaultTableModel) lot_table.getModel();
        if(dm1.getRowCount()!=0){
            Land land = new Land(plan_nummber_test.getText(), land_name_test.getText(), (String) gnd_number_combo.getSelectedItem(), land_west_test.getText(), land_east_test.getText(), land_north_test.getText(), land_south_test.getText());
            ArrayList<Lot> lotList = new ArrayList();
            try{
                for (int i = 0; i < lot_table.getRowCount(); i++) {

                    Lot lot = new Lot((String) lot_table.getValueAt(i, 0), Integer.parseInt((String) lot_table.getValueAt(i, 1)), Integer.parseInt((String) lot_table.getValueAt(i, 3)), Integer.parseInt((String) lot_table.getValueAt(i, 2)), land);
                    lotList.add(lot);

                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(this, "Check The Relevant Fields");
            }
            land.setLotList(lotList);
            try {
                boolean addNewLand = LandController.addNewLand(land);

                if (addNewLand) {
                    JOptionPane.showMessageDialog(this, "land add successsfully");
                    plan_nummber_test.setText(null);
                    land_name_test.setText(null);
                    gnd_name_test.setText(null);
                    lot_number_test.setText(null);
                    acre_test.setText(null);
                    perches_test.setText(null);
                    rood_test.setText(null);
                    land_north_test.setText(null);
                    land_east_test.setText(null);
                    land_west_test.setText(null);
                    land_south_test.setText(null);
                    gnd_number_combo.setSelectedItem(null);
                    DefaultTableModel dm = (DefaultTableModel) lot_table.getModel();
                    for( int i = dm.getRowCount() - 1; i >= 0; i-- ) {
                        dm.removeRow(i);
                    }

                    try {
                        Connector sConnector = Connector.getSConnector();
                        LotController = sConnector.getlotController();
                        LandController = sConnector.getLandController();
                        GramaNiladariDivisionController = sConnector.getGramaNiladariDivisionController();
                    } catch (RemoteException | SQLException | NotBoundException | MalformedURLException | ClassNotFoundException ex) {
                        Logger.getLogger(ApplicantForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        Lot lastAddedLot = LotController.getLastAddedLot();
                        lot_number_test.setText(IdGenerator.generateNextLotNumber(lastAddedLot.getLotNumber()));
                    } catch (ClassNotFoundException | SQLException | RemoteException ex) {
                        Logger.getLogger(LandForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Land lastAddedLand = LandController.getLastAddedLand();
                    String planNumber = lastAddedLand.getPlanNumber();
                    String generateNextPlanNumber = IdGenerator.generateNextPlanNumber(planNumber);
                    plan_nummber_test.setText(generateNextPlanNumber);

                }
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(this, "Check The Relevant Fields");
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Please add lots");
        }
    }//GEN-LAST:event_land_save_buttunActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void add_lot_buttunKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_add_lot_buttunKeyReleased
        // TODO add your handling code here
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            rood_test.requestFocus();
        }
    }//GEN-LAST:event_add_lot_buttunKeyReleased

    private void add_lot_buttunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_lot_buttunActionPerformed

        DefaultTableModel tableModel = (DefaultTableModel) lot_table.getModel();
        Object[] rawdata = {lot_number_test.getText(), acre_test.getText(), perches_test.getText(), rood_test.getText()};
        tableModel.addRow(rawdata);
        acre_test.setText("");
        perches_test.setText("");
        rood_test.setText("");
        lot_number_test.setText(IdGenerator.generateNextLotNumber(lot_number_test.getText()));
        add_lot_buttun.setEnabled(false);
    }//GEN-LAST:event_add_lot_buttunActionPerformed

    private void rood_testKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rood_testKeyReleased
        // TODO add your handling code here:
        if (acre_test.getText().trim().length() != 0 && perches_test.getText().trim().length() != 0 && rood_test.getText().trim().length() != 0) {
            add_lot_buttun.setEnabled(true);
        }
        if (acre_test.getText().trim().length() == 0 || perches_test.getText().trim().length() == 0 || rood_test.getText().trim().length() == 0) {
            add_lot_buttun.setEnabled(false);
        }
        roodsnotvalidlabel.setVisible(false);
        String newtext = PatternChecker.checkDecimal(rood_test.getText());
        rood_test.setText(newtext);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (PatternChecker.checkDecimaldirect(rood_test.getText())) {
                add_lot_buttun.requestFocus();
            } else {
                perchesnotvalidlabel.setVisible(true);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            add_lot_buttun.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            perches_test.requestFocus();
        }
    }//GEN-LAST:event_rood_testKeyReleased

    private void rood_testActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rood_testActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rood_testActionPerformed

    private void perches_testKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_perches_testKeyReleased
        // TODO add your handling code here:
        if (acre_test.getText().trim().length() != 0 && perches_test.getText().trim().length() != 0 && rood_test.getText().trim().length() != 0) {
            add_lot_buttun.setEnabled(true);
        }
        if (acre_test.getText().trim().length() == 0 || perches_test.getText().trim().length() == 0 || rood_test.getText().trim().length() == 0) {
            add_lot_buttun.setEnabled(false);
        }
        perchesnotvalidlabel.setVisible(false);
        String newtext = PatternChecker.checkDecimal(perches_test.getText());
        perches_test.setText(newtext);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (PatternChecker.checkDecimaldirect(perches_test.getText())) {
                rood_test.requestFocus();
            } else {
                perchesnotvalidlabel.setVisible(true);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            rood_test.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            acre_test.requestFocus();
        }
    }//GEN-LAST:event_perches_testKeyReleased

    private void perches_testActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perches_testActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_perches_testActionPerformed

    private void acre_testKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_acre_testKeyReleased
        // TODO add your handling code here:
        if (acre_test.getText().trim().length() != 0 && perches_test.getText().trim().length() != 0 && rood_test.getText().trim().length() != 0) {
            add_lot_buttun.setEnabled(true);
        }
        if (acre_test.getText().trim().length() == 0 || perches_test.getText().trim().length() == 0 || rood_test.getText().trim().length() == 0) {
            add_lot_buttun.setEnabled(false);
        }
        acresnotvalidlabel.setVisible(false);
        String newtext = PatternChecker.checkDecimal(acre_test.getText());
        acre_test.setText(newtext);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (PatternChecker.checkDecimaldirect(acre_test.getText())) {
                perches_test.requestFocus();
            } else {
                acresnotvalidlabel.setVisible(true);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            perches_test.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            land_south_test.requestFocus();
        }
    }//GEN-LAST:event_acre_testKeyReleased

    private void acre_testActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acre_testActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acre_testActionPerformed

    private void gnd_name_testKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gnd_name_testKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_gnd_name_testKeyReleased

    private void gnd_name_testActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gnd_name_testActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gnd_name_testActionPerformed

    private void gnd_number_comboKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gnd_number_comboKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            land_north_test.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            land_name_test.requestFocus();
        }
    }//GEN-LAST:event_gnd_number_comboKeyReleased

    private void gnd_number_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gnd_number_comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gnd_number_comboActionPerformed

    private void gnd_number_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_gnd_number_comboItemStateChanged
        try {
            GramaNiladariDivision searchGND = GramaNiladariDivisionController.searchGND((String) gnd_number_combo.getSelectedItem());
            if (searchGND != null) {
                gnd_name_test.setText(searchGND.getDivisionName());
            }
        } catch (ClassNotFoundException | SQLException | RemoteException ex) {
            Logger.getLogger(GramaNiladhariForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_gnd_number_comboItemStateChanged

    private void land_name_testKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_land_name_testKeyReleased
        // TODO add your handling code here:
        landnotvalidlabel.setVisible(false);
        String newtext = PatternChecker.checkstring(land_name_test.getText());
        land_name_test.setText(newtext);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (PatternChecker.checkStringdirect(land_name_test.getText())) {
                gnd_number_combo.requestFocus();
            } else {
                landnotvalidlabel.setVisible(true);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            gnd_number_combo.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {

        }
    }//GEN-LAST:event_land_name_testKeyReleased

    private void cancelButtonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cancelButtonKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            land_south_test.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            updateButton.requestFocus();
        }
    }//GEN-LAST:event_cancelButtonKeyReleased

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void updateButtonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_updateButtonKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            land_south_test.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            cancelButton.requestFocus();
        }
    }//GEN-LAST:event_updateButtonKeyReleased

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        try {
            if (!PatternChecker.checkStringdirect(update_land_name.getText())) {
                editlandnamenotvalidlabel.setVisible(true);
            } else if (update_land_name.getText().trim().length() == 0) {
                editlandnamenotvalidlabel.setVisible(true);
            } else {
                int showConfirmDialog = JOptionPane.showConfirmDialog(this, "do you want to update");
                if (showConfirmDialog == 0) {
                    Land land = new Land(String.valueOf(update_planNumber_combo.getSelectedItem()), update_land_name.getText(), (String) update_gnd_combo.getSelectedItem(), land_west_test.getText(), land_east_test.getText(), land_north_test.getText(), land_south_test.getText());
                    ArrayList<Lot> lotList = new ArrayList();

                    for (int i = 0; i < update_lot_table.getRowCount(); i++) {
                        Lot lot = new Lot((String) update_lot_table.getValueAt(i, 0), Integer.parseInt(String.valueOf(update_lot_table.getValueAt(i, 1))), Integer.parseInt(String.valueOf(update_lot_table.getValueAt(i, 3))), Integer.parseInt(String.valueOf(update_lot_table.getValueAt(i, 2))), land);
                        lotList.add(lot);
                    }
                    System.out.println(lotList.size());
                    land.setLotList(lotList);
                    try {
                        System.out.println("finish");
                        boolean updateLand = LandController.updateLand(land);
                        System.out.println(updateLand);
                        if (updateLand) {
                            JOptionPane.showMessageDialog(this, "land update successsfully");
                            update_planNumber_combo.setSelectedItem(null);
                            update_gnd_combo.setSelectedItem(null);
                            update_land_name.setText(null);
                            land_north_test.setText(null);
                            land_west_test.setText(null);
                            land_south_test.setText(null);
                            land_east_test.setText(null);
                            DefaultTableModel dm = (DefaultTableModel) update_lot_table.getModel();
                            for( int i = dm.getRowCount() - 1; i >= 0; i-- ) {
                                dm.removeRow(i);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "land doesn't update successsfully");
                        }
                    } catch (ClassNotFoundException | SQLException | RemoteException ex) {
                        Logger.getLogger(LandForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please Enter Data In Correct Formats.");
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void update_south_test1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_update_south_test1KeyReleased
        // TODO add your handling code here:
        bounedsouthnotvalidlabel.setVisible(false);
        String newtext = PatternChecker.checkstring(land_south_test.getText());
        land_south_test.setText(newtext);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (PatternChecker.checkStringdirect(land_south_test.getText())) {
                updateButton.requestFocus();
            } else {
                bounedsouthnotvalidlabel.setVisible(true);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            updateButton.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            land_west_test.requestFocus();
        }
    }//GEN-LAST:event_update_south_test1KeyReleased

    private void update_west_test1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_update_west_test1KeyReleased
        // TODO add your handling code here:
        bounedwestnotvalidlabel.setVisible(false);
        String newtext = PatternChecker.checkstring(land_west_test.getText());
        land_west_test.setText(newtext);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (PatternChecker.checkStringdirect(land_west_test.getText())) {
                land_south_test.requestFocus();
            } else {
                bounedwestnotvalidlabel.setVisible(true);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            land_south_test.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            land_east_test.requestFocus();
        }
    }//GEN-LAST:event_update_west_test1KeyReleased

    private void update_east_testKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_update_east_testKeyReleased
        // TODO add your handling code here:
        bounedeastnotvalidlabel.setVisible(false);
        String newtext = PatternChecker.checkstring(land_east_test.getText());
        land_east_test.setText(newtext);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (PatternChecker.checkStringdirect(land_east_test.getText())) {
                land_west_test.requestFocus();
            } else {
                bounedeastnotvalidlabel.setVisible(true);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            land_west_test.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            land_north_test.requestFocus();
        }
    }//GEN-LAST:event_update_east_testKeyReleased

    private void update_north_testKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_update_north_testKeyReleased

        boundednorthnotvalidlabel.setVisible(false);
        String newtext = PatternChecker.checkstring(land_north_test.getText());
        land_north_test.setText(newtext);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (PatternChecker.checkStringdirect(land_north_test.getText())) {
                land_east_test.requestFocus();
            } else {
               boundednorthnotvalidlabel.setVisible(true);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            land_east_test.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            update_gnd_combo.requestFocus();
        }
    }//GEN-LAST:event_update_north_testKeyReleased

    private void update_gnd_comboKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_update_gnd_comboKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            land_north_test.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            update_land_name.requestFocus();
        }
    }//GEN-LAST:event_update_gnd_comboKeyReleased

    private void update_land_nameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_update_land_nameKeyReleased
        //EnableUpdate();
        editlandnamenotvalidlabel.setVisible(false);
        String newtext = PatternChecker.checkstring(update_land_name.getText());
        update_land_name.setText(newtext);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (PatternChecker.checkStringdirect(update_land_name.getText())) {
                update_gnd_combo.requestFocus();
            } else {
                editlandnamenotvalidlabel.setVisible(true);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            update_gnd_combo.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            update_planNumber_combo.requestFocus();
        }
    }//GEN-LAST:event_update_land_nameKeyReleased

    private void update_planNumber_comboKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_update_planNumber_comboKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            update_land_name.requestFocus();
        }
    }//GEN-LAST:event_update_planNumber_comboKeyReleased

    private void update_planNumber_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_planNumber_comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_update_planNumber_comboActionPerformed

    private void update_planNumber_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_update_planNumber_comboItemStateChanged
        String selectedPlanNumber = (String) update_planNumber_combo.getSelectedItem();
        try {
            Land searchLand = LandController.searchLand(selectedPlanNumber);
            if (searchLand != null) {
                update_land_name.setText(searchLand.getLandName());
                land_east_test.setText(searchLand.getEastBound());
                land_north_test.setText(searchLand.getNorthBound());
                land_south_test.setText(searchLand.getSouthBound());
                land_west_test.setText(searchLand.getWestBound());
                update_gnd_combo.addItem(searchLand.getDivisionNumber());
                ArrayList<Lot> lotList = searchLand.getLotList();
                DefaultTableModel modelULT = (DefaultTableModel) update_lot_table.getModel();
                modelULT.getDataVector().removeAllElements();
                revalidate();
                for (Lot lot : lotList) {
                    Object[] rowdata = {lot.getLotNumber(), lot.getNumberOfAcres(), lot.getNumberOfPerches(), lot.getNumberofRoods()};
                    modelULT.addRow(rowdata);
                }
            }
        } catch (ClassNotFoundException | SQLException | RemoteException ex) {
            Logger.getLogger(LandForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_update_planNumber_comboItemStateChanged

    private void search_planNumber_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_search_planNumber_comboItemStateChanged
        String selectedPlanNumber = (String) search_planNumber_combo.getSelectedItem();
        try {
            Land searchLand = LandController.searchLand(selectedPlanNumber);
            if (searchLand != null) {
                search_land_name.setText(searchLand.getLandName());
                search_east_test1.setText(searchLand.getEastBound());
                search_north_test.setText(searchLand.getNorthBound());
                search_south_test.setText(searchLand.getSouthBound());
                search_west_test.setText(searchLand.getWestBound());
                search_gnd_test.setText(searchLand.getDivisionNumber());
                ArrayList<Lot> lotList = searchLand.getLotList();
                DefaultTableModel modelULT = (DefaultTableModel) search_lot_table.getModel();
                modelULT.getDataVector().removeAllElements();
                revalidate();
                for (Lot lot : lotList) {
                    Object[] rowdata = {lot.getLotNumber(), lot.getNumberOfAcres(), lot.getNumberOfPerches(), lot.getNumberofRoods()};
                    modelULT.addRow(rowdata);
                }
            }
        } catch (ClassNotFoundException | SQLException | RemoteException ex) {
            Logger.getLogger(LandForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_search_planNumber_comboItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void search_east_test1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_east_test1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_east_test1ActionPerformed

    private void view_all_load_buttunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_all_load_buttunActionPerformed
        try {
            DefaultTableModel viewAllModel = (DefaultTableModel) view_all_table.getModel();
            viewAllModel.getDataVector().removeAllElements();
            revalidate();
            ArrayList<Land> allLandDetail = LandController.getAllLandDetail();
            for (Land land : allLandDetail) {
                Object[] rowdata = {land.getPlanNumber(), land.getDivisionNumber(), land.getLandName(), land.getWestBound(), land.getEastBound(), land.getNorthBound(), land.getSouthBound(), land.getNumberOfLot()};
                viewAllModel.addRow(rowdata);
            }
        } catch (ClassNotFoundException | SQLException | RemoteException ex) {
            Logger.getLogger(LandForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_view_all_load_buttunActionPerformed

    private void update_north_test1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_update_north_test1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_update_north_test1KeyReleased

    private void update_east_test1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_update_east_test1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_update_east_test1KeyReleased

    private void update_west_test2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_update_west_test2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_update_west_test2KeyReleased

    private void update_south_test2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_update_south_test2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_update_south_test2KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AddNewLand2;
    private javax.swing.JTextField acre_test;
    private javax.swing.JLabel acreorhectarelabel;
    private javax.swing.JLabel acresnotvalidlabel;
    private javax.swing.JToggleButton add_lot_buttun;
    private javax.swing.JLabel boundednorthnotvalidlabel;
    private javax.swing.JLabel bounedeastnotvalidlabel;
    private javax.swing.JLabel bounedsouthnotvalidlabel;
    private javax.swing.JLabel bounedwestnotvalidlabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel editbounedeastnotvalidlabel1;
    private javax.swing.JLabel editbounedeastnotvalidlabel2;
    private javax.swing.JLabel editbounedeastnotvalidlabel3;
    private javax.swing.JLabel editbounednorthnotvalidlabel1;
    private javax.swing.JLabel editbounednorthnotvalidlabel2;
    private javax.swing.JLabel editbounednorthnotvalidlabel3;
    private javax.swing.JLabel editbounedsouthnotvalidlabel1;
    private javax.swing.JLabel editbounedsouthnotvalidlabel2;
    private javax.swing.JLabel editbounedsouthnotvalidlabel3;
    private javax.swing.JLabel editbounedwestnotvalidlabel1;
    private javax.swing.JLabel editbounedwestnotvalidlabel2;
    private javax.swing.JLabel editbounedwestnotvalidlabel3;
    private javax.swing.JLabel editlandnamenotvalidlabel;
    private javax.swing.JLabel extentlabel;
    private javax.swing.JTextField gnd_name_test;
    private javax.swing.JComboBox gnd_number_combo;
    private javax.swing.JLabel gndlabel;
    private javax.swing.JLabel gndnolabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField land_east_test;
    private javax.swing.JTextField land_name_test;
    private javax.swing.JTextField land_north_test;
    private javax.swing.JButton land_save_buttun;
    private javax.swing.JTextField land_south_test;
    private javax.swing.JTextField land_west_test;
    private javax.swing.JLabel landnamelabel;
    private javax.swing.JLabel landnotvalidlabel;
    private javax.swing.JTextField lot_number_test;
    private javax.swing.JTable lot_table;
    private javax.swing.JLabel lotnolabel;
    private javax.swing.JTextField perches_test;
    private javax.swing.JLabel percheslabel;
    private javax.swing.JLabel perchesnotvalidlabel;
    private javax.swing.JTextField plan_nummber_test;
    private javax.swing.JLabel plannolabel;
    private javax.swing.JTextField rood_test;
    private javax.swing.JLabel roodslabel;
    private javax.swing.JLabel roodsnotvalidlabel;
    private javax.swing.JTextField search_east_test1;
    private javax.swing.JTextField search_gnd_test;
    private javax.swing.JTextField search_land_name;
    private javax.swing.JTable search_lot_table;
    private javax.swing.JTextField search_north_test;
    private javax.swing.JComboBox search_planNumber_combo;
    private javax.swing.JTextField search_south_test;
    private javax.swing.JTextField search_west_test;
    private javax.swing.JButton updateButton;
    private javax.swing.JTextField update_east_test;
    private javax.swing.JTextField update_east_test1;
    private javax.swing.JTextField update_east_test2;
    private javax.swing.JComboBox update_gnd_combo;
    private javax.swing.JTextField update_land_name;
    private javax.swing.JTable update_lot_table;
    private javax.swing.JTextField update_north_test;
    private javax.swing.JTextField update_north_test1;
    private javax.swing.JTextField update_north_test2;
    private javax.swing.JComboBox update_planNumber_combo;
    private javax.swing.JTextField update_south_test1;
    private javax.swing.JTextField update_south_test2;
    private javax.swing.JTextField update_south_test3;
    private javax.swing.JTextField update_west_test1;
    private javax.swing.JTextField update_west_test2;
    private javax.swing.JTextField update_west_test3;
    private javax.swing.JButton view_all_load_buttun;
    private javax.swing.JTable view_all_table;
    // End of variables declaration//GEN-END:variables
}
