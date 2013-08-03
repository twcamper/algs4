import util.*;
/*
   1.3.37 Josephus problem. In the Josephus problem from antiquity, N people are in dire
   straits and agree to the following strategy to reduce the population. They arrange themselves
   in a circle (at positions numbered from 0 to N-1) and proceed around the circle,
   eliminating every Mth person until only one person is left. Legend has it that Josephus
   figured out where to sit to avoid being eliminated. Write a Queue client Josephus that
   takes M and N from the command line and prints out the order in which people are
   eliminated (and thus would show Josephus where to sit in the circle).
*/

public class _37_Josephus
{
  private static int nextIndex(int size, int current)
  { return (current < size - 1) ? current + 1 : 0; }

  public static Queue<Integer> buildQueue(int ordinal, int N)
  {
    // circle of elements (people?)
    Integer[] circle = new Integer[N];
    for (int i = 0; i < circle.length; i++)
      circle[i] = i;

    // queue that holds the order of elimination:
    // the last one survives!
    Queue<Integer> q = new Queue<>();
    int            i = 0;
    int      skipped = 0;
    int       toSkip = ordinal - 1;

    while (q.size() < N) {
      if (skipped < toSkip)
        skipped++;
      else {
        // when N is an even multiple of ordinal,
        // we'll hit the position of a previously
        // eliminated element.
        if (circle[i] == null)
          i = nextIndex(circle.length, i);

        q.enqueue(circle[i]);

        // indicate the element has been eliminated
        circle[i] = null;

        // reset to find next element to eliminate
        skipped = 0;
      }
      i = nextIndex(circle.length, i);
    }
    return q;
  }
  public static void main(String[] args)
  {

    if (args.length != 2)
      Usage.error("<M> <N>, where M is the space between people, and N is the number of people");

    Queue<Integer> q = buildQueue(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

    System.out.println(q);

    /* 2 7
     * 1 3 5 0 2 4 6
     *
     * 3 7
     * 2 5 1 4 0 3 6
     *
     * 2 9
     * 1 3 5 7 0 2 4 6 8
     *
     * 3 9
     * 2 5 8 3 6 0 4 7 1
     *
     * 4 9
     * 3 7 2 6 1 5 0 4 8
     *
     * 4 7
     * 3 0 4 1 5 2 6
     *
     * 3 6
     * 2 5 3 0 4 1
     *
     * 3 3
     * 2 0 1
     *
     * 6 6
     * 5 0 1 2 3 4
     *
     * 1 6
     * 0 1 2 3 4 5
     */

  }
}
