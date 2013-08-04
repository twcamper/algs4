package ch1_3;
import java.util.Random;
import java.util.Iterator;

public class _35_RandomQueue<Item> implements Iterable<Item>
{
  private Item[] q;
  private int frontPosition;
  private int emptyPosition;
  private int size;
  private int capacity;

  private Integer sampleIndex;
  private Random random;
  public _35_RandomQueue(int capacity)
  {
    frontPosition = 0;
    emptyPosition = 0;
    this.capacity = capacity;
    q =  (Item[]) new Object[capacity];
    random = new Random(System.currentTimeMillis());
  }
  private int copyIndex;
  public _35_RandomQueue(_35_RandomQueue<Item> other)
  {
    frontPosition = 0;
    emptyPosition = 0;
    if (other.isEmpty())
      capacity = 2;
    else
      capacity = other.size();

    q =  (Item[]) new Object[capacity];
    random = new Random(System.currentTimeMillis());

    other.resetCopyIndex();
    while (!other.isEmpty() && other.hasNext())
      this.enqueue(other.next());
  }
  public boolean hasNext()
  {
    // if we've wrapped around, without increasing capacity
    if (emptyPosition == 0)
      return copyIndex < capacity - 1;

    return copyIndex < emptyPosition;
  }
  public Item next()
  {
    Item data = q[copyIndex];
    if (copyIndex == capacity - 1)
      copyIndex = 0;
    else
      copyIndex++;

    return data;
  }
  public void resetCopyIndex()
  { copyIndex = frontPosition; }

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

    Item temp           = q[frontPosition];
    q[frontPosition]    = q[getSampleIndex()];
    q[getSampleIndex()] = temp;

    // reset sample index once we have dequeued
    sampleIndex         = null;
    Item item = q[frontPosition];
    incrementFrontPosition();
    size--;
    return item;
  }

  public Item sample()
  {
    return q[getSampleIndex()];
  }

  public int size()        { return size; }
  public Item front()      { return q[frontPosition]; }
  public Item rear()       { return q[frontPosition + size - 1]; }
  public boolean isEmpty() { return size == 0; }
  // lazy initialize sample index
  // this allows us to re-use the index
  // in a dequeue op that comes after a sample.
  private Integer getSampleIndex()
  {
    if (sampleIndex == null) {
      sampleIndex = randomIndex();
    }
    return sampleIndex;
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

  private Integer randomIndex()
  {
    return frontPosition + random.nextInt(size());
  }

  public Iterator<Item> iterator()
  { return new RandomIterator(this); }

  private class RandomIterator implements Iterator<Item>
  {
    private _35_RandomQueue<Item> copy;
    public RandomIterator(_35_RandomQueue<Item> other)
    {
      copy = new _35_RandomQueue(other);
    }
    public void remove() {}
    public boolean hasNext()
    {
      return !copy.isEmpty();
    }
    public Item next()
    {
      return copy.dequeue();
    }

  }

  public static void main(String[] args)
  {
    _35_RandomQueue<String> q = new _35_RandomQueue<>(10);
    q.enqueue("One");
    q.enqueue("flew");
    q.enqueue("over");
    q.enqueue("the");
    q.enqueue("cuckoo's");
    q.enqueue("nest.");

    for (String i : q)
      System.out.println(i);

    System.out.println();
    System.out.println(q.dequeue());
    System.out.println();

    for (String i : q)
      System.out.println(i);
  }
}
