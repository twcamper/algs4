public class _1_1_9_ToBinaryString {

  private static String toBinaryString(Integer N)
  {
    String s = "";
    for (int n = N; n > 0; n /= 2)
      s = (n % 2) + s;
    return s;
  }
  public static void main(String[] args)
  {
    if (args.length > 0) {
      for (int i = 0; i < args.length; i++)
        StdOut.println(toBinaryString(Integer.parseInt(args[i])));
    } else
      System.err.println("I need at least 1 integer, please.");
  }
}
