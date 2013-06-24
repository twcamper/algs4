public class _32_Histogram
{
  private static int[] buildHistogram(int N, double l, double r)
  {
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

    return counts;
  }

  private static int maxCount(int[] data)
  {
    int max = 0;
    for (int i = 0; i < data.length; i++)
      if (data[i] > max)
        max = data[i];

    return max;
  }
  private static void displayHistogram(int[] data)
  {
    int N = data.length;
    StdDraw.setCanvasSize(1024, 800);
    StdDraw.setPenColor(StdDraw.BOOK_RED);
    StdDraw.setYscale(0, (double)maxCount(data));

    for (int i = 0; i < N; i++) {
      double x  = 1.0 * i/N;
      double y  = data[i]/2.0;
      double rw = 0.5/N;       // half-width
      double rh = y;           // half-height
      StdDraw.filledRectangle(x, y, rw, rh);
    }
  }
  public static void main(String[] args)
  {
    if (args.length == 3) {
      int[] histogramData = buildHistogram(Integer.parseInt(args[0]),
                                           Double.parseDouble(args[1]),
                                           Double.parseDouble(args[2]));

      displayHistogram(histogramData);
    } else
      System.err.println("Usage: <int> <double> <double>");
  }
}
