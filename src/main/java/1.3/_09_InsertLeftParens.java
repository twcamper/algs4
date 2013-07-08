import util.*;
import java.util.*;
import java.util.Stack;

public class _09_InsertLeftParens
{
  private static Stack<String> reverseInput(String s)
  {
    StringTokenizer tokenizer = new StringTokenizer(s, " )+-/*", true);
    Stack<String> input = new Stack<String>();

    String token = "";
    while (tokenizer.hasMoreTokens()) {
      if (!(token = tokenizer.nextToken()).equals(" "))
        input.push(token);
    }

    return input;
  }
  private static boolean isOperator(String s)
  {
    if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
      return true;
    return false;
  }
  private static String insertLeftParens(String incompleteExpression)
  {
    Stack<String> input     = reverseInput(incompleteExpression);
    Stack<String> parens    = new Stack<String>();
    Stack<String> operators = new Stack<String>();
    String output = "";
    boolean rightExpressionWritten = false;

    while (!input.empty()) {
      String token = input.pop();
      output = token + output;
      // StdOut.printf("op: _%s_  paren: `%s`, %s\t\t", operators, parens, rightExpressionWritten);
      // StdOut.printf("%s: %s\n", token, output);

      if (token.equals(")")) {
        parens.push(token);
        rightExpressionWritten = false;
      } else if (isOperator(token)) {
        operators.push(token);
      } else {  // atomic expression (integer)
        if (rightExpressionWritten) {
          rightExpressionWritten = false;
          while (!operators.empty() && !parens.empty()) {
            operators.pop();
            parens.pop();
            output = "(" + output;
            rightExpressionWritten = true;
          }
        } else
          rightExpressionWritten = true;
      }
    }
    return output;
  }

  public static void main(String[] args)
  {
    if (args.length != 1)
      Usage.error("a quoted string with an arithmetic expression that's missing all the left parens");

    StdOut.println(insertLeftParens(args[0]));
  }
}
