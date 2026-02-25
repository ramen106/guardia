import java.security.SecureRandom;

public class Generator {
    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lower = "abcdefghijklmnopqrstuvwxyz";
    private static final String numbers = "0123456789";
    private static final String symbols = "!@#$%^&*()_+-=[]{}|";
    private static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=[]{}|";

    public static String generatePassword(int length)
    {
        SecureRandom secRand = new SecureRandom();
        StringBuilder password = new StringBuilder();
        
        // Random indices to get random upper, lower, numbers, symbols from
        // And random indices to place them at in the resulting password string
        int[] indices = new int[4];
        int[] indicesToPlace = new int[4];
        int maxSubstringLength = upper.length();

        for(int i = 0; i < 4; i++)
        {
            indices[i] = secRand.nextInt(0, maxSubstringLength);
            indicesToPlace[i] = secRand.nextInt(0, length);
        }
        
        // Wrap around if the resulting number too high
        indices[2] %= numbers.length();
        indices[3] %= symbols.length();
        
        // Fetch the randomly chosen required characters
        char[] chosenInitialChars = {upper.charAt(indices[0]), lower.charAt(indices[1]), numbers.charAt(indices[2]), symbols.charAt(indices[3])};

        for(int i = 0; i < length; i++)
        {
            int randomCharIdx = secRand.nextInt(0, 82);
            char randomChar = characters.charAt(randomCharIdx);
            password.append(randomChar);
        }

        // Overwrite the indices we generated earlier with the chosen random characters
        // To ensure at least 1 upper, 1 lower, 1 symbol, 1 number

        for(int i = 0; i < 4; i++)
        {
            password.setCharAt(indicesToPlace[i], chosenInitialChars[i]);
        }

        return password.toString();
    }
}