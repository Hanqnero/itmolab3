package ru.itmo.student.lab3.people;

import ru.itmo.student.lab3.environment.Container;
import ru.itmo.student.lab3.environment.Syringe;
import ru.itmo.student.lab3.environment.medicine.Injection;
import ru.itmo.student.lab3.environment.medicine.Medicine;

public interface CanMakeInjection {
    default void makeInjection(HasMedicalCondition p, Syringe m) {
        var isSuccessful = !m.isEmpty() && m.isNeedleReady();
        if (isSuccessful) {
            p.setMedicalCondition(p.getMedicalCondition()+m.getContent().getHpChange());
            m.empty();
        }
    }
}
