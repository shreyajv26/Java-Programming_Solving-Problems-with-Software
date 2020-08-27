
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna,String start_codon, String stop_codon)
    {
        String result = "";
        
        int startIndex = dna.indexOf(start_codon);  
        if (startIndex == -1)
            {
            return "";
            }
        
        int endIndex = dna.indexOf(stop_codon,startIndex+3);
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
        String start_codon = "ATG";
        String stop_codon = "TAA";
        
        String dna = "AATGCGCTAATATGGT";
        System.out.println("Correct DNA strand is:" +dna);
        String gene = findSimpleGene(dna,start_codon,stop_codon);
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
        
        //Regular correct DNA with output lowercase
        dna = "AATGCGCTAATATGGT";
        System.out.println("Correct Uppercase DNA strand is:" +dna);
        gene = findSimpleGene(dna,start_codon,stop_codon);
        length_gene = gene.length();
        if (length_gene%3 != 0)
            {
             System.out.println("Not a valid DNA");
            }
        else
            {
            System.out.println("Gene is:" +gene.toLowerCase());
            }
            
        //Regular correct DNA with output uppercase
        dna = "aatgcgctaatatggt";
        String dna_upper = dna.toUpperCase();
        System.out.println("Correct Lowercase DNA strand is:" +dna);
        System.out.println("Correct Uppercase DNA strand is:" +dna_upper);
        gene = findSimpleGene(dna_upper,start_codon,stop_codon);
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
        gene = findSimpleGene(dna,start_codon,stop_codon);
        length_gene = gene.length();
        if (length_gene%3 != 0)
            {
             System.out.println("Not a valid DNA");
            }
        else
            {
            System.out.println("Gene is:" +gene);
            }
        
        //DNA not containing TAA
        dna = "AATGCGTAGTATGGT";
        System.out.println("DNA not with TAA strand is:" +dna);
        gene = findSimpleGene(dna,start_codon,stop_codon);
        length_gene = gene.length();
        if (length_gene%3 != 0)
            {
             System.out.println("Not a valid DNA");
            }
        else
            {
            System.out.println("Gene is:" +gene);
            }
        
        //DNA with no ATG or TAA
        dna = "AATCGTACTATCGT";
        System.out.println("DNA neither with ATG or TAA strand is:" +dna);
        gene = findSimpleGene(dna,start_codon,stop_codon);
        length_gene = gene.length();
        if (length_gene%3 != 0)
            {
             System.out.println("Not a valid DNA");
            }
        else
            {
            System.out.println("Gene is:" +gene);
            }
        
        //DNA not with multiples of 3
        dna = "AATGGCTAATATGGT";
        System.out.println("DNA not multiple of 3 strand is:" +dna);
        gene = findSimpleGene(dna,start_codon,stop_codon);
        length_gene = gene.length();
        if (length_gene%3 != 0)
            {
             System.out.println("Not a valid DNA");
            }
        else
            {
            System.out.println("Gene is:" +gene);
            }
    }


    public static void main (String[] args) {
    Part2 pr2 = new Part2();
    pr2.testSimpleGene();
    }
    
}

