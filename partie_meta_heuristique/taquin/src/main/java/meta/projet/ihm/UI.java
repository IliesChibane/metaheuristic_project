package meta.projet.ihm;

import java.awt.EventQueue;
import java.awt.Color;


import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import java.io.FileWriter;
import java.io.IOException;

import meta.projet.solver.GASolver;
import meta.projet.solver.PSOSolver;
import meta.projet.solver.heuristic.MinMoves;
import javax.swing.JTable;

public class UI extends Thread{

	private JFrame frmProjetMetaheuristique;
	private JPanel panel_1 = new JPanel();
	private short[] solutionsLengths = {5, 10, 13, 18, 20, 24, 30, 30};
    private short[][][] initialStates = {
            {{2, 8, 3}, {1, 6, 4}, {7, 0, 5}}, // 5
            {{3, 6, 4}, {1, 0, 2}, {8, 7, 5}}, // 10
            {{3, 0, 4}, {1, 6, 5}, {8, 2, 7}}, // 13
            {{2, 1, 3}, {8, 0, 4}, {6, 7, 5}}, // 18
            {{4, 2, 0}, {8, 3, 6}, {7, 5, 1}}, // 20
            {{1, 6, 3}, {4, 0, 8}, {7, 2, 5}}, // 24
            {{5, 6, 7}, {4, 0, 8}, {3, 2, 1}}, // 30
            {{5, 2, 7}, {8, 0, 4}, {3, 6, 1}}, // 30
        };
 
        private int[] initialStatesInt = 
        {
            283164705, // 5
            364102875, // 10
            304165827, // 13
            213804675, // 18
            420836751, // 20
            163408725, // 24
            567408321, // 30
            527804361 // 30
        };
        private short[][] targetState = {{1, 2, 3}, {8, 0, 4}, {7, 6, 5}};
        private int[] populationSizesGA = {50,10,10,10,10,10,50,50};
        private int[] numberOfCrossovers = {50,50,50,50,50,50,50,50};
        private int[] numberOfMutations = {150,100,150,150,100,100,100,50};
        private int[] tolerancesGA = {60,30,30,30,30,100,100,100};
    
        private int[] populationSizesPSO = {100,1000,1000,1000,1000,1000,500,1000};
    
        private int[] tolerancesPSO = {7,7,5,7,10,7,10,10};
    
        private long startTime, endTime;
        private JTable table;
        


        private DefaultTableModel dtm, dtmm;
        private JTable table_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.getFrmProjetMetaheuristique().setVisible(true);
					window.getFrmProjetMetaheuristique().setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public UI(){		
		

		initialize();

		
		
	}
	
	public DefaultTableModel setUpTable(JTable table, String[] header, String[] tooltip) {
        dtm = new DefaultTableModel(header,0);
        table.setModel(dtm);
        
        StringBuilder builder = new StringBuilder();
        for(String s : tooltip) {
            builder.append(s);
        }
        String str = builder.toString();
        
        table.getTableHeader().setToolTipText(str);

        return dtm;
	}

	
	public void solutionGA() throws IOException{
		
		
		
		FileWriter resultsGA = null;
		

			resultsGA = new FileWriter("resultsGA.csv");
	        resultsGA.write("initial state; best solution length; population size; initial sequence length; max iterations; selection ratio; number of crossovers; number of mutations; crossover ratio; tolerance; solution best fitness; solution iteration; solution length; execution time\n");
			String header [] = {"Init state","Best Solu len","Popul Size","Init seq len","Max iter","Select ratio","NB crossovers","NB mutations","Cross ratio", "solu best fit", "solu iter", "solu len", "exe time (ns)" };
			String tooltip [] = {"initial state ", "best solution length ", "population size ", "initial sequence length ", "max iterations ", "selection ratio ", "number of crossovers ", "number of mutations ", "crossover ratio ", "tolerance ", "solution best fitness ", "solution iteration ", "solution length ", "execution time "};
			dtm = setUpTable(table, header, tooltip);

			 for (int i = 0; i < initialStates.length; ++i) {
	                         
	                             GASolver solver = new GASolver(
	                                 initialStates[i], // initialState
	                                 targetState, // targetState
	                                 populationSizesGA[i], // populationSize
	                                 new MinMoves(), // heuristic
	                                 1, // initialSequenceLength
	                                 5000, // maxIter
	                                 0.5f, // selectionRation
	                                 numberOfCrossovers[i], // numberOfCrossovers
	                                 numberOfMutations[i], // numberOfMutations
	                                 0.5f, // crossoverRatio
	                                 tolerancesGA[i] // tolerance 
	                             );
	                       
	                             
	                             startTime = System.nanoTime();
	                             solver.solve();
	                             endTime = System.nanoTime();
	                            
	                             dtm.addRow(new Object[]{initialStatesInt[i], solutionsLengths[i],populationSizesGA[i],"1","5000",numberOfCrossovers[i],numberOfMutations[i],"0.5",tolerancesGA[i],solver.getBestFitness(),solver.getSolutionIteration(),solver.getSolution().length,(endTime - startTime)});

	                             
	                          
									resultsGA.write(initialStatesInt[i] + "; " +
									     solutionsLengths[i] + "; " +
									     populationSizesGA[i] + "; " +
									     "1; " +
									     "5000; " +
									     "0.5; " +
									     numberOfCrossovers[i] + "; " +
									     numberOfMutations[i] + "; " +
									     "0.5; " +
									     tolerancesGA[i] + "; " +
									     solver.getBestFitness() + "; " +
									     solver.getSolutionIteration() + "; " +
									     solver.getSolution().length + "; " +
									     (endTime - startTime) + "\n"
									 );
	         }
	       
				resultsGA.close();

         table.setModel(dtm);

	}
	
