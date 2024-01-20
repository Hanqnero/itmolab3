package ru.hanqnero.uni.lab3.environment.items;

import ru.hanqnero.uni.lab3.environment.items.interfaces.Furniture;
import ru.hanqnero.uni.lab3.people.interfaces.CanWatchTV;

public class TV implements Furniture {
    public record TVProgramme(String name, int brainDamage) {}
    protected boolean isOn;
    private TVProgramme currentProgramme;

    public boolean getState() { return this.isOn; }

    @SuppressWarnings("unused")
    public void setState(boolean s) { isOn = s; }

    public void whenWatched(CanWatchTV person) {
        if (!getState() || currentProgramme == null) return;

        person.setMedicalCondition(person.getMedicalCondition() - currentProgramme.brainDamage());
    }

    public void setCurrentProgramme(TVProgramme currentProgramme) {
        this.currentProgramme = currentProgramme;
    }

}
