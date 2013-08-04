package ch1_3;
import java.util.Iterator;
public class _14_ArrayQueue<Item> implements Iterable<Item>
{
  protected Item[] q;
  protected int frontPosition;
  private int emptyPosition;
  private int size;
  private int capacity;

  // Grows capacity only when depth is about to exceed capacity
  // i.e,. when the array is actually full.
  // If items have come off the front, we can
  // wrap around an re-use those element positions
  // when the rear of the queue goes past the right end
  // of the array
  public _14_ArrayQueue(int cap)
  {
    frontPosition = 0;
    emptyPosition = 0;
    capacity      = cap;
    q =  (Item[]) new Object[capacity];
  }

  /*
     1.3.41 Copy a queue. Create a new constructor so that
     Queue<Item> r = new Queue<Item>(q);
     makes r a reference to a new and independent copy of the queue q. You should be able
     to enqueue and dequeue from either q or r without influencing the other. Hint : Delete
     all of the elements from q and add these elements to both q and r.
  */
  public _14_ArrayQueue(_14_ArrayQueue<Item> other)
  {
    frontPosition = 0;
    emptyPosition = 0;
    if (other.isEmpty())
      capacity = 2;
    else
      capacity = other.size() * 2;

    q = (Item[]) new Object[capacity];

    for (Item i : other)
      this.enqueue(i);
  }

  public void enqueue(Item item)
  {
    q[emptyPosition] = item;
    incrementQueueSize();
    incrementEmptyPosition();
  }

  public Item dequeue()
  {
    if (isEmpty())
      return null;

    Item item = q[frontPosition];
    incrementFrontPosition();
    size--;
    return item;
  }

  public int size()       { return size; }
  public Item front()      { return q[frontPosition]; }
  public Item rear()       { return q[frontPosition + size - 1]; }
  public boolean isEmpty() { return size == 0; }
  public String toString()
  {
    String s = "";
    for (int i = frontPosition; i < emptyPosition; i++)
      s += q[i] + " ";
    return s;
  }

  private void resize(int max)
  {
    Item[] temp = (Item[]) new Object[max];
    for (int i = 0; i < q.length; i++)
      temp[i] = q[i];
    q = temp;
  }

  private int nextIndex(int current)
  {
    if (current == capacity - 1)
      return 0;
    return current + 1;
  }

  private void incrementEmptyPosition()
  {
    emptyPosition = nextIndex(emptyPosition);
  }

  private void incrementFrontPosition()
  {
    frontPosition = nextIndex(frontPosition);
  }

  private void incrementQueueSize()
  {
    size++;
    if (size >= capacity) {
      capacity *= 2;
      resize(capacity);
    }
  }

  public Iterator<Item> iterator()
  { return new ArrayQueueIterator(); }

  private class ArrayQueueIterator implements Iterator<Item>
  {
    private int i;
    public ArrayQueueIterator()
    {
      i = frontPosition;
    }
    public void remove()     {}
    public boolean hasNext()
    {
      if (isEmpty())
        return false;

      // if we've wrapped around, without increasing capacity
      if (emptyPosition == 0)
        return i < capacity - 1;

      return i < emptyPosition;
    }
    public Item next()
    {
      Item data = q[i];
      i = nextIndex(i);
      return data;
    }
  }
}
