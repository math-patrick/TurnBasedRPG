package turnbasedrpg.moves;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author matheus.oliveira
 */
public class Pokemon implements Serializable {
    String name = "";
    Moves[] moves;
    int number = 0;
    int level = 1;
    int primaryType = 0;
    int secondaryType = 0;
    int OT = 0;

    double[] physicalAttack = new double[3];
    double[] specialAttack = new double[3];
    double[] physicalDefense = new double[3];
    double[] specialDefense = new double[3];
    double[] speed = new double[3];
    double[] health = new double[3];
    double maxhealth;

    public void calculateStats() {
        Random rand = new Random();
        // Gera IVs aleatórios
        setPhysicalAttackIV(rand.nextInt(31));
        setSpecialAttackIV(rand.nextInt(31));
        setPhysicalDefenseIV(rand.nextInt(31));
        setSpecialDefenseIV(rand.nextInt(31));
        setSpeedIV(rand.nextInt(31));
        setHealthIV(rand.nextInt(31));

        // Calcula os valores dos stats
        setHealthValue(calculateStatValue(getHealthStat(), getHealthIV(), 1));
        setMaxHealthValue(getHealthValue());
        setSpeedValue(calculateStatValue(getSpeedStat(), getSpeedIV()));
        // Physical
        setDefenseValue(calculateStatValue(getDefenseStat(1), getPhysicalDefenseIV()), 1);
        setAttackValue(calculateStatValue(getAttackStat(1), getPhysicalAttackIV()), 1);
        // Special
        setDefenseValue(calculateStatValue(getDefenseStat(2), getSpecialDefenseIV()), 2);
        setAttackValue(calculateStatValue(getAttackStat(2), getSpecialAttackIV()), 2);
    }

    public double calculateStatValue(double base, double IV, int isHealth) {
        return ((((2 * base) + IV) * getLevel())/100.0) + getLevel() + 10;
    }

    public double calculateStatValue(double base, double IV) {
        return ((((2 * base) + IV) * getLevel())/100.0) + 5;
    }

    public Moves getPokemonMove(int arrayPosition) {
        return this.moves[arrayPosition];
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
    public double getHealthValue() {
        return this.health[0];
    }

    public void setHealthValue(double health) {
        this.health[0] = health;
    }

    public double getHealthIV() {
        return this.health[2];
    }

    private void setHealthIV(double healthIV) {
        this.health[2] = healthIV;
    }

    public double getHealthStat() {
        return this.health[2];
    }

    public void setHealthStat(double healthStat) {
        this.health[2] = healthStat;
    }

    // Speed
    public double getSpeedValue() {
        return this.speed[0];
    }

    public void setSpeedValue(double speed) {
        this.speed[0] = speed;
    }

    public double getSpeedIV() {
        return this.speed[2];
    }

    private void setSpeedIV(double speedIV) {
        this.speed[2] = speedIV;
    }

    public double getSpeedStat() {
        return this.speed[2];
    }

    public void setSpeedStat(double speedStat) {
        this.speed[2] = speedStat;
    }

    // Defense
    public double getDefenseValue(int type) {
        if (type == 1) { //Fisico
            return this.physicalDefense[0];
        } else {
            return this.specialDefense[0];
        }
    }

    public void setDefenseValue(double defense, int type) {
        if (type == 1) { //Fisico
            this.physicalDefense[0] = defense;
        } else {
            this.specialDefense[0] = defense;
        }
    }

    public double getDefenseStat(int type) {
        if (type == 1) { // Fisico
            return this.physicalDefense[2];
        } else { // Fisico
            return this.specialDefense[2];
        }
    }

    public void setDefenseStat(double defense, int type) {
        if (type == 1) { // Fisico
            this.physicalDefense[2] = defense;
        } else {
            this.specialDefense[2] = defense;
        }
    }

    // PhysicalDefense
    public double getPhysicalDefenseIV() {
        return this.physicalDefense[2];
    }

    private void setPhysicalDefenseIV(double physicalDefenseIV) {
        this.physicalDefense[2] = physicalDefenseIV;
    }

    // SpecialDefense
    public double getSpecialDefenseIV() {
        return this.specialDefense[2];
    }

    private void setSpecialDefenseIV(double specialDefenseIV) {
        this.specialDefense[2] = specialDefenseIV;
    }

    // Attack
    public double getAttackValue(int type) {
        if (type == 1) { //Fisico
            return this.physicalAttack[0];
        } else {
            return this.specialAttack[0];
        }
    }

    public void setAttackValue(double attack, int type) {
        if (type == 1) { //Fisico
            this.physicalAttack[0] = attack;
        } else {
            this.specialAttack[0] = attack;
        }
    }

    public double getAttackStat(int type) {
        if (type == 1) { // Fisico
            return this.physicalAttack[2];
        } else { // Fisico
            return this.specialAttack[2];
        }
    }

    public void setAttackStat(double attack, int type) {
        if (type == 1) { // Fisico
            this.physicalAttack[2] = attack;
        } else {
            this.specialAttack[2] = attack;
        }
    }

    // PhysicalAttack
    public double getPhysicalAttackIV() {
        return this.physicalAttack[2];
    }

    private void setPhysicalAttackIV(double physicalAttackIV) {
        this.physicalAttack[2] = physicalAttackIV;
    }

    // SpecialAttack
    public double getSpecialAttackIV() {
        return this.specialAttack[2];
    }

    private void setSpecialAttackIV(double specialAttackIV) {
        this.specialAttack[2] = specialAttackIV;
    }

    public Moves[] getMoves() {
        return moves;
    }

    public void setMoves(Moves[] moves) {
        this.moves = moves;
    }

    public double getMaxHealthValue() {
        return maxhealth;
    }

    public void setMaxHealthValue(double maxhealth) {
        this.maxhealth = maxhealth;
    }

    public int getPrimaryType() {
        return primaryType;
    }

    public void setPrimaryType(int primaryType) {
        this.primaryType = primaryType;
    }

    public int getSecondaryType() {
        return secondaryType;
    }

    public void setSecondaryType(int secondaryType) {
        this.secondaryType = secondaryType;
    }

    public int getOT() {
        return OT;
    }

    public void setOT(int OT) {
        this.OT = OT;
    }

    
}
