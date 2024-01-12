package ru.hanqnero.uni.lab3.environment;

import ru.hanqnero.uni.lab3.people.interfaces.CanWatchTV;

public class TV implements Furniture {
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
