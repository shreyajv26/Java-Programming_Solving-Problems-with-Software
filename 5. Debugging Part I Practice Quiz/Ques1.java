
/**
 * Write a description of Ques1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ques1 {
    
    public void findAbc(String input) {
    int index = input.indexOf("abc");
    while (true) {
        if (index == -1) {
            break;
        }
        String found = input.substring(index+1, index+4);
        System.out.println(index+1);
        System.out.println(index+4);
        System.out.println(found);
        index = input.indexOf("abc", index+4);
        if(index >= input.length()-3){
            break;
        }
    }
}
   public void test() {
    //findAbc("abcd");
    findAbc("abcdabc");
}

}
