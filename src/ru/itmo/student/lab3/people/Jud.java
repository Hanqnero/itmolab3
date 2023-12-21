package ru.itmo.student.lab3.people;

import ru.itmo.student.lab3.environment.abstractions.Location;
import ru.itmo.student.lab3.environment.abstractions.Preparations;
import ru.itmo.student.lab3.environment.abstractions.Scene;

public class Jud extends Person implements AskToChangePosition{
    public Jud() {
        super("Jud");
    }

    public void takePartIn(Preparations p, float amount) {
        p.workOnCompletion(amount);

    }

    @Override
    public void whenAskedToChangePosition(Location.Position pos) {
       getCurrentScene().getLocation().changePosition(this, pos);
    }
}
