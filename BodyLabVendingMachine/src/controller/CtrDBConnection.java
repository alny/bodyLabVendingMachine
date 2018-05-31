package controller;


import database.DBConnection;
import infrastructure.CtrDBConnectionIF;
import infrastructure.DBConnectionIF;

public class CtrDBConnection implements CtrDBConnectionIF {
	private DBConnectionIF dbConnection;
	
	public CtrDBConnection() {
		dbConnection = DBConnection.getInstance();
	}
	
	@Override
	public Boolean recheckConnection() {
		return dbConnection.recheckConnection();
	}
	
}
