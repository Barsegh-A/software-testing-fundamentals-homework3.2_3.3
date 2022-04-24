package utils;

public class TestUtils {
    public static int getNumbers(String text) {
        return Integer.parseInt(text.replaceAll("[^0-9]", ""));
    }
}
