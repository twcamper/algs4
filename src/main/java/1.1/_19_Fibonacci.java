import java.math.BigInteger;

public class _19_Fibonacci
{
  public static long F(int N)
  {
    if (N < 2) return N;
    return F(N - 1) + F(N - 2);
  }
  public static void recursion(int size)
  {
    for (int n = 0; n < size; n++)
      StdOut.printf("%d %d\n", n, F(n));
    /*
       56 225851433717
       57 365435296162
       58 591286729879
       59 956722026041
       ^C
       real286m46.486s
       user286m40.079s
       sys0m8.548s

       4.5 hours
       */
  }

  public static void array(int size)
  {
    BigInteger series[] = new BigInteger[size];
    series[0] = BigInteger.ZERO;
    series[1] = BigInteger.ONE;

    for (int i = 2; i < size; i++)
      series[i] = series[i - 2].add(series[i - 1]);

    for (int i = 0; i < size; i++)
      StdOut.printf("%d %d\n", i, series[i]);

  }

  public static double three_var(int n)
  {
    if (n < 2) return (double)n;

    double low = 0d;
    double high = 1d;
    double temp;

    for (int i = 2; i <= n; i++) {
      temp = low;
      low = high;
      high = temp + low;
    }
    return high;
  }

  public static void iterative(int size)
  {
    for (int i = 0; i < size; i++)
      // output after fibonacci 78 is wrong: not sure if it's
      // the double overflowing or the formatted IO
      StdOut.printf("%d %.0f\n", i, three_var(i));
  }
  public static void main(String[] args)
  {
    if (args.length == 2 && args[1].length() == 1) {
      int size = Integer.parseInt(args[0]);
      switch(args[1].charAt(0)) {
        case 'a':
          array(size);
          return;
        case 'b':
          iterative(size);
          return;
        case 'c':
          recursion(size);;
          return;
      }
    }
    System.err.println("A size and then a, b, or c please");
  }
}
