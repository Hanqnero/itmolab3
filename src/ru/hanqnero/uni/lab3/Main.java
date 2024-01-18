package ru.hanqnero.uni.lab3;

import ru.hanqnero.uni.lab3.environment.*;
import ru.hanqnero.uni.lab3.environment.abstractions.*;
import ru.hanqnero.uni.lab3.environment.food.Breakfast;
import ru.hanqnero.uni.lab3.environment.food.HotChocolate;
import ru.hanqnero.uni.lab3.environment.food.Taste;
import ru.hanqnero.uni.lab3.environment.food.cooking.Stove;
import ru.hanqnero.uni.lab3.environment.medicine.Injection;
import ru.hanqnero.uni.lab3.environment.medicine.TakenInternallyMedicine;
import ru.hanqnero.uni.lab3.environment.properties.Color;
import ru.hanqnero.uni.lab3.environment.properties.Temperature;
import ru.hanqnero.uni.lab3.environment.riding.Sledge;
import ru.hanqnero.uni.lab3.people.*;
import ru.hanqnero.uni.lab3.people.moods.ActionMood;
import ru.hanqnero.uni.lab3.people.properties.FaceExpression;
import ru.hanqnero.uni.lab3.people.properties.HairStyle;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;


public class Main {
    public static void main(String[] args) {
        Scene mainScene = new Scene();
        mainScene.setLocation(new Location(Location.Type.Default));
        mainScene.setTimestamp(new TimeStamp(TimeStamp.Type.Regular, Instant.parse("2000-01-01T09:00:00.00Z"), TimeStamp.SpecialType.None));
        var mainLouis = new Louis();
        mainScene.addCharacter(mainLouis);

        mainLouis.nod(30f);

        var breakfast = new Breakfast(150, Taste.Default);
        mainLouis.consumeFood(breakfast);

        var hotChocolate = new HotChocolate(100, Taste.Disgusting, Temperature.Cold);
        var cup = new Cup();
        cup.fill(hotChocolate);
        var stove = new Stove();
        mainScene.getLocation().addObject(stove);
        stove.turnOn();
        mainLouis.cookFood(hotChocolate, stove);
        stove.turnOff();

        var mainGage = new Gage();
        mainGage.die();

        mainLouis.addNewMood(ActionMood.Patiently);
        mainLouis.consumeFood(cup);

        mainLouis.addClothingItem(new Clothes(Color.DarkGrey, Clothes.ClothingType.Suit));
        mainLouis.dislikeNewItem(new Clothes(Color.Black, Clothes.ClothingType.Suit));

        mainLouis.addNewMood(ActionMood.Shocked);
        mainLouis.shave();
        mainLouis.styleHair(HairStyle.Brushed);

        var mainEllie = new Ellie();
        mainScene.addCharacter(mainEllie);

        var mainRachel = new Rachel();
        mainScene.addCharacter(mainRachel);

        mainRachel.addNewMood(ActionMood.ConcentratedInsanity); // ...выражение сосредоточенного безумия...
        mainRachel.addNewMood(ActionMood.Shocked); // ...Шок остался... (It must be present in the first place then)

        mainEllie.addClothingItem(new Clothes(Color.Yellow, Clothes.ClothingType.Blouse));
        mainEllie.addClothingItem(new Clothes(Color.LightBlue, Clothes.ClothingType.Jeans));

        breakfast = new Breakfast(150, Taste.Default);
        mainEllie.consumeFood(breakfast);


        var photoScene = new Scene();
        photoScene.setLocation(new Location(Location.Type.Default));
        photoScene.setTimestamp(new TimeStamp(TimeStamp.Type.Special, Instant.parse("1970-01-01T12:00:00Z"), TimeStamp.SpecialType.EllieBirthday));

        var gageInPhoto = new Gage();
        var ellieInPhoto = new Ellie();
        var louisInPhoto = new Louis();
        var rachelInPhoto = new Rachel();
        var sledgeInPhoto = new Sledge();

        photoScene.addCharacter(gageInPhoto);
        photoScene.addCharacter(ellieInPhoto);
        photoScene.addCharacter(rachelInPhoto);
        photoScene.addCharacter(louisInPhoto);

        gageInPhoto.addClothingItem(new Clothes(Color.LightBlue, Clothes.ClothingType.Jacket));
        gageInPhoto.ride(sledgeInPhoto);
        ellieInPhoto.ride(sledgeInPhoto);
        gageInPhoto.setFaceExpression(FaceExpression.Smile);
        ellieInPhoto.setFaceExpression(FaceExpression.Smile);

        var photo = new Photo(photoScene, Photo.Type.Polaroid);

        mainEllie.addNewMood(ActionMood.Silent);
        mainEllie.hold(photo);

        var tragedyScene = new Scene();
        tragedyScene.setLocation(new Location(Location.Type.Default));
        var gageInTragedy = new Gage();
        var othersInTragedy = new Person[] {new Louis(), new Ellie(), new Rachel()};
        tragedyScene.addCharacter(gageInTragedy);
        for (var p: othersInTragedy) {
            tragedyScene.addCharacter(p);
            p.addNewMood(ActionMood.Screaming);
            p.doMoodAction(ActionMood.Screaming);
            p.removeMood(ActionMood.Screaming);
        }
//        Gage did not stop
        gageInTragedy.driveRisky(0f, 50f);
//        Gage dead now


        var louisThought = new Scene();
        louisThought.setLocation(new Location(Location.Type.Ceremony));

        var peopleInLouisThought = new LinkedList<Person>();
        peopleInLouisThought.add(new Louis());
        peopleInLouisThought.add(new Rachel());
        peopleInLouisThought.add(new Ellie());
        peopleInLouisThought.add(new Jud());

        var gageInLouisThought = new Gage();
        gageInLouisThought.die();
        louisThought.addCharacter(gageInLouisThought);
        var coffin = new Coffin();
        coffin.setState(Coffin.State.Open);
        coffin.fill(gageInLouisThought);

        louisThought.getLocation().addObject(coffin);
        for (var p: peopleInLouisThought) {
            louisThought.addCharacter(p);
        }

        if (coffin.getState().equals(Coffin.State.Open)) {
            for (var p: peopleInLouisThought) {
                p.addNewMood(ActionMood.Screaming);
                p.goOutOfLocation();
            }
        }

        mainLouis.thinkOfAScene(louisThought);


        var mainSteve = new Steve();
        var syringe = new Syringe();
        syringe.fill(new Injection(50));
        syringe.getNeedleReady();
        mainSteve.makeInjection(mainRachel, syringe);

        mainEllie.dislikeNewItem(new TakenInternallyMedicine(0, Color.Colorless));
        mainEllie.dislikeNewItem(new Injection(0));
        mainEllie.addNewMood(ActionMood.WithoutComplains);
        mainSteve.giveMedicine(mainEllie, new TakenInternallyMedicine(30, Color.Colorless));
        mainEllie.removeMood(ActionMood.WithoutComplains);

        mainScene.setTimestamp(new TimeStamp(TimeStamp.Type.Regular, Instant.parse("1970-01-01T10:00:00.00Z"), TimeStamp.SpecialType.None));
        mainEllie.sleep();

        var tv = new TV();
        mainScene.getLocation().addObject(tv);
        tv.setCurrentProgramme(new TVProgramme("Wheel Of Fortune", 50));
        int QUESTION_AMOUNT = 15;
        for (int i = 0; i < QUESTION_AMOUNT; ++i) {
            mainRachel.watchTV(tv);
            mainSteve.askQuestion(mainRachel, new Question("Something not important", 30, 1f));
            /*
              As more questions get asked and more TV get watches
              slower she will answer the questions
            */
        }

        mainRachel.removeMood(ActionMood.ConcentratedInsanity);

        var mainJud = new Jud();
        var ceremonyPreparations = new Scene();
        ceremonyPreparations.setLocation(new Location(Location.Type.Ceremony));
        ceremonyPreparations.addCharacter(mainJud);


        ceremonyPreparations.setLocation(
        new Location(Location.Type.Ceremony) {
            private float completeness;

            public boolean isCompleted() {
                return completeness == 1f;
            }

            public float getCompleteness() {
                return completeness;
            }

            public void workOnCompletion(float amount) {
                if (!isCompleted()) {
                    completeness += amount;

                    var MAX = 1f; // Clamp between logically right numbers
                    var MIN = 0f;
                    completeness = Math.max(Math.min(completeness, MAX), MIN);
                }
            }
        }

        );


        mainJud.takePartIn(ceremonyPreparations.getLocation(), .1f);

        mainJud.addNewMood(ActionMood.Calm);

        var judWifeFuneral = new Scene();
        var judAtWifeFuneral = new Jud();
        judAtWifeFuneral.addNewMood(ActionMood.Calm);

        assert judAtWifeFuneral.getCurrentMoods().contains(ActionMood.Calm) == mainJud.getCurrentMoods().contains(ActionMood.Calm) : "Он был так же спокоен, как и на похоронах своей жены три месяца назад";

        var threeMonthsAgo = mainScene.getTimestamp().date().minus(90L, ChronoUnit.DAYS);
        judWifeFuneral.setTimestamp(new TimeStamp(TimeStamp.Type.Special, threeMonthsAgo, TimeStamp.SpecialType.Funeral));

        mainSteve.askChangePosition(mainJud, Location.Position.Side);

        mainLouis.goOutOfLocation();

        interface CanBeCompleted {boolean isCompleted();}
        if (((CanBeCompleted)(ceremonyPreparations.getLocation())).isCompleted()) {
            ceremonyPreparations.addCharacter(mainLouis);
        }

        //TODO: move to different packages
        //TODO: make enums capslock
        //TODO: Furniture interface is empty
        //TODO: exceptions are to put in separate package
    }
}
