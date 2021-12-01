import java.util.*;
import greenfoot.*;

/**
 * 
 */
public class NewRoom extends World
{

    /**
     * Constructor for objects of class NewRoom.
     */
    public NewRoom()
    {
        super(800, 800, 1);
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Soul soul =  new Soul();
        addObject(soul, 397, 397);
        Health health =  new Health();
        addObject(health, 42, 43);
        Health health2 =  new Health();
        addObject(health2, 105, 45);
        Health health3 =  new Health();
        addObject(health3, 171, 44);
        Monster monster =  new Monster();
        addObject(monster, 548, 358);
        soul.setLocation(383,760);
        removeObject(health3);
        removeObject(health2);
        removeObject(health);
        Door door = new Door();
        addObject(door,379,1);
        Door door2 = new Door();
        addObject(door2,2,391);
        Door door3 = new Door();
        addObject(door3,795,397);
        SpeedySoul speedySoul = new SpeedySoul();
        addObject(speedySoul,222,390);
        removeObject(monster);
        SoulTears soulTears = new SoulTears();
        addObject(soulTears,566,618);
        Candle candle = new Candle();
        addObject(candle,99,142);
        Light light = new Light();
        addObject(light,104,648);
        DemonSpear demonSpear = new DemonSpear();
        addObject(demonSpear,542,442);
        BeastsClaws beastsClaws = new BeastsClaws();
        addObject(beastsClaws,210,499);
        candle.setLocation(550,273);
        speedySoul.setLocation(381,687);
        OPSoul oPSoul = new OPSoul();
        addObject(oPSoul,268,585);
        TankySoul tankySoul = new TankySoul();
        addObject(tankySoul,610,698);
        ToughSoul toughSoul = new ToughSoul();
        addObject(toughSoul,193,724);
        candle.setLocation(127,379);
        light.setLocation(136,427);
        candle.setLocation(237,342);
        light.setLocation(94,337);
        soulTears.setLocation(368,335);
        beastsClaws.setLocation(238,466);
        demonSpear.setLocation(160,477);
        beastsClaws.setLocation(396,455);
        demonSpear.setLocation(239,459);
        oPSoul.setLocation(708,731);
        tankySoul.setLocation(708,655);
        toughSoul.setLocation(739,560);
        speedySoul.setLocation(712,461);
        Monster monster2 = new Monster();
        addObject(monster2,484,627);
    }
}
