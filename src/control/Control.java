package control;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import Utils.Consts;
import Utils.E_RoomType;
import entity.BookObject;
import entity.Country;
import entity.Cruise;
import entity.CruiseOrder;
import entity.CruiseShip;
import entity.HistoryObject;
import entity.OfferObject;
import entity.Person;
import entity.Port;
import entity.Room;
import entity.SailTo;
import entity.Sailing;
import entity.popularDest;

public class Control {

	private static Control instance;

	private Control() {
	}

	public static Control getInstance() {
		if (instance == null)
			instance = new Control();
		return instance;
	}

	// ------------------- PERSON -------------------

	public ArrayList<Person> selectPerson() {
		ArrayList<Person> results = new ArrayList<Person>();
		try {
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery("{call dbo.selectPerson}")) {
					while (resultSet.next()) {

						results.add(new Person(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
								resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6),
								resultSet.getString(7)));
					}
				}
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public boolean insertPerson(Person p) {
		boolean b = false;
		try {
			// Load SQL Server JDBC driver and establish connection.
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {
				try (PreparedStatement statement = connection
						.prepareStatement("{call dbo.insertPerson(?,?,?,?,?,?,?)}")) {

					statement.setString(1, p.getPersonID());
					statement.setString(2, p.getFirstName());
					statement.setString(3, p.getSurName());
					statement.setDate(4, p.getDateOfBirth());
					statement.setString(5, p.getPhone());
					statement.setString(6, p.getEmail());
					statement.setString(7, p.getPassword());
					int rowsAffected = statement.executeUpdate();
					b = (rowsAffected > 0) ? true : false;
				}

				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	public boolean updatePerson(Person p) {
		boolean b = false;
		try {
			// Load SQL Server JDBC driver and establish connection.
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {
				try (PreparedStatement statement = connection
						.prepareStatement("{call dbo.updatePerson(?,?,?,?,?,?,?)}")) {

					statement.setString(1, p.getPersonID());
					statement.setString(2, p.getFirstName());
					statement.setString(3, p.getSurName());
					statement.setDate(4, p.getDateOfBirth());
					statement.setString(5, p.getPhone());
					statement.setString(6, p.getEmail());
					statement.setString(7, p.getPassword());
					int rowsAffected = statement.executeUpdate();
					b = (rowsAffected > 0) ? true : false;
				}

				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	// ------------------- SAILING -------------------

	public ArrayList<Sailing> selectSailing() {
		ArrayList<Sailing> results = new ArrayList<Sailing>();
		try {
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery("{call dbo.selectSailing}")) {
					while (resultSet.next()) {
						results.add(new Sailing(resultSet.getInt(1), resultSet.getDate(2), resultSet.getDate(3)));
					}
				}
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public ArrayList<Integer> selectSailingIDS() {
		ArrayList<Integer> i = new ArrayList<>();
		ArrayList<Sailing> s = selectSailing();
		for (Sailing sailing : s) {
			i.add(sailing.getSailingID());
		}
		return i;
	}

	public boolean insertSailing(Sailing p) {
		boolean b = false;
		try {
			// Load SQL Server JDBC driver and establish connection.
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {
				try (PreparedStatement statement = connection.prepareStatement("{call dbo.insertSailing(?,?,?)}")) {
					statement.setInt(1, p.getSailingID());
					statement.setDate(2, p.getLeavingTime());
					statement.setDate(3, p.getReturnTime());
					int rowsAffected = statement.executeUpdate();
					b = (rowsAffected > 0) ? true : false;
				}

				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	public boolean updateSailing(Sailing p) {
		boolean b = false;
		try {
			// Load SQL Server JDBC driver and establish connection.
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {
				try (PreparedStatement statement = connection.prepareStatement("{call dbo.updateSailing(?,?,?)}")) {
					statement.setInt(1, p.getSailingID());
					statement.setDate(2, p.getLeavingTime());
					statement.setDate(3, p.getReturnTime());
					int rowsAffected = statement.executeUpdate();
					b = (rowsAffected > 0) ? true : false;
				}

				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	// ------------------- CRUISE_SHIP -------------------

	public ArrayList<CruiseShip> selectCruiseShip() {
		ArrayList<CruiseShip> results = new ArrayList<CruiseShip>();
		try {
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery("{call dbo.selectCruiseShip}")) {
					while (resultSet.next()) {
						results.add(new CruiseShip(resultSet.getInt(1), resultSet.getString(2), resultSet.getDate(3),
								resultSet.getInt(4), resultSet.getInt(5)));
					}
				}
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public boolean insertCruiseShip(CruiseShip p) {
		boolean b = false;
		try {
			// Load SQL Server JDBC driver and establish connection.
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {
				try (PreparedStatement statement = connection
						.prepareStatement("{call dbo.insertCruiseShip(?,?,?,?,?)}")) {
					statement.setInt(1, p.getCruiseShipID());
					statement.setString(2, p.getShipName());
					statement.setDate(3, p.getManufacturingDate());
					statement.setInt(4, p.getMaxCapacity());
					statement.setInt(5, p.getMaxNumberOfPeople());
					int rowsAffected = statement.executeUpdate();
					b = (rowsAffected > 0) ? true : false;
				}

				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	public boolean updateCruiseShip(CruiseShip p) {
		boolean b = false;
		try {
			// Load SQL Server JDBC driver and establish connection.
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {
				try (PreparedStatement statement = connection
						.prepareStatement("{call dbo.updateCruiseShip(?,?,?,?,?)}")) {
					statement.setInt(1, p.getCruiseShipID());
					statement.setString(2, p.getShipName());
					statement.setDate(3, p.getManufacturingDate());
					statement.setInt(4, p.getMaxCapacity());
					statement.setInt(5, p.getMaxNumberOfPeople());
					int rowsAffected = statement.executeUpdate();
					b = (rowsAffected > 0) ? true : false;
				}

				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	// ------------------- PORT -------------------

	public ArrayList<Port> selectPort() {
		ArrayList<Port> results = new ArrayList<Port>();
		try {
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery("{call dbo.selectPort}")) {
					while (resultSet.next()) {
						results.add(new Port(resultSet.getString(1), resultSet.getString(2)));
					}
				}
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public boolean insertPort(Port p) {
		boolean b = false;
		try {
			// Load SQL Server JDBC driver and establish connection.
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {

				try (PreparedStatement statement = connection.prepareStatement("{call dbo.insertPort(?,?)}")) {
					statement.setString(1, p.getCountryName());
					statement.setString(2, p.getPortName());
					int rowsAffected = statement.executeUpdate();
					b = (rowsAffected > 0) ? true : false;
				}

				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	// ------------------- COUNTRY -------------------

	public ArrayList<Country> selectCountry() {
		ArrayList<Country> results = new ArrayList<Country>();
		try {
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery("{call dbo.selectCountry}")) {
					while (resultSet.next()) {
						results.add(new Country(resultSet.getString(1)));
					}
				}
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public boolean insertCountry(Country p) {
		boolean b = false;
		try {
			// Load SQL Server JDBC driver and establish connection.
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {

				try (PreparedStatement statement = connection.prepareStatement("{call dbo.insertCountry(?)}")) {
					statement.setString(1, p.getCountryName());
					int rowsAffected = statement.executeUpdate();
					b = (rowsAffected > 0) ? true : false;
				}

				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	// ------------------- CRUISE_ORDER -------------------

	public ArrayList<CruiseOrder> selectCruiseOrder() {
		ArrayList<CruiseOrder> results = new ArrayList<CruiseOrder>();
		try {
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery("{call dbo.selectCruiseOrder}")) {
					while (resultSet.next()) {
						results.add(new CruiseOrder(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
								resultSet.getString(4)));
					}
				}
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public boolean insertCruiseOrder(CruiseOrder p) {
		boolean b = false;
		try {
			// Load SQL Server JDBC driver and establish connection.
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {
				try (PreparedStatement statement = connection
						.prepareStatement("{call dbo.insertCruiseOrder(?,?,?,?)}")) {
					statement.setInt(1, p.getCruiseID());
					statement.setInt(2, p.getCruiseShipID());
					statement.setInt(3, p.getRoomNumber());
					statement.setString(4, p.getPersonID());
					int rowsAffected = statement.executeUpdate();
					b = (rowsAffected > 0) ? true : false;
				}

				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	// ------------------- ROOM -------------------

	public ArrayList<Room> selectRoom() {
		ArrayList<Room> results = new ArrayList<Room>();
		try {
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery("{call dbo.selectRoom}")) {
					while (resultSet.next()) {
						results.add(
								new Room(resultSet.getInt(1), resultSet.getInt(2),
										(resultSet.getString(3) == null) ? "-" : resultSet.getString(3),
										(resultSet.getString(4) == null) ? E_RoomType.none
												: E_RoomType.valueOf(resultSet.getString(4).toString()),
										resultSet.getInt(5)));
					}
				}
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public boolean insertRoom(Room p) {
		boolean b = false;
		try {
			// Load SQL Server JDBC driver and establish connection.
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {

				try (PreparedStatement statement = connection.prepareStatement("{call dbo.insertRoom(?,?,?,?,?)}")) {
					statement.setInt(1, p.getCruiseShipID());
					statement.setInt(2, p.getRoomNumber());
					statement.setString(3, p.getBedsAmount());
					statement.setString(4, new String(p.getRoomType().toString()));
					statement.setInt(5, p.getPrice());

					int rowsAffected = statement.executeUpdate();
					b = (rowsAffected > 0) ? true : false;
				}

				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	public boolean updateRoom(Room p) {
		boolean b = false;
		try {
			// Load SQL Server JDBC driver and establish connection.
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {

				try (PreparedStatement statement = connection.prepareStatement("{call dbo.updateRoom(?,?,?,?,?)}")) {
					statement.setInt(1, p.getCruiseShipID());
					statement.setInt(2, p.getRoomNumber());
					statement.setString(3, p.getBedsAmount());
					statement.setString(4, new String(p.getRoomType().toString()));
					statement.setInt(5, p.getPrice());

					int rowsAffected = statement.executeUpdate();
					b = (rowsAffected > 0) ? true : false;
				}

				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	// ------------------- SAIL_TO -------------------

	public ArrayList<SailTo> selectSailTo() {
		ArrayList<SailTo> results = new ArrayList<SailTo>();
		try {
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {

				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery("{call dbo.selectSailTo}")) {
					while (resultSet.next()) {
						results.add(new SailTo(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3),
								resultSet.getDate(4), resultSet.getDate(5)));
					}
				}
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public boolean insertSailTo(SailTo p) {
		boolean b = false;
		try {
			// Load SQL Server JDBC driver and establish connection.
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {

				try (PreparedStatement statement = connection.prepareStatement("{call dbo.insertSailTo(?,?,?,?,?)}")) {
					statement.setString(1, p.getCountryName());
					statement.setString(2, p.getPortName());
					statement.setInt(3, p.getSailingID());
					statement.setDate(4, p.getArrivalTime());
					statement.setDate(5, p.getLeavingTime());

					int rowsAffected = statement.executeUpdate();
					b = (rowsAffected > 0) ? true : false;
				}

				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	// ------------------- CRUISE -------------------

	public ArrayList<Cruise> selectCruise() {
		ArrayList<Cruise> results = new ArrayList<Cruise>();
		try {
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {

				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery("{call dbo.selectCruise}")) {
					while (resultSet.next()) {
						results.add(new Cruise(resultSet.getInt(1), resultSet.getInt(2)));
					}
				}
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public boolean insertCruise(Cruise p) {
		boolean b = false;
		try {
			// Load SQL Server JDBC driver and establish connection.
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {
				try (PreparedStatement statement = connection.prepareStatement("{call dbo.insertCruise(?,?)}")) {
					statement.setInt(1, p.getCruiseID());
					statement.setInt(2, p.getCruiseShipID());

					int rowsAffected = statement.executeUpdate();
					b = (rowsAffected > 0) ? true : false;
				}

				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	// ------------------- MAIN_PAGE_QUERIES -------------------

	public int getNumOfCountriesVisited(String personID) {
		CallableStatement cstmt = null;
		ResultSet rs = null;
		int c = -1;

		try {
			Connection connection = DriverManager.getConnection(Consts.getConnectionurl());
			cstmt = connection.prepareCall("{call dbo.getNumOfCountriesVisited(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			cstmt.setString("personID", personID);
			boolean results = cstmt.execute();
			int rowsAffected = 0;

			// Protects against lack of SET NOCOUNT in stored prodedure
			while (results || rowsAffected != -1) {
				if (results) {
					rs = cstmt.getResultSet();
					break;
				} else {
					rowsAffected = cstmt.getUpdateCount();
				}
				results = cstmt.getMoreResults();
			}
			while (rs.next()) {
				c = rs.getInt("num");
			}
		} catch (Exception ex) {

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();

				}
			}
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();

				}
			}
		}
		return c;
	}

	public int getNumOfPortsLanded(String personID) {
		CallableStatement cstmt = null;
		ResultSet rs = null;
		int c = -1;

		try {
			Connection connection = DriverManager.getConnection(Consts.getConnectionurl());
			cstmt = connection.prepareCall("{call dbo.getNumOfPortsLanded(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			cstmt.setString("personID", personID);
			boolean results = cstmt.execute();
			int rowsAffected = 0;

			// Protects against lack of SET NOCOUNT in stored prodedure
			while (results || rowsAffected != -1) {
				if (results) {
					rs = cstmt.getResultSet();
					break;
				} else {
					rowsAffected = cstmt.getUpdateCount();
				}
				results = cstmt.getMoreResults();
			}
			while (rs.next()) {
				c = rs.getInt("num");
			}
		} catch (Exception ex) {

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();

				}
			}
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();

				}
			}
		}
		return c;
	}

	public int getNumOfPastOrders(String personID) {
		CallableStatement cstmt = null;
		ResultSet rs = null;
		int c = -1;

		try {
			Connection connection = DriverManager.getConnection(Consts.getConnectionurl());
			cstmt = connection.prepareCall("{call dbo.getNumOfPastOrders(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			cstmt.setString("personID", personID);
			boolean results = cstmt.execute();
			int rowsAffected = 0;

			// Protects against lack of SET NOCOUNT in stored prodedure
			while (results || rowsAffected != -1) {
				if (results) {
					rs = cstmt.getResultSet();
					break;
				} else {
					rowsAffected = cstmt.getUpdateCount();
				}
				results = cstmt.getMoreResults();
			}
			while (rs.next()) {
				c = rs.getInt("num");
			}
		} catch (Exception ex) {

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();

				}
			}
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();

				}
			}
		}
		return c;
	}

	public HashMap<String, Double> getMostVisitedCountries() {
		HashMap<String, Double> hm = new HashMap<>();

		CallableStatement cstmt = null;
		ResultSet rs = null;
		try {
			Connection connection = DriverManager.getConnection(Consts.getConnectionurl());
			cstmt = connection.prepareCall("{call dbo.getMostVisitedCities}");
			cstmt.execute();
			rs = cstmt.getResultSet();
			while (rs.next()) {
				int j = 1;
				while (j <= 2) {
					hm.put(rs.getString(j++), rs.getDouble(j++));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return hm;

	}

	public HashMap<String, Integer> getMostPopularShips() {
		HashMap<String, Integer> hm = new HashMap<>();

		CallableStatement cstmt = null;
		ResultSet rs = null;
		try {
			Connection connection = DriverManager.getConnection(Consts.getConnectionurl());
			cstmt = connection.prepareCall("{call dbo.getMostPopularShips}");
			cstmt.execute();
			rs = cstmt.getResultSet();
			while (rs.next()) {
				int j = 1;
				while (j <= 2) {
					hm.put(rs.getString(j++), rs.getInt(j++));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return hm;

	}

	public HashMap<String, Double> getAmountPayed(String personID) {
		HashMap<String, Double> hm = new HashMap<>();
		CallableStatement cstmt = null;
		ResultSet rs = null;
		try {
			Connection connection = DriverManager.getConnection(Consts.getConnectionurl());
			cstmt = connection.prepareCall("{call dbo.getAmountPayed(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			cstmt.setString("personID", personID);
			boolean results = cstmt.execute();
			int rowsAffected = 0;

			// Protects against lack of SET NOCOUNT in stored prodedure
			while (results || rowsAffected != -1) {
				if (results) {
					rs = cstmt.getResultSet();
					break;
				} else {
					rowsAffected = cstmt.getUpdateCount();
				}
				results = cstmt.getMoreResults();
			}

			while (rs.next()) {
				int j = 1;
				while (j <= 2) {
					hm.put(rs.getString(j++), rs.getDouble(j++));
				}
			}
		} catch (Exception ex) {

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();

				}
			}
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();

				}
			}
		}
		return hm;
	}

	public ArrayList<HistoryObject> getPastOrdersByPerson(String personID) {
		ArrayList<HistoryObject> historyObj = new ArrayList<HistoryObject>();
		CallableStatement cstmt = null;
		ResultSet rs = null;
		try {
			Connection connection = DriverManager.getConnection(Consts.getConnectionurl());
			cstmt = connection.prepareCall("{call dbo.getPastOrdersByPerson(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			cstmt.setString("personID", personID);
			boolean results = cstmt.execute();
			int rowsAffected = 0;

			// Protects against lack of SET NOCOUNT in stored prodedure
			while (results || rowsAffected != -1) {
				if (results) {
					rs = cstmt.getResultSet();
					break;
				} else {
					rowsAffected = cstmt.getUpdateCount();
				}
				results = cstmt.getMoreResults();
			}

			while (rs.next()) {
				int j = 1;
				while (j <= 2) {
					historyObj.add(new HistoryObject(rs.getDate(j++), rs.getDate(j++), rs.getString(j++),
							rs.getInt(j++), rs.getDouble(j++)));
				}
			}
		} catch (Exception ex) {

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();

				}
			}
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();

				}
			}
		}
		return historyObj;
	}

	public boolean updatePassword(String personID, String password) {
		boolean b = false;
		try {
			Connection connection = DriverManager.getConnection(Consts.getConnectionurl());
			CallableStatement cs = connection.prepareCall("{call dbo.updatePassword(?,?)}");

			cs.setString("personID", personID);
			cs.setString("password", password);

			int rowsAffected = cs.executeUpdate();
			b = (rowsAffected > 0) ? true : false;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	public boolean updateEmail(String personID, String email) {
		boolean b = false;
		try {
			Connection connection = DriverManager.getConnection(Consts.getConnectionurl());
			CallableStatement cs = connection.prepareCall("{call dbo.updateEmail(?,?)}");

			cs.setString("personID", personID);
			cs.setString("email", email);

			int rowsAffected = cs.executeUpdate();
			b = (rowsAffected > 0) ? true : false;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	public boolean updatePhone(String personID, String phone) {
		boolean b = false;
		try {
			Connection connection = DriverManager.getConnection(Consts.getConnectionurl());
			CallableStatement cs = connection.prepareCall("{call dbo.updatePhone(?,?)}");

			cs.setString("personID", personID);
			cs.setString("phone", phone);

			int rowsAffected = cs.executeUpdate();
			b = (rowsAffected > 0) ? true : false;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	public ArrayList<OfferObject> getOffers() {
		ArrayList<OfferObject> offers = new ArrayList<OfferObject>();
		CallableStatement cstmt = null;
		ResultSet rs = null;

		try {
			Connection connection = DriverManager.getConnection(Consts.getConnectionurl());
			cstmt = connection.prepareCall("{call dbo.tempReturnObjects}", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			boolean results = cstmt.execute();
			int rowsAffected = 0;

			while (results || rowsAffected != -1) {
				if (results) {
					rs = cstmt.getResultSet();
					break;
				} else {
					rowsAffected = cstmt.getUpdateCount();
				}
				results = cstmt.getMoreResults();
			}

			while (rs.next()) {
				int j = 1;
				while (j <= 2) {
					offers.add(new OfferObject(rs.getString(j++), rs.getString(j++), rs.getString(j++),
							rs.getString(j++), rs.getString(j++), rs.getInt(j++), rs.getInt(j++)));

				}
			}
		} catch (Exception ex) {

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();

				}
			}
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();

				}
			}
		}
		return offers;
	}

	public ArrayList<OfferObject> refineOffersByMonth(ArrayList<String> montharr) {

		Set<OfferObject> offers = new HashSet<OfferObject>();
		CallableStatement cstmt = null;
		ResultSet rs = null;

		for (String m : montharr) {
			try {
				Connection connection = DriverManager.getConnection(Consts.getConnectionurl());
				cstmt = connection.prepareCall("{call dbo.try1(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				cstmt.setString("month", m);
				boolean results = cstmt.execute();
				int rowsAffected = 0;

				// Protects against lack of SET NOCOUNT in stored prodedure
				while (results || rowsAffected != -1) {
					if (results) {
						rs = cstmt.getResultSet();
						break;
					} else {
						rowsAffected = cstmt.getUpdateCount();
					}
					results = cstmt.getMoreResults();
				}

				while (rs.next()) {
					int j = 1;
					while (j <= 2) {
						offers.add(new OfferObject(rs.getString(j++), rs.getString(j++), rs.getString(j++),
								rs.getString(j++), rs.getString(j++), rs.getInt(j++), rs.getInt(j++)));

					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		ArrayList<OfferObject> arralist = new ArrayList<>();
		arralist.addAll(offers);
		return arralist;
	}

	public ArrayList<OfferObject> refineOffersByNights(ArrayList<Integer> nightsarr) {
		Set<OfferObject> offers = new HashSet<OfferObject>();
		CallableStatement cstmt = null;
		ResultSet rs = null;

		for (Integer m : nightsarr) {
			try {
				Connection connection = DriverManager.getConnection(Consts.getConnectionurl());
				cstmt = connection.prepareCall("{call dbo.try2(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				cstmt.setInt(1, m);
				boolean results = cstmt.execute();
				int rowsAffected = 0;

				// Protects against lack of SET NOCOUNT in stored prodedure
				while (results || rowsAffected != -1) {
					if (results) {
						rs = cstmt.getResultSet();
						break;
					} else {
						rowsAffected = cstmt.getUpdateCount();
					}
					results = cstmt.getMoreResults();
				}

				while (rs.next()) {
					int j = 1;
					while (j <= 2) {
						offers.add(new OfferObject(rs.getString(j++), rs.getString(j++), rs.getString(j++),
								rs.getString(j++), rs.getString(j++), rs.getInt(j++), rs.getInt(j++)));

					}
				}
			} catch (Exception ex) {

			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException ex) {
						ex.printStackTrace();

					}
				}
				if (cstmt != null) {
					try {
						cstmt.close();
					} catch (SQLException ex) {
						ex.printStackTrace();

					}
				}
			}
		}
		ArrayList<OfferObject> arralist = new ArrayList<>();
		arralist.addAll(offers);
		return arralist;
	}

	public BookObject getBook(int sailingID) {
		BookObject b = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		try {
			Connection connection = DriverManager.getConnection(Consts.getConnectionurl());
			cstmt = connection.prepareCall("{call dbo.getBook(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			cstmt.setInt(1, sailingID);
			boolean results = cstmt.execute();
			int rowsAffected = 0;

			// Protects against lack of SET NOCOUNT in stored prodedure
			while (results || rowsAffected != -1) {
				if (results) {
					rs = cstmt.getResultSet();
					break;
				} else {
					rowsAffected = cstmt.getUpdateCount();
				}
				results = cstmt.getMoreResults();
			}

			while (rs.next()) {
				int j = 1;
				while (j <= 2) {
					b = new BookObject(rs.getInt(j++), rs.getString(j++), rs.getInt(j++));
				}
			}
		} catch (Exception ex) {

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();

				}
			}
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();

				}
			}
		}

		return b;
	}

	public String[] getCountriesInSailing(int sailingid) {

		BookObject b = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		ArrayList<String> s = new ArrayList<>();

		try {
			Connection connection = DriverManager.getConnection(Consts.getConnectionurl());
			cstmt = connection.prepareCall("{call dbo.getCountriesInSailing(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			cstmt.setInt(1, sailingid);
			boolean results = cstmt.execute();
			int rowsAffected = 0;

			// Protects against lack of SET NOCOUNT in stored prodedure
			while (results || rowsAffected != -1) {
				if (results) {
					rs = cstmt.getResultSet();
					break;
				} else {
					rowsAffected = cstmt.getUpdateCount();
				}
				results = cstmt.getMoreResults();
			}

			while (rs.next()) {
				int j = 1;
				s.add(rs.getString(j++));

			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		String[] sArr = new String[5];
		for (int k = 0; k < s.size() && k < sArr.length && k < s.size(); k++) {
			sArr[k] = s.get(k);
		}

		return sArr;
	}

	public ArrayList<Room> getBookTable(int SailingID) {
		BookObject b = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		ArrayList<Room> s = new ArrayList<>();

		try {
			Connection connection = DriverManager.getConnection(Consts.getConnectionurl());
			cstmt = connection.prepareCall("{call dbo.getBookTable(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			cstmt.setInt(1, SailingID);
			boolean results = cstmt.execute();
			int rowsAffected = 0;

			// Protects against lack of SET NOCOUNT in stored prodedure
			while (results || rowsAffected != -1) {
				if (results) {
					rs = cstmt.getResultSet();
					break;
				} else {
					rowsAffected = cstmt.getUpdateCount();
				}
				results = cstmt.getMoreResults();
			}

			while (rs.next()) {
				int j = 1;
				s.add(new Room(rs.getInt(j++), rs.getInt(j++), rs.getString(j++),
						(E_RoomType.valueOf(rs.getString(j++))), rs.getInt(j++)));
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		/*
		 * String[] sArr = new String[5]; for (int k = 0; k < s.size() && k <
		 * sArr.length && k < s.size(); k++) { sArr[k] = s.get(k); }
		 */

		return s;
	}

	public HashMap<Integer, Double> profitFromStandard(int givenYear) {
		BookObject b = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		ArrayList<Room> s = new ArrayList<>();
		HashMap<Integer, Double> hm = new HashMap<>();

		try {
			Connection connection = DriverManager.getConnection(Consts.getConnectionurl());
			cstmt = connection.prepareCall("{call dbo.profitFromStandard(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			cstmt.setInt(1, givenYear);
			boolean results = cstmt.execute();
			int rowsAffected = 0;

			// Protects against lack of SET NOCOUNT in stored prodedure
			while (results || rowsAffected != -1) {
				if (results) {
					rs = cstmt.getResultSet();
					break;
				} else {
					rowsAffected = cstmt.getUpdateCount();
				}
				results = cstmt.getMoreResults();
			}

			while (rs.next()) {
				int j = 1;
				hm.put(rs.getInt(j++), rs.getDouble(j++));

			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		/*
		 * String[] sArr = new String[5]; for (int k = 0; k < s.size() && k <
		 * sArr.length && k < s.size(); k++) { sArr[k] = s.get(k); }
		 */

		return hm;
	}

	public HashMap<Integer, Double> profitFromSuite(int givenYear) {
		BookObject b = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		ArrayList<Room> s = new ArrayList<>();
		HashMap<Integer, Double> hm = new HashMap<>();

		try {
			Connection connection = DriverManager.getConnection(Consts.getConnectionurl());
			cstmt = connection.prepareCall("{call dbo.profitFromSuite(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			cstmt.setInt(1, givenYear);
			boolean results = cstmt.execute();
			int rowsAffected = 0;

			// Protects against lack of SET NOCOUNT in stored prodedure
			while (results || rowsAffected != -1) {
				if (results) {
					rs = cstmt.getResultSet();
					break;
				} else {
					rowsAffected = cstmt.getUpdateCount();
				}
				results = cstmt.getMoreResults();
			}

			while (rs.next()) {
				int j = 1;
				hm.put(rs.getInt(j++), rs.getDouble(j++));

			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		/*
		 * String[] sArr = new String[5]; for (int k = 0; k < s.size() && k <
		 * sArr.length && k < s.size(); k++) { sArr[k] = s.get(k); }
		 */

		return hm;
	}

	public int numOfUsers() {
		int c = -1;
		try {
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {

				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery("{call dbo.numOfUsers}")) {
					while (resultSet.next()) {
						c = resultSet.getInt(1);
					}
				}
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	public int numberOfOrders() {
		int c = -1;
		try {
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {

				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery("{call dbo.numberOfOrders}")) {
					while (resultSet.next()) {
						c = resultSet.getInt(1);
					}
				}
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	public int getIncome() {
		int c = -1;
		try {
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {

				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery("{call dbo.getIncome}")) {
					while (resultSet.next()) {
						c = resultSet.getInt(1);
					}
				}
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	public HashMap<Integer, ArrayList<entity.popularDest>> popularDest() {
		int year = (new java.util.Date()).getYear() + 1900;
		ArrayList<popularDest> pd = new ArrayList<>();
		HashMap<Integer, ArrayList<popularDest>> hm = new HashMap<>();
		for (int i = 0; i < 5; i++) {
			hm.put(year, new ArrayList<popularDest>());
			year = year - 1;
		}
		try {
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {

				try (Statement statement = connection.createStatement();
						ResultSet rs = statement.executeQuery("{call dbo.popularDest}")) {
					while (rs.next()) {
						ArrayList<entity.popularDest> arr = hm.get(rs.getInt(1));
						arr.add(new popularDest(rs.getString(2), rs.getString(3), rs.getInt(4)));
						hm.put(rs.getInt(1), arr);
					}
				}
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hm;
	}

	public int VIPcustomer() {
		int num = 0;

		try {
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {

				try (Statement statement = connection.createStatement();
						ResultSet rs = statement.executeQuery("{call dbo.VIPcustomer}")) {
					while (rs.next()) {
						num = rs.getInt(1);
					}
				}
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}

	public ArrayList<String> popPorts() {
		ArrayList<String> a = new ArrayList<>();

		try {
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {

				try (Statement statement = connection.createStatement();
						ResultSet rs = statement.executeQuery("{call dbo.Ports}")) {
					while (rs.next()) {
						a.add(rs.getString(1));
					}
				}
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	public HashMap<Integer, HashMap<Integer, Double>> roomCongestion() {
		HashMap<Integer, HashMap<Integer, Double>> a = new HashMap<>();
		int year = new java.util.Date().getYear() + 1900;

		for (int i = 0; i < 5; i++) {
			HashMap<Integer, Double> innerHM = new HashMap<>();
			for (int j = 1; j <= 12; j++) {
				innerHM.put(j, 0.0);
			}
			a.put(year, innerHM);
			year--;
		}
		try {
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {

				try (Statement statement = connection.createStatement();
						ResultSet rs = statement.executeQuery("{call dbo.roomCongestion}")) {
					while (rs.next()) {
						HashMap<Integer, Double> innerHM = new HashMap<>();
						innerHM.put(rs.getInt(2), rs.getDouble(3));
						a.put(rs.getInt(1), innerHM);
					}
				}
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	public Boolean deletePerson(String personID) {
		boolean b = false;
		try {
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {
				try (PreparedStatement statement = connection.prepareStatement("{call deletePerson(?)}")) {
					statement.setString(1, personID);
					int rowsAffected = statement.executeUpdate();
					b = (rowsAffected > 0) ? true : false;
				}

				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;

	}

	public Boolean deleteCruiseOrder(int cruiseID, int shipID, int roomNum, String personID) {
		boolean b = false;
		try {
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {
				try (PreparedStatement statement = connection.prepareStatement("{call deleteCruiseOrder(?,?,?,?)}")) {
					statement.setInt(1, shipID);
					statement.setInt(2, cruiseID);
					statement.setInt(3, roomNum);
					statement.setString(4, personID);
					int rowsAffected = statement.executeUpdate();
					b = (rowsAffected > 0) ? true : false;
				}

				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;

	}

	public Boolean updateCruiseOrder(CruiseOrder p) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean deleteSailing(int parseInt) {

		boolean b = false;
		try {
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {
				try (PreparedStatement statement = connection.prepareStatement("{call deleteSailing(?)}")) {
					statement.setInt(1, parseInt);

					int rowsAffected = statement.executeUpdate();
					b = (rowsAffected > 0) ? true : false;
				}

				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;

	}

	public Boolean deleteCruise(int a, int c) {

		boolean b = false;
		try {
			try (Connection connection = DriverManager.getConnection(Consts.getConnectionurl())) {
				try (PreparedStatement statement = connection.prepareStatement("{call deleteCruise(?,?)}")) {
					statement.setInt(1, c);
					statement.setInt(2, a);

					int rowsAffected = statement.executeUpdate();
					b = (rowsAffected > 0) ? true : false;
				}

				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;

	}

}