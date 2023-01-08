package gui;

import algorithms.Algorithm;
import javafx.animation.SequentialTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class AlgoPane {

    protected Algorithm algorithm;

    protected SequentialTransition algoAnimation;

    protected final BooleanProperty isRunningProperty = new SimpleBooleanProperty(false);

    protected final DoubleProperty progressProperty = new SimpleDoubleProperty(0);

    public BooleanProperty isRunningProperty() {
        return isRunningProperty;
    }

    public DoubleProperty progressProperty() {
        return progressProperty;
    }

    public abstract void start();

    public abstract void stop();

    public void pause() {
        algoAnimation.pause();
    }

    public void resume() {
        algoAnimation.play();
    }

}
