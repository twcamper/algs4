public class _07_Reverse
{
  private static String reverse(String s)
  {
    int N = s.length();
    if (N <= 1) return s;
    String a = s.substring(0, N/2);
    String b = s.substring(N/2, N);
    return reverse(b) + reverse(a);

  }
  public static void main(String[] args)
  {
    String s = "";
    for (int i = 0; i < args.length; i++)
      s = s.concat(String.format("%s ", args[i]));
    StdOut.println(reverse(s));
  }
}
