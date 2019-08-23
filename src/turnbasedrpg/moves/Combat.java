package turnbasedrpg.moves;

import java.io.Serializable;

/**
 * Class responsible for combat interactions
 *
 * @author matheus.oliveira
 */
public class Combat implements Serializable {

    Pokemon player1;
    Pokemon player2;

    String firstMessage;
    String secondMessage;

    DamageCalculation damageCalculation;

    int firstPlayerID;
    int winnerID = 0;

    public boolean isReady() {
        return !(player1 == null || player2 == null);
    }

    public int calculateSpeedPriority() {
        if (player1.getSpeedValue() > player2.getSpeedValue()) {
            return 1;
        } else {
            return 2;
        }
    }

    public void calculateDamage(int moveID, int moveID2) {
        Pokemon attacker;
        Pokemon defender;
        Moves attackerMove;
        Moves defenderMove;

        damageCalculation = new DamageCalculation();

        if (calculateSpeedPriority() == 1) {
            attacker = player1;
            defender = player2;
            attackerMove = attacker.getPokemonMove(moveID);
            defenderMove = defender.getPokemonMove(moveID2);
            setFirstPlayerID(1);
        } else {
            attacker = player2;
            defender = player1;
            attackerMove = attacker.getPokemonMove(moveID2);
            defenderMove = defender.getPokemonMove(moveID);
            setFirstPlayerID(2);
        }

        double healthDefender = defender.getHealthValue();
        double healthAttacker = attacker.getHealthValue();

        if (attackerMove.getStatChange() == 2) {
            // Debuff no inimigo
            defender.statModifier(attackerMove.statChangePower, attackerMove.statChangeType);
            setFirstMessage(attacker.getName() + " used " + attackerMove.getName() + ". " + attackerMove.getStatChangeName() + " raised by " + attackerMove.statChangePower + " stages!");
        } else if (attackerMove.getStatChange() == 1) {
            // Buff nele mesmo
            attacker.statModifier(attackerMove.statChangePower, attackerMove.statChangeType);
            setFirstMessage(attacker.getName() + " used " + attackerMove.getName() + ". " + defender.getName() + "'s " + attackerMove.getStatChangeName() + " lowered by " + attackerMove.statChangePower + " stages!");
        }

        if (attackerMove.getCategory() != 3) {
            damageCalculation.calculateDamage(attacker, defender, attackerMove);
            setFirstMessage(damageCalculation.getMessage());
            double dmgFirst = damageCalculation.getDamage();
            healthDefender -= dmgFirst;
        }

        if (healthDefender <= 0) {
            healthDefender = 0;
            setWinnerID(attacker.getOT());
            setSecondMessage(null);
        } else {
            if (defenderMove.getStatChange() == 2) {
                // Debuff no inimigo
                attacker.statModifier(defenderMove.statChangePower, defenderMove.statChangeType);
                setSecondMessage(defender.getName() + " used " + defenderMove.getName() + ". " + defenderMove.getStatChangeName() + " raised by " + defenderMove.statChangePower + " stages!");
            } else if (defenderMove.getStatChange() == 1) {
                // Buff nele mesmo
                defender.statModifier(defenderMove.statChangePower, defenderMove.statChangeType);
                setSecondMessage(defender.getName() + " used " + defenderMove.getName() + ". " + attacker.getName() + "'s " + defenderMove.getStatChangeName() + " lowered by " + defenderMove.statChangePower + " stages!");
            }

            if (defenderMove.getCategory() != 3) {
                DamageCalculation damageCalculation2 = new DamageCalculation();
                damageCalculation2.calculateDamage(defender, attacker, defenderMove);
                setSecondMessage(damageCalculation2.getMessage());
                double dmgSecond = damageCalculation2.getDamage();
                healthAttacker -= dmgSecond;
            }

            if (healthAttacker <= 0) {
                setWinnerID(defender.getOT());
                healthAttacker = 0;
            }
        }

        defender.setHealthValue(healthDefender);
        attacker.setHealthValue(healthAttacker);

        setPlayer1((calculateSpeedPriority() == 1) ? attacker : defender);
        setPlayer2((calculateSpeedPriority() == 1) ? defender : attacker);
    }

    public Pokemon getPlayer1() {
        return this.player1;
    }

    public Pokemon getPlayer2() {
        return this.player2;
    }

    public void setPlayer1(Pokemon p1) {
        this.player1 = p1;
    }

    public void setPlayer2(Pokemon p2) {
        this.player2 = p2;
    }

    public DamageCalculation getDamageCalculation() {
        return damageCalculation;
    }

    public void setDamageCalculation(DamageCalculation damageCalculation) {
        this.damageCalculation = damageCalculation;
    }

    public int getFirstPlayerID() {
        return firstPlayerID;
    }

    public void setFirstPlayerID(int firstPlayerID) {
        this.firstPlayerID = firstPlayerID;
    }

    public String getFirstMessage() {
        return firstMessage;
    }

    public void setFirstMessage(String firstMessage) {
        this.firstMessage = firstMessage;
    }

    public String getSecondMessage() {
        return secondMessage;
    }

    public void setSecondMessage(String secondMessage) {
        this.secondMessage = secondMessage;
    }

    public int getWinnerID() {
        return winnerID;
    }

    public void setWinnerID(int winnerID) {
        this.winnerID = winnerID;
    }
}
