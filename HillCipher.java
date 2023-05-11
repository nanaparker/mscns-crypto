// Practical Four (4) -> Implementation of the Hill Cipher

import java.util.Scanner;

public class HillCipher {
    public static void main(String[] args) throws Exception {
        Scanner text = new Scanner(System.in);
        Scanner choice = new Scanner(System.in);

        System.out.println("---Implementation of the Hill Cipher---");
        System.out.println("1. Encryption");
        System.out.println("2. Decryption");

        System.out.print("\nSelect a choice: ");
        int choices = choice.nextInt();

        switch(choices){
            case 1:
                System.out.print("Enter the plain text: ");
                String plainText = text.nextLine();
        
                System.out.print("Enter the key: ");
                String keyword = text.nextLine();

                hillCipherEnc(keyword, plainText);
                break;
            case 2:
                System.out.print("Enter the cipher text: ");
                String cipherText = text.nextLine();
        
                System.out.print("Enter the key: ");
                String key = text.nextLine();
                hillCipherDec(key, cipherText);
                break;
            default:
                System.out.println("Invalid response");
                System.exit(0);
        }
        choice.close();
        text.close();
    }

    // Personal Version
    public static void hillCipherEnc(String keyword, String plainText){

        // Verifying if key word fits requirements
        if (Math.floor(Math.sqrt(keyword.length())) != (int) Math.sqrt(keyword.length())){
            System.out.println("!!!!\tERROR\t!!!!\nKey word length must be in a square matrix");
            System.exit(0);
        }

        // Variables
        String alphabet = "abcdefghijklmnopqrstuvwxyz"; 
        int keyRows = (int) Math.sqrt(keyword.length()), keyCols= (int) Math.sqrt(keyword.length());
        int plainRows = 2, plainCols = Math.round((float)plainText.length()/2);
        int cipherRows = keyRows, cipherCols = (int) plainCols;
        int[][] keywordMat = new int[keyRows][keyCols];
        int[][] plainTextMat= new int[plainRows][(int) plainCols];
        int[][] cipherMat = new int[cipherRows][cipherCols];

        
        // Find out if there are any redundant characters that need
        // to be added to make the matrix complete
        int dummyBits = (plainRows * (int) plainCols) - plainText.length();

        if (dummyBits > 0) {
            for (int i = 0; i < dummyBits; i++){
                plainText = plainText.concat("x");
            }
        }
        System.out.println("Matrix: " + plainRows + " by "+ plainCols); 

        // Place keyword indexes in the matrix
        int count = 0;
        System.out.println("----Keyword----");
        for (int i = 0; i < keyRows; i++){
            for (int j = 0; j < keyCols; j++){
                keywordMat[j][i] = alphabet.indexOf(keyword.charAt(count));
                System.out.println("I Index: "+ j+ "\tJ Index: "+ i + "\tNumber: "+ keywordMat[j][i] + "\tLetter: " + alphabet.charAt(keywordMat[j][i]));
                
                if (count == keyword.length())
                    count = 0;
                else
                    count++;
            }
        }

        // Place plain text indexes in the matrix
        System.out.println("\n----PlainText----");
        int plainCount = 0;
        for (int i = 0; i < plainCols; i++){
            for (int j = 0; j < plainRows; j++){
                plainTextMat[j][i] = alphabet.indexOf(plainText.charAt(plainCount));
                System.out.println("I Index: "+ j+ "\tJ Index: "+ i + "\tNumber: "+ plainTextMat[j][i] + "\tLetter: " + alphabet.charAt(plainTextMat[j][i]));
                
                if (plainCount == plainText.length())
                    plainCount = 0;
                else
                    plainCount++;
            }
        }

        // Encrypt Plain Text
        System.out.println("\n----CipherText----");
        int sum = 0;
        for (int i = 0; i < plainRows; i++){
            for (int j = 0; j < plainCols; j++){
                for (int k = 0; k < keyRows;  k++){
                    sum = sum + (keywordMat[i][k] * plainTextMat[k][j]);
                }
                cipherMat[i][j] = sum % 26;
                System.out.println("I Index: "+ i+ "\tJ Index: "+ j + "\tNumber: "+ cipherMat[i][j] + "\tLetter: " + alphabet.charAt(cipherMat[i][j]));
                sum = 0;
            }
        }

        // Printing out Cipher text
        String cipher = "";
        for (int i = 0; i < cipherCols; i++){
            for (int j = 0; j < cipherRows; j++){
                cipher += alphabet.charAt(cipherMat[j][i]);
            }
        }

        System.out.println("Cipher Text: " + cipher);
    }


