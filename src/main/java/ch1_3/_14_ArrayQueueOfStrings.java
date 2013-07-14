package ch1_3;
public class _14_ArrayQueueOfStrings
{
  private String[] a;
  private int first;
  private int last;

  public _14_ArrayQueueOfStrings(int capacity)
  {
    first = 0;
    last  = 0;
    a = new String[capacity];
  }

  public int first()
  { return first; }

}
