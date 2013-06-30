package util;
import util.Date;
public class Transaction
{
  private final String who;
  private final Date when;
  private final double amount;

  public Transaction(String who, Date when, double amount)
  {
    this.who = who;
    this.when = when;
    this.amount = amount;
  }

  public String who() { return who; }
  public Date when() { return when; }
  public double amount() { return amount; }

  public String toString()
  {
    return String.format("%s\t\t%s %.2f", who(), when(), amount());
  }
  public boolean equals(Object other)
  {
    if (this == other) return true;
    if (other == null) return false;
    if (this.getClass() != other.getClass()) return false;

    Transaction that = (Transaction) other;
    if (this.who != that.who)              return false;
    if (this.when != that.when)            return false;
    if (this.amount != that.amount)        return false;

    return true;
  }
}
