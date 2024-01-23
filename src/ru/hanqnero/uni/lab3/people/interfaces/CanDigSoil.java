package ru.hanqnero.uni.lab3.people.interfaces;

import ru.hanqnero.uni.lab3.environment.abstractions.Location.Ground;
import ru.hanqnero.uni.lab3.environment.abstractions.exceptions.NoToolException;
import ru.hanqnero.uni.lab3.environment.abstractions.exceptions.WrongToolException;

public interface CanDigSoil extends HasExhaustion {
    /**
     * @return true if successfully mined stone of any type with pickaxe and false otherwise;
     * @throws WrongToolException thrown when digging requires different tool type
     * @throws NoToolException    thrown when ItemHeld is not type Tool or null
     */
    boolean dig(Ground ground) throws WrongToolException, NoToolException;
}