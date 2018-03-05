package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.CreditCard;

public class CreditCardsDB {
	
	public static CreditCard createCard(ResultSet rs) {
		CreditCard card = new CreditCard();
		try {
			card.setCardType(rs.getString("CardType"));
			card.setCardNumber(rs.getString("CardNumber"));
			card.setCcv(rs.getInt("CCV"));
			card.setExpirationMonth(rs.getInt("ExpirationMonth"));
			card.setExpirationYear(rs.getInt("ExpirationYear"));
			
			String query = "SELECT * FROM User "
						 + "INNER JOIN Address on Address.ID=User.AddressId "
						 + "WHERE User.ID=?;";
			Connection c = Database.getConnection();
			PreparedStatement s = Database.prepareStatement(c, query);
			s.setInt(1, rs.getInt("OwnerId"));
			ResultSet rs2 = s.executeQuery();
			if(rs2.next()) {
				card.setOwner(UserDB.createUser(rs2));
			}
			rs2.close();
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return card;
	}
	
	public static CreditCard getCreditCard(String cardNumber) {
		String query = "SELECT * FROM CreditCard WHERE CardNumber=" + cardNumber + ";";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		try {
			ResultSet rs = s.executeQuery();
			if(rs.next())
			{
				CreditCard card = createCard(rs);
				rs.close();
			    return card;
			}
			rs.close();
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean addCreditCard(CreditCard card) {
		String query = "SELECT ID FROM User WHERE FullName=" + card.getOwner().getFullName() + ";";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		int ownerId = -1;
		try {
			ResultSet rs = s.executeQuery();
			if(rs.next()) {
				ownerId = rs.getInt("ID");
			}
			rs.close();
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		query = "INSERT INTO CreditCard (CardHolderName, CardNumber, Balance, CardType, UserId, CVV, ExpirationYear, ExpirationMonth)"
				     + "VALUES (?, ?, 0.00, ?, " + ownerId + ", " + card.getCcv() + ", " + card.getExpirationYear() + "," + card.getExpirationMonth() + ");";
		ArrayList<String> params = new ArrayList<String>();
		params.add(card.getOwner().getFullName());
		params.add(card.getCardNumber());
		params.add(card.getCardType());
		int i = Database.runUpdate(query, params);
		if(i == 1) {
		    return true;
		}
		return false;
	}

	public static boolean updateBalance(CreditCard card) {
		String query = "UPDATE CreditCard SET Balance = " + card.getBalance() + " WHERE CardHolderName=?;";
		ArrayList<String> params = new ArrayList<String>();
		params.add(card.getOwner().getFullName());
		int i = Database.runUpdate(query, params);
		if(i == 1) {
			return true;
		}
		return false;
	}
	
	public static boolean verifyCard(CreditCard card) {
		CreditCard dataBaseCard = getCreditCard(card.getCardNumber());
		if(dataBaseCard.equals(card)) {
			return true;
		}
		return false;
	}
}
