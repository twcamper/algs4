public class _02_IntersectingIntervals
{
  private static void usageError()
  {
     System.err.println("Usage: <number of intervals>");
     System.exit(1);
  }
  private static Interval1D[]  buildIntervals(int N)
  {
    Interval1D[] intervals = new Interval1D[N];
    Double firstEnd = null;
    int i = 0;
    // read stream of double values in series, not paired
    while (!StdIn.isEmpty() && i < N) {
      if (firstEnd == null) {
        firstEnd = StdIn.readDouble();
      } else {
        Double otherEnd = StdIn.readDouble();;
        intervals[i++] = new Interval1D(Math.min(firstEnd, otherEnd), Math.max(firstEnd, otherEnd));
        firstEnd = null;
      }
    }
    return intervals;
  }
  private static void printIntersections(Interval1D[] intervals)
  {
    for (int a = 0; a < intervals.length - 1; a++)
      for (int b = a + 1; (b < intervals.length) && (intervals[b] != null); b++) {
        if (intervals[a].intersects(intervals[b])) {
          StdOut.println(intervals[a] + " & " + intervals[b]);
        } else
          StdOut.println(intervals[a] + " !& " + intervals[b]);
      }
  }
  public static void main(String[] args)
  {
    if (args.length != 1)
      usageError();

    printIntersections(buildIntervals(Integer.parseInt(args[0])));
  }
}
