package ru.hanqnero.uni.lab3.people;

import org.jetbrains.annotations.NotNull;
import ru.hanqnero.uni.lab3.environment.abstractions.Location;
import ru.hanqnero.uni.lab3.environment.abstractions.exceptions.WrongToolException;
import ru.hanqnero.uni.lab3.people.interfaces.AskToChangePosition;
import ru.hanqnero.uni.lab3.people.interfaces.CanDigSoil;

import java.util.Arrays;

public class Jud extends Person implements AskToChangePosition, CanDigSoil {
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

    private int exhaustion = 0;
    @Override
    public void dig(Location.Ground.Tools defaultTool, @NotNull Location.Ground ground) throws WrongToolException {
            ground.whenDug(defaultTool, this);
            exhaustion += ground.getSoilToughness() / 10;
    }

    @Override
    public void setExhaustion(int exhaustion) {
        this.exhaustion = exhaustion;
    }

    @Override
    public int getExhaustion() {
        return exhaustion;
    }
}
