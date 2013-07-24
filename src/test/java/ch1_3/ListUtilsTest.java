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
    l.item = "foo";
    l = ListUtils.insert(l, "bar");
    l = ListUtils.insert(l, "clave");
    l = ListUtils.insert(l, "samba");
  }
  @Test
  public void _21_findFirst()
  {
    assertEquals(true, ListUtils.find(l, "samba"));
  }

  @Test
  public void _21_findLast()
  {
    assertEquals(true, ListUtils.find(l, "foo"));
  }

  @Test
  public void _21_findMiddle()
  {
    assertEquals(true, ListUtils.find(l, "clave"));
  }

  @Test
  public void _21_findNone()
  {
    assertEquals(false, ListUtils.find(l, "Nerd Ball Pudding"));
  }

}
