package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.CreditCard;

public class CreditCardsDB {
	
	public static CreditCard createCard(ResultSet rs) {
		CreditCard card = new CreditCard();
		try {
			card.setCardType(rs.getString("CardType"));
			card.setCardNumber(rs.getString("CreditCardNumber"));
			card.setCcv(rs.getString("CCV"));
			card.setExpirationMonth(rs.getInt("ExpirationMonth"));
			card.setExpirationYear(rs.getInt("ExpirationYear"));
			String query = "SELECT * FROM users WHERE Id=" + rs.getInt("CustomerId");
			ResultSet rs2 = Database.runQuery(query);
			card.setOwner(UserDB.createUser(rs2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return card;
	}
	
	public static CreditCard getCreditCard(String cardNumber) {
		String query = "SELECT * FROM creditCardBuildings WHERE Name=" + cardNumber;
		ResultSet rs = Database.runQuery(query);
		try {
			if(rs.next())
			{
			    return createCard(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean addCreditCard(CreditCard card) {
		String query = "SELECT Id FROM users WHERE FullName=" + card.getOwner().getFullName();
		ResultSet rs = Database.runQuery(query);
		int ownerId = -1;
		try {
			if(rs.next()) {
				ownerId = rs.getInt("Id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		query = "INSERT INTO users (CardHolderName, CreditCardNumber, Balance, CardType, UserId, CVV, ExpirationYear, ExpirationMonth)"
				     + "VALUES (?, ?, 0.00, ?, " + ownerId + ", ?, " + card.getExpirationYear() + "," + card.getExpirationMonth() + ")";
		ArrayList<String> params = new ArrayList<String>();
		params.add(card.getOwner().getFullName());
		params.add(card.getCardNumber());
		params.add(card.getCardType());
		params.add(card.getCcv());
		int i = Database.runUpdate(query, params);
		if(i == 1) {
		    return true;
		}
		return false;
	}
}
