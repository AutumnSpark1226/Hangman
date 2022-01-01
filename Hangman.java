import java.util.Random;
import java.io.*;

class Hangman {
   public static void main(String[] args) {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      Random random = new Random();
      boolean won = false;
      String[] words = { "bomb", "toast", "communication", "sail", "highway", "prestige", "fair", "shock", "grass", "despair", "faithful", "crossing", "well", "headline", "assembly", "picture" };
      String word = words[random.nextInt(words.length - 1)];
      int guesses = 0;
      int wrongTries = 0;
      char[] guessStatus = new char[word.length()];
      for(int i = 0; i < word.length(); i++) {
         guessStatus[i] = '_';
      }
      String[] hangmen = { "", "________\n|      |", " |\n |\n |\n |\n |\n_L______\n|      |", " ____\n |\n |\n |\n |\n |\n |\n_L______\n|      |", " ____\n |/\n |\n |\n |\n |\n |\n_L______\n|      |", " ____\n |/  |\n |\n |\n |\n |\n |\n_L______\n|      |", " ____\n |/  |\n |   O\n |\n |\n |\n |\n_L______\n|      |", " ____\n |/  |\n |   O\n |   |\n |   |\n |\n |\n_L______\n|      |", " ____\n |/  |\n |   O\n |   |\n |   |\n |  /\n |\n_L______\n|      |", " ____\n |/  |\n |   O\n |   |\n |   |\n |  / \\\n |\n_L______\n|      |", " ____\n |/  |\n |   O\n |  \\|\n |   |\n |  / \\\n |\n_L______\n|      |", " ____\n |/  |\n |   O\n |  \\|/\n |   |\n |  / \\\n_L______\n|      |" };
      while(!won) {
         clearScreen();
         System.out.println(hangmen[wrongTries]);
         for(int i = 0; i < word.length(); i++) {
            System.out.print(guessStatus[i]);
            System.out.print(' ');
         }
         System.out.println();
         System.out.println();
         System.out.print("Guess: ");
         String input = "";
         try{
           input = br.readLine().toLowerCase();
        } catch(IOException e){
           e.printStackTrace();
           System.exit(1);
        }
         if(input.equalsIgnoreCase(word)) {
            won = true;
         }else if(word.toLowerCase().contains(input)) {
            char[] characters = new char[input.length()];
            for(int i = 0; i < input.length(); i++) {
               characters[i] = input.charAt(i);
            }
            for(int i = 0; i < input.length(); i++) {
               int[] newSolvedCharPositions = positionsOfChar(word.toLowerCase(), characters[i]);
               for(int ix = 0; ix < newSolvedCharPositions.length; ix++) {
                  int pos = newSolvedCharPositions[ix];
                  guessStatus[pos] = word.charAt(pos);
               }
            }
         } else{
            wrongTries++;
         }
         if((new String(guessStatus)).equals(word.toLowerCase())) {
            System.out.println("dfg");
            won = true;
         }else if(wrongTries >= hangmen.length - 1) {
            break;
         }
         guesses++;
      }
      if(won) {
         clearScreen();
         System.out.println("You won!");
         System.out.println(guesses + " guess(es)");
      }else{
         clearScreen();
         System.out.println(hangmen[wrongTries]);
         System.out.println("You lost!");
         System.out.println("The word was: " + word);
      }
   }

   static int[] positionsOfChar(String word, char charToGetPosition) {
      char[] characters = word.toCharArray();
      int[] outputUnformatted = new int[word.length()];
      int pos = 0;
      for(int i = 0; i < word.length(); i++) {
         if(charToGetPosition == characters[i]) {
            outputUnformatted[pos] = i;
            pos++;
         }
      }
      int[] output = new int[pos];
      for(int i = 0; i < pos; i++) {
         output[i] = outputUnformatted[i];
      }
      return output;
   }

   public static void clearScreen(){
     System.out.print("\033[H\033[2J");
     System.out.flush();
   }
}
