package turnbasedrpg.moves;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author matheus.oliveira
 */
public final class Pokemon implements Serializable {
    int number = 0;
    int level = 1;

    int[] physicalAttack = new int[3];
    int[] specialAttack = new int[3];
    int[] physicalDefense = new int[3];
    int[] specialDefense = new int[3];
    int[] speed = new int[3];
    int[] health = new int[3];

    String name = "";
    Moves[] moves;

    public Pokemon() {
        Random rand = new Random();
        // Configura o nível
        setLevel(50);

        // Gera IVs aleatórios
        setPhysicalAttackIV(rand.nextInt(31));
        setSpecialAttackIV(rand.nextInt(31));
        setPhysicalDefenseIV(rand.nextInt(31));
        setSpecialDefenseIV(rand.nextInt(31));
        setSpeedIV(rand.nextInt(31));
        setHealthIV(rand.nextInt(31));

        // Calcula os valores dos stats
        setHealthValue(calculateStatValue(getHealthStat(), getHealthIV(), 1));
        setSpeedValue(calculateStatValue(getSpeedStat(), getSpeedIV()));
        // Physical
        setDefenseValue(calculateStatValue(getDefenseStat(1), getPhysicalDefenseIV()), 1);
        setAttackValue(calculateStatValue(getAttackStat(1), getPhysicalAttackIV()), 1);
        // Special
        setDefenseValue(calculateStatValue(getDefenseStat(2), getSpecialDefenseIV()), 2);
        setAttackValue(calculateStatValue(getAttackStat(2), getSpecialAttackIV()), 2);
    }

    public int calculateStatValue(int stat, int IV, int isHealth) {
        return ((((stat + IV) * 2) * level)/100) + level * 10; 
    }

    public int calculateStatValue(int stat, int IV) {
        return ((((stat + IV) * 2) * level)/100) + 5; 
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
        this.setNumber(7);
        return this;
    }

    // Nome
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    
    
    // Nível
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    // Health
    public int getHealthValue() {
        return this.health[0];
    }

    public void setHealthValue(int health) {
        this.health[0] = health;
    }

    public int getHealthIV() {
        return this.health[2];
    }

    private void setHealthIV(int healthIV) {
        this.health[2] = healthIV;
    }

    public int getHealthStat() {
        return this.health[2];
    }

    public void setHealthStat(int healthStat) {
        this.health[3] = healthStat;
    }

    // Speed
    public int getSpeedValue() {
        return this.speed[0];
    }

    public void setSpeedValue(int speed) {
        this.speed[0] = speed;
    }

    public int getSpeedIV() {
        return this.speed[2];
    }

    private void setSpeedIV(int speedIV) {
        this.speed[2] = speedIV;
    }

    public int getSpeedStat() {
        return this.speed[2];
    }

    public void setSpeedStat(int speedStat) {
        this.speed[3] = speedStat;
    }

    // Defense
    public int getDefenseValue(int type) {
        if (type == 1) { //Fisico
            return this.physicalDefense[0];
        } else {
            return this.specialDefense[0];
        }
    }

    public void setDefenseValue(int defense, int type) {
        if (type == 1) { //Fisico
            this.physicalDefense[0] = defense;
        } else {
            this.specialDefense[0] = defense;
        }
    }

    public int getDefenseStat(int type) {
        if (type == 1) { // Fisico
            return this.physicalDefense[2];
        } else { // Fisico
            return this.specialDefense[2];
        }
    }

    public void setDefenseStat(int defense, int type) {
        if (type == 1) { // Fisico
            this.physicalDefense[3] = defense;
        } else {
            this.specialDefense[3] = defense;
        }
    }

    // PhysicalDefense
    public int getPhysicalDefenseIV() {
        return this.physicalDefense[2];
    }

    private void setPhysicalDefenseIV(int physicalDefenseIV) {
        this.physicalDefense[2] = physicalDefenseIV;
    }

    // SpecialDefense
    public int getSpecialDefenseIV() {
        return this.specialDefense[2];
    }

    private void setSpecialDefenseIV(int specialDefenseIV) {
        this.specialDefense[2] = specialDefenseIV;
    }

    // Attack
    public int getAttackValue(int type) {
        if (type == 1) { //Fisico
            return this.physicalAttack[0];
        } else {
            return this.specialAttack[0];
        }
    }

    public void setAttackValue(int attack, int type) {
        if (type == 1) { //Fisico
            this.physicalAttack[0] = attack;
        } else {
            this.specialAttack[0] = attack;
        }
    }

    public int getAttackStat(int type) {
        if (type == 1) { // Fisico
            return this.physicalAttack[2];
        } else { // Fisico
            return this.specialAttack[2];
        }
    }

    public void setAttackStat(int attack, int type) {
        if (type == 1) { // Fisico
            this.physicalAttack[3] = attack;
        } else {
            this.specialAttack[3] = attack;
        }
    }

    // PhysicalAttack
    public int getPhysicalAttackIV() {
        return this.physicalAttack[2];
    }

    private void setPhysicalAttackIV(int physicalAttackIV) {
        this.physicalAttack[2] = physicalAttackIV;
    }

    // SpecialAttack
    public int getSpecialAttackIV() {
        return this.specialAttack[2];
    }

    private void setSpecialAttackIV(int specialAttackIV) {
        this.specialAttack[2] = specialAttackIV;
    }
}
