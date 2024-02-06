package org.example.game_of_life;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOfLife extends Application {
    GridPane grid=new GridPane();
    GridPane rulesgrid=new GridPane();
    GameController gameController =new GameController();

    @Override
    public void start(Stage stage) throws IOException {
        GridPane FinPane=new GridPane();
        for(int i = 0; i< gameController.getBoard().length; i++){
            for(int j = 0; j< gameController.getBoard()[i].length; j++){
                Rectangle rec=new Rectangle(20,20);
                if(gameController.getBoard()[i][j]==0) {
                    rec.setFill(Color.CYAN);
                }
                else {
                    rec.setFill(Color.RED);
                }
                rec.setStroke(Color.BLACK);

                EventHandler<MouseEvent> handler= e->{
                    Rectangle r=(Rectangle) e.getSource();
                    r.setFill(r.getFill()==Color.CYAN ? Color.RED : Color.CYAN);
                    gameController.getBoard()[GridPane.getRowIndex(r)][GridPane.getColumnIndex(r)]=(gameController.getBoard()[GridPane.getRowIndex(r)][GridPane.getColumnIndex(r)]+1)%2;
                };
                rec.addEventHandler(MouseEvent.MOUSE_PRESSED,handler);
                grid.add(rec,j,i);
//                System.out.println(i*20+" "+j*20);
            }
        }
        for(int i = 0; i< gameController.getRules().length; i++) {
            for (int j = 0; j < gameController.getRules()[i].length; j++) {
                Rectangle rec=new Rectangle(20,20);
                if(gameController.getRules()[i][j]==0) {
                    rec.setFill(Color.CYAN);
                }
                else {
                    rec.setFill(Color.RED);
                }
                rec.setStroke(Color.BLACK);

                EventHandler<MouseEvent> handler= e->{
                    Rectangle r=(Rectangle) e.getSource();
                    r.setFill(r.getFill()==Color.CYAN ? Color.RED : Color.CYAN);
                    gameController.getRules()[GridPane.getRowIndex(r)][GridPane.getColumnIndex(r)]=(gameController.getRules()[GridPane.getRowIndex(r)][GridPane.getColumnIndex(r)]+1)%2;
                };
                rec.addEventHandler(MouseEvent.MOUSE_PRESSED,handler);
                rulesgrid.add(rec,j,i);
            }
        }
        Button button=new Button("Next Step");
        button.setOnAction(actionEvent -> draw());
        FinPane.add(grid,0,0);
        FinPane.add(button,0,1);
        FinPane.add(new Label("Rules:"),0,2);
        GridPane down=new GridPane();
        down.setHgap(10);
        GridPane numbers=new GridPane();
        numbers.add(new Label("0"),0,0);
        numbers.add(new Label("1"),0,1);
        GridPane numbers1=new GridPane();
        numbers1.setHgap(15);
        for (int i=0;i<9;i++){
            numbers1.add(new Label(String.valueOf(i)),i,0);
        }
        down.add(numbers,0,1);
        down.add(numbers1,1,0);
        down.add(rulesgrid,1,1);
        FinPane.add(down,0,3);

        stage.setTitle("Game of Life");
        stage.setScene(new Scene(FinPane,800,800));
        stage.show();
    }
    public void draw(){
        gameController.step();
        for (Node child : grid.getChildren()){
            Rectangle rec= (Rectangle) child;
            if(gameController.getBoard()[GridPane.getRowIndex(rec)][GridPane.getColumnIndex(rec)]==0){
                rec.setFill(Color.CYAN);
            }
            else {
                rec.setFill(Color.RED);
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}