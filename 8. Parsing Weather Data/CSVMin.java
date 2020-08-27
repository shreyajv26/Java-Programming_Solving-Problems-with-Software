
/**
 * Write a description of CSVMax here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.*;
import java.io.File;
import java.io.*;

public class CSVMin {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord LowestSoFar = null;
        
        for(CSVRecord currentRow : parser){
            if(LowestSoFar == null){
                LowestSoFar = currentRow;
            }
            
            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(LowestSoFar.get("TemperatureF"));
                
                if(currentTemp == -9999){
                    currentTemp = lowestTemp;
                }
                else if(lowestTemp > currentTemp){
                    LowestSoFar = currentRow;
                }
            }
        }
        return LowestSoFar;
    }
    
    public String fileWithColdestTemperature(){
        CSVRecord lowestSoFar = null;
        String filename = null;
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if(lowestSoFar == null){
                lowestSoFar = currentRow;
                filename = f.getName();
            }
            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));
                
                if(currentTemp == -9999){
                    currentTemp = lowestTemp;
                }
                else if(lowestTemp > currentTemp){
                    lowestSoFar = currentRow;
                    filename = f.getName();
                }
            }
        }
        System.out.println(lowestSoFar);
        return filename;
    }
    
        public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord LowestSoFar = null;
        
        for(CSVRecord currentRow : parser){
            if(LowestSoFar == null){
                LowestSoFar = currentRow;
            }
            
            else{
                if(currentRow.get("Humidity") == "N/A"){
                    break;
                }
                else{
                    double currentHum = Double.parseDouble(currentRow.get("Humidity"));
                    double lowestHum = Double.parseDouble(LowestSoFar.get("Humidity"));
                    if(lowestHum > currentHum){
                        LowestSoFar = currentRow;
                    }
                }
            }
        }
        return LowestSoFar;
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestSoFar = null;
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            
            if(lowestSoFar == null){
                lowestSoFar = currentRow;
            }
            else{
                String Hum = currentRow.get("Humidity");
                if(!Hum.equals("N/A")){
                    break;
                }
                else{
                    double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
                    double lowestTemp = Double.parseDouble(lowestSoFar.get("Humidity"));
                
                    if(lowestTemp > currentTemp){
                        lowestSoFar = currentRow;
                    }
                }
            }
        }
        return lowestSoFar;
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        CSVRecord Row1 = null;
        double AverageTemp = 0.0;
        double AddedTemp = 0.0;
        int count = 0;
        
        for(CSVRecord currentRow : parser){
            if(Row1 == null){
                Row1 = currentRow;
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                if(currentTemp == -9999){
                    currentTemp = 0.0;
                }
                else{
                    AddedTemp = AddedTemp + currentTemp;
                    count = count+1;
                }
            }
            
            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                if(currentTemp == -9999){
                    currentTemp = 0.0;
                }
                else{
                    AddedTemp = AddedTemp + currentTemp;
                    count = count+1;
                }
            }
        }
        AverageTemp = AddedTemp/count;
        return AverageTemp;
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        CSVRecord Row1 = null;
        double AverageTemp = 0.0;
        double AddedTemp = 0.0;
        int count = 0;
        
        for(CSVRecord currentRow : parser){
            if(Row1 == null){
                Row1 = currentRow;
                int humidity = Integer.parseInt(currentRow.get("Humidity"));
                
                if(humidity >= value){
                    double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                    
                    if(currentTemp == -9999){
                        currentTemp = 0.0;
                    }
                    else{
                    AddedTemp = AddedTemp + currentTemp;
                    count = count+1;
                    }
                }
            }
            
            else{
                int humidity = Integer.parseInt(currentRow.get("Humidity"));
                
                if(humidity >= value){
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                   
                   if(currentTemp == -9999){
                        currentTemp = 0.0;
                   }
                   else{
                       AddedTemp = AddedTemp + currentTemp;
                       count = count+1;
                   }
                }
            }
        }
       AverageTemp = AddedTemp/count;
        if(AverageTemp == 0.0){
            System.out.println("No temperatures with that humidity");
        }
       
       return AverageTemp;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord lowest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest Temperature was " + lowest.get("TemperatureF") + " at " + lowest.get("DateUTC"));
    }
    
    public void testFileWithColdestTemperature(){
        String fileName = fileWithColdestTemperature();
        System.out.println(fileName);
        FileResource fr = new FileResource("./nc_weather/2013/" + fileName);
        CSVRecord lowest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest Temperature was " + lowest.get("TemperatureF") + " at " + lowest.get("DateUTC"));
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowest = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg_temp = averageTemperatureInFile(parser);
        System.out.println("Average Temperature in file is " + avg_temp);
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg_temp = averageTemperatureWithHighHumidityInFile(parser, 80);
        System.out.println("Average Temperature in file > certain Humidity is " + avg_temp);
    }
}
