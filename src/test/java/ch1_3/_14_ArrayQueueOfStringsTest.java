package ch1_3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class _14_ArrayQueueOfStringsTest {

  @Test
  public void thisAlwaysPasses() {
  }

  @Test
  @Ignore
  public void thisIsIgnored() {
  }

  @Test
  public void pointlessGetterTest() {
    _14_ArrayQueueOfStrings q = new _14_ArrayQueueOfStrings(8);
    assertEquals(q.first(), 0);
  }
}
