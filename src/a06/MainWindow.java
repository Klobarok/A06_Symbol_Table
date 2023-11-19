package a06;


import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;

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
		String img = "img/test.png";
		displayNameAndImg(mainCenterPanel, leftDisplay, namePanel, name, img);
		
	}

	private void displayNameAndImg(JPanel mainCenterPanel, JPanel leftDisplay, JPanel namePanel, String name, String img) {
		JLabel lblPokemonName = new JLabel(name);
		namePanel.add(lblPokemonName);
		
		JPanel imageDisplay = new JPanel();
		leftDisplay.add(imageDisplay, BorderLayout.CENTER);
		
	    // Define the desired width and height for the image
	    int desiredWidth = 300; // Change as needed
	    int desiredHeight = 400; // Change as needed


	    JLabel imgLabel = new JLabel();

	    // Load and scale image
	    try {
	        ImageIcon originalIcon = new ImageIcon(img);
	        Image scaledImage = originalIcon.getImage().getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
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
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonPanel.add(btnPrevious);
		
		JButton btnNext = new JButton("Next");
		btnNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonPanel.add(btnNext);
		
		JButton btnSort = new JButton("Sort By...");
		btnSort.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonPanel.add(btnSort);
	}

}
