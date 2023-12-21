package ru.itmo.student.lab3.environment;

import ru.itmo.student.lab3.people.CanWatchTV;
import ru.itmo.student.lab3.people.HasMedicalCondition;
import ru.itmo.student.lab3.people.Person;

import java.util.Collection;

public class TV implements Furniture {
    protected boolean isOn;
    private TVProgramme currentProgramme;

    public boolean getState() { return this.isOn; }

    public void setState(boolean s) { isOn = s; }

    public void whenWatched(CanWatchTV person) {
        if (!getState() || currentProgramme == null) return;

        person.setMedicalCondition(person.getMedicalCondition() - currentProgramme.brainDamage());
    }

    public void setCurrentProgramme(TVProgramme currentProgramme) {
        this.currentProgramme = currentProgramme;
    }

}
