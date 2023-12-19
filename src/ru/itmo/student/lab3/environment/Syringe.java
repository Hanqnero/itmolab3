package ru.itmo.student.lab3.environment;

public class Syringe extends Container<Medicine>{
    private boolean needleReady = false;

    public void getNeedleReady() {
        this.needleReady = true;
    }
    public boolean isNeedleReady() {
        return this.needleReady;
    }
    @Override
    public boolean fill(Medicine m) {
        if (m.getType() == Medicine.Type.Injection) {
           this.content = m;
        }
        return m.getType() == Medicine.Type.Injection;
    }
}
