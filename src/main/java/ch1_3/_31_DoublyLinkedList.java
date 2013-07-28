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
      head.next = n;
      head.prev = n;
      tail.next = n;
      tail.prev = n;
    }
  }

  public void append(Item data)
  {

  }

  public Item head()
  {
    return head.data;
  }

  public Item tail()
  {
    return tail.data;
  }

  public Item removeHead()
  {
    return null;
  }

  public Item removeTail()
  {
    return null;
  }

  public void remove(Item data)
  {

  }

  public Item removeBefore(Item data)
  {
    return null;
  }

  public Item removeAfter(Item data)
  {
    return null;
  }

  public boolean isEmpty()
  {
    return head == null;
  }
}
