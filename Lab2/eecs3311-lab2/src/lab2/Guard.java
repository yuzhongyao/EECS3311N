package lab2;

public class Guard extends Piece {

    public Guard() {
        super("O", Type.GUARD);
    }

    /**
     * Returns true if the Guard can move onto the given cell.
     * @param cell Cell to check if the Guard can move onto
     * @return True, if Guard can move onto given cell, false otherwise
     */
    @Override
    public boolean canMoveOnto(Cell cell) { // TODO
    	boolean result = true;
        //guard can only move if the space is empty
        if(cell.hasPiece()) {
        	result = false;       	
        }
        
    	return result;
    }
}
