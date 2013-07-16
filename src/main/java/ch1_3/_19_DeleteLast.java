public class _19_DeleteLast<Item>
{
  private Node first;

  public _19_DeleteLast(Item item)
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

  private void print()
  {
    for (Node n = first; n != null; n = n.next)
      StdOut.println(n.item);
  }
  public static void main(String[] args)
  {
    _19_DeleteLast<Integer> demo = new _19_DeleteLast<Integer>(StdRandom.uniform(512));
    for (int i = 0; i < 5; i++)
      demo.insert(StdRandom.uniform(1024));

    demo.print();
    demo.deleteLast();
    StdOut.println();
    demo.print();
    demo.deleteLast();
    StdOut.println();
    demo.print();
  }
}
