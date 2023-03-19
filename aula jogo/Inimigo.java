import greenfoot.*;
public class Inimigo extends Actor
{
    public int speed = 3;
    public int horizontalDirection = 1;
    public int verticalDirection = 0;
    public void act()
    {
        checkCollision();
    }
    
    public void moveEnemy()
    {
        setLocation(getX() + (speed * horizontalDirection),
                    getY() + (speed * verticalDirection));
        if (getX() <= 0 || getX() >= getWorld().getWidth() - 1) 
        {
            horizontalDirection *= -1;
        }
         if (Greenfoot.getRandomNumber(100) < 2) 
        {
            verticalDirection = Greenfoot.getRandomNumber(3) - 1;
        }
    }
    
    private void checkCollision() 
    {
        Actor projectile = getOneIntersectingObject(feto_adoçante.class);
        if (projectile != null) 
        {
            Greenfoot.playSound("lego-yoda-death-sound-effect.wav");
            
            getWorld().removeObject(projectile);
            getWorld().removeObject(this);
        }
    }
}
