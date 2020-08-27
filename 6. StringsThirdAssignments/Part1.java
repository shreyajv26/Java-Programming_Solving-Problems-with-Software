
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.StorageResource;
import edu.duke.FileResource;

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

    public int findctgCodon(String dna){
        int count = 0;
        int currIndex = dna.indexOf("CTG");
        while(currIndex != -1){
            count++;
            currIndex = dna.indexOf("CTG", currIndex + 3);
        }
        return count;
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
   } // break statement directly brings us here
   
   public StorageResource getAllGenes(String dna){
       //dna = "ATGxxxTAAtttATGctgcaacggTGAaaga";
       StorageResource genList = new StorageResource();
       int StartIndex = 0;
       int count = 0;
       while(true){
           String currentGene = findGene(dna,StartIndex);
           if(currentGene.isEmpty()){
           
               break;
           }
           //System.out.println(currentGene);
           genList.add(currentGene);
           count = count + 1;
           System.out.println(count);
           StartIndex = dna.indexOf(currentGene,StartIndex) + currentGene.length();
       }
       return genList;
   }
   
   public float cgRatio(String dna){
       int count_cg = 0;
       int length_dna = dna.length();
       char c = 'C';
       char g = 'G';
       
       for (int i = 0; i < length_dna; i++){
           if (dna.charAt(i) == c || dna.charAt(i) == g){
               //System.out.println(dna.charAt(i));
               count_cg++;
           }
       }
       //System.out.println(count_cg);
       //System.out.println(length_dna);
       //If you dont do this typecast, java will think you are dividing integers and will give integer as output
       float cgRatio = (float)count_cg/length_dna;
       return cgRatio;
   }
   
   public int countCTG(String dna){
       int count_ctg = 0;
       int start_Index = dna.indexOf("CTG");
       
       while(true){
           if(start_Index == -1){
               break;
           }
           count_ctg++;
           start_Index = dna.indexOf("CTG",start_Index+3);
       }
       
       return count_ctg;
   }
   
   public void processGenes(StorageResource sr){
       // Count and Print all the strings in sr longer than 9
       int count = 0;
       int count_total = 0;
       float cgRatio = 0;
       int count_cg = 0;
       int length_longest = 0;
       String String_cg = "";
       String longest_String = "";
       String longer_string = "";
       
       for(String g: sr.data()){
           count_total++;
           if(g.length() > 60){
           count = count + 1;
           longer_string = g;
           System.out.println("Strings longer than 9 charecters "+longer_string);
           }
           cgRatio = cgRatio(g);
           if(cgRatio > 0.35){
               count_cg = count_cg + 1;
               String_cg = g;
               System.out.println("String_cg "+String_cg);
           }
           if(g.length() > length_longest){
               length_longest = g.length();
               longest_String = g;
           }
       }
       System.out.println("Count_total "+count_total);
       System.out.println("Count "+count);
       System.out.println("Count_cg "+count_cg);
       System.out.println("Length_longest "+length_longest);
       System.out.println("Longest_String "+longest_String);
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
   
   public void testonPart1(String dna){
       //dna = "ATGxxxxxxxxTAAATGyyyyyyzzTAGATGzzzzzzzyyxxxxxTGA";
       System.out.println("Testing All Genes On" +dna);
       StorageResource genes = getAllGenes(dna);
       for(String g: genes.data()){
           System.out.println(g);
       }
   }
   
   public void testonPart2(){
       String dna = "ATGCCATAGCTGCTGCTGCTGCTG";
       float cgRatio = cgRatio(dna);
       System.out.println("CG Ratio is "+cgRatio);
       
       int ctgCount = countCTG(dna);
       System.out.println("Count of CTGs in the DNA is "+ctgCount);
   }
   
   public void testProcessGenes(){
       System.out.println("DNA string that has some genes longer than 9 characters");
       //            012345678901234567890123456789012345678901234567
       String dna = "ATGxxxxxxxxTAAATGyyyyyyzzTAGATGzzzzzzzyyxxxxxTGA";
       StorageResource genes = getAllGenes(dna);
       processGenes(genes);
       
       System.out.println("DNA string that has no genes longer than 9 characters");
       dna = "ATGxxxTAA";
       genes = getAllGenes(dna);
       processGenes(genes);
       
       System.out.println("DNA string that has some genes whose C-G-ratio is higher than 0.35");
       dna = "ATGCCATAGCTGCTGCTGCTGCTG";
       genes = getAllGenes(dna);
       processGenes(genes);
       
       System.out.println("DNA string that has some genes whose C-G-ratio is lower than 0.35");
       dna = "ATGCTAA";
       genes = getAllGenes(dna);
       processGenes(genes);
   
   }
   
   public void testProcessGenesOnFile(){
       //From fileResource
       FileResource fr = new FileResource("GRch38dnapart.fa");
       String dna = fr.asString();
       String dna_upper = dna.toUpperCase();
       StorageResource genes = getAllGenes(dna_upper);
       processGenes(genes);
       int ctgIndex = findctgCodon(dna_upper);
       System.out.println("CTG codon count is "+ctgIndex);
   }
}

