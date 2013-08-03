package util;
public class Node<Item>
{
  public Item item;
  public Node<Item> next;

  public Node(Item i) { item = i; }

  public String toString()
  {
    return hashCode() + ":" + item;
  }
}
