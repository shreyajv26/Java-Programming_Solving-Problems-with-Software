
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String Stringa, String Stringb){
        int StartIndex = 0;
        int count = 0;
        
        while(StartIndex != -1){
            int currIndex = Stringb.indexOf(Stringa, StartIndex);
            //System.out.println(currIndex);
            if(currIndex == -1){
                break;
            }
            else{
                count = count + 1;
                //System.out.println(count);
                StartIndex = currIndex + Stringa.length();
            }
        }
        return count;
    }
    
    public void testHowMany(){
        String Stringb = "ATGAACGAATTGAATC";
        String Stringa = "GAA";
        int count = howMany(Stringa, Stringb);
        System.out.println(count);
        
        Stringb = "ATAAAA";
        Stringa = "AA";
        count = howMany(Stringa, Stringb);
        System.out.println(count);
    }

}
