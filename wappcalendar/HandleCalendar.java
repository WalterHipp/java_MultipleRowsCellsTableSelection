/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multiplerowscellstableselection.wappcalendar;

/**
 *
 * @author walter hipp
 */
public class HandleCalendar
{
    private static int maxDays, startDay, tst;
    public static String monateN[] = { "January", "February", "March", "April", "May", "June", "July", "August",
                           "September", "October", "November", "December" };
    
    public static String today;
    
    public HandleCalendar()
    {
        
    }
    
    public static String getMonthName(int m)
    {
        return monateN[m];
    }
    
    private void initDatumField()
    {
       String d = setNewDate(0, 0, 0);
       //aktDatum.setText(d);
    }
    
    public static String getToday()
    {
        if (today == null)
        {
            setNewDate(0, 0, 0);
        }
        return today;
    }
    
    public static String getDateString(int ye, int mo, int _day)
    {
       String day = String.valueOf(_day);
       if (day.length() < 2) day = "0" + day;
       String m = String.valueOf(mo);
       if (m.length() < 2) m = "0" + m;
       String y = String.valueOf(ye);

       return (day +"." + m + "." + y);
    }
    
    public static String setNewDate(int ye, int mo, int _day)
    {
       java.util.GregorianCalendar cal;

       if (ye <= 0) cal = new java.util.GregorianCalendar();
       else cal = new java.util.GregorianCalendar(ye, mo, _day);
       int m = cal.get(java.util.GregorianCalendar.MONTH);// + 1;
       //if (m.length() < 2) m = "0" + m;
       String y = String.valueOf(cal.get(java.util.GregorianCalendar.YEAR));
       String d = String.valueOf(cal.get(java.util.GregorianCalendar.DAY_OF_MONTH));
       
       if (today == null)
           today = d + ". " + monateN[m] + " " + y;
       //return (day +"." + m + "." + y);
  //System.out.println("m:" + m + " setNewDate" + monate[m] + " " + y);
       return (monateN[m] + " " + y);
    }
    
    public static int getMaxDays(String date)
    {
        //System.out.println("getMaxDays Date: " + date);
        String s1[] = date.split(" ");
        //for (int i = 0; i < s1.length; i++)
        //   System.out.println("getMaxDays dat[" + i + "]:" + s1[i]);
        s1[0] = String.valueOf(returnMonth(s1[0]));
        java.util.Vector<Integer> v1 = WAppNumberParser.parseForNumber(s1[0] + " " + s1[1]);
        int j = -1;
        int m = -1;
        if (v1 != null)
        {
            j = (v1.elementAt(0)).intValue();
            m = (v1.elementAt(1)).intValue();
        }
        java.util.Calendar calendar = new java.util.GregorianCalendar(j, m, 1);
        return calendar.getActualMaximum(java.util.GregorianCalendar.DAY_OF_MONTH);
    }
    
    public static int getStartDay(String date)
    {
        String s1[] = date.split(" ");
        s1[0] = String.valueOf(returnMonth(s1[0]));
        java.util.Vector<Integer> v1 = WAppNumberParser.parseForNumber(s1[0] + " " + s1[1]);
        int j = -1;
        int m = -1;
        if (v1 != null)
        {
            j = (v1.elementAt(0)).intValue();
            m = (v1.elementAt(1)).intValue();
        }
        java.util.Calendar calendar = new java.util.GregorianCalendar(j, m, 1);
        return calendar.get(java.util.Calendar.DAY_OF_WEEK);
    }
    
