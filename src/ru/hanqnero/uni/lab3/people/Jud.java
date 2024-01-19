package ru.hanqnero.uni.lab3.people;

import org.jetbrains.annotations.NotNull;
import ru.hanqnero.uni.lab3.environment.CanBeHeld;
import ru.hanqnero.uni.lab3.environment.Pickaxe;
import ru.hanqnero.uni.lab3.environment.Tool;
import ru.hanqnero.uni.lab3.environment.abstractions.Location;
import ru.hanqnero.uni.lab3.environment.abstractions.Location.Ground.RockType;
import ru.hanqnero.uni.lab3.environment.abstractions.Preparations;
import ru.hanqnero.uni.lab3.environment.abstractions.exceptions.NoToolException;
import ru.hanqnero.uni.lab3.environment.abstractions.exceptions.WrongToolException;
import ru.hanqnero.uni.lab3.people.interfaces.AskToChangePosition;
import ru.hanqnero.uni.lab3.people.interfaces.CanDigSoil;
import ru.hanqnero.uni.lab3.people.interfaces.CanHoldItems;

public class Jud extends Person implements AskToChangePosition, CanDigSoil, CanHoldItems {
    public Jud() {
        super();
    }
    @Override
    public String getName() {return "Jud";}

    public void takePartIn(Location p, float amount) {
        if (!(p instanceof Preparations prep)) throw new RuntimeException("Can take part in preparing only for preparations.");
        prep.workOnCompletion(amount);
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
    public boolean dig(@NotNull Location.Ground ground) throws WrongToolException, NoToolException {
            if (!(itemHeld instanceof Tool tool))
                throw new NoToolException();

            RockType dugRockType = ground.whenDug(tool, this);
            if (dugRockType.equals(RockType.TOO_HARD))
                ((Pickaxe) tool).onMeetingHardStone();
            exhaustion += ground.getSoilToughness() / 10;
            return dugRockType.compareTo(RockType.NONE) > 0;
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
