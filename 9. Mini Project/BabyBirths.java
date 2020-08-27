
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        for(CSVRecord rec : parser){
            String name = rec.get(0);
            String gender = rec.get(1);
            int NumBorn = Integer.parseInt(rec.get(2));
            
            System.out.println("Name: " + name + "Gender: " + gender + "NumBorn: " + NumBorn);
        }
    }
    
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int girlNames = 0;
        int boyNames = 0;
        int totalNames = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            int NumBorn = Integer.parseInt(rec.get(2));  
            totalBirths = NumBorn + totalBirths;
            totalNames = totalNames + 1;
            if(rec.get(1).equals("M")){
                totalBoys += NumBorn;
                boyNames = boyNames + 1;
            }
            else{
                totalGirls += NumBorn;
                girlNames = girlNames + 1;
            }
        }
        System.out.println("Total number of Births = " + totalBirths);
        System.out.println("Total number of Boys = " + totalBoys);
        System.out.println("Total number of Girls = " + totalGirls);
        
        System.out.println("Total number of Names = " + totalNames);
        System.out.println("Total number of Boy Names = " + boyNames);
        System.out.println("Total number of Girl Names = " + girlNames);
    }
    
    public int getRank(int year, String name, String gender){
        FileResource fr = new FileResource("./us_babynames/us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        
        int rank = 0;
        boolean found = false;
        
        for(CSVRecord rec : parser){
            String name1 = rec.get(0);
            String gender1 = rec.get(1);
            if(gender1.equals(gender)){
                rank ++;
                if(name1.equals(name)){
                    found = true;
                    break;
                }
            }
        }
        if(found == false){
            return -1;
        }
        return rank;
    }
    
    public String getName(int year, int rank, String gender){
        FileResource fr = new FileResource("./us_babynames/us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        int count = 0;
        boolean found = false;
        String name = "";
        
        for(CSVRecord rec : parser){
            String gender1 = rec.get(1);
            if(gender1.equals(gender)){
                count ++;
                if(count == rank){
                    found = true;
                    name = rec.get(0);
                    break;
                }
            }
        }
        if(found == false){
            return "NO NAME";
        }
        return name;
    } 
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        FileResource fr = new FileResource("./us_babynames/us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        int rank = getRank(year,name,gender);
        System.out.println("Rank is: " + rank);
        
        FileResource fr1 = new FileResource("./us_babynames/us_babynames_by_year/yob" + newYear + ".csv");
        CSVParser parser1 = fr1.getCSVParser(false);
        String name_out = getName(newYear,rank,gender);
        
        System.out.println(name + " born in " + year + " would be " + name_out + " born in " + newYear);
    }
    
    public int yearOfHighestRank(String name, String gender){
        int rank = 9999;
        int yearOfHighestRank = 0;
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            String filename = f.getName();
            
            int filename_year = Integer.parseInt(filename.substring(3,7));
            int rank1 = getRank(filename_year,name,gender); 
            
            if(rank1 < rank){
                rank = rank1;
                yearOfHighestRank = filename_year;
            }
        }
        return yearOfHighestRank;
    }
    
    public double getAverageRank(String name, String gender){
        int rank = 0;
        int count = 0;
        double AverageRank = 0.0;
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            String filename = f.getName();
            int filename_year = Integer.parseInt(filename.substring(3,7));
            int rank1 = getRank(filename_year,name,gender); 
            
            rank = rank + rank1;
            count = count + 1;
        }
        AverageRank = (double) rank/count;
        return AverageRank;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        FileResource fr = new FileResource("./us_babynames/us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        
        int total = 0;
        boolean found = false;
        
        for(CSVRecord rec : parser){
            String name1 = rec.get(0);
            String gender1 = rec.get(1);
            int NumBorn = Integer.parseInt(rec.get(2));
            
            if(gender1.equals(gender)){
                if(name1.equals(name)){
                    found = true;
                    break;
                }
                else{
                    total = total + NumBorn;
                }
            }
        }
        if(found == false){
            return -1;
        }
        return total;
    }
    
    public void testtotalBirths(){
       FileResource fr = new FileResource();
       totalBirths(fr);
    }
    
    public void testgetRank(){
        int rank = getRank(1971,"Frank","M");
        System.out.println("Rank is: " + rank);
    }
    
    public void testgetName(){
        String name = getName(1982,450,"M");
        System.out.println("Name is: " + name);
    }
    
    public void testwhatIsNameInYear(){
        whatIsNameInYear("Owen", 1974, 2014 , "M");
    }
    
    public void testyearOfHighestRank(){
        int yearOfHighestRank = yearOfHighestRank("Mich", "M");
        System.out.println(yearOfHighestRank);
    }
    
    public void testgetAverageRank(){
        double getAverageRank = getAverageRank("Robert", "M");
        System.out.println(getAverageRank);
    }
    
    public void testgetTotalBirthsRankedHigher(){
        int total = getTotalBirthsRankedHigher(1990, "Drew" , "M");
        System.out.println(total);
    }
}
