package ru.hanqnero.uni.lab3.people;

import ru.hanqnero.uni.lab3.environment.CanBeRidden;
import ru.hanqnero.uni.lab3.environment.Clothes;
import ru.hanqnero.uni.lab3.environment.Temperature;
import ru.hanqnero.uni.lab3.environment.food.Food;
import ru.hanqnero.uni.lab3.environment.food.HotChocolate;
import ru.hanqnero.uni.lab3.environment.food.Taste;

import java.util.LinkedList;

public class Gage extends Person implements
        HasFavoriteFood,CanWearClothes,CanRide,HasFaceExpression,CanBeDead {
    private final Food favoriteFood;
    private LinkedList<Clothes> currentClothes = new LinkedList<>();
    private CanBeRidden currentlyRides;
    private FaceExpression faceExpression;
    private LifeStatus lifeStatus = LifeStatus.Alive;

    public Gage(Food favoriteFood) {
        super("Gage");
        this.favoriteFood = favoriteFood;
    }

    public Gage() {
        super("Gage");
        this.favoriteFood = new HotChocolate(100, Taste.Default, Temperature.Hot);
    }

    @Override
    public Food getFavoriteFood() {
        return this.favoriteFood;
    }

    @Override
    public LinkedList<Clothes> getCurrentClothes() {
        return (LinkedList<Clothes>) this.currentClothes.clone();
    }

    @Override
    public void setCurrentClothes(LinkedList<Clothes> c) {
        this.currentClothes = c;
    }

    @Override
    public void addClothingItem(Clothes item) {
        if (!this.currentClothes.contains(item)) {
            this.currentClothes.add(item);
        }
    }

    @Override
    public void removeClothingItem(Clothes item) {
        this.currentClothes.remove(item);
    }

    @Override
    public void ride(CanBeRidden obj) {
        if (currentlyRides != null) {
            currentlyRides.whenStoppedRiding(this);
        }
        currentlyRides = obj;
        obj.whenRidden(this);
    }

    @Override
    public void stopRiding() {
       if (currentlyRides != null) {
           currentlyRides.whenStoppedRiding(this);
           currentlyRides = null;
       }
    }

    @Override
    public boolean isRiding() {
        return currentlyRides != null;
    }

    @Override
    public CanBeRidden getCurrentlyRides() {
        return currentlyRides;
    }

    @Override
    public FaceExpression getFaceExpression() {
        return faceExpression;
    }

    @Override
    public void setFaceExpression(FaceExpression expression) {
       faceExpression = expression;
    }

    @Override
    public void die() {
       lifeStatus = LifeStatus.Dead;
    }

    @Override
    public void resurrect() {
        lifeStatus = LifeStatus.Alive;
    }

    @Override
    public LifeStatus getLifeStatus() {
        return lifeStatus;
    }

    public void driveRisky(float safeDrivingThreshold, float actualDrivingSpeed) {
        if (actualDrivingSpeed > safeDrivingThreshold) die();
    }
}

