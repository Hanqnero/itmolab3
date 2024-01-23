package ru.hanqnero.uni.lab3.people;

import ru.hanqnero.uni.lab3.environment.riding.CanBeRidden;
import ru.hanqnero.uni.lab3.environment.items.Clothes;
import ru.hanqnero.uni.lab3.environment.properties.Temperature;
import ru.hanqnero.uni.lab3.environment.food.Food;
import ru.hanqnero.uni.lab3.environment.food.HotChocolate;
import ru.hanqnero.uni.lab3.environment.food.Taste;
import ru.hanqnero.uni.lab3.people.interfaces.*;
import ru.hanqnero.uni.lab3.people.properties.FaceExpression;
import ru.hanqnero.uni.lab3.people.properties.LifeStatus;

import java.util.LinkedList;
import java.util.List;

public class Gage extends Person implements
        HasFavoriteFood, CanWearClothes, CanRide, HasFaceExpression, CanBeDead {
    private final Food favoriteFood;
    private List<Clothes> currentClothes = new LinkedList<>();

    public Gage(Food favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    public Gage() {
        super();
        this.favoriteFood = new HotChocolate(100, Taste.Default, Temperature.Hot);
    }

    @Override
    public String getName() {return "Gage";}

    @Override
    public Food getFavoriteFood() {
        return this.favoriteFood;
    }

    @Override
    public List<Clothes> getCurrentClothes() {
        return new LinkedList<>(currentClothes);
    }

    @Override
    public void setCurrentClothes(List<Clothes> c) {
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

    private CanBeRidden currentlyRides;
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
        return false;
    }

    private FaceExpression faceExpression;
    @Override
    public void setFaceExpression(FaceExpression expression) {
       faceExpression = expression;
    }

    @Override
    public FaceExpression getFaceExpression() {
        return faceExpression;
    }

    private LifeStatus lifeStatus = LifeStatus.Alive;
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

