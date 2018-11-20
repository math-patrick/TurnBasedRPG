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
    
    double physicalAttackModifier;
    double specialAttackModifier;
    double physicalDefenseModifier;
    double specialDefenseModifier;
    double speedModifier;
    
    double physicalAttackTemp;
    double specialAttackTemp;
    double physicalDefenseTemp;
    double specialDefenseTemp;
    double speedTemp;
     
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

    
    public void statModifier(int stages, int type) {
        // What stat does it change? (1 - Attack, 2 - Special Attack, 3 - Defense, 4 - Special Defense, 5 - Speed, 6 - Accuracy, 7 - Avoidance)
        double stat = 0;
        switch(type) {
            // Attack
            case 1: 
                stat = getPhysicalAttackModifier();
                break;
            // SAttack
            case 2: 
                stat = getSpecialAttackModifier();
                break;
            // Defense
            case 3: 
                stat = getPhysicalDefenseModifier();
                break;
            // SDefense
            case 4: 
                stat = getSpecialDefenseModifier();
                break;
            // Speed
            case 5: 
                stat = getSpeedModifier();
                break;
        }
        
        double newStatModifier;
        stat += stages;
        
        if (stat > 6) {
            stat = 6;
        } else if (stat < -6){
            stat = -6;
        }
        
        double multiplier;
        
        if (stat > 0) {
            multiplier = stat * 0.5;
            multiplier ++;
        } else if (stat < 0) {
            
        }
        
        switch(type) {
            // Attack
            case 1: 
                setPhysicalAttackModifier(stat);
                break;
            // SAttack
            case 2: 
                setSpecialAttackModifier(stat);
                break;
            // Defense
            case 3: 
                setPhysicalDefenseModifier(stat);
                break;
            // SDefense
            case 4: 
                setSpecialDefenseModifier(stat);
                break;
            // Speed
            case 5: 
                setSpeedModifier(stat);
                break;
        }
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

    public double getPhysicalAttackModifier() {
        return physicalAttackModifier;
    }

    public void setPhysicalAttackModifier(double physicalAttackModifier) {
        this.physicalAttackModifier = physicalAttackModifier;
    }

    public double getSpecialAttackModifier() {
        return specialAttackModifier;
    }

    public void setSpecialAttackModifier(double specialAttackModifier) {
        this.specialAttackModifier = specialAttackModifier;
    }

    public double getPhysicalDefenseModifier() {
        return physicalDefenseModifier;
    }

    public void setPhysicalDefenseModifier(double physicalDefenseModifier) {
        this.physicalDefenseModifier = physicalDefenseModifier;
    }

    public double getSpecialDefenseModifier() {
        return specialDefenseModifier;
    }

    public void setSpecialDefenseModifier(double specialDefenseModifier) {
        this.specialDefenseModifier = specialDefenseModifier;
    }

    public double getSpeedModifier() {
        return speedModifier;
    }

    public void setSpeedModifier(double speedModifier) {
        this.speedModifier = speedModifier;
    }

    public double getMaxhealth() {
        return maxhealth;
    }

    public void setMaxhealth(double maxhealth) {
        this.maxhealth = maxhealth;
    }

    public double[] getPhysicalAttack() {
        return physicalAttack;
    }

    public void setPhysicalAttack(double[] physicalAttack) {
        this.physicalAttack = physicalAttack;
    }

    public double[] getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(double[] specialAttack) {
        this.specialAttack = specialAttack;
    }

    public double[] getPhysicalDefense() {
        return physicalDefense;
    }

    public void setPhysicalDefense(double[] physicalDefense) {
        this.physicalDefense = physicalDefense;
    }

    public double[] getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(double[] specialDefense) {
        this.specialDefense = specialDefense;
    }

    public double[] getSpeed() {
        return speed;
    }

    public void setSpeed(double[] speed) {
        this.speed = speed;
    }

    public double[] getHealth() {
        return health;
    }

    public void setHealth(double[] health) {
        this.health = health;
    }

    public double getPhysicalAttackTemp() {
        return physicalAttackTemp;
    }

    public void setPhysicalAttackTemp(double physicalAttackTemp) {
        this.physicalAttackTemp = physicalAttackTemp;
    }

    public double getSpecialAttackTemp() {
        return specialAttackTemp;
    }

    public void setSpecialAttackTemp(double specialAttackTemp) {
        this.specialAttackTemp = specialAttackTemp;
    }

    public double getPhysicalDefenseTemp() {
        return physicalDefenseTemp;
    }

    public void setPhysicalDefenseTemp(double physicalDefenseTemp) {
        this.physicalDefenseTemp = physicalDefenseTemp;
    }

    public double getSpecialDefenseTemp() {
        return specialDefenseTemp;
    }

    public void setSpecialDefenseTemp(double specialDefenseTemp) {
        this.specialDefenseTemp = specialDefenseTemp;
    }

    public double getSpeedTemp() {
        return speedTemp;
    }

    public void setSpeedTemp(double speedTemp) {
        this.speedTemp = speedTemp;
    }

    
}
