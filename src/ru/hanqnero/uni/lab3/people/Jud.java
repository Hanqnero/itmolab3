package ru.hanqnero.uni.lab3.people;

import ru.hanqnero.uni.lab3.environment.abstractions.Location;
import ru.hanqnero.uni.lab3.people.interfaces.AskToChangePosition;

import java.util.Arrays;

public class Jud extends Person implements AskToChangePosition {
    public Jud() {
        super();
    }
    @Override
    public String getName() {return "Jud";}

    public void takePartIn(Location p, float amount) {
        boolean hasMethod = Arrays.stream(p.getClass()
                .getDeclaredMethods())
                .filter(
                        (m) -> m.getName().equals("workOnCompletion")
                )
                .count() == 1;
        if (!hasMethod) return;
        interface HasCompletion {
            void workOnCompletion(float amount);
        }
        ((HasCompletion)p).workOnCompletion(amount);
    }

    @Override
    public void whenAskedToChangePosition(Location.Position pos) {
       getCurrentScene().getLocation().changePosition(this, pos);
    }
}
