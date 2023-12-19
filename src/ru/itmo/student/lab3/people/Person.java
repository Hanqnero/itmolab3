package ru.itmo.student.lab3.people;

import ru.itmo.student.lab3.environment.*;
import java.util.LinkedList;

public class Person {
    protected final String name;
    protected final Color favoriteClothesColor;
    protected final Food favoriteFood;
    protected int medicalCondition;
    protected LookType appearance;
    private LinkedList<Clothes> currentClothes;
    private Location currentLocation;
    private ActionMood currentMood;

    public Person(String name_,
                  Color favoriteClothesColor_,
                  Food favoriteFood_)
    {
        name = name_;
        favoriteClothesColor = favoriteClothesColor_;
        favoriteFood = favoriteFood_;
    }

    public void setAppearance(LookType a) { this.appearance = a; }
    public LookType getAppearance() { return this.appearance; }

    public Food getFavoriteFood() { return this.favoriteFood; }

    public Color getFavoriteClothesColor() { return this.favoriteClothesColor; }

    public void setMedicalCondition(int medicalCondition) {
        this.medicalCondition = medicalCondition;
    }
    public int getMedicalCondition() { return this.medicalCondition; }

    public void goOutOfLocation() { this.currentLocation.removeCharacter(this); }

    public void setCurrentMood(ActionMood currentMood) { this.currentMood = currentMood; }
    public ActionMood getCurrentMood() { return currentMood; }
}
