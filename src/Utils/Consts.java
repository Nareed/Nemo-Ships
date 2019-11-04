package Utils;

public final class Consts {
	private Consts() {
		throw new AssertionError();
	}

	public static String getConnectionurl() {
		return connectionUrl;
	}

	private static final String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=master;user=nemo_ships;password=fadiandnareed";

}