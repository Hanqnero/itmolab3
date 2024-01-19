package ru.hanqnero.uni.lab3.environment.abstractions.exceptions;

import ru.hanqnero.uni.lab3.environment.abstractions.Location;
import ru.hanqnero.uni.lab3.environment.abstractions.Location.Ground.Tools;

public class WrongToolException extends Exception{
    private Tools requiredTool;
    public WrongToolException(Tools usedTool, Tools requiredTool) {
        super("Wrong tool used to dig soil: %s required, but %s was used".formatted(
           requiredTool, usedTool
        ));
        this.requiredTool = requiredTool;
    }

    public Tools getRequiredTool() {
        return requiredTool;
    }
}
