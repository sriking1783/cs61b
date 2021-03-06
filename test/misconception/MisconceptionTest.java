/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misconception;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sastaputhra
 */
public class MisconceptionTest {
    
    @Test
    public void testCoreFunctionality() {
        System.out.println("Test 1: See comments in MisconceptionTests.java for description.");
        Board b = new Board(true);

        // Place a shield at position 0, 0.
        Piece shield = new Piece(true, b, 0, 0, "shield");
        b.place(shield, 0, 0);

        // Verify that it can be selected.
        assertTrue(b.canSelect(0, 0));
        b.select(0, 0);

        // Verify that the blank square to the top right of it can be selected.
        assertTrue(b.canSelect(1, 1));
        b.select(1, 1); 

        // Ensure that we can end turn after movement.
        assertTrue(b.canEndTurn());        
    }


    @Test
    public void testThatSelectAndCanSelectDontCallEachOther() {
        System.out.println("Test 2: See comments in MisconceptionTests.java for description.");

        SpyBoard b = new SpyBoard(true);

        // Place a shield at position 0, 0.
        Piece shield = new SpyPiece(true, b, 0, 0, "shield");
        b.place(shield, 0, 0);
        
        assertTrue(b.canSelect(0, 0));

        // Assert that canSelect has been called once, but
        // select has not been called.
        assertEquals(0, b.selectCount);
        assertEquals(1, b.canSelectCount);

        b.select(0, 0);

        // Assert that select and canSelect have been
        // called exactly once.
        assertEquals(1, b.selectCount);
        assertEquals(1, b.canSelectCount);

        assertTrue(b.canSelect(1, 1));

        assertEquals(1, b.selectCount);
        assertEquals(2, b.canSelectCount);

        b.select(1, 1); 

        assertEquals(2, b.selectCount);
        assertEquals(2, b.canSelectCount); 
    }


    @Test
    public void testThatSelectCallsMove() {
        System.out.println("Test 3: See comments in MisconceptionTests.java for description.");

        Board b1 = new Board(true);

        // Place a shield at position 0, 0.
        SpyPiece sshield = new SpyPiece(true, b1, 0, 0, "shield");
        sshield.moveCount= 0;
        b1.place(sshield, 0, 0);
        
        
        b1.select(0, 0);
        assertEquals(0, sshield.moveCount);
       
        b1.select(1, 1); 
        assertEquals(1, sshield.moveCount);
    }
    
    @Test
    public void testThatSelectWINNER() {
        System.out.println("Test 4: See comments in MisconceptionTests.java for description.");
        Board b = new Board(true);

        // Place a shield at position 0, 0.
        Piece shield = new Piece(false, b, 0, 0, "shield");
        b.place(shield, 0, 0);
        
        assertEquals("Water", b.winner());
        
        Piece shield1 = new Piece(true, b, 1, 0, "shield");
        b.place(shield1, 1, 0);
        
        assertEquals(null, b.winner());
    }


    public static void main(String[] args) {
        System.out.println("This file tests common misconceptions" +
            " as observed by Josh (and any TAs who edit this file).");
        System.out.println("If you fail any tests, " +
            " start by fixing test 1, then 2, and so on.");
        System.out.println("Due to JUnit limitations, they may run out of order.");

        //misconception.junit.textui.runClasses(MisconceptionTest.class);      
    }

    /* Special class that spies on your game. */
    public static class SpyBoard extends Board {
        public static int selectCount = 0;
        public static int canSelectCount = 0;

        public SpyBoard(boolean blank) {
            super(blank);
        }

        @Override
        public void select(int x, int y) {
            selectCount += 1;
            super.select(x, y);
        }

        @Override
        public boolean canSelect(int x, int y) {
            canSelectCount += 1;
            return super.canSelect(x, y);
        }
    }

    /* Special class that spies on your game. */
    public static class SpyPiece extends Piece {
        public static int moveCount = 0;

        public SpyPiece(boolean isFire, Board b, int x, int y, String type) {
            super(isFire, b, x, y, type);
        }

        @Override
        public void move(int x, int y) {
            moveCount += 1;
            super.move(x, y);
        }
    }
    
}
