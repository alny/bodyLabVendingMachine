package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.CannotFindException;
import controller.CtrCustomer;
import controller.CtrDBConnection;
import controller.CtrLoan;
import controller.CtrSale;
import database.DBConnection;
import database.PersistensException;
import infrastructure.CtrCustomerIF;
import infrastructure.CtrDBConnectionIF;
import infrastructure.CtrLoanIF;
import infrastructure.CtrSaleIF;
import model.CityZip;
import model.Customer;
import model.Loan;
import model.Product;

import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.JScrollBar;
import java.awt.Font;
import javax.swing.JComboBox;

public class SalesMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private JPanel parentPanel;
	private CardLayout parent;
	private CtrSaleIF saleCtr;




	public SalesMenu(JPanel mainPanel, CardLayout cardLayout) {
		parentPanel = mainPanel;
		parent = cardLayout;
		saleCtr = new CtrSale();
		init();
	}

	private void init() {
		setBounds(100, 100, 750, 500);
		setLayout(new BorderLayout(0, 0));
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Salg", null, panel, null);
		panel.setLayout(null);
		
		JButton btnLavSalg = new JButton("Lav salg");
		btnLavSalg.addActionListener((e) -> {
			try {
				saleCtr.createSale(1, 1, 100);
				saleCtr.createSale(2, 3, 100);
				saleCtr.createSale(3, 5, 100);
				saleCtr.createSale(4, 7, 100);
				saleCtr.createSale(5, 9, 100);
				saleCtr.createSale(6, 11, 100);
				saleCtr.createSale(7, 13, 100);
				saleCtr.createSale(8, 15, 100);
			} catch (CannotFindException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
				
				
			
		});
		btnLavSalg.setBounds(327, 203, 97, 25);
		panel.add(btnLavSalg);
		
		JButton btnTilbage = new JButton("Tilbage");
		btnTilbage.setBounds(12, 408, 97, 25);
		panel.add(btnTilbage);
		btnTilbage.addActionListener((e) -> {
			parent.show(parentPanel, "1");
		});
	}
}
