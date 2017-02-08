package jcard;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * An expiration date of a credit card.
 */
public final class ExpirationDate {

	private Date expiredAfter;
	
	/**
	 * Attaches the last possible datetime for the given month and year, 
	 * as well as the raw month and year values.
	 * 
	 * @param month
	 * @param year
	 */
	public ExpirationDate(final int month, final int year) {
		this.expiredAfter = createExpiredAfter(month, year);
	}
	
	/**
	 * Returns whether or not the expiration date has passed in American Samoa (the last timezone).
	 */
	public boolean isExpired() {
		// Return whether the exipred after time has passed in American Samoa
		return getSamoaNow().after(this.expiredAfter);
	}
	
	/**
	 * Returns the expiration date in MM/YYYY format.
	 */
    public String asMonthYear() {
        return new SimpleDateFormat("MM/yyyy").format(expiredAfter);
	}
	
    /**
     * Returns the expiration date in MM format.
     */
    public String asMonth() {
    	return new SimpleDateFormat("MM").format(expiredAfter);
    }

    /**
     * Returns the expiration date in YYYY format.
     */
    public String asYear() {
    	return new SimpleDateFormat("yyyy").format(expiredAfter);
    }
    
	@Override
	public String toString() {
		return "ExpirationDate [expiredAfter=" + asMonthYear() + "]";
	}

	/**
	 * Attach the last possible datetime for the provided month and year
	 * 
	 * @param month
	 * @param year
	 * @return the last possible datetime for the provided month
	 */
	private Date createExpiredAfter(final int month, final int year) {
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(year, month - 1, 0, 23, 59, 59);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		return calendar.getTime();
	}
	
	/**
	 * Gets the datetime in Samoa (datetime minus 11 hours (Samoa is UTC-11))
	 */
	private Date getSamoaNow() {
		// Get the current datetime in UTC
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
		// Get the datetime minus 11 hours (Samoa is UTC-11)
		calendar.add(Calendar.HOUR_OF_DAY, -11);
		
		return calendar.getTime();
	}
}