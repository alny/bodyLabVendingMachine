package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Controller.CtrCustomer;
import Controller.CtrLoan;
import Database.PersistensException;
import Infrastructure.CtrCustomerIF;
import Infrastructure.CtrLoanIF;
import Model.CityZip;
import Model.Customer;
import Model.Loan;
import Model.Product;

import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import java.awt.Font;

public class CustomerMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private JPanel parentPanel;
	private CardLayout parent;

	private JTable customerTable;
	private JLabel label;
	
	private CtrCustomerIF customerCtr;
	private OpretL�nMenu opretL�nMenu;
	private StatistiskMenu statistiskMenu;
	private CtrLoanIF loanCtr;
	private JTable loanTable;
	
	private int id;
	private String name;
	private String address;
	private String city;
	private String zipCode;
	private String phone;
	private List<Product> productList;

	public CustomerMenu(JPanel mainPanel, CardLayout cardLayout) {
		parentPanel = mainPanel;
		parent = cardLayout;
		customerCtr = new CtrCustomer();
		loanCtr = new CtrLoan();
		init();
	}

	private void init() {
		setBounds(100, 100, 750, 500);
		setLayout(new BorderLayout(0, 0));
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("Kundeliste", null, showCustomers(), null);
		tabbedPane.addTab("Test", null, showSpecificCustomer(), null);

	}

	private JPanel showCustomers() {

		JPanel showCustomers = new JPanel();
		showCustomers.setLayout(null);

		customerTable = new JTable();
		refreshCustomer();
		JScrollPane sp = new JScrollPane();
		sp.setBounds(0, 0, 735, 390);
		sp.setViewportView(customerTable);
		showCustomers.add(sp);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 0, 0);
		showCustomers.add(panel);
		FlowLayout fl_panel = new FlowLayout(FlowLayout.RIGHT, 5, 5);
		panel.setLayout(fl_panel);
		label = new JLabel("");
		label.setForeground(Color.RED);
		panel.add(label);

		JButton btnSeDetaljer = new JButton("Se Kundedetaljer");
		btnSeDetaljer.setBounds(14, 400, 152, 23);
		showCustomers.add(btnSeDetaljer);

		JButton btnTilbag = new JButton("Tilbage");
		btnTilbag.setBounds(308, 399, 80, 23);
		showCustomers.add(btnTilbag);
		
		JButton btnOpretLn = new JButton("Opret L\u00E5n");
		btnOpretLn.addActionListener((e) -> {
			opretL�nMenu = new OpretL�nMenu(parentPanel, parent);
			parentPanel.add(opretL�nMenu, "3");
			parent.show(parentPanel, "3");
		});
		btnOpretLn.setBounds(187, 399, 97, 25);
		showCustomers.add(btnOpretLn);
		btnTilbag.addActionListener((e) -> {
			parent.show(parentPanel, "1");
		});
		btnSeDetaljer.addActionListener((e) -> {
			
			int row = customerTable.getSelectedRow();
			if (row > -1) {
				id = Integer.parseInt(customerTable.getValueAt(row, 0).toString());
				name = customerTable.getValueAt(row, 1).toString();
				address = customerTable.getValueAt(row, 2).toString();
				phone = customerTable.getValueAt(row, 3).toString();
				city = customerTable.getValueAt(row, 4).toString();
				zipCode = customerTable.getValueAt(row, 5).toString();
				parentPanel.add(showSpecificCustomer(), "3");
				parent.show(parentPanel, "3");
			} 
			else {
				JOptionPane.showMessageDialog(null, "V�lg en kunde");
			}
		});

		return showCustomers;

	}
	
	public JPanel showSpecificCustomer() {
		
		JPanel showSpecificCustomer = new JPanel();

		showSpecificCustomer.setLayout(new BorderLayout(0, 0));
		
		JPanel kundeOplysningeer = new JPanel();
		kundeOplysningeer.setPreferredSize(new Dimension(200,300));
		showSpecificCustomer.add(kundeOplysningeer, BorderLayout.WEST);
		kundeOplysningeer.setLayout(null);
		
		JLabel lblKundeOplysninger = new JLabel("Kunde Oplysninger:");
		lblKundeOplysninger.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblKundeOplysninger.setBounds(24, 13, 151, 16);
		kundeOplysningeer.add(lblKundeOplysninger);
		
		JLabel lblNewLabel_3 = new JLabel("Navn:");
		lblNewLabel_3.setBounds(24, 84, 56, 16);
		kundeOplysningeer.add(lblNewLabel_3);
		
		JLabel label_1 = new JLabel("");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(66, 84, 122, 16);
		kundeOplysningeer.add(label_1);
		label_1.setText(name);
		
		JLabel lblAdresse = new JLabel("Adresse:");
		lblAdresse.setBounds(24, 130, 56, 16);
		kundeOplysningeer.add(lblAdresse);
		
		JLabel lblAdresse_1 = new JLabel("");
		lblAdresse_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAdresse_1.setBounds(85, 130, 103, 16);
		kundeOplysningeer.add(lblAdresse_1);
		lblAdresse_1.setText(address);
		
		JLabel lblTelefon = new JLabel("Telefon:");
		lblTelefon.setBounds(24, 278, 56, 16);
		kundeOplysningeer.add(lblTelefon);
		
		JLabel lblTelefon_1 = new JLabel("");
		lblTelefon_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTelefon_1.setBounds(85, 177, 103, 16);
		kundeOplysningeer.add(lblTelefon_1);
		lblTelefon_1.setText(phone);
		 
		JLabel lblBy = new JLabel("By:");
		lblBy.setBounds(24, 228, 56, 16);
		kundeOplysningeer.add(lblBy);
		
		JLabel lblBy_1 = new JLabel("");
		lblBy_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBy_1.setBounds(85, 228, 103, 16);
		kundeOplysningeer.add(lblBy_1);
		lblBy_1.setText(city);
		
		JLabel lblPostNr = new JLabel("Post Nr:");
		lblPostNr.setBounds(24, 177, 56, 16);
		kundeOplysningeer.add(lblPostNr);
		
		JLabel lblPostNr_1 = new JLabel("");
		lblPostNr_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPostNr_1.setBounds(85, 278, 103, 16);
		kundeOplysningeer.add(lblPostNr_1);
		lblPostNr_1.setText(zipCode);
		
		JPanel knapper = new JPanel();
		FlowLayout flowLayout = (FlowLayout) knapper.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		showSpecificCustomer.add(knapper, BorderLayout.SOUTH);
		
		JPanel loanTabel = new JPanel();
		loanTabel.setPreferredSize(new Dimension(500,400));
		showSpecificCustomer.add(loanTabel, BorderLayout.CENTER);
		
		loanTable = new JTable();
		refreshLoan();
		JScrollPane sp = new JScrollPane();
		sp.setBounds(0, 0, 735, 390);
		sp.setViewportView(loanTable);
		loanTabel.add(sp);
		
		JButton btnSeDetaljer = new JButton("Enkel Statistisk");
		btnSeDetaljer.setHorizontalAlignment(SwingConstants.RIGHT);
		knapper.add(btnSeDetaljer);
		btnSeDetaljer.setBounds(14, 400, 152, 23);
		
		btnSeDetaljer.addActionListener((e) -> {
			int row = loanTable.getSelectedRow();
			if (row > -1) {
				id = Integer.parseInt(loanTable.getValueAt(row, 0).toString());
				statistiskMenu = new StatistiskMenu(parentPanel, parent, id, productList);
				parentPanel.add(statistiskMenu, "4");
				parent.show(parentPanel, "4");
			} 
			else {
				JOptionPane.showMessageDialog(null, "V�lg et l�n");
			}
		});
		
		JButton btnSamletStatistisk = new JButton("Samlet Statistisk");
		btnSamletStatistisk.addActionListener((e) -> {
			
		});
		knapper.add(btnSamletStatistisk);
		 
		JButton btnTilbag = new JButton("Tilbage");
		knapper.add(btnTilbag);
		btnTilbag.setBounds(199, 400, 80, 23);
		
		btnTilbag.addActionListener((e) -> {
			parent.show(parentPanel, "2");
		});
				
		return showSpecificCustomer;
		
	}
	 
	private void refreshLoan() {
		try {
			Customer customer = new Customer(id, name, address, phone, new CityZip(zipCode, city));
			loanTable.setModel(loanTable(loanCtr.findLoansForCustomer(customer)));
		} catch (PersistensException e) {
			e.printStackTrace();
		}
	}
	
	private void refreshCustomer() {
		try {
			customerTable.setModel(customerTable(customerCtr.findAllCustomers()));
		} catch (PersistensException e) {
			e.printStackTrace();
		}
	}
	
	private TableModel loanTable(List<Loan> list) {

		DefaultTableModel model = new DefaultTableModel(new Object[] { "L�n-Id", "Dato Oprettet", "Automat-Id", "Model", "Serie Nr" },
				0);
		for (Loan entry : list) {

			model.addRow(new Object[] { entry.getId(), entry.getDate(), entry.getVendingmachine().getId(), 
					entry.getVendingmachine().getModel(), entry.getVendingmachine().getSerialNo() });
			productList = entry.getVendingmachine().getProducts();
			
		}
		return model;
	}
	
	private TableModel customerTable(List<Customer> list) {

		DefaultTableModel model = new DefaultTableModel(new Object[] { "Id", "Navn", "Adresse", "Post Nr", "By", "Telefon" },
				0);
		for (Customer entry : list) {

			model.addRow(new Object[] { entry.getId(), entry.getName(), entry.getAddress(),
					entry.getCityZip().getZipCode(), entry.getCityZip().getCity(), entry.getPhone() });
		}
		return model;
	}
}
