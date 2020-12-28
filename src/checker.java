/*
    Checker for literally only 1 reason,
    that reason being to check if 3 vars
    are valid numerical numbers (none
    of that decimal, letter or null
    randomness)
 */

public class checker {

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
