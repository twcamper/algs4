public class _15_Histogram {
  
  public static int[] histogram(int[] a, int M)
  {
    int[] h = new int[M];

    // count instances of 'i'
    for (int i = 0; i < h.length; i++) {
      for (int j = 0; j < a.length; j++)
        if (a[j] == i) h[i]++;
    }
    return h;
  }

  public static void main(String[] args)
  {
    if (args.length < 3)
      System.err.println("At least 3 integers required");
    else {
      int range = Integer.parseInt(args[0]);
      int[] values = new int[args.length - 1];
      for (int i = 1; i < values.length; i++)
        values[i-1] = Integer.parseInt(args[i]);

      int h[] = histogram(values, range);
      
      int sum = 0;
      for (int i = 0; i < h.length; i++) {
        StdOut.printf("%d\t", h[i]);
        sum += h[i];
      }
      StdOut.println();

      // java -ea
      assert (sum == values.length) : String.format("Histogram fails to sum correctly: expected %d got %d", values.length, sum);
    }
  }
}
