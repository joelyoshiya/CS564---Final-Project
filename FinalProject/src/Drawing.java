import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.Border;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
public class Drawing extends Canvas {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8464146386181518056L;
	static int width= 2000;
	static int height = 1000;
	static int framecount=0;
	static boolean favoritmoviefound = false;
	static boolean favoritactorfound = false;
	static boolean backbtnpushed = false;
	static boolean usercreated = false;
	private static boolean settingchange = false;
	private static TestJDBC Database = new TestJDBC();
	private static ArrayList<Pair> searchMovies = new ArrayList<Pair>();
	private static ArrayList<Pair> searchPeople = new ArrayList<Pair>();
	private static ArrayList<Double> longcord = new ArrayList<Double>();
	private static ArrayList<Double> latcord = new ArrayList<Double>();
	private static ArrayList<Object> movieInfo = new ArrayList<Object>();
	private static String currUser;
	private static String currPassword;
	
	// now for the things on the list
	
	static Color purple = new Color(128, 0, 128);
	static Color black = new Color(0, 0, 0);
	static Color white = new Color(255, 255, 255);
	static Color yellow = new Color(255, 255, 0);
	static Color teal = new Color(0,255,255);
	static Color test = new Color(255,0,255);
	static Color grey = new Color(100,100,100);
	static int circlecount = width/6;
	
	public static void main(String[] args) {
		JFrame frameOne = new JFrame();
		Database.Connection();
		loginscreen(frameOne);
		
        
    }
	 @Override
    public void paint(Graphics g) {
		 Random rand = new Random();
       if(framecount !=2) {
           for(int i=0; i< circlecount;i++) {
        	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
        	   g.fillOval(rand.nextInt(width), rand.nextInt(height), rand.nextInt(100), rand.nextInt(100));
           }
       }
       
       if(framecount==0) {
    	   g.setColor(black);	
           g.fillOval(width/2-400, height/2-200, 800, 400);
       }
       else if(framecount == 1) {
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(0, 150, width/5-20, height-300);
    	   g.fillOval(2*width/5-32, 150, width/5-20, height-300);
    	   g.fillOval(4*width/5-70, 150, width/5-20, height-300);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(1*width/5-17, 150, width/5-20, height-300);
    	   g.fillOval(3*width/5-50, 150, width/5-20, height-300);
       }
       else if(framecount==2) {//For the mapFrame
    	   Toolkit t=Toolkit.getDefaultToolkit(); 
    	   Image i=t.getImage("map2.png"); 
    	   g.drawImage(i, 0, 0, width, height, this);
    	   for(int j=0; j<longcord.size();j++) {
    		   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    		   if(j<10) {
    			   g.fillOval((int) Math.round(longcord.get(j)), (int) Math.round(latcord.get(j)), 50/(j+1), 50/(j+1));
    		   }else {
    			   g.fillOval((int) Math.round(longcord.get(j)), (int) Math.round(latcord.get(j)), 10, 10);
    		   }
    		  
    	   }
    	   
           
         //  g.drawImage(i, 120,100,this);  
       }else if(framecount==3) {//login user frame
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(0, 150, width/5-20, height-300);
    	   g.fillOval(2*width/5-32, 150, width/5-20, height-300);
    	   g.fillOval(4*width/5-70, 150, width/5-20, height-300);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(1*width/5-17, 150, width/5-20, height-300);
    	   g.fillOval(3*width/5-50, 150, width/5-20, height-300);
       }else if(framecount==4) {//For the movie specifics (show all tuple info)
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(0, 150, width/5-20, height-300);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(1*width/5-17, 150, width/5-20, height-300);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(2*width/5-32, 150, width/10-20, width/10-20);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(2*width/5-32,height/2-150, width/10-20, height-320-width/10);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(2*width/5-32+width/10, 150, width/10-20, width/10-20);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(2*width/5-32+width/10, 130+width/10, width/10-20, width/10-20);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(2*width/5-32+width/10, 110+width/5, width/10-20, width/10-20);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(2*width/5-32+width/10, 3*width/10+90, width/10-20, width/10-20);

    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(23*width/40, 150, width/10-20, width/10-20);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(23*width/40, 130+width/10, width/10-20, width/10-20);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(23*width/40, 110+width/5, width/10-20, width/10-20);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(23*width/40, 3*width/10+90, width/10-20, width/10-20);

       }else if(framecount == 5){//User Settings page

    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(2*width/5-32, 150, width/5-20, height-300);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(1*width/5-17, 150, width/5-20, height-300);
    	   g.fillOval(3*width/5-50, 150, width/5-20, height-300);
       }
       
       //g.fillOval(, 100, 50, 50);
      
       
       
    }
	 
