package ru.itmo.student.lab3.environment;

import ru.itmo.student.lab3.people.Person;

public class Coffin extends Container<Person> implements Furniture {
    public enum State {Open, Closed};
    protected State state = State.Closed;

    @Override
    public void fill(Person o) {
        if (this.state == State.Open) {
            this.content = o;
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

    public State getState() { return state; }
    public void setState(State state) { this.state = state; }

}
