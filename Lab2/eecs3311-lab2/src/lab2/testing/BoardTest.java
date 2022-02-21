package lab2.testing;

import java.util.List;

import org.junit.*;

import lab2.Board;
import lab2.Cell;
import lab2.Coordinate;
import lab2.Piece;

public class BoardTest {

    private Board board;

    @Before
    public void setup() {
        this.board = new Board();
    }

    @Test
    public void testGetCell() {
        Cell cell = board.getCell(new Coordinate(1, 4));
        Assert.assertNotNull(cell.getPiece());
    }
    
    @Test
    public void testNumberOfMusketeers() {
    	List<Cell> p = board.getMusketeerCells();
    	Assert.assertEquals(3, p.size());   	
    }
    
    
    
}
