package ru.hanqnero.uni.lab3.people;

import ru.hanqnero.uni.lab3.environment.Clothes;
import ru.hanqnero.uni.lab3.environment.Container;
import ru.hanqnero.uni.lab3.environment.abstractions.Desire;
import ru.hanqnero.uni.lab3.environment.abstractions.Scene;
import ru.hanqnero.uni.lab3.environment.food.Food;
import ru.hanqnero.uni.lab3.environment.food.FoodToCook;
import ru.hanqnero.uni.lab3.environment.Stove;

import java.util.LinkedList;

import static java.lang.Float.max;
import static java.lang.Float.min;

public class Louis extends Person implements
        CanConsumeFood,CanHaveDesires,CanWearClothes,HasDislikedItems {
    private int saturation = 0;
    private Desire currentDesire;
    private LinkedList<Clothes> currentClothes = new LinkedList<>();
    private HairLength faceHairLength = HairLength.Default;
    private HairStyle hairStyle = HairStyle.Messy;
    private Scene currentlyThinkingOf;
    private float headAngle = 90f;
    private LinkedList<Object> dislikedItems = new LinkedList<>();

    public Louis() { super("Louis"); }

    public void cookFood(FoodToCook f, Stove s) {
        s.heat(f);
        s.heat(f);
    }

    @Override
    public void consumeFood(Food f) {
    if (!f.isConsumed()) {
        var foodSaturation = f.getSaturationChange();
        var personSaturation = this.getSaturation();

        if (this instanceof HasFavoriteFood) {
            boolean isFoodFavorite = ((HasFavoriteFood) this).getFavoriteFood().getClass() == f.getClass();
            if (isFoodFavorite) {
                    foodSaturation *= 2;
            }
        }

        this.setSaturation(foodSaturation + personSaturation);
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
    public int getSaturation() { return this.saturation; }

    @Override
    public void setSaturation(int s) { this.saturation = s; }

    public void nod(float angle) {
        float MINANGLE = 0f;
        float MAXANGLE = 60f;
//        clamp the angle value
        angle = min(max(angle, MINANGLE), MAXANGLE);
       this.headAngle -= angle;

//       The head is tilted Now

        this.headAngle += angle;
    }

    @Override
    public void desire(Desire desire) {
        this.currentDesire = desire;
    }

    public Desire getCurrentDesire() {
        return currentDesire;
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

    public void shave() {
        this.faceHairLength = HairLength.Short;
    }

    public HairLength getFaceHairLength() {
        return faceHairLength;
    }

    public HairStyle getHairStyle() {
        return hairStyle;
    }

    public void styleHair(HairStyle hs) {
        this.hairStyle = hs;
    }

    @Override
    public LinkedList<Object> getDislikedItems() {
        return (LinkedList<Object>)this.dislikedItems.clone();
    }

    @Override
    public void dislikeNewItem(Object o) {
        this.dislikedItems.add(o);
    }

    @Override
    public void stopDisliking(Object o) {
        this.dislikedItems.remove(o);
    }

    public void thinkOfAScene(Scene s) {
        currentlyThinkingOf = s;
        // Imagining a complex scene more time
        try {
            long sceneComplexity = s.getLocation().getCharacters().size() + s.getLocation().getObjects().size();
            Thread.sleep(sceneComplexity * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
