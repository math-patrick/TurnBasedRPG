package turnbasedrpg.moves;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author matheus.oliveira
 */
public class Pokemon {

    int level = 1;
    int[] physicalAttack;
    int[] specialAttack;
    int[] physicalDefense;
    int[] specialDefense;
    int[] speed;
    int[] health;
    
    int physicalAttack;
    int specialAttack;
    int physicalDefense;
    int specialDefense;
    int speed;
    int health;

    int physicalAttackIV;
    int specialAttackIV;
    int physicalDefenseIV;
    int specialDefenseIV;
    int speedIV;
    int healthIV;

    String name = "";
    Moves[] moves;

    public Pokemon() {
        Random rand = new Random();

        // Gera IVs aleatórios
        physicalAttackIV = rand.nextInt(31);
        specialAttackIV = rand.nextInt(31);
        physicalDefenseIV = rand.nextInt(31);
        specialDefenseIV = rand.nextInt(31);
        speedIV = rand.nextInt(31);
        healthIV = rand.nextInt(31);
    }

    public int getStatValue(int stat, int IV, int isHealth) {
        return (((IV + 2 * stat) * level / 100) + 10 + level);
    }

    public int getStatValue(int stat, int IV) {
        return (((IV + 2 * stat) * level / 100) + 5); // * Nature Value
    }

    public int getHealthValue() {
        return this.getStatValue(health, healthIV, 1);
    }

    public int getPhysicalAttackValue() {
        return this.getStatValue(physicalAttack, physicalAttackIV);
    }

    public int getSpecialAttackValue() {
        return this.getStatValue(specialAttack, specialAttackIV);
    }

    public int getPhysicalDefenseValue() {
        return this.getStatValue(physicalDefense, physicalDefenseIV);
    }

    public int getSpecialDefenseValue() {
        return this.getStatValue(specialDefense, specialDefenseIV);
    }

    public int getSpeedValue() {
        return this.getStatValue(speed, speedIV);
    }

    public void createPokemon(String name, Moves[] moves) {
        this.name = name;
        this.moves = moves;
        this.level = 5;
    }

    public Moves getPokemonMove(int arrayPosition) {
        return this.moves[arrayPosition];
    }

    public Pokemon getSquirtle() {
        Moves[] movesArray = new Moves[4];
        movesArray[0] = new Moves().getNormalMove(1);
        movesArray[1] = new Moves().getNormalMove(2);
        movesArray[2] = new Moves().getWaterMove(1);
        movesArray[3] = new Moves().getWaterMove(2);
        this.createPokemon("Squirtle", movesArray);
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
