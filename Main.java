public class Main {
  public static void main(String[] args) {
    String input = """
      int integer = 4 * 3 / 2+2-32;
      """;
    Lexer lexer = new Lexer(input);
    lexer.tokenize();
    lexer.printTokens();
  }
}
