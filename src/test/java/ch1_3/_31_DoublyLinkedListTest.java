package ch1_3;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class _31_DoublyLinkedListTest {

  private _31_DoublyLinkedList<Integer> l;
  @Before
  public void setUp()
  {
    l = new _31_DoublyLinkedList<Integer>();
  }

  @Test
  public void newListIsEmpty()
  {
    assertEquals(true, l.isEmpty());
  }

  @Test
  public void insertToEmptyLeavesNonEmpty()
  {
    l.insert(3);
    assertEquals(false, l.isEmpty());
  }

  @Test
  public void insertToEmptyHeadEqualsTail()
  {
    l.insert(3);
    assertThat(l.head(), equalTo(3));
    assertThat(l.head(), equalTo(l.tail()));
  }

  @Test
  public void appendToEmptyLeavesNonEmpty()
  {
    l.append(3);
    assertEquals(false, l.isEmpty());
  }

  @Test
  public void appendToEmptyHeadEqualsTail()
  {
    l.append(3);
    assertThat(l.head(), equalTo(3));
    assertThat(l.head(), equalTo(l.tail()));
  }

  @Test
  public void insertToOneHeadChanges()
  {
    l.insert(0);
    l.insert(1);
    assertThat(l.head(), equalTo(1));
  }

  @Test
  public void insertToOneTailRemains()
  {
    l.insert(0);
    l.insert(1);
    assertThat(l.tail(), equalTo(0));
  }

  @Test
  public void appendToOneTailChanges()
  {
    l.insert(0);
    assertThat(l.tail(), equalTo(0));
    l.append(1);
    assertThat(l.tail(), equalTo(1));
  }

  @Test
  public void appendToOneHeadRemains()
  {
    l.insert(0);
    l.append(1);
    assertThat(l.head(), equalTo(0));
  }

  @Test
  public void removeEmpty()
  {
    assertThat(l.remove(3), equalTo(null));
  }

  @Test
  public void removeFromOne()
  {
    l.insert(3);
    l.remove(3);
    assertEquals(true, l.isEmpty());
  }

  @Test
  public void removeHeadFromOneLeavesHeadNull()
  {
    l.insert(12);
    l.removeHead();
    assertThat(l.head(), equalTo(null));
  }

  @Test
  public void removeHeadFromOneLeavesTailNull()
  {
    l.insert(12);
    l.removeHead();
    assertThat(l.tail(), equalTo(null));
  }

  @Test
  public void removeTailFromOneLeavesHeadNull()
  {
    l.insert(12);
    l.removeTail();
    assertThat(l.head(), equalTo(null));
  }

  @Test
  public void removeTailFromOneLeavesTailNull()
  {
    l.insert(12);
    l.removeTail();
    assertThat(l.tail(), equalTo(null));
  }

  @Test
  public void removeFromOneNotFound()
  {
    l.insert(0);
    assertThat(l.remove(3), equalTo(null));
  }

  @Test
  public void removeFromTwoAtHead()
  {
    l.insert(0);
    l.insert(1);
    assertThat(l.remove(1), equalTo(1));
    assertThat(l.head(), equalTo(0));
    assertThat(l.tail(), equalTo(0));
  }

  @Test
  public void removeFromTwoAtTail()
  {
    l.insert(0);
    l.insert(1);
    assertThat(l.remove(0), equalTo(0));
    assertThat(l.head(), equalTo(1));
    assertThat(l.tail(), equalTo(1));
  }

  @Test
  public void removeFromTwoNotFound()
  {
    l.insert(0);
    l.insert(1);
    assertThat(l.remove(3), equalTo(null));
    assertThat(l.head(), equalTo(1));
    assertThat(l.tail(), equalTo(0));
  }

  @Test
  public void removeFromMiddle()
  {
    l.insert(0);
    l.insert(1);
    l.insert(2);

    assertThat(l.remove(1), equalTo(1));
    assertThat(l.head(), equalTo(2));
    assertThat(l.tail(), equalTo(0));
  }

  @Test
  public void removeFromThreeAtHead()
  {
    l.insert(0);
    l.insert(1);
    l.insert(2);
    assertThat(l.remove(2), equalTo(2));
    assertThat(l.head(), equalTo(1));
    assertThat(l.tail(), equalTo(0));
  }

  @Test
  public void removeFromThreeAtTail()
  {
    l.insert(0);
    l.insert(1);
    l.insert(2);
    assertThat(l.remove(0), equalTo(0));
    assertThat(l.head(), equalTo(2));
    assertThat(l.tail(), equalTo(1));
  }

  @Test
  public void removeHeadClearsMany()
  {
    l.append(0);
    l.append(1);
    l.append(2);
    l.append(3);
    l.append(4);
    l.append(5);

    assertThat(l.removeHead(), equalTo(0));
    assertThat(l.removeHead(), equalTo(1));
    assertThat(l.removeHead(), equalTo(2));
    assertThat(l.removeHead(), equalTo(3));
    assertThat(l.removeHead(), equalTo(4));
    assertThat(l.removeHead(), equalTo(5));
    assertEquals(true, l.isEmpty());
  }

  @Test
  public void removeTailClearsMany()
  {
    l.insert(0);
    l.insert(1);
    l.insert(2);
    l.insert(3);
    l.insert(4);
    l.insert(5);

    assertThat(l.removeTail(), equalTo(0));
    assertThat(l.removeTail(), equalTo(1));
    assertThat(l.removeTail(), equalTo(2));
    assertThat(l.removeTail(), equalTo(3));
    assertThat(l.removeTail(), equalTo(4));
    assertThat(l.removeTail(), equalTo(5));
    assertEquals(true, l.isEmpty());

  }

  @Test
  public void removeBeforeEmpty()
  {
    assertThat(l.removeBefore(11), equalTo(null));
  }

  @Test
  public void removeAfterEmpty()
  {
    assertThat(l.removeAfter(11), equalTo(null));
  }

  @Test
  public void removeBeforeOneLeavesHeadAndTail()
  {
    l.insert(11);
    l.removeBefore(11);
    assertThat(l.head(), equalTo(11));
    assertThat(l.tail(), equalTo(11));
  }

  @Test
  public void removeAfterOneLeavesHeadAndTail()
  {
    l.insert(11);
    l.removeAfter(11);
    assertThat(l.head(), equalTo(11));
    assertThat(l.tail(), equalTo(11));
  }

  @Test
  public void removeBeforeRemovesHead()
  {
    l.insert(1);
    l.insert(10);
    assertThat(l.removeBefore(1), equalTo(10));
    assertThat(l.head(), equalTo(1));
  }

  @Test
  public void removeBeforeNothingFound()
  {
    l.insert(1);
    l.insert(10);
    l.insert(11);
    l.insert(12);
    assertThat(l.removeBefore(9), equalTo(null));
  }

  @Test
  public void removeBeforeRemovesMiddle()
  {
    l.insert(1);
    l.insert(10);
    l.insert(11);
    l.insert(12);
    assertThat(l.removeBefore(10), equalTo(11));
  }

  @Test
  public void removeAfterFindsTail()
  {
    l.insert(0);
    l.insert(1);
    assertThat(l.removeAfter(0), equalTo(null));
  }

  @Test
  public void removeAfterFindsHead()
  {
    l.insert(0);
    l.insert(1);
    l.insert(30);
    assertThat(l.removeAfter(30), equalTo(1));
    assertThat(l.head(), equalTo(30));
  }

  @Test
  public void removeAfterRemovesTail()
  {
    l.insert(0);
    l.insert(1);
    l.insert(31);
    assertThat(l.removeAfter(1), equalTo(0));
    assertThat(l.tail(), equalTo(1));
  }

  @Test
  public void removeAfterNothingFound()
  {
    l.insert(0);
    l.insert(1);
    l.insert(31);
    assertThat(l.removeAfter(9), equalTo(null));
  }

  @Test
  public void removeAfterRemovesMiddle()
  {
    l.insert(1);
    l.insert(10);
    l.insert(11);
    l.insert(12);
    assertThat(l.removeAfter(11), equalTo(10));
  }

}
