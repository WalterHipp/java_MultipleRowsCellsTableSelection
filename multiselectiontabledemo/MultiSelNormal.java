/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package multiselectiontabledemo;

import javax.swing.table.AbstractTableModel;
import static multiselectiontabledemo.MultiSelColor.selectionOffset;

/**
 *
 * @author Walter
 */
public class MultiSelNormal extends javax.swing.JPanel {

    private MainFrame main;
    MyTableModel2 myMod2;
    
    /**
     * Creates new form MultiSelNormal
     */
    public MultiSelNormal(MainFrame _main) {
        main = _main;
        initComponents();
        /*addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                System.out.println("Resized to " + e.getComponent().getSize());
            }
            @Override
            public void componentMoved(java.awt.event.ComponentEvent e) {
                System.out.println("Moved to " + e.getComponent().getLocation());
            }
        });*/
        //initTable2();
    }

    /*private void initTable2()
    {

    }*/
    
    private MyTableModel2 Table2ModOrdi()
    {
        selectionOffset = -1;
        jTable1.setDefaultRenderer(Object.class,
                    new MultipleSelectionRenderer(2));
        jTable1.setColumnSelectionAllowed(false);
        jTable1.setRowSelectionAllowed(false);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        //MultiRowsCellsSelection mrc = 
         //       new MultiRowsCellsSelection(jTextArea1,2);
        MultipleRowsCellsTableSelection mrc = 
                new MultipleRowsCellsTableSelection(jTextArea1,2);
        mrc.setTblListeners(jTable1);
        if (main.chkBox1.isSelected())
                MultipleSelectionRenderer.coloredTest = true;
        else
            MultipleSelectionRenderer.coloredTest = false;
        //jTable1.getSelectionModel().addListSelectionListener(new MainFrame.RowListener());
        //jTable1.getColumnModel().getSelectionModel().
        //    addListSelectionListener(new MainFrame.ColumnListener());
        myMod2 = new MyTableModel2();
        //javax.swing.JTable table = new javax.swing.JTable(myMod);
        return myMod2;
    }
    
    public javax.swing.JScrollPane getScrollPane() 
    {
        return jScrollPane1;
    }
    
    public javax.swing.JPanel getMainPanel() 
    {
        return this;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(204, 204, 0));

        jTable1.setModel(Table2ModOrdi());
        jScrollPane1.setViewportView(jTable1);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

        class MyTableModel2 extends AbstractTableModel {
            private String[] columnNames = new String[] { "French", "Spanish", "Italian", "  German" };
            private String[][] tableData = new String[][]{ { "un", "uno", "uno", "eins" }, { "deux", "dos", "due", "zwei" },
                { "trois", "tres", "tre", "drei" }, { "quatre", "cuatro", "quattro", "vier" },
                { "cinq", "cinco", "cinque", "fünf" }, { "six", "seis", "sei", "sechs" },
                { "sept", "siete", "sette", "sieben" } }; // Simple Table use it instead of calendar table
            
            //private String[] columnN;
            //private String tableD[][];
            
            MyTableModel2()
            {
                System.out.println("model 1");
            }
            
            public int getColumnCount() {
                return columnNames.length;
            }
       
            public int getRowCount() {
                return tableData.length;
            }

            public String getColumnName(int col) {
                return columnNames[col];
            }

            public Object getValueAt(int row, int col) {
                //System.out.println("val at row " + row + " col " + col);
                return tableData[row][col];
            }
             /*
            * Don't need to implement this method unless your table's
            * data can change.
            */
            public void setValueAt(Object value, int row, int col) {
                tableData[row][col] = value.toString();
                fireTableCellUpdated(row, col);
            }
        }  
}
