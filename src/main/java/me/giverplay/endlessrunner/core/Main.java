package me.giverplay.endlessrunner.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main extends Canvas implements Runnable
{
  public static final int WIDTH = 640;
  public static final int HEIGHT = 480;
  
  public static BufferedImage PLAYER;
  public static BufferedImage STONE;
  
  private static Main instance;
  
  private Graphics graphics;
  private Game game;
  
  private boolean running;
  
  private int ticks = 0;
  private int fps = 0;
  
  public static void main(String[] args)
  {
    new Main();
  }
  
  public static Main getInstance()
  {
    return instance;
  }
  
  private Main()
  {
    instance = this;
    game = new Game();
    createFrame();
    
    new Thread(this).start();
    requestFocus();
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
    graphics.setColor(new Color(0xFF0099FF));
    graphics.fillRect(0, 0, WIDTH, HEIGHT);
    
    game.render(graphics);
    
    graphics.setFont(new Font("Arial", Font.BOLD, 12));
    graphics.setColor(Color.WHITE);
    graphics.drawString("TICKS: " + ticks, 4, HEIGHT - 16);
    graphics.drawString("FPS: " + fps, 4, HEIGHT - 2);
    
    getBufferStrategy().show();
  }
  
  public void gameOver()
  {
    game = new Game();
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
  
  static
  {
    try
    {
      STONE = ImageIO.read(Main.class.getResource("/Stone.png"));
      PLAYER = ImageIO.read(Main.class.getResource("/Player.png"));
    }
    catch(IOException e)
    {
      e.printStackTrace();
      System.exit(1);
    }
  }
}
