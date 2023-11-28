package pokemon;


import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Cursor;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//public static Pokemon currentPokemon = // TODO we need to keep track of current pokemon. We can do this by cycling through a pokemon list or somehting. 
	//Initialize a varialble to keep track of the pokemon ID we're on. 
	public int currentPokemonID = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		mainCenterPanel();
	}

	private void mainCenterPanel() {
		JPanel mainCenterPanel = new JPanel();
		contentPane.add(mainCenterPanel, BorderLayout.CENTER);
		mainCenterPanel.setLayout(new BorderLayout(0, 0));
		
		leftDisplay(mainCenterPanel);
		JLabel[] statValueLabels = {new JLabel("1"),new JLabel("Charmander"), 
				new JLabel("Fire"), new JLabel("39"), new JLabel("52"), 
				new JLabel("43"), new JLabel("60"),new JLabel("50"),
				new JLabel("65"), new JLabel("Charmeleon")};
		
		rightDisplay(mainCenterPanel, statValueLabels);
		displayButtons();
	}

	private void leftDisplay(JPanel mainCenterPanel) {
		JPanel leftDisplay = new JPanel();
		mainCenterPanel.add(leftDisplay, BorderLayout.WEST);
		leftDisplay.setLayout(new BorderLayout(0, 0));
		
		JPanel namePanel = new JPanel();
		leftDisplay.add(namePanel, BorderLayout.NORTH);
		
		JLabel lblName = new JLabel("Name: ");
		namePanel.add(lblName);
		String name = "Charmander";
		String img = "src/imgs/doduo.jpg";
		displayNameAndImg(mainCenterPanel, leftDisplay, namePanel, name, img);
		
	}

	private void displayNameAndImg(JPanel mainCenterPanel, JPanel leftDisplay, JPanel namePanel, String name, String img) {
		JLabel lblPokemonName = new JLabel(name);
		namePanel.add(lblPokemonName);
		
		JPanel imageDisplay = new JPanel();
		leftDisplay.add(imageDisplay, BorderLayout.CENTER);
		
	    // Define the desired width and height for the image
	    int desiredWidth = 400; // Change as needed
	    int desiredHeight = 400; // Change as needed


	    JLabel imgLabel = new JLabel();

	    // Load and scale image
	    try {
	        ImageIcon originalIcon = new ImageIcon(img);
	        Image scaledImage = originalIcon.getImage().getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_AREA_AVERAGING);
	        imgLabel.setIcon(new ImageIcon(scaledImage));
	    } catch (Exception e) {
	        imgLabel.setText("Image not found");
	        e.printStackTrace();
	    }

	    imageDisplay.add(imgLabel);

	    // Dynamic updates
	    mainCenterPanel.revalidate();
	    mainCenterPanel.repaint();
	}

	private void rightDisplay(JPanel mainCenterPanel, JLabel[] statValueLabels) {
	    JPanel statsPanel = new JPanel(new GridLayout(0, 2, 10, 10)); // rows, columns, hgap, vgap
	    statsPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // top, left, bottom, right padding

	    String[] statNames = {"ID:", "Name:", "Type:", "HP:", "Attack:", "Defense:", 
	                          "Special-Attack:", "Special-Defense:", "Speed:", "Evolution:"};

	    //JLabel[] statValueLabels = new JLabel[statNames.length];

	    for (int i = 0; i < statNames.length; i++) {
	        statsPanel.add(new JLabel(statNames[i]));
	        //statValueLabels[i] = new JLabel(""); // Initially empty
	        statsPanel.add(statValueLabels[i]);
	    }

	    // Center the stats panel in the right display
	    JPanel rightDisplay = new JPanel(new BorderLayout());
	    rightDisplay.add(statsPanel, BorderLayout.CENTER);

	    // Add rightDisplay to the mainCenterPanel
	    mainCenterPanel.add(rightDisplay, BorderLayout.EAST);
		
		
	}

	private void displayButtons() {
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		JButton btnEvolution = new JButton("Show Evolution");
		buttonPanel.add(btnEvolution);
		// Add ActionListener to the button
        btnEvolution.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Evolution button clicked!");
                // Add any additional action you want to perform here
                // TODO get the current pokemon and then check if it has an evolution
                // if(pokemon has evolution){
                // currentPokemonID = Pokemon.getEvolution();}
            }
        });
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonPanel.add(btnPrevious);
		btnPrevious.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Previous button clicked!");
                //TODO if(currentPokemonID == 1 || currentPokemonID ==0){
                // currentPokemonID = pokemonData.length;
                //} else{
                //TODO currentPokemonID --; }
            }
        });
		
		JButton btnNext = new JButton("Next");
		btnNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonPanel.add(btnNext);
		btnPrevious.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Next button clicked!");
                //TODO if(currentPokemonID == pokemonData.length ){
                // currentPokemonID = 1;
                //} else{
                //TODO currentPokemonID ++; }
            }
        });
		
		
		JButton btnSort = new JButton("Sort By...");
		btnSort.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonPanel.add(btnSort);
	    btnSort.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            SortOptionsDialog dialog = new SortOptionsDialog(MainWindow.this);
	            dialog.setVisible(true);
	        }
	    });
	    buttonPanel.add(btnSort);
	}

}
