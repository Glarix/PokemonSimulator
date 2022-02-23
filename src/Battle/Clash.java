/**
 * Class to represent the clash between two pokemons
 */
package Battle;

import PokeStuff.Ability;
import PokeStuff.Pokemon;
import Util.Utils;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Clash implements Runnable{

    private Pokemon firstPokemon;
    private Pokemon secondPokemon;
    private Coach coach1;
    private Coach coach2;
    private Logger logger;

    public Clash(Pokemon firstPokemon, Pokemon secondPokemon, Coach coach1, Coach coach2, Logger logger) {
        this.firstPokemon = firstPokemon;
        this.secondPokemon = secondPokemon;
        this.coach1 = coach1;
        this.coach2 = coach2;
        this.logger = logger;
    }


    /**
     * The actual batlle logic
     */


    /**
     * Core method to run a clash between two pokemons
     */
    @Override
    public void run() {
        int roundCounter = 1;
        Pokemon saveFirstPokemon = firstPokemon;
        Pokemon saveSecondPokemon = secondPokemon;

        boolean neutrelBattle = false;
        // If coach2 is null then the clash is against a Neutrel
        if (coach2 == null)
            neutrelBattle = true;

        // Clash until a Pokemon is dead
        while (firstPokemon.Alive() && secondPokemon.Alive()) {
            Pokemon firstPokeCopy = null;
            Pokemon secondPokeCopy = null;

            // Cloning the current state of a pokemon to send them to clash round
            try {
                firstPokeCopy = (Pokemon) firstPokemon.clone();
                secondPokeCopy = (Pokemon) secondPokemon.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            // Selecting possible abilities for 2 pokemons
            Ability poke1Ability = getSelectedAbility(firstPokeCopy, false);
            Ability poke2Ability;
            if (neutrelBattle)
                poke2Ability = getSelectedAbility(secondPokeCopy, true);
            else
                poke2Ability = getSelectedAbility(secondPokeCopy, false);


            logger.log(roundCounter + ". " + firstPokemon.getName());
            if (poke1Ability != null)
                logger.log(" " + poke1Ability.getName());
            else
                logger.log(" stunned");
            logger.log(" / " +  secondPokemon.getName());
            if (poke2Ability != null)
                logger.log(" " + poke2Ability.getName());
            else
                logger.log(" stunned");
            logger.log(" -> Rezultat:");
            logger.logNewLine();



            // Pokemon attacks executed simultaneously
            ExecutorService executor = Executors.newFixedThreadPool(2);

            executor.execute(new PokemonAttack(firstPokemon, secondPokeCopy, poke1Ability));
            executor.execute(new PokemonAttack(secondPokemon, firstPokeCopy, poke2Ability));

            executor.shutdown();
            try {
                boolean finished = executor.awaitTermination(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Post battle verifications
            int firstPokeCurrHP = firstPokemon.getHP();
            int secondPokeCurrHP = secondPokemon.getHP();
            firstPokemon = firstPokeCopy;
            secondPokemon = secondPokeCopy;

            // Logging first pokemon result
            logPokemonAttack(firstPokemon, firstPokeCurrHP, "a");

            // Logging for second pokemon
            logPokemonAttack(secondPokemon,secondPokeCurrHP, "b");

            logger.log("-===========================================-");
            logger.logNewLine();

            // Implementinting the Dodge mechanic
            if (firstPokemon.isDodge()){
                firstPokemon.setHP(firstPokeCurrHP);
                firstPokemon.setDodge(false);
                firstPokemon.setStunned(false);
            }

            if (secondPokemon.isDodge()) {
                secondPokemon.setHP(secondPokeCurrHP);
                secondPokemon.setDodge(false);
                secondPokemon.setStunned(false);
            }

            roundCounter++;
        }

        // Checking how a clash has finished
        checkResult(saveFirstPokemon, saveSecondPokemon);

    }

    /**
     * Method to determine how a Clash ended
     * @param saveFirstPokemon The initial state of the first Pokemon
     * @param saveSecondPokemon The initial state of the second Pokemon
     */
    private void checkResult(Pokemon saveFirstPokemon, Pokemon saveSecondPokemon) {
        // If a draw, no buffs are needed
        if (!firstPokemon.Alive() && !secondPokemon.Alive()){
            logger.log("Draw");
            logger.logNewLine();
        }
        // If second Pokemon won then it gets buffed
        if (!firstPokemon.Alive() && secondPokemon.Alive()){
            logger.log(coach2.getName() + "(" + secondPokemon.getName() + ") castiga" +
                    " Arena invingand pe celalalt antrenor. " + secondPokemon.getName() + " are" +
                    "acum + 1 la toate caracteristicile:");
            logger.logNewLine();

            firstPokemon = saveFirstPokemon;
            secondPokemon = saveSecondPokemon;
            Utils.resetPokemon(firstPokemon);
            Utils.resetPokemon(secondPokemon);
            Utils.buffWinner(secondPokemon);

            logger.log("HP " + secondPokemon.getHP() + ", Attack " + secondPokemon.getNormalAttack() +
                    ", Special attack " + secondPokemon.getSpecialAttack() + ", Defense " +
                    secondPokemon.getDef() + ", Special Defense " + secondPokemon.getSpecialDef());
            logger.logNewLine();
        }

        // If first Pokemon wins then it gets buffed
        if (firstPokemon.Alive() && !secondPokemon.Alive()) {
            logger.log(coach1.getName() + "(" + firstPokemon.getName() + ") castiga" +
                    " Arena invingand pe celalalt antrenor. " + firstPokemon.getName() + " are" +
                    "acum + 1 la toate caracteristicile:");
            logger.logNewLine();

            firstPokemon = saveFirstPokemon;
            secondPokemon = saveSecondPokemon;
            Utils.resetPokemon(firstPokemon);
            Utils.resetPokemon(secondPokemon);
            Utils.buffWinner(firstPokemon);

            logger.log("HP " + firstPokemon.getHP() + ", Attack " + firstPokemon.getNormalAttack() +
                    ", Special attack " + firstPokemon.getSpecialAttack() + ", Defense " +
                    firstPokemon.getDef() + ", Special Defense " + firstPokemon.getSpecialDef());
            logger.logNewLine();
        }
    }


    /**
     * Method to log the attack of a POKEMON
     * @param pokemon The Pokemon launching the attack
     * @param pokeCurrHP The currentHP of the Pokemon
     * @param pokeNum The order of Pokemon attack: a -> first / b -> second
     */
    private void logPokemonAttack(Pokemon pokemon, int pokeCurrHP, String pokeNum) {
        if (pokemon.isDodge())
            logger.log("\t" + pokeNum + ". " + pokemon.getName() + " HP " + pokeCurrHP + " ");
        else
            logger.log("\t" + pokeNum + ". " + pokemon.getName() + " HP " + pokemon.getHP() + " ");
        if (pokemon.isDodge())
            logger.log("(dodge), ");
        if (pokemon.isStunned())
            logger.log("stunned, ");
        if (pokemon.getAbility1() != null && pokemon.getAbility2() != null){
            logger.log(pokemon.getAbility1().getName() + " cooldown " +
                    pokemon.getAbility1().getCurrentCd() + ", " +
                    pokemon.getAbility2().getName() + " cooldown " +
                    pokemon.getAbility2().getCurrentCd());
        }
        logger.logNewLine();
    }

    /**
     * Method to pick an available attack for a Pokemon
     * @param poke The Pokemon in need of an attack
     * @return The code number of the chosen attack
     */
    private int pickAttackType (Pokemon poke) {
        Random r = new Random();
        int min = 1;
        int max = 4;

        int attType1 = -1;
        while (true) {
            attType1 = r.nextInt(max - min) + min;
            // If > 1 then it is an ability so it has to be verified
            if (attType1 > 1) {
                if (attType1 == 2) {
                    if (poke.getAbility1().getCurrentCd() == 0) break;
                    continue;
                } else if (poke.getAbility2().getCurrentCd() == 0) break;
                continue;
            }
            break;
        }

        return attType1;
    }

    private Ability abilityFromSimpleAttack(int dmg, boolean special) {
        if (special)
            return new Ability("atac special", dmg, false, false, 0, false, special);
        return new Ability("atac normal", dmg, false, false, 0, false, special);
    }

    /**
     * Methodd to get the Actual selected Ability for a Pokemon attack
     * @param poke The Pokemon in need of an attack
     * @param isNeutrel if it is a Neutrel Pokemon
     * @return the Chosen ability
     */
    private Ability getSelectedAbility(Pokemon poke, boolean isNeutrel) {
        // If Pokemon is stunned then it cannot attack
        if (poke.isStunned()) {
            poke.setStunned(false);
            return null;
        }
        // If Neutrel he can only get first type of attack
        if (isNeutrel) {
            if (poke.getSpecialAttack() != 0)
                return abilityFromSimpleAttack(poke.getSpecialAttack(), true);
            else
                return abilityFromSimpleAttack(poke.getNormalAttack(), false);
        }

        // For non-Neutrel pokemons
        int attackTypePoke1 = pickAttackType(poke);
        Ability forPoke;
        if (attackTypePoke1 == 1) {
            if (poke.getSpecialAttack() == 0) {
                forPoke = abilityFromSimpleAttack(poke.getNormalAttack(), false);
            } else forPoke = abilityFromSimpleAttack(poke.getSpecialAttack(), true);
        } else {
            if (attackTypePoke1 == 2)
                forPoke = poke.getAbility1();
            else
                forPoke = poke.getAbility2();
        }

        // if ability has dodge the set the Pokemon to dodged state
        if (forPoke.isDodge())
            poke.setDodge(true);
        return forPoke;
    }
}
