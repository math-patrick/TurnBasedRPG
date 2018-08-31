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
public class Types {
    public String name;
    public int fg;
    
    public Types (int fg) {
        switch (fg) {
            case 2:
                this.name = "Water";
            default:
                this.name = "Normal";
        }
    }
}
