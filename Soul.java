import java.util.*;
import greenfoot.*;

/**
 * 
 */
public class Soul extends Actor
{
    private int damageTimer = 100;
    private boolean noTouch = false;

    private int damageByMonsters;

    private String weapon = "SoulSpear";
    private int weaponTimer = 30;
    private boolean weaponHolder = true;

    private String statsBooster = "";
    private int health = 30;
    private int totalHealth;
    private int currentHealth;
    private int levelUpHealth;
    private int bonusHealth;
    private int speed;
    private int bonusDamage;
    private int bonusDefense;
    private int worldChecker = 1;
    private int expNum = 0;
    public static int killCount = 0;
    private int killCountHold = 0;
    private int level = 0;

    private String holyItem = "";

    /**
     * Act - do whatever the Soul wants to do. This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        moveAndTurn();
        damage();
        getWorld().showText("Hp: " + totalHealth, 300, 100);
        getWorld().showText("Speed: " + speed, 500, 100);
        getWorld().showText("Protection: " + bonusDefense, 700, 100);
        getWorld().showText("Damage: " + bonusDamage, 100, 100);

        weapons();
        useWeapons();

        statBoosters();
        useStatBoosters();

        nextLevel();

        experience();
        levelUp();
    }

    /**
    The Soul's movement
     */
    public void moveAndTurn()
    {
        if (Greenfoot.isKeyDown("w")) {
            setRotation(270);
            move(speed);
            setRotation(0);
        }
        if (Greenfoot.isKeyDown("s")) {
            setRotation(90);
            move(speed);
            setRotation(0);
        }
        if (Greenfoot.isKeyDown("a")) {
            setRotation(180);
            move(speed);
            setRotation(0);
        }
        if (Greenfoot.isKeyDown("d")) {
            setRotation(0);
            move(speed);
            setRotation(0);
        }
    }

    /**
     * Monsters damage Soul
     */
    public void damage()
    {
        if (noTouch == false) {
            if (isTouching(Monster.class)) {
                totalHealth = (totalHealth + bonusDefense) - damageByMonsters;
                noTouch = true;
            }
        }
        if (noTouch == true) {
            if (damageTimer > 0) {
                damageTimer = damageTimer - 1;
            }
            if (damageTimer == 0) {
                noTouch = false;
                damageTimer = 100;
            }
        }
    }

    public void statBoosters()
    {
        Actor soulTough = getOneIntersectingObject(ToughSoul.class);
        Actor soulSpeedy = getOneIntersectingObject(SpeedySoul.class);
        Actor soulTanky = getOneIntersectingObject(TankySoul.class);
        Actor soulOP = getOneIntersectingObject(OPSoul.class);
        if (soulTough != null) {
            statsBooster = "ToughSoul";
            getWorld().removeObjects(getWorld().getObjects(ToughSoul.class));
        }
        if (soulSpeedy != null) {
            statsBooster = "SpeedySoul";
            getWorld().removeObjects(getWorld().getObjects(SpeedySoul.class));
        }
        if (soulTanky != null) {
            getWorld().removeObjects(getWorld().getObjects(TankySoul.class));
            statsBooster = "TankySoul";
        }
        if (soulOP != null) {
            getWorld().removeObjects(getWorld().getObjects(OPSoul.class));
            statsBooster = "OPSoul";
        }
    }

    public void useStatBoosters()
    {
        if (statsBooster == "ToughSoul") {
            bonusDamage = 4;
        } else if (statsBooster != "OPSoul"){
            bonusDamage = 0;
        }
        if (statsBooster == "SpeedySoul") {
            speed = 4;
        } else if (statsBooster != "OPSoul"){
            speed = 2;
        }
        if (statsBooster == "TankySoul") {
            bonusHealth = 12;
            bonusDefense = 2;
        } else if (statsBooster != "OPSoul"){
            bonusHealth = 0;
            bonusDefense = 0;
        }
        if (statsBooster == "OPSoul") {
            bonusHealth = 6;
            bonusDamage = 2;
            speed = 3;
            bonusDefense = 1;
        } else if (statsBooster != "ToughSoul" && statsBooster != "SpeedySoul" && statsBooster != "TankySoul"){
            bonusHealth = 0;
            bonusDamage = 0;
            speed = 2;
            bonusDefense = 0;
        }
    }

    /**
     * HOLY ITEMS - Vampiric Cape
     */
    /*public void VamCape()
    {
    Actor VamCape = getOneIntersectingObject(VamCape.class);
    if (VamCape != null) {
    World world = getWorld();
    world.removeObject(VamCape);
    holyItem = "VamCape";
    }
    while (holyItem == "VamCape") {
    }
    }*/

    /**
     * Next Rooms
     */

    public void nextLevel()
    {
        if (getWorld().getObjects(Soul.class) != null)
        {
            if (isTouching(DoorUp.class) || isTouching(DoorLeft.class) || isTouching(DoorRight.class))
            {
                if (getWorld().getObjects(Monster.class).size() == 0){
                    if (worldChecker == 1){
                        World room2 = new Room2();
                        room2.addObject(this, 10, 200);
                        Greenfoot.setWorld(room2);
                        worldChecker += 1;
                    }
                }
            }
        }
    }

