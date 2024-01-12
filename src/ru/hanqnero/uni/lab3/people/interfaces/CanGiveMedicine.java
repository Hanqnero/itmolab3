package ru.hanqnero.uni.lab3.people.interfaces;

import ru.hanqnero.uni.lab3.environment.medicine.TakenInternallyMedicine;

public interface CanGiveMedicine {
    void giveMedicine(HasMedicalCondition p, TakenInternallyMedicine m);
}
