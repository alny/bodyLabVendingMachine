package infrastructure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface DBConnectionIF {
	public Boolean recheckConnection();
}