    public void weapons()
    {
        Actor soulSoulSpear = getOneIntersectingObject(SoulSpear.class);
        Actor soulDemonSpear = getOneIntersectingObject(DemonSpear.class);
        Actor soulClaws = getOneIntersectingObject(BeastsClaws.class);
        Actor soulLight = getOneIntersectingObject(Light.class);
        Actor soulCandle = getOneIntersectingObject(Candle.class);
        Actor soulTears = getOneIntersectingObject(SoulTears.class);
        if (soulSoulSpear != null) {
            weapon = "SoulSpear";
        }
        if (soulDemonSpear != null) {
            weapon = "DemonSpear";
        }
        if (soulClaws != null) {
            weapon = "BeastsClaws";
        }
        if (soulLight != null) {
            weapon = "Light";
        }
        if (soulCandle != null) {
            weapon = "Candle";
        }
        if (soulTears != null) {
            weapon = "SoulTears";
        }
    }

    public void useWeapons()
    {
        SoulSpear holdingSoulSpear = new SoulSpear();
        DemonSpear holdingDemonSpear = new DemonSpear();
        BeastsClaws holdingClaws = new BeastsClaws();
        Light holdingLight = new Light();
        Candle holdingCandle = new Candle();
        SoulTears holdingTears = new SoulTears();
        Actor soulSoulSpear = getOneIntersectingObject(SoulSpear.class);
        Actor soulDemonSpear = getOneIntersectingObject(DemonSpear.class);
        Actor soulClaws = getOneIntersectingObject(BeastsClaws.class);
        Actor soulLight = getOneIntersectingObject(Light.class);
        Actor soulCandle = getOneIntersectingObject(Candle.class);
        Actor soulTears = getOneIntersectingObject(SoulTears.class);
        if (weapon == "SoulSpear") {
            List soulSpearInWorld = getWorld().getObjects(SoulSpear.class);
            if (Greenfoot.isKeyDown("space")) {
                if (soulSpearInWorld.size() == 0) {
                    getWorld().addObject(holdingSoulSpear, getX() + 25, getY());
                }
                weaponHolder = false;
            }
            if (weaponTimer > 0) {
                weaponTimer = weaponTimer - 1;
            }
            if (weaponTimer == 0) {
                getWorld().removeObjects(soulSpearInWorld);
                weaponTimer = 30;
                weaponHolder = true;
            }
        } else {
            getWorld().removeObject(soulSoulSpear);
        }
        if (weapon == "DemonSpear") {
            List demonSpearInWorld = getWorld().getObjects(DemonSpear.class);
            if (Greenfoot.isKeyDown("space")) {
                if (demonSpearInWorld.size() == 0) {
                    getWorld().addObject(holdingDemonSpear, getX() + 25, getY());
                }
                weaponHolder = false;
            }
            if (weaponTimer > 0) {
                weaponTimer = weaponTimer - 1;
            }
            if (weaponTimer == 0) {
                getWorld().removeObjects(demonSpearInWorld);
                weaponTimer = 30;
                weaponHolder = true;
            }
        } else {
            getWorld().removeObject(soulDemonSpear);
        }
        if (weapon == "BeastsClaws") {
            List beastsclawsInWorld = getWorld().getObjects(BeastsClaws.class);
            if (Greenfoot.isKeyDown("space")) {
                if (beastsclawsInWorld.size() == 0) {
                    getWorld().addObject(holdingClaws, getX() + 25, getY());
                }
                weaponHolder = false;
            }
            if (weaponTimer > 0) {
                weaponTimer = weaponTimer - 1;
            }
            if (weaponTimer == 0) {
                getWorld().removeObjects(beastsclawsInWorld);
                weaponTimer = 30;
                weaponHolder = true;
            }
        } else {
            getWorld().removeObject(soulClaws);
        }
        if (weapon == "Light") {
            List lightInWorld = getWorld().getObjects(Light.class);
            if (Greenfoot.isKeyDown("space")) {
                if (lightInWorld.size() == 0) {
                    getWorld().addObject(holdingLight, getX() + 25, getY());
                }
                weaponHolder = false;
            }
            if (weaponTimer > 0) {
                weaponTimer = weaponTimer - 1;
            }
            if (weaponTimer == 0) {
                getWorld().removeObjects(lightInWorld);
                weaponTimer = 30;
                weaponHolder = true;
            }
        } else {
            getWorld().removeObject(soulLight);
        }
        if (weapon == "Candle") {
            List candleInWorld = getWorld().getObjects(Candle.class);
            if (Greenfoot.isKeyDown("space")) {
                if (candleInWorld.size() == 0) {
                    getWorld().addObject(holdingCandle, getX() + 25, getY());
                }
                weaponHolder = false;
            }
            if (weaponTimer > 0) {
                weaponTimer = weaponTimer - 1;
            }
            if (weaponTimer == 0) {
                getWorld().removeObjects(candleInWorld);
                weaponTimer = 30;
                weaponHolder = true;
            }
        } else {
            getWorld().removeObject(soulCandle);
        }
        if (weapon == "SoulTears") {
            List soulTearsInWorld = getWorld().getObjects(SoulTears.class);
            if (Greenfoot.isKeyDown("space")) {
                if (soulTearsInWorld.size() == 0) {
                    getWorld().addObject(holdingTears, getX() + 25, getY());
                }
                weaponHolder = false;
            }
            if (weaponTimer > 0) {
                weaponTimer = weaponTimer - 1;
            }
            if (weaponTimer == 0) {
                getWorld().removeObjects(soulTearsInWorld);
                weaponTimer = 30;
                weaponHolder = true;
            }
        } else {
            getWorld().removeObject(soulTears);
        }
    }

    public void experience()
    {
        if(killCount > killCountHold)
        {
            expNum += 10;
            killCountHold++;
        }
    }
    
    public void levelUp()
    {
        if(expNum == 100)
        {
            level += 1;
            expNum = 0;
        }
    }
}