import ch1_3._35_RandomQueue;

public class BridgeDealer
{
  private _35_RandomQueue<String> deck;

  public BridgeDealer()
  {
    deck = new _35_RandomQueue<String>(52);
    loadDeck(deck);
  }
  public boolean hasCards() { return !deck.isEmpty(); }
  public void printDeck() { System.out.println(deck); }

  public void dealHand(String player)
  {
    System.out.println(player);
    for (int i = 0; i < 13; i++)
      System.out.printf("%s\t", deck.dequeue());

    System.out.println();
  }

  private static void loadDeck(_35_RandomQueue<String> deck)
  {
    String[] suits = {"C", "D", "H", "S"};
    String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    for (String suit : suits)
      for (String rank : ranks)
        deck.enqueue(rank + suit);
  }

  public static void main(String[] args)
  {
    BridgeDealer dealer = new BridgeDealer();
    dealer.dealHand("North");
    dealer.dealHand("South");
    dealer.dealHand("East");
    dealer.dealHand("West");
  }
}
