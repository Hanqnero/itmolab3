package ru.hanqnero.uni.lab3.people.interfaces;

import ru.hanqnero.uni.lab3.people.properties.FaceExpression;

public interface HasFaceExpression {
    @SuppressWarnings("unused")
    FaceExpression getFaceExpression();

    void setFaceExpression(FaceExpression expression);
}
