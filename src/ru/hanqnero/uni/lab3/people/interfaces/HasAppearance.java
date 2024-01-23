package ru.hanqnero.uni.lab3.people.interfaces;

@SuppressWarnings("unused")
public interface HasAppearance {
    LookType getAppearance();

    void setAppearance();

    enum LookType {Beautiful, Default}
}