    public static int[] getMonth(String date)
    {
      int j = -1;
      int m = -1;
      //int d = -1;
      int ret[] = new int[2]; // 3];
      //System.out.println("getMonth date:" + date);
      String s1[] = date.split("\\.");
      //for (int i = 0; i < s1.length; i++)
      //      System.out.println("getMonth dat[" + i + "]:" + s1[i]);
      java.util.Vector<Integer> v1 = null;
      if (s1.length >= 2)
      {
          s1 = s1[1].split(" ");
          //for (int i = 0; i < s1.length; i++)
          //  System.out.println("getMonth2 dat[" + i + "]:" + s1[i]);
          s1[1] = String.valueOf(returnMonth(s1[1]));
          v1 = WAppNumberParser.parseForNumber(s1[1] + " " + s1[2]);
      }
      else if (s1.length == 1)
      {
          s1 = date.split(" ");
          //for (int i = 0; i < s1.length; i++)
          //  System.out.println("getMonth3 dat[" + i + "]:" + s1[i]);
          s1[0] = String.valueOf(returnMonth(s1[0]));
          v1 = WAppNumberParser.parseForNumber(s1[0] + " " + s1[1]);
      }
  //System.out.println("s1[0]:" + s1[0]);
      
      if (v1 != null)
      {
        j = (v1.elementAt(0)).intValue();
        m = (v1.elementAt(1)).intValue();
      }
      ret[0] = j; ret[1] = m; //ret[2] = d;
      return ret;
    }
    
    public static int returnMonth(String m_name)
    {
       if (m_name.equals("January")) return 0;
       if (m_name.equals("February")) return 1;
       if (m_name.equals("March")) return 2;
       if (m_name.equals("April")) return 3;
       if (m_name.equals("May")) return 4;
       if (m_name.equals("June")) return 5;
       if (m_name.equals("July")) return 6;
       if (m_name.equals("August")) return 7;
       if (m_name.equals("September")) return 8; 
       if (m_name.equals("October")) return 9;
       if (m_name.equals("November")) return 10;
       if (m_name.equals("December")) return 11;
       return -1;
    }
    
    public static boolean isSatSun(int j, int m, int _d)
    {
        //String[] days = {"NULL", "Sonntag", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag" };
        java.util.Calendar calendar = new java.util.GregorianCalendar(j, m, _d);
        int d = (calendar.get(calendar.DAY_OF_WEEK));
        //System.out.println("isSatSun j:" + j + " m:" + m + " _d:" + _d + " FD:" + d);
        if (d == 1)
            return true;
        if (d == 7) //1 = Sonntag, 7 = Samstag
            return true;
        return false;
    }
    
    public static int getMonthMaxDay(int mo, int year)
    {
        int r = 0;
        if (mo == 1 || mo == 3 || mo == 5 || mo == 7 || mo == 8 || mo == 10 || mo == 12 )
            r = 31;
        if (mo == 2)
        {
            java.util.Calendar calendar = new java.util.GregorianCalendar(year, mo-1, 1);
            r = calendar.getActualMaximum(java.util.GregorianCalendar.DAY_OF_MONTH);
            //System.out.println("NaxD" + r + " mo: " + mo + " y: " + year);
            //r = 27;
        }
        if (mo == 4 || mo == 6 || mo == 9 || mo == 11 )
          r = 30;
        return r;
    }
    
    public static String[] getMonthString(int mo, int year)
    {
        String m = "0";
        String mn = null;
        
        if (mo == 1 || mo == 3 || mo == 5 || mo == 7 || mo == 8 || mo == 10 || mo == 12 )
        {
            for (int i1 = 0; i1 <= 29; i1++)
                m += "0";
            switch (mo)
            {
                    case 1: mn = "January";
                            break;
                    case 3: mn = "March";
                            break;
                    case 5: mn = "May";
                            break;
                    case 7: mn = "July";
                            break;
                    case 8: mn = "August";
                            break;
                    case 10: mn = "October";
                            break;
                    case 12: mn = "December";
                            break;
             }
          }
          if (mo == 2)
          {
              int mxDay = getMonthMaxDay(2, year);
              for (int i1 = 0; i1 <= mxDay - 1; i1++)
                 m += "0";
              mn = "February";
          }
          if (mo == 4 || mo == 6 || mo == 9 || mo == 11 )
          {
             for (int i1 = 0; i1 <= 28; i1++)
               m += "0";
             switch (mo)
             {
                    case 4: mn = "April";
                            break;
                    case 6: mn = "June";
                            break;
                    case 9: mn = "September";
                            break;
                    case 11: mn = "November";
                            break;
             }
        }
        String ret[] = new String[2];
        ret[0] = m;
        ret[1] = mn;
        return ret;
    }
}
