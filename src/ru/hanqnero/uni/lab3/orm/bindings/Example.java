package ru.hanqnero.uni.lab3.orm.bindings;


import ru.hanqnero.uni.lab3.environment.Clothes;
import ru.hanqnero.uni.lab3.environment.Color;
import ru.hanqnero.uni.lab3.environment.Temperature;
import ru.hanqnero.uni.lab3.environment.abstractions.Location;
import ru.hanqnero.uni.lab3.environment.abstractions.Preparations;
import ru.hanqnero.uni.lab3.environment.abstractions.Scene;
import ru.hanqnero.uni.lab3.environment.abstractions.TimeStamp;
import ru.hanqnero.uni.lab3.environment.food.HotChocolate;
import ru.hanqnero.uni.lab3.environment.food.Taste;
import ru.hanqnero.uni.lab3.people.ActionMood;
import ru.hanqnero.uni.lab3.people.Gage;
import java.time.Instant;

public class Example {
    public static void main(String[] args) {

        Session s = new Session("jdbc:postgresql://localhost:5432/lab3", "intellij","strong");
        SceneEntityManager sceneManager = new SceneEntityManager(s);

        var scene = new Scene();
        scene.setLocation(new Preparations(Location.Type.Ceremony));
        ((Preparations)scene.getLocation()).workOnCompletion(0.2f);

        var person = new Gage(new HotChocolate(42, Taste.Disgusting, Temperature.Cold));
        person.addClothingItem(new Clothes(Color.Black, Clothes.ClothingType.Suit));
        person.addClothingItem(new Clothes(Color.Yellow, Clothes.ClothingType.Jeans));
        person.addNewMood(ActionMood.ConcentratedInsanity);
        person.addNewMood(ActionMood.Calm);

        scene.addCharacter(person);
        scene.getLocation().changePosition(person, Location.Position.Side);
        var date = new TimeStamp(TimeStamp.Type.Regular, Instant.now(), TimeStamp.SpecialType.None);
        scene.setTimestamp(date);

        sceneManager.save(scene);

        var sceneCopy = sceneManager.findByID(scene.getID());

    }
}
