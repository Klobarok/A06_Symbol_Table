package pokemon;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.BevelBorder;
import java.util.Arrays;

/**
 * Contains the components for the dialog popup box that allows the user to generate a table that can be filtered/sorted which the
 * user can later use to view a pokemon on the main window. 
 *  @author Joel Berg + Joseph Peat
 */
public class SortOptionsDialog extends JDialog {
	private JRadioButton rbType = new JRadioButton("Type:");
	//private JTextField txtName;
    //private String selectedName = "";
    private boolean[] selection = new boolean[5]; // [Name, Type, HP, Attack, Speed]
    //private String selectedSortingCriteria = ""; // For "HP", "Attack", "Speed", etc.
    private boolean[] typeSelection;
	
	private JCheckBox[] typeCheckboxes; // Array to store references to the checkboxes

	
    public SortOptionsDialog(JFrame parent) {
        super(parent, "Sort By", true);

        getContentPane().setLayout(new GridLayout(0, 1)); // Using GridLayout for simplicity
        //setSize(400,300);
        JPanel namePanel = new JPanel();
        getContentPane().add(namePanel);
        namePanel.setLayout(new GridLayout(0, 1, 0, 0));
        
        //sort by ID
        JRadioButton rbID = new JRadioButton("ID: ");
        rbID.setVerticalAlignment(SwingConstants.TOP);
        namePanel.add(rbID);
        
        // Name sort option
        JRadioButton rbName = new JRadioButton("Name:");
        rbName.setHorizontalAlignment(SwingConstants.LEFT);
        //rbName.setSelected(true);
        namePanel.add(rbName);
        //txtName = new JTextField(10);
       // namePanel.add(txtName);
        
        JPanel typePanel = new JPanel();
        typePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        getContentPane().add(typePanel);
        typePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        

        // Type sort option
        //JRadioButton rbType = new JRadioButton("Type:");
        typePanel.add(rbType);
        JPanel typeChoicePanel = new JPanel();
        typePanel.add(typeChoicePanel);
        typeChoicePanel.setLayout(new GridLayout(5, 3, 0, 0));
        
        String[] types = {"Bug", "Dragon", "Electric", 
        		"Fairy", "Fighting", "Fire", "Ghost",
        		"Grass", "Ground", "Ice", "Normal", 
        		"Poison", "Psychic", "Rock", "Water"};
        typeSelection = new boolean[types.length]; // Initialize the array
        typeCheckboxes = new JCheckBox[types.length];
        for (int i = 0; i < types.length; i++) {
        	final int index = i; // Use index in the lambda expression
            typeCheckboxes[i] = new JCheckBox(types[i]);
            typeChoicePanel.add(typeCheckboxes[i]);
            typeCheckboxes[i].addActionListener(e -> {
                typeSelection[index] = typeCheckboxes[index].isSelected();
            });
        }
        // ActionListener for rbType
        /*rbType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean typeSelected = rbType.isSelected();
                for (JCheckBox checkBox : typeCheckboxes) {
                    checkBox.setEnabled(typeSelected); // Enable/disable based on rbType's state
                }
            }
        }); */
        
        // Other radio buttons
       /* JRadioButton rbHP = new JRadioButton("HP:");
        JRadioButton rbAttack = new JRadioButton("Attack:");
        JRadioButton rbSpeed = new JRadioButton("Speed:");
        getContentPane().add(rbHP);
        getContentPane().add(rbAttack);
        getContentPane().add(rbSpeed);*/
        
        // Group the radio buttons
        ButtonGroup group = new ButtonGroup();
        
        group.add(rbID);
        group.add(rbName);
        group.add(rbType);
        //group.add(rbHP);
        //group.add(rbAttack);
        //group.add(rbSpeed);
        
        // Action listeners for radio buttons
        rbID.addActionListener(e -> updateSelection(0));
        rbName.addActionListener(e -> updateSelection(1));
        rbType.addActionListener(e -> updateSelection(2));
        //rbHP.addActionListener(e -> updateSelection(2));
        //rbAttack.addActionListener(e -> updateSelection(3));
        //rbSpeed.addActionListener(e -> updateSelection(4));
        
        // Submit button
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(e -> handleSubmit());
        getContentPane().add(btnSubmit);

        // Set dialog properties
        pack();
        setLocationRelativeTo(parent);
    }
    
    private void updateSelection(int index) {
        Arrays.fill(selection, false);
        selection[index] = true;
    }
    
    private void handleSubmit() {
        //String enteredText = txtName.getText();
       // System.out.println("Entered Name: " + enteredText);
        System.out.println("Selection: " + Arrays.toString(selection));
        
        if (rbType.isSelected()) {
            System.out.println("Type Selections: " + Arrays.toString(typeSelection));
        }
        
        // table TODO Based on the sorting selection we need to make a table. 
        // Initialize the table data and column names
        String[] columnNames = {"ID", "Name", "Type"};
        String[][] original = PokemonSymbolTable.symbolTableTo2DArray().clone();
        
        
        //iterate through selection array to come up with the true index. 
        String[][] sorted = null;
        for (int i = 0; i < selection.length; i++) {
            if (selection[i]) {
                switch (i) {
                    case 0:
                    	sorted = original;
                        System.out.println("Sort by ID selected");
                        break;
                    case 1:
                        sorted = PokeSort.sortByName(original);
                        System.out.println("Sort by Name selected");
                        break;
                    case 2:
                        sorted = PokeSort.filterAndSortByTypes(original, typeSelection);
                        System.out.println("Sort by Type selected");
                        break;

                }
                break; // Break the for loop once the true value is found and handled
            }
        }
        // Create the table
        JTable table = new JTable(sorted, columnNames);
        // Create a new frame to display the table
        JFrame tableFrame = new JFrame("Selected Data");
        tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tableFrame.setSize(400, 300);
        tableFrame.setLocation(getMousePosition());
        tableFrame.setLocation(600,600);

        // Add the table to a scroll pane (for large data sets)
        JScrollPane scrollPane = new JScrollPane(table);
        tableFrame.getContentPane().add(scrollPane);

        // Display the frame
        tableFrame.setVisible(true);
        
     // Add a ListSelectionListener
        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) { // This check prevents double events
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    Object id = table.getValueAt(selectedRow, 0); // Assuming ID is in column 0
                    System.out.println("Selected Pokemon ID: " + id);
                    //TODO change the MainWindow.currentPokemonID
                }
            }
        });
        
        dispose();
    }
}
