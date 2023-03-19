import greenfoot.*;  

public class feto_adoçante extends Actor
{
    public void act()
    {
        checkCollisions();
        movimentar();
        sairCena();
    }
    
    private void movimentar()
    {
        if(getWorld() != null)
        {
            setLocation(getX(), getY() - 5);
        }
    }
    
    private void sairCena()
    {
        if(getY() == 0)
        {
            MyWorld mundoQueEstamos = (MyWorld) getWorld();
            mundoQueEstamos.removeObject(this);
        }
    }
    
    public boolean checkCollisions() 
    {
        Actor actor = getOneIntersectingObject(olavo_de_carvalho.class);
        
        if (actor != null) 
        {
            getWorld().removeObject(actor);
            try
            {
                getWorld().removeObject(this);
            } catch (IllegalStateException e)
            {}
            return true;
        }
        
        return false;
    }
}
