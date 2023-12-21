package ru.itmo.student.lab3.environment;

import ru.itmo.student.lab3.environment.medicine.Injection;
import ru.itmo.student.lab3.environment.medicine.Medicine;

public class Syringe extends Container<Injection>{
    private boolean needleReady = false;

    public void getNeedleReady() {
        this.needleReady = true;
    }
    public boolean isNeedleReady() {
        return this.needleReady;
    }
    @Override
    public void fill(Injection m) {
        setEmpty(false);
        content = m;
    }

    @Override
    public void empty() {
        setEmpty(true);
        content = null;
    }

    @Override
    public Injection getContent() {
        return content;
    }
}
