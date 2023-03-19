import greenfoot.*;


public class MyWorld extends World
{
    olavo_de_carvalho olavo = new olavo_de_carvalho();
    Heroi player = new Heroi();
    public MyWorld()
    {    
        super(500, 600, 1);
        setBackground("mar_de_LCS.png");
        addObject(player, 250, 530);
        addObject(olavo, Greenfoot.getRandomNumber(600), 0);
    }
}

