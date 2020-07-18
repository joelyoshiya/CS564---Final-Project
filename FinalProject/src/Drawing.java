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
	static Color grey = new Color(100,100,100);
	static int circlecount = width/6;

	// MAIN
	public static void main(String[] args) {
		JFrame frameOne = new JFrame();
		loginscreen(frameOne);
		//Next screen to navigate to
		//New user screen?
    }
	 @Override
	 /**
	  *
	  */
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
       else if(framecount == 1) {
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(10, 150, width/5, height-300);
    	   g.fillOval(2*width/5+ 10, 150, width/5, height-300);
    	   g.fillOval(4*width/5+ 10, 150, width/5, height-300);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(1*width/5+ 10, 150, width/5, height-300);
    	   g.fillOval(3*width/5+ 10, 150, width/5, height-300);
       }else if(framecount == 2){

	   }
       //g.fillOval(, 100, 50, 50);
       
    }

	/**
	 *
	 * @param inputframe
	 */
	 public static void newuser(JFrame inputframe) {
		 Random rand = new Random();
		 inputframe.getContentPane().setBackground(purple);
		 inputframe.setDefaultCloseOperation(inputframe.EXIT_ON_CLOSE);
		 inputframe.setTitle("Log in start screen");
		 inputframe.setVisible(true);
		 JLabel UserNameLb = new JLabel();
		 JLabel PasswordLb = new JLabel();
		 JLabel Newuserinfor = new JLabel();
		 JLabel likedMovielb = new JLabel();
		 JLabel favMovielb = new JLabel();
		 JLabel likedactorslb = new JLabel();
		 JLabel favactorslb = new JLabel();

		 	framecount=1;
	        Canvas canvas = new Drawing();
	        canvas.setSize(width, height);
	        
	        
	        
	        Newuserinfor.setForeground(white);
	        Newuserinfor.setOpaque(true);
	        Newuserinfor.setBackground(grey);
	        Newuserinfor.setText("Enter In New User INFO");
	        Newuserinfor.setBounds(110, 200, 200, 50);
	        Newuserinfor.setFont(new Font("Courier", Font.BOLD,15));
	        
	        likedMovielb.setForeground(white);
	        likedMovielb.setOpaque(true);
	        likedMovielb.setBackground(grey);
	        likedMovielb.setText("Liked Movies");
	        likedMovielb.setBounds(width/5+110, 200, 200, 50);
	        likedMovielb.setFont(new Font("Courier", Font.BOLD,15));
	        
	        favMovielb.setForeground(white);
	        favMovielb.setOpaque(true);
	        favMovielb.setBackground(grey);
	        favMovielb.setText("Favorite Movies");
	        favMovielb.setBounds(2*width/5+110, 200, 200, 50);
	        favMovielb.setFont(new Font("Courier", Font.BOLD,15));
	        
	        likedactorslb.setForeground(white);
	        likedactorslb.setOpaque(true);
	        likedactorslb.setBackground(grey);
	        likedactorslb.setText("Liked Actors");
	        likedactorslb.setBounds(3*width/5+110, 200, 200, 50);
	        likedactorslb.setFont(new Font("Courier", Font.BOLD,15));
	        
	        favactorslb.setForeground(white);
	        favactorslb.setOpaque(true);
	        favactorslb.setBackground(grey);
	        favactorslb.setText("Favorite Actors");
	        favactorslb.setBounds(4*width/5+110, 200, 200, 50);
	        favactorslb.setFont(new Font("Courier", Font.BOLD,15));
	        

	        //************* this is the
	        UserNameLb.setForeground(white);
	        UserNameLb.setOpaque(true);
	        UserNameLb.setBackground(grey);
	        UserNameLb.setText("User Name: ");
	        UserNameLb.setBounds(width/2-200, height/2-150, 200, 50);
	        UserNameLb.setFont(new Font("Courier", Font.BOLD,15));
	        PasswordLb.setForeground(white);
	        PasswordLb.setOpaque(true);
	        PasswordLb.setBackground(grey);
	        PasswordLb.setText("User Password: ");
	        PasswordLb.setBounds(width/2-200, height/2-50, 200, 50);
	        PasswordLb.setFont(new Font("Courier", Font.BOLD,15));
	        
	        
	        
	        
	        inputframe.add(PasswordLb);
	        inputframe.add(Newuserinfor);
	        inputframe.add(UserNameLb);
	        inputframe.add(likedMovielb);
	        inputframe.add(favMovielb);
	        inputframe.add(favMovielb);
	        inputframe.add(likedactorslb);
	        inputframe.add(favactorslb);
	        inputframe.add(canvas);
	        
	        inputframe.pack();
	        inputframe.setVisible(true);
	 }

	/**
	 *
	 * @param inputframe
	 */
	 public static void loginscreen(JFrame inputframe) {
		 	framecount =0;
		 	JLabel Labelone = new JLabel();
			JLabel UserNameLb = new JLabel();
			JLabel PasswordLb = new JLabel();
			
			JButton newuserbtn = new JButton();
			JButton loginbtn = new JButton();
			JTextField fieldone = new JTextField(100);
			JTextField UserNamefld = new JTextField(100);
			JTextField Passwordfld = new JTextField(100);
			inputframe.getContentPane().setBackground(purple);
			inputframe.setDefaultCloseOperation(inputframe.EXIT_ON_CLOSE);
			inputframe.setTitle("Log in start screen");
			inputframe.setVisible(true);
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
	        		inputframe.remove(UserNamefld);
	        		inputframe.remove(Passwordfld);
	        		inputframe.remove(UserNameLb);
	        		inputframe.remove(PasswordLb);
	        		inputframe.remove(loginbtn);
	        		inputframe.remove(newuserbtn);
	        		inputframe.remove(canvas);
	        		newuser(inputframe);
	            }  
	        });  
	       
	        loginbtn.setText("LOG IN");
	        loginbtn.setBounds(width/2-200,height/2+50, 200, 50);
	        loginbtn.setBackground(test);
	       // addWindowListener(this);
	        loginbtn.addActionListener(new ActionListener(){  
	        	public void actionPerformed(ActionEvent e){  
	        		inputframe.setVisible(false);
	            }  
	        });  
	        
	        
	        inputframe.add(UserNamefld);
	        inputframe.add(Passwordfld);
	        inputframe.add(UserNameLb);
	        inputframe.add(PasswordLb);
	        inputframe.add(loginbtn);
	        inputframe.add(newuserbtn);
	        inputframe.add(canvas);
	       
	        inputframe.pack();
	        inputframe.setVisible(true);

	 }

	/**
	 * Method to plot geographical points on a map image given latitude/longitude values
	 * Input should be a set of doubles for each person, then each person mapped
	 * @param input - empty frame, get latitude/longitude info from MySQL and plot a point on a map image
	 * @return -1 if back button pushed, else it doesn't really matter
	 */
	 int mapFrame(JFrame input, double[][] coordinates){
		 //distinct frame count for the mapFrame
	 	framecount = 2;

	 	//Assuming the input double array, coordinates, has a 0 index containing latitude
		//And corresponding longitude in the 1 index
	 	double[] x_coord = new double[coordinates[0].length];
	 	double[] y_coord = new double[coordinates[1].length];

	 	double[][] x_yVals = convertDegreesToCartesian(coordinates);

	 	for(int i = 0; i < x_yVals.length; i++){
	 		for(int j = 0; j < x_yVals[i].length; j++){
	 			if(i == 0){
	 				x_coord[j] = x_yVals[i][j];
				}else if(i == 1){
	 				y_coord[j] = x_yVals[i][j];
				}
			}
		}
	 	//NOW have all x and y coordinates

	 	return -1;
	 }

	/**
	 * Converts the Latitude/Longitude coordinates into Cartesian X and Y values
	 * That will be used to plot points on a Cartesian map of the world in mapFrame()
	 * @param coordinates
	 * @return
	 */
	 double[][] convertDegreesToCartesian(double[][] coordinates){
	 	//TODO fully implement
		 double[][] x_yVals = new double[coordinates.length][coordinates[0].length];
		 //Now convert latitude/longitude into a corresponding X and Y value
		 for(int i = 0; i < coordinates.length; i++){
		 	for(int j = 0; j < coordinates[i].length; j++){
		 		//Do something, write values
				if(i == 0){//X Value
					x_yVals[i][j] = 0;// TODO CALCULATE for the X value
				}else if(i == 1){//Y value
					x_yVals[i][j] = 0;// TODO CALCULATE for the Y value
				}
			}
		 }
		 return x_yVals;
	 }

	 
}