    public static void hillCipherDec(String keyword, String cipherText){

        // Verifying if key word fits requirements
        if (Math.floor(Math.sqrt(keyword.length())) != (int) Math.sqrt(keyword.length())){
            System.out.println("!!!!\tERROR\t!!!!\nKey word length must be in a square matrix");
            System.exit(0);
        }


        // Variables
        String alphabet = "abcdefghijklmnopqrstuvwxyz"; 
        int keyRows = (int) Math.sqrt(keyword.length()), keyCols= (int) Math.sqrt(keyword.length());
        int cipherRows = 2, cipherCols = Math.round((float)cipherText.length()/2);
        int plainRows = keyRows, plainCols = cipherCols;
        int[][] keywordMat = new int[keyRows][keyCols];
        int[][] cipherTextMat= new int[plainRows][plainCols];
        int[][] plainMat = new int[plainRows][plainCols];


        // Place keyword indexes in the matrix
        int count = 0;
        System.out.println("\n----Keyword----");
        for (int i = 0; i < keyRows; i++){
            for (int j = 0; j < keyCols; j++){
                keywordMat[i][j] = alphabet.indexOf(keyword.charAt(count));
                System.out.println("I Index: "+ i+ "\tJ Index: "+ j + "\tNumber: "+ keywordMat[i][j] + "\tLetter: " + alphabet.charAt(keywordMat[i][j]));
                
                if (count == keyword.length())
                    count = 0;
                else
                    count++;
            }
        }

        // Place cipher text indexes in the matrix
        System.out.println("\n----CipherText----");
        int cipherCount = 0;
        for (int i = 0; i < cipherCols; i++){
            for (int j = 0; j < cipherRows; j++){
                cipherTextMat[j][i] = alphabet.indexOf(cipherText.charAt(cipherCount));
                System.out.println("Row Index: "+ j + "\tCol Index: " + i + "\tNumber: "+ cipherTextMat[j][i] + "\tLetter: " + alphabet.charAt(cipherTextMat[j][i]));
                
                if (cipherCount == cipherText.length())
                    cipherCount = 0;
                else
                    cipherCount++;
            }
        }

        // Task 1: Find the determinant
        int det = (keywordMat[0][0] *  keywordMat[1][1]) - (keywordMat[0][1] *  keywordMat[1][0]);
        System.out.println("\nDeterminant: " + det);
        if (det < 0){
            det = (det + 26) % 26;
        }

        // Task 2: Solve for x
        int x = 0;
        while ((det*x) % 26 !=1){
            x++;
        }
        System.out.println("Value of X: " + x);

        // Task 3: Find adjucate of matrix and get mod 26 of each value
        int adjMat[][] = new int[cipherRows][cipherCols];

        int adjCount = keyword.length()-1;
        for (int i = 0; i < keyRows; i++){
            for (int j = 0; j < keyCols; j++){
                adjMat[i][j] = alphabet.indexOf(keyword.charAt(adjCount))%26;
                if (adjCount == 2 || adjCount == 1)
                    adjMat[i][j] = ((adjMat[i][j] * -1) + 26) % 26;

                if (adjCount == keyword.length())
                    adjCount = 0;
                else
                    adjCount--;
            }
        }

        // Task 4: Multiply values by x and get mod 26 of each value
        System.out.println("\n----Decryption Matrix----");
        for (int i = 0; i < keyRows; i++){
            for (int j = 0; j < keyCols; j++){
                adjMat[i][j] = (adjMat[i][j]*x)%26;
                
                System.out.println("Row Index: "+ i + "\tCol Index: " + j + "\tNumber: "+ adjMat[i][j]);

                if (adjCount == keyword.length())
                    adjCount = 0;
                else
                    adjCount--;
            }
        }

        // Decrypt Cipher Text (Decryption Matrix * Cipher Matrix)
        System.out.println("\n----Plain Text----");
        int sum = 0;
        for (int i = 0; i < cipherCols; i++){
            for (int j = 0; j < cipherRows; j++){
                for (int k = 0; k < keyRows;  k++){
                    sum = sum + (adjMat[j][k] * cipherTextMat[k][i]);
                }

                plainMat[j][i] = sum % 26;
                System.out.println("Row Index: "+ i + "\tCol Index: " + j + "\tNumber: "+ plainMat[j][i] + "\tLetter: " + alphabet.charAt(plainMat[j][i]));
                sum = 0;
            }
        }

        // Printing out Cipher text
        String plain = "";
        for (int i = 0; i < plainCols; i++){
            for (int j = 0; j < plainRows; j++){
                plain += alphabet.charAt(plainMat[j][i]);
            }
        }

        System.out.println("\nPlain Text: " + plain);
    }
}