import java.io.FileNotFoundException;
import java.util.*;
class hangman
{
    public static void main(String args[]) throws FileNotFoundException
    {
       Hangmanp();
    }
    public static void Hangmanp() throws FileNotFoundException 
    {
        words m = new words();
        String word = m.chooseWord();
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome to the game,Hangman!");
        ArrayList <Character> list=new ArrayList<>();
        ArrayList <Character> list2=new ArrayList<>();
        HashSet <Character> set=new HashSet<>();
        for(int i=0;i<word.length();i++)
        {
            set.add(word.charAt(i));
        }
        int length=set.size();
        System.out.println("I am thinking of a word that is " + word.length() + " letters long. \n");
        int lives=8,q=0,hint=1;
        while(lives>0)
        {
            System.out.println("No of lives left:"+lives);
            String available=get_available_letters(list2);
            System.out.println("Available Letters: " + available);
            System.out.print("Guess any letter:"+get_guessed_word(word,list));
            char x= sc.next().charAt(0);
            System.out.println("you have chosen the letter:"+x);
            System.out.println();
            if(valid(x))
            {
                if(is_word_guessed(word,x))
                {
                    q++;
                    list.add(x);
                    list2.add(x);
                    System.out.println("Congrats! this letter is present in the word");
                    if(q==length)
                    {
                        System.out.println("Congratulations You Won");
                        break;
                    }
                }
                else if(x=='?' && hint==1)
                {
                    q++;
                    hint--;
                    char d=giveHint(word,list);
                    System.out.println("Hint is:"+d);
                    list.add(d);
                    list2.add(d);
                    if(q==length)
                    {
                        System.out.println("You have guessed the word");
                        System.out.println("Congratulations You Won");
                        break;
                    }
                }
                else
                {
                    if(x=='?')
                    {
                        System.out.println("OOPS! you have already used hint");
                    }
                    else
                    {
                        lives--;
                        int l=8-lives;
                        list2.add(x);
                        String img=image(l);
                        System.out.println("OOPS! you have chosen the wrong letter");
                        System.out.println("Your remaining lives are: " + lives);
                        System.out.println("Type '?' for hint!");
                        System.out.print("Please Note that you can only use the hint once");
                        System.out.println(img);
                        System.out.println();
                    }
                }
            }
            else
            {
                System.out.println("You have chosen invalid letter");
            }
        }
        if(lives<1)
        {
            System.out.println("You lose! Better luck next time");
            System.out.println("The word is:"+word);
        }
        sc.close();
    }
    public static String image(int index)
    {
        String images[] = {"","\n+---+\n|   |\n|\n|\n|\n|\n=========", "\n\n+---+\n|   |\n|   0\n|\n|\n|\n=========",
            "\n\n+---+\n|   |\n|   0\n|  /\n|\n|\n=========", "\n\n+---+\n|   |\n|   0\n|  / \\ \n|\n|\n=========", "\n\n    +---+\n    |   |\n    0   |\n   /|\\  |\n        |\n        |\n        =========", "\n\n    +---+\n    |   |\n    0   |\n   /|\\  |\n    |   |\n        |\n        =========", "\n\n    +---+\n    |   |\n    0   |\n   /|\\  |\n    |   |\n   /    |\n        =========", "\n\n    +---+\n    |   |\n    0   |\n   /|\\  |\n    |   |\n   / \\  |\n        =========", "\n\n    "};
        String s1=images[index];
        return s1;
    }
    public static boolean valid(char letter)
    {
        if(letter>='a' && letter<='z' || letter=='?')
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static String get_guessed_word(String word ,List<Character>letter_guessed)
    {
        /* secret_word: word guess by the user
        letters_guessed: list hold all the word guess by the user
        returns: 
        return string which contain all the right guessed characters
        Example :- 
        if secret_word -> "kindness" and letters_guessed = [k, n, s]
        return "k_n_n_ss"
        */
        String s1="";
        for(int i=0;i<word.length();i++)
        {
            if(letter_guessed.contains(word.charAt(i)))
            {
                s1=s1+""+word.charAt(i);
            }
            else
            {
                s1=s1+""+'_';
            }
        }
        return s1;
    }
    public static boolean is_word_guessed(String word,char c)
    {
        for(int i=0;i<word.length();i++)
        {
            if(word.charAt(i)==c)
            {
                return true;
            }
        }
        return false;
    }
    public static String get_available_letters(List<Character> letters_guessed)
    {
         /*
        letters_guessed: list contains all guessed characters
        returns: 
        it return string which contains all characters except guessed letters
        Example :-
        letters_guessed = ['e', 'a'] then    
        return sting is -> `bcdfghijklmnopqrstuvwxyz`
        */
        String alpha="abcdefghijklmnopqrstuvwxyz";
        for(int i=0;i<alpha.length();i++)
        {
            if(letters_guessed.contains(alpha.charAt(i)))
            {
                alpha=alpha.replace(String.valueOf(alpha.charAt(i)),"");
            }
        }
        return alpha;
    }
    public static char giveHint(String word, List<Character> letters_guessed)
    {
        Random random=new Random();
        String s1=word;
        for(int i=0;i<word.length();i++)
        {
            if(letters_guessed.contains(word.charAt(i)))
            {
                s1=s1.replace(String.valueOf(word.charAt(i)),"");
            }
        }
        char a[]=s1.toCharArray();
        char c=a[random.nextInt(a.length)];
        return c;
    }
}