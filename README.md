# jCard
A simple credit card validation Java library with no dependencies

## Usage

```Java
import jcard.Card

// Create a new card
Card visa = new Card("4444333322221111", 1, 2019, 888);

// Validate the card (checks that the card isn't expired and is mod10 valid)
card.isValid();

// Perform validation checks individually
card.isExpired();
card.isMod10Valid();

/* 
 * Returns the credit card number with each of the number's digits but the 
 * first six and the last four digits replaced by an X, formatted the way
 * they appear on their respective brands' cards. Example.: XXXX-XXXX-XXXX-1111
 */
card.mask();

// Returns the brand of the card (needs to import jcard.enums.Brand to use Brand.VISA or ther other brands)
card.getBrand() == Brand.VISA;
```

This project is a Java implementation of the [pycard](https://github.com/jusbrasil/pycard) library with some improvements I found interesting to make.
