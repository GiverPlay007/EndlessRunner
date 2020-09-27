package me.giverplay.endlessrunner.entity;

import java.awt.Color;
import java.awt.Graphics;

public class Player
{
  private int x;
  private int y;
  
  public Player(int x, int y)
  {
    this.x = x;
    this.y = y;
  }
  
  public void tick()
  {
    x++;
  }
  
  public void render(Graphics g)
  {
    g.setColor(Color.RED);
    g.fillRect(x, y, 32, 32);
  }
  
  public int getX()
  {
    return x;
  }
  
  public void setX(int x)
  {
    this.x = x;
  }
  
  public int getY()
  {
    return y;
  }
  
  public void setY(int y)
  {
    this.y = y;
  }
}
