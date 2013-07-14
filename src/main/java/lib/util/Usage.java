package util;
public class Usage
{
  public static void error(String cliUsage)
  {
    System.err.println(String.format("Usage: %s",  cliUsage));
    System.exit(1);
  }
}
