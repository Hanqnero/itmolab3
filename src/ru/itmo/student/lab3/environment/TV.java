package ru.itmo.student.lab3.environment;

import ru.itmo.student.lab3.people.Person;

public class TV {
    protected boolean isOn;
    private TVProgramme currentProgramme;

    public void setState(boolean s) { isOn = s; }
    public boolean getState() { return this.isOn; }

    public void watch(Person[] people) {
        if (this.isOn) for (var p: people) p.setMedicalCondition(p.getMedicalCondition() + currentProgramme.getBrainDamage());
    }
}
