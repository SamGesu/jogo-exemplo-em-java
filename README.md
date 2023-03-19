# jogo-exemplo-em-java

MyWorld:
```java
import greenfoot.*;

public class MyWorld extends World {

    // Declara e instancia objetos no jogo.
    olavo_de_carvalho olavo = new olavo_de_carvalho();
    Heroi player = new Heroi();

    // Metodo construtor do mundo.
    public MyWorld() {
        super(500, 600, 1); // Chama o construtor da superclasse (World) para criar um mundo com determinadas dimensões.
        setBackground("mar_de_LCS.png"); // Coloca o plano de fundo.
        addObject(player, 250, 530); // Adiciona o objeto do jogador ao mundo em determinadas coordenadas.
        addObject(olavo, Greenfoot.getRandomNumber(600), 0); // Adiciona o objeto inimigo em uma coordenada aleatoria em X e no topo da tela.
    }
}
```
Heroi:
```java
import greenfoot.*;

public class Heroi extends Actor {
    
    private int delayTiro = 20; // Variável que controla o atraso entre os tiros.

    public void act() {
        movimentar(); // Chama o método para mover o personagem.
        atirar(); // Chama o método para atirar.
    }
    
    // Método para mover o personagem.
    private void movimentar() {
        if(Greenfoot.isKeyDown("left")) { // Verifica se a tecla esquerda foi pressionada.
            setLocation(getX() - 5, getY()); // Move o personagem 5 pixels para a esquerda.
        }
        if(Greenfoot.isKeyDown("right")) { // Verifica se a tecla direita foi pressionada.
            setLocation(getX() + 5, getY()); // Move o personagem 5 pixels para a direita.
        }
    }
    
    // Método para atirar.
    private void atirar() {
        if(Greenfoot.isKeyDown("space")) { // Verifica se a tecla espaço foi pressionada.
            if(delayTiro == 0) { // Verifica se o atraso entre os tiros acabou.
                MyWorld mundoQueEstamos = (MyWorld) getWorld(); // Obtém o mundo em que o personagem está.
                mundoQueEstamos.addObject(new feto_adoçante(), getX(), getY() - 30); // Adiciona um novo objeto tiro no mundo.
                delayTiro = 20; // Reinicia o atraso entre os tiros.
                String[] falas = {"neco-arc-sound-effect.wav","neco-arc-dori.wav","necoarc-nyanyanya.wav","necoarc-nyanyanya2.wav"}; // Cria um array de falas.
                int indexAleatoria = Greenfoot.getRandomNumber(falas.length); // Obtém um índice aleatório do array de falas.
                String falaAleatoria = falas[indexAleatoria]; // Obtém a fala aleatória correspondente ao índice.
                Greenfoot.playSound(falaAleatoria); // Toca a fala aleatória.
            }
            else { // Caso o atraso entre os tiros ainda não tenha acabado.
                delayTiro--; // Decrementa o atraso entre os tiros.
            }
        }
    }
}

```

Projetil:
```java
import greenfoot.*;  

public class feto_adoçante extends Actor
{
    public void act()
    {
        checkCollisions(); // verifica colisões com outros atores
        movimentar(); // movimenta o ator
        sairCena(); // verifica se o ator saiu da cena
    }
    
    private void movimentar()
    {
        if(getWorld() != null) // verifica se o mundo existe
        {
            setLocation(getX(), getY() - 5); // move o ator para cima (no eixo y)
        }
    }
    
    private void sairCena()
    {
        if(getY() == 0) // verifica se o ator chegou ao topo da tela
        {
            MyWorld mundoQueEstamos = (MyWorld) getWorld(); // pega o mundo atual
            mundoQueEstamos.removeObject(this); // remove o ator do mundo
        }
    }
    
    public boolean checkCollisions() 
    {
        Actor actor = getOneIntersectingObject(olavo_de_carvalho.class); // verifica se colidiu com um ator específico
        
        if (actor != null) // se colidiu com o ator olavo_de_carvalho
        {
            getWorld().removeObject(actor); // remove o ator olavo_de_carvalho do mundo
            try
            {
                getWorld().removeObject(this); // tenta remover o próprio ator
            } catch (IllegalStateException e) // caso ocorra uma exceção
            {} // não faz nada
            return true; // retorna verdadeiro
        }
        
        return false; // caso contrário, retorna falso
    }
}

```
Inimigo:
```java
import greenfoot.*;

public class Inimigo extends Actor
{
    public int speed = 3; // velocidade de movimento do inimigo
    public int horizontalDirection = 1; // direção horizontal do inimigo (1 = direita, -1 = esquerda)
    public int verticalDirection = 0; // direção vertical do inimigo (0 = sem movimento)
    
    public void act()
    {
        checkCollision(); // verifica colisão com os projéteis
        moveEnemy(); // move o inimigo pela tela
    }
    
    public void moveEnemy()
    {
        setLocation(getX() + (speed * horizontalDirection), // move o inimigo na direção horizontal
                    getY() + (speed * verticalDirection)); // move o inimigo na direção vertical
        if (getX() <= 0 || getX() >= getWorld().getWidth() - 1) // verifica se o inimigo chegou ao limite da tela na horizontal
        {
            horizontalDirection *= -1; // inverte a direção horizontal do inimigo
        }
        if (Greenfoot.getRandomNumber(100) < 2) // define aleatoriamente a direção vertical do inimigo
        {
            verticalDirection = Greenfoot.getRandomNumber(3) - 1; // define a direção vertical (-1 = para cima, 0 = sem movimento, 1 = para baixo)
        }
    }
    
    private void checkCollision() 
    {
        Actor projectile = getOneIntersectingObject(feto_adoçante.class); // verifica se o inimigo colidiu com algum projétil
        
        if (projectile != null) // se colidiu com algum projétil
        {
            Greenfoot.playSound("lego-yoda-death-sound-effect.wav"); // reproduz um efeito sonoro
            
            getWorld().removeObject(projectile); // remove o projétil da tela
            getWorld().removeObject(this); // remove o inimigo da tela
        }
    }
}
```
olavo_de_carvalho:
```java
import greenfoot.*; 

public class olavo_de_carvalho extends Inimigo
{
    public void act()
    {
        super.moveEnemy(); // chama o método de movimentação herdado da classe pai
        super.act(); // chama o método de verificação de colisão herdado da classe pai
    }
}
```
