import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
  private ArrayList<Token> tokens;
  private String input;
  private int cursor;
  private TokenType[] tokenTypes = {
      new TokenType("^\\s+", "WHITESPACE"),
      new TokenType("^[a-zA-Z_][a-zA-Z0-9_]*", "WORD"),
      new TokenType("^\\d+\\.\\d+", "REAL"),
      new TokenType("^\\d+", "INTEGER"),
      new TokenType("^\"[^\"]*\"", "STRING"),
      new TokenType("^\'[^\']*\'", "STRING"),
      new TokenType("^[+-/*=<>!]", "OPERATOR"),
      new TokenType("^[.,;(){}\\[\\]]", "SYMBOL"),
  };
  private final HashSet<String> keywordSet =
      new HashSet<>(Arrays.asList("if", "else", "return", "for", "while"));
  private final HashSet<String> dataTypeSet =
      new HashSet<>(Arrays.asList("int", "float", "char", "string", "bool"));

  public Lexer(String input) {
    this.tokens = new ArrayList<>();
    this.input = input;
    this.cursor = 0;
  }

  public void tokenize() {
    while (this.cursor < this.input.length()) {
      Token token = this.nextToken();

      if (token != null) {
        if (token.getType().equals("WHITESPACE"))
          continue;
        this.tokens.add(token);
      } else {
        throw new RuntimeException("[ERROR]: null lexeme found at character " +
                                   (this.cursor + 1));
      }
    }
  }

  public Token nextToken() {
    if (this.cursor >= this.input.length()) {
      return null;
    }

    for (int i = 0; i < tokenTypes.length; i++) {
      Pattern pattern = Pattern.compile(tokenTypes[i].getRegex());
      Matcher matcher = pattern.matcher(this.input.substring(this.cursor));

      if (matcher.find()) {
        String lexeme = matcher.group();
        this.cursor += lexeme.length();
        Token token = new Token(lexeme, tokenTypes[i].getType());
        // Determines whether the token is an DATA_TYPE or KEYWORD or IDENTIFIER
        if (token.getType().equals("WORD")) {
          if (keywordSet.contains(lexeme))
            token.setType("KEYWORD");
          else if (dataTypeSet.contains(lexeme))
            token.setType("DATA_TYPE");
          else
            token.setType("IDENTIFIER");
        }
        return token;
      }
    }

    return null;
  }

  public void printTokens() {
    System.out.println("[INPUT]\n" + input + "\n[OUTPUT]");
    for (Token token : this.tokens) {
      System.out.println(token);
    }
  }
}
