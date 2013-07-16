public class LinkedListExercises<Item>
{
  private Node first;

  public LinkedListExercises(Item item)
  {
    first = new Node();
    first.item = item;
  }
  private class Node
  {
    Item item;
    Node next;
  }
  private void insert(Item i)
  {
    Node n = new Node();
    n.item = i;
    n.next = first;
    first = n;
  }
  private void deleteLast()
  {
    Node n = first;
    while (n.next.next != null)
      n = n.next;
    n.next = null;
  }
  private void deleteIndex(int target)
  {
    int i = 0;
    Node current = first;
    Node previous = null;
    while (current != null && i != target) {
      previous = current;
      current = current.next;
      i++;
    }

    if (current == null)
      return;
    if (previous == null)
      first = first.next;
    else
      previous.next = current.next;
  }

  private boolean find(Item i)
  {
    for (Node n = first; n != null; n = n.next)
      if (n.item == i)
        return true;
    return false;
  }
  private void print()
  {
    for (Node n = first; n != null; n = n.next)
      StdOut.printf("%s\t", n.item);
    StdOut.println();
  }

  private static void populate(LinkedListExercises<Integer> list, int size)
  {
    for (int i = 0; i < size; i++)
      list.insert(StdRandom.uniform(1024));
  }

  private static void _19_deleteLast()
  {
    LinkedListExercises<Integer> demo = new LinkedListExercises<Integer>(StdRandom.uniform(512));
    populate(demo, 5);

    StdOut.println("Ex. 1.3.19 - delete last item");
    demo.print();
    demo.deleteLast();
    demo.print();
    demo.deleteLast();
    demo.print();
  }

  private static void _20_deleteArbitrary()
  {
    LinkedListExercises<Integer> demo = new LinkedListExercises<Integer>(StdRandom.uniform(512));
    populate(demo, 10);

    StdOut.println("Ex. 1.3.20 - delete arbitrary item");
    demo.print();
    demo.deleteIndex(3);
    demo.print();
    demo.deleteIndex(0);
    demo.print();
    demo.deleteIndex(8);
    demo.print();
    demo.deleteIndex(8);
    demo.print();

  }

  private static void _21_find()
  {
    LinkedListExercises<Integer> demo = new LinkedListExercises<Integer>(StdRandom.uniform(512));
    demo.insert(25);
    demo.insert(26);
    demo.insert(27);

    StdOut.println("Ex. 1.3.21 - find item by value");
    demo.print();

    StdOut.printf("%d: %s\n", 25, demo.find(25));
    StdOut.printf("%d: %s\n", 35, demo.find(35));

  }
  public static void main(String[] args)
  {
    _19_deleteLast();
    _20_deleteArbitrary();
    _21_find();
  }
}
