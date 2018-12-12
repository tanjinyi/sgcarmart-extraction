package ai.preferred.crawler.sgcarmart;
public class Helper {
    //remove symbols and letters in numbers.
    public static int parseStringtoNum(String aString){
        String newString = aString.replaceAll("[^0-9]","");
        int result = Integer.parseInt(newString);
        return result;
    }
}
