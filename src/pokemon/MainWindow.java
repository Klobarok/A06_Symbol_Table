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
import java.io.IOException;
import java.util.List;

/**
 * Main window for the Pokémon application.
 * This class creates the main frame for the application,
 * displaying Pokémon data and providing navigation through the Pokémon list.
 */
public class MainWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private PokemonSymbolTable symbolTable;
    private String csvFilePath = "src/data/pokemon151.csv";
    public int currentPokemonID = 0;

    // GUI components for displaying Pokemon information
    private JLabel lblPokemonName; // Label for displaying Pokemon's name
    private JLabel imgLabel;       // Label for displaying Pokemon's image
    private JLabel[] statValueLabels; // Array of labels for displaying Pokemon stats

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
     * Create the frame and initialize the components.
     *
     * @throws IOException if there is an issue reading the Pokémon data.
     */
    public MainWindow() throws IOException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        mainCenterPanel();

        // Initialize the symbol table and load Pokémon data from the CSV file
        this.symbolTable = new PokemonSymbolTable();
        List<Pokemon> pokemonList = PokemonCSVReader.readPokemonFromCSV(csvFilePath);
        for (Pokemon pokemon : pokemonList) {
            symbolTable.put(pokemon.getId(), pokemon);
        }

        // Display the first Pokémon if available
        if (!symbolTable.isEmpty()) {
            updateDisplay(symbolTable.get(symbolTable.min().getId()));
        }

        // Creating the EvolutionGraph
        EvolutionGraph evoGraph = new EvolutionGraph(symbolTable);
    }

    /**
     * Set up the main center panel with left and right displays and button panel.
     */
    private void mainCenterPanel() {
        JPanel mainCenterPanel = new JPanel();
        contentPane.add(mainCenterPanel, BorderLayout.CENTER);
        mainCenterPanel.setLayout(new BorderLayout(0, 0));

        leftDisplay(mainCenterPanel);
        rightDisplay(mainCenterPanel);
        displayButtons();
    }

    /**
     * Set up the left display for showing Pokémon name and image.
     *
     * @param mainCenterPanel The central panel of the window.
     */
    private void leftDisplay(JPanel mainCenterPanel) {
        JPanel leftDisplay = new JPanel();
        mainCenterPanel.add(leftDisplay, BorderLayout.WEST);
        leftDisplay.setLayout(new BorderLayout(0, 0));

        JPanel namePanel = new JPanel();
        leftDisplay.add(namePanel, BorderLayout.NORTH);

        JLabel lblName = new JLabel("Name: ");
        namePanel.add(lblName);

        // Initialize the label for Pokemon's name
        lblPokemonName = new JLabel("");
        namePanel.add(lblPokemonName);

        // Initialize the panel and label for Pokemon's image
        JPanel imageDisplay = new JPanel();
        leftDisplay.add(imageDisplay, BorderLayout.CENTER);
        imgLabel = new JLabel();
        imageDisplay.add(imgLabel);
    }

    /**
     * Sets up the display for the Pokémon's name and image.
     *
     * @param mainCenterPanel The central panel of the window.
     * @param leftDisplay     The left panel where the name and image are displayed.
     * @param namePanel       The panel for displaying the Pokémon's name.
     * @param name            The name of the Pokémon to display.
     * @param img             The path to the Pokémon's image file.
     */
    private void displayNameAndImg(JPanel mainCenterPanel, JPanel leftDisplay, JPanel namePanel, String name, String img) {
        JLabel lblPokemonName = new JLabel(name);
        namePanel.add(lblPokemonName);

        JPanel imageDisplay = new JPanel();
        leftDisplay.add(imageDisplay, BorderLayout.CENTER);

        // Define the desired width and height for the image
        int desiredWidth = 400;
        int desiredHeight = 400;

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

    /**
     * Update the image display based on the provided image path.
     *
     * @param imgPath The path to the Pokémon's image.
     */
    private void updateImage(String imgPath) {
        int desiredWidth = 400;
        int desiredHeight = 400;

        try {
            ImageIcon originalIcon = new ImageIcon(imgPath);
            Image scaledImage = originalIcon.getImage().getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_AREA_AVERAGING);
            imgLabel.setIcon(new ImageIcon(scaledImage)); // Replace imgLabel with your JLabel
        } catch (Exception e) {
            imgLabel.setText("Image not found");
            e.printStackTrace();
        }
    }


    /**
     * Set up the right display for showing Pokémon stats.
     *
     * @param mainCenterPanel The central panel of the window.
     */
    private void rightDisplay(JPanel mainCenterPanel) {
        JPanel statsPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        statsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] statNames = {"ID:", "Name:", "Type:", "HP:", "Attack:", "Defense:", "Special-Attack:", "Special-Defense:", "Speed:"};

        statValueLabels = new JLabel[statNames.length]; // Initialize the array

        for (int i = 0; i < statNames.length; i++) {
            statsPanel.add(new JLabel(statNames[i]));
            statValueLabels[i] = new JLabel(""); // Initialize each label
            statsPanel.add(statValueLabels[i]);
        }

        JPanel rightDisplay = new JPanel(new BorderLayout());
        rightDisplay.add(statsPanel, BorderLayout.CENTER);
        mainCenterPanel.add(rightDisplay, BorderLayout.EAST);
    }

    /**
     * Update the stats display based on the provided Pokémon object.
     *
     * @param pokemon The Pokémon to display.
     */
    private void updateStats(Pokemon pokemon) {
        statValueLabels[0].setText(String.valueOf(pokemon.getId()));
        statValueLabels[1].setText(pokemon.getName());
        statValueLabels[2].setText(pokemon.getType());
        statValueLabels[3].setText(String.valueOf(pokemon.getHp()));
        statValueLabels[4].setText(String.valueOf(pokemon.getAttack()));
        statValueLabels[5].setText(String.valueOf(pokemon.getDefense()));
        statValueLabels[6].setText(String.valueOf(pokemon.getSpecialAttack()));
        statValueLabels[7].setText(String.valueOf(pokemon.getSpecialDefense()));
        statValueLabels[8].setText(String.valueOf(pokemon.getSpeed()));
    }

    /**
     * Update the entire display (both left and right panels) for the given Pokémon.
     *
     * @param pokemon The Pokémon to display.
     */
    private void updateDisplay(Pokemon pokemon) {
        lblPokemonName.setText(pokemon.getName()); // Assuming lblPokemonName is a JLabel for Pokemon's name
        updateImage(pokemon.getImgPath());
        updateStats(pokemon);
    }

    /**
     * Set up the buttons for Pokémon navigation and actions.
     */
    private void displayButtons() {
        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        JButton btnEvolution = new JButton("Show Evolution");
        buttonPanel.add(btnEvolution);
        btnEvolution.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Evolution button clicked!");
                //TODO Add in the Evolution button functionality.
            }
        });

        JButton btnPrevious = new JButton("Previous");
        btnPrevious.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonPanel.add(btnPrevious);
        btnPrevious.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!symbolTable.isEmpty()) {
                    currentPokemonID--;
                    if (currentPokemonID < symbolTable.min().getId()) {
                        currentPokemonID = symbolTable.max().getId(); // Loop to the last
                    }
                    updateDisplay(symbolTable.get(currentPokemonID));
                }
            }
        });

        JButton btnNext = new JButton("Next");
        btnNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonPanel.add(btnNext);
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!symbolTable.isEmpty()) {
                    currentPokemonID++;
                    if (currentPokemonID > symbolTable.max().getId()) {
                        currentPokemonID = symbolTable.min().getId(); // Loop back to the first
                    }
                    updateDisplay(symbolTable.get(currentPokemonID));
                }
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
