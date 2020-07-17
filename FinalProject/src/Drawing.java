import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.util.Random;
import java.awt.Color;
import java.awt.Font;
public class Drawing extends Canvas {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8464146386181518056L;
	static int width= 2000;
	static int height = 1000;
	static int framecount=0;
	
	
	// now for the things on the list
	
	static Color purple = new Color(128, 0, 128);
	static Color black = new Color(0, 0, 0);
	static Color white = new Color(255, 255, 255);
	static Color yellow = new Color(255, 255, 0);
	static Color teal = new Color(0,255,255);
	static Color test = new Color(255,0,255);
	static int circlecount = width/6;
	
	public static void main(String[] args) {
		JLabel Labelone = new JLabel();
		JLabel UserNameLb = new JLabel();
		JLabel PasswordLb = new JLabel();
		JFrame frameOne = new JFrame();
		JButton newuserbtn = new JButton();
		JButton loginbtn = new JButton();
		JTextField fieldone = new JTextField(100);
		JTextField UserNamefld = new JTextField(100);
		JTextField Passwordfld = new JTextField(100);
		frameOne.getContentPane().setBackground(purple);
        frameOne.setDefaultCloseOperation(frameOne.EXIT_ON_CLOSE);
        frameOne.setTitle("Log in start screen");
        frameOne.setVisible(true);
        Canvas canvas = new Drawing();
        canvas.setSize(width, height);
        //Labelone.setBackground(black);
        //Labelone.setForeground(black);
        Labelone.setText("Log In");
        Labelone.setBounds(width/2-150, height/2-350, 300, 150);
        Labelone.setFont(new Font("Courier", Font.BOLD,75));
        
        UserNameLb.setForeground(white);
        UserNameLb.setOpaque(true);
        UserNameLb.setBackground(black);
        UserNameLb.setText("User Name: ");
        UserNameLb.setBounds(width/2-200, height/2-150, 200, 50);
        UserNameLb.setFont(new Font("Courier", Font.BOLD,20));
        
        PasswordLb.setForeground(white);
        PasswordLb.setOpaque(true);
        PasswordLb.setBackground(black);
        PasswordLb.setText("User Password: ");
        PasswordLb.setBounds(width/2-200, height/2-50, 200, 50);
        PasswordLb.setFont(new Font("Courier", Font.BOLD,20));
        
        
        
        UserNamefld.setBounds(width/2, height/2-150, 200,50 );
        UserNamefld.setBackground(teal);
        UserNamefld.setText("Enter User Name Here");
        Border border = BorderFactory.createLineBorder(Color.ORANGE);
        UserNamefld.setBorder(border);
        
        Passwordfld.setBounds(width/2, height/2-50, 200,50 );
        Passwordfld.setBackground(teal);
        Passwordfld.setText("Enter Password Here");
        Border border2 = BorderFactory.createLineBorder(Color.ORANGE);
        Passwordfld.setBorder(border2);
        
        
        
        newuserbtn.setText("Dont't Have a Log In?");
        newuserbtn.setBounds(width/2,height/2+50, 200, 50);
        newuserbtn.setBackground(yellow);
       // addWindowListener(this);
        newuserbtn.addActionListener(new ActionListener(){  
        	public void actionPerformed(ActionEvent e){ 
        		frameOne.remove(UserNamefld);
                frameOne.remove(Passwordfld);
                frameOne.remove(UserNameLb);
                frameOne.remove(PasswordLb);
                frameOne.remove(loginbtn);
                frameOne.remove(newuserbtn);
                frameOne.remove(canvas);
        	//frameOne.remove(canvas);
        	newuser(frameOne);
             //.setVisible(false);
            }  
        });  
       
        loginbtn.setText("LOG IN");
        loginbtn.setBounds(width/2-200,height/2+50, 200, 50);
        loginbtn.setBackground(test);
       // addWindowListener(this);
        loginbtn.addActionListener(new ActionListener(){  
        	public void actionPerformed(ActionEvent e){  
             frameOne.setVisible(false);
            }  
        });  
        
        
        frameOne.add(UserNamefld);
        frameOne.add(Passwordfld);
        frameOne.add(UserNameLb);
        frameOne.add(PasswordLb);
        frameOne.add(loginbtn);
        frameOne.add(newuserbtn);
        frameOne.add(canvas);
       
        frameOne.pack();
        frameOne.setVisible(true);
        
       
       
        
    }
	 @Override
    public void paint(Graphics g) {
       
       Random rand = new Random();
       for(int i=0; i< circlecount;i++) {
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(rand.nextInt(width), rand.nextInt(height), rand.nextInt(100), rand.nextInt(100));
       }
       if(framecount==0) {
    	   g.setColor(black);	
           g.fillOval(width/2-400, height/2-200, 800, 400);
       }
       
       
       //g.fillOval(, 100, 50, 50);
      
       
       
    }
	 
	 public static void newuser(JFrame inputframe) {
		 
		 inputframe.getContentPane().setBackground(purple);
		 inputframe.setDefaultCloseOperation(inputframe.EXIT_ON_CLOSE);
		 inputframe.setTitle("Log in start screen");
		 inputframe.setVisible(true);
		 	framecount=1;
	        Canvas canvas = new Drawing();
	        canvas.setSize(width, height);
	        inputframe.add(canvas);
	        
	        inputframe.pack();
	        inputframe.setVisible(true);
	 }
	 
}
