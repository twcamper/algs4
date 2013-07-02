public class _04_Parentheses
{
  private static boolean isBalanced()
  {
    Stack<Character> s = new Stack<Character>();
    char ch; 
    while (!StdIn.isEmpty()) {
      switch((ch = StdIn.readChar())) {
        case '[':
        case'{':
        case '(':
          s.push(ch);
          break;
        case ']':
          if (s.pop() != '[') return false;
          break;
        case '}':
          if (s.pop() != '{') return false;
          break;
        case ')':
          if (s.pop() != '(') return false;
          break;
        }
      }
    return true;
  }

  public static void main(String[] args)
  {
    StdOut.println("\nBalanced? " + isBalanced());
  }
}
