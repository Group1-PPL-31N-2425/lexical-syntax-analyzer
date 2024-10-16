public class TokenType {
  private String regex;
  private String type;

  public TokenType(String regex, String type) {
    this.regex = regex;
    this.type = type;
  }

  public String getRegex() {
    return this.regex;
  }

  public String getType() {
    return this.type;
  }

}
