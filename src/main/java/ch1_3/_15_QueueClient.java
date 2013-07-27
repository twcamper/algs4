package ch1_3;
import util.*;
import edu.princeton.cs.introcs.*;
import edu.princeton.cs.algs4.*;

public class _15_QueueClient
{

  private static String fromLast(int n)
  {
    _14_ArrayQueue<String> q = new _14_ArrayQueue<String>(1000);
    // _29_ListQueue<String> q = new _29_ListQueue<String>();
    // Queue<String> q = new Queue<String>();

    while (!StdIn.isEmpty())
      q.enqueue(StdIn.readString());

    if (n > q.size())
      throw new IllegalArgumentException("number of input strings less than arg <n>");

    while (!q.isEmpty()) {
      if (q.size() == n)
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
