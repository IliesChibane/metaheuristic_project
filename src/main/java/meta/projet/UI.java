package meta.projet;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;

public class UI {

	private JFrame frmProjetMetaheuristique;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frmProjetMetaheuristique.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProjetMetaheuristique = new JFrame();
		frmProjetMetaheuristique.getContentPane().setBackground(Color.DARK_GRAY);
		frmProjetMetaheuristique.getContentPane().setLayout(null);
		
		JPanel panel = new MotionPanel(frmProjetMetaheuristique);
		panel.setBounds(0, 0, 1000, 42);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(127, 255, 212), null, null, new Color(127, 255, 212)));
		panel.setBackground(Color.DARK_GRAY);
		frmProjetMetaheuristique.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.setBounds(969, 0, 21, 37);
		panel.add(lblNewLabel);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setForeground(new Color(127, 255, 212));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(127, 255, 212), null, null, null));
		tabbedPane.setBounds(0, 43, 1000, 657);
		tabbedPane.setBackground(Color.DARK_GRAY);
		frmProjetMetaheuristique.getContentPane().add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(127, 255, 212), null, null, null));
		panel_1.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("A* Algorithm", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, new Color(127, 255, 212), null, null, null));
		scrollPane.setBounds(0, 59, 237, 570);
		scrollPane.getVerticalScrollBar().setVisible(false);
		panel_1.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		
		scrollPane.setViewportView(panel_2);
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setLayout(null);
		
		JLabel lblEtatInitial = new JLabel("Etat initial");
		lblEtatInitial.setBounds(13, 2, 70, 88);
		panel_2.add(lblEtatInitial);
		lblEtatInitial.setForeground(new Color(127, 255, 212));
		lblEtatInitial.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 2, 163, 88);
		separator.setBackground(new Color(127, 255, 212));
		panel_2.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 120, 83, 88);
		separator_1.setBackground(new Color(127, 255, 212));
		panel_2.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(147, 120, 83, 88);
		separator_2.setBackground(new Color(127, 255, 212));
		panel_2.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(66, 238, 97, 88);
		separator_3.setBackground(new Color(127, 255, 212));
		panel_2.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(0, 356, 83, 88);
		separator_4.setBackground(new Color(127, 255, 212));
		panel_2.add(separator_4);
		
		JLabel lblEtatFinal = new JLabel("Etat final");
		lblEtatFinal.setBounds(160, 356, 70, 88);
		lblEtatFinal.setForeground(new Color(127, 255, 212));
		lblEtatFinal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblEtatFinal);
		
		JLabel lblRsultat = new JLabel("Résultat");
		lblRsultat.setBounds(0, 477, 70, 88);
		lblRsultat.setForeground(new Color(127, 255, 212));
		lblRsultat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblRsultat);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField.setText("0.00");
		textField.setForeground(Color.DARK_GRAY);
		textField.setEditable(false);
		textField.setBackground(new Color(255, 255, 255));
		textField.setBounds(74, 510, 156, 27);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_4.setForeground(Color.DARK_GRAY);
		panel_4.setBackground(new Color(127, 255, 212));
		panel_4.setBounds(160, 2, 70, 88);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		
		
		
		
		
		JLabel lblNewLabel_2 = new JLabel("");
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(UI.class.getResource("/meta/projet/grid-27529_1280.png")).getImage().getScaledInstance(70, 88, Image.SCALE_DEFAULT));
		lblNewLabel_2.setIcon(imageIcon);
		lblNewLabel_2.setBounds(0, 0, 70, 88);
		panel_4.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(" 1");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setForeground(Color.DARK_GRAY);
		lblNewLabel_3.setBounds(24, 0, 22, 26);
		panel_4.add(lblNewLabel_3);
		
		JLabel label_5 = new JLabel(" 1");
		label_5.setForeground(Color.DARK_GRAY);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_5.setBounds(48, 0, 22, 26);
		panel_4.add(label_5);
		
		JLabel label_6 = new JLabel(" 1");
		label_6.setForeground(Color.DARK_GRAY);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_6.setBounds(0, 0, 22, 26);
		panel_4.add(label_6);
		
		JLabel label_7 = new JLabel(" 1");
		label_7.setForeground(Color.DARK_GRAY);
		label_7.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_7.setBounds(0, 30, 22, 26);
		panel_4.add(label_7);
		
		JLabel label_8 = new JLabel(" 1");
		label_8.setForeground(Color.DARK_GRAY);
		label_8.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_8.setBounds(24, 30, 22, 26);
		panel_4.add(label_8);
		
		JLabel label_9 = new JLabel(" 1");
		label_9.setForeground(Color.DARK_GRAY);
		label_9.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_9.setBounds(48, 30, 22, 26);
		panel_4.add(label_9);
		
		JLabel label_10 = new JLabel(" 1");
		label_10.setForeground(Color.DARK_GRAY);
		label_10.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_10.setBounds(48, 62, 22, 26);
		panel_4.add(label_10);
		
		JLabel label_11 = new JLabel(" 1");
		label_11.setForeground(Color.DARK_GRAY);
		label_11.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_11.setBounds(24, 62, 22, 26);
		panel_4.add(label_11);
		
		JLabel label_12 = new JLabel(" 1");
		label_12.setForeground(Color.DARK_GRAY);
		label_12.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_12.setBounds(0, 62, 22, 26);
		panel_4.add(label_12);
		
		JPanel panel_5 = new JPanel();
		panel_5.setForeground(Color.DARK_GRAY);
		panel_5.setBackground(new Color(127, 255, 212));
		panel_5.setBounds(80, 120, 70, 88);
		panel_2.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel label_1 = new JLabel("");
		ImageIcon imageIcon1 = new ImageIcon(new ImageIcon(UI.class.getResource("/meta/projet/grid-27529_1280.png")).getImage().getScaledInstance(70, 88, Image.SCALE_DEFAULT));
		label_1.setIcon(imageIcon1);
		label_1.setBounds(0, 0, 70, 88);
		panel_5.add(label_1);
		
		JLabel label_13 = new JLabel(" 1");
		label_13.setForeground(Color.DARK_GRAY);
		label_13.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_13.setBounds(24, 0, 22, 26);
		panel_5.add(label_13);
		
		JLabel label_14 = new JLabel(" 1");
		label_14.setForeground(Color.DARK_GRAY);
		label_14.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_14.setBounds(48, 0, 22, 26);
		panel_5.add(label_14);
		
		JLabel label_15 = new JLabel(" 1");
		label_15.setForeground(Color.DARK_GRAY);
		label_15.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_15.setBounds(0, 0, 22, 26);
		panel_5.add(label_15);
		
		JLabel label_16 = new JLabel(" 1");
		label_16.setForeground(Color.DARK_GRAY);
		label_16.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_16.setBounds(0, 30, 22, 26);
		panel_5.add(label_16);
		
		JLabel label_17 = new JLabel(" 1");
		label_17.setForeground(Color.DARK_GRAY);
		label_17.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_17.setBounds(24, 30, 22, 26);
		panel_5.add(label_17);
		
		JLabel label_18 = new JLabel(" 1");
		label_18.setForeground(Color.DARK_GRAY);
		label_18.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_18.setBounds(48, 30, 22, 26);
		panel_5.add(label_18);
		
		JLabel label_19 = new JLabel(" 1");
		label_19.setForeground(Color.DARK_GRAY);
		label_19.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_19.setBounds(48, 62, 22, 26);
		panel_5.add(label_19);
		
		JLabel label_20 = new JLabel(" 1");
		label_20.setForeground(Color.DARK_GRAY);
		label_20.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_20.setBounds(24, 62, 22, 26);
		panel_5.add(label_20);
		
		JLabel label_21 = new JLabel(" 1");
		label_21.setForeground(Color.DARK_GRAY);
		label_21.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_21.setBounds(0, 62, 22, 26);
		panel_5.add(label_21);
		
		JPanel panel_6 = new JPanel();
		panel_6.setForeground(Color.DARK_GRAY);
		panel_6.setBackground(new Color(127, 255, 212));
		panel_6.setBounds(0, 238, 70, 88);
		panel_2.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel label_2 = new JLabel("");
		ImageIcon imageIcon2 = new ImageIcon(new ImageIcon(UI.class.getResource("/meta/projet/grid-27529_1280.png")).getImage().getScaledInstance(70, 88, Image.SCALE_DEFAULT));
		label_2.setIcon(imageIcon2);
		label_2.setBounds(0, 0, 70, 88);
		panel_6.add(label_2);
		
		JLabel label_31 = new JLabel(" 1");
		label_31.setForeground(Color.DARK_GRAY);
		label_31.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_31.setBounds(24, 0, 22, 26);
		panel_6.add(label_31);
		
		JLabel label_32 = new JLabel(" 1");
		label_32.setForeground(Color.DARK_GRAY);
		label_32.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_32.setBounds(48, 0, 22, 26);
		panel_6.add(label_32);
		
		JLabel label_33 = new JLabel(" 1");
		label_33.setForeground(Color.DARK_GRAY);
		label_33.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_33.setBounds(0, 0, 22, 26);
		panel_6.add(label_33);
		
		JLabel label_34 = new JLabel(" 1");
		label_34.setForeground(Color.DARK_GRAY);
		label_34.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_34.setBounds(0, 30, 22, 26);
		panel_6.add(label_34);
		
		JLabel label_35 = new JLabel(" 1");
		label_35.setForeground(Color.DARK_GRAY);
		label_35.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_35.setBounds(24, 30, 22, 26);
		panel_6.add(label_35);
		
		JLabel label_36 = new JLabel(" 1");
		label_36.setForeground(Color.DARK_GRAY);
		label_36.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_36.setBounds(48, 30, 22, 26);
		panel_6.add(label_36);
		
		JLabel label_37 = new JLabel(" 1");
		label_37.setForeground(Color.DARK_GRAY);
		label_37.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_37.setBounds(48, 62, 22, 26);
		panel_6.add(label_37);
		
		JLabel label_38 = new JLabel(" 1");
		label_38.setForeground(Color.DARK_GRAY);
		label_38.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_38.setBounds(24, 62, 22, 26);
		panel_6.add(label_38);
		
		JLabel label_39 = new JLabel(" 1");
		label_39.setForeground(Color.DARK_GRAY);
		label_39.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_39.setBounds(0, 62, 22, 26);
		panel_6.add(label_39);
		
		JPanel panel_7 = new JPanel();
		panel_7.setForeground(Color.DARK_GRAY);
		panel_7.setBackground(new Color(127, 255, 212));
		panel_7.setBounds(160, 238, 71, 88);
		panel_2.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel label_3 = new JLabel("");
		ImageIcon imageIcon3 = new ImageIcon(new ImageIcon(UI.class.getResource("/meta/projet/grid-27529_1280.png")).getImage().getScaledInstance(70, 88, Image.SCALE_DEFAULT));
		label_3.setIcon(imageIcon3);
		label_3.setBounds(0, 0, 71, 88);
		panel_7.add(label_3);
		
		JLabel label_22 = new JLabel(" 1");
		label_22.setForeground(Color.DARK_GRAY);
		label_22.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_22.setBounds(24, 0, 22, 26);
		panel_7.add(label_22);
		
		JLabel label_23 = new JLabel(" 1");
		label_23.setForeground(Color.DARK_GRAY);
		label_23.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_23.setBounds(48, 0, 22, 26);
		panel_7.add(label_23);
		
		JLabel label_24 = new JLabel(" 1");
		label_24.setForeground(Color.DARK_GRAY);
		label_24.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_24.setBounds(0, 0, 22, 26);
		panel_7.add(label_24);
		
		JLabel label_25 = new JLabel(" 1");
		label_25.setForeground(Color.DARK_GRAY);
		label_25.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_25.setBounds(0, 30, 22, 26);
		panel_7.add(label_25);
		
		JLabel label_26 = new JLabel(" 1");
		label_26.setForeground(Color.DARK_GRAY);
		label_26.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_26.setBounds(24, 30, 22, 26);
		panel_7.add(label_26);
		
		JLabel label_27 = new JLabel(" 1");
		label_27.setForeground(Color.DARK_GRAY);
		label_27.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_27.setBounds(48, 30, 22, 26);
		panel_7.add(label_27);
		
		JLabel label_28 = new JLabel(" 1");
		label_28.setForeground(Color.DARK_GRAY);
		label_28.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_28.setBounds(48, 62, 22, 26);
		panel_7.add(label_28);
		
		JLabel label_29 = new JLabel(" 1");
		label_29.setForeground(Color.DARK_GRAY);
		label_29.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_29.setBounds(24, 62, 22, 26);
		panel_7.add(label_29);
		
		JLabel label_30 = new JLabel(" 1");
		label_30.setForeground(Color.DARK_GRAY);
		label_30.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_30.setBounds(0, 62, 22, 26);
		panel_7.add(label_30);
		
		JPanel panel_8 = new JPanel();
		panel_8.setForeground(Color.DARK_GRAY);
		panel_8.setBackground(new Color(127, 255, 212));
		panel_8.setBounds(74, 356, 70, 88);
		panel_2.add(panel_8);
		panel_8.setLayout(null);
		
		JLabel label_4 = new JLabel("");
		ImageIcon imageIcon4 = new ImageIcon(new ImageIcon(UI.class.getResource("/meta/projet/grid-27529_1280.png")).getImage().getScaledInstance(70, 88, Image.SCALE_DEFAULT));
		label_4.setIcon(imageIcon4);
		label_4.setBounds(0, 0, 71, 88);
		panel_8.add(label_4);
		
		JLabel label_40 = new JLabel(" 1");
		label_40.setForeground(Color.DARK_GRAY);
		label_40.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_40.setBounds(24, 0, 22, 26);
		panel_8.add(label_40);
		
		JLabel label_41 = new JLabel(" 1");
		label_41.setForeground(Color.DARK_GRAY);
		label_41.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_41.setBounds(48, 0, 22, 26);
		panel_8.add(label_41);
		
		JLabel label_42 = new JLabel(" 1");
		label_42.setForeground(Color.DARK_GRAY);
		label_42.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_42.setBounds(0, 0, 22, 26);
		panel_8.add(label_42);
		
		JLabel label_43 = new JLabel(" 1");
		label_43.setForeground(Color.DARK_GRAY);
		label_43.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_43.setBounds(0, 30, 22, 26);
		panel_8.add(label_43);
		
		JLabel label_44 = new JLabel(" 1");
		label_44.setForeground(Color.DARK_GRAY);
		label_44.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_44.setBounds(24, 30, 22, 26);
		panel_8.add(label_44);
		
		JLabel label_45 = new JLabel(" 1");
		label_45.setForeground(Color.DARK_GRAY);
		label_45.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_45.setBounds(48, 30, 22, 26);
		panel_8.add(label_45);
		
		JLabel label_46 = new JLabel(" 1");
		label_46.setForeground(Color.DARK_GRAY);
		label_46.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_46.setBounds(48, 62, 22, 26);
		panel_8.add(label_46);
		
		JLabel label_47 = new JLabel(" 1");
		label_47.setForeground(Color.DARK_GRAY);
		label_47.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_47.setBounds(24, 62, 22, 26);
		panel_8.add(label_47);
		
		JLabel label_48 = new JLabel(" 1");
		label_48.setForeground(Color.DARK_GRAY);
		label_48.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_48.setBounds(0, 62, 22, 26);
		panel_8.add(label_48);
		
		
		
		
		JLabel lblSolutionOptimale = new JLabel("Solution Optimale");
		lblSolutionOptimale.setBounds(10, 11, 340, 37);
		panel_1.add(lblSolutionOptimale);
		lblSolutionOptimale.setForeground(new Color(127, 255, 212));
		lblSolutionOptimale.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		JLabel lblEnsembleOuvert = new JLabel("Ensemble Ouvert");
		lblEnsembleOuvert.setForeground(new Color(127, 255, 212));
		lblEnsembleOuvert.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnsembleOuvert.setBounds(279, 59, 149, 37);
		panel_1.add(lblEnsembleOuvert);
		
		JLabel label = new JLabel("Ensemble Ouvert");
		label.setForeground(new Color(127, 255, 212));
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(279, 353, 149, 37);
		panel_1.add(label);
		
		JTextArea textArea = new JTextArea();
		textArea.setForeground(Color.DARK_GRAY);
		textArea.setBounds(279, 88, 124, 232);
		panel_1.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setForeground(Color.DARK_GRAY);
		textArea_1.setBounds(279, 382, 124, 232);
		panel_1.add(textArea_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setForeground(new Color(127, 255, 212));
		panel_3.setBackground(Color.DARK_GRAY);
		panel_3.setBounds(453, 71, 528, 543);
		panel_1.add(panel_3);
		
		JLabel lblComparaison = new JLabel("Comparaison");
		lblComparaison.setForeground(new Color(127, 255, 212));
		lblComparaison.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblComparaison.setBounds(453, 28, 149, 37);
		panel_1.add(lblComparaison);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(313, 162, 66, 54);
		panel_1.add(lblNewLabel_1);
		frmProjetMetaheuristique.setTitle("Projet Meta-Heuristique");
		frmProjetMetaheuristique.setIconImage(Toolkit.getDefaultToolkit().getImage(UI.class.getResource("/meta/projet/dot_matrix.png")));
		frmProjetMetaheuristique.setBackground(Color.DARK_GRAY);
		frmProjetMetaheuristique.setBounds(100, 100, 1000, 700);
		frmProjetMetaheuristique.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProjetMetaheuristique.setUndecorated(true);
	}
}
