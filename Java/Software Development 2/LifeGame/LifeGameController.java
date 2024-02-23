/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class LifeGameController
 * Name: mottc
 * Created 1/11/2023
 */
package mottc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * LifeGameController purpose: controller for the life game
 *
 * @author mottc
 * @version created on 1/11/2023 at 8:48 AM
 */
public class LifeGameController extends Application {

    private static int numAliveCells;
    private static int numDeadCells;

    /**
     * Launches the GUI
     * @param args
     */
    public static void main(String[] args) {

        launch(args);
    }//end main

    /**
     * defines all the parameters for the GUI. calls LifeGrid to instantiate
     * a new LifeGrid object which can be interacted with on the gamePane,
     * sets a randomize button which randomizes which cells are dead or alive,
     * a next iteration button which cycles to the next iteration of cell life,
     * reset button that resets all the cells back to dead, and Labels that
     * show how many cells are alive and how many are dead. When a cell is
     * clicked on by the user, it toggles between alive and dead.
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        Pane gamePane = new Pane();
        HBox buttonBox = new HBox();
        HBox labelBox = new HBox();
        HBox outerBox = new HBox(buttonBox,labelBox);
        VBox outerPane = new VBox(gamePane,outerBox);
        LifeGrid lifeGrid = new LifeGrid(gamePane,500,500);
        Button randomButton = new Button("Randomize");
        Button iterationBtn = new Button("Next Iteration");
        Button resetBtn = new Button("Reset");

        updateCellCount(lifeGrid);

        Label aliveCells = new Label("Alive Cells: " + numAliveCells);
        Label deadCells = new Label("Dead Cells: "+ numDeadCells);

        aliveCells.setPrefSize(100,10);
        deadCells.setPrefSize(100,10);

        buttonBox.getChildren().addAll(randomButton,iterationBtn,resetBtn);

        labelBox.getChildren().addAll(aliveCells,deadCells);

        outerBox.setSpacing(75);


        //randomButton eventHandler
        randomButton.setOnAction(e ->{

            lifeGrid.randomize();

            updateCellCount(lifeGrid);

            aliveCells.setText("Alive Cells: " + numAliveCells);
            deadCells.setText("Dead Cells: "+ numDeadCells);
        });

        //iterationBtn eventHandler
        iterationBtn.setOnAction(e->{

            lifeGrid.iterate();

            updateCellCount(lifeGrid);

            aliveCells.setText("Alive Cells: " + numAliveCells);
            deadCells.setText("Dead Cells: "+ numDeadCells);
        });

        //resetBtn eventHandler
        resetBtn.setOnAction(e->{
            lifeGrid.reset();

            updateCellCount(lifeGrid);

            aliveCells.setText("Alive Cells: " + numAliveCells);
            deadCells.setText("Dead Cells: "+ numDeadCells);
        });

        //gamePane eventHandler
        gamePane.setOnMouseClicked(e->{

            int col = (int)e.getX()/10;
            int row = (int)e.getY()/10;

            //System.out.println("("+row+","+col+")");

            lifeGrid.toggleCell(row,col);

        });


        Scene scene = new Scene(outerPane);

        stage.setScene(scene);
        stage.show();


    }//end start

    /**
     * setter for numAliveCells and numDeadCells
     * @param lifeGrid - lifeGrid object
     */
    private static void updateCellCount(LifeGrid lifeGrid){

        numAliveCells = lifeGrid.numAliveCells();
        numDeadCells = lifeGrid.numDeadCells();

    }//end updateCellCount

}//end LifeGameController