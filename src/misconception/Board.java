/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misconception;
import draw.StdDrawPlus;
import java.awt.Point;
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
        Piece piece;
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
        
        Player player = current_turn();
        Piece piece = pieceAt(x,y);
        if((piece != null) && (piece.x != x && piece.y != y )){
            shield.move(x, y);
        }
        else if(player.piece == null){
            player.piece = shield;
        }
         else if(player.piece == shield){
            shield.move(x, y);
            player.x = x;
            player.y = y;
            player.has_moved = true;
        }
        
        pieces[x][y] = shield;
    }

    boolean canSelect(int x, int y) {
        Piece piece = pieceAt(x, y);
        Player player = current_turn();
        
        if(x > 8 || y > 8)
            return false;
        if(player.piece == null){
            if(piece != null){
                if(canFireSelect(piece) || canWaterSelect(piece)){
                    return true;
                }                
            }
        }
        return true;
    }
    
    Piece remove(int x, int y) {
        return pieces[x][y];
    }

    void select(int x, int y) {
        Player player = current_turn();
            if((player.x == -1 && player.y == -1)||(player.piece == null)){
                player.x = x;
                player.y = y;
                player.piece = pieceAt(x, y);
            }

            else if(player.x != x && player.y !=y){
                player.x = x;
                player.y = y;
                if(player.piece !=null){
                    place(player.piece, x, y);
                }
                else{
                    player.piece = pieceAt(x, y);
                    player.has_moved = true;
                }
                    
            }
        
        //player.can_select = null;
    }

    boolean canEndTurn() {
        Player player = current_turn();
        if (player.has_moved)
            return true;
        return false;
    }
    
    void endTurn() {
        Player player = current_turn();
        player.has_moved = false;
        player.piece = null;
        player.x = -1;
        player.y = -1;
        player.turn=false;
        
        Player other_player = other_turn();
        other_player.has_moved = false;
        other_player.turn=true;
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

    private Player other_turn()
    {
        if(fire.turn)
            return water;
        else
            return fire;
    }

    
    
}
