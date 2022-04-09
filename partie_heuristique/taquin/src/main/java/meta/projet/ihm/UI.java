package meta.projet.ihm;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import java.util.Collection;

import meta.projet.classesi.solver.Node;
import meta.projet.classesi.solver.AStar;
import meta.projet.classesi.solver.DFS;
import meta.projet.classesi.solver.BFS;
import meta.projet.classesi.solver.heuristic.MinMoves;
import meta.projet.classesi.solver.heuristic.MissPlaced;



public class UI {

	private JFrame frmProjetMetaheuristique;
	private JPanel panel_2 = new JPanel(),panel_4 = new JPanel(), panel_6 = new JPanel(), panel_3 = new JPanel(), panel_5 = new JPanel(),panel_1 = new JPanel();
	private JTextField textField_1 = new JTextField(), textField_3, textField_5 = new JTextField(),textField_9  = new JTextField(), textField_11  = new JTextField() ;
	private JComboBox<String> comboBox = new JComboBox<String>();
	private JList<Integer> list = new JList<Integer>(), list_1 = new JList<Integer>(), list_2 = new JList<Integer>(), list_3 = new JList<Integer>(), list_4= new JList<Integer>(), list_5 = new JList<Integer>();

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
	
	
	public int getMaxNiveauAStar() {
		return Integer.parseInt(textField_3.getText());
	}
	
	public int getEtatInitAStar() {
		if(textField_1.getText().length() == 9)
			return Integer.parseInt(textField_1.getText());
		else 
			return 283164705;
	}
	
	public void showSolutionAStar(int rows, int cols) {
		GridLayout grid = new GridLayout(rows, cols,10,10);
		
		panel_2.setLayout(grid);
		
		String heuristique = comboBox.getSelectedItem().toString();
		AStar solver;
		
		if(heuristique.equals("MinMoves")){		
		solver = new AStar(
				getEtatInitAStar(),
                123804765,
                new MinMoves(),
                getMaxNiveauAStar()
            );
		}else{
			solver = new AStar(
					getEtatInitAStar(),
	                123804765,
	                new MissPlaced(),
	                getMaxNiveauAStar()
	            );
		}
            if (solver.solve()) {
                
                for (Node node = solver.getSolution(); node != null; node = node.getParent()) {
                	
                	//Créer une novelle matrice 
                	
                	JPanel p = new JPanel();
            		p.setForeground(Color.DARK_GRAY);
            		p.setBackground(new Color(0, 191, 255));
            		p.setBounds(160, 2, 70, 88);
            		panel_2.add(p);
            		
                	
                	// afficher le chemain de la solu
            		
            		format_matrice(node.getState(),p);
                	
                } 
            } else {
            	panel_2.removeAll();
            	list.removeAll();
            	list_1.removeAll();
            	JOptionPane.showMessageDialog(null, "Vous ne pouvez pas avoir une solution avec ces paramètres, veuillez les changer");          	
            }
            
            DefaultListModel<Integer> model = new DefaultListModel<Integer>();
            DefaultListModel<Integer> model_1 = new DefaultListModel<Integer>();
            Collection<Node>  op = solver.getOpened();
            Collection<Node>  clo = solver.getClosed();
            
      
            list.setModel(model);
            list.removeAll();
            
            list_1.setModel(model_1);
            list_1.removeAll();
            
            model.removeAllElements();
            model_1.removeAllElements();
            
            for(Node n: op) {
            	model.addElement(n.getState());
            }
            for(Node n: clo) {
            	model_1.addElement(n.getState());
            }
           

		
	}
	
	
	public int getEtatInitbfs() {
		if(textField_5.getText().length() == 9)
			return Integer.parseInt(textField_5.getText());
		else 
			return 283164705;
	}
	
	
	
