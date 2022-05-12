package meta.projet.ihm;


import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.io.IOException;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;


public class BAR extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			BAR frame = new BAR();
			UI u = new UI();

			public void run() {
				try {					
					frame.setLocationRelativeTo(null);								
					u.start();
					
					//u.join();
					u.getFrmProjetMetaheuristique().setVisible(true);
					frame.setVisible(true);	
					frame.setAlwaysOnTop(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public BAR(){
		setBackground(SystemColor.textHighlight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(SystemColor.window);
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setUndecorated(true);
		
		JLabel label = new JLabel("Résolution en cours");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 30));
		label.setBounds(67, 50, 314, 37);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Patientez...");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.ITALIC, 15));
		label_1.setBounds(10, 220, 389, 37);
		contentPane.add(label_1);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(161, 130, 74, 78);
		Icon imgIcon = new ImageIcon(this.getClass().getResource("/loading.gif"));
		lblNewLabel.setIcon(imgIcon);	
	
		lblNewLabel.setBounds(180, 130, 70, 70);
		contentPane.add(lblNewLabel);
		
		
		
		
	}
}
