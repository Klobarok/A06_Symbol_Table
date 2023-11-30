package pokemon;

import javax.swing.*;
import java.awt.*;

/**
 * Custom cell renderer for JList that displays Pokémon information.
 * It shows the name of the Pokémon and scales its image to the specified width and height.
 */
class PokemonListCellRenderer extends DefaultListCellRenderer {
    private static final int IMG_WIDTH = 150;  // Set the width for the image
    private static final int IMG_HEIGHT = 150; // Set the height for the image

    /**
     * Customizes each cell in a JList to display a Pokémon's name and scaled image.
     *
     * @param list         The JList we're painting.
     * @param value        The value returned by list.getModel().getElementAt(index).
     * @param index        The cells index.
     * @param isSelected   True if the specified cell was selected.
     * @param cellHasFocus True if the specified cell has the focus.
     * @return A component whose paint() method will render the specified value.
     */
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Pokemon) {
            Pokemon pokemon = (Pokemon) value;
            if (pokemon != null) {
                setText(pokemon.getName());

                ImageIcon originalIcon = new ImageIcon(pokemon.getImgPath());
                Image scaledImage = originalIcon.getImage().getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_SMOOTH);
                setIcon(new ImageIcon(scaledImage));
            }
        } else {
            setText("Unknown Pokemon");
        }
        return this;
    }
}