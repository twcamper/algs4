import util.*;

public class _37_Josephus
{

  private static int nextIndex(int size, int current)
  {
    return (current < size - 1) ? current + 1 : 0;
  }
  public static Queue<Integer> buildQueue(int interval, int N)
  {
    Integer[] people = new Integer[N];
    for (int i = 0; i < people.length; i++)
      people[i] = i;
    Queue<Integer> q = new Queue<>();
    int i = 0;
    int intervalCounter = 0;
    while (q.size() < N) {
      if (intervalCounter == interval) {
        // if (people[i] == null)
          // i = nextIndex(people.length, i);
        q.enqueue(people[i]);
        people[i] = null;
        intervalCounter = 0;
      } else {
        intervalCounter++;
        i = nextIndex(people.length, i);
      }

    }
    /*
     * init array of size 'people' to ints 0 to N-1
     * interval counter = 0
     * array index = 0
     * while queue size < 'people'
     *   if interval counter < interval
     *     increment interval counter
     *     if array index < array size
     *       increment array index
     *     else
     *       reset array index to 0
     *   else
     *     if array[index] == null
     *       increment array index
     *     enqueue a copy of the val array[index]
     *     set array[index] = null
     *     reset interval counter to 0
     *
     */
    return q;
  }
  public static void main(String[] args)
  {

    if (args.length != 2)
      Usage.error("<M> <N>, where M is the space between people, and N is the number of people");

    Queue<Integer> q = buildQueue(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

    System.out.println(q);
    for (Integer i : q)
      System.out.println(i);

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
     * 2 5 3
     */

  }
}
