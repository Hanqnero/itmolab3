package ru.hanqnero.uni.lab3.people;

import ru.hanqnero.uni.lab3.environment.Clothes;
import ru.hanqnero.uni.lab3.environment.Container;
import ru.hanqnero.uni.lab3.environment.food.cooking.Stove;
import ru.hanqnero.uni.lab3.environment.food.Food;
import ru.hanqnero.uni.lab3.environment.food.FoodToCook;
import ru.hanqnero.uni.lab3.environment.abstractions.Scene;
import ru.hanqnero.uni.lab3.people.interfaces.*;
import ru.hanqnero.uni.lab3.people.properties.HairLength;
import ru.hanqnero.uni.lab3.people.properties.HairStyle;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Float.max;
import static java.lang.Float.min;

@SuppressWarnings("FieldCanBeLocal")
public class Louis extends Person implements
        CanConsumeFood, CanHaveDesires, CanWearClothes, HasDislikedItems {
    private int saturation = 0;
    private Desire currentDesire;
    private List<Clothes> currentClothes = new LinkedList<>();
    private HairLength faceHairLength;
    private HairStyle hairStyle = HairStyle.Messy;
    @SuppressWarnings("unused")
    private Scene currentlyThinkingOf;

    public Louis() { super();}

    @Override
    public String getName() {return "Louis";}


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
    public int getSaturation() { return this.saturation; }
    @Override
    public void setSaturation(int s) { this.saturation = s; }


    @SuppressWarnings("unused")
    private float headAngle = 90f;
    public void nod(float angle) {
        float MIN_ANGLE = 0f;
        float MAX_ANGLE = 60f;
//        clamp the angle value
        angle = min(max(angle, MIN_ANGLE), MAX_ANGLE);
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

    public void shave() {
        this.faceHairLength = HairLength.Short;
    }


    @SuppressWarnings("unused")
    public HairLength getFaceHairLength() {
        return faceHairLength;
    }

    @SuppressWarnings("unused")
    public HairStyle getHairStyle() {
        return hairStyle;
    }

    public void styleHair(HairStyle hs) {
        this.hairStyle = hs;
    }

    private final List<Object> dislikedItems = new LinkedList<>();
    @Override
    public List<Object> getDislikedItems() {
        return new LinkedList<>(dislikedItems);
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
