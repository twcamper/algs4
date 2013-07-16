import util.*;
import ch1_3.*;
public class _15_QueueClient
{

  private static String fromLast(int n)
  {
    _14_ArrayQueue<String> q = new _14_ArrayQueue<String>(50);

    while (!StdIn.isEmpty())
      q.enqueue(StdIn.readString());

    if (n > q.depth())
      throw new IllegalArgumentException("number of input strings less than arg <n>");

    while (!q.isEmpty()) {
      if (q.depth() == n)
        return q.dequeue();
      q.dequeue();
    }

    return "";
  }

  public static void main(String[] args)
  {
    if (args.length != 1)
      Usage.error("<n> to indicate which nth string from the last on stdin");

    StdOut.println(fromLast(Integer.parseInt(args[0])));
  }
}
