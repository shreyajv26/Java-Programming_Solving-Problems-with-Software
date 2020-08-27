
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.File;

public class Part4 {
    
    public void findURL()
    {
        URLResource file = new  URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
   	for (String word : file.words()) 
   	    {
   	        String wordLower = word.toLowerCase();
   	        int pos = wordLower.indexOf("youtube.com");
   	        if (pos != -1) 
   	            {
   	                int beg = word.lastIndexOf("\"",pos);
   	                int end = word.indexOf("\"",pos+1);
   	                System.out.println(word.substring(beg+1,end));
   	            }
   	     }
    }
    
    public static void main (String[] args) {
    Part4 pr4 = new Part4();
    pr4.findURL();
    }
}
