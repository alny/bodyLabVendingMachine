package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Controller.CtrCustomer;
import Database.PersistensException;
import Model.Customer;

public class CustomerMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private JPanel parentPanel;
	private CardLayout parent;

	private JTable customerTable;
	private JLabel label;
	
	private CtrCustomer customerCtr;

	public CustomerMenu(JPanel mainPanel, CardLayout cardLayout) {
		parentPanel = mainPanel;
		parent = cardLayout;
		customerCtr = new CtrCustomer();
		init();
	}

	private void init() {
		setBounds(100, 100, 750, 500);
		setLayout(new BorderLayout(0, 0));
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("Kundeliste", null, showCustomers(), null);

	}

	public JPanel showCustomers() {

		JPanel showCustomers = new JPanel();
		showCustomers.setLayout(null);
		showCustomers.setLayout(null);

		customerTable = new JTable();
		refresh();
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
		btnTilbag.setBounds(199, 400, 80, 23);
		showCustomers.add(btnTilbag);
		btnTilbag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.show(parentPanel, "1");
			}
		});
		btnSeDetaljer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.show(parentPanel, "1");
			}
		});

		return showCustomers;

	}
	
	public void refresh() {
		try {
			customerTable.setModel(customerTable(customerCtr.findAllCustomers()));
		} catch (PersistensException e) {
			e.printStackTrace();
		}
	}
	
	public TableModel customerTable(List<Customer> list) {

		DefaultTableModel model = new DefaultTableModel(new Object[] { "Navn", "Adresse", "Post Nr", "By", "Telefon" },
				0);
		for (Customer entry : list) {

			model.addRow(new Object[] { entry.getName(), entry.getAddress(),
					entry.getCityZip().getZipCode(), entry.getCityZip().getCity(), entry.getPhone() });
		}
		return model;

	}

}
