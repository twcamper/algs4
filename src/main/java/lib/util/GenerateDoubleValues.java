public class GenerateDoubleValues
{

  public static void main(String[] args)
  {
    if (args.length == 1) {
      int count = Integer.parseInt(args[0]);

      StdRandom.setSeed(System.currentTimeMillis());
      for (int i = 0; i < count; i++)
        if (i % 3 == 0)
          StdOut.println(StdRandom.uniform(0.0, 100.0));
        else
          StdOut.println(StdRandom.uniform(-99.0, 65536.5));
    } else
      System.err.println("I need a count of double values to produce");
  }
}
