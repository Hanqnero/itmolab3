package ru.itmo.student.lab3.people;

import ru.itmo.student.lab3.environment.medicine.Medicine;
import ru.itmo.student.lab3.environment.medicine.TakenInternallyMedicine;

public interface CanGiveMedicine {
    void giveMedicine(HasMedicalCondition p, TakenInternallyMedicine m);
}
