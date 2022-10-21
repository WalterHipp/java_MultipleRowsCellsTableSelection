/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multiselectiontabledemo;

/**
 *
 * @author Walter
 */
public class MultipleSelectionRenderer 
    implements javax.swing.table.TableCellRenderer
{
    private static javax.swing.JLabel   label = new javax.swing.JLabel();
    private java.awt.Color              lightBlue = new java.awt.Color(160, 160, 255);
    private java.awt.Color              darkBlue  = new java.awt.Color( 64,  64, 128);
    private static String               tipTxt = null;
    
    public static boolean coloredTest       = false;
    public static boolean mustSelect = false;
    public static java.util.ArrayList<Integer> selectionList = 
            new java.util.ArrayList<Integer>();
    private int tbl;
    
    public MultipleSelectionRenderer(int _tbl)
    {
        tbl = _tbl;
    }
    
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
            label.setBackground(lightBlue); //(darkBlue);
            label.setForeground(java.awt.Color.white);
        }
        else if (isSelected)
        {
            label.setBackground(lightBlue);
        }
        else
        {
            //Map the index of the column in the view at viewColumnIndex to 
            //the index of the column in the table model.
            column = table.convertColumnIndexToModel(column); 
            if (column >= 0 && value != null)
            {
                String s = value.toString();
                if (RegexMatches.testStringContains(".*(Sa).*", s) ||
                            RegexMatches.testStringContains(".*(Su).*", s))
                { 
                    label.setBackground(new java.awt.Color(255, 255, 228));
                    label.setForeground(java.awt.Color.RED);
                    //return label; 
                }
                if (mustSelect) //the example use different colors,
                    //to show what is going on
                    //usually use one color = the selected color = lightBlue
                {
                    //column += MultiSelectionTableDemo.selectionOffset;
                    if (selectionList.size() < 1) return label;
                    int beginRow = selectionList.get(0);// first row
                    int endRow = selectionList.get(3);  // last row
                    int aCol = selectionList.get(1);    // first selected column
                    int eCol = selectionList.get(2);    // last selected column
                    int end = table.getColumnCount();
                    int beg = MultiSelColor.selectionOffset;
                    if (beginRow == row && endRow == row)
                    {
                        if (column >= aCol && column <= eCol)
                            label.setBackground(lightBlue);
                    }
                    else if (beginRow != endRow)
                    {
                        if (coloredTest)
                        {
                            //int diff = endRow - beginRow;
                            if (row == beginRow && row < endRow
                            && column >= aCol && column < end)
                                label.setBackground(java.awt.Color.YELLOW);
                            else if (row > beginRow && row < endRow
                                && column >= beg && column < end)
                                label.setBackground(java.awt.Color.RED);
                            else if (row == endRow && column >= beg && column <= eCol)
                                label.setBackground(java.awt.Color.GREEN);
                        }
                        else
                        {
                            if ((row == beginRow && row < endRow
                                    && column >= aCol && column < end)
                                || (row > beginRow && row < endRow
                                    && column >= beg && column < end)
                                || (row == endRow && column >= beg && column <= eCol))
                                label.setBackground(lightBlue);
                        }
                    }
                }
            }
        }
        return label;
    }
    
    public static void setToolText(String txt) // uesed to set ToolTip Text
    {
        tipTxt = txt;
    }
}

