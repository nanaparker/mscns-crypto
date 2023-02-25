// Frequency Analysis Algorithm -> Finding the most occurring letter(s),
// assuming they are E and shifting everything backwards with the discovered key

import java.util.HashMap;

public class FrequencyAnalysis {

    // *--Most Occurring Letter Check--*
    public static String[] maxCheck(String word){

        HashMap<String, Integer> check = new HashMap<>();
        HashMap<String, Integer> max = new HashMap<>();

        for (int i = 0; i < word.length(); i++){
            if (check.containsKey(String.valueOf(word.charAt(i)))){
                check.replace(String.valueOf(word.charAt(i)),check.get(String.valueOf(word.charAt(i))) + 1);
            } else {
                check.put(String.valueOf(word.charAt(i)), 1);
            }
        }

        String[] letters = check.keySet().toArray(new String[0]);
        int index = 0;
        
        // Finding the letter with the highest value
        for (int i = 0; i < check.size(); i++){
            if(max.isEmpty()){
                index = i;
                max.put(letters[i], check.get(letters[i]));
            }
            else { 
                if (max.get(letters[index]) < check.get(letters[i])){
                    index = i;
                    max.clear();
                    max.put(letters[i], check.get(letters[i]));
                } else if (max.get(letters[index]) == check.get(letters[i])){
                    index = i;
                    max.put(letters[i], check.get(letters[i]));
                }
            }
        }

        letters = max.keySet().toArray(new String[0]);

        return letters;
    }


    // *--Key Detection--*
    public static int[] keyDetection(String[] map){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] index = new int [map.length];
        
        if (map.length == 1){
            index[0] = alphabet.indexOf(map[0]) - 4 ;
        } else {
            for (int i = 0; i < map.length; i++){
                index[i] = alphabet.indexOf(map[i]) - 4;
            }
        }

        return index;
    }


    // *--Decryption--*
    public static void decrypt(int[] index, String word){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        char[] charr = word.toCharArray();
        
        if (index.length == 1){
            String finalWord = "";

            for (char c : charr){
                int num = alphabet.indexOf(String.valueOf(c));
                int finalIndex = (num - index[0] + 26) % 26;
                finalWord += alphabet.charAt(finalIndex);
            }

            System.out.println("Initial Word: " + word + "\tFinal Word: "+ finalWord);
        } else {
            String[] finalWords = new String [index.length];
            String finalWord = "";

            for (int i = 0; i < index.length; i++){
                for (char c: charr){
                    int num = alphabet.indexOf(String.valueOf(c));
                    int finalIndex = (num - index[i] + 26) % 26;
                    finalWord += alphabet.charAt(finalIndex);
                }

                finalWords[i] = finalWord;
                finalWord = "";

                System.out.println("Initial Word: " + word + "\t\tIndex: " + index[i] + "\tFinal Word: " + finalWords[i]);
            }
        }
    }


    public static void freqAnalysis(String word){
        String[] freq_letters = maxCheck(word);
        int[] keys = keyDetection(freq_letters);
        decrypt(keys, word);
    }



    public static void main(String[] args) throws Exception {
        System.out.println("-----------------");
        freqAnalysis("aoyllaoyllzhylupul");
        System.out.println("-----------------");
        freqAnalysis("gragrafnergjragl");
    }

}
