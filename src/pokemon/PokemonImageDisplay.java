package pokemon;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PokemonImageDisplay {

    /**
     * Displays images of each Pokemon in a window.
     *
     * @param pokemonList The list of Pokemon to display.
     */
    public static void displayPokemonImages(List<Pokemon> pokemonList) {
        JFrame frame = new JFrame("Pokemon Images");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        frame.add(imageLabel, BorderLayout.CENTER);

        frame.setVisible(true);

        for (Pokemon pokemon : pokemonList) {
            if (pokemon.getImgPath() != null){
                System.out.println(pokemon.getImgPath());
            }else {
                System.out.println("No image for" + pokemon.getName());
            }

            SwingUtilities.invokeLater(() -> {
                ImageIcon icon = new ImageIcon(pokemon.getImgPath());
                imageLabel.setIcon(icon);
                frame.setTitle(pokemon.getName());
            });
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // Example usage
        String csvFilePath = "src/data/pokemon151.csv";
        List<Pokemon> pokemonList = PokemonCSVReader.readPokemonFromCSV(csvFilePath);
                displayPokemonImages(pokemonList);
    }
}
