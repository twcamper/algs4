package ch1_3;
/*
 * An object that has a linked list and operates on it.
 */
public class ListContainer<Item>
{
  private class Node
  {
    Item item;
    Node next;
  }
  private Node first;

  public boolean isEmpty() { return first == null; }
  public Item first()      { return first.item; }

  public Item last()
  {
    Node n = first;
    while (n.next != null)
      n = n.next;
    return n.item;
  }
  public Item itemAt(int index)
  {
    Node n = first;
    int i = 0;
    while (n != null && i++ < index)
      n = n.next;

    if (n == null)
      return null;
    return n.item;
  }
  public void insert(Item i)
  {
    Node n = new Node();
    n.item = i;
    n.next = first;
    first = n;
  }
  public void deleteLast()
  {
    if (isEmpty())
      return;
    if (first.next == null) {
      first = null;
    } else {
      Node n = first;
      while (n.next.next != null)
        n = n.next;
      n.next = null;
    }
  }
  public void deleteIndex(int target)
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

  public boolean find(Item i)
  {
    for (Node n = first; n != null; n = n.next)
      if (n.item.equals(i))
        return true;
    return false;
  }
}
