/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

import javax.management.ListenerNotFoundException;
import javax.xml.bind.Marshaller.Listener;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
/** Animation delay or pause time between ball moves */ 
	private static final int DELAY = 50; 
	
	
	private double vx, vy;
	private GOval ball;
	private GRect bricks;
	private GRect paddle;

/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		createGame();
		runGame();
	}
	
	private void runGame() {
		vy = 3.0;
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) vx = -vy;
		
		bricksGameplay();
		movePaddle();
		while (ball.getX() < getWidth()){
			while ( ball.getY() < getHeight()) {
				moveBall();
				checkIfInBounds();
//				checkForCollision();
				pause(DELAY);	
			}
		}	
	}
//	
//	private GObject checkForCollision{
//		
//	}
	
	private void checkIfInBounds() {
		
		/* Y collision right side */
		if (ball.getY() > getHeight() - BALL_RADIUS) {
			vy = -vy;
			}
		
		/* X collision right side */
		else if (ball.getX() > getWidth() - BALL_RADIUS) {
			vx = -vx;
		}
		
		/* Y collision left side */
		else if (ball.getY() < BALL_RADIUS - BALL_RADIUS) {
		vy = -vy;
		}	
		/* X collision left side */
		else if (ball.getX() < BALL_RADIUS - BALL_RADIUS) {
			vx = -vx;
		}
			
	}
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private void moveBall() {
			ball.move(vx,vy);
	}
	
	private void movePaddle() {
		
	}
	
	private void bricksGameplay() {
		// TODO Add collision between balls and bricks and the gameplay stuff

	}
	
	private void createGame() {
		// TODO Auto-generated method stub
		createBricks();
		createPaddle();
		createBall();
	}
	
	private void createBall() {
		ball = new GOval(getWidth() / 2 - BALL_RADIUS / 2, getHeight() / 2, BALL_RADIUS, BALL_RADIUS);
		ball.setFilled(true);
		ball.setColor(Color.black);
		add(ball);
	}
	
	/** Creates the paddle and centeres it with the correct ofset in the bottom */
	private void createPaddle() {
		paddle = new GRect(getWidth() / 2 - PADDLE_WIDTH / 2, getHeight()- PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setColor(Color.black);
		add(paddle);
	}
	
	/** This method creates the bricks you see on the screen */
	private void createBricks() {
		int bricks = 0; // How many bricks exist on the current row
		int rows = 1; // How many rows there are
		int x = BRICK_WIDTH + BRICK_SEP; // Where to start drawing the brick on the x-axis
		int y = BRICK_HEIGHT + BRICK_SEP; // Where to start drawing the brick on the y-axis
		Color brickColor = Color.red; // The color of the brick being drawin
		
		/* Draws the bricks to the canvas. It calculates the position and color of the brick
		 * depending on how many rows there are, and how many bricks there are on that row. */
		while (rows < NBRICK_ROWS) {
			for (int i = 0; i < NBRICKS_PER_ROW; i++) {	
				if (bricks == 10) {
					rows++;
					bricks = 0;
				}
				
				/* Decides the color of the row currently being drawn */
				if (rows <= 2) {
					brickColor = Color.red;
				}
					else if (rows > 2 && rows <= 4) {
						brickColor = Color.yellow;
					}
					else if (rows > 4 && rows <= 6) {
						brickColor = Color.cyan;
					}
					else if (rows > 6 && rows <= 8) {
						brickColor = Color.magenta;
					}
					else if (rows > 8 && rows <= 10) {
						brickColor = Color.black;
					}

				/* Draws bricks out to the canvas until the max value us met.
				 * It then resets to start on a new row. */
				if (bricks <= NBRICKS_PER_ROW) {
					add(createBricks(x*i, y*rows, BRICK_WIDTH, BRICK_WIDTH, brickColor));
					bricks++;
				}
			}		
		}
	}

    /** Here a rectangle used as the bricks is being made. */
	
	/* It awaits specifications on how to look from when
	 * it is being called and added to the canvas */ 
	private GRect createBricks(int x, int y, int w, int h, Color brickColor) {
		bricks = new GRect(x + BRICK_SEP, y + BRICK_Y_OFFSET, BRICK_WIDTH, BRICK_HEIGHT);
		bricks.setFilled(true);
		bricks.setColor(brickColor);
		return bricks;
	}

}
