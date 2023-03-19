import greenfoot.*;  

public class Heroi extends Actor
{
    private int delayTiro = 20;
    public void act()
    {
        movimentar();
        atirar();
    }
    
    private void movimentar()
    {
        if(Greenfoot.isKeyDown("left"))
        {
            setLocation(getX() - 5, getY());
        }
        if(Greenfoot.isKeyDown("right"))
        {
            setLocation(getX() + 5, getY()); 
        }
    }
    
    private void atirar()
    {
        if(Greenfoot.isKeyDown("space"))
        {
            if(delayTiro == 0)
            {
                MyWorld mundoQueEstamos = (MyWorld) getWorld();
                mundoQueEstamos.addObject(new feto_adoçante(), getX(), getY() - 30);
                delayTiro = 20;
                String[] falas = {"neco-arc-sound-effect.wav","neco-arc-dori.wav","necoarc-nyanyanya.wav","necoarc-nyanyanya2.wav"};
                int indexAleatoria = Greenfoot.getRandomNumber(falas.length);
                String falaAleatoria = falas[indexAleatoria];
                Greenfoot.playSound(falaAleatoria);
            }
            else
            {
                delayTiro--;
            }
        }
    }
}
