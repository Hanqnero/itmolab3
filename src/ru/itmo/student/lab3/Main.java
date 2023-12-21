package ru.itmo.student.lab3;

import ru.itmo.student.lab3.environment.*;
import ru.itmo.student.lab3.environment.abstractions.*;
import ru.itmo.student.lab3.environment.food.*;
import ru.itmo.student.lab3.environment.medicine.Injection;
import ru.itmo.student.lab3.environment.medicine.TakenInternallyMedicine;
import ru.itmo.student.lab3.people.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static ru.itmo.student.lab3.environment.abstractions.TimeStamp.Type.Regular;

public class Main {
    public static void main(String[] args) {
        Scene mainScene = new Scene();
        mainScene.setLocation(new Location(Location.Type.Default));
        var mainLouis = new Louis();
        mainScene.addCharacter(mainLouis);

        mainLouis.nod(30f);

        var breakfast = new Breakfast(150);
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

        breakfast = new Breakfast(150);
        mainEllie.consumeFood(breakfast);


        var photoScene = new Scene();
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

//        TODO: SKIPPED A BIT OF TEXT HERE

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

        mainScene.setTimestamp(new TimeStamp(Regular, Instant.parse("T10:00:00Z"), TimeStamp.SpecialType.None));
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
        ceremonyPreparations.addCharacter(mainJud);
        ceremonyPreparations.setLocation(new Preparations(Location.Type.Ceremony));
        mainJud.takePartIn((Preparations) ceremonyPreparations.getLocation(), .1f);

        mainJud.addNewMood(ActionMood.Calm);

        var judWifeFuneral = new Scene();
        var judAtWifeFuneral = new Jud();
        judAtWifeFuneral.addNewMood(ActionMood.Calm);

        assert judAtWifeFuneral.getCurrentMoods().contains(ActionMood.Calm) == mainJud.getCurrentMoods().contains(ActionMood.Calm) : "Он был так же спокоен, как и на похоронах своей жены три месяца назад";

        var threeMonthsAgo = mainScene.getTimestamp().date().minus(3, ChronoUnit.MONTHS);
        judWifeFuneral.setTimestamp(new TimeStamp(TimeStamp.Type.Special, threeMonthsAgo, TimeStamp.SpecialType.Funeral));

        mainSteve.askChangePosition(mainJud, Location.Position.Side);

    }
}
