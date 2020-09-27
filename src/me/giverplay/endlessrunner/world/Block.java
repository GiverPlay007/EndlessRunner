package me.giverplay.endlessrunner.world;

import static me.giverplay.endlessrunner.world.World.BLOCK_SIZE;

import java.awt.Graphics;
import me.giverplay.endlessrunner.core.Main;

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
    g.drawImage(Main.STONE, x - Camera.x, y - Camera.y, BLOCK_SIZE, BLOCK_SIZE, null);
  }
  
  public int getX()
  {
    return x;
  }
  
  public int getY()
  {
    return y;
  }
}
