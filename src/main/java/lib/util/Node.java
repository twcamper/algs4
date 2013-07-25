package util;
public class Node<Item>
{
  public Item item;
  public Node<Item> next;

  public String toString()
  {
    return hashCode() + ":" + item;
  }
}
