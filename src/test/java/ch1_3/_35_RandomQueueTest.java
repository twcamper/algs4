package ch1_3;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class _35_RandomQueueTest {

  private _35_RandomQueue<Integer> l;
  @Before
  public void setUp()
  {
    l = new _35_RandomQueue<Integer>(5);
    l.enqueue(90);
    l.enqueue(91);
    l.enqueue(92);
    l.enqueue(93);
    l.enqueue(94);
  }

  @Test
  public void sampleDoesNotAffectSize()
  {
    l.sample();
    assertEquals(5, l.size());
  }

  @Test
  public void sampleReturnsNonNull()
  {
    assertThat(l.sample(), not(equalTo(null)));
  }

  @Test
  public void sampleReturnsSameValueTwice()
  {
    Integer i = l.sample();
    assertThat(l.sample(), equalTo(i));
  }

  @Test
  public void dequeueReturnsNonNull()
  {
    assertThat(l.dequeue(), not(equalTo(null)));
  }

  @Test
  public void dequeueReturnsWhatSampleDid()
  {
    Integer i = l.sample();
    assertThat(l.dequeue(), equalTo(i));
  }

  @Test
  public void dequeueDecrementsSize()
  {
    l.dequeue();
    assertEquals(4, l.size());
  }

  @Test
  public void dequeueCanClear()
  {
    l.dequeue();
    l.dequeue();
    l.dequeue();
    l.dequeue();
    l.dequeue();
    assertEquals(true, l.isEmpty());
  }

  private boolean hasBeenReturned(Integer[] used, int value)
  {
    for (int i = 0; i < used.length && used[i] != null; i++) {
      if (used[i] == value)
        return true;
    }
    return false;
  }

  @Test
  public void dequeueDoesNotRepeat()
  {
    Integer[] used = new Integer[l.size()];
    Integer val;

    val = l.dequeue();
    assertEquals(false, hasBeenReturned(used, val));
    used[0] = val;
    val = l.dequeue();
    assertEquals(false, hasBeenReturned(used, val));
    used[1] = val;
    val = l.dequeue();
    assertEquals(false, hasBeenReturned(used, val));
    used[2] = val;
    val = l.dequeue();
    assertEquals(false, hasBeenReturned(used, val));
    used[3] = val;
    val = l.dequeue();
    assertEquals(false, hasBeenReturned(used, val));
  }
}
