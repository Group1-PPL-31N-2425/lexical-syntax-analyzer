public class Token {
  private String value;
  private String type;

  public Token(String value, String type) {
    this.value = value;
    this.type = type;
  }

  public String getValue() {
    return this.value;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String newType) {
    this.type = newType;
  }

  @Override
  public String toString() {
    return "[Lexeme]: " + this.value + "\n[Token]: " + this.type + "\n";
  }
}
