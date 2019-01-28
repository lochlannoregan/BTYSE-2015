package com.xlorx.bigdatamonitor.model.Algorithm;

/**
 * Created by User on 25/09/2014.
 */
public class NewtonThreadPool {

    private double oldGuess;
    private double newGuess;
    private double findFunction;
    private double findPower;
    private double findFirstLevelDerivative;
    private double maxDifference = 5;
    private static final double a0 = 106.759754045327; //160.388387;
    private static final double a1 = -0.0909522039900628; //-0.147187;

    public NewtonThreadPool(Double oldGuess) {
        this.oldGuess = oldGuess;
        newGuess = oldGuess;
    }

    public boolean hasMoreGuesses() {
        return Math.abs(oldGuess - newGuess) < maxDifference;
    }

    public double findThreadOptimum() {
        oldGuess = newGuess;
        findPower = Math.pow(oldGuess, a1);
        findFunction = a0 * findPower;
        findFirstLevelDerivative = Math.pow(oldGuess, a1 - 1.0);
        findFirstLevelDerivative = a1 * a0 * findFirstLevelDerivative;
        newGuess = oldGuess - (findFunction / findFirstLevelDerivative);
        return newGuess;

    }
}
