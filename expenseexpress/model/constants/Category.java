package team14.expenseexpress.model.constants;

/**
 * @author Team 14
 * @version 0.1
 * @since 2015-02-19
 */
public final class Category {
    public static final String AIRFARE = "Air fare";
    public static final String GROUND_TRANSPORT = "Ground transport";
    public static final String PRIVATE_AUTOMOBILE = "Private automobile";
    public static final String FUEL = "Fuel";
    public static final String PARKING = "Parking";
    public static final String REGISTRATION = "Registration";
    public static final String ACCOMMODATION = "Accommodation";
    public static final String MEAL = "Meal";
    public static final String SUPPLIES = "Supplies";
    public static final String[] LIST = {AIRFARE, GROUND_TRANSPORT, PRIVATE_AUTOMOBILE, FUEL,
            PARKING, REGISTRATION, ACCOMMODATION, MEAL, SUPPLIES};

    /**
     * Empty private constructor.
     */
    private Category() {
        // empty
    }
}