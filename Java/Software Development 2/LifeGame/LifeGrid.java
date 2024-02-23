/*
 * CS1021 - 0-0
 * Winter 2018-2019
 * Lab Game Of Life
 * Name: Derek Riley (edits by Chris Taylor)
 * Created 11/25/2016
 * Modified: 1/11/2023 (Cody Mott - mottc@msoe.edu)
 */

package mottc;

import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the grid of cells used to model Conway's Game of Life.
 */
public class LifeGrid {

    /**
     * This instance variable stores the grid of Cells
     */
    private List<List<Cell>> cells;

    /**
     * This instance variable stores the width of the cell grid
     */
    private final int numberOfCellsWide;

    /**
     * This instance variable stores the height of the cell grid
     */
    private final int numberOfCellsHigh;

    /**
     * This constructor builds a LifeGrid using the size of the Pane passed and the scale of the cells
     * @param gamePane viewing pane
     */
    public LifeGrid(Pane gamePane) {
        this(gamePane, (int) gamePane.getPrefWidth(), (int) gamePane.getPrefHeight());
    }

    /**
     * This constructor builds a LifeGrid using the size of the Pane passed and the scale of the cells
     * @param gamePane viewing pane
     * @param width the preferred width of the viewing pane
     * @param height the preferred width of the viewing pane
     */
    public LifeGrid(Pane gamePane, int width, int height) {
        this.numberOfCellsWide = width/Cell.SCALE;
        this.numberOfCellsHigh = height/Cell.SCALE;
        cells = new ArrayList<>();

        //initialize the two-dimensional ArrayList
        for(int i = 0; i < numberOfCellsHigh; i++) {
            cells.add(new ArrayList<>());
        }

        //create cells
        for(int i=0; i<numberOfCellsHigh; i++) {     // yPosition
            for(int j=0; j<numberOfCellsWide; j++) { // xPosition
                cells.get(i).add(new Cell(j, i));
            }
        }

        //set neighbors for all cells
        for(int i=0; i<numberOfCellsHigh; i++) {     // yPosition
            for(int j=0; j<numberOfCellsWide; j++) { // xPosition
                if(i>0) {
                    if(j>0) {
                        cells.get(i).get(j).setNeighborAboveLeft(cells.get(i-1).get(j-1));
                    }
                    cells.get(i).get(j).setNeighborAboveCenter(cells.get(i-1).get(j));
                    if(j<numberOfCellsWide-1) {
                        cells.get(i).get(j).setNeighborAboveRight(cells.get(i-1).get(j+1));
                    }
                }
                if(j>0) {
                    cells.get(i).get(j).setNeighborMiddleLeft(cells.get(i).get(j-1));
                }
                if(j<numberOfCellsWide-1) {
                    cells.get(i).get(j).setNeighborMiddleRight(cells.get(i).get(j+1));
                }
                if(i<numberOfCellsHigh-1) { // bottom boarder elements
                    if(j>0) {
                        cells.get(i).get(j).setNeighborBelowLeft(cells.get(i+1).get(j-1));
                    }
                    cells.get(i).get(j).setNeighborBelowCenter(cells.get(i+1).get(j));
                    if(j<numberOfCellsWide-1) {
                        cells.get(i).get(j).setNeighborBelowRight(cells.get(i+1).get(j+1));
                    }
                }
            }
        }
        initialize(gamePane);
    }

    /**
     * This method randomizes the life and death attributes of all cells in the cells.
     * Cells have a 50% chance of being alive or dead.
     */
    public void randomize() {
        for(List<Cell> row : cells) {
            for(Cell cell : row) {
                cell.setAlive(Math.random()<0.5);
                cell.updateColors();
            }
        }
    }

    /**
     * This method triggers one iteration (tick) of the game of life rules for the entire grid.
     */
    public void iterate() {
        for(List<Cell> row : cells) {
            for(Cell cell : row) {
                cell.determineNextTick();
            }
        }
        for(List<Cell> row : cells) {
            for(Cell cell : row) {
                cell.updateTick();
            }
        }
    }

    /**
     * This method adds all the cell rectangles to the Pane
     */
    private void initialize(Pane gamePane) {
        for(List<Cell> row : cells) {
            for(Cell cell : row) {
                gamePane.getChildren().add(cell);
            }
        }
    }

    /**
     * counts the number of alive cells
     * @return - number of alive cells
     */
    public int numAliveCells(){

        int numAliveCells=0;

        for(List<Cell> row : cells) {
            for(Cell cell : row) {
               if( cell.isAlive()){
                   numAliveCells++;
               }//end if
            }//end for
        }//end for

        for(List<Cell> row : cells) {
            for(Cell cell : row) {
                if(cell.isAlive()){
                    numAliveCells++;
                }//end if
            }//end for
        }//end for

        return numAliveCells;

    }//end numAliveCells;

    /**
     * counts the number of dead cells
     * @return - number of dead cells
     */
    public int numDeadCells(){

        int numDeadCells=0;

        for(List<Cell> row : cells) {
            for(Cell cell : row) {
                if(!cell.isAlive()){
                    numDeadCells++;
                }//end if
            }//end for
        }//end for

        for(List<Cell> row : cells) {
            for(Cell cell : row) {
                if(!cell.isAlive()){
                    numDeadCells++;
                }//end if
            }//end for
        }//end for

        return numDeadCells;

    }//end numAliveCells;

    /**
     * toggles cell at (row,col) from alive to dead and from dead to alive
     * @param row - row index of cell
     * @param col - column index of cell
     */
    public void toggleCell(int row, int col){

        boolean alive = true;
        boolean dead = false;
        Cell cell= cells.get(row).get(col);

        if (!cell.isAlive()) {
            cells.get(row).get(col).setAlive(alive);
        }//end if

        else{
            cells.get(row).get(col).setAlive(dead);
        }//end else

        cells.get(row).get(col).updateColors();

    }//end toggle cell

    /**
     * sets all cells to dead
     */
    public void reset(){

        boolean dead= false;

        for(List<Cell> row: cells){
            for(Cell cell: row){
                cell.setAlive(dead);

                cell.updateColors();
            }//end for
        }//end for
    }//end reset

}//end LifeGrid
