package ru.hanqnero.uni.lab3.people.interfaces;

import ru.hanqnero.uni.lab3.environment.abstractions.Location.Ground;
import ru.hanqnero.uni.lab3.environment.abstractions.exceptions.NoToolException;
import ru.hanqnero.uni.lab3.environment.abstractions.exceptions.WrongToolException;

public interface CanDigSoil extends HasExhaustion {
   void dig(Ground ground) throws WrongToolException, NoToolException;
}
