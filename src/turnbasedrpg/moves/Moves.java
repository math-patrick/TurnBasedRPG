/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnbasedrpg.moves;

/**
 *
 * @author matheus.oliveira
 */
public final class Moves {
    // Name
    public String name = "";
    // Power
    public int power = 10;
    // PP (power points)
    public int pp = 20;
    // Accuracy
    public int accuracy = 100;
    // Type (1. Normal, 2. Water)
    public int fgType = 1;
    // Category (1. Physical, 2. Special, 3. Status)
    public int fgCategory = 1;
    // Does it changes a pokémon status? (1 - Yes, 2 - No)
    public int statChange = 2;
    // What stat does it change? (1 - Attack, 2 - Special Attack, 3 - Defense, 4 - Special Defense, 5 - Speed, 6 - Accuracy, 7 - Avoidance)
    public int statChangeType = 1;
    // By how much?
    public int statChangePower = 1;
    // Does it afflict a status change? (1 - Yes, 2 - No)
    public int statusChange = 2; 
    // Which one? (1 - Confusion, 2 - Burn, 3 - Frozen, 4 - Sleep, 5 - Poison)
    public int statusChangeType = 1; 
    
    public Moves() {
        
    }
    
    public Moves (int power, String name, int fgType, int accuracy) {
        this.setPower(power);
        this.setName(name);
        this.setFgType(fgType);
        this.setAccuracy(accuracy);
    }
    
    public Moves (int power, String name, int fgType) {
        this.setPower(power);
        this.setName(name);
        this.setFgType(fgType);
    }
    
    public Moves (int power, String name) {
        this.setPower(power);
        this.setName(name);
    }
    
    public Moves getNormalMove(int id) {
        switch (id) {
            case 1:
                return new Moves(40, "Water Gun");
            case 2:
                Moves bubble = new Moves(30, "Bubble");
                return bubble;
        }
        return null;
    }
    
    public Moves getWaterMove(int id) {
        switch (id) {
            case 1:
                return new Moves(40, "Water Gun", 2);
            case 2:
                return new Moves(30, "Bubble", 2);
            case 3:
                return new Moves(60, "Water Pulse", 2);
        }
        return null;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }
    
    public void setFgType(int fgType) {
        this.fgType = fgType;
    }
    
    public int getFgType() {
        return fgType;
    }
}
