package com.example.fxeventdemo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class BallPane extends Pane {

    public final double radius = 20;
    private double x = 375, y =325;
    private double dx = 1, dy = 1;

    private Timeline animation;
    private Circle circle = new Circle(x, y, radius);
    Button p1 = new Button();
    Button p2 = new Button();

    Label ctrlabel = new Label() ;
    Label scrlabel = new Label() ;
    int p1s =0;
    int p2s =0;
    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public Timeline getAnimation() {
        return animation;
    }
    public void setAnimation(Timeline animation) {
        this.animation = animation;
    }

    public BallPane() {

        scrlabel.setLayoutX(375);
        scrlabel.setLayoutY(20);
        scrlabel.setScaleX(5);
        setBackground(new Background(new BackgroundFill(Color.web("#" + "ABCDEF"), CornerRadii.EMPTY, Insets.EMPTY)));
        p1.setBackground(new Background(new BackgroundFill(Color.web("#" + "000000"), CornerRadii.EMPTY, Insets.EMPTY)));
        p2.setBackground(new Background(new BackgroundFill(Color.web("#" + "000000"), CornerRadii.EMPTY, Insets.EMPTY)));
       // getChildren().add(bg);
        circle.setFill(Color.GREEN); // Set ball color
        getChildren().add(circle); // Place a ball into this pane
        p1.setScaleX(10);
        p1.setDisable(true);
        p1.setRotate(90);
        p2.setScaleX(10);
        p2.setDisable(true);
        p2.setRotate(90);

        ctrlabel.setScaleX(5);
        ctrlabel.setLayoutX(x);
        ctrlabel.setLayoutY(y);
        getChildren().add(ctrlabel);
        getChildren().add(scrlabel);

        p1.setLayoutX(0);
        p1.setLayoutY(y-25);
        p2.setLayoutX(2*x-20);
        p2.setLayoutY(y-25);

        getChildren().add(p1);
        getChildren().add(p2);


        animation = new Timeline(new KeyFrame(Duration.millis(50), e -> {
            try {
                moveBall();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.setRate(15);
        animation.play(); // Start animation
    }

    public void play() {
        animation.play();
    }

    public void pause() {
        animation.pause();
    }

    public void increaseSpeed() {
        animation.setRate(animation.getRate() + 0.1);
    }

    public void decreaseSpeed() {
        animation.setRate(
                animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
    }

    public DoubleProperty rateProperty() {
        return animation.rateProperty();
    }

    protected void moveBall() throws InterruptedException {
        //Controls of left and right bar
        setOnKeyPressed(e->{
            if(e.getCode()== KeyCode.W && p1.getLayoutY()>=99){
                p1.setLayoutY(p1.getLayoutY()-100);
            }
            if(e.getCode()== KeyCode.S && p1.getLayoutY()<=600){
                p1.setLayoutY(p1.getLayoutY()+100);
            }
            if(e.getCode()== KeyCode.UP && p2.getLayoutY()>=99){
                p2.setLayoutY(p2.getLayoutY()-100);
            }if(e.getCode()== KeyCode.DOWN && p2.getLayoutY()<=600){
                p2.setLayoutY(p2.getLayoutY()+100);
            }

        });
        //ball movement if they
        if(y<p1.getLayoutY()+110&&y>p1.getLayoutY()-110 && x==30  ){
            dx*=-1;
        }
        else if(y<p2.getLayoutY()+110&&y>p2.getLayoutY()-110 && x==720  ){
            dx*=-1;
        }

        if(x==20 || x ==730){
            if(x == 20) p2s+=1;
            if(x == 730) p1s+=1;
            pause();
            ctrlabel.setText("You lost  !");
            x=375;
            y=325;
            circle.setCenterY(y);
            circle.setCenterX(x);
            setOnKeyPressed(e->{
                if (e.getCode() == KeyCode.SPACE) {
                    play();
                    scrlabel.setText(p1s+"------"+p2s);
                    ctrlabel.setText("");
                }
            });

        }
        // Check boundaries
        if (x < radius || x > getWidth() - radius ) {
            dx *= -1; // Change ball move direction
        }
        if (y < radius || y > getHeight() - radius) {
            dy *= -1; // Change ball move direction
        }

        // Adjust ball position
        x += dx;
        y += dy;
        circle.setCenterX(x);
        circle.setCenterY(y);

     }
    }

