package ru.itmo.student.lab3.environment;

public class TVProgramme {
    protected final String name;

    private final int brainDamage;

    public TVProgramme(String n, int bd) { this.name = n; this.brainDamage = bd; }

    public int getBrainDamage() { return brainDamage; }
}
