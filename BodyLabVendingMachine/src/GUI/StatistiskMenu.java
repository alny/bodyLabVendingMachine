package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.JComboBox;


public class StatistiskMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private JPanel parentPanel;
	private CardLayout parent;
	

	public StatistiskMenu(JPanel mainPanel, CardLayout cardLayout, int vmId) {
		parentPanel = mainPanel;
		parent = cardLayout;
		init();
	}

	private void init() {
		setBounds(100, 100, 750, 500);
		setLayout(new BorderLayout(0, 0));
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("Statitisk", null, showStatistisk(), null);
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(14, 190, 143, 22);
		showStatistisk.add(comboBox);
		
		JLabel lblLagerBeholdning = new JLabel("Lager beholdning:");
		lblLagerBeholdning.setBounds(14, 264, 133, 16);
		showStatistisk.add(lblLagerBeholdning);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(14, 293, 143, 22);
		showStatistisk.add(comboBox_1);
		btnTilbag.addActionListener((e) -> {
			parent.show(parentPanel, "1");
		});
		btnSeDetaljer.addActionListener((e) -> {
			
		});

		return showStatistisk;

	}
}
