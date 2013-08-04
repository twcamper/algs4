import util.*;
import java.io.File;

public class _43_ListFiles
{
  private int depth;
  private Queue<String> q;
  private static final String TAB = "  ";
  private boolean ignoreHidden;
  public _43_ListFiles(boolean ignoreHidden, String topDirectory)
  {
    depth = 0;
    this.ignoreHidden = ignoreHidden;
    q = new Queue<String>();
    build(new File(topDirectory));
  }

  private void add(String f)
  {
    String indent = "";
    for (int i = 0; i < depth; i++)
      indent += TAB;

    q.enqueue(indent + f);
  }
  private void build(File dir)
    throws IllegalArgumentException
  {
     if (!dir.isDirectory())
       throw new IllegalArgumentException(String.format("'%s' is not a directory", dir));

     if (ignoreHidden && dir.isHidden())
       return;

     add(dir.getName() + " -\\");
     depth++;
     File[] list = dir.listFiles();
     for (int i = 0; i < list.length; i++) {
       if (list[i].isDirectory()) {
         build(list[i]);
       } else {
         if (!ignoreHidden && !list[i].isHidden())
           add(list[i].getName());
       }
     }
     depth--;
  }

  public void print()
  {
    for (String f : q)
      System.out.println(f);
  }
  public static void usage()
  {
    Usage.error("[-a] directory path");
  }
  public static void main(String[] args)
  {
    if (args.length < 1)
      usage();

    _43_ListFiles ls = null;
    if (args[0].charAt(0) == '-') {
      if (args[0].compareTo("-a") != 0)
        usage();
      else
        ls = new _43_ListFiles(false, args[1]);
    } else {
      ls = new _43_ListFiles(true, args[0]);
    }
    ls.print();
  }
}

