package lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lab2.Exceptions.InvalidMoveException;

public class HumanAgent extends Agent {

    public HumanAgent(Board board) {
        super(board);
    }

    /**
     * Asks the human for a move with from and to coordinates and makes sure its valid.
     * Create a Move object with the chosen fromCell and toCell
     * @return the valid human inputted Move
     */
    @Override
    public Move getMove() { // TODO

    	List<Cell> from = new ArrayList<Cell>();
    	List<Cell> dest = new ArrayList<Cell>();
    	List<String> s = new ArrayList<String>();
    	Coordinate coor = null;
    	from = board.getPossibleCells();
    	String temp;
    	
    	
    	Scanner scanner = new Scanner(System.in);
    	System.out.print("Possible pieces are ");
    	for(Cell c: from) {
    		temp = Character.toString(Utils.convertIntToLetter(c.getCoordinate().col + 1)) + Integer.toString(c.getCoordinate().row + 1);
    		s.add(temp);
    	}
    	System.out.print(s.toString() + ". Enter the piece you want to move: ");    	
    	while(!scanner.hasNext("[A-Za-z][1-5]") ) {	
    		System.out.print("\nEnter a possible Coordinate from " +s.toString());
    		
    		scanner.next().toUpperCase();
    		
    	}
    	
    	s.clear();
    	
    	try {
			coor = Utils.parseUserMove(scanner.next().toUpperCase());
		} catch (InvalidMoveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    
    	System.out.print("Possible destinations are ");
    	dest = board.getPossibleDestinations(board.getCell(coor));
    	for(Cell c: dest) {
    		temp = Character.toString(Utils.convertIntToLetter(c.getCoordinate().col + 1)) + Integer.toString(c.getCoordinate().row + 1);
    		s.add(temp);
    	}
    	System.out.print(s.toString() + ". Enter where you want to move: "); 
    	while(!scanner.hasNext("[A-Za-z][1-5]")) {	
    		System.out.print("\nEnter a possible Coordinate from " +s.toString());
    		scanner.next().toUpperCase();
    	}
    	Coordinate coor2 = null;
    	try {
			coor2 = Utils.parseUserMove(scanner.next().toUpperCase());
		} catch (InvalidMoveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Move r = new Move(board.getCell(coor), board.getCell(coor2));
    	
    	
    	
        return r;
    }
}
