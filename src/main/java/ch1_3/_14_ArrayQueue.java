package ch1_3;
public class _14_ArrayQueue<Item>
{
  private Item[] q;
  private int frontPosition;
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

  public void enqueue(Item item)
  {
    q[emptyPosition] = item;
    incrementQueueSize();
    incrementEmptyPosition();
  }

  public Item dequeue()
  {
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

  private void incrementEmptyPosition()
  {
    emptyPosition++;
    if (emptyPosition >= capacity)
      emptyPosition = 0;
  }

  private void incrementFrontPosition()
  {
    frontPosition++;
    if (frontPosition >= capacity)
      frontPosition = 0;
  }

  private void incrementQueueSize()
  {
    size++;
    if (size >= capacity) {
      capacity *= 2;
      resize(capacity);
    }
  }
}
