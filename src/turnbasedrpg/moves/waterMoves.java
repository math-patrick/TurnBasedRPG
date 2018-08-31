package turnbasedrpg.moves;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author matheus.oliveira
 */
public class waterMoves extends Moves {
    
    public waterMoves() {
        this.fgType = 1; //Tipo: Água
    }
    
    public void getMoves() {
        waterMoves waterGun = new waterMoves();
        waterGun.setAccuracy(100);
        waterGun.setPower(40);
        waterGun.setName("Water Gun");
    }
}
