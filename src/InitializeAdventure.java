/**
 * Class for all adventure preparations and initializations
 */
import Battle.Coach;
import Items.Item;
import PokeStuff.Pokemon;
import PokeStuff.PokemonFactory;
import Util.Utils;

import java.io.*;
import java.util.ArrayList;


public abstract class InitializeAdventure {

    /**
     * Method to read from file and create the two coacher for duel
     * @param path the path to the file
     * @return list of two created coaches
     */
    public static ArrayList<Coach> createCoaches(String path) {
        ArrayList<Coach> coaches = new ArrayList<>();
        PokemonFactory pf = new PokemonFactory();

        File f = new File(path);
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            int trainers = 0;
            // Reading 2 coaches
            while (trainers < 2) {
                String name = br.readLine();
                int age = Integer.parseInt(br.readLine());
                // Creating the pokemons
                ArrayList<Pokemon> pokemons = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    String pokeInfo = br.readLine();
                    String[] info = pokeInfo.split(";");
                    ArrayList<Item> pokeItems = new ArrayList<>();
                    String pokeName = info[0];
                    // Check if a pokemon is duplicated
                    if (duplicatePokemon(pokemons, pokeName))
                        throw new IllegalArgumentException("Duplicate pokemon detected");
                    // Creating the items if there are any
                    if (info.length > 1) {
                        String items = info[1];
                        if (items != null && items.length() > 0) {
                            String[] allItems = items.split(":");
                            for (String str : allItems) {
                                Item item = Utils.createItem(str);
                                // Check for duplicate items
                                if (!duplicateItem(pokeItems, item))
                                    pokeItems.add(item);
                                else throw new IllegalArgumentException("Duplicate item detected");
                            }
                        }
                    }
                    pokemons.add(pf.createPokemon(pokeName, pokeItems));
                }
                coaches.add(new Coach(name, age, pokemons));
                trainers++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return coaches;
    }

    /**
     * Method to apply the buffs of all items on all the pokemons of a coach
     * @param coach the coach with his pokemkons
     */
    public static void applyAllItemBuffs(Coach coach) {
        ArrayList<Pokemon> pokemons = coach.getPokemons();

        for (Pokemon p : pokemons) {
            ArrayList<Item> items = p.getItems();
            if (items != null && items.size() > 0) {
                for (Item i : items) {
                    p.applyItemBuffs(i);
                }
            }
        }
    }

    /**
     * Method to check if an item is duplicate
     * @param items list with current items
     * @param item the item to be checked
     * @return true if it's a duplicate / false if not
     */
    private static boolean duplicateItem(ArrayList<Item> items, Item item) {
        if (items == null || items.size() == 0)
            return false;
        if (items.contains(item))
            return true;
        return false;
    }

    /**
     * Method to check if a pokemon is duplicate
     * @param pokes list of current pokemons
     * @param pokemonName the pokemon to be checked
     * @return true if is a duplicate / false if not
     */
    private static boolean duplicatePokemon(ArrayList<Pokemon> pokes, String pokemonName) {
        if (pokes == null || pokes.size() == 0)
            return false;
        for (Pokemon p : pokes) {
            if (p.getName().equals(pokemonName))
                return true;
        }
        return false;
    }


}
