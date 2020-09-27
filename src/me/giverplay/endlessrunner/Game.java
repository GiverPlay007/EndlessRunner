package me.giverplay.endlessrunner;

import java.awt.Graphics;
import me.giverplay.endlessrunner.entity.Player;
import me.giverplay.endlessrunner.world.World;

public class Game
{
  private World world;
  private Player player;
  
  protected Game()
  {
    this.world = new World(this);
    this.player = new Player(0, Main.HEIGHT - World.BLOCK_SIZE * 2);
  }
  
  protected void tick()
  {
    world.tick();
    player.tick();
  }
  
  protected void render(Graphics g)
  {
    world.render(g);
    player.render(g);
  }
  
  public Player getPlayer()
  {
    return player;
  }
}
