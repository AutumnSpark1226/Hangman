class Hangman {
   public static void main(String[] args) {
      boolean won = false;
      String[] words = { "bomb", "toast", "communication", "sail", "highway", "prestige", "fair", "shock", "grass", "despair", "faithful", "crossing", "well", "headline", "assembly", "picture" };
      String word = words[Random.randint(0, 4)];
      int guesses = 0;
      int wrongTries = 0;
      char[] guessStatus = new char[word.length()];
      for(int i = 0; i < word.length(); i++) {
         guessStatus[i] = '_';
      }
      String[] hangmen = { "", "________\n|      |", " |\n |\n |\n |\n |\n_L______\n|      |", " ____\n |\n |\n |\n |\n |\n |\n_L______\n|      |", " ____\n |/\n |\n |\n |\n |\n |\n_L______\n|      |", " ____\n |/  |\n |\n |\n |\n |\n |\n_L______\n|      |", " ____\n |/  |\n |   O\n |\n |\n |\n |\n_L______\n|      |", " ____\n |/  |\n |   O\n |   |\n |   |\n |\n |\n_L______\n|      |", " ____\n |/  |\n |   O\n |   |\n |   |\n |  /\n |\n_L______\n|      |", " ____\n |/  |\n |   O\n |   |\n |   |\n |  / \\\n |\n_L______\n|      |", " ____\n |/  |\n |   O\n |  \\|\n |   |\n |  / \\\n |\n_L______\n|      |", " ____\n |/  |\n |   O\n |  \\|/\n |   |\n |  / \\\n_L______\n|      |" };
      while(!won) {
         SystemTools.clearScreen();
         System.out.println(hangmen[wrongTries]);
         for(int i = 0; i < word.length(); i++) {
            System.out.print(guessStatus[i]);
            System.out.print(' ');
         }
         System.out.println();
         System.out.println();
         String input = (Input.readString("Guess: ")).toLowerCase();
         if(input.equalsIgnoreCase(word)) {
            won = true;
         }else if(stringContains(word.toLowerCase(), input)) {
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
         if(charToString(guessStatus).equals(word.toLowerCase())) {
            System.out.println("dfg");
            won = true;
         }else if(wrongTries >= hangmen.length - 1) {
            break;
         }
         guesses++;
      }
      if(won) {
         SystemTools.clearScreen();
         System.out.println("You won!");
         System.out.println(guesses + " guess(es)");
      }else{
         SystemTools.clearScreen();
         System.out.println(hangmen[wrongTries]);
         System.out.println("You lost!");
         System.out.println("The word was: " + word);
      }
   }

   static boolean stringContains(String word1, String word2) {
      return word1.indexOf(word2.toLowerCase()) != -1;
   }

   static int[] positionsOfChar(String word, char charToGetPosition) {
      char[] characters = stringToChar(word);
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
   
   static char[] stringToChar(String input) {
      char[] characters = new char[input.length()];
      for(int i = 0; i < input.length(); i++) {
         characters[i] = input.charAt(i);
      }
      return characters;
   }

   static String charToString(char[] input) {
      String output = "";
      for(int i = 0; i < input.length; i++) {
         output += input[i];
      }
      return output;
   }
}
