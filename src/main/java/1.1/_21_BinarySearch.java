import java.util.Arrays;

public class _21_BinarySearch {

  private static int depth = 0;
  public static void trace(int l, int h)
  {
    depth++;
    for (int i = 0; i < depth; i++)
      StdOut.printf(" ");

    StdOut.printf("%d..%d\n", l, h);
  }
  public static int rank(int key, int[] a) {
    depth = 0;
    return rank(key, a, 0, a.length - 1);
  }

  public static int rank(int key, int[] a, int low, int high) {
    trace(low, high);
    if (low > high) return -1;
    int mid = low + (high - low) / 2;
    if      (key < a[mid]) return rank(key, a, low, mid -1);
    else if (key > a[mid]) return rank(key, a, mid + 1, high);
    else                   return mid;
  }

  public static void main(String[] args) {
    int[] whitelist = new In(args[0]).readAllInts();

    Arrays.sort(whitelist);

    // read key; print if not in whitelist
    while (!StdIn.isEmpty()) {
      int key = StdIn.readInt();
      if (rank(key, whitelist) == -1)
        StdOut.println(key);
    }
  }
}
