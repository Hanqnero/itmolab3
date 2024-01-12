package ru.hanqnero.uni.lab3.people;

import ru.hanqnero.uni.lab3.environment.abstractions.Location;
import ru.hanqnero.uni.lab3.environment.abstractions.Preparations;
import ru.hanqnero.uni.lab3.people.interfaces.AskToChangePosition;

public class Jud extends Person implements AskToChangePosition {
    public Jud() {
        super();
    }
    @Override
    public String getName() {return "Jud";}

    public void takePartIn(Preparations p, float amount) {
        p.workOnCompletion(amount);

    }

    @Override
    public void whenAskedToChangePosition(Location.Position pos) {
       getCurrentScene().getLocation().changePosition(this, pos);
    }
}
