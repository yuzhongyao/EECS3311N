package lab2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Board {
    public int size = 5;

    // 2D Array of Cells for representation of the game board
    public final Cell[][] board = new Cell[size][size];

    private Piece.Type turn;
    private Piece.Type winner;

    /**
     * Create a Board with the current player turn set.
     */
    public Board() {
    	//starter code had capital 'B' in 'boards/Starter.txt'
    	//but actual text file is lower case 'b'
        this.loadBoard("boards/Starter.txt");
        
    }

    /**
     * Create a Board with the current player turn set and a specified board.
     * @param boardFilePath The path to the board file to import (e.g. "Boards/Starter.txt")
     */
    public Board(String boardFilePath) {
        this.loadBoard(boardFilePath);
    }

    /**
     * Creates a Board copy of the given board.
     * @param board Board to copy
     */
    public Board(Board board) {
        this.size = board.size;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                this.board[row][col] = new Cell(board.board[row][col]);
            }
        }
        this.turn = board.turn;
        this.winner = board.winner;
    }

    /**
     * @return the Piece.Type (Muskeeteer or Guard) of the current turn
     */
    public Piece.Type getTurn() {
        return turn;
    }

    /**
     * Get the cell located on the board at the given coordinate.
     * @param coordinate Coordinate to find the cell
     * @return Cell that is located at the given coordinate
     */
    public Cell getCell(Coordinate coordinate) { // TODO
        return this.board[coordinate.row][coordinate.col];
    }

    /**
     * @return the game winner Piece.Type (Muskeeteer or Guard) if there is one otherwise null
     */
    public Piece.Type getWinner() {
        return winner;
    }

    /**
     * Gets all the Musketeer cells on the board.
     * @return List of cells
     */
    public List<Cell> getMusketeerCells() { // TODO
    	List<Cell> result = new ArrayList<Cell>();
    	for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if(this.board[row][col].hasPiece()) {
                	if(this.board[row][col].getPiece().getType().equals(Piece.Type.MUSKETEER)) {
                	result.add(this.board[row][col]);
                	}
                	
                }
            }	
        }
        
        return result;
    }

    /**
     * Gets all the Guard cells on the board.
     * @return List of cells
     */
    public List<Cell> getGuardCells() { // TODO
    	List<Cell> result = new ArrayList<Cell>();
    	for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if(this.board[row][col].hasPiece()) {
                	if(this.board[row][col].getPiece().getType().equals(Piece.Type.GUARD)) {
                	result.add(this.board[row][col]);
                	}
                	
                }
            }	
        }
        
        return result;
    }

    /**
     * Executes the given move on the board and changes turns at the end of the method.
     * @param move a valid move
     */
    public void move(Move move) { // TODO
    	
    	if(isValidMove(move)) {
    		
        	if(this.turn.equals(Piece.Type.MUSKETEER)) {
        		this.board[move.toCell.getCoordinate().row][move.toCell.getCoordinate().col].setPiece(move.fromCell.getPiece());
        		this.board[move.fromCell.getCoordinate().row][move.fromCell.getCoordinate().col].removePiece();    
        		this.turn = Piece.Type.GUARD;
        		
        	}
        	else {
        		this.board[move.toCell.getCoordinate().row][move.toCell.getCoordinate().col].setPiece(move.fromCell.getPiece());
        		this.board[move.fromCell.getCoordinate().row][move.fromCell.getCoordinate().col].removePiece(); 
        		this.turn = Piece.Type.MUSKETEER;
        	}
    	}
    }

    /**
     * Undo the move given.
     * @param move Copy of a move that was done and needs to be undone. The move copy has the correct piece info in the
     *             from and to cell fields. Changes turns at the end of the method.
     */
    public void undoMove(Move move) { // TODO
    	Move copy = new Move(move);
    	//undo musketeers turn
    	if(this.turn.equals(Piece.Type.GUARD)) {
    		this.board[copy.fromCell.getCoordinate().row][copy.fromCell.getCoordinate().col].setPiece(new Musketeer());
    		this.board[copy.toCell.getCoordinate().row][copy.toCell.getCoordinate().col].setPiece(new Guard());
    		this.turn = Piece.Type.MUSKETEER;
    	}
    	//undo guards turn
    	else {
    		this.board[copy.fromCell.getCoordinate().row][copy.fromCell.getCoordinate().col].setPiece(new Guard());
    		this.board[copy.toCell.getCoordinate().row][copy.toCell.getCoordinate().col].removePiece();
    		this.turn = Piece.Type.GUARD;
    	}
    	
    	
    }

    /**
     * Checks if the given move is valid. Things to check:
     * (1) the toCell is next to the fromCell
     * (2) the fromCell piece can move onto the toCell piece.
     * @param move a move
     * @return     True, if the move is valid, false otherwise
     */
    public Boolean isValidMove(Move move) { // TODO
    	boolean result = true;
    	
    	if(!(move.fromCell.hasPiece())) {
    		return false;
    	}
    	
    	
        int from_col = move.fromCell.getCoordinate().col;
        int from_row = move.fromCell.getCoordinate().row;
        int to_col = move.toCell.getCoordinate().col;
        int to_row = move.toCell.getCoordinate().row;
        
        
        if(from_col < 0 || from_col > size - 1 || from_row < 0 || from_row > size - 1 || to_col < 0 || to_col > size - 1 || to_row < 0 || to_row > size - 1) {
        	return false;
        }
        
        
        //check if cells are next to each other orthogonally
        
        if(!((Math.abs(from_col - to_col) == 1) && (from_row == to_row)) && !((Math.abs(from_row - to_row) == 1) && (from_col == to_col))) {
        	return false;
        }
        //check if cells has eligible pieces to be able to move
        //Musketeer turn
        if(turn.equals(Piece.Type.MUSKETEER)) {
        	if(!(move.fromCell.getPiece().getType().equals(Piece.Type.MUSKETEER))) {
        		result = false;
        	}
        	else {
        		if(!(move.toCell.hasPiece())) {
        			result = false;
        		}
        		else {
        			if(!(move.toCell.getPiece().getType().equals(Piece.Type.GUARD))) {
        				result = false;
        			}
        		}
        	}
        }
        
        if(turn.equals(Piece.Type.GUARD)) {
        	if(!(move.fromCell.getPiece().getType().equals(Piece.Type.GUARD))) {
        		result = false;
        	}
        	if(move.toCell.hasPiece()) {
        		result = false;
        	}
        	
        }
        
    	return result;
    }

    /**
     * Get all the possible cells that have pieces that can be moved this turn.
     * @return      Cells that can be moved from the given cells
     */
    public List<Cell> getPossibleCells() { // TODO
    	List<Cell> result = new ArrayList<Cell>();
    	List<Cell> turn_cells = new ArrayList<Cell>();
    	
    	if(turn.equals(Piece.Type.MUSKETEER)) {
    		turn_cells = getMusketeerCells();
    	}
    	if(turn.equals(Piece.Type.GUARD)) {
    		turn_cells = getGuardCells();
    	}
    	
    	for(Cell c : turn_cells) {
    		//able to move up
    		if(c.getCoordinate().row + 1 < 5) {
    		Move t1 = new Move(c, board[c.getCoordinate().row + 1][c.getCoordinate().col]);
    		if(isValidMove(t1)) {
    			result.add(c);
    			continue;
    			
    		}
    		}
    		//able to move down
    		if(c.getCoordinate().row - 1 > 0) {
    		Move t2 = new Move(c, board[c.getCoordinate().row - 1][c.getCoordinate().col]);
    		if(isValidMove(t2)) {
    			result.add(c);
    			continue;
    		}
    		}
    		//able to move left
    		if(c.getCoordinate().col - 1 > 0) {
    		Move t3 = new Move(c, board[c.getCoordinate().row][c.getCoordinate().col - 1]);
    		if(isValidMove(t3)) {
    			result.add(c);
    			continue;
    		}
    		}
    		//able to move right
    		if(c.getCoordinate().col + 1 < 5) {
    		Move t4 = new Move(c, board[c.getCoordinate().row][c.getCoordinate().col + 1]);
    		if(isValidMove(t4)) {
    			result.add(c);
    			continue;
    		}
    		}
    	}
    	
        return result;
    }

    /**
     * Get all the possible cell destinations that is possible to move to from the fromCell.
     * @param fromCell The cell that has the piece that is going to be moved
     * @return List of cells that are possible to get to
     */
    public List<Cell> getPossibleDestinations(Cell fromCell) { // TODO
    	List<Cell> result = new ArrayList<Cell>();
    	if(!(fromCell.getCoordinate().row + 1 > size - 1)) {
    		Move t1 = new Move(fromCell, board[fromCell.getCoordinate().row + 1][fromCell.getCoordinate().col]);
    		if(isValidMove(t1)) {
        		result.add(board[fromCell.getCoordinate().row + 1][fromCell.getCoordinate().col]);
        	}
    	}
    	if(!(fromCell.getCoordinate().row - 1 < 0)) {
    		Move t2 = new Move(fromCell, board[fromCell.getCoordinate().row - 1][fromCell.getCoordinate().col]);
    		if(isValidMove(t2)) {
        		result.add(board[fromCell.getCoordinate().row - 1][fromCell.getCoordinate().col]);
        	}
    	}
    	if(!(fromCell.getCoordinate().col - 1 < 0)) {
    		Move t3 = new Move(fromCell, board[fromCell.getCoordinate().row][fromCell.getCoordinate().col - 1]);
    		if(isValidMove(t3)) {
        		result.add(board[fromCell.getCoordinate().row][fromCell.getCoordinate().col - 1]);
        	}
    	}
    	if(!(fromCell.getCoordinate().col + 1 > size - 1)) {
    	Move t4 = new Move(fromCell, board[fromCell.getCoordinate().row][fromCell.getCoordinate().col + 1]);
    		if(isValidMove(t4)) {
    			result.add(board[fromCell.getCoordinate().row][fromCell.getCoordinate().col + 1]);
    		}
    	}
        return result;
    }
    

    /**
     * Get all the possible moves that can be made this turn.
     * @return List of moves that can be made this turn
     */
    public List<Move> getPossibleMoves() { // TODO
    	List<Move> result = new ArrayList<Move>();
    	List<Cell> possible = new ArrayList<Cell>();   	
    	List<Cell> dest = new ArrayList<Cell>();
    	
    	possible = getPossibleCells();
    	
    		
    	for(Cell poss : possible) {
    		dest = getPossibleDestinations(poss);
    		for(Cell d: dest) {
    			result.add( new Move(poss, d));
    		}
    			
    	}
    	
    	
        return result;
    }

    /**
     * Checks if the game is over and sets the winner if there is one.
     * @return True, if the game is over, false otherwise.
     */
    public boolean isGameOver() { // TODO
    	boolean result = false;
    	if(getGuardCells().size() == 0) {
    		result = true;
    		this.winner = Piece.Type.MUSKETEER;
    	}
    	List<Cell> musk = new ArrayList<Cell>();
    	musk = getMusketeerCells();
    	if(musk.get(0).getCoordinate().row == musk.get(1).getCoordinate().row && musk.get(0).getCoordinate().row == musk.get(2).getCoordinate().row
    			||musk.get(0).getCoordinate().col == musk.get(1).getCoordinate().col && musk.get(0).getCoordinate().col == musk.get(2).getCoordinate().col) {
    		result = true;
    		this.winner = Piece.Type.GUARD;
    	}
    	if(this.turn.equals(Piece.Type.MUSKETEER)) {
    		if(getPossibleMoves().size() == 0) {
    			this.winner = Piece.Type.MUSKETEER;
    			result = true;
    		}
    	}

    
        return result;
    }

    /**
     * Saves the current board state to the boards directory
     */
    public void saveBoard() {
        String filePath = String.format("boards/%s.txt",
                new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
        File file = new File(filePath);

        try {
            file.createNewFile();
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            writer.write(turn.getType() + "\n");
            for (Cell[] row: board) {
                StringBuilder line = new StringBuilder();
                for (Cell cell: row) {
                    if (cell.getPiece() != null) {
                        line.append(cell.getPiece().getSymbol());
                    } else {
                        line.append("_");
                    }
                    line.append(" ");
                }
                writer.write(line.toString().strip() + "\n");
            }
            writer.close();
            System.out.printf("Saved board to %s.\n", filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.printf("Failed to save board to %s.\n", filePath);
        }
    }

    @Override
    public String toString() {
        StringBuilder boardStr = new StringBuilder("  | A B C D E\n");
        boardStr.append("--+----------\n");
        for (int i = 0; i < size; i++) {
            boardStr.append(i + 1).append(" | ");
            for (int j = 0; j < size; j++) {
                Cell cell = board[i][j];
                boardStr.append(cell).append(" ");
            }
            boardStr.append("\n");
        }
        return boardStr.toString();
    }

    /**
     * Loads a board file from a file path.
     * @param filePath The path to the board file to load (e.g. "boards/Starter.txt")
     */
    private void loadBoard(String filePath) {
        File file = new File(filePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.printf("File at %s not found.", filePath);
            System.exit(1);
        }

        turn = Piece.Type.valueOf(scanner.nextLine().toUpperCase());

        int row = 0, col = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] pieces = line.trim().split(" ");
            for (String piece: pieces) {
                Cell cell = new Cell(new Coordinate(row, col));
                switch (piece) {
                    case "O" -> cell.setPiece(new Guard());
                    case "X" -> cell.setPiece(new Musketeer());
                    default -> cell.setPiece(null);
                }
                this.board[row][col] = cell;
                col += 1;
            }
            col = 0;
            row += 1;
        }
        scanner.close();
        System.out.printf("Loaded board from %s.\n", filePath);
    }
}
