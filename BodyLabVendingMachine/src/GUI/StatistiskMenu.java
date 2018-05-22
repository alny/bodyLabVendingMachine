package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Controller.CannotFindException;
import Controller.CtrBusinessIntelligence;
import Controller.CtrCustomer;
import Controller.CtrProduct;
import Database.PersistensException;
import Model.Loan;
import Model.Product;

import java.awt.Font;
import java.util.List;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class StatistiskMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private JPanel parentPanel;
	private List<Product> pList;
	private CardLayout parent;
	private CtrProduct productCtr;
	private CtrBusinessIntelligence businessCtr;
	private int vendingMachineId;
	private int comboBoxOneProductId;
	private int comboBoxTwoProductId;
	private JLabel label_1;
	

	public StatistiskMenu(JPanel mainPanel, CardLayout cardLayout, int vmId, List<Product> productList) {
		parentPanel = mainPanel;
		parent = cardLayout;
		pList = productList;
		vendingMachineId = vmId;
		productCtr = new CtrProduct();
		businessCtr = new CtrBusinessIntelligence();
		init();
	}

	private void init() {
		setBounds(100, 100, 750, 500);
		setLayout(new BorderLayout(0, 0));
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("Statitisk", null, showStatistisk(), null);
		try {
			label_1.setText(Float.toString(businessCtr.getSumFromMachine(vendingMachineId)) + " kr");
		} catch (CannotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addActionListener((e) -> {
			
		});
		try {
			pList = productCtr.findProductsInVM(vendingMachineId);
		} catch (PersistensException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CannotFindException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox.addItem("Vælg Produkt");

		for (Product entry : pList) {
			System.out.println(entry.getName());
			comboBox.addItem(entry.getName());
			comboBoxOneProductId = entry.getId();
		}
		comboBox.setBounds(14, 190, 200, 22);
		showStatistisk.add(comboBox);
		
		JLabel lblLagerBeholdning = new JLabel("Lager beholdning:");
		lblLagerBeholdning.setBounds(14, 264, 133, 16);
		showStatistisk.add(lblLagerBeholdning);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.addItem("Vælg Produkt");
		
		for (Product entry : pList) {
			System.out.println(entry.getName());
			comboBox_1.addItem(entry.getName());
			comboBoxTwoProductId = entry.getId();
		}
		comboBox_1.setBounds(14, 293, 200, 22);
		showStatistisk.add(comboBox_1);
		
		JLabel label = new JLabel("0");
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setBounds(355, 193, 105, 19);
		showStatistisk.add(label);
		
		label_1 = new JLabel("0.00 kr");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_1.setBounds(355, 86, 133, 18);
		showStatistisk.add(label_1);
		
		JLabel label_2 = new JLabel("0");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_2.setBounds(355, 296, 80, 16);
		showStatistisk.add(label_2);
		btnTilbag.addActionListener((e) -> {
			parent.show(parentPanel, "2");
		});
		comboBox.addActionListener((e) -> {
			try {	
				label.setText(Float.toString(businessCtr.getSumFromProduct(comboBoxOneProductId))+ " kr");
			} catch (CannotFindException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		return showStatistisk;

	}
}
