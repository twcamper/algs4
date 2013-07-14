import util.*;
import java.util.*;
import java.util.Stack;
public class _10_InfixToPostfix
{

  private static String toPostfix(String infixExpression)
  {
    ArrayList<String> infixTokens = Expressions.tokenize(infixExpression);
    Stack<String> operators = new Stack<String>();
    String postfix = "";
    String operator = "";

    for (String token : infixTokens) {
      if (Expressions.isOperator(token) || Expressions.isParen(token)) {
        // write out operators if they have precedence over the next one to the right
        while (!operators.empty() && Expressions.hasPrecedence(operators.peek(), token))
          postfix += operators.pop() + " ";

        // have we finished with a sub-expression?
        if (!operators.empty() && operators.peek().equals("("))
          operators.pop();

        // save off operators to be printed later
        if (!token.equals(")"))
          operators.push(token);
      } else
        postfix += token + " "; // operand

    }

    // write out any remaining
    while (!operators.empty())
      postfix += operators.pop();

    return postfix;
  }
  public static void main(String[] args)
  {
    if (args.length != 1)
      Usage.error("a quoted arithmetic or algebraic expression");

    StdOut.println(toPostfix(args[0]));
  }

}
