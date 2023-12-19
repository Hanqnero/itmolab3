package ru.itmo.student.lab3.people;

import ru.itmo.student.lab3.people.Person;

public interface CanMakeInjection {
    default boolean makeInjection(Person p, Medicine m) {
        if (m.getType() == Medicine.Type.Injection) {
            p.setMedicalCondition(p.getMedicalCondition() + m.hpChange);
        }
        return m.getType() == Medicine.Type.Injection;
    }
}
