package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.CreditCard;

public class CreditCardsDB {
	
	public static CreditCard createCard(ResultSet rs, Connection c) {
		CreditCard card = new CreditCard();
		try {
			card.setID(rs.getInt("ID"));
			card.setCardType(rs.getString("CardType"));
			card.setCardNumber(rs.getString("CardNumber"));
			card.setCcv(rs.getInt("CCV"));
			card.setBalance(rs.getDouble("Balance"));
			card.setExpirationMonth(rs.getInt("ExpirationMonth"));
			card.setExpirationYear(rs.getInt("ExpirationYear"));
			
			String query = "SELECT * FROM User "
						 + "INNER JOIN Address on Address.ID=User.AddressId "
						 + "WHERE User.ID=?;";
			
			PreparedStatement s = Database.prepareStatement(c, query);
			s.setInt(1, rs.getInt("OwnerId"));
			ResultSet rs2 = s.executeQuery();
			if(rs2.next()) {
				card.setOwner(UserDB.createUser(rs2));
			}
			rs2.close();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return card;
	}
	
	public static CreditCard createCard(ResultSet rs) {
		CreditCard card = new CreditCard();
		try {
			card.setOwner(UserDB.getUserById(rs.getInt("OwnerId")));
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
		String query = "SELECT ID FROM User WHERE FullName=?;";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		int ownerId = -1;
		try {
			s.setString(1, card.getOwner().getFullName());
			ResultSet rs = s.executeQuery();
			if(rs.next()) {
				ownerId = rs.getInt("ID");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		query = "INSERT INTO CreditCard (OwnerId, CardType, CardNumber, CCV, ExpirationMonth, ExpirationYear, Balance)"
				     + "VALUES (?, ?, ?, ?, ?, ?, ?);";
		
		s = Database.prepareStatement(c, query);
		int i = -1;
		try {
			s.setInt(1, ownerId);
			s.setString(2, card.getCardType());
			s.setString(3, card.getCardNumber());
			s.setInt(4, card.getCcv());
			s.setInt(5, card.getExpirationMonth());
			s.setInt(6, card.getExpirationYear());
			s.setDouble(7, card.getBalance());
			i = s.executeUpdate();
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(i != -1) {
		    return true;
		}
		return false;
	}
	
	public static boolean updateBalance(CreditCard card, int ownerId) {
		String query = "UPDATE CreditCard SET Balance = ? WHERE OwnerId=?;";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		int i = -1;
		try {
			s.setDouble(1, card.getBalance());
			s.setInt(2, ownerId);
			i = s.executeUpdate();
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(i != -1) {
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

	public static ArrayList<CreditCard> getCreditCardsByUser(int userId) {
		String query = "SELECT * FROM CreditCard WHERE OwnerId=?;";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		ArrayList<CreditCard> cards = new ArrayList<CreditCard>();
		CreditCard card = null;
		try {
			s.setDouble(1, userId);
			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				card = createCard(rs);
				cards.add(card);
			}
			rs.close();
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cards;
		
	}
}
