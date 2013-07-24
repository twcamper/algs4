package ch1_3;

import util.Node;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ListUtilsTest {

  private Node<String> l;
  @Before
  public void setUp()
  {
    l = new Node<String>();
    l.item = "tail";
    l = ListUtils.insert(l, "third");
    l = ListUtils.insert(l, "second");
    l = ListUtils.insert(l, "head");
  }
  @Test
  public void _21_findFirst()
  {
    assertEquals(true, ListUtils.find(l, "head"));
  }

  @Test
  public void _21_findLast()
  {
    assertEquals(true, ListUtils.find(l, "tail"));
  }

  @Test
  public void _21_findMiddle()
  {
    assertEquals(true, ListUtils.find(l, "second"));
  }

  @Test
  public void _21_findNone()
  {
    assertEquals(false, ListUtils.find(l, "Nerd Ball Pudding"));
  }

  @Test
  public void _24_removeAfterFirst()
  {
    ListUtils.removeAfter(l);
    assertEquals(false, ListUtils.find(l, "second"));
    assertThat(l.next.item, equalTo("third"));
  }

  @Test
  public void _24_removeAfterMiddle()
  {
    Node<Integer> head = new Node<Integer>();
    Node<Integer> middle = new Node<Integer>();
    Node<Integer> tail = new Node<Integer>();

    head.item = 10;
    middle.item = 20;
    tail.item = 30;
    head.next = middle;
    middle.next = tail;

    ListUtils.removeAfter(middle);
    assertThat(middle.next, equalTo(null));
  }

  @Test
  public void _24_removeAfterLast()
  {
    Node<Integer> head = new Node<Integer>();
    Node<Integer> tail = new Node<Integer>();

    head.item = 1;
    head.next = tail;
    tail.item = 2;

    ListUtils.removeAfter(tail);
    assertEquals(2, ListUtils.size(head));
  }
}
