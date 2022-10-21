/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package multiselectiontabledemo;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Walter
 */

public class MultiSelColor extends javax.swing.JPanel {

    private MainFrame main;
    private MyTableModel3 myMod3;
    public static int selectionOffset;
    
    /**
     * Creates new form MultiSelColor
     */
    public MultiSelColor(MainFrame _main) {
        main = _main;
        initComponents();
        initTable();
    }
    
    private void initTable() 
    {
        new WidthAdjuster(jTable1); // used with calendar table
        setTableColumSizes(jTable1); // used with calendar table
        setEmtyTable(jTable1, 2022); // used with calendar table
        selectionOffset = 1;
        jTable1.setDefaultRenderer(Object.class,
                    new MultipleSelectionRenderer(3));
        jTable1.setColumnSelectionAllowed(false);
        jTable1.setRowSelectionAllowed(false);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        MultipleRowsCellsTableSelection mrc = 
                new MultipleRowsCellsTableSelection(jTextArea1,3);
        mrc.setTblListeners(jTable1);
        if (main.chkBox1.isSelected())
                MultipleSelectionRenderer.coloredTest = true;
        else
            MultipleSelectionRenderer.coloredTest = false;
    }
    
    private MyTableModel3 Tabl3ModOrdi()
    {
        jTable1.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 70));
        jTable1.setFillsViewportHeight(true);
        //jTable1.getSelectionModel().addListSelectionListener(new MainFrame.RowListener());
        //jTable1.getColumnModel().getSelectionModel().
        //    addListSelectionListener(new MainFrame.ColumnListener());
        myMod3 = new MyTableModel3();
        //javax.swing.JTable table = new javax.swing.JTable(myMod);
        return myMod3;
    }
    
    public javax.swing.JPanel getMainPanel() 
    {
        return this;
    }
    
    public javax.swing.JScrollPane getScrollPane1() 
    {
        return jScrollPane1;
    }
    
    public javax.swing.JScrollPane getScrollPane2() 
    {
        return jScrollPane2;
    }
    
    private void setTableColumSizes(javax.swing.JTable table) // used with calendar table
    {
      javax.swing.table.TableColumn column = null;
      for (int i = 0; i < table.getColumnCount(); i++)
      {
        column = table.getColumnModel().getColumn(i);
        switch (i)
        {
          case 0:  column.setPreferredWidth(80);
                   break;
          default: column.setPreferredWidth(25);
                   break;
        }
      }
    }
    
    private void setEmtyTable(javax.swing.JTable tbl, int j) // used with calendar table
    {
        String y[] = null;
        int startDay = WeekDay.dayOfWeek(1, 12, j-1);
        int endDay = wappcalendar.HandleCalendar.getMonthMaxDay(12, j-1);
        for (int xx = 1; xx <= endDay; xx++)
        {
                tbl.setValueAt(WeekDay.weekDay[startDay++] + " ", 0, xx);
                if (startDay > 6) startDay = 0;
        }
        //multiplerowscellstableselection.wappcalendar.Feiertage.storeBeweglicheFeiertage(j-1);
        y = tbl.getValueAt(0, 0).toString().split(" ");
        tbl.setValueAt(y[0] + " " + (j-1), 0, 0);
        for (int x = 1; x <= 12; x++)
        {
            y = tbl.getValueAt(x, 0).toString().split(" ");
            tbl.setValueAt(y[0] + " " + j, x, 0);
            startDay = WeekDay.dayOfWeek(1, x, j);
            endDay = wappcalendar.HandleCalendar.getMonthMaxDay(x, j);
            for (int xx = 1; xx <= endDay; xx++)
            {
                tbl.setValueAt(WeekDay.weekDay[startDay++] + " ", x, xx);
                if (startDay > 6) startDay = 0;
            }
        }
        startDay = WeekDay.dayOfWeek(1, 1, j+1);
        endDay = wappcalendar.HandleCalendar.getMonthMaxDay(1, j+1);
        //newabwhproject.wappcalendar.Feiertage.storeBeweglicheFeiertage(j+1);
        for (int xx = 1; xx <= endDay; xx++)
        {
                tbl.setValueAt(WeekDay.weekDay[startDay++] + " ", 13, xx);
                if (startDay > 6) startDay = 0;
        }
        y = tbl.getValueAt(1, 0).toString().split(" ");
        tbl.setValueAt(y[0] + " " + (j+1), 13, 0);
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

        setBackground(new java.awt.Color(0, 204, 204));

        jTable1.setModel(Tabl3ModOrdi());
        jScrollPane1.setViewportView(jTable1);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
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

    class MyTableModel3 extends AbstractTableModel {
        private String tableData[][] = new String[][]{
                {"December", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"January", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"February", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"March", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"April", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"May", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"June", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"July", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"August", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"September", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"October", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"November", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"December", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"January", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            };
            private String columnNames[] = new String[] {
                "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
                };
            
        MyTableModel3()
        {
            System.out.println("model 3");
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
