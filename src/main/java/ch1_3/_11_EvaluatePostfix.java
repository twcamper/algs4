import util.*;
public class _11_EvaluatePostfix
{
  private static void add(Stack<Integer> s)
  {
    s.push(s.pop() + s.pop());
  }
  private static void subtract(Stack<Integer> s)
  {
    int subtrahend = s.pop();
    int minuend    = s.pop();
    s.push(minuend - subtrahend);
  }
  private static void multiply(Stack<Integer> s)
  {
    s.push(s.pop() * s.pop());
  }
  private static void divide(Stack<Integer> s)
  {
    int divisor  = s.pop();
    int dividend = s.pop();
    s.push(dividend / divisor);
  }
  public static void main(String[] args)
  {
    Stack<Integer> values = new Stack<Integer>();

    while (!StdIn.isEmpty()) {
      String token = StdIn.readString();
      try {
        values.push(Integer.parseInt(token));
      } catch (NumberFormatException e) {
        switch (token) {
          case "+":
            add(values);
            break;
          case "*":
            multiply(values);
            break;
          case "-":
            subtract(values);
            break;
          case "/":
            divide(values);
            break;
          default:
            throw new IllegalArgumentException(String.format("Bad operator '%s' in expression\n", token));
        }
      }
    }

    if (values.size() != 1)
      throw new RuntimeException(String.format("Invalid expression: stack should have exactly one value but has %s\n", values));
    StdOut.println(values.pop());
  }
}
