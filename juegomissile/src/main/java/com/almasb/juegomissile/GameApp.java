package com.almasb.juegomissile;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 *
 * @author Catalina
 */

// JavaFX - GameFX

public class GameApp extends Application {
    Group ventana = new Group();
    // ancho (widht) - alto (height)
    Canvas canvas = new Canvas(850, 550);
    
    double posX, posY;

    //stage.setResizable(false);
    final GraphicsContext gc = canvas.getGraphicsContext2D();
    
    // Carga Imagen de fondo
    Image city = new Image("cityfinal.jpg" );

    // Imagenes de los edificios
    Image buil01 = new Image( "build01.png" );
    Image buil02 = new Image( "build02.png" );
    Image buil03 = new Image( "build03.png" );
    Image transparente = new Image( "transparente.png" );

    // Arreglo de edificios con imagenes
    ArrayList<Image> buildingsI = new ArrayList<Image>();
    // Arreglo de 0 y 1 donde 1 hay edificio, 0 no hay edificio
    ArrayList<Integer> buildingsN = new ArrayList<Integer>();
    
           
    final Image asteroide = new Image( "asteroide.png" );
    final Image boom = new Image( "boom.png" );

    final long startNanoTime = System.nanoTime();
    
    public boolean verificar(){
        for(int i = 0; i < 9; i++){
            if(buildingsN.get(i) == 1)
                return true;
        }
            return false;
    }
    
    public void draw(Scene escenario){
        if(verificar()){
            gc.drawImage(city, 0, 0);
            for(int i = 0, cont = 0; i < 9; i++){
                if(buildingsN.get(i) == 1)
                gc.drawImage(buildingsI.get(i), cont, 390);
                cont = cont + 90;            
            }
            missiles(escenario);    
        }
        else{
            gc.drawImage(city, 0, 0);
        }
    }
            
    public void missiles(final Scene escenario){
        int i = 2;
        while(i > 0){
            new AnimationTimer(){   
                int bandera = 0;
                Random aleatorio = new Random();
                double x1 = new Integer(aleatorio.nextInt(850));
                double y1 = 0;
                double x2 = new Integer(aleatorio.nextInt(850));
                double y2 = 0;
                public void handle(long currentNanoTime)
                {
                    double t = (currentNanoTime - startNanoTime) / 1000000000000000000.0;  
                    
                    escenario.setOnMouseClicked( new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent e) {
                            posX = e.getX();
                            posY = e.getY();
                            if(posX <= 850 && posY <= 550)
                                if (e.getButton() == MouseButton.PRIMARY) {
                                    Circle linea = new Circle(posX, posY, 10, Color.BLACK);
                                    Timeline lineatiempo = new Timeline();
                                    lineatiempo.setAutoReverse(false);                                    
                                    lineatiempo.setCycleCount(3);
                                    KeyFrame f1 = new KeyFrame(Duration.ZERO, new KeyValue(linea.translateXProperty(), posX));
                                    KeyFrame f2 = new KeyFrame(new Duration(2000), new KeyValue(linea.translateYProperty(), 400));
                                    lineatiempo.getKeyFrames().addAll(f1, f2);
                                    //lineatiempo.play(); 
                                    ventana.getChildren().add(linea); 
                                    /*new AnimationTimer(){
                                        public void handle(long currentNanoTime){
                                            
                                        }
                                    }.start();*/
                                }
                        }
                    });
                    
                    x1 = x1 + 0.2;
                    y1 = y1 + 0.7;
                    x2 = x2 - 0.2;
                    y2 = y2 + 0.7;
                    
                    if(y1 <= 390 || y2 <= 390 && bandera == 0){
                        gc.drawImage(asteroide, x1, y1); 
                        gc.drawImage(asteroide, x2, y2); 
                    }
                    else{                                                     
                        if(bandera == 0){
                            gc.drawImage(boom, x1, y1); 
                            gc.drawImage(boom, x2, y2);
                            bandera = 1;
                            if((x1 >= 0 && x1 <= 90) || (x2 >= 0 && x2 <= 90))
                                buildingsN.add(0, 0);
                            if((x1 > 90 && x1 <= 180) || (x2 >= 90 && x2 <= 180))
                                buildingsN.add(1, 0);
                            if((x1 > 180 && x1 <= 270) || (x2 >= 180 && x2 <= 270))
                                buildingsN.add(2, 0);
                            if((x1 > 270 && x1 <= 360) || (x2 >= 270 && x2 <= 360))
                                buildingsN.add(3, 0);
                            if((x1 > 360 && x1 <= 450) || (x2 >= 360 && x2 <= 450))
                                buildingsN.add(4, 0);
                            if((x1 > 450 && x1 <= 540) || (x2 >= 450 && x2 <= 540))
                                buildingsN.add(5, 0);
                            if((x1 > 540 && x1 <= 630) || (x2 >= 540 && x2 <= 630))
                                buildingsN.add(6, 0);
                            if((x1 > 630 && x1 <= 720) || (x2 >= 630 && x2 <= 720))
                                buildingsN.add(7, 0);
                            if((x1 > 720 && x1 <= 810) || (x2 >= 720 && x2 <= 810))
                                buildingsN.add(8, 0);
                            draw(escenario);
                        }
                    }
                }
            }.start();
            i--;
        }
    }
    
    public void Game(Stage stage) throws InterruptedException{

        Scene escenario = new Scene(ventana);
        stage.setScene(escenario);
        
        ventana.getChildren().add(canvas);
                
        // En el arreglo de números, empezamos con todos los edificios 
        for(int i = 0; i < 9; i++){
            buildingsN.add(1);
        }

        //buildings.add(transparente);
        buildingsI.add(buil01);
        buildingsI.add(buil02);
        buildingsI.add(buil03);
        buildingsI.add(buil01);
        buildingsI.add(buil02);
        buildingsI.add(buil03);
        buildingsI.add(buil01);
        buildingsI.add(buil02);
        buildingsI.add(buil03);
        buildingsI.add(buil01);

        draw(escenario);
    }
    
    @Override
    public void start(Stage stage) throws InterruptedException {
        
        // Caracteristicas de la ventana
        stage.setTitle("Missile Command 2.0");
        stage.setWidth(850);
        stage.setHeight(550);
        Game(stage);

        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}