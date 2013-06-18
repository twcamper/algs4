public class _3_Equal {

  public static void main(String[] args)
  {
    if (args.length == 3) {
      int n1 = Integer.parseInt(args[0]);
      int n2 = Integer.parseInt(args[1]);
      int n3 = Integer.parseInt(args[2]);

      if (n1 == n2 && n1 == n3)
        StdOut.println("Equal");
      else
        StdOut.println("Not Equal");
    } else {
      System.err.println("Three integers are required");
    }
  }
}
