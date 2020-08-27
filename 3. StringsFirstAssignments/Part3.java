
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {

    public int twoOccurrences(String stringa, String stringb)
    {
        int length_stringa = stringa.length();
        int length_stringb = stringb.length();
        int count = 0;
        
        for(int i=0; i<=length_stringb-1; i++)
            {
                int startIndex = stringb.indexOf(stringa);
                if(startIndex != -1)
                    {
                        count++;
                    }
                stringb = stringb.substring(startIndex+1);
                startIndex = stringb.indexOf(stringa);
            }
        return count;
    }
    
    public String lastPart(String stringa, String stringb)
    {
        int length_stringa = stringa.length();
        int length_stringb = stringb.length();
        int startIndex = stringb.indexOf(stringa);
        if (startIndex != -1)
            {
                stringb = stringb.substring(startIndex+length_stringa);
                return stringb;
            }
        else
            {
                return stringb;
            }
    }
    
    public void testing()
        {
            String stringa = "ATG";
            String stringb = "TAAATGATGATGATGATGATATG";
            int count = twoOccurrences(stringa, stringb);
            System.out.println(count);
            if (count >=2)
            {
                System.out.println("True");
            }
            else
            {
                System.out.println("False");
            }
            
            stringa = "ATG";
            stringb = "ATGTAAT";
            count = twoOccurrences(stringa, stringb);
            System.out.println(count);
            if (count >=2)
            {
                System.out.println("True");
            }
            else
            {
                System.out.println("False");
            }
            
            stringa = "ATG";
            stringb = "ATCCTAAT";
            count = twoOccurrences(stringa, stringb);
            System.out.println(count);
            if (count >=2)
            {
                System.out.println("True");
            }
            else
            {
                System.out.println("False");
            }
            
            //Testing lastPart
            stringa = "AN";
            stringb = "BANANA";
            String final_string = lastPart(stringa, stringb);
            System.out.println(final_string);
            
            stringa = "zoo";
            stringb = "forest";
            final_string = lastPart(stringa, stringb);
            System.out.println(final_string);
        }
    
    public static void main (String[] args) {
    Part3 pr3 = new Part3();
    pr3.testing();
    }
}
