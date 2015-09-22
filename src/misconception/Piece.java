/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misconception;

import misconception.Board;

/**
 *
 * @author sastaputhra
 */
class Piece {
    int x;
    int y;
    boolean isFire;
    String shield;

    Piece(boolean isFire, Board b, int x, int y, String shield) {
        this.isFire = isFire;
        this.x = x;
        this.y =y;
        this.shield = shield;//"pawn", "bomb", or "shield"
    }

    boolean isFire() {
        return isFire;
    }
    
    boolean isKing(){
        return "king".equals(shield);
    }
    
    boolean isBomb(){
        return "bomb".equals(shield);
    }
    
    boolean isShield() {
        return "shield".equals(shield);
    }
    
    int side(){
        if(isFire)
            return 0;
        else
            return 1;
    }
    void move(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    boolean hasCaptured(){
        return true;
    }
    
    void doneCapturing(){
        
    }
}