	 public static void newuser(JFrame inputframe) {
		 Random rand = new Random();
		 inputframe.getContentPane().setBackground(purple);
		 inputframe.setDefaultCloseOperation(inputframe.EXIT_ON_CLOSE);
		 inputframe.setTitle("Log in start screen");
		 inputframe.setVisible(true);
		 JLabel UserNameLb = new JLabel();
		 JLabel PasswordLb = new JLabel();
		 JLabel UserrealNameLb = new JLabel();
		 JLabel UserAgeLb = new JLabel();
		 JLabel Newuserinfor = new JLabel();
		 JLabel likedMovielb = new JLabel();
		 JLabel favMovielb = new JLabel();
		 JLabel likedactorslb = new JLabel();
		 JLabel favactorslb = new JLabel();
		 JTextField UserNamefld = new JTextField(100);
		 JTextField Passwordfld = new JTextField(100);
		 JTextField UserRealNamefld = new JTextField(100);
		 JTextField UserAgefld = new JTextField(100);
		 JTextField Searchmoviefld = new JTextField(100);
		 JButton searchmoviebtn = new JButton();
		 JLabel searchedformovieslb = new JLabel();
		 JLabel likedmoviedlb = new JLabel();
		 
		 JLabel likedmovielb2 = new JLabel();
		 JLabel favoritmovelb = new JLabel();
		 
		 JList  listliked = new JList();
	     Vector dataliked = new Vector();
	     
	     JList  listliked2 = new JList();
	     Vector dataliked2 = new Vector();
	     
	     JList  listfavoritmovie = new JList();
	     Vector datafavoritMovie = new Vector();
	     
	     
	     JList  list = new JList();
	     Vector data = new Vector();
	     list.setSelectedIndex(0);
	     
	     JScrollPane serchedmovies = new JScrollPane(list);
	     JScrollPane likedmovies = new JScrollPane(listliked);
	     JScrollPane likedmovies2 = new JScrollPane(listliked2);
	     JScrollPane favoritmovies = new JScrollPane(listfavoritmovie);
	     // starting actors information
	     JLabel searchedforactorslb = new JLabel();
		 JLabel likedactorslb1 = new JLabel();
		 
		 JButton searchactorbtn = new JButton();
		 JButton instructionsbt1 = new JButton();
		 JButton instructionsbt2 = new JButton();
		 JButton instructionsbt3 = new JButton();
		 JButton instructionsbt4 = new JButton();
		 JButton instructionsbt5 = new JButton();
		 
		 JTextField Searchactorfld = new JTextField(100);
		 JList listsearchedactors = new JList();
		 JList listlikeactorsone = new JList();
		 JList listlikeactorstwo = new JList();
		 JList listfavoritactors = new JList();
		 
		 Vector dataserchedactors = new Vector();
		 Vector datalikedactorsone = new Vector();
		 Vector datalikedactorstwo = new Vector();
		 Vector datafavoritactors = new Vector();
		 
		 JScrollPane searchedactors = new JScrollPane(listsearchedactors);
	     JScrollPane likedactorsone = new JScrollPane(listlikeactorsone);
	     JScrollPane likedactorstwo = new JScrollPane(listlikeactorstwo);
	     JScrollPane favoritactors = new JScrollPane(listfavoritactors);
		 
	     JLabel likedactorlbtwo = new JLabel();
		 JLabel favoreteactorlb = new JLabel();
	     
		 JButton submitbtn = new JButton();
		 JButton backbtn = new JButton();
	     
		 	framecount=1;
	        Canvas canvas = new Drawing();
	        canvas.setSize(width, height);
	        
	        
	        submitbtn.setText("Submit");
	        submitbtn.setBounds(width/2-180, height-150, 300, 100);
	        submitbtn.setBackground(yellow);
	        submitbtn.setFont(new Font("Courier", Font.BOLD,45));
	        
	        backbtn.setText("Back");
	        backbtn.setBounds(30, 30, 150, 100);
	        backbtn.setBackground(yellow);
	        backbtn.setFont(new Font("Courier", Font.BOLD,45));
	        
	        instructionsbt1.setText("Instructions");
			instructionsbt1.setBounds(90, 750, 200, 50);
			instructionsbt1.setBackground(yellow);
			instructionsbt1.setFont(new Font("Courier", Font.BOLD,12));
			
			instructionsbt2.setText("Instructions");
			instructionsbt2.setBounds(width/5+75, 750, 200, 50);
			instructionsbt2.setBackground(yellow);
			instructionsbt2.setFont(new Font("Courier", Font.BOLD,15));
			
			instructionsbt3.setText("Instructions");
			instructionsbt3.setBounds(2*width/5+60, 750, 200, 50);
			instructionsbt3.setBackground(yellow);
			instructionsbt3.setFont(new Font("Courier", Font.BOLD,15));
			
			instructionsbt4.setText("Instructions");
			instructionsbt4.setBounds(3*width/5+40, 750, 200, 50);
			instructionsbt4.setBackground(yellow);
			instructionsbt4.setFont(new Font("Courier", Font.BOLD,15));
			
			instructionsbt5.setText("Instructions");
			instructionsbt5.setBounds(4*width/5+20, 750, 200, 50);
			instructionsbt5.setBackground(yellow);
			instructionsbt5.setFont(new Font("Courier", Font.BOLD,15));
	        
	        Newuserinfor.setForeground(white);
	        Newuserinfor.setOpaque(true);
	        Newuserinfor.setBackground(grey);
	        Newuserinfor.setText("Enter In New User INFO");
	        Newuserinfor.setBounds(90, 230, 200, 50);
	        Newuserinfor.setFont(new Font("Courier", Font.BOLD,15));
	        
	        likedMovielb.setForeground(white);
	        likedMovielb.setOpaque(true);
	        likedMovielb.setBackground(grey);
	        likedMovielb.setText("    Liked Movies");
	        likedMovielb.setBounds(width/5+75, 230, 200, 50);
	        likedMovielb.setFont(new Font("Courier", Font.BOLD,15));
	        
	        favMovielb.setForeground(white);
	        favMovielb.setOpaque(true);
	        favMovielb.setBackground(grey);
	        favMovielb.setText("   Favorite Movies");
	        favMovielb.setBounds(2*width/5+60, 230, 200, 50);
	        favMovielb.setFont(new Font("Courier", Font.BOLD,15));
	        
	        likedactorslb.setForeground(white);
	        likedactorslb.setOpaque(true);
	        likedactorslb.setBackground(grey);
	        likedactorslb.setText("    Liked Actors");
	        likedactorslb.setBounds(3*width/5+40, 230, 200, 50);
	        likedactorslb.setFont(new Font("Courier", Font.BOLD,15));
	        
	        favactorslb.setForeground(white);
	        favactorslb.setOpaque(true);
	        favactorslb.setBackground(grey);
	        favactorslb.setText("     Favorite Actors");
	        favactorslb.setBounds(4*width/5+20, 230, 200, 50);
	        favactorslb.setFont(new Font("Courier", Font.BOLD,15));
	        
	        
	        
	        
	        
	        //************* this is the setting up user log in infor tab*******//
	        UserNameLb.setForeground(white);
	        UserNameLb.setOpaque(true);
	        UserNameLb.setBackground(grey);
	        UserNameLb.setText("User Name: ");
	        UserNameLb.setBounds(35, height/2-160, 150, 50);
	        UserNameLb.setFont(new Font("Courier", Font.BOLD,15));
	        
	        PasswordLb.setForeground(white);
	        PasswordLb.setOpaque(true);
	        PasswordLb.setBackground(grey);
	        PasswordLb.setText("User Password: ");
	        PasswordLb.setBounds(35, height/2-60, 150, 50);
	        PasswordLb.setFont(new Font("Courier", Font.BOLD,15));
	        
	        UserrealNameLb.setForeground(white);
	        UserrealNameLb.setOpaque(true);
	        UserrealNameLb.setBackground(grey);
	        UserrealNameLb.setText("User Real Name: ");
	        UserrealNameLb.setBounds(35, height/2+40, 150, 50);
	        UserrealNameLb.setFont(new Font("Courier", Font.BOLD,15));
	        
	        UserAgeLb.setForeground(white);
	        UserAgeLb.setOpaque(true);
	        UserAgeLb.setBackground(grey);
	        UserAgeLb.setText("User Age: ");
	        UserAgeLb.setBounds(35, height/2+140, 150, 50);
	        UserAgeLb.setFont(new Font("Courier", Font.BOLD,15));
	        
	        UserNamefld.setBounds(185, height/2-160, 150,50 );
	        UserNamefld.setBackground(teal);
	        UserNamefld.setText("Enter User Name Here");
	        Border border = BorderFactory.createLineBorder(Color.ORANGE);
	        UserNamefld.setBorder(border);
	        
	        Passwordfld.setBounds(185, height/2-60, 150,50 );
	        Passwordfld.setBackground(teal);
	        Passwordfld.setText("Enter Password Here");
	        Border border2 = BorderFactory.createLineBorder(Color.ORANGE);
	        Passwordfld.setBorder(border2);
	        
	        UserRealNamefld.setBounds(185, height/2+40, 150,50 );
	        UserRealNamefld.setBackground(teal);
	        UserRealNamefld.setText("Enter Real Name Here");
	        
	        
	        UserAgefld.setBounds(185, height/2+140, 150,50 );
	        UserAgefld.setBackground(teal);
	        UserAgefld.setText("Enter Age Here");
	        
	        submitbtn.addActionListener(new ActionListener(){  
	public void actionPerformed(ActionEvent e){ 
	        		//System.out.println("search botton was pushed");
	        		String username = UserNamefld.getText();
	        		String Userpassword = Passwordfld.getText();
	        		String UserRealName = UserRealNamefld.getText();
					String Userage = UserAgefld.getText();
					

					if(!Database.verifyNewUser(username)) {
	        			usercreated = false;
	        			JOptionPane.showMessageDialog(null, "I am sorry, The User Name "+username+""
	    						+ " Has already Been taken, please choose anouther ");
	        			return;
	        		}
	        		
	        		try {
	        			int age = Integer.parseInt(Userage);
	        			System.out.println(username+" "+Userpassword+" "+UserRealName+" "+age);
	        			Database.addNewUser(username, Userpassword,UserRealName,age);
	        			Thread.sleep(2000);
	        			
	        			System.out.println("LIKED MOVIES "+((Pair)dataliked.get(1)).getKey());
	        			
	        			for(int i = 0; i < datalikedactorsone.size();i++) {
	        				Database.addLikedPerson(Userpassword, username, ((Pair)datalikedactorsone.get(i)).getKey());
	        			}
	        			
	        			for(int i = 0; i < dataliked.size();i++) {
	        				Database.addLikedMovie(Userpassword, username, ((Pair)dataliked.get(i)).getKey());
	        			}
	        			
	        			for(int i = 0; i < datafavoritactors.size();i++) {
	        				Database.addFavPerson(Userpassword, username, ((Pair)datafavoritactors.get(i)).getKey());
	        			}
	        			
	        			for(int i = 0; i < datafavoritMovie.size();i++) {
	        				Database.addFavMovie(Userpassword, username, ((Pair)datafavoritMovie.get(i)).getKey());
	        			}
	        			
	        		
	        			
	        			
	        			usercreated = true;
	        		} catch (NumberFormatException ne) {
	        			JOptionPane.showMessageDialog(null, "I am sorry, The age "+Userage+""
								+ " is not an Integer, please try again ");
	        			usercreated = false;
	        			return;
	        		} catch (Exception e1) {
	        			usercreated = false;
	        			return;
	        		}
	        	// this is the lists you need to work with
	        	// if you can not tell what they are by the name just asked me
	        	// note: I only have the print out so the list dont through an error
	        		// this is how you deal with the lists 
	        	//	System.out.println(datalikedactorsone.get(0));
	        		// NAMES:
	        		//datalikedactorsone
	        		//datafavoritactors
	        		//datafavoritMovie
	        		//dataliked ------ this refures to movies
	        		
	        	// return a boolen to false if the user infor allready is in the data base
	            }  
	        });   
	        
	        backbtn.addActionListener(new ActionListener(){  
	        	public void actionPerformed(ActionEvent e){ 
	        		
	        		inputframe.remove(instructionsbt1);
	        		inputframe.remove(instructionsbt2);
	        		inputframe.remove(instructionsbt3);
	        		inputframe.remove(instructionsbt4);
	        		inputframe.remove(instructionsbt5);
	        		inputframe.remove(PasswordLb);
	    	        inputframe.remove(Newuserinfor);
	    	        inputframe.remove(UserNameLb);
	    	        inputframe.remove(likedMovielb);
	    	        inputframe.remove(favMovielb);
	    	        inputframe.remove(favMovielb);
	    	        inputframe.remove(likedactorslb);
	    	        inputframe.remove(favactorslb);
	    	        inputframe.remove(Passwordfld);
	    	        inputframe.remove(UserNamefld);
	    	        inputframe.remove(likedmovies);
	    	        inputframe.remove(serchedmovies);
	    	        inputframe.remove(Searchmoviefld);
	    	        inputframe.remove(searchmoviebtn);
	    	        inputframe.remove(searchedformovieslb);
	    	        inputframe.remove(likedmoviedlb);
	    	        inputframe.remove(likedmovielb2);
	    	        inputframe.remove(favoritmovelb);
	    	        inputframe.remove(likedmovies2);
	    	        inputframe.remove(favoritmovies);
	    	        inputframe.remove(searchedforactorslb);
	    	        inputframe.remove(likedactorslb1);
	    	        inputframe.remove(searchactorbtn);
	    	        inputframe.remove(Searchactorfld);
	    	        inputframe.remove(likedactorsone);
	    	        inputframe.remove(likedactorstwo);
	    	        inputframe.remove(searchedactors);
	    	        inputframe.remove(favoritactors);
	    	        inputframe.remove(likedactorlbtwo);
	    	        inputframe.remove(favoreteactorlb);
	    	        inputframe.remove(UserrealNameLb);
	    	        inputframe.remove(UserAgeLb);
	    	        inputframe.remove(UserRealNamefld);
	    	        inputframe.remove(UserAgefld);
	    	        inputframe.remove(submitbtn);
	    	        inputframe.remove(backbtn);
	    	        
	    	        inputframe.remove(canvas);
	    	        loginscreen(inputframe);
	            }  
	        });  
	        
	        instructionsbt1.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e){ 
					 
					 JOptionPane.showMessageDialog(null, "Type In Username, Password, Real Name, and Age in the corresponding boxes on the right\n"
					 		+ "Note: The age must be an Integer\n"
					 		+ "Once all ovals are filled out click Submitt","Instructions", JOptionPane.INFORMATION_MESSAGE);
					
				 }
			 }); 
	        instructionsbt2.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e){ 
					 
					 JOptionPane.showMessageDialog(null, "TO SEARCH FOR A MOVIE YOU LIKE\n"
					 		+ "1)Type the name of a movie in the search bar on the left\n"
					 		+ "2) Click the Search button\n"
					 		+ "3) The result should appear on the list on the left. If it does not, try searching for single words in the movie\n\n"
					 		+ "TO ADD MOVIE TO LIKED LIST\n"
					 		+ "1) Click on the movie in your Searched list(left)\n"
					 		+ "2) A popup will appear to confirm that you wish to add the movie\n\n"
					 		+ "TO REMOVE A MOVIE FROM YOUR LIKED LIST\n"
					 		+ "1) Click on the movie in your liked list(right)\n"
					 		+ "2)A popup will appear to confirm that you wish to remove the movie\\n" ,"Instructions", JOptionPane.INFORMATION_MESSAGE);
					
				 }
			 }); 
	        instructionsbt3.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e){ 
					 
					 JOptionPane.showMessageDialog(null, "TO ADD A MOVIE TO FAVORITE\n"
					 		+ "1) Click on the movie in your liked list(left)\n"
					 		+ "2) A popup will appear to confirm that you wish to add the movie to favorite\n"
					 		+ "3) Note: you are only allowed one favorite\n\n"
					 		+ "TO REMOVE A MOVIE FROM YOUR FAVORITE LIST \n"
					 		+ "1) Click on the movie in your favorite list(right)\n"
					 		+ "2)A popup will appear to confirm that you wish to remove the movie\n" ,"Instructions", JOptionPane.INFORMATION_MESSAGE);
					
				 }
			 }); 
	        instructionsbt4.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e){ 
					 
					 JOptionPane.showMessageDialog(null, "TO SEARCH FOR AN ACTOR YOU LIKE\n"
					 		+ "1)Type the name of an actor in the search bar on the left\n"
					 		+ "2) Click the Search button\n"
					 		+ "3) The result should appear on the list on the left. If it does not, try searching for single words in the actor's name\n\n"
					 		+ "TO ADD AN ACTOR TO LIKED LIST\n"
					 		+ "1) Click on the actor in your Searched list(left)\n"
					 		+ "2) A popup will appear to confirm that you wish to add the actor\n\n"
					 		+ "TO REMOVE AN ACTOR FROM YOUR LIKED LIST\n"
					 		+ "1) Click on the actor in your liked list(right)\n"
					 		+ "2)A popup will appear to confirm that you wish to remove the actor\\n" ,"Instructions", JOptionPane.INFORMATION_MESSAGE);
					
				 }
			 }); 
	        instructionsbt5.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e){ 
					 
					 JOptionPane.showMessageDialog(null, "TO ADD AN ACTOR TO FAVORITE\n"
					 		+ "1) Click on the actor in your liked list(left)\n"
					 		+ "2) A popup will appear to confirm that you wish to add the actor to favorite\n"
					 		+ "3) Note: you are only allowed one favorite\n\n"
					 		+ "TO REMOVE AN ACTOR FROM YOUR FAVORITE LIST \n"
					 		+ "1) Click on the actor in your favorite list(right)\n"
					 		+ "2)A popup will appear to confirm that you wish to remove the actor\n" ,"Instructions", JOptionPane.INFORMATION_MESSAGE);
					
				 }
			 }); 
	        
	      //******************************************************************//
	      //******************************************************************//
	      //******************************************************************//
	      //******************************************************************//
	      //************* this is the setting up user log in infor tab*******/
	       
	        searchedformovieslb.setForeground(white);
	        searchedformovieslb.setOpaque(true);
	        searchedformovieslb.setBackground(grey);
	        searchedformovieslb.setText("Search results:");
	        searchedformovieslb.setBounds(width/5, height/2-100, 150, 40);
	        searchedformovieslb.setFont(new Font("Courier", Font.BOLD,12));
	        
	        likedmoviedlb.setForeground(white);
	        likedmoviedlb.setOpaque(true);
	        likedmoviedlb.setBackground(grey);
	        likedmoviedlb.setText("Liked Movies:");
	        likedmoviedlb.setBounds(190+width/5, height/2-100, 150, 40);
	        likedmoviedlb.setFont(new Font("Courier", Font.BOLD,12));
	        
	        
	        
	        
	       
	        list.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like to add "+item.toString()+" to your liked movies list?");
	                     if(res==0) {
	                    	 listliked.setSelectedIndex(0);
	                    	 dataliked.addElement(item);
	                    	 dataliked2.addElement(item);
	                    	 listliked.setListData(dataliked);
	                    	 listliked2.setListData(dataliked2);
	                    	
	                    	
	                     }
	                  }
	               }
	            }
	         });
	        
	        
	       

	       
	       
	        listliked.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like to remove "+item.toString()+" from your liked list?");
	                     if(res==0) {
	                    	 listliked.setSelectedIndex(0);
	                    	 dataliked.remove(item);
	                    	 dataliked2.remove(item);
	                    	 listliked.setListData(dataliked);
	                    	 listliked2.setListData(dataliked2);
	                    	
	                     };
	                  }
	               }
	            }
	         });
	        
	        serchedmovies.setBounds(width/5,height/2-50,150,200);
	        likedmovies.setBounds(190+width/5,height/2-50,150,200);

	        
	        
	        
	        
	        
	        
	        Searchmoviefld.setBounds(width/5, height/2-150, 150,50 );
	        Searchmoviefld.setBackground(teal);
	        Searchmoviefld.setText("Search Movie name here");
	        
	        
	        
	        
	        searchmoviebtn.setText("Search Movie");
	        searchmoviebtn.setBounds(190+width/5,height/2-150, 150, 50);
	        searchmoviebtn.setBackground(yellow);
	       // addWindowListener(this);
	        searchmoviebtn.addActionListener(new ActionListener(){  
	        	public void actionPerformed(ActionEvent e){ 
	        		// this is the location that needs to allign with what the user 
	        		// is looking for
	        		// then could you add what is returned to the list ellements bellow
	        		// 
					String MovieSearchKey = Searchmoviefld.getText();
					searchMovies = Database.searchMovie(MovieSearchKey);

					data.clear();
	        		
	        		for(int i=0;i<searchMovies.size();i++) {
	        			data.addElement(searchMovies.get(i));
	        		}
	    	        list.setListData(data);
	        		
	        		
//	        		data.addElement("India");
//	    	        data.addElement("Australia");
//	    	        data.addElement("England");
//	    	        data.addElement("England");
//	    	        data.addElement("New Zealand");
//	    	        data.addElement("South Africa");
//					list.setListData(data);
					
	            }  
	        });  
	        UserNamefld.addFocusListener(new FocusListener() {
	        	public void focusGained(FocusEvent e) {
	        		UserNamefld.setText(""); 
	        	}
	        	public void focusLost(FocusEvent e) {

	        	}
	        	});
	        Passwordfld.addFocusListener(new FocusListener() {
	        	public void focusGained(FocusEvent e) {
	        		Passwordfld.setText(""); 
	        	}
	        	public void focusLost(FocusEvent e) {

	        	}
	        	}); 
	        UserRealNamefld.addFocusListener(new FocusListener() {
	        	public void focusGained(FocusEvent e) {
	        		UserRealNamefld.setText(""); 
	        	}
	        	public void focusLost(FocusEvent e) {

	        	}
	        	}); 
	        UserAgefld.addFocusListener(new FocusListener() {
	        	public void focusGained(FocusEvent e) {
	        		UserAgefld.setText(""); 
	        	}
	        	public void focusLost(FocusEvent e) {

	        	}
	        	});  
	        Searchmoviefld.addFocusListener(new FocusListener() {
	        	public void focusGained(FocusEvent e) {
	        		Searchmoviefld.setText(""); 
	        	}
	        	public void focusLost(FocusEvent e) {

	        	}
	        	}); 
	        Searchactorfld.addFocusListener(new FocusListener() {
	        	public void focusGained(FocusEvent e) {
	        		Searchactorfld.setText(""); 
	        	}
	        	public void focusLost(FocusEvent e) {

	        	}
	        	});  
	        
	        
	      //************* this is the settings for liked movies************************// 
	      //******************************************************************//
		      //******************************************************************//
		      //******************************************************************//
		      //******************************************************************//
	        
	      // this is the setting for the favorite Movie**********************************//
	        
	        
	        likedmovielb2.setForeground(white);
	        likedmovielb2.setOpaque(true);
	        likedmovielb2.setBackground(grey);
	        likedmovielb2.setText("Liked Movies: ");
	        likedmovielb2.setBounds(2*width/5-20, height/2-100, 150, 40);
	        likedmovielb2.setFont(new Font("Courier", Font.BOLD,12));
	        
	        favoritmovelb.setForeground(white);
	        favoritmovelb.setOpaque(true);
	        favoritmovelb.setBackground(grey);
	        favoritmovelb.setText("Favorite Movie:");
	        favoritmovelb.setBounds(185+2*width/5, height/2-100, 150, 40);
	        favoritmovelb.setFont(new Font("Courier", Font.BOLD,12));
	        
	        
	        
	       
	        
	        likedmovies2.setBounds(2*width/5-20,height/2-50,150,200);
	       // list.setSelectedIndex(0);
	        
	        //this is to make sure the liked list match
	       
	        listliked2.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     if(!favoritmoviefound) {
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like to add "+item.toString()+" as your favorit Movie? ");
	                     if(res==0) {
	                    	 datafavoritMovie.add(item);
	                    	 listfavoritmovie.setListData(datafavoritMovie);
	                    	 favoritmoviefound = true;
	                    	
	                     }else {
	                    	
	                     }
	                  }
	                  }
	               }
	            }
	         });
	        
	        
	        favoritmovies.setBounds(185+2*width/5,height/2-50,150,100);
	        
	        
	        listfavoritmovie.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like to remove "+item.toString()+" from your favorite movie");
	                     if(res==0) {
	                    	 listliked.setSelectedIndex(0);
	                    	 datafavoritMovie.remove(item);
	                    	 listfavoritmovie.setListData(datafavoritMovie);
	                    	 favoritmoviefound = false;
	                    	
	                     };
	                  }
	               }
	            }
	         });
	        
	        
	      // this is the setting for the favorite Movie**********************************//
	      //************************8 this is going to be for the liked actor tabe ************************************//
	        
	        searchedforactorslb.setForeground(white);
	        searchedforactorslb.setOpaque(true);
	        searchedforactorslb.setBackground(grey);
	        searchedforactorslb.setText("Search results:");
	        searchedforactorslb.setBounds(3*width/5-30, height/2-100, 150, 40);
	        searchedforactorslb.setFont(new Font("Courier", Font.BOLD,12));
	        
	        likedactorslb1.setForeground(white);
	        likedactorslb1.setOpaque(true);
	        likedactorslb1.setBackground(grey);
	        likedactorslb1.setText("Liked Actors:");
	        likedactorslb1.setBounds(167+3*width/5, height/2-100, 150, 40);
	        likedactorslb1.setFont(new Font("Courier", Font.BOLD,12));
	        
	        
	        searchactorbtn.setText("Search Actor");
	        searchactorbtn.setBounds(167+3*width/5,height/2-150, 150, 50);
	        searchactorbtn.setBackground(yellow);
	        
	        Searchactorfld.setBounds(3*width/5-30, height/2-150, 150,50 );
	        Searchactorfld.setBackground(teal);
	        Searchactorfld.setText("Search Actor name here");
	        
	        likedactorlbtwo.setForeground(white);
	        likedactorlbtwo.setOpaque(true);
	        likedactorlbtwo.setBackground(grey);
	        likedactorlbtwo.setText("Liked Actors: ");
	        likedactorlbtwo.setBounds(4*width/5-60, height/2-100, 150, 40);
	        likedactorlbtwo.setFont(new Font("Courier", Font.BOLD,12));
	        
	        favoreteactorlb.setForeground(white);
	        favoreteactorlb.setOpaque(true);
	        favoreteactorlb.setBackground(grey);
	        favoreteactorlb.setText("Favorite Actor:");
	        favoreteactorlb.setBounds(5*width/5-250, height/2-100, 150, 40);
	        favoreteactorlb.setFont(new Font("Courier", Font.BOLD,12));
	        
	        
	        listsearchedactors.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like to add "+item.toString()+" to your liked actors list?");
	                     if(res==0) {
	                    	 listlikeactorsone.setSelectedIndex(0);
	                    	 datalikedactorsone.addElement(item);
	                    	 datalikedactorstwo.addElement(item);
	                    	 listlikeactorsone.setListData(datalikedactorsone);
	                    	 listlikeactorstwo.setListData(datalikedactorstwo);
	                    	
	                     }
	                  }
	               }
	            }
	         });
	        searchedactors.setBounds(3*width/5-30,height/2-50,120,200);
	        listlikeactorsone.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like to remove "+item.toString()+" from your liked list?");
	                     if(res==0) {
	                    	 listlikeactorsone.setSelectedIndex(0);
	                    	 datalikedactorsone.remove(item);
	                    	 datalikedactorstwo.remove(item);
	                    	 listlikeactorsone.setListData(datalikedactorsone);
	                    	 listlikeactorstwo.setListData(datalikedactorstwo);
	                    	
	                     };
	                  }
	               }
	            }
	         });
	        
	       
	        likedactorsone.setBounds(167+3*width/5,height/2-50,150,200);
		       
	        listlikeactorstwo.addMouseListener(new MouseAdapter() {
		            public void mouseClicked(MouseEvent me) {
		               if (me.getClickCount() == 1) {
		                  JList target = (JList)me.getSource();
		                  int index = target.locationToIndex(me.getPoint());
		                  if (index >= 0) {
		                     Object item = target.getModel().getElementAt(index);
		                     if(!favoritactorfound) {
		                     int res = JOptionPane.showConfirmDialog(null, "Would you like to add "+item.toString()+" as your favorit Movie? ");
		                     if(res==0) {
		                    	 datafavoritactors.add(item);
		                    	 listfavoritactors.setListData(datafavoritactors);
		                    	 favoritactorfound = true;
		                    	
		                     }else {
		                    	
		                     }
		                  }
		                  }
		               }
		            }
		         });
	        likedactorstwo.setBounds(4*width/5-60,height/2-50,150,200);
	        

	        
	        
	        listfavoritactors.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like to remove "+item.toString()+" from your favorite actors");
	                     if(res==0) {
	                    	 //listliked.setSelectedIndex(0);
	                    	 datafavoritactors.remove(item);
	                    	 listfavoritactors.setListData(datafavoritactors);
	                    	 favoritactorfound = false;
	                    	
	                     };
	                  }
	               }
	            }
	         });
	        
	        favoritactors.setBounds(5*width/5-250,height/2-50,150,100);
	        
	        searchactorbtn.addActionListener(new ActionListener(){  
	        	public void actionPerformed(ActionEvent e){ 
	        		// this is the location that needs to allign with what the user 
	        		// is looking for
	        		// then could you add what is returned to the list ellements bellow
	        		// 
					String ActorSearchKey = Searchactorfld.getText();

					searchPeople = Database.searchPeople(ActorSearchKey);
	        		dataserchedactors.clear();
	        		
	        		for(int i = 0;i<searchPeople.size();i++) {
	        			dataserchedactors.addElement(searchPeople.get(i));
	        		}
	        		listsearchedactors.setListData(dataserchedactors);
					/**
	        		dataserchedactors.addElement("India");
	        		dataserchedactors.addElement("Australia");
	        		dataserchedactors.addElement("England");
	        		dataserchedactors.addElement("England");
	        		dataserchedactors.addElement("New Zealand");
	        		dataserchedactors.addElement("South Africa");
					listsearchedactors.setListData(dataserchedactors);
					*/
	            }  
	        });  
		  //************************8 this is going to be for the liked actor tabe ************************************//  
	        
	        inputframe.add(instructionsbt1);
	        inputframe.add(instructionsbt2);
	        inputframe.add(instructionsbt3);
	        inputframe.add(instructionsbt4);
	        inputframe.add(instructionsbt5);
	        inputframe.add(PasswordLb);
	        inputframe.add(Newuserinfor);
	        inputframe.add(UserNameLb);
	        inputframe.add(likedMovielb);
	        inputframe.add(favMovielb);
	        inputframe.add(favMovielb);
	        inputframe.add(likedactorslb);
	        inputframe.add(favactorslb);
	        inputframe.add(Passwordfld);
	        inputframe.add(UserNamefld);
	        inputframe.add(likedmovies);
	        inputframe.add(serchedmovies);
	        inputframe.add(Searchmoviefld);
	        inputframe.add(searchmoviebtn);
	        inputframe.add(searchedformovieslb);
	        inputframe.add(likedmoviedlb);
	        inputframe.add(likedmovielb2);
	        inputframe.add(favoritmovelb);
	        inputframe.add(likedmovies2);
	        inputframe.add(favoritmovies);
	        inputframe.add(searchedforactorslb);
	        inputframe.add(likedactorslb1);
	        inputframe.add(searchactorbtn);
	        inputframe.add(Searchactorfld);
	        inputframe.add(likedactorsone);
	        inputframe.add(likedactorstwo);
	        inputframe.add(searchedactors);
	        inputframe.add(favoritactors);
	        inputframe.add(likedactorlbtwo);
	        inputframe.add(favoreteactorlb);
	        inputframe.add(UserrealNameLb);
	        inputframe.add(UserAgeLb);
	        inputframe.add(UserRealNamefld);
	        inputframe.add(UserAgefld);
	        inputframe.add(submitbtn);
	        inputframe.add(backbtn);
	        
	        inputframe.add(canvas);
	        
	        inputframe.pack();
	        inputframe.setVisible(true);
	        
	      
	 }
	 
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
	        		String username = UserNamefld.getText();
	        		String password = Passwordfld.getText();
	        		TestJDBC Database = new TestJDBC();
	        		Database.Connection();
	        		
	        		if(Database.verifyLogin(username,password)) {
	        			currUser = username;
	        			currPassword = password;
	        			inputframe.remove(UserNamefld);
		        		inputframe.remove(Passwordfld);
		        		inputframe.remove(UserNameLb);
		        		inputframe.remove(PasswordLb);
		        		inputframe.remove(loginbtn);
		        		inputframe.remove(newuserbtn);
		        		inputframe.remove(canvas);
		        		loginhome(inputframe);
	        		} else{
					
					JOptionPane.showMessageDialog(null, "I am sorry, The Username or Password is incorrect, Please try again");
				}
	        		
	            }  
	        });  
	        UserNamefld.addFocusListener(new FocusListener() {
	        	public void focusGained(FocusEvent e) {
	        		UserNamefld.setText(""); 
	        	}
	        	public void focusLost(FocusEvent e) {

	        	}
	        	}); 
	        Passwordfld.addFocusListener(new FocusListener() {
	        	public void focusGained(FocusEvent e) {
	        		Passwordfld.setText(""); 
	        	}
	        	public void focusLost(FocusEvent e) {

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
	 public static void loginhome(JFrame inputframe) {
		 framecount =3;
		 inputframe.getContentPane().setBackground(purple);
			inputframe.setDefaultCloseOperation(inputframe.EXIT_ON_CLOSE);
			inputframe.setTitle("Log in Home");
			inputframe.setVisible(true);
	        Canvas canvas = new Drawing();
	        canvas.setSize(width, height);
	        
	        JTextArea SimActorlb = new JTextArea();
	        JTextArea SimGenraLb = new JTextArea();
	        JTextArea SimDirectorLb = new JTextArea();
	        JTextArea ComboLb = new JTextArea();
	        JTextArea instrucLb = new JTextArea();
	        JTextArea instruclistLb = new JTextArea();
	        
			SimActorlb.setForeground(white);
			SimActorlb.setOpaque(true);
			SimActorlb.setBackground(grey);
			SimActorlb.setText("Recommended Movies \nWith liked actors");
			SimActorlb.setBounds(width/5+70, 200, 200, 75);
			SimActorlb.setFont(new Font("Courier", Font.BOLD,15));
			
			SimGenraLb.setForeground(white);
			SimGenraLb.setOpaque(true);
			SimGenraLb.setBackground(grey);
			SimGenraLb.setText("Recommended Movies \nWith liked Genres");
			SimGenraLb.setBounds(2*width/5+60, 200, 200, 75);
			SimGenraLb.setFont(new Font("Courier", Font.BOLD,15));
			
			SimDirectorLb.setForeground(white);
			SimDirectorLb.setOpaque(true);
			SimDirectorLb.setBackground(grey);
			SimDirectorLb.setText("Recommended Movies \nWith liked Directors\nor Writers");
			SimDirectorLb.setBounds(3*width/5+42, 200, 200, 75);
			SimDirectorLb.setFont(new Font("Courier", Font.BOLD,15));
			
			ComboLb.setForeground(white);
			ComboLb.setOpaque(true);
			ComboLb.setBackground(grey);
			ComboLb.setText("Recommended Movies \nWith All Three\nAttributes");
			ComboLb.setBounds(4*width/5+22, 200, 200, 75);
			ComboLb.setFont(new Font("Courier", Font.BOLD,15));
			
			instrucLb.setForeground(white);
			instrucLb.setOpaque(true);
			instrucLb.setBackground(grey);
			instrucLb.setText("\n    Instructions");
			instrucLb.setBounds(90, 200, 200, 75);
			instrucLb.setFont(new Font("Courier", Font.BOLD,15));
			
			
			instruclistLb.setForeground(white);
			instruclistLb.setOpaque(true);
			instruclistLb.setBackground(grey);
			instruclistLb.setText("1) The left consists of movie\nrecomendatons organized by three \ncategories and one combination\n"
					+ "2) Click on the list box you wish \nto select more information about \na movie with in that box \n"
					+"3) Select the movie inside \nthe list by clicking on it");
			instruclistLb.setBounds(40, 300, 300, 400);
			instruclistLb.setFont(new Font("Courier", Font.BOLD,15));
			
			JList listsimactor = new JList();
			JList listsingen = new JList();
			JList listsindirc = new JList();
			JList listsinall = new JList();
			 
			Vector datasimactor = new Vector();
			Vector datasimgen = new Vector();
			Vector datasimdir = new Vector();
			Vector datasimall = new Vector();
			 
			JScrollPane simactorpn = new JScrollPane(listsimactor);
		    JScrollPane simgenon = new JScrollPane(listsingen);
		    JScrollPane simdircon= new JScrollPane(listsindirc);
		    JScrollPane simallpn = new JScrollPane(listsinall);
		    
		    JButton logoutbtn = new JButton();
			JButton usersettingbtn = new JButton();
			
		    simactorpn.setBounds(width/5+20,height/2-200,300,400);
		    simgenon.setBounds(2*width/5+8,height/2-200,300,400);
		    simdircon.setBounds(3*width/5-10,height/2-200,300,400);
		    simallpn.setBounds(4*width/5-27,height/2-200,300,400);
		    
		    logoutbtn.setText("Log out");
		    logoutbtn.setBounds(20,50, 200, 50);
		    logoutbtn.setBackground(yellow);
	        
		    usersettingbtn.setText("User Settings");
		    usersettingbtn.setBounds(width-300,50, 200, 50);
		    usersettingbtn.setBackground(yellow);
		    
		    
		    // Allen this is how to add the information to the list above you will need to do this of each 
		    // of the fours lists
		    
		    ArrayList<Pair> simactorMovies = Database.simActorMovie(currPassword,currUser);
			ArrayList<Pair> simgenreMovies = Database.simGenreMovie(currPassword,currUser);
			ArrayList<Pair> simdirMovies = Database.simDirMovie(currPassword,currUser);
			ArrayList<Pair> simallMovies = Database.simAllMovie(currPassword,currUser);

			for(int i =0;i<simactorMovies.size();i++) {
				datasimactor.addElement(simactorMovies.get(i));
			}

			for(int i =0;i<simgenreMovies.size();i++) {
				datasimgen.addElement(simgenreMovies.get(i));
			}

			for(int i =0;i<simdirMovies.size();i++) {
				datasimdir.addElement(simdirMovies.get(i));
			}

			for(int i =0;i<simallMovies.size();i++) {
				datasimall.addElement(simallMovies.get(i));
			}


			listsimactor.setListData(datasimactor);
			
			listsingen.setListData(datasimgen);
			listsindirc.setListData(datasimdir);
			listsinall.setListData(datasimall);
		    
		   // datasimdir.addElement("test");
		    
		    
		    listsimactor.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like to look into the movie "+item.toString()+" more?");
	                     if(res==0) {
	                    	inputframe.remove(logoutbtn);
	             			inputframe.remove(usersettingbtn);
	             		    inputframe.remove(simactorpn);
	             			inputframe.remove(simgenon);
	             			inputframe.remove(simdircon);
	             			inputframe.remove(simallpn);
	             			inputframe.remove(instruclistLb);
	             			inputframe.remove(instrucLb);
	             			inputframe.remove(ComboLb);
	             			inputframe.remove(SimDirectorLb);
	             			inputframe.remove(SimActorlb);
	             			inputframe.remove(SimGenraLb);
	             	        inputframe.remove(canvas);
	             	        selectedMovie(inputframe, item);
	                    	
	                     };
	                  }
	               }
	            }
	         });
		    
		    listsingen.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like to look into the movie "+item.toString()+" more?");
	                     if(res==0) {
	                    	inputframe.remove(logoutbtn);
	             			inputframe.remove(usersettingbtn);
	             		    inputframe.remove(simactorpn);
	             			inputframe.remove(simgenon);
	             			inputframe.remove(simdircon);
	             			inputframe.remove(simallpn);
	             			inputframe.remove(instruclistLb);
	             			inputframe.remove(instrucLb);
	             			inputframe.remove(ComboLb);
	             			inputframe.remove(SimDirectorLb);
	             			inputframe.remove(SimActorlb);
	             			inputframe.remove(SimGenraLb);
	             	        inputframe.remove(canvas);
	             	        selectedMovie(inputframe, item);
	                    	
	                     };
	                  }
	               }
	            }
	         });
		    listsindirc.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like to look into the movie "+item.toString()+" more?");
	                     if(res==0) {
	                    	inputframe.remove(logoutbtn);
	             			inputframe.remove(usersettingbtn);
	             		    inputframe.remove(simactorpn);
	             			inputframe.remove(simgenon);
	             			inputframe.remove(simdircon);
	             			inputframe.remove(simallpn);
	             			inputframe.remove(instruclistLb);
	             			inputframe.remove(instrucLb);
	             			inputframe.remove(ComboLb);
	             			inputframe.remove(SimDirectorLb);
	             			inputframe.remove(SimActorlb);
	             			inputframe.remove(SimGenraLb);
	             	        inputframe.remove(canvas);
	             	        selectedMovie(inputframe, item);
	                    	
	                     };
	                  }
	               }
	            }
	         });
		    listsinall.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like to look into the movie "+item.toString()+" more?");
	                     if(res==0) {
	                    	inputframe.remove(logoutbtn);
	             			inputframe.remove(usersettingbtn);
	             		    inputframe.remove(simactorpn);
	             			inputframe.remove(simgenon);
	             			inputframe.remove(simdircon);
	             			inputframe.remove(simallpn);
	             			inputframe.remove(instruclistLb);
	             			inputframe.remove(instrucLb);
	             			inputframe.remove(ComboLb);
	             			inputframe.remove(SimDirectorLb);
	             			inputframe.remove(SimActorlb);
	             			inputframe.remove(SimGenraLb);
	             	        inputframe.remove(canvas);
	             	        selectedMovie(inputframe, item);
	                    	
	                     };
	                  }
	               }
	            }
	         });
		    
		    logoutbtn.addActionListener(new ActionListener(){  
	        	public void actionPerformed(ActionEvent e){ 
	        		inputframe.remove(logoutbtn);
         			inputframe.remove(usersettingbtn);
         		    inputframe.remove(simactorpn);
         			inputframe.remove(simgenon);
         			inputframe.remove(simdircon);
         			inputframe.remove(simallpn);
         			inputframe.remove(instruclistLb);
         			inputframe.remove(instrucLb);
         			inputframe.remove(ComboLb);
         			inputframe.remove(SimDirectorLb);
         			inputframe.remove(SimActorlb);
         			inputframe.remove(SimGenraLb);
         	        inputframe.remove(canvas);
	        		inputframe.remove(canvas);
	        		loginscreen(inputframe);
	        	
	            }  
	        });  
		    
		    usersettingbtn.addActionListener(new ActionListener(){  
	        	public void actionPerformed(ActionEvent e){ 
	        		inputframe.remove(logoutbtn);
         			inputframe.remove(usersettingbtn);
         		    inputframe.remove(simactorpn);
         			inputframe.remove(simgenon);
         			inputframe.remove(simdircon);
         			inputframe.remove(simallpn);
         			inputframe.remove(instruclistLb);
         			inputframe.remove(instrucLb);
         			inputframe.remove(ComboLb);
         			inputframe.remove(SimDirectorLb);
         			inputframe.remove(SimActorlb);
         			inputframe.remove(SimGenraLb);
         	        inputframe.remove(canvas);
	        		inputframe.remove(canvas);
	        		Userinfor(inputframe);
	        	
	            }  
	        });  
		   
  
		    
		    
		    inputframe.add(logoutbtn);
			inputframe.add(usersettingbtn);
		    inputframe.add(simactorpn);
			inputframe.add(simgenon);
			inputframe.add(simdircon);
			inputframe.add(simallpn);
			inputframe.add(instruclistLb);
			inputframe.add(instrucLb);
			inputframe.add(ComboLb);
			inputframe.add(SimDirectorLb);
			inputframe.add(SimActorlb);
			inputframe.add(SimGenraLb);
	        inputframe.add(canvas);
		       
	        inputframe.pack();
	        inputframe.setVisible(true);
		 
		 
	 }
	 public static void selectedMovie(JFrame inputframe, Object Movie) {
		 framecount=4;
		 Random rand = new Random();
		 inputframe.getContentPane().setBackground(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
		 inputframe.setDefaultCloseOperation(inputframe.EXIT_ON_CLOSE);
		 inputframe.setTitle("Log in start screen");
		 inputframe.setVisible(true);
	     Canvas canvas = new Drawing();
	     canvas.setSize(width, height);
	     
	     
	     JLabel synopsislb = new JLabel();
		 JLabel Listofactorslb = new JLabel();
		 JLabel Dirclb = new JLabel();
		 JTextArea Listofwriterslb = new JTextArea();
		 JTextArea actorinstructlb = new JTextArea();
		 JLabel Ratinglb = new JLabel();
		 JLabel grossincomelb = new JLabel();
		 JLabel contreylb = new JLabel();
		 JLabel langlb = new JLabel();
		 JLabel realsedatelb = new JLabel();
		 JLabel Durationlb = new JLabel();
		 JLabel MovieGenrelb = new JLabel();
		 JLabel IDlb = new JLabel();
		 JLabel Titlelb = new JLabel();
		 JTextArea Directorinfolb = new JTextArea();
		 JTextArea ratingsinfolb = new JTextArea();
		 JTextArea grossinfolb = new JTextArea();
		 JTextArea contreyinfolb = new JTextArea();
		 JTextArea langinfolb = new JTextArea();
		 JTextArea realsedateinfolb = new JTextArea();
		 JTextArea Durationinfrolb = new JTextArea();
		 JTextArea MovieGenreinfolb = new JTextArea();
		 JTextArea IDinfolb = new JTextArea();
		 
		 
		 JButton likedbtn = new JButton();
		 JButton notlikedbtn = new JButton();
		 JButton mapbtn = new JButton();
		 JButton backbtn = new JButton();
		 //JTextArea synopsistxtlb = new JTextArea();
		 JTextArea synopsistxtlb = new JTextArea();
		 
		 JList listactor = new JList();
		 JList listwriters = new JList();
		 Vector dataactor = new Vector();
		 Vector datawrtiers = new Vector();
		 
		 JScrollPane actopn = new JScrollPane(listactor);
		 JScrollPane wrtierspn = new JScrollPane(listwriters);
		 
		//@ allen this is where you are going
		 // place in the strings and they should be in the properlocation
		 // you also need to add the to the two vectors above
		 // haveing the writers and actors in the list
		 String IDst = ((Pair)Movie).getKey();
		 movieInfo.clear();
		 movieInfo = Database.getMovieInfo(IDst);
		// create table movie(ID, Title, dateOfRelease, MovieGenre, Duration, country , Writers, worldgross, Director, mLanguage, listofactors

		 String Titlest = movieInfo.get(1).toString();
		 String origSyn = movieInfo.get(2).toString();
		 String Synopsisst = origSyn.replaceAll("(.{1,25})(?:$| )", "$1\n");
		 String Directorsst = movieInfo.get(9).toString();
		 String Ratingst = "8.7";
	     String grossst =  movieInfo.get(8).toString();
	     String contreyst = movieInfo.get(6).toString();
	     String langst =movieInfo.get(10).toString();
	     String realsedatest =movieInfo.get(3).toString();
	     String Durationst =movieInfo.get(5).toString();
		 String MovieGenrest =movieInfo.get(4).toString();
		 
		 String actors = movieInfo.get(11).toString();

		 actors = actors.replaceAll(",\\s+",",");
		 String [] listofactors = actors.split(",");
		 ArrayList<Pair> actorsinMovie = Database.getPeople(listofactors);
		 System.out.println("actors size:"+actorsinMovie.size());
		 for(int i = 0; i<actorsinMovie.size();i++) {
			 dataactor.addElement(actorsinMovie.get(i));
		 }
		 listactor.setListData(dataactor);

		 String writers = movieInfo.get(7).toString();
		 writers = writers.replaceAll(",\\s+",",");
		 String[] listofwriters = writers.split(",");

		 for(int i = 0;i<listofwriters.length;i++) {
			 datawrtiers.addElement(listofwriters[i]);
		 }
		 listwriters.setListData(datawrtiers);


		 synopsislb.setForeground(white);
		 synopsislb.setOpaque(true);
		 synopsislb.setBackground(grey);
		 synopsislb.setText("    Synopsis");
		 synopsislb.setBounds(100, 180, 170, 50);
		 synopsislb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 synopsistxtlb.setForeground(black);
		 synopsistxtlb.setOpaque(true);
		 synopsistxtlb.setBackground(white);
		 synopsistxtlb.setText(Synopsisst);
		 synopsistxtlb.setBounds(70, 250, 230, 500);
		 synopsistxtlb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 
		 
		 Listofactorslb.setForeground(white);
		 Listofactorslb.setOpaque(true);
		 Listofactorslb.setBackground(grey);
		 Listofactorslb.setText("  List Of Actors");
		 Listofactorslb.setBounds(width/5+85, 180, 170, 50);
		 Listofactorslb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 actorinstructlb.setForeground(white);
		 actorinstructlb.setOpaque(true);
		 actorinstructlb.setBackground(grey);
		 actorinstructlb.setText(" See an Actor you\n       Like?\n Click their Name \n Follow The Popup");
		 actorinstructlb.setBounds(width/5+85, 780, 170, 100);
		 actorinstructlb.setFont(new Font("Courier", Font.BOLD,15));
		 actopn.setBounds(width/5+60,250,230,500);
		 
		 Dirclb.setForeground(white);
		 Dirclb.setOpaque(true);
		 Dirclb.setBackground(grey);
		 Dirclb.setText("Director");
		 Dirclb.setBounds(2*width/5+10, 180, 100, 50);
		 Dirclb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 Directorinfolb.setForeground(black);
		 Directorinfolb.setOpaque(true);
		 Directorinfolb.setBackground(white);
		 Directorinfolb.setText(Directorsst);
		 Directorinfolb.setBounds(2*width/5+10, 250, 100, 50);
		 
		 
		 Listofwriterslb.setForeground(white);
		 Listofwriterslb.setOpaque(true);
		 Listofwriterslb.setBackground(grey);
		 Listofwriterslb.setText("List of \nWriters");
		 Listofwriterslb.setBounds(2*width/5+10, 400, 100, 50);
		 Listofwriterslb.setFont(new Font("Courier", Font.BOLD,15));
		 wrtierspn.setBounds(2*width/5+10,460,100,300);
		 
		 Ratinglb.setForeground(white);
		 Ratinglb.setOpaque(true);
		 Ratinglb.setBackground(grey);
		 Ratinglb.setText("Ratings");
		 Ratinglb.setBounds(2*width/5+210, 180, 100, 50);
		 Ratinglb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 ratingsinfolb.setForeground(black);
		 ratingsinfolb.setOpaque(true);
		 ratingsinfolb.setBackground(white);
		 ratingsinfolb.setText(Ratingst);
		 ratingsinfolb.setBounds(2*width/5+210, 250, 100, 50);
		 
		 grossincomelb.setForeground(white);
		 grossincomelb.setOpaque(true);
		 grossincomelb.setBackground(grey);
		 grossincomelb.setText("Gross Income");
		 grossincomelb.setBounds(2*width/5+190, 350, 150, 50);
		 grossincomelb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 grossinfolb.setForeground(black);
		 grossinfolb.setOpaque(true);
		 grossinfolb.setBackground(white);
		 grossinfolb.setText(grossst);
		 grossinfolb.setBounds(2*width/5+190, 410, 150, 50);
		 
		 contreylb.setForeground(white);
		 contreylb.setOpaque(true);
		 contreylb.setBackground(grey);
		 contreylb.setText("Countrey Orgin");
		 contreylb.setBounds(2*width/5+190, 520, 150, 50);
		 contreylb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 contreyinfolb.setForeground(black);
		 contreyinfolb.setOpaque(true);
		 contreyinfolb.setBackground(white);
		 contreyinfolb.setText(contreyst);
		 contreyinfolb.setBounds(2*width/5+190, 580, 150, 50);
		 
		 langlb.setForeground(white);
		 langlb.setOpaque(true);
		 langlb.setBackground(grey);
		 langlb.setText("Languge");
		 langlb.setBounds(2*width/5+190, 700, 150, 50);
		 langlb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 langinfolb.setForeground(black);
		 langinfolb.setOpaque(true);
		 langinfolb.setBackground(white);
		 langinfolb.setText(langst);
		 langinfolb.setBounds(2*width/5+190, 760, 150, 50);
		 
		 realsedatelb.setForeground(white);
		 realsedatelb.setOpaque(true);
		 realsedatelb.setBackground(grey);
		 realsedatelb.setText("Release Date");
		 realsedatelb.setBounds(93*width/160, 180, 150, 50);
		 realsedatelb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 realsedateinfolb.setForeground(black);
		 realsedateinfolb.setOpaque(true);
		 realsedateinfolb.setBackground(white);
		 realsedateinfolb.setText(realsedatest);
		 realsedateinfolb.setBounds(93*width/160, 250, 150, 50);
		 
		 Durationlb.setForeground(white);
		 Durationlb.setOpaque(true);
		 Durationlb.setBackground(grey);
		 Durationlb.setText("Duration");
		 Durationlb.setBounds(93*width/160, 350, 150, 50);
		 Durationlb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 Durationinfrolb.setForeground(black);
		 Durationinfrolb.setOpaque(true);
		 Durationinfrolb.setBackground(white);
		 Durationinfrolb.setText(Durationst);
		 Durationinfrolb.setBounds(93*width/160, 410, 150, 50);
		 
		 MovieGenrelb.setForeground(white);
		 MovieGenrelb.setOpaque(true);
		 MovieGenrelb.setBackground(grey);
		 MovieGenrelb.setText("Genre");
		 MovieGenrelb.setBounds(93*width/160, 520, 150, 50);
		 MovieGenrelb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 MovieGenreinfolb.setForeground(black);
		 MovieGenreinfolb.setOpaque(true);
		 MovieGenreinfolb.setBackground(white);
		 MovieGenreinfolb.setText(MovieGenrest);
		 MovieGenreinfolb.setBounds(93*width/160, 580, 150, 50);
		 
		 IDlb.setForeground(white);
		 IDlb.setOpaque(true);
		 IDlb.setBackground(grey);
		 IDlb.setText("Movie ID");
		 IDlb.setBounds(93*width/160, 700, 150, 50);
		 IDlb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 IDinfolb.setForeground(black);
		 IDinfolb.setOpaque(true);
		 IDinfolb.setBackground(white);
		 IDinfolb.setText(IDst);
		 IDinfolb.setBounds(93*width/160, 760, 150, 50);
		 
		 
		 Titlelb.setForeground(white);
		 Titlelb.setText(Titlest);
		 Titlelb.setBounds(200, 50, 1000, 100);
		 Titlelb.setFont(new Font("Courier", Font.BOLD,50));
		 
		 likedbtn.setText("Did you like this Movie?");
		 likedbtn.setBounds(3*width/4, height/4, 200, 100);
		 likedbtn.setBackground(yellow);
		 
		 notlikedbtn.setText("Did you not like this Movie?");
		 notlikedbtn.setBounds(3*width/4, 2*height/4-100, 200, 100);
		 notlikedbtn.setBackground(yellow);
		 
		 mapbtn.setText("You Wanna See A Cool Map?");
		 mapbtn.setBounds(3*width/4, 3*height/4-200, 200, 100);
		 mapbtn.setBackground(yellow);
		 
		 backbtn.setText("Back");
		 backbtn.setBounds(3*width/4, 4*height/4-300, 200, 100);
		 backbtn.setBackground(yellow);
		 
		 mapbtn.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				 //Find Stuff to delete
				 
				 inputframe.remove(actorinstructlb);
				 inputframe.remove(Titlelb);
				 inputframe.remove(realsedateinfolb);
				 inputframe.remove(Durationinfrolb);
				 inputframe.remove(MovieGenreinfolb);
				 inputframe.remove(IDinfolb);
				 inputframe.remove(grossinfolb);
				 inputframe.remove(contreyinfolb);
				 inputframe.remove(langinfolb);
				 inputframe.remove(ratingsinfolb);
				 inputframe.remove(Directorinfolb);
				 inputframe.remove(wrtierspn);
				 inputframe.remove(actopn);
				 inputframe.remove(synopsistxtlb);
				 inputframe.remove(likedbtn);
				 inputframe.remove(notlikedbtn);
				 inputframe.remove(mapbtn);
				 inputframe.remove(backbtn);
				 inputframe.remove(IDlb);
				 inputframe.remove(MovieGenrelb);
				 inputframe.remove(Durationlb);
				 inputframe.remove(realsedatelb);
				 inputframe.remove(langlb);
				 inputframe.remove(contreylb);
				 inputframe.remove(grossincomelb);
				 inputframe.remove(Ratinglb);
				 inputframe.remove(Listofwriterslb);
				 inputframe.remove(Dirclb);
				 inputframe.remove(Listofactorslb);
				 inputframe.remove(synopsislb);
				 inputframe.remove(canvas);
				 // TODO GET COORDINATES TO PASS IN
				 logansmap(inputframe, Movie);

			 }
		 });
		 
		 backbtn.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				 //Find Stuff to delete
				 inputframe.remove(actorinstructlb);
				 inputframe.remove(Titlelb);
				 inputframe.remove(realsedateinfolb);
				 inputframe.remove(Durationinfrolb);
				 inputframe.remove(MovieGenreinfolb);
				 inputframe.remove(IDinfolb);
				 inputframe.remove(grossinfolb);
				 inputframe.remove(contreyinfolb);
				 inputframe.remove(langinfolb);
				 inputframe.remove(ratingsinfolb);
				 inputframe.remove(Directorinfolb);
				 inputframe.remove(wrtierspn);
				 inputframe.remove(actopn);
				 inputframe.remove(synopsistxtlb);
				 inputframe.remove(likedbtn);
				 inputframe.remove(notlikedbtn);
				 inputframe.remove(mapbtn);
				 inputframe.remove(backbtn);
				 inputframe.remove(IDlb);
				 inputframe.remove(MovieGenrelb);
				 inputframe.remove(Durationlb);
				 inputframe.remove(realsedatelb);
				 inputframe.remove(langlb);
				 inputframe.remove(contreylb);
				 inputframe.remove(grossincomelb);
				 inputframe.remove(Ratinglb);
				 inputframe.remove(Listofwriterslb);
				 inputframe.remove(Dirclb);
				 inputframe.remove(Listofactorslb);
				 inputframe.remove(synopsislb);
				 inputframe.remove(canvas);
				 loginhome(inputframe);

			 }
		 });
		 
		 likedbtn.addActionListener(new ActionListener(){  
	        	public void actionPerformed(ActionEvent e){ 
	        		// @ allen when this button the movie that is being refrencs must be added to the liked movies list
	        		Database.addLikedMovie(currPassword, currUser, IDst);
	        	}  
	        });  
		 
		 notlikedbtn.addActionListener(new ActionListener(){  
	        	public void actionPerformed(ActionEvent e){ 
	        		// @ allen when this button the movie that is being refrencs must be added to the list that will never 
	        		// show up on the recomended movies list again
	        		Database.addDislikedMovie(currPassword,currUser,IDst);
	            }  
	        }); 
		 
		 listactor.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like to add "+item.toString()+" to your liked actors List?");
	                     if(res==0) {
							// @ Allen, this Iteam needs to be added to the liked actors list
							Database.addLikedPerson(currPassword,currUser,((Pair)item).getKey());
	                    	
	                     };
	                  }
	               }
	            }
	         });
		 inputframe.add(actorinstructlb);
		 inputframe.add(Titlelb);
		 inputframe.add(realsedateinfolb);
		 inputframe.add(Durationinfrolb);
		 inputframe.add(MovieGenreinfolb);
		 inputframe.add(IDinfolb);
		 inputframe.add(grossinfolb);
		 inputframe.add(contreyinfolb);
		 inputframe.add(langinfolb);
		 inputframe.add(ratingsinfolb);
		 inputframe.add(Directorinfolb);
		 inputframe.add(wrtierspn);
		 inputframe.add(actopn);
		 inputframe.add(synopsistxtlb);
		 inputframe.add(likedbtn);
		 inputframe.add(notlikedbtn);
		 inputframe.add(mapbtn);
		 inputframe.add(backbtn);
		 inputframe.add(IDlb);
		 inputframe.add(MovieGenrelb);
		 inputframe.add(Durationlb);
		 inputframe.add(realsedatelb);
		 inputframe.add(langlb);
		 inputframe.add(contreylb);
		 inputframe.add(grossincomelb);
		 inputframe.add(Ratinglb);
		 inputframe.add(Listofwriterslb);
		 inputframe.add(Dirclb);
		 inputframe.add(Listofactorslb);
		 inputframe.add(synopsislb);
	     inputframe.add(canvas);
	       
	     inputframe.pack();
	     inputframe.setVisible(true);
	 }
	 public static void Userinfor(JFrame inputframe) {
		 framecount=5;
		 inputframe.getContentPane().setBackground(purple);
		 inputframe.setDefaultCloseOperation(inputframe.EXIT_ON_CLOSE);
		 inputframe.setTitle("Log in start screen");
		 inputframe.setVisible(true);
	     Canvas canvas = new Drawing();
	     canvas.setSize(width, height);
	     
	     JTextField UserNamefld = new JTextField(100);
		 JTextField Passwordfld = new JTextField(100);
		 JTextField UserRealNamefld = new JTextField(100);
		 JTextField UserAgefld = new JTextField(100);
		
		 JButton instructionsbt = new JButton();
		 JButton UserNamebtn = new JButton();
		 JButton  Passwordbtn = new JButton();
		 JButton  UserRealNamebtn = new JButton();
		 JButton  UserAgebtn = new JButton();
		 
		 JLabel Updateuserinfolb = new JLabel();
		 
		 JLabel UpdateMovieinfolb = new JLabel();
		 JLabel UpdateMoviefavoriteinfolb = new JLabel();
		 JLabel UpdateMovielikeinfolb = new JLabel();
		 
		 JLabel UpdateActorinfolb = new JLabel();
		 JLabel UpdatefavoritActorinfolb = new JLabel();
		 JLabel UpdatelikedActorinfolb = new JLabel();
		 
		 JList listslikedmovies = new JList();
		 JList listsfavoritemovie = new JList();
		 JList listslikedactor = new JList();
		 JList listfavoriteactor = new JList();
			 
		 Vector datalikedmovies = new Vector();
		 Vector datafavoritemovie = new Vector();
		 Vector datalikedactor = new Vector();
		 Vector datafavoriteactor = new Vector();
		 
		//@ Allen the four vectors above need to be populated with the corresposding names
		 // if you can not figure out it by the name just asked me 
		 
		 ArrayList<Pair> likedMovies = Database.likedMovie(currPassword,currUser);
		 ArrayList<Pair> likedPeople = Database.likedPeople(currPassword,currUser);
		 ArrayList<Pair> favoriteMovie = Database.favoriteMovie(currPassword,currUser);
		 ArrayList<Pair> favoritePerson = Database.favoritePerson(currPassword,currUser);

		 for(int i = 0; i<likedMovies.size();i++) {
			 datalikedmovies.addElement(likedMovies.get(i));
		 }

		 for(int i = 0; i<likedPeople.size();i++) {
			datalikedactor.addElement(likedPeople.get(i));
		}

		for(int i = 0; i<favoriteMovie.size();i++) {
			datafavoritemovie.addElement(favoriteMovie.get(i));
		}

		for(int i = 0; i<favoritePerson.size();i++) {
			datafavoriteactor.addElement(favoritePerson.get(i));
		}

		 
		 listslikedmovies.setListData(datalikedmovies);
		 listslikedactor.setListData(datalikedactor);
		 listfavoriteactor.setListData(datafavoriteactor);
		 listsfavoritemovie.setListData(datafavoritemovie);
		 if(datafavoritemovie.size()>0) {
			 favoritmoviefound = true;
		 }else {
			 favoritmoviefound = false;
		 }
		 
		 if(datafavoriteactor.size()>0) {
			 favoritactorfound = true;
		 }else {
			 favoritactorfound = false;
		 }
		 
			 
		 JScrollPane likedmoviepn = new JScrollPane(listslikedmovies);
		 JScrollPane favoritmoviepn = new JScrollPane(listsfavoritemovie);
		 JScrollPane likedactorpn= new JScrollPane(listslikedactor);
		 JScrollPane favoritactorpn = new JScrollPane(listfavoriteactor);
		 
		 JButton deletebtn = new JButton();
		 JButton backbtn = new JButton();
		 
		 UserNamefld.setBounds(width/5, height/2-160, 150,50 );
	     UserNamefld.setBackground(teal);
	     UserNamefld.setText(currUser);
	     Border border = BorderFactory.createLineBorder(Color.ORANGE);
	     UserNamefld.setBorder(border);
	        
	     Passwordfld.setBounds(width/5, height/2-60, 150,50 );
	     Passwordfld.setBackground(teal);
	     Passwordfld.setText(currPassword);
	     Border border2 = BorderFactory.createLineBorder(Color.ORANGE);
	     Passwordfld.setBorder(border2);
	        
	     UserRealNamefld.setBounds(width/5, height/2+40, 150,50 );
	     UserRealNamefld.setBackground(teal);
	     UserRealNamefld.setText("change Real Name Here");
	        
	     UserAgefld.setBounds(width/5, height/2+140, 150,50 );
	     UserAgefld.setBackground(teal);
	     UserAgefld.setText("change Age Here");
	     
	     UserNamebtn.setText("Change User \nName");
	     UserNamebtn.setBounds(3*width/10,height/2-160, 150, 50);
	     UserNamebtn.setBackground(yellow);
	     
	     Passwordbtn.setText("Change Password");
	     Passwordbtn.setBounds(3*width/10,height/2-60, 150, 50);
	     Passwordbtn.setBackground(yellow);
	     
	     UserRealNamebtn.setText("Change Real \nName");
	     UserRealNamebtn.setBounds(3*width/10,height/2+40, 150, 50);
	     UserRealNamebtn.setBackground(yellow);
	     
	     UserAgebtn.setText("Change User \nAge");
	     UserAgebtn.setBounds(3*width/10,height/2+140, 150, 50);
	     UserAgebtn.setBackground(yellow);
	     
	     Updateuserinfolb.setForeground(white);
	     Updateuserinfolb.setOpaque(true);
	     Updateuserinfolb.setBackground(grey);
	     Updateuserinfolb.setText(" User Information");
	     Updateuserinfolb.setBounds(5*width/20-25, 180, 200, 50);
	     Updateuserinfolb.setFont(new Font("Courier", Font.BOLD,17));
	     
	     UpdateMovieinfolb.setForeground(white);
	     UpdateMovieinfolb.setOpaque(true);
	     UpdateMovieinfolb.setBackground(grey);
	     UpdateMovieinfolb.setText(" Movie Information");
	     UpdateMovieinfolb.setBounds(141*width/320-25, 180, 200, 50);
	     UpdateMovieinfolb.setFont(new Font("Courier", Font.BOLD,17));
	     
	     UpdateMoviefavoriteinfolb.setForeground(white);
	     UpdateMoviefavoriteinfolb.setOpaque(true);
	     UpdateMoviefavoriteinfolb.setBackground(grey);
	     UpdateMoviefavoriteinfolb.setText(" Favorite Movies");
	     UpdateMoviefavoriteinfolb.setBounds(141*width/320, 650, 150, 50);
	     UpdateMoviefavoriteinfolb.setFont(new Font("Courier", Font.BOLD,15));
	     
	     UpdateMovielikeinfolb.setForeground(white);
	     UpdateMovielikeinfolb.setOpaque(true);
	     UpdateMovielikeinfolb.setBackground(grey);
	     UpdateMovielikeinfolb.setText("  Liked Movies");
	     UpdateMovielikeinfolb.setBounds(141*width/320, 250, 150, 50);
	     UpdateMovielikeinfolb.setFont(new Font("Courier", Font.BOLD,15));
	     
	     UpdateActorinfolb.setForeground(white);
	     UpdateActorinfolb.setOpaque(true);
	     UpdateActorinfolb.setBackground(grey);
	     UpdateActorinfolb.setText(" Actor Information");
	     UpdateActorinfolb.setBounds(79*width/128, 180, 200, 50);
	     UpdateActorinfolb.setFont(new Font("Courier", Font.BOLD,17));
	     
	     UpdatefavoritActorinfolb.setForeground(white);
	     UpdatefavoritActorinfolb.setOpaque(true);
	     UpdatefavoritActorinfolb.setBackground(grey);
	     UpdatefavoritActorinfolb.setText(" Favorite Actors");
	     UpdatefavoritActorinfolb.setBounds(81*width/128, 650, 150, 50);
	     UpdatefavoritActorinfolb.setFont(new Font("Courier", Font.BOLD,15));
	     
	     UpdatelikedActorinfolb.setForeground(white);
	     UpdatelikedActorinfolb.setOpaque(true);
	     UpdatelikedActorinfolb.setBackground(grey);
	     UpdatelikedActorinfolb.setText("  Liked Actors");
	     UpdatelikedActorinfolb.setBounds(81*width/128, 250, 150, 50);
	     UpdatelikedActorinfolb.setFont(new Font("Courier", Font.BOLD,15));
	     
	     likedactorpn.setBounds(81*width/128, 310, 150, 300);
	     favoritactorpn.setBounds(81*width/128, 710, 150, 100);
	     likedmoviepn.setBounds(141*width/320, 310, 150, 300);
	     favoritmoviepn.setBounds(141*width/320, 710, 150, 100);
	     
	     deletebtn.setText("Delete Account");
	     deletebtn.setBounds(4*width/5, height/2-50, 200, 100);
	     deletebtn.setBackground(yellow);
		 
		 backbtn.setText("Back");
		 backbtn.setBounds(100, height/2-50, 200, 100);
		 backbtn.setBackground(yellow);
		 
		 instructionsbt.setText("Instructions");
		 instructionsbt.setBounds(650, height-150, 600, 75);
		 instructionsbt.setBackground(yellow);
		 instructionsbt.setFont(new Font("Courier", Font.BOLD,20));
		 
		 

		 
		 backbtn.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				 inputframe.remove(instructionsbt);
				 inputframe.remove(deletebtn);
			     inputframe.remove(backbtn);
			     inputframe.remove(likedmoviepn);
			     inputframe.remove(favoritmoviepn);
			     inputframe.remove(likedactorpn);
			     inputframe.remove(favoritactorpn);
			     inputframe.remove(UpdatefavoritActorinfolb);
			     inputframe.remove(UpdatelikedActorinfolb);
			     inputframe.remove(UpdateActorinfolb);
			     inputframe.remove(UpdateMovielikeinfolb);
			     inputframe.remove(UpdateMoviefavoriteinfolb);
			     inputframe.remove(UpdateMovieinfolb);
			     inputframe.remove(Updateuserinfolb);
			     inputframe.remove(UserNamebtn);
			     inputframe.remove(Passwordbtn);
			     inputframe.remove(UserRealNamebtn);
			     inputframe.remove(UserAgebtn);
			     inputframe.remove(UserNamefld);
			     inputframe.remove(Passwordfld);
			     inputframe.remove(UserRealNamefld);
			     inputframe.remove(UserAgefld);
			     inputframe.remove(canvas);
			     loginhome(inputframe);

			 }
		 });
		 
		 deletebtn.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
                 int res = JOptionPane.showConfirmDialog(null, "Are you sure you would like to delete Your User Account?");
                 if(res==0) {
                	 // hey allen this is where the current user will need to be removed
                	 // so just removie this user tuple from the list
                	 Database.deleteUser(currUser,currPassword);
                	 inputframe.remove(instructionsbt);
                	 inputframe.remove(deletebtn);
    			     inputframe.remove(backbtn);
    			     inputframe.remove(likedmoviepn);
    			     inputframe.remove(favoritmoviepn);
    			     inputframe.remove(likedactorpn);
    			     inputframe.remove(favoritactorpn);
    			     inputframe.remove(UpdatefavoritActorinfolb);
    			     inputframe.remove(UpdatelikedActorinfolb);
    			     inputframe.remove(UpdateActorinfolb);
    			     inputframe.remove(UpdateMovielikeinfolb);
    			     inputframe.remove(UpdateMoviefavoriteinfolb);
    			     inputframe.remove(UpdateMovieinfolb);
    			     inputframe.remove(Updateuserinfolb);
    			     inputframe.remove(UserNamebtn);
    			     inputframe.remove(Passwordbtn);
    			     inputframe.remove(UserRealNamebtn);
    			     inputframe.remove(UserAgebtn);
    			     inputframe.remove(UserNamefld);
    			     inputframe.remove(Passwordfld);
    			     inputframe.remove(UserRealNamefld);
    			     inputframe.remove(UserAgefld);
    			     inputframe.remove(canvas);
                	 loginscreen(inputframe);
                 }
				
			 }
		 });
		 
		 UserNamebtn.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){ 
				 String UpdatedUserName = UserNamefld.getText();
				 settingchange = false;
				 if(!Database.verifyNewUser(UpdatedUserName)) {
					 JOptionPane.showMessageDialog(null, "I am sorry, The UserName "+UpdatedUserName+""
								+ " is aready taken. Please try another ");
					return;
				 }
				 Database.changeUsername(UpdatedUserName,currUser,currPassword);
				 settingchange = true;
				 currUser = UpdatedUserName;
				 // @ allen this is when the user want to change there user name
				 // so check if its allowed to be changed to that 
				 // return a boolean to make sure its okay 
				 // when that is done ill through a message if its not acctable
				 
				 
				
			 }
		 });
		 
		 Passwordbtn.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){ 
				 String Updatedpassword = Passwordfld.getText();
				 Database.changePassword(Updatedpassword,currUser,currPassword);
				 settingchange = true;
				 currPassword = Updatedpassword;
				 //@Allen user want to change password
				 
				
			 }
		 });
		 
		 UserRealNamebtn.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){ 
				 String UpdatedRealName = UserRealNamefld.getText();
				 // @ Allen User wants to change the real name
				 Database.changeName(UpdatedRealName,currUser,currPassword);
				 settingchange = true;
				 
				
			 }
		 });
		 
		 UserAgebtn.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){ 
				 String UpdatedUserage = UserAgefld.getText();
				 settingchange=false;
				 try {
					 int newage = Integer.parseInt(UpdatedUserage);
					 Database.changeAge(newage,currUser,currPassword);
					 settingchange=true;
				 } catch(NumberFormatException ne) {
					 JOptionPane.showMessageDialog(null, "I am sorry, "+UpdatedUserage+""
								+ " is not an integer. Please try again ");
					 return;
				 }
				 // @ Allen The user want to change the age
				 // check to make sure it is and int and just assigne it to a boolne
				 // and ill throw the message after ur done
				 
				
			 }
		 });
		 
		 instructionsbt.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){ 
				 
				 JOptionPane.showMessageDialog(null, "TO UPDATE USERNAME/PASSWORD/REAL NAME/AGE \n1)"
				 		+ "Type in new information and click corresponding Submit(yellow) \n "
				 		+ "\nTO DELETE A LIKED MOVIE/ACTOR \n"
				 		+ "1) Click on corresponding list"
				 		+ "\n2) Click on Movie/Actor you wish to remove"
				 		+ "\n3) Click Yes on popup test box \n \n"
				 		+ "TO DELETE FAVORITE MOVIE/ACTOR \n"
				 		+ "1) Click on Favorite Movie/Actor "
				 		+ "\n2) Click Yes on popup test box\n \n"
				 		+ "TO ADD A NEW FAVORITE MOVIE/ACTOR\n"
				 		+ "1) Delete corresponding Favorite Movie/Actor\n"
				 		+ "2) Click on your favorite Actor/Movie from your Liked tab\n"
				 		+ "2) Click Add To Favorite? on popup test box",
				 		"Instructions", JOptionPane.INFORMATION_MESSAGE);
				
			 }
		 });
		  
		 listslikedmovies.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     //int res = JOptionPane.showConfirmDialog(null, "Would you like to remove "+item.toString()+" from your Liked Movies");
	                     Object[] options = {"Yes","No", "Add to Favorites? "};
	                     int res = 4;
	                     if(!favoritmoviefound) {
		                      res = JOptionPane.showOptionDialog(null,"Would you like to remove "+item.toString()+" from your Liked Movies","",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, options,options[0]); 

	                     }else {
		                      res = JOptionPane.showConfirmDialog(null, "Would you like to remove "+item.toString()+" from your Liked Movies");
	                     }
	                     if(res==0) {
	                    	 //listliked.setSelectedIndex(0);
	                    	 //@allen this item object needs to be removed from te like movie list
	                    	 Database.removeLikedMovie(((Pair)item).getKey(),currUser,currPassword);
	                    	 datalikedmovies.remove(item);
	                    	 listslikedmovies.setListData(datalikedmovies);
	                    	
	                     }else if(res==2 && !favoritmoviefound) {
	                    	 //@ allen this means that the item object needs to be added to
	                    	 // the favorit movie
	                    	 favoritmoviefound = true;
	                    	 Database.addFavMovie(currPassword,currUser,((Pair)item).getKey());
	                    	 datafavoritemovie.add(item);
	                    	 listsfavoritemovie.setListData(datafavoritemovie);
 
	                     };
	                  }
	               }
	            }
	         });
		 
		 listslikedactor.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     Object[] options = {"Yes","No", "Add to Favorites? "};
	                     int res = 4;
	                     if(!favoritactorfound) {
		                      res = JOptionPane.showOptionDialog(null,"Would you like to remove "+item.toString()+" from your Liked Actors?","",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, options,options[0]); 

	                     }else {
		                      res = JOptionPane.showConfirmDialog(null, "Would you like to remove "+item.toString()+" from your Liked Actors?");
	                     }
	                     if(res==0) {
	                    	 //listliked.setSelectedIndex(0);
	                    	 //@allen this item object needs to be removed from te like actor list
	                    	 Database.removeLikedPerson(((Pair)item).getKey(),currUser,currPassword);
	                    	 datalikedactor.remove(item);
	                    	 listslikedactor.setListData(datalikedactor);
	                    	
	                     }else if(res==2 && !favoritactorfound ) {
	                    	 //@ allen this means that the item object needs to be added to
	                    	 // the favorit actor
	                    	 favoritactorfound = true;
	                    	 Database.addFavPerson(currPassword,currUser,((Pair)item).getKey());
	                    	 datafavoriteactor.add(item);
	                    	 listfavoriteactor.setListData(datafavoriteactor); 
	                     };
	                  }
	               }
	            }
	         });
		 listsfavoritemovie.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like to remove "+item.toString()+"from Your Favorite Movies?");
	                     if(res==0) {
	                    	 favoritmoviefound = false;
	                    	 Database.removeFavMovie(((Pair)item).getKey(),currUser,currPassword);
	                    	 datafavoritemovie.remove(item);
	                    	 listsfavoritemovie.setListData(datafavoritemovie);
	                    	 // @ allen this needs to removve this movie from the favorit list
	                    	
	                     }
	                  }
	               }
	            }
	         });
		 listfavoriteactor.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like to remove "+item.toString()+"from Your Favorite Movies?");
	                     if(res==0) {
	                    	 favoritactorfound = false;
	                    	 Database.removeFavPerson(((Pair)item).getKey(),currUser,currPassword);
	                    	 datafavoriteactor.remove(item);
	                    	 listfavoriteactor.setListData(datafavoriteactor);
	                    	 //@ allen this need to remove the actor from favroti list
	                    	
	                     }
	                  }
	               }
	            }
	         });
		 UserNamefld.addFocusListener(new FocusListener() {
	        	public void focusGained(FocusEvent e) {
	        		UserNamefld.setText(""); 
	        	}
	        	public void focusLost(FocusEvent e) {

	        	}
	        	});
	    Passwordfld.addFocusListener(new FocusListener() {
	        	public void focusGained(FocusEvent e) {
	        		Passwordfld.setText(""); 
	        	}
	        	public void focusLost(FocusEvent e) {

	        	}
	        	}); 
	    UserRealNamefld.addFocusListener(new FocusListener() {
	        	public void focusGained(FocusEvent e) {
	        		UserRealNamefld.setText(""); 
	        	}
	        	public void focusLost(FocusEvent e) {

	        	}
	        	}); 
	    UserAgefld.addFocusListener(new FocusListener() {
	        	public void focusGained(FocusEvent e) {
	        		UserAgefld.setText(""); 
	        	}
	        	public void focusLost(FocusEvent e) {

	        	}
	        	});  
		 
		 
		 
	    
	   
		 inputframe.add( instructionsbt);
		 inputframe.add(deletebtn);
	     inputframe.add(backbtn);
	     inputframe.add(likedmoviepn);
	     inputframe.add(favoritmoviepn);
	     inputframe.add(likedactorpn);
	     inputframe.add(favoritactorpn);
	     inputframe.add(UpdatefavoritActorinfolb);
	     inputframe.add(UpdatelikedActorinfolb);
	     inputframe.add(UpdateActorinfolb);
	     inputframe.add(UpdateMovielikeinfolb);
	     inputframe.add(UpdateMoviefavoriteinfolb);
	     inputframe.add(UpdateMovieinfolb);
	     inputframe.add(Updateuserinfolb);
	     inputframe.add(UserNamebtn);
	     inputframe.add(Passwordbtn);
	     inputframe.add(UserRealNamebtn);
	     inputframe.add(UserAgebtn);
	     inputframe.add(UserNamefld);
	     inputframe.add(Passwordfld);
	     inputframe.add(UserRealNamefld);
	     inputframe.add(UserAgefld);
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
		 public static void logansmap(JFrame inputframe, Object Movie) {
			 Random rand = new Random();
			 framecount =2;
			 inputframe.getContentPane().setBackground(Color.CYAN);
			 inputframe.setDefaultCloseOperation(inputframe.EXIT_ON_CLOSE);
			 inputframe.setTitle("Map Screen");
			 inputframe.setVisible(true);
			 JButton backbtn = new JButton();
			 JTextArea Informationfld = new JTextArea();
			 double xoffset = -70;
			 double yoffset = 70;
			 
			 
			 
			 backbtn.setText("Back");
			 backbtn.setBounds(width-300, height/2-50, 200, 100);
			 backbtn.setBackground(yellow);
			 
			 Informationfld.setForeground(white);
			 Informationfld.setOpaque(true);
			 Informationfld.setBackground(grey);
			 Informationfld.setText("Each Dot Represents\nan Actor's Birthplace\nThe Size of the dot\nrepresents the order\nbilled in the movie");
			 Informationfld.setBounds(30, 500, 250, 150);
			 Informationfld.setFont(new Font("Courier", Font.BOLD,15));
			 
			 
			 
	    	 ArrayList<Double> longtemp =new ArrayList<Double>();
	    	 ArrayList<Double> lattemp = new ArrayList<Double>();
	    	 longtemp.add(-119.417931);
	    	 longtemp.add(-89.500000);
	    	 longtemp.add(2.349014);
	    	 longtemp.add(151.209900);
	    	 longtemp.add(12.496366);
	    	 longtemp.add(-153.369141);
	    	 longtemp.add(46.460938);
	    	 longtemp.add(-46.625290);
	    	 longtemp.add(139.839478);
	    	 lattemp.add(36.778259);
	    	 lattemp.add(44.500000);
	    	 lattemp.add(48.864716);
	    	 lattemp.add(-33.865143);
	    	 lattemp.add(41.902782);
	    	 lattemp.add(66.160507);
	    	 lattemp.add(-19.002846);
	    	 lattemp.add(-23.533773);
	    	 lattemp.add(35.652832);
	    	 
		     for(int i=0; i< lattemp.size();i++) {
		    	 
		    	 
		    	 double longvale = longtemp.get(i);
		    	 double latvale = lattemp.get(i);
		    	 // the further down we go the larger the y off set needs to be
		    	 // 
		    	 yoffset = Math.sqrt((Math.abs(latvale)+ 180 -Math.abs(longvale)))+70;
		    	 if(latvale<0) {
		    		 yoffset = yoffset+40;
		    	 }
		    	 longcord.add((double) (((180 +longvale)*width/360.0)+xoffset));
		    	 latcord.add((double) (height-((90+ latvale)*height/180.0)+yoffset));
		    	 
		    	// yoffset = 70;
		    	 
		    	 //longcord.add((double) (((180 +(rand.nextInt(360)-180)*1725/360))));
		    	// longcord.add((double)(rand.nextInt(361)*(width/360.0)));
		    	// latcord.add((double)(height-(rand.nextInt(181)*(height/180.0))));
		    	 //latcord.add((double) (height-((90+(rand.nextInt(180)-90)*996/180))));
		    	 //System.out.println(1725/2+Math.cos(-119.417931*Math.PI /180)*1725/2);
		    	 //System.out.println(996-(996/2+Math.sin(36.778259*Math.PI /180)*996/2));
		    	 //longcord.add(1725/2+Math.cos(-89.500000*Math.PI /180)*1725/2);
		    	 //latcord.add(996-(996/2+Math.sin(44.500000*Math.PI /180)*996/2));
//		    	 longcord.add((double) (((180 -119.417931)*1725/360)));
//		    	 latcord.add((double) (height-((90+36.778259)*996/180)));
//		    	 int FOCAL_LENGTH = 1750;
//		    	 double latitude = Double.valueOf(36.778259);
//		    	    double longitude = Double.valueOf(-119.417931);
//
//		    	    latitude = latitude * Math.PI / 180;
//		    	    longitude = longitude * Math.PI / 180;
//
//		    	    double x = 1750 * Math.sin(latitude) * Math.cos(longitude);
//		    	    double y = 950 * Math.sin(latitude) * Math.sin(longitude);
//		    	    double z = 6371 * Math.cos(latitude);
//
//		    	    double projectedX = x;
//		    	    double projectedY = y;
//		    	    projectedX+=950;
//		    	    projectedY+=500;
//		    	    
//		    	    System.out.println(projectedX);
//		    	    System.out.println(projectedY);
//		    	    
//		    	    longcord.add(projectedX);
//		    	    latcord.add(projectedY);
		    	    
		     }
		    
		     Canvas canvas = new Drawing();
		     canvas.setSize(width, height);
		     
		     backbtn.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e){
					 inputframe.remove(Informationfld);
					 inputframe.remove(backbtn);
				     inputframe.remove(canvas);
				     longcord = new ArrayList<Double>();
				     latcord =new ArrayList<Double>();
					 selectedMovie(inputframe, Movie);
					 

				 }
			 });
		    
		     inputframe.add(Informationfld);
		     inputframe.add(backbtn);
		     inputframe.add(canvas); 
		     inputframe.pack();
		     inputframe.setVisible(true);
			 
		        
		 }
		 
	}
