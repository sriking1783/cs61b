/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misconception;
import draw.StdDrawPlus;
import java.util.ArrayList;
import misconception.Piece;

/**
 *
 * @author sastaputhra
 */
class Board {
    
    private static Piece[][] board;
    //ArrayList<Integer> pieces = new ArrayList<>();
    private static Piece[][] pieces;
    private static Piece []fireshield;
    private static Piece []watershield;
    
    private static Piece []firebomb;
    private static Piece []waterbomb;
    
    private static Piece []firepawn;
    private static Piece []waterpawn;
    
    private class Player{
        String name;
        int x;
        int y;
        boolean turn;
        boolean has_moved;
        Player(String name, int x, int y, boolean turn){
            this.name = name;
            this.x = x;
            this.y = y;
            this.turn = turn;
            this.has_moved = false;
        }
        
        
    }
    
    Player water, fire;
    
    private void initialize_pieces() {   
        int i = 0;
        int j = 0;
        water = new Player("water", -1, -1, false);
        fire = new Player("fire", -1, -1, true);
        /*for(Piece piece: firepawn){
            //piece= new  Piece(true, this, i, j, "pawn");
            board[i][j] = new  Piece(true, this, i, j, "pawn");
            i += 2;
        }
        i = 1;
        j = 1;
        for(Piece piece: fireshield){
            //piece= new  Piece(true, this, i, j, "shield");
            board[i][j] = new  Piece(true, this, i, j, "shield");
            i += 2;
        }
        
        i = 0;
        j = 2;
        for(Piece piece: firebomb){
            //piece= new  Piece(true, this, i, j, "bomb");
            board[i][j] = new  Piece(true, this, i, j, "bomb");
            i +=2;
        }
        
        i = 1;
        j = 7;
        for(Piece piece: waterpawn){
            //piece= new  Piece(false, this, i, j, "pawn");
            board[i][j] = new  Piece(false, this, i, j, "pawn");;
            i += 2;
        }
        i = 0;
        j = 6;
        for(Piece piece: watershield){
            //piece= new  Piece(false, this, i, j, "shield");
            board[i][j] = new  Piece(false, this, i, j, "shield");;
            i += 2;
        }
        
        i = 1;
        j = 5;
        for(Piece piece: waterbomb){
            //piece= new  Piece(false, this, i, j, "bomb");
            board[i][j] = new  Piece(false, this, i, j, "bomb");
            i +=2;
        }*/
        pieces = new Piece[8][8];
    }
    
    private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                
                if(board[i][j]!=null) {
                    if(board[i][j].isFire()){
                        if(board[i][j].isShield()){
                             StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        }
                        else if(board[i][j].isBomb()){
                             StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        }
                        else{
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        }
                    }
                    else{
                        if(board[i][j].isShield()){
                             StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                        }
                        else if(board[i][j].isBomb()){
                             StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        }
                        else{
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                        }
                    }
                }
            }
        }
        
        
    }

    Board(boolean shouldBeEmpty) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        fireshield = new Piece[4];
        watershield = new Piece[4];
        
        firebomb = new Piece[4];
        waterbomb = new Piece[4];
        
        firepawn = new Piece[4];
        waterpawn = new Piece[4];
        
        board = new Piece[8][8];
        
        initialize_pieces();
        
        while(!shouldBeEmpty){
            drawBoard(N);
        }
        
    }
    
    Piece pieceAt(int x, int y){
        if(x > 8 || y > 8)
            return null;
        return pieces[x][y];
    }

    void place(Piece shield, int x, int y) {
        if(x > 8 || y > 8)
            return ;
        pieces[x][y] = shield;
    }

    boolean canSelect(int x, int y) {
        Piece piece = pieceAt(x, y);
        if(x > 8 || y > 8)
            return false;
        if(piece != null){
            if(canFireSelect(piece) || canWaterSelect(piece)){
                return true;
            }                
        }
        return true;
    }
    
    Piece remove(int x, int y) {
        return pieces[x][y];
    }

    void select(int x, int y) {
        Player player = current_turn();
        if(canSelect(x, y)){
            if(player.x != x && player.y !=y){
                player.x = x;
                player.y = y;
                player.has_moved = true;
            }
            else{
                player.has_moved = false;
            }
        }
    }

    boolean canEndTurn() {
        Player player = current_turn();
        if (player.has_moved)
            return true;
        return false;
    }
    
    void endTurn() {
        
    }
    
    String winner() {
        return "Fire";
    }
    
    private boolean canFireSelect(Piece piece)
    {
        if(fire.turn)
        {
            if(piece == null){
                return true;
            }
            else if(piece.isFire()){
                return true;
            }
            else{
                return false;
            }
        }
        else
            return false;
    }
      
    private boolean canWaterSelect(Piece piece)
    {
        if(water.turn)
        {
            if(piece == null)
                return true;
            else if(!piece.isFire()){
                return true;
            }
            else
                return false;
        }
        else
            return false;
    } 
    
    private Player current_turn()
    {
        if(water.turn)
            return water;
        else
            return fire;
    }

    
    
}
