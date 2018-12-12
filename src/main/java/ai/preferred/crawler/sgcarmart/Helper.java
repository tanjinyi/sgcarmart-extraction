package ai.preferred.crawler.sgcarmart;

public class Helper {
    //remove symbols and letters in numbers.
    public static int parseStringtoNum(String aString) {
        String newString = aString.replaceAll("[^0-9]", "");
        int result = newString.length() > 0 ? Integer.parseInt(newString) : 0;
        return result;
    }
}
