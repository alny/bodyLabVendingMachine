package gui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Color;

public class MainMenu extends JFrame {

	private JPanel parentPanel;
	private JPanel menuPanel;
	private CustomerMenu customerMenu;
	private SalesMenu salesMenu;
	
	private BufferedImage myPicture;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					SwingUtilities.updateComponentTreeUI(frame);
					
					frame.setLocationRelativeTo(null);
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
		menuPanel.setBackground(Color.WHITE);
		customerMenu = new CustomerMenu(parentPanel, cl);;
		salesMenu = new SalesMenu(parentPanel, cl);;
		try {
			myPicture = ImageIO.read(new File("src/images/download.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 515);
		setContentPane(parentPanel);
		setResizable(false);

		parentPanel.setLayout(cl);
		cl.show(parentPanel, "1");

		parentPanel.add(menuPanel, "1");
		parentPanel.add(customerMenu, "2");
		parentPanel.add(salesMenu, "3");
		menuPanel.setLayout(null);

		JLabel lblBodyLab = new JLabel(new ImageIcon(myPicture));
		lblBodyLab.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBodyLab.setBounds(82, 28, 563, 140);
		menuPanel.add(lblBodyLab);

		JButton btnLogin = new JButton("Log Ind");
		btnLogin.addActionListener((e) -> {
				cl.show(parentPanel, "2");
		});
		btnLogin.setBounds(294, 227, 147, 52);
		menuPanel.add(btnLogin);

		JButton btnAfslut = new JButton("Afslut");
		btnAfslut.addActionListener((e) -> {
				setVisible(false);
				dispose();
		});
		btnAfslut.setBounds(325, 368, 89, 23);
		menuPanel.add(btnAfslut);
		
		JButton btnLavSalg = new JButton("Lav salg");
		btnLavSalg.setBounds(12, 442, 97, 25);
		menuPanel.add(btnLavSalg);
		
		btnLavSalg.addActionListener((e) -> {
			cl.show(parentPanel, "3");
		});
		
		

	}
}

