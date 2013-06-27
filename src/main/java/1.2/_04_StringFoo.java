public class _04_StringFoo
{
  public static void main(String[] args)
  {
    String string1 = "hello";
    String string2 = string1;
    string1 = "world";
    StdOut.println(string1);
    StdOut.println(string2);

    // prints "world\nhello", so line 6 copies the value, not the reference.
  }
}
