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
public class _42_StackTest {

  private _42_Stack<Integer> s;
  private _42_Stack<Integer> t;
  @Before
  public void setUp()
  {
    s = new _42_Stack<Integer>();
  }

  @Test
  public void copyEmptyIsEmpty()
  {
    t = new _42_Stack(s);
    assertEquals(true, t.isEmpty());
  }

  @Test(expected=java.util.NoSuchElementException.class)
  public void copyEmptyPopStackUnderflow()
  {
    t = new _42_Stack(s);
    t.pop();
  }

  @Test
  public void copyOne()
  {
    s.push(421);
    t = new _42_Stack(s);
    assertEquals(1, t.size());
    assertThat(t.peek(), equalTo(s.peek()));
  }

  @Test
  public void copyOnePushToOnePopFromOther()
  {
    s.push(4);
    t = new _42_Stack(s);
    t.push(6);
    assertThat(s.pop(), equalTo(4));
    assertThat(t.pop(), equalTo(6));
  }

  @Test
  public void copyTwo()
  {
    s.push(2);
    s.push(1);
    t = new _42_Stack(s);
    assertThat(t.peek(), equalTo(s.peek()));
  }

  @Test
  public void copyMany()
  {
    s.push(5);
    s.push(4);
    s.push(3);
    s.push(2);
    s.push(1);
    t = new _42_Stack(s);
    assertThat(t.pop(), equalTo(s.pop()));
    assertThat(t.pop(), equalTo(s.pop()));
    assertThat(t.pop(), equalTo(s.pop()));
    assertThat(t.pop(), equalTo(s.pop()));
    assertThat(t.pop(), equalTo(s.pop()));

    assertEquals(true, s.isEmpty());
    assertEquals(true, t.isEmpty());
  }
}
