package ru.hanqnero.uni.lab3.environment.items.containers;

import ru.hanqnero.uni.lab3.environment.items.interfaces.Furniture;
import ru.hanqnero.uni.lab3.people.Person;

public class Coffin extends Container<Person> implements Furniture {
    protected boolean state = false;

    @Override
    public void fill(Person o) {
        if (state) {
            content = o;
            o.goOutOfLocation();
        }
    }

    @Override
    public void empty() {
       setEmpty(true);
       content = null;
    }

    @Override
    public Person getContent() {
        return content;
    }

    public boolean isOpen() { return state;}
    public void setState(boolean state) { this.state = state;}

}
