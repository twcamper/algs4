public class _03_Rectangles
{
  private static void usage()
  {
    System.err.println("Usage: <number of rectangles> <min left> <min right>");
  }
  private static void usageError()
  {
    usage();
    System.exit(1);
  }
  private static Interval2D[] generateRectangles(int N, double min, double max)
  {
    Interval2D[] r = new Interval2D[N];
    for (int i = 0; i < N; i++) {
      double xOne   = StdRandom.uniform(min, max);
      double xOther = StdRandom.uniform(min, max);
      double yOne   = StdRandom.uniform(min, max);
      double yOther = StdRandom.uniform(min, max);

      r[i] = new Interval2D(new Interval1D(Math.min(xOne,xOther), Math.max(xOne,xOther)),
          new Interval1D(Math.min(yOne,yOther), Math.max(yOne,yOther)));

    }

    return r;
  }
  private static void drawRectangles(Interval2D[] rectangles)
  {
    for (int i = 0; i < rectangles.length; i++)
      rectangles[i].draw();
  }
  private static void printIntersectionStats(Interval2D[] rectangles)
  {
    int intersections = 0;
    for (int a = 0; a < rectangles.length - 1; a++)
      for (int b = a + 1; b < rectangles.length; b++)
        if (rectangles[a].intersects(rectangles[b])) {
          intersections++;
          // Interval2D does not expose its sides (x and y), so the only
          // way to find subsets would be to save off the composing
          // Interval1D objects in a separate array when generating the
          // rectangles.  Eff that.
        }

    StdOut.println("Intersections: " + intersections);
  }
  public static void main(String[] args)
  {
    if (args.length != 3)
      usageError();

    int N = Integer.parseInt(args[0]);
    double min = Double.parseDouble(args[1]);
    double max = Double.parseDouble(args[2]);

    if (min > max) {
      usage();
      throw new IllegalArgumentException("arg 1 should be less than arg 2");
    }

    Interval2D[] rectangles = generateRectangles(N, min, max);
    drawRectangles(rectangles);
    printIntersectionStats(rectangles);

  }
}
