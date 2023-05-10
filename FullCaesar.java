import java.util.Scanner;

public class FullCaesar {

    public static void main(String[] args) throws Exception {
        Scanner text = new Scanner(System.in);

        System.out.println("Enter the plain text: ");
        String plainText = text.nextLine();

        System.out.println("Enter the key: ");
        int key = text.nextInt();

        System.out.println("----------------------");
        practical3(plainText, key);
        text.close();
    }


    public static void practical3(String plainTextt, int key){

        String engAlphabet = "abcdefghijklmnopqrstuvwxyz";

        int[] plainttextIntArray = new int[plainTextt.length()];
        int[] cipherTextIntArray = new int[plainTextt.length()];
        char[] cipherTextCharArray = new char[plainTextt.length()];

        for (int i=0; i < plainTextt.length(); i++){
            plainttextIntArray[i] = engAlphabet.indexOf(plainTextt.charAt(i));
            cipherTextIntArray[i] = (plainttextIntArray[i] + key) % 26;
            cipherTextCharArray[i] = engAlphabet.charAt(cipherTextIntArray[i]);
        }

        System.out.println("Plain Text: " + plainTextt +"\tCipher Text: " + String.valueOf(cipherTextCharArray));

    }
}
