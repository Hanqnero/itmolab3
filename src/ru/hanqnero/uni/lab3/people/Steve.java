package ru.hanqnero.uni.lab3.people;

import ru.hanqnero.uni.lab3.environment.abstractions.Location;
import ru.hanqnero.uni.lab3.environment.abstractions.Question;
import ru.hanqnero.uni.lab3.environment.medicine.TakenInternallyMedicine;

public class Steve extends Person
        implements CanMakeInjection,CanGiveMedicine{
    public Steve() {
        super("Steve");
    }

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