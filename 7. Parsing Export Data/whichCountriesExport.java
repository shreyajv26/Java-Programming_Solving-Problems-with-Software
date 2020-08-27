
/**
 * Write a description of whichCountriesExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class whichCountriesExport {
    public void countryInfo(CSVParser parser, String country){
        for(CSVRecord record: parser){
            String country1 = record.get("Country");
            
            if(country1.contains(country)){
                String exports = record.get("Exports");
                String Value = record.get("Value (dollars)");
            
                System.out.print(country1 + ":" );
                System.out.print(exports + ":");
                System.out.println(Value);
            }
        }
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record: parser){
            String exports = record.get("Exports");
            
            if(exports.contains(exportItem1) && exports.contains(exportItem2)){
            String Country = record.get("Country");
            
            System.out.println(Country);
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem1){
        int count = 0;
        for(CSVRecord record: parser){
            String exports = record.get("Exports");
            
            if(exports.contains(exportItem1)){
            String Country = record.get("Country");
            count = count + 1;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record: parser){
            String Value = record.get("Value (dollars)");
            
            if(Value.length() > amount.length()){
            String Country = record.get("Country");
            
            System.out.print(Country + "  ");
            System.out.println(Value);
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        countryInfo(parser,"Nauru");
        
        CSVParser parser1 = fr.getCSVParser();
        listExportersTwoProducts(parser1,"cotton","flowers");
        
        CSVParser parser2 = fr.getCSVParser();
        int count = numberOfExporters(parser2,"cocoa");
        System.out.println(count);
        
        CSVParser parser3 = fr.getCSVParser();
        bigExporters(parser3, "$999,999,999,999");
    }
    

}
