package util;
import java.util.*;
public class Expressions
{

  private static final String DELIMITERS = " )+-/*";

  public static Stack<String> reverseInput(String s)
  {
    StringTokenizer tokenizer = new StringTokenizer(s, DELIMITERS, true);
    Stack<String> input = new Stack<String>();

    String token = "";
    while (tokenizer.hasMoreTokens()) {
      if (!(token = tokenizer.nextToken()).equals(" "))
        input.push(token);
    }

    return input;
  }
  public static boolean isOperator(String s)
  {
    if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
      return true;
    return false;
  }
}
