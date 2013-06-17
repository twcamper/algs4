public class _1_1_WhatDoTheyPrint {

  static void _1_1_6()
  {
    int f = 0;
    int g = 1;

    for (int i = 0; i < 16; i++) {
      StdOut.printf("%d\t", f);
      f = f + g;
      g = f - g;
    }
    StdOut.println();
  }

  static void _1_1_7a()
  {
    double t = 9.0;
    while (Math.abs(t - 9.0/t) > .001)
      t = (9.0/t + t) / 2.0;
    StdOut.printf("%.5f\n", t);
  }
  static void _1_1_7b()
  {
    int sum = 0;
    for (int i = 1; i < 1000; i++)
      for (int j = 0; j < i; j++)
        sum++;
    StdOut.println(sum);
  }

  static void _1_1_7c()
  {
    int sum = 0;
    for (int i = 1; i < 1000; i *= 2)
      for (int j = 0; j < 1000; j++)
        sum++;
    StdOut.println(sum);
  }

  public static void main(String[] args) {

    StdOut.println("Ex. 1.1.6");
    _1_1_6();

    StdOut.println("Ex. 1.1.7a");
    _1_1_7a();

    StdOut.println("Ex. 1.1.7b");
    _1_1_7b();

    StdOut.println("Ex. 1.1.7c");
    _1_1_7c();

    StdOut.println("Ex. 1.1.8a");
    StdOut.println('b');

    StdOut.println("Ex. 1.1.8b");
    StdOut.println('b' + 'c');

    StdOut.println("Ex. 1.1.8c");
    StdOut.println((char)('a' + 4));

  }
}
