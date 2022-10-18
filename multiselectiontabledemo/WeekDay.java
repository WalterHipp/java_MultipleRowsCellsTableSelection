/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multiselectiontabledemo;

/**
 *
 * @author walter
 */
public class WeekDay
{
    static int lstMonthDay[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static String weekDay[] = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
    
    /* works as well as dayOfWeek
    private int dow(int d1, int m1, int y1)
    {
       String wd[] = {"So", "Mo", "Di", "Mi", "Do", "Fr", "Sa" };
       int d = d1;
       int m = m1;
       int y = y1;
       int t[] = {0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};
       //y -= m < 3;
       if( m < 3) y = y-1;
       int weekday = (y + y/4 - y/100 + y /400 + t[m-1] + d) % 7;
       System.out.println(d1 + "." + m1 + "." + y1 + " = " + weekday + " d:" + wd[weekday]);
       return weekday;
       
    } */
    
     public static int dayOfWeek(int d, int m, int y)
     {
        /*int d = d1;
        int m = m1;
        int y = y1; */
        //System.out.println(d + "." + m + "." + y);
        int weekday = ((23*m/9+d+4+(m<3?y--:y-2)+y/4-y/100+y/400)%7);
        //System.out.println("dow:" + weekday + " WD: " + weekDay[weekday]);
        //return wd[weekday];
        return weekday;
     }
     
    public static void main(String args[])
    {
        dayOfWeek(1, 1, 2022);
    }
}
