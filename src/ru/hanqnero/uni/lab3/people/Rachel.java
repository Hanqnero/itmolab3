package ru.hanqnero.uni.lab3.people;

import ru.hanqnero.uni.lab3.environment.abstractions.Question;

public class Rachel extends Person
        implements CanBeAskedQuestions,HasMedicalCondition,CanWatchTV{
    private int availableBrainPower = 1000;

    public Rachel() {
        super();
    }

    @Override
    public String getName() {return "Rachel";}

    public int getAvailableBrainPower() { return availableBrainPower; }

    public void setAvailableBrainPower(int availableBrainPower) { this.availableBrainPower = availableBrainPower; }


    @Override
    public void whenAskedQuestion(Question q) {
        this.setAvailableBrainPower(this.getAvailableBrainPower()-q.brainPower());
        try {
            Thread.sleep((long) (q.baseTimeToAnswer()*1000*getAnsweringSpeed()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double getAnsweringSpeed() {

        return (double) availableBrainPower / 1000;
    }

    private int medicalCondition = 200;
    @Override
    public void setMedicalCondition(int m) {
       medicalCondition = m;
    }

    @Override
    public int getMedicalCondition() {
        return medicalCondition;
    }
}
