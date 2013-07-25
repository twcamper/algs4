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
    if (node == null || node.next == null)
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

  /*
     1.3.24 Write a method removeAfter() that takes a linked-list Node as argument and
     removes the node following the given one (and does nothing if the argument or the next
     field in the argument node is null).
  */
  public static <I> void insertAfter(Node<I> listMember, Node<I> newNode)
  {
    if (listMember == null || newNode == null)
      return;

    newNode.next = listMember.next;
    listMember.next = newNode;
  }
  public static <I> Node<I> nodeFor(Node<I> list, I item)
  {
    for (Node<I> n = list; n != null; n = n.next)
      if (n.item.equals(item))
        return n;
    return null;
  }

  public static <I> Node<I> nodeAt(Node<I> list, int index)
  {
    Node<I> n;
    int i;
    for (i = 0, n = list; n != null && i < index; i++, n = n.next)
      ;

    return n;
  }

  public static <I> I itemAt(Node<I> list, int index)
  {
    Node <I> n = nodeAt(list, index);
    if (n == null)
      return null;
    return n.item;
  }

  public static <I> I lastItem(Node<I> list)
  {
    Node <I> n = lastNode(list);
    if (n == null)
      return null;
    return n.item;
  }

  public static <I> Node<I> lastNode(Node<I> list)
  {
    Node<I> n = list;;
    while (n != null && n.next != null)
      n = n.next;
    return n;
  }

  /*
     1.3.26 Write a method remove() that takes a linked list and a string key as arguments
     and removes all of the nodes in the list that have key as its item field.
 */
 public static <I> Node<I> removeAll(Node<I> list, I item)
 {
   Node<I> curr = list;
   Node<I> prev = null;

   while (curr != null) {
     if (curr.item.equals(item)) {
       curr = curr.next; // advance node to be tested
       if (prev == null)
         list = curr;    // first node matches: reset head
       else if (prev.next != null)
         prev.next = prev.next.next;  // bypass current
     } else {
       prev = curr;
       curr = curr.next;
     }
   }
   return list;
 }

 public static <I> void print(Node<I> n, String message)
 {
    if (n == null) {
      System.out.println();
      return;
    }
    if (message != null)
      System.out.println(message);
    System.out.printf("%s\t", n);
    print(n.next, null);
 }
}
