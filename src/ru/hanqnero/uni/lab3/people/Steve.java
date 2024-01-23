package ru.hanqnero.uni.lab3.people;

import ru.hanqnero.uni.lab3.environment.abstractions.Location;
import ru.hanqnero.uni.lab3.environment.medicine.TakenInternallyMedicine;
import ru.hanqnero.uni.lab3.environment.abstractions.Question;
import ru.hanqnero.uni.lab3.people.interfaces.*;

public class Steve extends Person
        implements CanMakeInjection, CanGiveMedicine {
    @Override
    public String getName() {return "Steve";}

    @Override
    public void giveMedicine(HasMedicalCondition p, TakenInternallyMedicine m) {
        p.setMedicalCondition(m.getHpChange()+p.getMedicalCondition());
    }

    public void askQuestion(CanBeAskedQuestions p, Question q) {
        p.whenAskedQuestion(q);
    }

    public void askChangePosition(AskToChangePosition p, Location.Position pos) {
        p.whenAskedToChangePosition(pos);
    }
}
