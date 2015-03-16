package team14.expenseexpress.model;

/**
 * @author  Team 14
 * @version 0.1
 * @since   2015-02-19
 */
public enum Currency {
    CAD ("CAD"),
    USD ("USD"),
    EUR ("EUR"),
    GBP ("GBP"),
    CHF ("CHF"),
    JPY ("JPY"),
    CNY ("CNY");
    
    private String name;
    
    private Currency(String name){
    	this.name = name;
    }
    public static Currency fromString(String text) {
        if (text != null) {
          for (Currency currency : Currency.values()) {
            if (text.equalsIgnoreCase(currency.name)) {
              return currency;
            }
          }
        }
        return null;
      }
  
    public String getName(){
    	return name;
    }
}