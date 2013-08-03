package ch1_3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class _39_RingBufferTest {

  private _39_RingBuffer<Integer> q;

  @Before
  public void setUp()
  {
    q = new _39_RingBuffer<>(5);
  }

  @Test
  public void newBufferIsEmpty()
  {
    assertEquals(true, q.isEmpty());
  }

  @Test
  public void addToEmptyLeavesNonEmpty()
  {
    q.add(0);
    assertEquals(false, q.isEmpty());
  }

  @Test
  public void addToEmptyFrontHasCorrectValue()
  {
    q.add(-532);
    assertThat(q.front(), equalTo(-532));
  }

  @Test
  public void addToEmptyFrontEqualsRear()
  {
    q.add(2);
    assertThat(q.front(), equalTo(q.rear()));
  }

  @Test(expected=IndexOutOfBoundsException.class)
  public void addToFullThrowsError()
  {
    q.add(0);
    q.add(1);
    q.add(2);
    q.add(3);
    q.add(4);
    q.add(5);
  }
  @Test
  public void addAfterOne()
  {
    q.add(0);
    q.add(1);
    assertThat(q.front(), equalTo(0));
    assertThat(q.rear(), equalTo(1));
  }

  @Test
  public void addAfterTwo()
  {
    q.add(0);
    q.add(1);
    q.add(2);
    assertThat(q.front(), equalTo(0));
    assertThat(q.rear(), equalTo(2));
  }

  @Test
  public void addAfterMany()
  {
    q.add(0);
    q.add(1);
    q.add(2);
    q.add(3);
    q.add(4);
    assertThat(q.front(), equalTo(0));
    assertThat(q.rear(), equalTo(4));
  }

  @Test
  public void addAfterRemove()
  {
    q.add(10);
    q.add(20);

    q.remove();
    q.add(30);
    assertThat(q.front(), equalTo(20));
    assertThat(q.rear(), equalTo(30));
  }

  @Test
  public void addAfterClear()
  {
    q.add(10);
    q.add(20);
    q.remove();
    q.remove();

    q.add(30);
    assertThat(q.front(), equalTo(30));
    assertThat(q.rear(), equalTo(30));

    q.add(40);
    q.add(50);
    q.add(60);
    q.add(70);
    assertThat(q.front(), equalTo(30));
    assertThat(q.rear(), equalTo(70));

    assertThat(q.remove(), equalTo(30));
    assertThat(q.remove(), equalTo(40));
    assertThat(q.remove(), equalTo(50));
    assertThat(q.remove(), equalTo(60));
    assertThat(q.remove(), equalTo(70));
    assertEquals(true, q.isEmpty());
  }

  @Test
  public void removeEmpty()
  {
    assertThat(q.remove(), equalTo(null));
  }

  @Test
  public void removeFromOneEmptiesBuffer()
  {
    q.add(13);
    q.remove();
    assertEquals(true, q.isEmpty());
  }

  @Test
  public void removeFromOneReturnsCorrectValue()
  {
    q.add(13);
    assertThat(q.remove(), equalTo(13));
  }

  @Test
  public void removeFromTwoReturnsFirst()
  {
    q.add(0);
    q.add(1);
    assertThat(q.remove(), equalTo(0));
  }

  @Test
  public void removeFromTwoLeavesLast()
  {
    q.add(0);
    q.add(1);
    q.remove();
    assertThat(q.rear(), equalTo(1));
  }

  @Test
  public void removeFromTwoFrontEqualsRear()
  {
    q.add(0);
    q.add(1);
    q.remove();
    assertThat(q.front(), equalTo(q.rear()));
  }

  @Test
  public void removeFromManyReturnsFirst()
  {
    q.add(0);
    q.add(1);
    q.add(2);
    q.add(3);

    assertThat(q.remove(), equalTo(0));
  }

  @Test
  public void removeFromManyLeavesNewFront()
  {
    q.add(0);
    q.add(1);
    q.add(2);
    q.add(3);

    q.remove();
    assertThat(q.front(), equalTo(1));
  }

  @Test
  public void removeFromManyEmptiesBuffer()
  {
    q.add(0);
    q.add(1);
    q.add(2);
    q.add(3);
    q.add(7);

    q.remove();
    q.remove();
    q.remove();
    q.remove();
    q.remove();
    q.remove();

    assertEquals(true, q.isEmpty());
  }

  @Test
  public void removeFromManyReturnsValuesInOrder()
  {
    q.add(0);
    q.add(1);
    q.add(2);
    q.add(3);
    q.add(4);

    assertThat(q.remove(), equalTo(0));
    assertThat(q.remove(), equalTo(1));
    assertThat(q.remove(), equalTo(2));
    assertThat(q.remove(), equalTo(3));
    assertThat(q.remove(), equalTo(4));
  }

  @Test
  public void isFullReturnsFalseWhenEmpty()
  {
    assertEquals(false, q.isFull());
  }

  @Test
  public void isFullReturnsFalseWhenNotFull()
  {
    q.add(0);
    q.add(1);
    q.add(2);
    q.add(3);
    assertEquals(false, q.isFull());
  }

  @Test
  public void isFullReturnsTrueWhenFull()
  {
    q.add(0);
    q.add(1);
    q.add(2);
    q.add(3);
    q.add(4);
    assertEquals(true, q.isFull());
  }

  @Test
  public void FillAndEmptyAfterWrapping()
  {
    q = new _39_RingBuffer<>(3);
    q.add(0);    // first == 0
    q.remove();
    q.add(1);    // first == 1
    q.remove();
    q.add(2);    // first == 2
    q.remove();

    q.add(3);    // first == 0
    q.add(4);
    q.add(5);
    assertEquals(true, q.isFull());
    q.remove();
    q.remove();
    q.remove();
    assertEquals(true, q.isEmpty());
  }

  // @Test
  // public void iteratorMakesBufferTraversable()
  // {
    // String actual = "";
    // String expected = "10 20 30 40 50 60 70 80 90 100 ";

    // q.add(10);
    // q.add(20);
    // q.add(30);
    // q.add(40);
    // q.add(50);
    // q.add(60);
    // q.add(70);
    // q.add(80);
    // q.add(90);
    // q.add(100);

    // for (Integer i : q)
      // actual += i + " ";

    // assertEquals(expected, actual);
  // }

  // @Test
  // public void iteratorDoesNothingWhenBufferEmpty()
  // {
    // for (Integer i : q)
      // assertTrue(false);
  // }
}
