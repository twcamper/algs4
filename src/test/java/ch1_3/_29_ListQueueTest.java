package ch1_3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class _29_ListQueueTest {

  private _29_ListQueue<Integer> q;

  @Before
  public void setUp()
  {
    q = new _29_ListQueue<>();
  }

  @Test
  public void newQueueIsEmpty()
  {
    assertEquals(true, q.isEmpty());
  }

  @Test
  public void enqueueToEmptyLeavesNonEmpty()
  {
    q.enqueue(0);
    assertEquals(false, q.isEmpty());
  }

  @Test
  public void enqueueToEmptyFrontHasCorrectValue()
  {
    q.enqueue(-532);
    assertThat(q.front(), equalTo(-532));
  }

  @Test
  public void enqueueToEmptyFrontEqualsRear()
  {
    q.enqueue(2);
    assertThat(q.front(), equalTo(q.rear()));
  }

  @Test
  public void enqueueAfterOne()
  {
    q.enqueue(0);
    q.enqueue(1);
    assertThat(q.front(), equalTo(0));
    assertThat(q.rear(), equalTo(1));
  }

  @Test
  public void enqueueAfterTwo()
  {
    q.enqueue(0);
    q.enqueue(1);
    q.enqueue(2);
    assertThat(q.front(), equalTo(0));
    assertThat(q.rear(), equalTo(2));
  }

  @Test
  public void enqueueAfterMany()
  {
    q.enqueue(0);
    q.enqueue(1);
    q.enqueue(2);
    q.enqueue(3);
    q.enqueue(4);
    q.enqueue(5);
    q.enqueue(6);
    assertThat(q.front(), equalTo(0));
    assertThat(q.rear(), equalTo(6));
  }

  @Test
  public void enqueueAfterDequeue()
  {
    q.enqueue(10);
    q.enqueue(20);

    q.dequeue();
    q.enqueue(30);
    assertThat(q.front(), equalTo(20));
    assertThat(q.rear(), equalTo(30));
  }

  @Test
  public void enqueueAfterClear()
  {
    q.enqueue(10);
    q.enqueue(20);
    q.dequeue();
    q.dequeue();

    q.enqueue(30);
    assertThat(q.front(), equalTo(30));
    assertThat(q.rear(), equalTo(30));

    q.enqueue(40);
    q.enqueue(50);
    q.enqueue(60);
    q.enqueue(70);
    assertThat(q.front(), equalTo(30));
    assertThat(q.rear(), equalTo(70));

    assertThat(q.dequeue(), equalTo(30));
    assertThat(q.dequeue(), equalTo(40));
    assertThat(q.dequeue(), equalTo(50));
    assertThat(q.dequeue(), equalTo(60));
    assertThat(q.dequeue(), equalTo(70));
    assertEquals(true, q.isEmpty());
  }

  @Test
  public void dequeueEmpty()
  {
    assertThat(q.dequeue(), equalTo(null));
  }

  @Test
  public void dequeueFromOneEmptiesQueue()
  {
    q.enqueue(13);
    q.dequeue();
    assertEquals(true, q.isEmpty());
  }

  @Test
  public void dequeueFromOneReturnsCorrectValue()
  {
    q.enqueue(13);
    assertThat(q.dequeue(), equalTo(13));
  }

  @Test
  public void dequeueFromTwoReturnsFirst()
  {
    q.enqueue(0);
    q.enqueue(1);
    assertThat(q.dequeue(), equalTo(0));
  }

  @Test
  public void dequeueFromTwoLeavesLast()
  {
    q.enqueue(0);
    q.enqueue(1);
    q.dequeue();
    assertThat(q.rear(), equalTo(1));
  }

  @Test
  public void dequeueFromTwoFrontEqualsRear()
  {
    q.enqueue(0);
    q.enqueue(1);
    q.dequeue();
    assertThat(q.front(), equalTo(q.rear()));
  }

  @Test
  public void dequeueFromManyReturnsFirst()
  {
    q.enqueue(0);
    q.enqueue(1);
    q.enqueue(2);
    q.enqueue(3);
    q.enqueue(4);
    q.enqueue(5);
    q.enqueue(6);
    q.enqueue(7);

    assertThat(q.dequeue(), equalTo(0));
  }

  @Test
  public void dequeueFromManyLeavesNewFront()
  {
    q.enqueue(0);
    q.enqueue(1);
    q.enqueue(2);
    q.enqueue(3);
    q.enqueue(4);
    q.enqueue(5);
    q.enqueue(6);
    q.enqueue(7);

    q.dequeue();
    assertThat(q.front(), equalTo(1));
  }

  @Test
  public void dequeueFromManyEmptiesQueue()
  {
    q.enqueue(0);
    q.enqueue(1);
    q.enqueue(2);
    q.enqueue(3);
    q.enqueue(7);

    q.dequeue();
    q.dequeue();
    q.dequeue();
    q.dequeue();
    q.dequeue();
    q.dequeue();

    assertEquals(true, q.isEmpty());
  }

  @Test
  public void dequeueFromManyReturnsValuesInOrder()
  {
    q.enqueue(0);
    q.enqueue(1);
    q.enqueue(2);
    q.enqueue(3);
    q.enqueue(4);

    assertThat(q.dequeue(), equalTo(0));
    assertThat(q.dequeue(), equalTo(1));
    assertThat(q.dequeue(), equalTo(2));
    assertThat(q.dequeue(), equalTo(3));
    assertThat(q.dequeue(), equalTo(4));
  }

  @Test
  public void iteratorMakesQueueTraversable()
  {
    String actual = "";
    String expected = "10 20 30 40 50 60 70 80 90 100 ";

    q.enqueue(10);
    q.enqueue(20);
    q.enqueue(30);
    q.enqueue(40);
    q.enqueue(50);
    q.enqueue(60);
    q.enqueue(70);
    q.enqueue(80);
    q.enqueue(90);
    q.enqueue(100);

    for (Integer i : q)
      actual += i + " ";

    assertEquals(expected, actual);
  }

  @Test
  public void iteratorDoesNothingWhenQueueEmpty()
  {
    for (Integer i : q)
      assertTrue(false);
  }

  @Test
  public void copyEmpty()
  {
    _29_ListQueue<Integer> r = new _29_ListQueue<>(q);
    assertEquals(true, r.isEmpty());
  }

  @Test
  public void copyEmptyAddToEach()
  {
    _29_ListQueue<Integer> r = new _29_ListQueue<>(q);
    q.enqueue(0);
    r.enqueue(1);
    assertEquals(1, q.size());
    assertEquals(1, r.size());
    assertThat(q.dequeue(), equalTo(0));
    assertThat(r.dequeue(), equalTo(1));
  }

  @Test
  public void copyOne()
  {
    q.enqueue(-311);
    _29_ListQueue<Integer> r = new _29_ListQueue<>(q);
    assertEquals(1, r.size());
    assertEquals(q.front(), r.front());
  }

  @Test
  public void copyOneNodeAlterOneQueue()
  {
    q.enqueue(-311);
    _29_ListQueue<Integer> r = new _29_ListQueue<>(q);
    r.dequeue();
    r.enqueue(-310);
    assertNotEquals(q.front(), r.front());
    assertNotEquals(q.rear(), r.rear());
  }

  @Test
  public void copyTwoNodesEmptyOneQueue()
  {
    q.enqueue(0);
    q.enqueue(1);
    _29_ListQueue<Integer> r = new _29_ListQueue<>(q);
    q.dequeue();
    q.dequeue();
    assertEquals(2, r.size());
    assertEquals(0, q.size());
  }

  @Test
  public void copyManyAddMoreToOneQueue()
  {
    q.enqueue(0);
    q.enqueue(1);
    q.enqueue(2);
    q.enqueue(3);
    q.enqueue(4);
    _29_ListQueue<Integer> r = new _29_ListQueue<>(q);
    r.enqueue(5);
    r.enqueue(6);
    r.enqueue(7);
    assertEquals(8, r.size());
    assertEquals(5, q.size());
  }
}
