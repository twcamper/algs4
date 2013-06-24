public class _32_Histogram
{

  public static void main(String[] args)
  {
    if (args.length == 3) {
      int    N = Integer.parseInt(args[0]);
      double l = Double.parseDouble(args[1]);
      double r = Double.parseDouble(args[2]);

      int[]        counts = new int[N];
      double intervalSize = Math.abs(l - r)/(double)N;
      double       setMin = Math.min(l,r);

      while (!StdIn.isEmpty()) {
        double value = StdIn.readDouble();
        for (int i = 0; i < N; i++) {
          double intervalBottom = setMin + (i * intervalSize);
          double intervalTop    = setMin + ((i+1) * intervalSize);

          if (value >= intervalBottom && value <= intervalTop) {
            counts[i]++;
          }
        }
      }

      for (int i = 0; i < N; i++) {
        StdOut.printf("%d: ", i);
        for (int j = 0; j < counts[i]; j++)
          StdOut.printf("*");
        StdOut.println();
      }
    } else
      System.err.println("Usage: <int> <double> <double>");
  }
}
