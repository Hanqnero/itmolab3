package ru.hanqnero.uni.lab3.people;

import org.jetbrains.annotations.NotNull;
import ru.hanqnero.uni.lab3.environment.CanBeHeld;
import ru.hanqnero.uni.lab3.environment.Pickaxe;
import ru.hanqnero.uni.lab3.environment.Tool;
import ru.hanqnero.uni.lab3.environment.abstractions.Location;
import ru.hanqnero.uni.lab3.environment.abstractions.exceptions.NoToolException;
import ru.hanqnero.uni.lab3.environment.abstractions.exceptions.WrongToolException;
import ru.hanqnero.uni.lab3.people.interfaces.AskToChangePosition;
import ru.hanqnero.uni.lab3.people.interfaces.CanDigSoil;
import ru.hanqnero.uni.lab3.people.interfaces.CanHoldItems;

import java.util.Arrays;

public class Jud extends Person implements AskToChangePosition, CanDigSoil, CanHoldItems {
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

    private CanBeHeld itemHeld;
    @Override
    public void pickUp(@NotNull CanBeHeld item) {
        drop();
        itemHeld = item;
        item.whenHeld(this);
    }

    @Override
    public CanBeHeld getItemHeld() {
        return itemHeld;
    }

    @Override
    public void drop() {
        if (itemHeld == null) return;
        itemHeld.whenDropped();
    }

    @Override
    public void dig(@NotNull Location.Ground ground) throws WrongToolException, NoToolException {
            if (!(itemHeld instanceof Tool))
                throw new NoToolException();
            var tool = (Tool) getItemHeld();

            var currentRock = ground.whenDug(tool.getType(), this);
            if (currentRock.equals(Location.Ground.RockType.TOO_HARD)) {
                if (itemHeld instanceof Pickaxe pick) {
                    pick.onMeetingHardStone();
                }
            }
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

    @Override
    public void sweat() {
        exhaustion--;
    }
}
