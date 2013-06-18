public class _11_PrintBooleanTable {

  public static void main(String[] args) {
    // boolean[][] table = new boolean[10][10];
    boolean[][] table = {
      {true, true, true, false, true},
      {true, false, true, false, true},
      {true, true, true, false, true},
      {true, false, true, false, true},
      {true, false, true, false, true},
      {true, false, true, false, true}
    };
    for (int row = 0; row < table.length; row++)
      for (int col = 0; col < table[row].length; col++) {
        StdOut.printf("%d, %d: %s\n", row, col, (table[row][col] ? "*" : ""));
      }
  }
}
