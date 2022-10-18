package multiselectiontabledemo;


/**
 *
 * @author walter hipp
 */
public class MultipleRowsCellsTableSelection
{
    int aktRow = -1, aktColumn = -1;
    javax.swing.JTextArea   output = null;
    
    String newline = System.getProperty("line.separator");
    
    public  MultipleRowsCellsTableSelection(javax.swing.JTextArea o, int tbl)
    {
        output = o;
    }
    
    private void test(String s, javax.swing.JTable table)
    {
        for (int i = 0; i < MultipleSelectionRenderer.selectionList.size(); i++)
        {
            switch (i)
            {
                
                case 0: output.append("Test calling from " + s + newline
                          + " Your Selection begins at Row " + 
                          MultipleSelectionRenderer.selectionList.get(i));
                        break;
                case 1: output.append(" , column: " + 
                        MultipleSelectionRenderer.selectionList.get(i));
                        break;
                case 3: output.append(" and ends at Row " + 
                            MultipleSelectionRenderer.selectionList.get(i)
                            + " column: " + MultipleSelectionRenderer.selectionList.get(2)
                            + newline);
                        break;
            }
        }
        if (table != null)
        {
            output.append("or in other words:" + newline 
                    + "you selected the following:" + newline);
            int rows = MultipleSelectionRenderer.selectionList.get(3) -
                    MultipleSelectionRenderer.selectionList.get(0);
            int b = MultipleSelectionRenderer.selectionList.get(0);
            int e = MultipleSelectionRenderer.selectionList.get(3);
            int x = MultipleSelectionRenderer.selectionList.get(2);
            int rbegin = MultipleSelectionRenderer.selectionList.get(1);
            if (rows >= 1)
                x = table.getColumnCount()-1;
            for (int i = b; i <= e; i++)
            {
                if (i == b)
                {
                    int row = MultipleSelectionRenderer.selectionList.get(0);
                    String m = table.getValueAt(row, 0).toString();
                    output.append(m + " ");
                    for (int ii = rbegin; ii <= x; ii++)
                        output.append(table.getValueAt(b, ii) + " ");
                    output.append(newline);
                    
                }
                else if (i > b && i < e)
                {
                    for (int ii = 0; ii < table.getColumnCount(); ii++)
                        output.append(table.getValueAt(i, ii) + " ");
                    output.append(newline);
                }
                else if (i == e)
                     for (int ii =  0;
                    ii <= MultipleSelectionRenderer.selectionList.get(2); ii++)
                output.append(table.getValueAt(e, ii) + " ");
            }
        }
        
    }
    
    private void moveLeft(int r, int c)
    {
        int xBegin = MultipleSelectionRenderer.selectionList.get(1);
        int xEnd = MultipleSelectionRenderer.selectionList.get(2);
        //System.out.println("left c: " + c + " xBegin: " + xBegin + " xEnd:" + xEnd);
        if (c < xBegin && r ==  MultipleSelectionRenderer.selectionList.get(0))
            MultipleSelectionRenderer.selectionList.set(1,c);
        else
            MultipleSelectionRenderer.selectionList.set(2,c);
        //test("moveLeft", null);
    }
    
    private void moveRight(int r, int c)
    {
        int xBegin = MultipleSelectionRenderer.selectionList.get(1);
        int xEnd = MultipleSelectionRenderer.selectionList.get(2);
        //System.out.println("right c: " + c + " xBegin: " + xBegin + " xEnd:" + xEnd);
        if (c > xEnd && r ==  MultipleSelectionRenderer.selectionList.get(3))
            MultipleSelectionRenderer.selectionList.set(2,c);
        else if (xEnd == xBegin && c > xEnd)
        {
             MultipleSelectionRenderer.selectionList.set(1,c);
             MultipleSelectionRenderer.selectionList.set(2,c);
        }
        else
            MultipleSelectionRenderer.selectionList.set(1,c);
        //test("moveRight", null);
    }
    
    private void moveUp(int r, int c)
    {
        int bRow = MultipleSelectionRenderer.selectionList.get(0);
        int eRow = MultipleSelectionRenderer.selectionList.get(3);
        if (r == bRow)
        {
            MultipleSelectionRenderer.selectionList.set(2,c);
            MultipleSelectionRenderer.selectionList.set(1,c);
            MultipleSelectionRenderer.selectionList.set(3,r);
            MultipleSelectionRenderer.selectionList.set(0,r);
            MultipleSelectionRenderer.selectionList.set(4,0); // = equal
        }
        else if (MultipleSelectionRenderer.selectionList.get(4) < 0)
        {
            MultipleSelectionRenderer.selectionList.set(3,r);
        }
        else
        {
            MultipleSelectionRenderer.selectionList.set(4,1); // = up
            /*System.out.println("up row: " + r + " bRow " + bRow 
                + " eRow " + eRow + " column: " + c
                + " direction " + MultipleSelectionRenderer.selectionList.get(4));*/
            MultipleSelectionRenderer.selectionList.set(1,c);
            MultipleSelectionRenderer.selectionList.set(0, r);
        }
        //test("moveUp", null);
    }
    
