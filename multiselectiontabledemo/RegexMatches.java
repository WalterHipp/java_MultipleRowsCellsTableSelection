/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multiselectiontabledemo;

/**
 *
 * @author unknown, code found at:
 * http://www.tutorialspoint.com/java/java_regular_expressions.htm
 */
    
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches
{
    private static Pattern pattern;
    private static Matcher matcher;
    
    public RegexMatches()
    {
    }
    
    public static void test()
    {
        String s = "Sa EU";
        boolean b = RegexMatches.testStringContains(".*(EU).*", s);
        if (b)
           System.out.println("matches: " + s);
        else
           System.out.println("no matches: " + s);
    }
    
    public static boolean testStringContains(String regex, String input)
    {
       pattern = Pattern.compile(regex);
       matcher = pattern.matcher(input);

       //System.out.println("Current REGEX is: "+ regex);
       //System.out.println("Current INPUT is: "+ input);

       //System.out.println("lookingAt(): " + matcher.lookingAt());
       //System.out.println("matches(): " + matcher.matches());
       return matcher.matches();
    }
    
    public static boolean testLookingAt(String regex, String input)
    {
       pattern = Pattern.compile(regex);
       matcher = pattern.matcher(input);

       /*System.out.println("Current REGEX is: "+ regex);
       System.out.println("Current INPUT is: "+ input);

       System.out.println("lookingAt(): " + matcher.lookingAt());
       System.out.println("matches(): " + matcher.matches());*/
       return matcher.lookingAt();
    }

    public static void main( String args[] )
    {
       RegexMatches.test();
   }
}
