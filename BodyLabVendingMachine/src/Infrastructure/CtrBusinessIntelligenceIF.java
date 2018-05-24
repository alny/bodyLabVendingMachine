package Infrastructure;

import java.util.List;

import Controller.CannotFindException;
import Database.PersistensException;
import Model.Sale;

public interface CtrBusinessIntelligenceIF {

	float getSumFromMachine(int vmId, String startD, String endD) throws CannotFindException;

	float getSumFromProduct(int pId, String startD, String endD) throws CannotFindException;
	
	int getTotalSumFromAllMachines(int cId, String startD, String endD) throws CannotFindException;

	List<Sale> getAllSalesFromMachine(int vmId) throws CannotFindException;

	List<Sale> getAllSalesFromProduct(int pId) throws CannotFindException;

	int getAmountOfSalesFromMachine(int vmId) throws CannotFindException;

	int getAmountOfSalesFromProduct(int pId) throws CannotFindException;

	Sale createSale(int vmId, int productId, float price) throws CannotFindException;
	
	int getQuatity(int vmId, int productId) throws PersistensException, CannotFindException;

	int getTotalSumProductFromAllMachines(int cId, String startD, String endD, int productId)
			throws CannotFindException;

}