package ru.hanqnero.uni.lab3.people.interfaces;

@SuppressWarnings("unused")
public interface HasAppearance {
    public enum LookType { Beautiful, Default }
    LookType getAppearance();
    void setAppearance();
}
