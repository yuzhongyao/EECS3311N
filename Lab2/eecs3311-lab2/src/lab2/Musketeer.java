package lab2;

public class Musketeer extends Piece {

    public Musketeer() {
        super("X", Type.MUSKETEER);
    }

    /**
     * Returns true if the Musketeer can move onto the given cell.
     * @param cell Cell to check if the Musketeer can move onto
     * @return True, if Musketeer can move onto given cell, false otherwise
     */
    @Override
    public boolean canMoveOnto(Cell cell) { // TODO

    	boolean result = true;

    	//if the cell does not contain a guard piece
    	if(!cell.hasPiece()) {
    		return false;
    	}
    	if(!cell.getPiece().getType().equals(Piece.Type.GUARD)) {
    		result = false;
    	}
    	   	
        return result;
    }
}
