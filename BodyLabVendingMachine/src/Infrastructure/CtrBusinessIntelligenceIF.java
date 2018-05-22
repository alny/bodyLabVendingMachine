package Infrastructure;

import java.util.List;

import Controller.CannotFindException;
import Database.PersistensException;
import Model.Sale;

public interface CtrBusinessIntelligenceIF {

	float getSumFromMachine(int vmId) throws CannotFindException;

	float getSumFromProduct(int pId) throws CannotFindException;
	
	int getTotalSumFromAllMachines(int cId) throws CannotFindException;

	List<Sale> getAllSalesFromMachine(int vmId) throws CannotFindException;

	List<Sale> getAllSalesFromProduct(int pId) throws CannotFindException;

	int getAmountOfSalesFromMachine(int vmId) throws CannotFindException;

	int getAmountOfSalesFromProduct(int pId) throws CannotFindException;

	Sale createSale(int vmId, int productId, float price) throws CannotFindException;

}