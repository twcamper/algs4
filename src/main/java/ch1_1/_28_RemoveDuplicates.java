import java.util.*;

public class _28_RemoveDuplicates {

  public static int rank(int key, int[] a) {
    return rank(key, a, 0, a.length - 1);
  }

  public static int rank(int key, int[] a, int low, int high) {
    if (low > high) return -1;
    int mid = low + (high - low) / 2;
    if      (key < a[mid]) return rank(key, a, low, mid -1);
    else if (key > a[mid]) return rank(key, a, mid + 1, high);
    else                   return mid;
  }

  public static int[] unique(int[] a) {
    if (a.length == 0)
      return a;

    int size = 0;
    int[] squeezed = new int[a.length];
    squeezed[0] = a[0];
    for (int i = 1; i < a.length; i++)
      if (a[i - 1] != a[i])
        squeezed[size++] = a[i];

    return Arrays.copyOfRange(squeezed, 0, size);
  }

  public static void main(String[] args) {
    int[] input = new In(args[0]).readAllInts();

    Arrays.sort(input);
    int[] whitelist = unique(input);

    // read key; print if not in whitelist
    while (!StdIn.isEmpty()) {
      int key = StdIn.readInt();
      if (rank(key, whitelist) == -1)
        StdOut.println(key);
    }
  }
}
