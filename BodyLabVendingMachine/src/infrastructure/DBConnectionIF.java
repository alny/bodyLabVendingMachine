package infrastructure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface DBConnectionIF {
	public Boolean recheckConnection();
	public void startTransaction() throws SQLException;
	public void commitTransaction() throws SQLException;
	public void rollbackTransaction() throws SQLException;
	public int executeInsertWithIdentity(PreparedStatement ps) throws SQLException;
	public int executeInsertWithIdentity(String sql) throws SQLException;
	public int executeUpdate(String sql) throws SQLException;
	public void disconnect();
	public Connection getConnection();
}
