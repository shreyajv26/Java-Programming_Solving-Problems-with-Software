
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna)
    {
        String result = "";
        
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1)
            {
            return "";
            }
        
        int endIndex = dna.indexOf("TAA",startIndex+3);
        if (endIndex == -1)
            {
            return "";
            }
            
        result = dna.substring(startIndex,endIndex+3);
        return result;
    }
    
    
    public void testSimpleGene()
    {
        //Regular correct DNA
        String dna = "AATGCGCTAATATGGT";
        System.out.println("Correct DNA strand is:" +dna);
        String gene = findSimpleGene(dna);
        int length_gene = 0; 
        length_gene = gene.length();
        if (length_gene%3 != 0)
            {
             System.out.println("Not a valid DNA");
            }
        else
            {
            System.out.println("Gene is:" +gene);
        }
        
        //DNA not containing ATG
        dna = "ACGTAATATAAGT";
        System.out.println("DNA not with ATG strand is:" +dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is:" +gene);
        
        //DNA not containing TAA
        dna = "AATGCGTAGTATGGT";
        System.out.println("DNA not with TAA strand is:" +dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is:" +gene);
        
        //DNA with no ATG or TAA
        dna = "AATCGTACTATCGT";
        System.out.println("DNA neither with ATG or TAA strand is:" +dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is:" +gene);
        
        //DNA not with multiples of 3
        dna = "AATGGCTAATATGGT";
        System.out.println("DNA not multiple of 3 strand is:" +dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is:" +gene);
    }


    public static void main (String[] args) {
    Part1 pr1 = new Part1();
    pr1.testSimpleGene();
    }
    
}

