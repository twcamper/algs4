package ch1_3;
import java.util.Random;

public class _35_RandomQueue<Item> extends _14_ArrayQueue<Item>
{
  private Integer sampleIndex;
  private Random random;
  public _35_RandomQueue(int capacity)
  {
    super(capacity);
    random = new Random(System.currentTimeMillis());
  }

  public Item dequeue()
  {
    Item temp           = q[frontPosition];
    q[frontPosition]    = q[getSampleIndex()];
    q[getSampleIndex()] = temp;

    // reset sample index once we have dequeued
    sampleIndex         = null;
    return super.dequeue();
  }

  public Item sample()
  {
    return q[getSampleIndex()];
  }

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

  private Integer randomIndex()
  {
    return frontPosition + random.nextInt(size());
  }
}
