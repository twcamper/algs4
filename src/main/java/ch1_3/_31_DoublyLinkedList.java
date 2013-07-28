package ch1_3;

public class _31_DoublyLinkedList<Item>
{

  private class DoubleNode
  {
    Item data;
    DoubleNode next;
    DoubleNode prev;

    public DoubleNode(Item data) { this.data = data; }
  }

  private DoubleNode head;
  private DoubleNode tail;

  public void insert(Item data)
  {
    DoubleNode n = new DoubleNode(data);
    if (head == null) {
      head = n;
      tail = n;
    } else {
      n.next = head;
      head.prev = n;
      head = n;
    }
  }

  public void append(Item data)
  {
    DoubleNode n = new DoubleNode(data);
    if (tail == null) {
      head = n;
      tail = n;
    } else {
      tail.next = n;
      n.prev = tail;
      tail = n;
    }
  }

  public Item head()
  {
    if (head == null)
      return null;
    return head.data;
  }

  public Item tail()
  {
    if (tail == null)
      return null;
    return tail.data;
  }

  public Item removeHead()
  {
    Item data = head.data;
    if (head == tail)
      tail = null;
    head = head.next;
    return data;
  }

  public Item removeTail()
  {
    Item data = tail.data;
    if (tail == head)
      head = null;
    tail = tail.prev;
    return data;
  }

  public Item remove(Item data)
  {
    DoubleNode n = find(data);

    if (n == null)
      return null;
    if (n.prev == null)
      return removeHead();
    if (n.next == null)
      return removeTail();

    n.prev = n.next;

    return n.data;
  }

  private DoubleNode find(Item data)
  {
    for (DoubleNode n = head; n != null; n = n.next)
      if (n.data.equals(data))
        return n;
    return null;
  }
  public Item removeBefore(Item data)
  {
    DoubleNode n = find(data);

    if (n == null || n.prev == null)
      return null;

    if (n.prev == head)
      return removeHead();

    Item i = n.prev.data;
    n.prev = n.prev.prev;
    return i;
  }

  public Item removeAfter(Item data)
  {
    DoubleNode n = find(data);

    if (n == null || n.next == null)
      return null;

    if (n.next == tail)
      return removeTail();

    Item i = n.next.data;
    n.next = n.next.next;
    return i;
  }

  public boolean isEmpty()
  {
    return head == null;
  }
}
