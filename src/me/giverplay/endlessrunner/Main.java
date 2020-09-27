package me.giverplay.endlessrunner;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;

public class Main extends Canvas implements Runnable
{
  public static final int WIDTH = 640;
  public static final int HEIGHT = 480;
  
  private Graphics graphics;
  private Game game;
  
  private boolean running;
  
  private int ticks = 0;
  private int fps = 0;
  
  public static void main(String[] args)
  {
    new Main();
  }
  
  private Main()
  {
    game = new Game();
    createFrame();
    
    new Thread(this).start();
  }
  
  private void createFrame()
  {
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    
    JFrame frame = new JFrame("Endless Runner");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.add(this);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    
    createBufferStrategy(3);
    graphics = getBufferStrategy().getDrawGraphics();
  }
  
  private void tick()
  {
    game.tick();
  }
  
  private void render()
  {
    graphics.setColor(Color.BLACK);
    graphics.fillRect(0, 0, WIDTH, HEIGHT);
    
    game.render(graphics);
    
    graphics.setFont(new Font("Arial", Font.PLAIN, 12));
    graphics.setColor(Color.WHITE);
    graphics.drawString("TICKS: " + ticks, 0, 16);
    graphics.drawString("FPS: " + fps, 0, 34);
    
    getBufferStrategy().show();
  }
  
  @Override
  public void run()
  {
    running = true;
    
    long timer = System.currentTimeMillis();
    long lastTime = System.nanoTime();
    long now;
    
    double nsTick = 1_000_000_000 / 60.0D;
    double unprocessed = 0.0D;
    
    int currentFps = 0;
    int currentTicks = 0;
    
    while(running)
    {
      now = System.nanoTime();
      unprocessed += (now - lastTime) / nsTick;
      lastTime = now;
      
      while(unprocessed >= 1)
      {
        tick();
        currentTicks++;
        unprocessed--;
      }
      
      render();
      currentFps++;
  
      if(System.currentTimeMillis() - timer >= 1000)
      {
        this.ticks = currentTicks;
        this.fps = currentFps;
        currentTicks = 0;
        currentFps = 0;
        timer += 1000;
      }
    }
  }
}
