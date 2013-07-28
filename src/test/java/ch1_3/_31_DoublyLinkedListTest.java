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
    l.remove(3);
  }

  @Test
  public void removeFromOne()
  {
    l.insert(3);
    l.remove(3);
    assertEquals(true, l.isEmpty());
  }
}
