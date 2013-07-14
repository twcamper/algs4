public class _04_Parentheses
{
  private static boolean isBalanced(String expression)
  {
    Stack<Character> s = new Stack<Character>();
    char ch; 
    for (int i = 0; i < expression.length(); i++) {
      switch((ch = expression.charAt(i))) {
        case '[':
        case'{':
        case '(':
          s.push(ch);
          break;
        case ']':
          if (s.isEmpty() || s.pop() != '[') return false;
          break;
        case '}':
          if (s.isEmpty() || s.pop() != '{') return false;
          break;
        case ')':
          if (s.isEmpty() || s.pop() != '(') return false;
          break;
        }
      }
    return true;
  }

  public static void main(String[] args)
  {
    StdOut.println("\nBalanced? " + isBalanced(StdIn.readString()));
  }
}
