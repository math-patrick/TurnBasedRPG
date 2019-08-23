package turnbasedrpg.moves;

import java.io.Serializable;
import java.util.Random;

/**
 * Class responsible for damage calculations
 * @author Zhaetar
 */
public class DamageCalculation implements Serializable  {

    String message;
    double damage; 

    public void calculateDamage(Pokemon attacker, Pokemon defender, Moves move) {
        Random rand = new Random();
        //  Fórmula de calculo de dano: Damage = ((((2 * Level / 5 + 2) * AttackStat * AttackPower / DefenseStat) / 50) + 2) * STAB * Weakness/Resistance * RandomNumber / 100
        int category = move.getCategory();
        double tempDamage = (((((2 * attacker.getLevel() / 5.0)) + 2)
                * move.getPower()
                * (attacker.getAttackTemp(category) / defender.getDefenseTemp(category))) / 50.0) + 2;
        boolean isCrit = rand.nextInt(24) == 1;
        double randomNumber = (rand.nextInt(16) + 85) / 100.0;
        double effective = 1;
        double STAB = (int) ((attacker.getPrimaryType() == move.getType() || attacker.getSecondaryType() == move.getType())
                ? 1.5 : 1);
        double modifier = (1 * (isCrit ? 1.5 : 1) * (randomNumber) * STAB * effective);
        
        setDamage((tempDamage * modifier)/4);
        
        message = attacker.getName() + " used " + move.getName() + " and dealt " + Math.round(getDamage()) + " damage!";
        if (isCrit) {
            message += " A critical hit!";
        }
        if (effective!=1) {
            message+= (effective > 1) ? " Supper effective!" : " Not very effective..";
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

}