	public void showSolutionbfs(int rows, int cols) {
		
		
		GridLayout grid = new GridLayout(rows, cols,10,10);
		
		panel_4.setLayout(grid);
        BFS bfss = new BFS(getEtatInitbfs(), 123804765); 
          

            if (bfss.solve()) {
                String solu = bfss.DisplayResolutionPath();
                String[] arrOfSolu = solu.split("<--");
                
                for (String a : arrOfSolu) {

                	a = a.replaceAll(" ", "");
                	//Créer une novelle matrice 
                	
                	JPanel panell = new JPanel();
            		panell.setForeground(Color.DARK_GRAY);
            		panell.setBackground(new Color(0, 191, 255));
            		panell.setBounds(160, 2, 70, 88);
            		panel_4.add(panell);
            		
                	// afficher le chemain de la solu

            		
            		format_matrice(Integer.parseInt(a),panell);         	

                } 
            } else {
            	
            	panel_4.removeAll();
            	list_2.removeAll();
            	list_3.removeAll();
            	JOptionPane.showMessageDialog(null, "Vous ne pouvez pas avoir une solution avec ces paramètres, veuillez les changer");          	
            }
            
            DefaultListModel<Integer> modell = new DefaultListModel<Integer>();
            DefaultListModel<Integer> modell_1 = new DefaultListModel<Integer>();
            Collection<Node>  op = bfss.getOpened();
            Collection<Node>  clo = bfss.getClosed();
   
            list_2.setModel(modell);
            list_2.removeAll();
            
         
            list_3.setModel(modell_1);
            list_3.removeAll();
            
            modell.removeAllElements();
            modell_1.removeAllElements();
            
            for(Node n: op) {
            	modell.addElement(n.getState());
            }
            for(Node n: clo) {
            	modell_1.addElement(n.getState());
            }
           
		
	}
	
	public int getEtatInitdfs() {
		if(textField_9.getText().length() == 9)
			return Integer.parseInt(textField_9.getText());
		else 
			return 283164705;
	}
	
	public int getMaxNiveauDfs() {
		return Integer.parseInt(textField_11.getText());
	}
	
	public void showSolutiondfs(int rows, int cols) {
		
		GridLayout grid = new GridLayout(rows, cols,10,10);
		
		panel_6.setLayout(grid);
		
		
        DFS dfss = new DFS(getEtatInitdfs(), 123804765,getMaxNiveauDfs()); 
          

            if (dfss.solve()) {
                String solu = dfss.DisplayResolutionPath();
                String[] arrOfSolu = solu.split("<--");
                
                for (String a : arrOfSolu) {
                	
                	a = a.replaceAll(" ", "");
                	//Créer une novelle matrice 
                	
                	JPanel panelll1 = new JPanel();
            		panelll1.setForeground(Color.DARK_GRAY);
            		panelll1.setBackground(new Color(0, 191, 255));
            		panelll1.setBounds(160, 2, 70, 88);
            		panel_6.add(panelll1);
            		
                	// afficher le chemin de la solu
            		
            		format_matrice(Integer.parseInt(a),panelll1);
            		


                } 
            } else {
            	panel_6.removeAll();
            	list_4.removeAll();
            	list_5.removeAll();
            	JOptionPane.showMessageDialog(null, "Vous ne pouvez pas avoir une solution avec ces paramètres, veuillez les changer");          	
            }
            
            DefaultListModel<Integer> modelll = new DefaultListModel<Integer>();
            DefaultListModel<Integer> modelll_1 = new DefaultListModel<Integer>();
            Collection<Node>  op = dfss.getOpened();
            Collection<Node>  clo = dfss.getClosed();
   
            list_4.setModel(modelll);
            list_4.removeAll();
            
         
            list_5.setModel(modelll_1);
            list_5.removeAll();
            
            modelll.removeAllElements();
            modelll_1.removeAllElements();
            
            for(Node n: op) {
            	modelll.addElement(n.getState());
            }
            for(Node n: clo) {
            	modelll_1.addElement(n.getState());
            }
           
		
	}
	
