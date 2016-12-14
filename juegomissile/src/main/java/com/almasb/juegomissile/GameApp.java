/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almasb.juegomissile;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.GameEntity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.settings.GameSettings;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Catalina
 */
public class GameApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Missile Command 2.0");
        stage.setWidth(850);
        stage.setHeight(550);
        
        Group root = new Group();
        Scene theScene = new Scene( root );
        stage.setScene( theScene );
        
        // ancho (widht) - alto (height)
        Canvas canvas = new Canvas( 850, 550 );
        root.getChildren().add( canvas );
         
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        Image city = new Image( "cityfinal.jpg" );
        
        ArrayList<Image> buildings = new ArrayList<Image>();
        
        Image buil01 = new Image( "build01.png" );
        Image buil02 = new Image( "build02.png" );
        Image buil03 = new Image( "build03.png" );
        
        buildings.add(buil01);
        buildings.add(buil02);
        buildings.add(buil03);
        buildings.add(buil01);
        buildings.add(buil02);
        buildings.add(buil03);
        buildings.add(buil01);
        buildings.add(buil02);
        buildings.add(buil03);
        buildings.add(buil01);
            
        gc.drawImage(city, 0, 0);
        
        for(int i = 0, cont = 0; i < 9; i++){
            gc.drawImage(buildings.get(i), cont, 390);
            cont = cont + 90;
        }
        
        /*
        gc.drawImage(buil01, 0, 390);
        gc.drawImage(buil02, 90, 390);
        gc.drawImage(buil03, 180, 390);        
        gc.drawImage(buil01, 270, 390);
        gc.drawImage(buil02, 360, 390);
        gc.drawImage(buil03, 450, 390);
        */
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
