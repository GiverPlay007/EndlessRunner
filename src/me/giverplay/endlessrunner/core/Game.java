package me.giverplay.endlessrunner.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import me.giverplay.endlessrunner.entity.Player;
import me.giverplay.endlessrunner.world.World;

public class Game
{
  private World world;
  private Player player;
  
  private int points;
  
  protected Game()
  {
    this.world = new World(this);
    this.player = new Player(this, world, 1, Main.HEIGHT - World.BLOCK_SIZE * 5);
    
    new Listeners(this);
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
    
    g.setColor(Color.BLACK);
    g.setFont(new Font("arial", Font.BOLD, 18));
    g.drawString("Pontos: " + points, 6, 18);
  }
  
  public Player getPlayer()
  {
    return player;
  }
  
  public void resetPoints()
  {
    this.points = 0;
  }
  
  public void addPoint(int points)
  {
    this.points += points;
  }
  
  public int getPoints()
  {
    return this.points;
  }
}
