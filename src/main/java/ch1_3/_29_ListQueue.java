package ch1_3;
import java.util.Iterator;

public class _29_ListQueue<Item> implements Iterable<Item>
{
  private class Node
  {
    Item item;
    Node next;
    public Node(Item i)      { this.item = i; }
    public String toString() { return hashCode() + ":" + item; }
  }

  private Node last;

  public _29_ListQueue()   { last = null; }
  public Item front()      { return last.next.item; }
  public Item rear()       { return last.item; }
  public boolean isEmpty() { return last == null; }
  public String toString()
  {
    String s = "";
    return s;
  }

  public void enqueue(Item data)
  {
    Node newLast = new Node(data);

    if (last == null) {
      last = newLast;
      last.next = last;
    } else {
      // insert between front and rear (between first and old last)
      newLast.next = last.next;  // newLast points to first
      last.next    = newLast;    // old last points to new last
      last         = newLast;    // instance var references new 'last' node
    }
  }

  public Item dequeue()
  {
    if (isEmpty())
      return null;

    Item i = last.next.item;

    if (last.equals(last.next))
      last = null;
    else {
      last.next = last.next.next;
    }

    return i;
  }

  public void print()
  {
    Node n = last;
    while (!n.next.equals(last)) {
      System.out.printf("%s\t", n.next);
      n = n.next;
    }
    System.out.println();
  }

  public Iterator<Item> iterator()
  { return new CircularListIterator(); }

  private class CircularListIterator implements Iterator<Item>
  {
    private Node n = last.next;
    public    void remove()  {/*dummy to satisfy interface*/ }
    public boolean hasNext() { return !n.equals(last);  }
    public    Item next()    {
      Item data = n.item;
      n = n.next;
      return data;
    }

  }
}
