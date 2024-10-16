public class Main {
  public static void main(String[] args) {
    // Test input
    String input = """
      // This is a comment
      
      /*
       * This is a 
       * multiline
       * comment
       */ 

      /*
         A different multiline comment
      */

      int integer = 4 * 3 / 2+2.4-32;
      string s = "hello, world!";
      float f = 1000.23;
      char c = 'c';
      bool b = true;
      """;
    Lexer lexer = new Lexer(input);
    lexer.tokenize();
    lexer.printTokens();
  }
}
