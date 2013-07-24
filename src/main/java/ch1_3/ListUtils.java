package ch1_3;
import util.Node;
/*
 * static methods to operate on a linked list
 */
public class ListUtils
{

  public static <I> void append(Node<I> list, I i)
  {
    Node<I> n = list;
    while (n.next != null)
      n = n.next;
    n.next = new Node<I>();
    n.next.item = i;
  }
  public static <I> Node<I> insert(Node<I> list, I i)
  {
    Node<I> newHead = new Node<I>();
    newHead.item = i;
    newHead.next = list;
    return newHead;
  }

  public static <I> boolean find(Node<I> list, I i)
  {
    for (Node<I> n = list; n != null; n = n.next) {
      if (n.item.equals(i))
        return true;
    }
    return false;
  }

  public static <I> void removeAfter(Node<I> node)
  {
    if (node.next == null)
      return;

    node.next = node.next.next;
  }

  public static <I> int size(Node<I> list)
  {
    int count = 0;
    for (Node<I> n = list; n != null; count++, n = n.next)
      ;
    return count;
  }
}
