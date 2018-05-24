package GUI;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class MainMenu extends JFrame {

	private JPanel parentPanel;
	private JPanel menuPanel;
	private CustomerMenu customerMenu;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					SwingUtilities.updateComponentTreeUI(frame);
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainMenu() {
		CardLayout cl = new CardLayout();
		parentPanel = new JPanel();
		menuPanel = new JPanel();
		customerMenu = new CustomerMenu(parentPanel, cl);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 515);
		setContentPane(parentPanel);

		parentPanel.setLayout(cl);
		cl.show(parentPanel, "1");

		parentPanel.add(menuPanel, "1");
		parentPanel.add(customerMenu, "2");
		menuPanel.setLayout(null);

		JLabel lblBodyLab = new JLabel("Bodylab's POS System");
		lblBodyLab.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBodyLab.setBounds(268, 94, 205, 32);
		menuPanel.add(lblBodyLab);

		JButton btnLogin = new JButton("Log Ind");
		btnLogin.addActionListener(	new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(parentPanel, "2");
			}
		});
		btnLogin.setBounds(294, 227, 147, 52);
		menuPanel.add(btnLogin);

		JButton btnAfslut = new JButton("Afslut");
		btnAfslut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		btnAfslut.setBounds(325, 368, 89, 23);
		menuPanel.add(btnAfslut);

	}

}
