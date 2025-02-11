package com.gamewerks.blocky.engine;

import com.gamewerks.blocky.util.Constants;
import com.gamewerks.blocky.util.Position;

public class BlockyGame {
    private static final int LOCK_DELAY_LIMIT = 30;
    
    private Board board;
    private Piece activePiece;
    private Direction movement;
    static PieceKind[] pieceArray = {PieceKind.I, PieceKind.J,PieceKind.L,PieceKind.O,PieceKind.S,PieceKind.T,PieceKind.Z};
    PieceKind[] newArray = shuffle(pieceArray);
    int cur = 0;
    private int lockCounter;
    
    public BlockyGame() {
        board = new Board();
        movement = Direction.NONE;
        lockCounter = 0;
        trySpawnBlock();
    }
    
    //checks for duplicates in the array, and if there is one, then returns fale. Otherwise, returns true.
    private boolean noDuplicate(PieceKind[] arr, int randomNumberInt) {
        for(int i = 0; i < arr.length; i++) {
            if(newArray[i] == arr[randomNumberInt]) {
                //duplicate found
                return(false);
            }
        }
        return(true);
    }
    
    //Shuffles the array by creating a new array with random values from the original array.
    private PieceKind[] shuffle(PieceKind[] arr){
       
        newArray = new PieceKind[arr.length];
        
        for(int i = 0; i < arr.length; i++)
        {
            int randomNumberInt = (int) (Math.random() * (arr.length));
            
            if (newArray[0] == null) {
                newArray[0] = arr[randomNumberInt];
            } else {
                
                if ((noDuplicate(arr,randomNumberInt)) == true) {
                    newArray[i] = arr[randomNumberInt];
                
                } else if (noDuplicate(arr,randomNumberInt) == false) {
                    i--;
                }
            }
            
        }
        return(newArray);
    }
    
    private void trySpawnBlock() {

        if (activePiece == null) {
            activePiece = new Piece(newArray[cur], new Position(Constants.BOARD_HEIGHT - 1, Constants.BOARD_WIDTH / 2 - 2));
            cur++;

            if (cur > pieceArray.length-1) {
                newArray = shuffle(pieceArray);
                cur = 0;
            }
            if (board.collides(activePiece)) {
                System.exit(0);
            }
            
        }
    }
    
    
    private void processMovement() {
        Position nextPos;
        switch(movement) {
        case NONE:
            nextPos = activePiece.getPosition();
            break;
        case LEFT:
            nextPos = activePiece.getPosition().add(0, -1);
            break;
        case RIGHT:
            nextPos = activePiece.getPosition().add(0, 1);
            break;
        default:
            throw new IllegalStateException("Unrecognized direction: " + movement.name());
        }
        if (!board.collides(activePiece.getLayout(), nextPos)) {
            activePiece.moveTo(nextPos);
        }
    }
    
    private void processGravity() {
        Position nextPos = activePiece.getPosition().add(-1, 0);
        if (!board.collides(activePiece.getLayout(), nextPos)) {
            lockCounter = 0;
            activePiece.moveTo(nextPos);
        } else {
            if (lockCounter < LOCK_DELAY_LIMIT) {
                lockCounter += 1;
            } else {
                board.addToWell(activePiece);
                lockCounter = 0;
                activePiece = null;
            }
        }
    }
    
    private void processClearedLines() {
        board.deleteRows(board.getCompletedRows());
    }
    
    public void step() {
        trySpawnBlock();
        processMovement();
        processGravity();
        processClearedLines();
    }
    
    public boolean[][] getWell() {
        return board.getWell();
    }
    
    public Piece getActivePiece() { return activePiece; }
    public void setDirection(Direction movement) { this.movement = movement; }
    public void rotatePiece(boolean dir) { activePiece.rotate(dir); }
}
