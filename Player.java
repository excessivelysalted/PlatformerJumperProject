import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{   private int enemyHit = 0;
    public static int enemiesKilled;
    
    private int dX = 4;
    private int dY = 2;
    private int acceleration = 1;
    private int jumpStrength = 15;
    
    private boolean isAttacking;
    
    private GreenfootImage image1;
    private GreenfootImage image2;
    private GreenfootImage image3;
    private GreenfootImage image4;
    private GreenfootImage image5;
    private GreenfootImage image6;
    private GreenfootImage image7;
    private GreenfootImage image8;
    
    public Player()
    {
        image1 = new GreenfootImage("KnightRight.png");
        image2 = new GreenfootImage("KnightLeft.png");
        image3 = new GreenfootImage("KnightWalkingRight.png");
        image4 = new GreenfootImage("KnightWalkingLeft.png");
        image5 = new GreenfootImage("KnightJumpingRight.png");
        image6 = new GreenfootImage("KnightJumpingLeft.png");
        image7 = new GreenfootImage("KnightWithSwordUpRight.png");
        image8 = new GreenfootImage("KnightWithSwordUpLeft.png");
    }
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        checkKeys();
        checkForFall();
        pickUpSword();
        checkForEnoughHits();
    }
    
    
    /**
     * Allows the character to move Right.
     */
    public void moveRight()
    {
        setLocation(getX()+dX, getY());
    }
    /**
     * Allows the character to move left.
     */
    public void moveLeft()
    {
        setLocation(getX()-dX, getY());
    }
    /**
     * Sets how fast the character will fall.
     */
    public void fall()
    {
       setLocation(getX(), getY()+dY);
       dY += acceleration;
    }
    
    /**
     * Detects if the player is on the platform.
     */
    public boolean isOnPlatform()
    {
        Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2, Platform.class);
        return under != null;
    }

    /**
     * Detects if the player is on the ground.
     */
    public boolean isOnGround()
    {
        Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2, Ground.class);
        return under != null;
    }
    
    public boolean checkIfNearEnemy()
    {
        Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2, Ground.class);
        return under != null;
    }
    
    /**
     * Checks if a key is being pressed.
     */
    public void checkKeys()
    {   
        if(Greenfoot.isKeyDown("d"))
        {
            moveRight();
            animateRightWalk();
        }
        else if(Greenfoot.isKeyDown("a"))
        {
            moveLeft();
            animateLeftWalk();
        }
        else
        {
            setToStand();
        }
        if(Greenfoot.isKeyDown("w") && isOnGround() || Greenfoot.isKeyDown("w") && isOnPlatform())
        {
            jump();
            animateJump();
        }
        
        if(Greenfoot.isKeyDown("space") && !isAttacking)
        {
            attack();
            isAttacking = true;
            hitRightAnimate();
            hitLeftAnimate();
        }
        if(!Greenfoot.isKeyDown("space") && isAttacking)
        {
            isAttacking = false;
        }
    }
    
    public void pickUpSword()
    {
        if(isTouching(Sword.class))
        {
            removeTouching(Sword.class);
            
            image1 = new GreenfootImage("KnightWithSwordRight.png");
            image2 = new GreenfootImage("KnightWithSwordLeft.png");
            image3 = new GreenfootImage("KnightWithSwordWalkingRight.png");
            image4 = new GreenfootImage("KnightWithSwordWalkingLeft.png");
            image5 = new GreenfootImage("KnightWithSwordJumpingRight.png");
            image6 = new GreenfootImage("KnightWithSwordJumpingLeft.png");
        }
    }
    
    
    public void attack()
    {
         if(isTouching(Creature.class))
         {
            enemyHit++; 
         }
    }
    
    public void checkForEnoughHits()
    {
        if(enemyHit >= 4)
        {
            removeTouching(Enemy.class);
            enemiesKilled++;
            enemyHit = 0;
        }
    }
    
    
    
    
    
    
    
    /**
     * Checks if the plager .
     */
    public void checkForFall()
    {
        if(isOnGround() || isOnPlatform())
        {
            dY = 0;
            setToLand();
        }
        else
        {  
            fall();
        }
    }
    
    /**
     * Sets how the player will jump.
     */
    public void jump()
    {
       dY = -jumpStrength; 
       fall();
    }
    
    public void hitRightAnimate()
    {
         if(getImage() == image1)
        {
            setImage(image7);
        }
        else if(getImage() == image7)
        {
            setImage(image1);
        }
    }
    public void hitLeftAnimate()
    {
        if(getImage() == image2)
        {
            setImage(image8);
        }
        else if(getImage() == image8)
        {
            setImage(image2);
        }
    }
    
    public void setToLand()
    {
        if(getImage() == image5)
        {
            setImage(image1);
        }
        else if(getImage() == image6)
        { 
            setImage(image2);
        }
    }
    public void setToStand()
    {
        if(getImage() == image3)
        {
            setImage(image1);
        }
        else if(getImage() == image4)
        { 
            setImage(image2);
        }
    }
    public void animateJump()
    {
        if(getImage() == image1 || getImage() == image3)
        {
            setImage(image5);
        }
        if(getImage() == image2 ||getImage() == image4)
        {
            setImage(image6);
        }
    }
    public void animateRightWalk()
    {
        if(getImage() == image1)
        {
            setImage(image3);
        }
        else
        {
            setImage(image1);
        }
    }
    
    public void animateLeftWalk()
    {
        if(getImage() == image2)
        {
            setImage(image4);
        }
        else
        {
            setImage(image2);
        }
    }
}
