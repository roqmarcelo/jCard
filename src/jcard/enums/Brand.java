package jcard.enums;

public enum Brand {

	VISA("^4\\d{12}(\\d{3})?$", "Visa"),
	MASTERCARD("^(5[1-5]\\d{4}|677189)\\d{10}$", "MasterCard"),
	AMEX("^3[47]\\d{13}$", "American Express"),
	DINERS ("^3(?:0[0-5]|[68][0-9])[0-9]{11}$", "Diners"),
	DISCOVER("^(6011|65\\d{2})\\d{12}$", "Discover"),
	UNKNOWN("", "Unknown");
	
	private String regex;
	
	private String name;
	
	private Brand(final String regex, final String name) {
		this.regex = regex;
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	/**
     * Gets a Brand from a card number
     */
    public static Brand from(final String number) {
    	if (number == null || number.length() == 0) {
    		return UNKNOWN;
    	}
    	
        for (Brand brand : Brand.values()) {
            if (number.matches(brand.regex)) {
                return brand;
            }
        }
        
        return UNKNOWN;
    }
}