import java.io.*;
import java.util.Random;
import java.util.Scanner;
public class words {
    public String[] loadWords() throws FileNotFoundException {
        File file=new File("C:\\Users\\rachi\\OneDrive\\Documents\\JavaProject\\words.txt");   
        Scanner sc = new Scanner(file); 
        String line = sc.nextLine();
        String[] wordList = line.split("\\s");
        sc.close();

    return wordList;
    }

    public String chooseWord() throws FileNotFoundException {
        String[] wordList = loadWords();
        Random r=new Random();
        int index = r.nextInt(wordList.length);
        String secretWord = wordList[index];
        secretWord = secretWord.toLowerCase();
        return secretWord;
    }
}