    private void moveDown(int r, int c)
    {
        int bRow = MultipleSelectionRenderer.selectionList.get(0);
        int eRow = MultipleSelectionRenderer.selectionList.get(3);
        //System.out.println("r" + r + " bRow " + bRow + " eRow" + eRow);
        if (r == eRow)
        {
            MultipleSelectionRenderer.selectionList.set(2,c);
            MultipleSelectionRenderer.selectionList.set(1,c);
            MultipleSelectionRenderer.selectionList.set(3,r);
            MultipleSelectionRenderer.selectionList.set(0,r);
            MultipleSelectionRenderer.selectionList.set(4,0); // = equal
        }
        else if (MultipleSelectionRenderer.selectionList.get(4) == 1)
        {
            MultipleSelectionRenderer.selectionList.set(0,r);
        }
        else
        {
            MultipleSelectionRenderer.selectionList.set(4,-1); // = down
            MultipleSelectionRenderer.selectionList.set(2,c);
            MultipleSelectionRenderer.selectionList.set(3,r);
        }
        //test("moveDown", null);
    }
    
    private void makeSelections(final java.awt.event.MouseEvent me,
            javax.swing.JTable table)
    {
        if (! me.isControlDown())
        {
            java.awt.Point p = me.getPoint();
            int r = table.rowAtPoint(p);
            if (r < 0) return;
            int c = table.columnAtPoint(p);
            if (c > aktColumn)
                moveRight(r, c);
            else if (c < aktColumn)
                moveLeft(r, c);
            if (r > aktRow)
                moveDown(r, c);
            else if (r < aktRow)
                moveUp(r, c);
            aktRow = r;
            aktColumn = c;
        }
    }
    
    private void addMouseListener(javax.swing.JTable table)
    {
        table.addMouseListener(new java.awt.event.MouseAdapter()
        {
          @Override
          public void mousePressed(java.awt.event.MouseEvent e)
          {
              final int tr = table.getSelectedRow();
              final int col = table.getSelectedColumn();
              aktRow = tr;
              aktColumn = col;
              MultipleSelectionRenderer.mustSelect = true;
              //MultipleSelectionRenderer.selectionList.clear();
              MultipleSelectionRenderer.selectionList.add(tr); //first row
              MultipleSelectionRenderer.selectionList.add(col); //dot
              MultipleSelectionRenderer.selectionList.add(col); //mark
              MultipleSelectionRenderer.selectionList.add(tr); //last Row
              MultipleSelectionRenderer.selectionList.add(0); //last Direction: 
                            //0 = first and last row are equal, 1 = up, -1 = down
              //table.addNotify();
          }
          
          @Override
          public void mouseReleased(java.awt.event.MouseEvent e)
          {
            if(javax.swing.SwingUtilities.isLeftMouseButton(e))
            {
                final int cols[] = table.getSelectedColumns();
                if (cols.length < 1) return;
                javax.swing.SwingUtilities.invokeLater(new Runnable()
                {
                    public void run()
                    {
                        java.awt.Point p = e.getPoint();
                        int r = table.rowAtPoint(p);
                        //aktRow = r;
                        MultipleSelectionRenderer.mustSelect = false;
                        //System.out.println("mouseReleased mustSelect: " + MultipleSelectionRenderer.mustSelect);
                        test("mouseReleased", table);
                        MultipleSelectionRenderer.selectionList.clear();
                    }
                });
            }
          }
        });
    }
    
    public void setTblListeners(final javax.swing.JTable table)
    {
       addMouseListener(table);
       table.addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
       {
            @Override
            public void mouseDragged(final java.awt.event.MouseEvent me)
            {
                /*javax.swing.SwingUtilities.invokeLater(new Runnable()
                {
                    public void run()
                    {
                        makeSelections(me);
                    }
                });*/
                makeSelections(me, table);
            }
            @Override
            public void mouseMoved(final java.awt.event.MouseEvent me)
            {
                return;
            }
        });
   }
}
