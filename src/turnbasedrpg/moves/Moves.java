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
public class Moves {
    public String name = "";
    public int power = 10;
    public int accuracy = 100;
    public int fgType = 1;
    
    public Moves() {
        
    }
    
    public void getMoves() {
        Moves waterGun = new Moves();
        waterGun.setFgType(2);
        waterGun.setAccuracy(100);
        waterGun.setPower(40);
        waterGun.setName("Water Gun");
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
