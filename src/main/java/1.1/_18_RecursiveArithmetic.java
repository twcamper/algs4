public class _18_RecursiveArithmetic
{
  public static int multiply(int a, int b)
  {
    if (b == 0)     return 0;
    if (b % 2 == 0) return multiply(a + a, b/2);
    return multiply(a + a, b/2) + a;
  }

  public static int raise(int a, int b)
  {
    if (b == 0)     return 1;
    if (b % 2 == 0) return raise(a * a, b/2);
    return raise(a * a, b/2) * a;
  }

  public static void main(String[] args)
  {
    if (args.length == 2) {
      int a = Integer.parseInt(args[0]);
      int b = Integer.parseInt(args[1]);
      StdOut.printf("%d * %d = %d\n", a, b, multiply(a, b));
      StdOut.printf("%d ** %d = %d\n", a, b, raise(a, b));
    } else
      System.err.println("Expect 2 integers.");

  }
}
