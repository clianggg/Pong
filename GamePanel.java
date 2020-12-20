import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class GamePanel extends JPanel implements ActionListener{ 

	int intScore1 = 0;
	int intScore2 = 0;
	int intp1x = 20;
	int intp1y = 150;
	int intp2x = 760;
	int intp2y = 150;
	int intBallX = 400;
	int intBallY = 200;
	int intSpeedX = 3;
	int intSpeedY = 3;
	JButton replay;
	BufferedImage ball = null;
	BufferedImage paddle = null;

	public void actionPerformed(ActionEvent evt){ 
		
		if(evt.getSource() == replay){ 
			restart(); 
			replay.setVisible(false);
		}
	}
	
	public void paintComponent(Graphics g){ 
		
		//Drawing Components
		super.paintComponent(g); 
		g.drawImage(paddle,intp1x,intp1y,null); 
		g.drawImage(paddle,intp2x,intp2y,null); 
		g.drawImage(ball,intBallX,intBallY,null); 
		g.drawString(""+intScore1,300,20); 
		g.drawString(""+intScore2,480,20); 
		g.drawLine(400,0,400,400); 
		
		checkCollision(); 
		updateScore();
		
		if(intScore1 == 10){ //if player 1 wins
			g.drawString("WIN!",260,20); 
			intSpeedX = 0; 
			intSpeedY = 0;
			replay.setVisible(true); 
						
		}else if(intScore2 == 10){ //if player 2 wins
			g.drawString("WIN!",500,20); 
			intSpeedX = 0;
			intSpeedY = 0;
			replay.setVisible(true); 
		}
		
		//update ball x and y position
		intBallX = intBallX - intSpeedX;
		intBallY = intBallY - intSpeedY;
	}

	public void checkCollision(){ //checkCollision method to check if ball hits paddles or walls and needs to bounce
		
		//check if ball hit walls
		if(intBallY < 0 || intBallY > 385){
			intSpeedY *= -1; 
		}
		
		//check if ball hit paddles
		if(intBallX == intp1x+20){ 
			if(intBallY >= intp1y && intBallY <= intp1y+100 ){	
				intSpeedX *= -1; 
			}
			
		}else if(intBallX+15 == intp2x){ 
			if(intBallY >= intp2y && intBallY <= intp2y+100 ){
				intSpeedX *= -1; 
			}
		}
	}
	
	public void updateScore(){ 
		//check if player scored
		if(intBallX <= 0){ 
			intBallX = 400;
			intBallY = 200;
			intScore2++;
		}else if(intBallX >= 800){ 
			intBallX = 400;
			intBallY = 200;
			intScore1++;
		}
	}
	
	public void restart(){ 
		//reset all game variable values
		intScore1 = 0;
		intScore2 = 0;
		intp1y = 150;
		intp1x = 20;
		intp2y = 150;
		intp2x = 760;
		intBallX = 400;
		intBallY = 200;
		intSpeedX = 3;
		intSpeedY = 3;
	}

	public GamePanel(){
		super();
		replay = new JButton("replay"); 
		replay.addActionListener(this); 
		replay.setFocusable(false); 
		replay.setVisible(false); 
		super.add(replay); 
		
		try{ 
			ball = ImageIO.read(new File("ball.png"));
			paddle = ImageIO.read(new File("bill.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}
		
	}
}
