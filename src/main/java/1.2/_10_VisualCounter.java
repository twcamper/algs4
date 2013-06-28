public class _10_VisualCounter
{
  private final String name;
  private int count = 0;
  private int changes = 0;

  public _10_VisualCounter(String id, int scale)
  {
    name = id;
    StdDraw.setPenRadius(.008);
    StdDraw.setXscale(0, scale);
    StdDraw.setYscale(0, scale);
  }

  private void plot()
  {
    Point2D p = new Point2D(changes, count);
    p.draw();
  }
  public void increment()
  {
    count++;
    changes++;
    plot();
  }
  public void decrement()
  {
    if (count > 0) {
      count--;
      changes++;
      plot();
    }
  }
  public int tally() { return count; }
  public String toString() { return name + ": changes = " + changes + ", tally = " +  count; }

  private static void usageError()
  {
     System.err.println("Usage: <number of changes to plot>");
     System.exit(1);
  }

  public static void main(String[] args)
  {
    if (args.length != 1)
      usageError();

    int N = Integer.parseInt(args[0]);

    _10_VisualCounter vc = new _10_VisualCounter("test client", N);

    for (int i = 0; i < N; i++) {
      int r = StdRandom.uniform(0, 65536);

      // better than even chance of incrementing
      if (r % 2 == 0 || r % 7 == 0)
        vc.increment();
      else
        vc.decrement();
    }
    StdOut.println("Final: " + vc);
  }
}
