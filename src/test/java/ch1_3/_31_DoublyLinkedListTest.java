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

}