    public static void format_matrice(int code, JPanel panel)
    {
        
    	panel.setLayout(new GridLayout(3,3));
    	int j = 0, k = 100000000;

        for(int i=0;i<9;++i)
        {
            int a = (code / k) % 10;
            k /= 10;
            JLabel c = new JLabel("  ".concat(Integer.toString(a)));
            c.setSize(20, 20);
            c.setFont(new Font("Tahoma", Font.BOLD, 30));
            c.setForeground(Color.DARK_GRAY);
            panel.add(c);
            j++;
            if(j==3) j = 0;
            
        }
    }

	
	private void initialize() {
		frmProjetMetaheuristique = new JFrame();
		frmProjetMetaheuristique.getContentPane().setBackground(Color.DARK_GRAY);
		frmProjetMetaheuristique.getContentPane().setLayout(null);
		
		JPanel panel = new MotionPanel(frmProjetMetaheuristique);
		panel.setBounds(0, 0, 1000, 42);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), null, null, new Color(0, 191, 255)));
		panel.setBackground(Color.DARK_GRAY);
		frmProjetMetaheuristique.getContentPane().add(panel);
		panel.setLayout(null);
		
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), null, null, null));
		panel_6.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), null, null, null));
		
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
		lblNewLabel.setForeground(new Color(0, 191, 255));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 191, 255), null, null, null));
		tabbedPane.setBounds(0, 43, 1000, 657);
		tabbedPane.setBackground(Color.DARK_GRAY);
		frmProjetMetaheuristique.getContentPane().add(tabbedPane);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), null, null, null));
		panel_1.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Algorithme A*", null, panel_1, null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 59, 391, 555);
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), null, null, null));
		scrollPane.getVerticalScrollBar().setVisible(false);
		panel_1.setLayout(null);
		panel_1.add(scrollPane);
		
		
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(834, 97, 121, 223);
        panel_1.add(scrollPane_1);
        scrollPane_1.setViewportView(list_1);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), null, null, null));
		
		scrollPane.setViewportView(panel_2);
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setLayout(null);
		
		JLabel lblEtatInitial = new JLabel("Etat Final");
		lblEtatInitial.setBounds(13, 2, 70, 88);
		panel_2.add(lblEtatInitial);
		lblEtatInitial.setForeground(new Color(0, 191, 255));
		lblEtatInitial.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		
		JLabel lblSolutionOptimale = new JLabel("Solution Optimale");
		lblSolutionOptimale.setBounds(10, 11, 340, 37);
		panel_1.add(lblSolutionOptimale);
		lblSolutionOptimale.setForeground(new Color(0, 191, 255));
		lblSolutionOptimale.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		JLabel lblEnsembleOuvert = new JLabel("Ensemble Ouvert");
		lblEnsembleOuvert.setBounds(464, 57, 149, 37);
		lblEnsembleOuvert.setForeground(new Color(0, 191, 255));
		lblEnsembleOuvert.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(lblEnsembleOuvert);
		
		JLabel lblEnsembleFerm_2 = new JLabel("Ensemble Fermé");
		lblEnsembleFerm_2.setBounds(832, 59, 149, 37);
		lblEnsembleFerm_2.setForeground(new Color(0, 191, 255));
		lblEnsembleFerm_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(lblEnsembleFerm_2);
		
		
		
		JLabel lblEtatInitial_1 = new JLabel("Etat Initial");
		lblEtatInitial_1.setBounds(464, 375, 149, 37);
		lblEtatInitial_1.setForeground(new Color(0, 191, 255));
		lblEtatInitial_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(lblEtatInitial_1);
		textField_1.setBounds(464, 411, 178, 31);
		
		
		textField_1.setText("283164705");
		textField_1.setForeground(Color.DARK_GRAY);
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_1.setColumns(10);
		textField_1.setBackground(Color.WHITE);
		panel_1.add(textField_1);
		
		JLabel lblNiveauMaximum = new JLabel("Niveau Maximum");
		lblNiveauMaximum.setBounds(777, 375, 149, 37);
		lblNiveauMaximum.setForeground(new Color(0, 191, 255));
		lblNiveauMaximum.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(lblNiveauMaximum);
		
        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBounds(465, 97, 121, 223);
        panel_1.add(scrollPane1);
        scrollPane1.setViewportView(list);
		
		textField_3 = new JTextField();
		textField_3.setBounds(777, 411, 178, 31);
		textField_3.setText("5");
		textField_3.setForeground(Color.DARK_GRAY);
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_3.setEditable(true);
		textField_3.setColumns(10);
		textField_3.setBackground(Color.WHITE);
		panel_1.add(textField_3);
		
		JButton btnNewButton = new JButton("Résoudre");
		btnNewButton.setBounds(818, 509, 137, 38);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_2.removeAll();
				
				
				JLabel lblEtatInitial = new JLabel("Etat Final");
				lblEtatInitial.setBounds(13, 2, 70, 88);
				panel_2.add(lblEtatInitial);
				lblEtatInitial.setForeground(new Color(0, 191, 255));
				lblEtatInitial.setFont(new Font("Tahoma", Font.PLAIN, 16));
				
				showSolutionAStar(5,3);
				
				
				JLabel label_49 = new JLabel();
				label_49.setBounds(315, 441, 70, 88);
				label_49.setIcon(new ImageIcon(new ImageIcon(UI.class.getResource("/2026901.png")).getImage().getScaledInstance(label_49.getWidth(), label_49.getHeight(), Image.SCALE_SMOOTH)));
				label_49.setForeground(new Color(0, 191, 255));
				label_49.setFont(new Font("Tahoma", Font.PLAIN, 16));
				
				panel_2.add(label_49);
				
			}
		});
		btnNewButton.setBackground(new Color(0, 191, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setForeground(Color.DARK_GRAY);
		panel_1.add(btnNewButton);
		frmProjetMetaheuristique.setTitle("Projet Meta-Heuristique");
		frmProjetMetaheuristique.setIconImage(Toolkit.getDefaultToolkit().getImage(UI.class.getResource("/dot_matrix.png")));
		frmProjetMetaheuristique.setBackground(Color.DARK_GRAY);
		frmProjetMetaheuristique.setBounds(100, 100, 1000, 700);
		frmProjetMetaheuristique.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProjetMetaheuristique.setUndecorated(true);
		
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"MinMoves", "MissPlaced"}));
	    comboBox.setBounds(464, 518, 178, 22);
	    panel_1.add(comboBox);
		
		
		
		showSolutionAStar(5,3);
		
		panel_3.setLayout(null);
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), null, null, null));
		panel_3.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Algorithme BFS", null, panel_3, null);
		
		textField_5.setText("283164705");
		textField_5.setForeground(Color.DARK_GRAY);
		textField_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_5.setColumns(10);
		textField_5.setBackground(Color.WHITE);
		textField_5.setBounds(464, 411, 178, 31);
		panel_3.add(textField_5);
		
		JLabel label_49 = new JLabel("Etat Initial");
		label_49.setForeground(new Color(0, 191, 255));
		label_49.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_49.setBounds(269, 399, 106, 88);
		label_49.setIcon(new ImageIcon(new ImageIcon(UI.class.getResource("/2026901.png")).getImage().getScaledInstance(label_49.getWidth(), label_49.getHeight(), Image.SCALE_SMOOTH)));
		
	    panel_2.add(label_49);
	    
	    
	    
	    
		JScrollPane scrollPane_11 = new JScrollPane();
		scrollPane_11.setBounds(0, 59, 391, 555);
		panel_3.add(scrollPane_11);
		
		
		panel_4.setBackground(Color.DARK_GRAY);
		panel_4.setForeground(new Color(0, 191, 255));
		scrollPane_11.setViewportView(panel_4);
		

		
		JLabel label_15 = new JLabel("Etat Final");
		label_15.setForeground(new Color(0, 191, 255));
		label_15.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_15.setBounds(2, 4, 385, 101);
		panel_4.add(label_15);
		
		showSolutionbfs(5, 3);
		
		JLabel label_16 = new JLabel();
		label_16.setForeground(new Color(0, 191, 255));
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_16.setBounds(2, 115, 108, 101);
		label_16.setIcon(new ImageIcon(new ImageIcon(UI.class.getResource("/2026901.png")).getImage().getScaledInstance(label_16.getWidth(), label_16.getHeight(), Image.SCALE_SMOOTH)));
		
		panel_4.add(label_16);
		
		JLabel label_1 = new JLabel("Solution Optimale");
		label_1.setForeground(new Color(0, 191, 255));
		label_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_1.setBounds(10, 11, 340, 37);
		panel_3.add(label_1);
		
		JLabel label_2 = new JLabel("Ensemble Ouvert");
		label_2.setForeground(new Color(0, 191, 255));
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(464, 57, 149, 37);
		panel_3.add(label_2);
		
		JLabel lblEnsembleFerm_1 = new JLabel("Ensemble Fermé");
		lblEnsembleFerm_1.setForeground(new Color(0, 191, 255));
		lblEnsembleFerm_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnsembleFerm_1.setBounds(832, 59, 149, 37);
		panel_3.add(lblEnsembleFerm_1);
		
		JLabel label_5 = new JLabel("Etat Initial");
		label_5.setForeground(new Color(0, 191, 255));
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_5.setBounds(464, 375, 149, 37);
		panel_3.add(label_5);
		
		
		
		JButton button = new JButton("Résoudre");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				panel_4.removeAll();
				list_2.removeAll();
				list_3.removeAll();
				JLabel lblEtatInitial = new JLabel("Etat Final");
				lblEtatInitial.setBounds(13, 2, 70, 88);
				panel_4.add(lblEtatInitial);
				lblEtatInitial.setForeground(new Color(0, 191, 255));
				lblEtatInitial.setFont(new Font("Tahoma", Font.PLAIN, 16));
				
				showSolutionbfs(10,10);
				
				JLabel label_49 = new JLabel();
				label_49.setBounds(315, 441, 70, 88);
				label_49.setIcon(new ImageIcon(new ImageIcon(UI.class.getResource("/2026901.png")).getImage().getScaledInstance(label_49.getWidth(), label_49.getHeight(), Image.SCALE_SMOOTH)));
				label_49.setForeground(new Color(0, 191, 255));
				label_49.setFont(new Font("Tahoma", Font.PLAIN, 16));				
				panel_4.add(label_49);
			}
		});
		button.setForeground(Color.DARK_GRAY);
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setBackground(new Color(0, 191, 255));
		button.setBounds(464, 487, 137, 38);
		panel_3.add(button);
		
		
		panel_5.setLayout(null);
		panel_5.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), null, null, null));
		panel_5.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Algorithme DFS", null, panel_5, null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 59, 391, 555);
		panel_5.add(scrollPane_2);
		
		
		JScrollPane scrollPanel_1 = new JScrollPane();
		scrollPanel_1.setBounds(474, 105, 121, 223);
        panel_5.add(scrollPanel_1);
        scrollPanel_1.setViewportView(list_4);
        
        
        JScrollPane scrollPanell_1 = new JScrollPane();
        scrollPanell_1.setBounds(832, 105, 121, 223);
        panel_5.add(scrollPanell_1);
        scrollPanell_1.setViewportView(list_5);
		
		
		textField_9 = new JTextField();
		textField_9.setText("283164705");
		textField_9.setForeground(Color.DARK_GRAY);
		textField_9.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_9.setColumns(10);
		textField_9.setBackground(Color.WHITE);
		textField_9.setBounds(464, 411, 178, 31);
		panel_5.add(textField_9);
		panel_6.setBackground(Color.DARK_GRAY);
		scrollPane_2.setViewportView(panel_6);
		panel_6.setLayout(null);
		panel_6.setLayout(null);
		
		JLabel label_6 = new JLabel("Etat Final");
		label_6.setBounds(2, 4, 385, 101);
		label_6.setForeground(new Color(0, 191, 255));
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_6.add(label_6);
		
		
		
		textField_11 = new JTextField();
		textField_11.setText("4");
		textField_11.setForeground(Color.DARK_GRAY);
		textField_11.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_11.setEditable(true);
		textField_11.setColumns(10);
		textField_11.setBackground(Color.WHITE);
		textField_11.setBounds(464, 512, 178, 31);
		panel_5.add(textField_11);
		
		
		
		showSolutiondfs(5,3);
		
		JLabel label_17 = new JLabel();
		label_17.setBounds(2, 115, 112, 101);
		label_17.setForeground(new Color(0, 191, 255));
		label_17.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_17.setIcon(new ImageIcon(new ImageIcon(UI.class.getResource("/2026901.png")).getImage().getScaledInstance(label_17.getWidth(), label_17.getHeight(), Image.SCALE_SMOOTH)));
		panel_6.add(label_17);
		
		JLabel label_8 = new JLabel("Solution Optimale");
		label_8.setForeground(new Color(0, 191, 255));
		label_8.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_8.setBounds(10, 11, 340, 37);
		panel_5.add(label_8);
		
		JLabel label_9 = new JLabel("Ensemble Ouvert");
		label_9.setForeground(new Color(0, 191, 255));
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_9.setBounds(464, 57, 149, 37);
		panel_5.add(label_9);
		
		JLabel lblEnsembleFerm = new JLabel("Ensemble Fermé");
		lblEnsembleFerm.setForeground(new Color(0, 191, 255));
		lblEnsembleFerm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnsembleFerm.setBounds(832, 59, 149, 37);
		panel_5.add(lblEnsembleFerm);
		
		JLabel label_12 = new JLabel("Etat Initial");
		label_12.setForeground(new Color(0, 191, 255));
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_12.setBounds(464, 375, 149, 37);
		panel_5.add(label_12);
	
		
		
		
		JScrollPane scrollPane_111 = new JScrollPane();
        scrollPane_111.setBounds(465, 97, 121, 223);
        panel_3.add(scrollPane_111);
        scrollPane_111.setViewportView(list_2);
        
        JScrollPane scrollPane_1111 = new JScrollPane();
        scrollPane_1111.setBounds(834, 97, 121, 223);
        panel_3.add(scrollPane_1111);
        scrollPane_1111.setViewportView(list_3);
        
		
		JLabel label_14 = new JLabel("Niveau Maximum");
		label_14.setForeground(new Color(0, 191, 255));
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_14.setBounds(464, 476, 149, 37);
		panel_5.add(label_14);
		
		
		
		JButton button_1 = new JButton("Résoudre");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				panel_6.removeAll();
				list_4.removeAll();
				list_5.removeAll();
				JLabel lblEtatInitial = new JLabel("Etat Final");
				lblEtatInitial.setBounds(13, 2, 70, 88);
				panel_6.add(lblEtatInitial);
				lblEtatInitial.setForeground(new Color(0, 191, 255));
				lblEtatInitial.setFont(new Font("Tahoma", Font.PLAIN, 16));
				
				showSolutiondfs(10, 10);
				
				JLabel label_49 = new JLabel();
				label_49.setBounds(315, 441, 70, 88);
				label_49.setIcon(new ImageIcon(new ImageIcon(UI.class.getResource("/2026901.png")).getImage().getScaledInstance(label_49.getWidth(), label_49.getHeight(), Image.SCALE_SMOOTH)));
				label_49.setForeground(new Color(0, 191, 255));
				label_49.setFont(new Font("Tahoma", Font.PLAIN, 16));
				panel_6.add(label_49);
			}
		});
		button_1.setForeground(Color.DARK_GRAY);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_1.setBackground(new Color(0, 191, 255));
		button_1.setBounds(816, 454, 137, 38);
		panel_5.add(button_1);
		
		
		
		
		
	}
}
