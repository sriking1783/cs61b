/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misconception;
import draw.StdDrawPlus;
import misconception.Piece;

/**
 *
 * @author sastaputhra
 */
class Board {
    
    private static Piece[][] board;
    private static Piece[][] pieces;
    private static Piece []fireshield;
    private static Piece []watershield;
    
    private static Piece []firebomb;
    private static Piece []waterbomb;
    
    private static Piece []firepawn;
    private static Piece []waterpawn;
    
    private void initialize_pieces() {
        int i = 0;
        int j = 0;
        for(Piece piece: firepawn){
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
        }
        
        pieces = new Piece[][] { firepawn,fireshield,firebomb,waterpawn,watershield,waterbomb };
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
        for(Piece[] piece_array : pieces){
            
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
        
        while(shouldBeEmpty){
            drawBoard(N);
        }
        
    }
    
    Piece pieceAt(int x, int y){
        if(x > 8 || y > 8)
            return null;
        return pieces[x][y];
    }

    void place(Piece shield, int x, int y) {
        shield.move(x, y);
    }

    boolean canSelect(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    Piece remove(int x, int y) {
        return pieces[x][y];
    }

    void select(int x, int y) {
        if(canSelect(x, y)){
            
        }
    }

    boolean canEndTurn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    void endTurn() {
        
    }
    
    String winner() {
        return "Fire";
    }

    
    
}
