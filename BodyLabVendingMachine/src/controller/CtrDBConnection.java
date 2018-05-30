package controller;


import database.DBConnection;
import infrastructure.CtrDBConnectionIF;
import infrastructure.DBConnectionIF;

public class CtrDBConnection implements CtrDBConnectionIF {
	private DBConnectionIF dbC;
	
	public CtrDBConnection() {
		dbC = DBConnection.getInstance();
	}
	
	@Override
	public Boolean recheckConnection() {
		return dbC.recheckConnection();
	}
	
}
