import util.Node;
import ch1_3.ListUtils;

public class _40_MoveToFront
{
  private static Node<Character> moveToFront()
  {
    Node<Character> list = null;
    while (!StdIn.isEmpty()){
      Character ch = StdIn.readChar();
      if (list == null)
        list = new Node<Character>(ch);
      else {
        list = ListUtils.remove(list, ch);
        list = ListUtils.insert(list, ch);
      }
    }
    return list;
  }

  private static void print(Node<Character> n)
  {
    if (n == null) {
      System.out.println();
      return;
    }

    System.out.print(n.item);
    print(n.next);
  }

  public static void main(String[] args)
  {
    print(moveToFront());
  }
}
