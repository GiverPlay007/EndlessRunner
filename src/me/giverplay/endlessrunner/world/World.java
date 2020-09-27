package me.giverplay.endlessrunner.world;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import me.giverplay.endlessrunner.Game;
import me.giverplay.endlessrunner.Main;

public class World
{
  public static final int BLOCK_SIZE = 32;
  
  private static final Random random = new Random();
  
  private List<Block> blocks = new ArrayList<>();
  private Game game;
  
  private int lastWidth;
  private int lastPos;
  
  public World(Game game)
  {
    this.game = game;
  }
  
  public void tick()
  {
    if(game.getPlayer().getX() >= lastPos)
    {
      int wid = random.nextInt(4) + 4;
      lastPos = lastPos + (lastPos + lastWidth != 0 ? (random.nextInt(3) +1) * 32 : 0) + lastWidth * BLOCK_SIZE;
      lastWidth = wid;
      generateBlocks(wid);
    }
  }
  
  public void render(Graphics g)
  {
    blocks.forEach(e -> e.render(g));
  }
  
  public void generateBlocks(int width)
  {
    for(int i = 0; i < width; i++)
    {
      blocks.add(new Block(lastPos + (i * BLOCK_SIZE), Main.HEIGHT - BLOCK_SIZE));
    }
  }
}
