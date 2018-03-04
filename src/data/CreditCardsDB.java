package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.CreditCard;

public class CreditCardsDB {
	
	public static CreditCard createCard(ResultSet rs) {
		CreditCard card = new CreditCard();
		try {
			card.setOwner(UserDB.getUserById(rs.getInt("OwnerId")));
			card.setCardType(rs.getString("CardType"));
			card.setCardNumber(rs.getString("CardNumber"));
			card.setCcv(rs.getInt("CCV"));
			card.setExpirationMonth(rs.getInt("ExpirationMonth"));
			card.setExpirationYear(rs.getInt("ExpirationYear"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return card;
	}
	
	public static CreditCard getCreditCard(String cardNumber) {
		String query = "SELECT * FROM CreditCard WHERE CardNumber=" + cardNumber;
		ResultSet rs = Database.runQuery(query);
		try {
			if(rs.next())
			{
				CreditCard card = createCard(rs);
				rs.close();
			    return card;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean addCreditCard(CreditCard card) {
		int ownerId = UserDB.getUserIdByEmailAddress(card.getOwner().getEmailAddress());
		//TODO TALK ABOUT WHERE THE BALANCE GOES SINCE THIS DOESNT MATCH THE SCHEMA
		String query = "INSERT INTO CreditCard (OwnerId, CardType, CardNumber, CCV, ExpirationMonth, ExpirationYear) "
				     + "VALUES (" + ownerId + ", ?, ?, " + card.getCcv() + ", " + card.getExpirationMonth() + ", " + card.getExpirationYear() + ");";
		ArrayList<String> params = new ArrayList<String>();
		params.add(card.getCardType());
		params.add(card.getCardNumber());
		int i = Database.runUpdate(query, params);
		if(i == 1) {
		    return true;
		}
		return false;
	}
}
