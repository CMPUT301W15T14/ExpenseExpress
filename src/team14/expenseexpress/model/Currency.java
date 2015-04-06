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
    
    private String name = "CAD";
   
	 /**
     * UnCallable Constructor
     *	
     * @param Expense expense
     */
    private Currency(String name){
    	this.name = name;
    }
	 /**
     * Getter method that returns a currency object, relevant
     *	to a String that is passed
     *
     * @param String text
     */
	public static Currency fromString(String text) {
        if (text != null) {
          for (Currency currency : Currency.values()) {
            if (text.equalsIgnoreCase(currency.name)) {
            	//currency.name = text;
              return currency;
            }
          }
        }
        return null;
      }
	 /**
     * getter, retrieves currency name
     *	
     * @param String
     */
    public String getName(){
    	return name;
    }
}