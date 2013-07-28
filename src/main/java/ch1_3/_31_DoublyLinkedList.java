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
    return head.data;
  }

  public Item tail()
  {
    return tail.data;
  }

  public Item removeHead()
  {
    Item data = head.data;
    head = head.next;
    return data;
  }

  public Item removeTail()
  {
    return null;
  }

  public void remove(Item data)
  {
    DoubleNode n = head;
    while (n != null)  {
      if (n.data.equals(data)) {
        if (n.prev == null) {
          removeHead();
        } else if (n.next == null) {
          removeTail();
        } else {

        }
        break;
      }
      n = n.next;
    }
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
