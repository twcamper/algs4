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

    for (String token : infixTokens) {
      if (Expressions.isOperator(token) || Expressions.isParen(token)) {
        ;
      } else
        postfix += token;
    }
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
