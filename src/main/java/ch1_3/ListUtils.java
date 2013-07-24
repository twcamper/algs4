package ch1_3;
import util.Node;
/*
 * static methods to operate on a linked list
 */
public class ListUtils
{

  public static <Item> Node<Item> insert(Node<Item> list, Item i)
  {
    Node<Item> newHead = new Node<Item>();
    newHead.item = i;
    newHead.next = list;
    return newHead;
  }

  public static <Item> boolean find(Node<Item> list, Item i)
  {
    for (Node<Item> n = list; n != null; n = n.next) {
      if (n.item.equals(i))
        return true;
    }
    return false;
  }
}
