package ch1_3;

public class _39_RingBuffer<Item>
{
  private int capacity;
  protected Item[] q;
  protected int frontPosition;
  private int emptyPosition;
  private int size;

  public _39_RingBuffer(int capacity)
  {
    this.capacity = capacity;
    frontPosition = 0;
    emptyPosition = 0;
    size          = 0;
    q             =  (Item[]) new Object[capacity];
  }

  public boolean isEmpty() { return size == 0;}
  public boolean isFull()  { return size == capacity;}
  public int     size()    { return size; }
  public void    add(Item item)
    throws IndexOutOfBoundsException
  {
    if (isFull())
      throw new IndexOutOfBoundsException("RingBuffer is full");

    q[emptyPosition] = item;
    size++;
    incrementEmptyPosition();
  }
  public Item    remove()
  {
    if (isEmpty())
      return null;

    Item item = q[frontPosition];
    incrementFrontPosition();
    size--;
    return item;
  }

  public Item front()
  { return q[frontPosition];}

  public Item rear()
  {
    if (emptyPosition == 0)
      return q[capacity - 1];

    return q[emptyPosition - 1];
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

}
