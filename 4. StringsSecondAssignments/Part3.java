
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    
   public int findStopCodon(String dna, int startIndex, String stopCodon){
        
        int currIndex = dna.indexOf(stopCodon, startIndex+3);
        while(currIndex != -1){
        int diff = currIndex - startIndex;
            if(diff % 3 == 0){
                return currIndex;
            }
            else{
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
   }
   
   public String findGene(String dna, int where){
        String startCodon = "ATG";
        int startIndex = dna.indexOf(startCodon,where);
        if(startIndex == -1)
            {
                return "";
            }
        
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        
        int temp = Math.min(taaIndex,tagIndex);
        int minIndex = Math.min(temp,tgaIndex);
        if(minIndex == dna.length()){
            return "";
        }
        else{
            return dna.substring(startIndex,minIndex+3);
        }
    }
    public void printAllGenes(String dna){
       //dna = "ATGxxxTAAtttATGctgcaacggTGAaaga";
       int StartIndex = 0;
       while(true){
           String currentGene = findGene(dna,StartIndex);
           if(currentGene.isEmpty()){
               break;
           }
           
           System.out.println(currentGene);
           StartIndex = dna.indexOf(currentGene,StartIndex) + currentGene.length();
       }
   }
   
   public int countGenes(String dna){
       int StartIndex = 0;
       int count = 0;
       
       while(true){
           String currentGene = findGene(dna,StartIndex);
           int currIndex = dna.indexOf(currentGene);
           
           //System.out.println(currIndex);
           if(currentGene.isEmpty()){
                break;
            }
            else{
                count = count + 1;
                //System.out.println(count);
                StartIndex = currIndex + currentGene.length();
            }
       }
       return count;
   }
   
   public void testCountGenes(){
       String dna = "ATGTAAGATGCCCTAGT";
       int count = countGenes(dna);
       System.out.println(count);
   }
}
