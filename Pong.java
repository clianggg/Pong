import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Pong implements ActionListener, MouseMotionListener, KeyListener{ 
	
	JFrame frame;
	GamePanel panel;
	Timer timer;
	
	public void actionPerformed(ActionEvent evt){ 
		
		if(evt.getSource() == timer){ 
			panel.repaint(); 
		}
	}
	
	public void mouseMoved(MouseEvent evt){ 
		
		if(evt.getY() > 0 && evt.getY() < 300){
			panel.intp1y = evt.getY();
		}
	}
	public void mouseDragged(MouseEvent evt){
		
	}

	public void keyReleased(KeyEvent evt){
		
	}
	public void keyPressed(KeyEvent evt){
		
	}
	public void keyTyped(KeyEvent evt){ 
		
		if(evt.getKeyChar() == 'w' && panel.intp2y > 0){ 
			panel.intp2y = panel.intp2y - 10;
			
		}else if(evt.getKeyChar() == 's' && panel.intp2y+100 <= 390){ 
			panel.intp2y = panel.intp2y + 10;
		}
	}
	
	public Pong(){
		
		frame = new JFrame("Pong");
		panel = new GamePanel(); 
		panel.setPreferredSize(new Dimension(800,400)); 
		panel.addMouseMotionListener(this); 
	
		frame.addKeyListener(this); 
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false); 
		frame.setVisible(true);
		
		timer = new Timer(1000/60,this); 
		timer.start(); 
	}
	
	//Main Method
	public static void main(String[] args){
		new Pong(); 
	} 
}
