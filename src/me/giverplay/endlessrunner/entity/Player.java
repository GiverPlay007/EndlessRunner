package me.giverplay.endlessrunner.entity;

import java.awt.Color;
import java.awt.Graphics;
import me.giverplay.endlessrunner.core.Main;
import me.giverplay.endlessrunner.world.Camera;
import me.giverplay.endlessrunner.world.World;

public class Player
{
  private World world;
  
  private double gravity = 0.4;
  private double vspd = 0;
  private double x;
  private double y;
  
  private boolean jump = false;
  
  private int speed = 2;
  
  public Player(World world, int x, int y)
  {
    this.world = world;
    this.x = x;
    this.y = y;
  }
  
  public void tick()
  {
    vspd += gravity;
  
    if (jump && !world.moveAllowed(getX(), (int) y + 1))
    {
      vspd = -9;
      jump = false;;
    }
  
    if (!world.moveAllowed((int) x, (int) (y + vspd)))
    {
      int signVsp = 0;
    
      if (vspd >= 0)
      {
        signVsp = 1;
      } else
      {
        signVsp = -1;
      }
    
      while (world.moveAllowed((int) x, (int) (y + signVsp)))
      {
        y += signVsp;
      }
      
      vspd = 0;
    }
    
    jump = false;
    y += vspd;
    
    if(world.moveAllowed(getX() + speed, getY()))
    {
      x += speed;
    }
    
    Camera.x = getX() - Main.WIDTH / 2;
    
    if(getY() >= Main.HEIGHT)
    {
      Main.getInstance().gameOver();
    }
  }
  
  public void render(Graphics g)
  {
    g.setColor(Color.RED);
    g.fillRect(getX() - Camera.x, getY() - Camera.y, 32, 32);
  }
  
  public int getX()
  {
    return (int) x;
  }
  
  public void setX(int x)
  {
    this.x = x;
  }
  
  public int getY()
  {
    return (int) y;
  }
  
  public void setY(int y)
  {
    this.y = y;
  }
  
  public void jump()
  {
    jump = true;
  }
}
