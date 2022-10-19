package multiselectiontabledemo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Walter
 */
public class MainFrame extends javax.swing.JFrame implements ActionListener {

    MyTableModel myMod;
    javax.swing.JPanel mPnl2;
    javax.swing.JPanel mPnl3;
    MultiSelNormal tab2Panel;
    MultiSelColor tab3Panel;
    
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                java.awt.Dimension d = e.getComponent().getSize();
                if (mPnl2 != null)
                    mPnl2.setSize(d.width-75, d.height-125);
                if (mPnl3 != null)
                {
                    mPnl3.setSize(d.width-75, d.height-125);
                    //tab3Panel.getScrollPane1().setSize(d.width-75, d.height/2);
                    //tab3Panel.getScrollPane2().setSize(d.width-75, d.height/2-200);
                    //System.out.println("Resized");
                }
            }
        });
        initComponents();
        addListener();
    }
    
    private void addListener()
    {
        javax.swing.event.ChangeListener changeListener =
                new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent changeEvent)
            {
                javax.swing.JTabbedPane sourceTabbedPane = 
                        (javax.swing.JTabbedPane) changeEvent.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                System.out.println("Tab changed to: " + 
                        sourceTabbedPane.getTitleAt(index));
                if (index == 1)
                    viewTab2();
                else if (index == 2)
                    viewTab3();
            }
        };
        jTabbedPane.addChangeListener(changeListener);
    }
    
    private MyTableModel TableModOrdi()
    {
        jTable1.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 70));
        jTable1.setFillsViewportHeight(true);
        jTable1.getSelectionModel().addListSelectionListener(new RowListener());
        jTable1.getColumnModel().getSelectionModel().
            addListSelectionListener(new ColumnListener());
        jRadioButton1.addActionListener(this);
        jRadioButton2.addActionListener(this);
        jRadioButton3.addActionListener(this);
        rowCheck.addActionListener(this);
        columnCheck.addActionListener(this);
        cellCheck.addActionListener(this);
        myMod = new MyTableModel();
        //javax.swing.JTable table = new javax.swing.JTable(myMod);
        //jTable1.getColumnModel().getColumn(jTable1.getSelectedColumn())
                //.setCellRenderer(new StatusColumnCellRenderer());
        jTable1.setDefaultRenderer(Object.class,
                    new MySelectionRenderer());
        return myMod;
    }
    
    private void viewTab2()
    {
        tab3Panel = new MultiSelColor(this);
        tab2Panel = new MultiSelNormal(this);
        /*javax.swing.JScrollPane sc = tab2Panel.getScrollPane();
        sc.setSize(981, 813);
        jPanelTab2.add(sc); */
        mPnl2 = tab2Panel.getMainPanel() ;
        mPnl2.setSize(981, 813);
        jPanelTab2.add(mPnl2);
        jTabbedPane.setSelectedIndex(1);
        
    }
    
     private void viewTab3()
    {
        tab3Panel = new MultiSelColor(this);
        /*javax.swing.JScrollPane sc = tab2Panel.getScrollPane();
        sc.setSize(981, 813);
        jPanelTab2.add(sc); */
        mPnl3 = tab3Panel.getMainPanel() ;
        mPnl3.setSize(981, 813);
        jPanelTab3.add(mPnl3);
        jTabbedPane.setSelectedIndex(2);
    }
    
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        output.append("command\n" + command);
        //Cell selection is disabled in Multiple Interval Selection
        //mode. The enabled state of cellCheck is a convenient flag
        //for this status.
        if ("Row Selection" == command) {
            jTable1.setRowSelectionAllowed(rowCheck.isSelected());
            //In MIS mode, column selection allowed must be the
            //opposite of row selection allowed.
            if (!cellCheck.isEnabled()) {
                jTable1.setColumnSelectionAllowed(!rowCheck.isSelected());
            }
        } else if ("Column Selection" == command) {
            jTable1.setColumnSelectionAllowed(columnCheck.isSelected());
            //In MIS mode, row selection allowed must be the
            //opposite of column selection allowed.
            if (!cellCheck.isEnabled()) {
                jTable1.setRowSelectionAllowed(!columnCheck.isSelected());
            }
        } else if ("Cell Selection" == command) {
            jTable1.setCellSelectionEnabled(cellCheck.isSelected());
        } else if ("Multiple Interval Selection" == command) { 
            jTable1.setSelectionMode(
                    ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            //If cell selection is on, turn it off.
            if (cellCheck.isSelected()) {
                cellCheck.setSelected(false);
                jTable1.setCellSelectionEnabled(false);
            }
            //And don't let it be turned back on.
            cellCheck.setEnabled(false);
        } else if ("Single Interval Selection" == command) {
            jTable1.setSelectionMode(
                    ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            //Cell selection is ok in this mode.
            cellCheck.setEnabled(true);
        } else if ("Single Selection" == command) {
            jTable1.setSelectionMode(
                    ListSelectionModel.SINGLE_SELECTION);
            //Cell selection is ok in this mode.
            cellCheck.setEnabled(true);
        }

        //Update checkboxes to reflect selection mode side effects.
        rowCheck.setSelected(jTable1.getRowSelectionAllowed());
        columnCheck.setSelected(jTable1.getColumnSelectionAllowed());
        if (cellCheck.isEnabled()) {
            cellCheck.setSelected(jTable1.getCellSelectionEnabled());
        }
    }
    
    private void outputSelection() {
        output.selectAll();
        output.replaceSelection("");
        output.append(String.format("Lead: %d, %d. ",
                   jTable1.getSelectionModel().getLeadSelectionIndex(),
                    jTable1.getColumnModel().getSelectionModel().
                        getLeadSelectionIndex()));
        int[] rselection = jTable1.getSelectedRows();
        int[] cselection = jTable1.getSelectedColumns();
        System.out.println("cselection " + cselection.length);
        int rBegin = jTable1.getSelectionModel().getLeadSelectionIndex();
        if (rselection.length == 1)
            rBegin ++;
        int row = jTable1.getSelectionModel().getLeadSelectionIndex();
        int col = jTable1.getColumnModel().getSelectionModel().
                        getLeadSelectionIndex();
        //for (int i = 0; i< rselection.length; i++)
        /*System.out.println("row 0 " + rselection[0] + " last" 
                + rselection[rselection.length - 1] + " length " + rselection.length);
        for (int i = rselection[0]; i <= rselection[rselection.length - 1]; i++)
            System.out.println("i " + i);*/
        for (int i = 0; i < rselection.length; i++)
            output.append("\nselected row(s), row number " + (rselection[i] + 1));
            //System.out.println("row[ " + i + "] " + rselection[i]);
        for (int i = 0; i < cselection.length; i++)
            output.append("\nselected col[" + col + "]\n");
        output.append("Row: " + (row + 1) + " Column: " + col);
        output.append("\n");
        output.append("selected cell: " + myMod.getValueAt(row, col) + "\n");
        output.append("\n");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jTabbedPane = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        TabelPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        outputPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        output = new javax.swing.JTextArea();
        selectionPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        rowCheck = new javax.swing.JCheckBox();
        columnCheck = new javax.swing.JCheckBox();
        cellCheck = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jPanelTab2 = new javax.swing.JPanel();
        jPanelTab3 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        chkBox1 = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenuItem3.setText("jMenuItem3");

        jMenuItem4.setText("jMenuItem4");

        jMenuItem6.setText("jMenuItem6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(TableModOrdi());
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout TabelPanelLayout = new javax.swing.GroupLayout(TabelPanel);
        TabelPanel.setLayout(TabelPanelLayout);
        TabelPanelLayout.setHorizontalGroup(
            TabelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TabelPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        TabelPanelLayout.setVerticalGroup(
            TabelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabelPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        output.setColumns(20);
        output.setRows(5);
        jScrollPane4.setViewportView(output);

        javax.swing.GroupLayout outputPanelLayout = new javax.swing.GroupLayout(outputPanel);
        outputPanel.setLayout(outputPanelLayout);
        outputPanelLayout.setHorizontalGroup(
            outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        outputPanelLayout.setVerticalGroup(
            outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, outputPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "make your selections", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 51, 153))); // NOI18N
        jPanel4.setForeground(new java.awt.Color(0, 51, 153));

        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("only single selections are possible");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setForeground(new java.awt.Color(0, 51, 153));
        jRadioButton1.setText("Multiple Interval Selection");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setForeground(new java.awt.Color(0, 51, 153));
        jRadioButton2.setText("Single Selection");

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setForeground(new java.awt.Color(0, 51, 153));
        jRadioButton3.setText("Single Interval Selection");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton2)
                            .addComponent(jRadioButton3)
                            .addComponent(jRadioButton1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "selection Options", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 51, 153))); // NOI18N

        rowCheck.setForeground(new java.awt.Color(0, 51, 153));
        rowCheck.setText("Row Selection");

        columnCheck.setForeground(new java.awt.Color(0, 51, 153));
        columnCheck.setText("Column Selection");
        columnCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                columnCheckActionPerformed(evt);
            }
        });

        cellCheck.setForeground(new java.awt.Color(0, 51, 153));
        cellCheck.setText("Cell Selection");

        jLabel4.setForeground(new java.awt.Color(0, 51, 153));
        jLabel4.setText(" multiple selections are possible");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rowCheck)
                    .addComponent(columnCheck)
                    .addComponent(cellCheck))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(5, 5, 5)
                .addComponent(rowCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(columnCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cellCheck)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout selectionPanelLayout = new javax.swing.GroupLayout(selectionPanel);
        selectionPanel.setLayout(selectionPanelLayout);
        selectionPanelLayout.setHorizontalGroup(
            selectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 969, Short.MAX_VALUE)
            .addGroup(selectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(selectionPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(764, Short.MAX_VALUE)))
            .addGroup(selectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(selectionPanelLayout.createSequentialGroup()
                    .addGap(227, 227, 227)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(559, Short.MAX_VALUE)))
        );
        selectionPanelLayout.setVerticalGroup(
            selectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(selectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(selectionPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(19, Short.MAX_VALUE)))
            .addGroup(selectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selectionPanelLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(17, 17, 17)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TabelPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(outputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(selectionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabelPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(outputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane.addTab("Normal Java Table", jPanel2);

        javax.swing.GroupLayout jPanelTab2Layout = new javax.swing.GroupLayout(jPanelTab2);
        jPanelTab2.setLayout(jPanelTab2Layout);
        jPanelTab2Layout.setHorizontalGroup(
            jPanelTab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 987, Short.MAX_VALUE)
        );
        jPanelTab2Layout.setVerticalGroup(
            jPanelTab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 776, Short.MAX_VALUE)
        );

        jTabbedPane.addTab("Normal Multi.Sel Table", jPanelTab2);

        jPanelTab3.setBackground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout jPanelTab3Layout = new javax.swing.GroupLayout(jPanelTab3);
        jPanelTab3.setLayout(jPanelTab3Layout);
        jPanelTab3Layout.setHorizontalGroup(
            jPanelTab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 987, Short.MAX_VALUE)
        );
        jPanelTab3Layout.setVerticalGroup(
            jPanelTab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 776, Short.MAX_VALUE)
        );

        jTabbedPane.addTab("Multi Sel. Calendar Table", jPanelTab3);

        jToolBar1.setRollover(true);

        chkBox1.setText("Use different selection colors");
        chkBox1.setToolTipText("for the Tables MultiSel Table and MultiSelCalendarTable");
        chkBox1.setFocusable(false);
        chkBox1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBox1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(chkBox1);

        jMenu1.setText("File");

        jMenuItem1.setText("Show Tab2");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem5.setText("Show Tab3");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);
        jMenu1.add(jSeparator1);

        jMenuItem2.setText("Quit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       viewTab2();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void columnCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_columnCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_columnCheckActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
         viewTab3();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
                javax.swing.UIManager.setLookAndFeel(
                       javax.swing.UIManager.getSystemLookAndFeelClassName());
                //javax.swing.UIManager.setLookAndFeel(
                //    new oracle.bali.ewt.olaf.OracleLookAndFeel());
        } catch (Exception e) { e.printStackTrace(); }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel TabelPanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cellCheck;
    public javax.swing.JCheckBox chkBox1;
    private javax.swing.JCheckBox columnCheck;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelTab2;
    private javax.swing.JPanel jPanelTab3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextArea output;
    private javax.swing.JPanel outputPanel;
    private javax.swing.JCheckBox rowCheck;
    private javax.swing.JPanel selectionPanel;
    // End of variables declaration//GEN-END:variables

    class MyTableModel extends AbstractTableModel {
        private final String[] columnNames = new String[] { "French", "Spanish", "Italian", "  German" };
        private final Object[][] data = {
	    { "un", "uno", "uno", "eins" }, { "deux", "dos", "due", "zwei" },
                { "trois", "tres", "tre", "drei" }, { "quatre", "cuatro", "quattro", "vier" },
                { "cinq", "cinco", "cinque", "fünf" }, { "six", "seis", "sei", "sechs" },
                { "sept", "siete", "sette", "sieben" }
        };

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        @Override
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        @Override
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }

    }
    
    private class RowListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }
            output.append("ROW SELECTION EVENT. \n"); // + iSelectedIndex);
            outputSelection();
        }
    }

    private class ColumnListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }
            output.append("COLUMN SELECTION EVENT.\n");
            outputSelection();
        }
    
    }
    
    class MySelectionRenderer 
    implements javax.swing.table.TableCellRenderer
    {
        private javax.swing.JLabel   label = new javax.swing.JLabel();
        private java.awt.Color  lightBlue = new java.awt.Color(160, 160, 255);
        private java.awt.Color  darkBlue  = new java.awt.Color( 64,  64, 128);
        private String  tipTxt = null;
    
        public boolean coloredTest       = false;
        public boolean mustSelect = false;
        public java.util.ArrayList<Integer> selectionList = 
            new java.util.ArrayList<Integer>();
    
        public java.awt.Component getTableCellRendererComponent(
            javax.swing.JTable table, 
            Object value, boolean isSelected, boolean hasFocus,
            int row, int column)
        {
            //Label erzeugen
            if (value == null)
                label.setText("");
            else label.setText(value.toString());
                label.setOpaque(true);
            javax.swing.border.Border b = 
                javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1);
            label.setBorder(b);
            label.setFont(table.getFont());
            label.setForeground(table.getForeground());
            label.setToolTipText(tipTxt);
            label.setBackground(java.awt.Color.white);
            if (hasFocus)    
            { 
                label.setBackground(java.awt.Color.GREEN); //(darkBlue);
                label.setForeground(java.awt.Color.RED);
            }
            else if (isSelected)
            {
                label.setBackground(lightBlue);
            }
            return label;
        }
    }
}
