import util.*;
import util.Date;
import util.Transaction;
public class _13_Transaction
{

  public static void main(String[] args)
  {
    if (args.length != 5)
      Usage.error("<name> <mm> <dd> <yyyy> <amount>");

    Date   d = new Date(Integer.parseInt(args[1]),
                                  Integer.parseInt(args[2]),
                                  Integer.parseInt(args[3]));
    Transaction t = new Transaction(args[0], d, Double.parseDouble(args[4]));

    StdOut.println(t);
  }
}
