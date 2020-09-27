package me.giverplay.endlessrunner.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listeners implements KeyListener
{
  private Game game;
  
  public Listeners(Game game)
  {
    this.game = game;
    Main.getInstance().addKeyListener(this);
  }
  
  @Override
  public void keyTyped(KeyEvent keyEvent)
  {
  
  }
  
  @Override
  public void keyPressed(KeyEvent e)
  {
    if(e.getKeyCode() == KeyEvent.VK_SPACE)
    {
      if(game.getPlayer() != null)
      {
        game.getPlayer().jump();
      }
    }
  }
  
  @Override
  public void keyReleased(KeyEvent keyEvent)
  {
  
  }
}
