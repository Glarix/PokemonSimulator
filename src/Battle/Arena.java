/**
 * Class to represent the arena where clashes occur
 */
package Battle;

import PokeStuff.Pokemon;
import PokeStuff.PokemonFactory;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Arena {

    private static Arena theArena;

    private int numOfAdventures;
    private Coach coach1;
    private Coach coach2;

    public static Arena Instance(Coach coach1, Coach coach2) {
        if (theArena == null) {
            theArena = new Arena();
        }
        theArena.setCoach1(coach1);
        theArena.setCoach2(coach2);
        theArena.numOfAdventures = 1;
        return theArena;
    }


    private Arena() {

    }

    public void setCoach1(Coach coach1) {
        this.coach1 = coach1;
    }

    public void setCoach2(Coach coach2) {
        this.coach2 = coach2;
    }

    /**
     * Core method that determines the type of battles that will occur in every adventure and initiates the
     * clashes between pokemons
     * @param logType the type of logging to be used : String -> log to file / null -> log to console
     */
    public void adventure(String logType) {
        Random r = new Random();
        int min = 1;
        int max = 4; // exclusive
        PokemonFactory pf = new PokemonFactory();
        Pokemon forCoach1;
        Pokemon forCoach2;

        // Until all 4 adventures are played
        while (numOfAdventures < 5) {
            int fightType = r.nextInt(max - min) + min;

            // For first 3 adventures a pokemon is choosed by his position
            if (numOfAdventures < 4) {
                forCoach1 = coach1.getPokemons().get(numOfAdventures - 1);
                forCoach2 = coach2.getPokemons().get(numOfAdventures - 1);
            } else { // For last adventure the pokemons are choosed based on their stats
                forCoach1 = calculateStrongestPokemon(coach1.getPokemons());
                forCoach2 = calculateStrongestPokemon(coach2.getPokemons());
            }

            logBattleType(logType, fightType);

            // Determining the type of fight and initiating the clashes
            // 1 -> vs. Neutrel1 / 2 -> vs. Neutrel2 / 3 -> vs. other Coach
            switch (fightType) {
                case 1:
                    pokemonClashInitiator(forCoach1, forCoach2, coach1, coach2, 1, logType);
                    break;
                case 2:
                    pokemonClashInitiator(forCoach1, forCoach2, coach1, coach2, 2, logType);
                    break;
                case 3:
                    pokemonClashInitiator(forCoach1, forCoach2, coach1, coach2, 3, logType);
                    break;
            }

            // After batlle vs other coach, go to next adventure
            if (fightType == 3) {
                numOfAdventures++;
            }
        }
        numOfAdventures = 1;
    }

    private void logBattleType(String logType, int fightType) {
        Logger log = new Logger(logType);
        log.log("Aventura " + numOfAdventures);
        log.logNewLine();
        log.log("Lupta tip: " + fightType);
        log.logNewLine();
        log.log("----------------------------------------");
        log.logNewLine();
        log.commitLog();
    }


    /**
     * Method that determines the strongest pokemon from a list
     * @param pokes the list with pokemons
     * @return the strongest pokemon found
     */
    private Pokemon calculateStrongestPokemon(ArrayList<Pokemon> pokes) {
        if (pokes == null || pokes.size() == 0)
            return null;

        Pokemon strongest = pokes.get(0);
        int power = strongest.getHP() + strongest.getNormalAttack() + strongest.getSpecialAttack() +
                strongest.getDef() + strongest.getSpecialDef();

        for (int i = 1; i < 3; i++) {
            Pokemon p = pokes.get(i);
            int newPower = p.getHP() + p.getNormalAttack() + p.getSpecialAttack() + p.getDef() + p.getSpecialDef();
            if (newPower > power) {
                strongest = p;
                power = newPower;
            }
            else if (newPower == power) {
                int compare = p.getName().compareTo(strongest.getName());
                if (compare < 0)
                    strongest = p;
            }
        }

        return strongest;
    }

    /**
     * Method to initiate a clash for two coaches
     * @param forCoach1 Pokemon from coach1
     * @param forCoach2 Pokemon from Coach2
     * @param coach1 The first coach
     * @param coach2 The second coach
     * @param fightType The type of the fight
     * @param logType The type of logging
     */
    private void pokemonClashInitiator(Pokemon forCoach1, Pokemon forCoach2, Coach coach1, Coach coach2, int fightType, String logType) {
        Logger loggerCoach1 = new Logger(logType);
        Logger loggerCoach2 = new Logger(logType);
        Pokemon adversary1 = forCoach2;
        Pokemon adversary2 = forCoach1;

        PokemonFactory pf = new PokemonFactory();
        // If fightType1 -> adversary will be Neutrel1 for both coaches
        if (fightType == 1) {
            adversary1 = pf.createPokemon("Neutrel1", null);
            adversary2 = pf.createPokemon("Neutrel1", null);
        } else if (fightType == 2) { // If 2 then adversary Neutrel2
            adversary1 = pf.createPokemon("Neutrel2", null);
            adversary2 = pf.createPokemon("Neutrel2", null);
        }
        // Clash between 2 coaches
        if (fightType == 3) {
            Clash cl = new Clash(forCoach1, forCoach2, coach1, coach2, loggerCoach1);
            cl.run();
            loggerCoach1.logNewLine();
            loggerCoach1.commitLog();
        } else { // Two threads for simultaneous Clashes between coach and neutrel
            ExecutorService executor = Executors.newFixedThreadPool(2);
            executor.execute(new Clash(forCoach1, adversary1,
                    coach1, null, loggerCoach1));
            executor.execute(new Clash(forCoach2, adversary2,
                    coach2, null, loggerCoach2));

            executor.shutdown();
            try {
                boolean finished = executor.awaitTermination(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Logging the results
            loggerCoach1.logNewLine();
            loggerCoach1.commitLog();
            loggerCoach2.logNewLine();
            loggerCoach2.commitLog();
        }

    }

}