	public void solutionPSO() throws IOException {
		
		FileWriter resultsPSO = new FileWriter("resultsPSO.csv");
		
        resultsPSO.write("initial state; best solution length; initial sequence length; max iterations; number of particles; w; c1; c2; initial velocity; tolerance; solution best fitness; solution iteration; solution length; execution time\n");
		String header1 [] = {"Init state","Best Solu len","Init seq len","Max iter","NB particles","w","C1", "C2", "Init velocity", "Tolerance", "solu best fit","solu iter","solu len","exe time (ns)" };
		String tooltip1 [] = { "initial state ", "best solution length ", "initial sequence length ", "max iterations ", "number of particles ", "w ", "c1 ", "c2 ", "initial velocity ", "tolerance ", "solution best fitness ", "solution iteration ", "solution length ", "execution time "}; 
		dtmm = setUpTable(table_1, header1, tooltip1);

		 for (int i = 0; i < initialStates.length; ++i) 
	        {
	                    PSOSolver s = new PSOSolver(initialStates[i], targetState, 30.0f, 1.5f, 1.0f, 500, populationSizesPSO[i], (float)1, tolerancesPSO[i]);
	    
	                    startTime = System.nanoTime();
	                    s.solve();
	                    endTime = System.nanoTime();
	                    
	                    resultsPSO.write(initialStatesInt[i] + ", " +
	                                    solutionsLengths[i] + ", " +
	                                    "5, " +
	                                    "500, " +
	                                    populationSizesPSO[i] + ", " +
	                                    "30, " +
	                                    "1.5, " +
	                                    "1, " +
	                                    "1, " +
	                                    tolerancesPSO[i] + ", " +
	                                    s.getCurrentBestEvaluation() + ", " +
	                                    s.getSolutionIteration() + ", " +
	                                    s.getSequenceSize() + ", " +
	                                    (endTime - startTime) + "\n"
	                                    );             
	                    
	                    dtmm.addRow(new Object[]{initialStatesInt[i], solutionsLengths[i],"5","500",populationSizesPSO[i],"30","1.5","1","1",tolerancesPSO[i],s.getCurrentBestEvaluation(),s.getSolutionIteration(),s.getSequenceSize(),(endTime - startTime)});

	    
	                
	            
	        }
	        resultsPSO.close();
        table_1.setModel(dtmm);


	}
		

	private void initialize(){
		
		
		setFrmProjetMetaheuristique(new JFrame());
		getFrmProjetMetaheuristique().getContentPane().setBackground(Color.DARK_GRAY);
		getFrmProjetMetaheuristique().getContentPane().setLayout(null);
		
		JPanel panel = new MotionPanel(getFrmProjetMetaheuristique());
		panel.setBounds(0, 0, 1200, 42);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), null, null, new Color(0, 191, 255)));
		panel.setBackground(Color.DARK_GRAY);
		getFrmProjetMetaheuristique().getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.setBounds(1169, 0, 21, 37);
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
		tabbedPane.setBounds(0, 43, 1200, 657);
		tabbedPane.setBackground(Color.DARK_GRAY);
		getFrmProjetMetaheuristique().getContentPane().add(tabbedPane);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), null, null, null));
		panel_1.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Algorithme GA", null, panel_1, null);
		panel_1.setLayout(null);
		
		
		JLabel lblSolutionOptimale = new JLabel("Solution Optimale");
		lblSolutionOptimale.setBounds(10, 11, 340, 37);
		panel_1.add(lblSolutionOptimale);
		lblSolutionOptimale.setForeground(new Color(0, 191, 255));
		lblSolutionOptimale.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 1171, 556);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 191, 255), null, null, null));
		panel_3.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Algorithme PSO", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel label = new JLabel("Solution Optimale");
		label.setBounds(10, 11, 340, 37);
		label.setForeground(new Color(0, 191, 255));
		label.setFont(new Font("Tahoma", Font.BOLD, 25));
		panel_3.add(label);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 59, 1169, 555);
		panel_3.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		getFrmProjetMetaheuristique().setTitle("Projet Meta-Heuristique");
		getFrmProjetMetaheuristique().setBackground(Color.DARK_GRAY);
		getFrmProjetMetaheuristique().setBounds(100, 100, 1200, 700);
		getFrmProjetMetaheuristique().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmProjetMetaheuristique().setUndecorated(true);
		

	}
	

	@Override
	public void run() {
		try {
			solutionGA();
			solutionPSO();
			Frame[] allFrames = Frame.getFrames();
			
			for(Frame fr : allFrames){ 		
			    String specificFrameName = fr.getClass().getName();
			    if(specificFrameName.equals("meta.projet.ihm.BAR")){
			        fr.dispose();
			    }
			}			

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public JFrame getFrmProjetMetaheuristique() {
		return frmProjetMetaheuristique;
	}

	public void setFrmProjetMetaheuristique(JFrame frmProjetMetaheuristique) {
		this.frmProjetMetaheuristique = frmProjetMetaheuristique;
	}
}
