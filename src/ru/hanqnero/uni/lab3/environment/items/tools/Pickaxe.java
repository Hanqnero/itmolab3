package ru.hanqnero.uni.lab3.environment.items.tools;

import org.jetbrains.annotations.NotNull;
import ru.hanqnero.uni.lab3.environment.abstractions.Location;
import ru.hanqnero.uni.lab3.environment.items.interfaces.CanBeHeld;
import ru.hanqnero.uni.lab3.environment.items.interfaces.Tool;
import ru.hanqnero.uni.lab3.people.interfaces.CanHoldItems;
import ru.hanqnero.uni.lab3.people.interfaces.HasExhaustion;

public class Pickaxe implements CanBeHeld, Tool {
    private CanHoldItems holder;

    @Override
    public Location.Ground.Tools getType() {return Location.Ground.Tools.PICKAXE;}

    @Override
    public void whenHeld(@NotNull CanHoldItems holder) {
       this.holder = holder;
       holder.getItemHeld().whenDropped();

    }
    public void whenDropped() {
        holder = null;
    }

    @Override
    public CanHoldItems getHolder() {
        return holder;
    }

    private final Handle handle = new Handle();

    public void onMeetingHardStone() {
        handle.vibrate();
    }

    @Override
    public String toString() {
        return getType().toString();
    }

    class Handle {
        public void vibrate() {
            if (holder instanceof HasExhaustion p) {
                p.setExhaustion(p.getExhaustion() + 1); // Backfiring vibration exhausts holder
            }
        }
    }
}
