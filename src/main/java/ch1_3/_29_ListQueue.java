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
    for (Item i : this)
      System.out.printf("%s\t", i);
    System.out.println();
  }

  public Iterator<Item> iterator()
  { return new CircularListIterator(); }

  private class CircularListIterator implements Iterator<Item>
  {
    private Node n;
    private Node first;

    public CircularListIterator()
    {
      if (!isEmpty()) {
        n     = last.next;
        first = last.next;
      }
    }
    private boolean hasReturnedFirst = false;
    public    void remove()  {/*dummy to satisfy interface*/ }
    public boolean hasNext()
    {
      if (first == null)
        return false;

      if (n.equals(first) && hasReturnedFirst)
        return false;

      return true;
    }
    public Item next()
    {
      if (n.equals(first))
        hasReturnedFirst = true;

      Item data = n.item;
      n = n.next;
      return data;
    }

  }
}
