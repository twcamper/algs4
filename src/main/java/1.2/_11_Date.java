public class _11_Date
{
  public static void main(String[] args)
  {
    if (args.length != 3)
      Usage.error("mm dd yyyy");

    int mm   = Integer.parseInt(args[0]);
    int dd   = Integer.parseInt(args[1]);
    int yyyy = Integer.parseInt(args[2]);
    SmartDate d = new SmartDate(mm, dd, yyyy);

    StdOut.println(d);
  }
}
