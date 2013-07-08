import util.*;
import java.util.*;

public class _09_InsertLeftParens
{
  private static void invalidInput(String s, String expression)
  {
    throw new IllegalArgumentException(String.format("Invalid input: element %x\n%s", s, expression));
  }
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
  public static void main(String[] args)
  {
    if (args.length != 1)
      Usage.error("a quoted string with an arithmetic expression that's missing all the left parens");

    Stack<String> input        = reverseInput(args[0]);
    Stack<Character> parens    = new Stack<Character>();
    Stack<Integer> operands    = new Stack<Integer>();
    Stack<Character> operators = new Stack<Character>();
    String output = "";

    while (!input.isEmpty()) {
      String token = input.pop();
      try { // arg is an operand if parseInt likes it
        int n = Integer.parseInt(token);
        if (operands.isEmpty())
          operands.push(n);
        else
          if (operators.isEmpty())
            invalidInput(token, args[0]);
          else {
            output = operands.pop() + output;
            output = operators.peek() + output;
            output = n + output;
            while (!operators.isEmpty() && !parens.isEmpty()) {
              operators.pop();
              parens.pop();
              output = "(" + output;
            }
          }
      } catch (NumberFormatException e) {
        // not an int, so it's a paren or an operator
        char ch = token.charAt(0);
        switch(ch) {
          case ')':
            parens.push(ch);
            output = ch + output;
            break;
          case '+':
          case '-':
          case '*':
          case '/':
            operators.push(ch);
            output = ch + output;
            break;
          default:
            invalidInput(token, args[0]);
        }
      }
    }
    StdOut.println(output);
  }
}
