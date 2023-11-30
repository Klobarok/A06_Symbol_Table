package pokemon;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.BevelBorder;
import java.util.Arrays;

/**
 * Dialog for sorting and filtering Pokémon data.
 * It allows the user to generate a table based on various sorting criteria like ID, Name, or Type.
 * The selected Pokémon from this table can be viewed on the main window.
 *
 * @author Joel Berg + Joseph Peat
 */
public class SortOptionsDialog extends JDialog {
    private MainWindow mainWindow;
    private JRadioButton rbType = new JRadioButton("Type:");
    private boolean[] selection = new boolean[5]; // [ID, Name, Type]
    private boolean[] typeSelection;
    private JCheckBox[] typeCheckboxes; // Array to store references to the checkboxes

    /**
     * Constructor to create a sort options dialog.
     *
     * @param mainWindow The parent window to which this dialog is attached.
     */
    public SortOptionsDialog(MainWindow mainWindow) {
        super(mainWindow, "Sort By", true);
        this.mainWindow = mainWindow;

        getContentPane().setLayout(new GridLayout(0, 1));
        JPanel namePanel = new JPanel();
        getContentPane().add(namePanel);
        namePanel.setLayout(new GridLayout(0, 1, 0, 0));

        // Sort by ID and Name
        JRadioButton rbID = new JRadioButton("ID: ");
        JRadioButton rbName = new JRadioButton("Name:");
        namePanel.add(rbID);
        namePanel.add(rbName);

        // Type sort option panel
        JPanel typePanel = new JPanel();
        typePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        getContentPane().add(typePanel);
        typePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        typePanel.add(rbType);

        // Type checkboxes panel
        JPanel typeChoicePanel = new JPanel();
        typePanel.add(typeChoicePanel);
        typeChoicePanel.setLayout(new GridLayout(5, 3, 0, 0));

        // Type options
        String[] types = {"Bug", "Dragon", "Electric", "Fighting", "Fire", "Flying", "Ghost", "Grass", "Ground", "Ice", "Normal", "Poison", "Psychic", "Rock", "Water"};
        typeSelection = new boolean[types.length];
        typeCheckboxes = new JCheckBox[types.length];
        for (int i = 0; i < types.length; i++) {
            final int index = i;
            typeCheckboxes[i] = new JCheckBox(types[i]);
            typeCheckboxes[i].setEnabled(false); // Initially disabled
            typeChoicePanel.add(typeCheckboxes[i]);
            typeCheckboxes[i].addActionListener(e -> typeSelection[index] = typeCheckboxes[index].isSelected());
        }

        // ActionListener for rbType
        rbType.addActionListener(e -> {
            boolean typeSelected = rbType.isSelected();
            for (JCheckBox checkBox : typeCheckboxes) {
                checkBox.setEnabled(typeSelected); // Enable/disable based on rbType's state
            }
        });

        // Group the radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add(rbID);
        group.add(rbName);
        group.add(rbType);

        // Action listeners for radio buttons
        rbID.addActionListener(e -> updateSelection(0));
        rbName.addActionListener(e -> updateSelection(1));
        rbType.addActionListener(e -> updateSelection(2));

        // Submit button
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(e -> handleSubmit());
        getContentPane().add(btnSubmit);

        // Set dialog properties
        pack();
        setLocationRelativeTo(mainWindow);
    }

    /**
     * Updates the selection array based on the user's choice of sorting criteria.
     *
     * @param index The index of the selected sorting criteria in the selection array.
     */
    private void updateSelection(int index) {
        Arrays.fill(selection, false);
        selection[index] = true;
    }

    /**
     * Handles the submit action.
     * It creates and displays a table based on the user's selected sorting criteria.
     * The table is displayed in a new frame and allows the user to select a Pokémon
     * to view in the main window.
     */
    private void handleSubmit() {
        // Initialize the table data and column names
        String[] columnNames = {"ID", "Name", "Type"};
        String[][] original = PokemonSymbolTable.symbolTableTo2DArray().clone();

        // Determine the sorting method based on the user's selection
        String[][] sorted = null;
        for (int i = 0; i < selection.length; i++) {
            if (selection[i]) {
                switch (i) {
                    case 0: // Sort by ID
                        sorted = original;
                        break;
                    case 1: // Sort by Name
                        sorted = PokeSort.sortByName(original);
                        break;
                    case 2: // Filter and Sort by Types
                        sorted = PokeSort.filterAndSortByTypes(original, typeSelection);
                        break;
                }
                break; // Exit the loop once a selection is handled
            }
        }

        // Create the table with the sorted data
        JTable table = new JTable(sorted, columnNames);

        // Create a new frame to display the table
        JFrame tableFrame = new JFrame("Selected Data");
        tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tableFrame.setSize(400, 300);
        tableFrame.setLocation(this.getLocation()); // Set the frame at the same location as the dialog

        // Add the table to a scroll pane and to the frame
        JScrollPane scrollPane = new JScrollPane(table);
        tableFrame.getContentPane().add(scrollPane);
        tableFrame.setVisible(true); // Display the frame

        // Add a ListSelectionListener to the table for selecting a Pokémon
        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String idString = (String) table.getValueAt(selectedRow, 0);
                    try {
                        int selectedId = Integer.parseInt(idString);
                        mainWindow.updateDisplayForId(selectedId); // Update the main window display
                    } catch (NumberFormatException ex) {
                        ex.printStackTrace(); // Handle invalid ID format
                    }
                }
            }
        });

        dispose(); // Close the dialog
    }
}