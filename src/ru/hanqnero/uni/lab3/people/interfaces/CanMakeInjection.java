package ru.hanqnero.uni.lab3.people.interfaces;

import ru.hanqnero.uni.lab3.environment.items.containers.Syringe;

public interface CanMakeInjection {
    default void makeInjection(HasMedicalCondition p, Syringe m) {
        var isSuccessful = !m.isEmpty() && m.isNeedleReady();
        if (isSuccessful) {
            p.setMedicalCondition(p.getMedicalCondition() + m.getContent().getHpChange());
            m.empty();
        }
    }
}
