package ru.hanqnero.uni.lab3.people.interfaces;

import ru.hanqnero.uni.lab3.environment.abstractions.Location.Ground;
import ru.hanqnero.uni.lab3.environment.abstractions.Location.Ground.Tools;
import ru.hanqnero.uni.lab3.environment.abstractions.exceptions.WrongToolException;

public interface CanDigSoil extends HasExhaustion {
   void dig(Tools tool, Ground ground) throws WrongToolException;
}
