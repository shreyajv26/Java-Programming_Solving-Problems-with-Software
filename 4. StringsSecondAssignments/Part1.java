
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
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
    
    public void testFindStopCodon(){
    //            01234567890123456789012345
    String dna = "xxxyyyzzzTAAxxxyyyzzzTAAzz";
    int dex = findStopCodon(dna,0,"TAA");
    System.out.println("Output should be 9");
    System.out.println(dex);
    
    //     0123456789012345678901234
    dna = "xxxxTAAyyyzzzyyyzzzTAGzzz";
    dex = findStopCodon(dna,0,"TAA");
    System.out.println("Output should be 25");
    System.out.println(dex);
    //     012345678901234567890123456
    dna = "xxxxxxTAGyyyzzzyyyzzzTAGzzz";
    dex = findStopCodon(dna,0,"TAG");
    System.out.println("Output should be 6");
    System.out.println(dex);
    
    //     012345678901234567890123456
    dna = "xxxxxxTGAyyyzzzyyyzzzTGAzzz";
    dex = findStopCodon(dna,0,"TGA");
    System.out.println("Output should be 6");
    System.out.println(dex);

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
   } // break statement directly brings us here

    public void testFindGene(){
    //            012345678901234567890
    String dna = "xxxyyyzzzxxxyyyzzzTAA";
    System.out.println("DNA string is "+dna);
    String gene = findGene(dna,0);
    System.out.println("GENE is "+gene);
    
    //     0123456789012345678901
    dna = "ATGxxxyyyTAAxxxyyyaaax";
    System.out.println("DNA string is "+dna);
    gene = findGene(dna,0);
    System.out.println("GENE is "+gene);
    
    //     0123456789012345678901
    dna = "ATGxxxyyyTAAxxxTAGaaax";
    System.out.println("DNA string is "+dna);
    gene = findGene(dna,0);
    System.out.println("GENE is "+gene);
    
    //     012345678901234567890123
    dna = "ATGxxxyyyyTAAxxxxyyyaaax";
    System.out.println("DNA string is "+dna);
    gene = findGene(dna,0);
    System.out.println("GENE is "+gene);
    
    //     0123456789012345678901
    dna = "ATGxxxyyyzzzxxxyyyaaax";
    System.out.println("DNA string is "+dna);
    gene = findGene(dna,0);
    System.out.println("GENE is "+gene);
   }
}
