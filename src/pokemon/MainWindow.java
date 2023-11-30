package pokemon;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

/**
 * Main window for the Pokémon application.
 * This class creates the main frame for the application,
 * displaying Pokémon data and providing navigation through the Pokémon list.
 *
 * @author Joel Berg + Joseph Peat
 */
public class MainWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private PokemonSymbolTable symbolTable;
    private String csvFilePath = "src/data/pokemon151.csv";
    public int currentPokemonID = 1;

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
     * Displays the evolution chain of a Pokémon in a dialog.
     * The evolution chain is presented in a JList within a JScrollPane,
     * and each Pokémon can be selected to update the main display.
     *
     * @param pokemonId The ID of the Pokémon whose evolution chain is to be displayed.
     */
    private void showEvolutionChain(int pokemonId) {
        // Create the EvolutionGraph and get the evolution chain for the specified Pokémon
        EvolutionGraph evoGraph = new EvolutionGraph(symbolTable);
        List<Pokemon> evolutionChain = evoGraph.getCompleteEvolutionChainPokemons(pokemonId, symbolTable);

        // Create a JList to display the Pokémon in the evolution chain
        JList<Pokemon> list = new JList<>(new Vector<>(evolutionChain));
        list.setCellRenderer(new PokemonListCellRenderer()); // Use a custom cell renderer for display

        // Add a listener to handle selection events on the list
        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Pokemon selectedPokemon = list.getSelectedValue();
                updateDisplay(selectedPokemon);
            }
        });

        // Create a JScrollPane to contain the list and set its preferred size
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(500, 400));

        // Create a JOptionPane for the scroll pane
        JOptionPane optionPane = new JOptionPane(scrollPane);
        JDialog dialog = optionPane.createDialog(this, "Evolution Chain");

        // Calculate the position for the dialog
        Point mainWindowLocation = this.getLocation();
        int newX = mainWindowLocation.x + this.getWidth();
        int newY = mainWindowLocation.y;

        // Set the location and display the dialog
        dialog.setLocation(newX, newY);
        dialog.setVisible(true);
    }

    /**
     * Updates the display in the main window to show the information of the Pokémon with the specified ID.
     * This method looks up the Pokémon based on the provided ID, updates the current Pokémon ID tracking
     * in the main window, and then updates the display to show the selected Pokémon's details.
     *
     * @param pokemonId The ID of the Pokémon to be displayed. This should correspond to a valid ID in the symbol table.
     */
    public void updateDisplayForId(int pokemonId) {
        Pokemon selectedPokemon = symbolTable.get(pokemonId);
        if (selectedPokemon != null) {
            currentPokemonID = pokemonId; // Update the currentPokemonID
            updateDisplay(selectedPokemon);
        }
    }

    /**
     * Sets up and displays the navigation and action buttons in the main window.
     */
    private void displayButtons() {
        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        // Evolution Button: Shows the evolution chain of the current Pokémon
        JButton btnEvolution = new JButton("Show Evolution");
        btnEvolution.setFocusable(false);
        btnEvolution.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEvolution.addActionListener(e -> showEvolutionChain(currentPokemonID));
        buttonPanel.add(btnEvolution);

        // Previous Button: Displays the previous Pokémon in the list
        JButton btnPrevious = new JButton("Previous");
        btnPrevious.setFocusable(false);
        btnPrevious.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnPrevious.addActionListener(e -> {
            if (!symbolTable.isEmpty()) {
                currentPokemonID--;
                if (currentPokemonID < symbolTable.min().getId()) {
                    currentPokemonID = symbolTable.max().getId(); // Loop to the last
                }
                updateDisplay(symbolTable.get(currentPokemonID));
            }
        });
        buttonPanel.add(btnPrevious);

        // Next Button: Displays the next Pokémon in the list
        JButton btnNext = new JButton("Next");
        btnNext.setFocusable(false);
        btnNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNext.addActionListener(e -> {
            if (!symbolTable.isEmpty()) {
                currentPokemonID++;
                if (currentPokemonID > symbolTable.max().getId()) {
                    currentPokemonID = symbolTable.min().getId(); // Loop back to the first
                }
                updateDisplay(symbolTable.get(currentPokemonID));
            }
        });
        buttonPanel.add(btnNext);

        // Sort Button: Opens the sorting options dialog
        JButton btnSort = new JButton("Sort By...");
        btnSort.setFocusable(false);
        btnSort.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSort.addActionListener(e -> {
            SortOptionsDialog dialog = new SortOptionsDialog(MainWindow.this);

            // Position the sort dialog to the right of the main window
            Point mainWindowLocation = MainWindow.this.getLocation();
            int newX = mainWindowLocation.x + MainWindow.this.getWidth();
            int newY = mainWindowLocation.y;
            dialog.setLocation(newX, newY);

            dialog.setVisible(true);
        });
        buttonPanel.add(btnSort);
    }
}