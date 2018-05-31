package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import infrastructure.DBSaleIF;
import model.Product;
import model.Sale;
import model.VendingMachine;

public class DBSale implements DBSaleIF {
	
	// Klasse variabel
	private static DBSale instance;
	// Instans variabel
	private Connection connection;
	
	// Konstruktøren er privat for at sikre at kun en instans af klassen oprettes
	private DBSale() {
		connection = DBConnection.getInstance().getConnection();
	}
	
	// Metoden returnerer eller opretter en instans af klassen alt efter om den eksisterer
	public static DBSale getInstance() {
		if (instance == null) {
			instance = new DBSale();
		}
		return instance;
	}
	
	// public metode der indsætter et salg i databasen, som tager et salgs objekt som parameter
	@Override
	public int insertSale(Sale sale) throws PersistensException {
		// Lokal variabel af typen int, instantieres til 0
		int id = 0;
		// Lokal variabel af typen String er sat til final da den indeholder embbedded sql og derfor kun må tildeles en gang
		final String insert = "insert into Sale (vendingMachineId, productId, salesPrice)" + " values (?,?,?)";
		final String changeQuantity = "update MachineProduct set qty = qty - 1 where productId = ? and vendingMachineId = ?";
		// try/catch blok bruges til at indkapsle den kode der kan kaste en exception 
		try {
			// transaktion startes
			DBConnection.getInstance().startTransaction();
			PreparedStatement insertPS = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement updateQty = connection.prepareStatement(changeQuantity);
			insertPS.setInt(1, sale.getVendingmachine().getId());
			
			insertPS.setInt(2, sale.getProduct().getId());

			insertPS.setFloat(3, sale.getPrice());
			
			// Her eksekveres sql statementet og returnerer et id fra forespørgslen
			id = DBConnection.getInstance().executeInsertWithIdentity(insertPS);
			

			updateQty.setInt(1, sale.getProduct().getId());
			updateQty.setInt(2, sale.getVendingmachine().getId());
			updateQty.executeUpdate();
			// transaktion committes da alt gik som det skulle
			DBConnection.getInstance().commitTransaction();
		} catch (SQLException e) {
			try {
				// transaktion rollbackes hvis noget skulle gå galt
				DBConnection.getInstance().rollbackTransaction();
				// Her fanges de undtagelser der kan opstå i metoden
				PersistensException pe = new PersistensException(e, "Salg kunne ikke gennemføres rollback");
				throw pe;
			} catch (SQLException e1) {
				PersistensException pe = new PersistensException(e, "rollback kunne ikke gennemføres");
				throw pe;
			}
		}
		return id;
	}
}
