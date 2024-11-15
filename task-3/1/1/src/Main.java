import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int randomNumber = 100 + random.nextInt(900);
        System.out.println("Three digit number = " + randomNumber);
        System.out.println("Largest digit = " + maxInteger(randomNumber));
        System.out.println("Three digit number = " + randomNumber);
        System.out.println("Largest digit = " + maxIntegerAnyLenght(randomNumber));
    }

    //We immediately know that the number will be three digits
    public static int maxInteger(int num) {
        if (num % 10 == 9) {
            return 9;
        } else {
            int max = 0;
            while (num != 0) {
                int digit = num % 10;
                if (max < digit) {
                    max = digit;
                } else {
                    num /= 10;
                }
            }
            return max;
        }
    }

    //For a number of any length
    public static int maxIntegerAnyLenght(int num) {
        if (num % 10 == 9) {
            return num % 10;
        } else {
            String numStr = Integer.toString(num);
            int max = 0;
            for (int i = 0; i < numStr.length(); i++) {
                int digit = Character.getNumericValue(numStr.charAt(i));
                if (digit > max) {
                    max = digit;
                }
            }
            return max;
        }
    }
}
