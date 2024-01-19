package ru.hanqnero.uni.lab3.environment;

import ru.hanqnero.uni.lab3.environment.abstractions.Location;
import ru.hanqnero.uni.lab3.people.interfaces.CanHoldItems;

public class Shovel implements CanBeHeld, Tool{
    private CanHoldItems holder;

    @Override
    public void whenHeld(CanHoldItems holder) {
        this.holder = holder;
        holder.getItemHeld().whenDropped();
    }

    @Override
    public CanHoldItems getHolder() {
        return holder;
    }

    @Override
    public void whenDropped() {
        holder = null;
    }

    @Override
    public Location.Ground.Tools getType() {
        return Location.Ground.Tools.SHOVEL;
    }
}
