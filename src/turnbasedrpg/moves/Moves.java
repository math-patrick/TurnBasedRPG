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
    public String name = "";
    public int power = 10;
    public int accuracy = 100;
    public int fgType = 1;
    
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
    
    public Moves getWaterMove(int id) {
        switch (id) {
            case 1:
                Moves waterGun = new Moves(40, "Water Gun");
                return waterGun;
            case 2:
                Moves bubble = new Moves(30, "Bubble");
                return bubble;
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
