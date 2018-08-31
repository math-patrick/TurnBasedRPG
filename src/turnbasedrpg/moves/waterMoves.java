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
public class waterMoves extends Moves {
    public waterMoves (int power, String name) {
        this.setFgType(2);
        this.setPower(power);
        this.setName(name);
    }
}
