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
public class LinkedListTest {

  private LinkedList<Integer> l;
  @Before
  public void setUp()
  {
    l = new LinkedList<Integer>();
  }
  @Test
  public void newListIsEmpty()
  {
    assertEquals(l.isEmpty(), true);
  }
  @Test
  public void insertOne()
  {
    LinkedList<String> l = new LinkedList<String>();
    l.insert("Noo");
    assertThat(l.first(), equalTo("Noo"));
  }
  @Test
  public void insertReplacesFirst()
  {
    l.insert(0);
    l.insert(1);
    assertThat(l.first(), equalTo(1));
  }
  @Test
  public void _19_deleteFromEmptyDoesNotThrow()
    throws NullPointerException
  {
    l.deleteLast();
    assertEquals(l.isEmpty(), true);
  }
  @Test
  public void _19_deleteLastFromOne()
  {
    l.insert(0);

    l.deleteLast();
    assertEquals(l.isEmpty(), true);
  }

  @Test
  public void _19_deleteLastFromTwo()
  {
    l.insert(3);
    l.insert(33);

    l.deleteLast();
    assertThat(l.last(), equalTo(33));
  }

  @Test
  public void _19_deleteLastFromThree()
  {
    l.insert(2);
    l.insert(444);
    l.insert(33);

    l.deleteLast();
    assertThat(l.last(), equalTo(444));
  }

  @Test
  public void _20_deleteFirst()
  {
     l.insert(100);
     l.insert(200);
     l.deleteIndex(0);
     assertThat(l.first(), equalTo(l.last()));
  }

  @Test
  public void _20_deleteLast()
  {
     l.insert(100);
     l.insert(150);
     l.insert(200);
     assertThat(l.last(), equalTo(100));
     l.deleteIndex(2);
     assertThat(l.last(), equalTo(150));
  }

  @Test
  public void _20_deleteMiddle()
  {
     l.insert(100);
     l.insert(150);
     l.insert(200);
     l.insert(250);
     l.insert(350);
     assertThat(l.last(), equalTo(100));
     l.deleteIndex(3);
     assertThat(l.last(), equalTo(100));
  }

  @Test
  public void _20_deleteOutOfRangeDoesNotThrow()
      throws NullPointerException
  {
     l.insert(100);
     l.deleteIndex(3);
  }

  @Test
  public void _20_deleteOutOfRangeDoesNotAlter()
      throws NullPointerException
  {
     l.insert(100);
     l.insert(150);
     l.deleteIndex(3);
     assertThat(l.first(), equalTo(150));
     assertThat(l.last(), equalTo(100));
  }


  @Test
  public void _21_findFirst()
  {
    l.insert(25);
    l.insert(26);
    l.insert(27);

    assertEquals(l.find(25), true);
  }

  @Test
  public void _21_findLast()
  {
    l.insert(25);
    l.insert(26);
    l.insert(27);

    assertEquals(l.find(27), true);
  }

  @Test
  public void _21_findMiddle()
  {
    l.insert(25);
    l.insert(26);
    l.insert(27);
    l.insert(28);

    assertEquals(l.find(27), true);
  }

  @Test
  public void _21_findNone()
  {
    l.insert(25);
    l.insert(27);
    l.insert(28);

    assertEquals(l.find(26), false);
  }
}
