package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controller.CannotFindException;
import controller.CtrBusinessIntelligence;
import controller.CtrCustomer;
import controller.CtrProduct;
import controller.CtrVendingMachine;
import database.PersistensException;
import infrastructure.CtrBusinessIntelligenceIF;
import infrastructure.CtrVendingMachineIF;
import model.Loan;
import model.Product;

import java.awt.Font;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class StatisticMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private JPanel parentPanel;
	private List<Product> pList;
	private CardLayout parent;
	private CtrVendingMachineIF ctrVM;
	private CtrBusinessIntelligenceIF businessCtr;
	private int vendingMachineId;
	private int comboBoxOneProductId;
	private int comboBoxTwoProductId;
	private JLabel label_1;
	private JTextField startDate;
	private JTextField endDate;
	private String startD;
	private String endD;
	private JLabel label;
	private JComboBox<String> comboBox;
	private JLabel label_2;
	private JComboBox<String> comboBox_1;
	private JDialog dialog;
	private boolean all;
	private int cId;

	public StatisticMenu(JPanel mainPanel, CardLayout cardLayout, int vmId, List<Product> productList, boolean all, int id) {
		startD = "01/01/18";
		endD = "01/01/19";
		parentPanel = mainPanel;
		parent = cardLayout;
		pList = productList;
		vendingMachineId = vmId;
		ctrVM = new CtrVendingMachine();
		businessCtr = new CtrBusinessIntelligence();
		this.all = all;
		cId = id;
		init();
		if (all == true) {
			statisticForAll();
		}
	}

	private void init() {
		setBounds(100, 100, 750, 500);
		setLayout(new BorderLayout(0, 0));
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("Statitisk", null, showStatistisk(), null);
		try {
			label_1.setText(Float.toString(businessCtr.getSumFromMachine(vendingMachineId, startD, endD)) + " kr");
		} catch (CannotFindException e) {
			createFailureDialog("automaten kunne ikke findes");
		}
	}

	private JPanel showStatistisk() {

		JPanel showStatistisk = new JPanel();
		showStatistisk.setLayout(null);

		JButton btnSeDetaljer = new JButton("Opdater Søgning");
		btnSeDetaljer.setBounds(14, 400, 152, 23);
		showStatistisk.add(btnSeDetaljer);

		JButton btnTilbag = new JButton("Tilbage");
		btnTilbag.setBounds(199, 400, 80, 23);
		showStatistisk.add(btnTilbag);

		JLabel lblNewLabel = new JLabel("Total Omsætning: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(14, 81, 152, 23);
		showStatistisk.add(lblNewLabel);

		JLabel lblOmstningPrProdukt = new JLabel("Oms\u00E6tning pr produkt:");
		lblOmstningPrProdukt.setBounds(12, 161, 145, 16);
		showStatistisk.add(lblOmstningPrProdukt);

		comboBox = new JComboBox<String>();
		comboBox.addActionListener((e) -> {

		});
		try {
			if(all == false) {
			pList = ctrVM.findVendingMachine(vendingMachineId, true).getProducts();
			}else {
				pList = businessCtr.findCustomerProduct(cId);
			}
		} catch (PersistensException e1) {
			createFailureDialog("der kunne ikke forbindes til databasen");
		} catch (CannotFindException e1) {
			createFailureDialog("automaten kunne ikke findes");
		}
		comboBox.addItem("Vælg Produkt");

		for (Product entry : pList) {
			comboBox.addItem(entry.getName());
			comboBoxOneProductId = entry.getId();
		}
		comboBox.setBounds(14, 190, 200, 22);
		showStatistisk.add(comboBox);

		JLabel lblLagerBeholdning = new JLabel("Lager beholdning D.D.:");
		lblLagerBeholdning.setBounds(14, 264, 133, 16);
		showStatistisk.add(lblLagerBeholdning);

		comboBox_1 = new JComboBox<String>();
		comboBox_1.addItem("Vælg Produkt");
		comboBox_1.addActionListener((e) -> {
			try {
				label_2.setText(Integer.toString(businessCtr.getQuantity(vendingMachineId, comboBoxTwoProductId)));
			} catch (PersistensException ex) {
				createFailureDialog("der kunne ikke forbindes til databasen");
			} catch (CannotFindException ex) {
				createFailureDialog("automaten kunne ikke findes");
			}
		});

		for (Product entry : pList) {
			comboBox_1.addItem(entry.getName());
			comboBoxTwoProductId = entry.getId();
		}
		comboBox_1.setBounds(14, 293, 200, 22);
		showStatistisk.add(comboBox_1);

		label = new JLabel("0");
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setBounds(355, 193, 105, 19);
		showStatistisk.add(label);

		label_1 = new JLabel("0.00 kr");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_1.setBounds(355, 86, 133, 18);
		showStatistisk.add(label_1);

		label_2 = new JLabel("0");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_2.setBounds(355, 296, 80, 16);
		showStatistisk.add(label_2);

		startDate = new JTextField();
		startDate.setBounds(14, 33, 116, 22);
		showStatistisk.add(startDate);
		startDate.setColumns(10);

		endDate = new JTextField();
		endDate.setBounds(152, 33, 116, 22);
		showStatistisk.add(endDate);
		endDate.setColumns(10);
		startDate.setText("1/1/2018");
		int day = new Date().getDate();
		int month = new Date().getMonth() + 1;
		int year = new Date().getYear() + 1900;
		String date = Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year);

		endDate.setText(date);

		JLabel lblNewLabel_1 = new JLabel("Angiv Periode (MM/DD/YYYY)");
		lblNewLabel_1.setBounds(12, 13, 176, 16);
		showStatistisk.add(lblNewLabel_1);
		startDate.addActionListener((e) -> {

			updateFields();
		});

		endDate.addActionListener((e) -> {
			updateFields();
		});

		btnTilbag.addActionListener((e) -> {
			parent.show(parentPanel, "3");
		});
		comboBox.addActionListener((e) -> {
			try {
				label.setText(
						Float.toString(businessCtr.getSumFromProduct(comboBoxOneProductId, startD, endD)) + " kr");
			} catch (CannotFindException e1) {
				createFailureDialog("produktet findes ikke");
			}
		});

		return showStatistisk;

	}

	public void updateFields() {

		if (all == false) {

			startD = startDate.getText() + " 23:59:00";
			endD = endDate.getText()+ " 23:59:00";
			try {
				label_1.setText(Float.toString(businessCtr.getSumFromMachine(vendingMachineId, startD, endD)) + " kr");
				if (comboBox.getSelectedIndex() != 0) {
					label.setText(
							Float.toString(businessCtr.getSumFromProduct(comboBoxOneProductId, startD, endD)) + " kr");
				}

			} catch (CannotFindException e1) {
				createFailureDialog("automaten kunne ikke findes");

			}
		} else {
			statisticForAll();
		}
	}

	public void statisticForAll() {
		startD = startDate.getText()+ " 23:59:59";
		endD = endDate.getText()+ " 23:59:59";
		try {
			label_1.setText(Float.toString(businessCtr.getTotalSumFromAllMachines(cId, startD, endD)) + " kr");
			if (comboBox.getSelectedIndex() != 0) {
				label.setText(Float.toString(
						businessCtr.getTotalSumProductFromAllMachines(cId, startD, endD, comboBoxOneProductId))
						+ " kr");
			}
		} catch (CannotFindException e) {
			e.printStackTrace();
		}
	}
	
	private JDialog createFailureDialog(String failuremessage) {
		dialog = new JDialog();
		dialog.getContentPane().setLayout(new FlowLayout());
		JButton okB = new JButton("Ok");
		okB.addActionListener((e) -> {
			dialog.setVisible(false);
		});
		dialog.getContentPane().add(new JLabel(failuremessage));
		dialog.getContentPane().add(okB);
		dialog.setSize(300, 100);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		return dialog;
	}
	
}