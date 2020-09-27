package me.giverplay.endlessrunner.world;

import static me.giverplay.endlessrunner.world.World.BLOCK_SIZE;

import java.awt.Color;
import java.awt.Graphics;

public class Block
{
  private int x;
  private int y;
  
  public Block(int x, int y)
  {
    this.x = x;
    this.y = y;
  }
  
  public void render(Graphics g)
  {
    g.setColor(new Color(0, 150, 200));
    g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
    
    g.setColor(Color.BLACK);
    g.drawRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
  }
}
