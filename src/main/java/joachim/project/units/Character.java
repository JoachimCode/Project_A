package joachim.project.units;

import joachim.project.display.EntityImage;
import joachim.project.display.Screen;

import javax.swing.*;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * This class is to be the base model of all the characters in the game. It holds basic variables and common
 * methods, like move and shoot. Further classes will extend this class to create the different characters.
 */
public class Character {
    private int width = 60;
    private int height = 80;
    private int xCord;
    private int yCord;
    private int yStartCord;
    private int xStartCord;
    private int yMax = 880 - height;
    private int xMax = 1920-width- 10;
    private int yMin = 10;
    private int xMin = 10;
    private int movementspeed = 2;
    private int baseMovementSpeed = 2;
    private int attackSpeed = 5;
    private int baseAttackSpeed = 5;
    private int attackDamage = 1;
    private int attackCooldown = 30;
    private List<Bullet> list_of_player_bullets = new CopyOnWriteArrayList<>();
    private JFrame root;
    private Screen screen;
    private EntityImage image;
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void shoot(String direction){
        rwLock.readLock().lock();
        Bullet bullet;
        switch (direction){
            case("down"):
                bullet = new Bullet(screen, "bullet_down.png", "bullet", xCord +width/2, yCord +height, direction,
                        bulletSpeed);
                break;
            case("up"):
                bullet = new Bullet(screen, "bullet_up.png", "bullet", xCord +width/2, yCord, direction,
                        bulletSpeed);
                break;
            case("left"):
                bullet = new Bullet(screen, "bullet_left.png", "bullet", xCord, yCord +(height)/2, direction,
                        bulletSpeed);
                break;
            case("right"):
                bullet = new Bullet(screen, "bullet_right.png", "bullet", xCord +width, yCord +(height)/2, direction,
                        bulletSpeed);
                break;
            default:
                bullet = new Bullet(screen, "bullet_down.png", "bullet", xCord, yCord +height, direction,
                        bulletSpeed);
                break;
        }
        screen.add_to_elements(bullet.get_image());
        list_of_player_bullets.add(bullet);
        rwLock.readLock().unlock();
    }

    public boolean check_bounds(String direction){
        boolean in_bounds = true;
        if(direction.equals("left") && xCord < xMin){
            in_bounds = false;
        }
        else if(direction.equals("right") && xCord > xMax){
            in_bounds = false;
        }
        else if(direction.equals("up") && yCord < yMin){
            in_bounds = false;
        }
        else if (direction.equals("down") && yCord > yMax){
            in_bounds = false;
        }
        return in_bounds;
    }

    public Iterator<Bullet> getBulletListIterator(){
        rwLock.readLock().lock();
        Iterator<Bullet> player_bullet_list = list_of_player_bullets.iterator();
        rwLock.readLock().unlock();
        return player_bullet_list;
    }

    public void move_up(){
        if(check_bounds("up")) {
            rwLock.writeLock().lock();
            yCord = yCord - movementSpeed;
            image.setX(xCord);
            image.setY(yCord);
            rwLock.writeLock().unlock();
        }

    }

    public void move_down(){
        if(check_bounds("down")) {
            rwLock.writeLock().lock();
            yCord = yCord + movementspeed;
            image.setX(xCord);
            image.setY(yCord);
            rwLock.writeLock().unlock();
        }
    }


    public void move_left(){
        if(check_bounds("left")) {
            rwLock.writeLock().lock();
            xCord = xCord - movementspeed;
            image.setX(xCord);
            image.setY(yCord);
            rwLock.writeLock().unlock();
        }
    }

    public void move_right(){
        if(check_bounds("right")) {
            rwLock.writeLock().lock();
            xCord = xCord + movementspeed;
            image.setX(xCord);
            image.setY(yCord);
            rwLock.writeLock().unlock();
        }
    }
    public void move_up_right(){
        move_right();
        move_up();
    }

    public void move_up_left(){
        move_up();
        move_left();
    }
    public void move_down_left(){
        move_down();
        move_left();
    }
    public void move_down_right(){
        move_down();
        move_right();
    }

    public int getX(){
        rwLock.readLock().lock();
        int x = xCord;
        rwLock.readLock().unlock();
        return x;
    }
    public int getY(){
        rwLock.readLock().lock();
        int y = yCord;
        rwLock.readLock().unlock();
        return y;
    }

    public int getHeight(){
        rwLock.readLock().lock();
        int temp_height = height;
        rwLock.readLock().unlock();
        return temp_height;
    }

    public int getWidth(){
        rwLock.readLock().lock();
        int temp_width = width;
        rwLock.readLock().unlock();
        return temp_width;
    }

    public EntityImage getSprite(){
        return image;
    }

    public void setX(int x){
        rwLock.writeLock().lock();
        xCord = x;
        image.setX(xCord);
        rwLock.writeLock().unlock();
    }

    public void setY(int y){
        rwLock.writeLock().lock();
        yCord = y;
        image.setY(yCord);
        rwLock.writeLock().unlock();
    }

    public void setRoot(JFrame root) {
        this.root = root;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public void setImage(EntityImage image) {
        this.image = image;
    }

    public ReadWriteLock getRwLock() {
        return rwLock;
    }
}
