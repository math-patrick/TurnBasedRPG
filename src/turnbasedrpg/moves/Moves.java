/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnbasedrpg.moves;

import java.io.Serializable;

/**
 *
 * @author matheus.oliveira
 */
public final class Moves implements Serializable {

    /**
     * Move name
     */
    public String name = "";

    /**
     * Move power, defaults 10
     */
    public int power = 10;

    /**
     * Move power points, defaults 20
     */
    public int pp = 20;
    // Accuracy
    public int accuracy = 100;
    // Type (1. Normal, 2. Water)
    public int fgType = 1;
    // Category (1. Physical, 2. Special, 3. Status)
    public int fgCategory = 1;
    // Does it changes a pokémon status? (1 - Yes (enemy), 2 - Yes (self), 0 - No)
    public int statChange = 0;
    // What stat does it change? (1 - Attack, 2 - Special Attack, 3 - Defense, 4 - Special Defense, 5 - Speed, 6 - Accuracy, 7 - Avoidance)
    public int statChangeType = 1;
    // By how much?
    public int statChangePower = 1;
    // Does it afflict a status change? (1 - Yes, 2 - No)
    public int statusChange = 2; 
    // Which one? (1 - Confusion, 2 - Burn, 3 - Frozen, 4 - Sleep, 5 - Poison)
    public int statusChangeType = 1; 
        
    public Moves () {
    }
    
    public Moves getNormalMove(int id) {
        Moves move = new Moves();
        move.setFgType(1);
        switch (id) {
            case 1:
                // Tackle
                move.setName("Tackle");
                move.setPP(35);
                move.setPower(40);
                move.setFgCategory(1);
                break;
            case 2:
                // Tail Whip
                move.setName("Tail Whip");
                move.setPP(30);
                move.setPower(0);
                move.setFgCategory(3);
                move.setStatChange(1);
                move.setStatChangeType(3);
                break;
        }
        return move;
    }
    
    public Moves getWaterMove(int id) {
        Moves waterMove = new Moves();
        waterMove.setFgType(2);
        switch (id) {
            case 1:
                // Water Gun
                waterMove.setName("Water Gun");
                waterMove.setPP(25);
                waterMove.setPower(40);
                waterMove.setFgCategory(2);
                break;
            case 2:
                // Withdraw
                waterMove.setName("Withdraw");
                waterMove.setPP(40);
                waterMove.setPower(0);
                waterMove.setFgCategory(3);
                waterMove.setStatChange(2);
                waterMove.setStatChangeType(3);
                break;
        }
        return waterMove;
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

    public int getPP() {
        return pp;
    }

    public void setPP(int pp) {
        this.pp = pp;
    }

    public int getFgCategory() {
        return fgCategory;
    }

    public void setFgCategory(int fgCategory) {
        this.fgCategory = fgCategory;
    }

    public int getStatChange() {
        return statChange;
    }

    public void setStatChange(int statChange) {
        this.statChange = statChange;
    }

    public int getStatChangeType() {
        return statChangeType;
    }

    public void setStatChangeType(int statChangeType) {
        this.statChangeType = statChangeType;
    }

    public int getStatChangePower() {
        return statChangePower;
    }

    public void setStatChangePower(int statChangePower) {
        this.statChangePower = statChangePower;
    }

    public int getStatusChange() {
        return statusChange;
    }

    public void setStatusChange(int statusChange) {
        this.statusChange = statusChange;
    }

    public int getStatusChangeType() {
        return statusChangeType;
    }

    public void setStatusChangeType(int statusChangeType) {
        this.statusChangeType = statusChangeType;
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
