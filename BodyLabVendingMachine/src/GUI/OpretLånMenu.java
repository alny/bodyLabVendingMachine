package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Controller.CtrBusinessIntelligence;

import Controller.CtrVendingMachine;

import Infrastructure.CtrBusinessIntelligenceIF;
import Infrastructure.CtrVendingMachineIF;
import Model.Product;


import java.util.List;



public class OpretLånMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private JPanel parentPanel;
	private List<Product> pList;
	private CardLayout parent;
	private CtrVendingMachineIF ctrVM;
	private CtrBusinessIntelligenceIF businessCtr;

	public OpretLånMenu(JPanel mainPanel, CardLayout cardLayout) {
		parentPanel = mainPanel;
		parent = cardLayout;
		ctrVM = new CtrVendingMachine();
		businessCtr = new CtrBusinessIntelligence();
		init();
	}

	private void init() {

		setBounds(100, 100, 750, 500);
		setLayout(new BorderLayout(0, 0));
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("Opret Lån", null, showOpretLån(), null);
	}

	private JPanel showOpretLån() {

		JPanel showOpretLån = new JPanel();
		showOpretLån.setLayout(null);



		return showOpretLån;

	}
	
}