package data;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.CreditCard;

public class CreditCardsDB {
	public static CreditCard getCreditCard(String cardNumber) {
		String query = "SELECT * FROM creditCardBuildings WHERE Name=" + cardNumber;
		ResultSet rs = Database.runQuery(query);
		try {
			if(rs.next())
			{
			    //TODO: make creditCard
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static CreditCard getCreditCard(CreditCard creditCard) {
		return getCreditCard(creditCard.getCardNumber());
	}
	
	public static boolean addCreditCard() {
		String query = "INSERT INTO creditCards VALUES (NULL, ?, ?, ?)";
		//TODO: Add values I need from David.
		int i = Database.runUpdate(query);
		if(i == 1) {
		    return true;
		}
		return false;
	}
	
	public static boolean deleteCreditCard(String creditCardName) {
		String query = "DELETE FROM creditCards WHERE CreditCardname=" + creditCardName;
		int i = Database.runUpdate(query);
		if(i == 1) {
		    return true;
		}
	    return false;
	}
}
