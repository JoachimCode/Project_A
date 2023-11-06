package joachim.project.units;

import joachim.project.display.EntityImage;
import joachim.project.display.Screen;

import java.io.IOException;

public class PlayerCharacter extends Character{
    private EntityImage healthBarImage;
    private int maxHealth = 5;
    private int health = 5;
    private int abilityCooldown = 10;
    private int abilityRemainingCooldown = 10;
    public PlayerCharacter(Screen screen){
        setScreen(screen);
        setImage(new EntityImage("player_character.png", getX(), getY(), getWidth(), getHeight(), "playercharacter"));
        healthBarImage = new EntityImage("hp5.png",460,980, 1000, 35, "hp_bar");
    }

    public void loseHealth(){
        getRwLock().writeLock().lock();
         health = health - 1;
        getRwLock().writeLock().unlock();
    }
}
