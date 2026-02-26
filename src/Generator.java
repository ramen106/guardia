import java.security.SecureRandom;

public class Generator {
    private static final SecureRandom secRand = new SecureRandom();

    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lower = "abcdefghijklmnopqrstuvwxyz";
    private static final String numbers = "0123456789";
    private static final String symbols = "!@#$%^&*()_+-=[]{}|";
    private static final String characters = upper + lower + numbers + symbols;

    public static String generatePassword(int length)
    {
        StringBuilder password = new StringBuilder(length);
        
        // Putting all the substrings into an array to loop through
        // And pick a random character from each
        String[] pools = {upper, lower, numbers, symbols};
        int maxStringLength = characters.length();

        for(String pool : pools)
        {
            int maxSubstringLength = pool.length();
            int randomIdx = secRand.nextInt(0, maxSubstringLength);
            password.append(pool.charAt(randomIdx));
        }
        
        // Generate the rest of the password
        for(int i = 4; i < length; i++)
        {
            int randomCharIdx = secRand.nextInt(0, maxStringLength);
            char randomChar = characters.charAt(randomCharIdx);
            password.append(randomChar);
        }

        // Swap the first 4 indices with 4 other random indices
        // To ensure at least 1 upper, 1 lower, 1 symbol, 1 number placed at random points
        for(int i = 0; i < 4; i++)
        {
            int swapIdx = secRand.nextInt(length);
            char temp = password.charAt(i);
            password.setCharAt(i, password.charAt(swapIdx));
            password.setCharAt(swapIdx, temp);
        }

        return password.toString();
    }
}