public class FixedCapacityStack<Item>
{
  private Item[] a;
  private int top;

  public FixedCapacityStack(int capacity)
  { a = (Item[]) new Object[capacity];  }

  public boolean isEmpty() {  return top == 0;  }
  public int size()        {  return top;  }

  public void push(Item item)
  {  a[top++] = item;  }

  public Item pop()
  { return a[--top];  }

  private static void processPop(FixedCapacityStack stack)
  {
    if (!stack.isEmpty())
      StdOut.println(stack.pop() + " ");
  }
  private static void printFinal(String name, FixedCapacityStack stack)
  {
    StdOut.printf("%s: %d left on stack\n", name, stack.size());
  }

  public static void main(String[] args)
  {
    FixedCapacityStack<String> s;
    FixedCapacityStack<Integer> i;
    s = new FixedCapacityStack<String>(100);
    i = new FixedCapacityStack<Integer>(100);
    while (!StdIn.isEmpty()) {
      String item = StdIn.readString();
      try {
        Integer  n = Integer.parseInt(item);
        if (n.equals(-1))
          processPop(i);
        else
          i.push(n);
      } catch (NumberFormatException e) {
        if (item.equals("-"))
          processPop(s);
        else
          s.push(item);
      }
    }

    printFinal("String", s);
    printFinal("Integer", i);
  }

}
