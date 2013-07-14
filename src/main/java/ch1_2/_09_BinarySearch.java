import java.util.Arrays;

public class _09_BinarySearch {

  private static Counter keys = new Counter("keys examined");
  private static int rank(int key, int[] a) {
    int low = 0;
    int high = a.length - 1;
    keys.increment();

    while (low <= high) {
      int mid = low + (high - low) / 2;
      if      (key < a[mid]) high  = mid - 1;
      else if (key > a[mid]) low   = mid + 1;
      else                   return mid;
    }
    return -1;
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
    StdOut.println(keys);
  }
}
