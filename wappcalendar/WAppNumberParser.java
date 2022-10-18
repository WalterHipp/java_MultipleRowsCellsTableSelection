package multiplerowscellstableselection.wappcalendar;

public class WAppNumberParser
{

  /* public WAppNumberParser()
  {
  } */

  public static java.util.Vector<Integer> parseForNumber(String s)
  {
      java.util.Vector<Integer> ergv = null;
      char c[] = s.toCharArray();
  //System.out.println("numberParser: " + s + " c.length: " + c.length);
      int stelle = 1;
      int num = 0;
      int erg = 0;
      boolean isZahl = false;
      for (int i = c.length - 1; i >= 0; i--)
      {
          switch(c[i])
          {
            case '0': num = 0;
                      break;
            case '1': num = 1;
                      break;
            case '2': num = 2;
                      break;
            case '3': num = 3;
                      break;
            case '4': num = 4;
                      break;
            case '5': num = 5;
                      break;
            case '6': num = 6;
                      break;
            case '7': num = 7;
                      break;
            case '8': num = 8;
                      break;
            case '9': num = 9;
                      break;
            default:  if (isZahl)
                      {
                        if (ergv == null) ergv = new java.util.Vector<Integer>();
                        ergv.add(new Integer(erg));
                      }
  //System.out.println("neue Zahl" + " erg:" + erg);
                      erg = 0;
                      num = 0;
                      stelle = 1;
                      isZahl = false;
                      break;
          }
          if (c[i] >= '0'  && c[i] <= '9')
          {
            isZahl = true;
            erg += num *= stelle;
            stelle *= 10;
            //System.out.println("num: " + num + " erg: " + erg + " i: " + i);
          }
      }
      if (isZahl)
      {
        if (ergv == null) ergv = new java.util.Vector<Integer>();
        ergv.add(new Integer(erg));
      }
      //for (int i = ergv.size() - 1; i >= 0; i--)
        //System.out.println(i + ". Zahl: " + ((Integer) ergv.elementAt(i)).intValue());
      return ergv;
  }
}