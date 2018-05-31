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
	
	// Konstrukt�ren er privat for at sikre at kun en instans af klassen oprettes
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
	
	// public metode der inds�tter et salg i databasen, som tager et salgs objekt som parameter
	@Override
	public int insertSale(Sale sale) throws PersistensException {
		// Lokal variabel af typen int, instantieres til 0
		int id = 0;
		// Lokal variabel af typen String er sat til final da den indeholder embbedded sql og derfor kun m� tildeles en gang
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
			
			// Her eksekveres sql statementet og returnerer et id fra foresp�rgslen
			id = DBConnection.getInstance().executeInsertWithIdentity(insertPS);
			

			updateQty.setInt(1, sale.getProduct().getId());
			updateQty.setInt(2, sale.getVendingmachine().getId());
			updateQty.executeUpdate();
			// transaktion committes da alt gik som det skulle
			DBConnection.getInstance().commitTransaction();
		} catch (SQLException e) {
			try {
				// transaktion rollbackes hvis noget skulle g� galt
				DBConnection.getInstance().rollbackTransaction();
				// Her fanges de undtagelser der kan opst� i metoden
				PersistensException pe = new PersistensException(e, "Salg kunne ikke gennemf�res rollback");
				throw pe;
			} catch (SQLException e1) {
				PersistensException pe = new PersistensException(e, "rollback kunne ikke gennemf�res");
				throw pe;
			}
		}
		return id;
	}
}
