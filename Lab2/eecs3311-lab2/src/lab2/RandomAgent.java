package lab2;

import java.util.ArrayList;
import java.util.List;

public class RandomAgent extends Agent {

    public RandomAgent(Board board) {
        super(board);
    }

    /**
     * Gets a valid random move the RandomAgent can do.
     * @return a valid Move that the RandomAgent can perform on the Board
     */
    @Override
    public Move getMove() { // TODO
    	List<Move> result = new ArrayList<Move>();
    	Move r;
    	int i;

    	result = board.getPossibleMoves();
    	i = (int) (Math.random() * result.size());
    	r = result.get(i);
    	
        return r;
    }
}
