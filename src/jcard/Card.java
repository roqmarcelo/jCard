package jcard;

import jcard.enums.Brand;

public final class Card {

	// A regexp for matching non-digit values
	private static final String NON_DIGIT_REGEXP = "\\D";
	
    private String number;
    
    private ExpirationDate expirationDate;
    
	private int cvc;
	
	private Brand brand;
    
    /**
     * Attaches the provided card data to the card after removing non-digits from the provided number.
     * 
     * @param number
     * @param month
     * @param year
     * @param cvc
     */
    public Card(final String number, final int month, final int year, final int cvc) {
    	if (isNullOrEmpty(number)) {
    		throw new IllegalArgumentException("the attribute 'number' cannot be empty");
    	}
    	
    	this.number = number.replaceAll(NON_DIGIT_REGEXP, "");
    	this.expirationDate = new ExpirationDate(month, year);
    	this.cvc = cvc;
    	this.brand = Brand.from(this.number);
    	
    	System.out.println("cvc = [" + this.cvc + "]");
    }
    
    /**
     * Returns the credit card number with each of the number's digits but the 
     * first six and the last four digits replaced by an X, formatted the way
     * they appear on their respective brands' cards.
     */
    public String mask() {
    	if (!isMod10Valid()) {
    		return "invalid";
    	}
    	
    	if (Brand.AMEX == brand) {
    		return String.format("XXXX-XXXXXX-X%s", number.substring(11, 15));
    	}
    	
    	if (Brand.DINERS == brand) {
    		return String.format("XXXX-XXXXXX-%s", number.substring(10, 14));
    	}
    	
    	return String.format("XXXX-XXXX-XXXX-%s", number.substring(12, 16));
    }
    
    /**
     * Returns whether or not the card is expired.
     */
    public boolean isExpired() {
    	return expirationDate.isExpired();
    }
    
    /**
     * Returns whether or not the card is a valid card for making payments.
     */
    public boolean isValid() {
    	return !isExpired() && isMod10Valid();
    }
    
    /**
     * Returns whether or not the card's number validates against the mod10 
     * algorithm, automatically returning false on an empty value.
     */
    public boolean isMod10Valid() {
    	if (isNullOrEmpty(number)) {
    		return false;
    	}
    	
		int sum = 0;
		boolean alternate = false;

		for (int i = number.length() - 1; i >= 0; i--) {
			int n = Integer.parseInt(number.substring(i, i + 1));
			
			if (alternate) {
				n *= 2;
				
				if (n > 9) {
					n = (n % 10) + 1;
				}
			}
			
			sum += n;
			
			alternate = !alternate;
		}

		return (sum % 10 == 0);
    }

    public Brand getBrand() {
		return brand;
	}

	private boolean isNullOrEmpty(String value) {
    	return value == null || value.length() == 0;
    }
    
	@Override
	public String toString() {
		return "Card [brand=" + brand.getName() + ", number=" + mask() + ", expirationDate=" + expirationDate.asMonthYear() + "]";
	}
}