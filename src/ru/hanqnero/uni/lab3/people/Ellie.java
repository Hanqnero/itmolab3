package ru.hanqnero.uni.lab3.people;

import ru.hanqnero.uni.lab3.environment.CanBeHeld;
import ru.hanqnero.uni.lab3.environment.riding.CanBeRidden;
import ru.hanqnero.uni.lab3.environment.Clothes;
import ru.hanqnero.uni.lab3.environment.Container;
import ru.hanqnero.uni.lab3.environment.food.Food;
import ru.hanqnero.uni.lab3.people.interfaces.*;
import ru.hanqnero.uni.lab3.people.properties.FaceExpression;

import java.util.LinkedList;
import java.util.List;

public class Ellie extends Person implements
        CanWearClothes, CanConsumeFood, CanRide, HasFaceExpression,HasMedicalCondition, HasDislikedItems {
    private List<Clothes> currentClothes = new LinkedList<>();
    private int saturation;
    private CanBeHeld itemHeld;
    private int medicalCondition = 200;
    private CanBeRidden currentlyRides;
    private FaceExpression faceExpression;
    private final List<Object> dislikedItems = new LinkedList<>();

    public Ellie() { super(); }

    @Override
    public String getName() {return "Ellie";}

    @Override
    public List<Clothes> getCurrentClothes() {
        return new LinkedList<>(currentClothes);
    }

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

    @Override
    public void consumeFood(Food f) {
        if (!f.isConsumed()) {
            var foodSaturation = f.getSaturationChange();
            var personSaturation = this.getSaturation();

            this.setSaturation(foodSaturation + personSaturation);
            f.consume();
        }
    }

    @Override
    public void consumeFood(Container<Food> c) {
        if (!c.isEmpty()) {
            Food food = c.getContent();
            this.consumeFood(food);
            c.empty();
        }
    }

    @Override
    public int getSaturation() {
        return this.saturation;
    }

    @Override
    public void setSaturation(int s) {
        this.saturation = s;
    }

    public void hold(CanBeHeld item) {
       this.itemHeld = item;
       item.whenHeld(this);
    }

    @SuppressWarnings("unused")
    public CanBeHeld getItemHeld() {
        return this.itemHeld;
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
    public boolean isRiding() {
        return currentlyRides == null;
    }

    @Override
    public void stopRiding() {
        if (isRiding()) {
            currentlyRides.whenStoppedRiding(this);
        }
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
    public int getMedicalCondition() {
        return medicalCondition;
    }

    @Override
    public void setMedicalCondition(int m) {
        medicalCondition = m;
    }

    @Override
    public List<Object> getDislikedItems() {
        return dislikedItems;
    }

    @Override
    public void dislikeNewItem(Object o) {
        if (!dislikedItems.contains(o)) {
            dislikedItems.add(o);
        }
    }

    @Override
    public void stopDisliking(Object o) {
        dislikedItems.remove(o);
    }

    public enum SleepingState {Awake, Sleeping}
    private SleepingState sleepState = SleepingState.Awake;
    public void sleep() {
       sleepState = SleepingState.Sleeping;
    }
    @SuppressWarnings("unused")
    public void wakeUp() {
        sleepState = SleepingState.Awake;
    }

    @SuppressWarnings("unused")
    public SleepingState getSleepState() {
        return sleepState;
    }
}
