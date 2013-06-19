import java.util.*;
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
      int[] intArgs = new int[args.length];
      for (int i = 0; i < intArgs.length; i++)
        intArgs[i] = Integer.parseInt(args[i]);

      // range 'to' is exclusive, thus we don't use len - 1
      int h[] = histogram(Arrays.copyOfRange(intArgs, 1, intArgs.length), intArgs[0]);
      
      int sumOfH = 0;
      for (int i = 0; i < h.length; i++) {
        StdOut.printf("%d\t", h[i]);
        sumOfH += h[i];
      }
      StdOut.println();

      // java -ea
      assert (sumOfH == (intArgs.length - 1)) : "Histogram fails to sum correctly: expected " + (intArgs.length - 1);
    }
  }
}
