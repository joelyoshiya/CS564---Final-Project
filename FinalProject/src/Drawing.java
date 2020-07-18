import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
		loginscreen(frameOne);
		
        
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
       else if(framecount == 1) {
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(10, 150, width/5, height-300);
    	   g.fillOval(2*width/5+ 10, 150, width/5, height-300);
    	   g.fillOval(4*width/5+ 10, 150, width/5, height-300);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(1*width/5+ 10, 150, width/5, height-300);
    	   g.fillOval(3*width/5+ 10, 150, width/5, height-300);
       }
       else if(framecount==2) {
    	   
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
		 JLabel Newuserinfor = new JLabel();
		 JLabel likedMovielb = new JLabel();
		 JLabel favMovielb = new JLabel();
		 JLabel likedactorslb = new JLabel();
		 JLabel favactorslb = new JLabel();
		 JTextField UserNamefld = new JTextField(100);
		 JTextField Passwordfld = new JTextField(100);
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
	        likedMovielb.setText("    Liked Movies");
	        likedMovielb.setBounds(width/5+110, 200, 200, 50);
	        likedMovielb.setFont(new Font("Courier", Font.BOLD,15));
	        
	        favMovielb.setForeground(white);
	        favMovielb.setOpaque(true);
	        favMovielb.setBackground(grey);
	        favMovielb.setText("     Favorite Movies");
	        favMovielb.setBounds(2*width/5+110, 200, 200, 50);
	        favMovielb.setFont(new Font("Courier", Font.BOLD,15));
	        
	        likedactorslb.setForeground(white);
	        likedactorslb.setOpaque(true);
	        likedactorslb.setBackground(grey);
	        likedactorslb.setText("    Liked Actors");
	        likedactorslb.setBounds(3*width/5+110, 200, 200, 50);
	        likedactorslb.setFont(new Font("Courier", Font.BOLD,15));
	        
	        favactorslb.setForeground(white);
	        favactorslb.setOpaque(true);
	        favactorslb.setBackground(grey);
	        favactorslb.setText("     Favorite Actors");
	        favactorslb.setBounds(4*width/5+110, 200, 200, 50);
	        favactorslb.setFont(new Font("Courier", Font.BOLD,15));
	        
	        
	        
	        
	        
	        //************* this is the setting up user log in infor tab*******//
	        UserNameLb.setForeground(white);
	        UserNameLb.setOpaque(true);
	        UserNameLb.setBackground(grey);
	        UserNameLb.setText("User Name: ");
	        UserNameLb.setBounds(40, height/2-150, 150, 50);
	        UserNameLb.setFont(new Font("Courier", Font.BOLD,15));
	        
	        PasswordLb.setForeground(white);
	        PasswordLb.setOpaque(true);
	        PasswordLb.setBackground(grey);
	        PasswordLb.setText("User Password: ");
	        PasswordLb.setBounds(40, height/2-50, 150, 50);
	        PasswordLb.setFont(new Font("Courier", Font.BOLD,15));
	        
	        UserNamefld.setBounds(190, height/2-150, 150,50 );
	        UserNamefld.setBackground(teal);
	        UserNamefld.setText("Enter User Name Here");
	        Border border = BorderFactory.createLineBorder(Color.ORANGE);
	        UserNamefld.setBorder(border);
	        
	        Passwordfld.setBounds(190, height/2-50, 150,50 );
	        Passwordfld.setBackground(teal);
	        Passwordfld.setText("Enter Password Here");
	        Border border2 = BorderFactory.createLineBorder(Color.ORANGE);
	        Passwordfld.setBorder(border2);
	        
	      //************* this is the setting up user log in infor tab*******/
	       
	        searchedformovieslb.setForeground(white);
	        searchedformovieslb.setOpaque(true);
	        searchedformovieslb.setBackground(grey);
	        searchedformovieslb.setText("Search results:");
	        searchedformovieslb.setBounds(40+width/5, height/2-100, 120, 40);
	        searchedformovieslb.setFont(new Font("Courier", Font.BOLD,12));
	        
	        likedmoviedlb.setForeground(white);
	        likedmoviedlb.setOpaque(true);
	        likedmoviedlb.setBackground(grey);
	        likedmoviedlb.setText("Liked Movies:");
	        likedmoviedlb.setBounds(280+width/5, height/2-100, 120, 40);
	        likedmoviedlb.setFont(new Font("Courier", Font.BOLD,12));
	        
	        
	        
	        
	        JList  list = new JList();
	        Vector data = new Vector();
	        list.setSelectedIndex(0);
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
	        
	        JScrollPane serchedmovies = new JScrollPane(list);
	        serchedmovies.setBounds(40+width/5,height/2-50,100,200);

	       
	       
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
	        
	        JScrollPane likedmovies = new JScrollPane(listliked);
	        likedmovies.setBounds(280+width/5,height/2-50,100,200);

	        
	        
	        
	        
	        
	        
	        Searchmoviefld.setBounds(40+width/5, height/2-150, 150,50 );
	        Searchmoviefld.setBackground(teal);
	        Searchmoviefld.setText("Search Movie name here");
	        
	        
	        
	        
	        searchmoviebtn.setText("Search Movie");
	        searchmoviebtn.setBounds(230+width/5,height/2-150, 150, 50);
	        searchmoviebtn.setBackground(yellow);
	       // addWindowListener(this);
	        searchmoviebtn.addActionListener(new ActionListener(){  
	        	public void actionPerformed(ActionEvent e){ 
	        		System.out.println("search botton was pushed");
	        		data.addElement("India");
	    	        data.addElement("Australia");
	    	        data.addElement("England");
	    	        data.addElement("England");
	    	        data.addElement("New Zealand");
	    	        data.addElement("South Africa");
	    	        list.setListData(data);
	            }  
	        });  
	        
	        
	        
	      //************* this is the settings for liked movies************************//  
	        
	      // this is the setting for the favorite Movie**********************************//
	        
	        
	        likedmovielb2.setForeground(white);
	        likedmovielb2.setOpaque(true);
	        likedmovielb2.setBackground(grey);
	        likedmovielb2.setText("Liked Movies: ");
	        likedmovielb2.setBounds(40+2*width/5, height/2-100, 120, 40);
	        likedmovielb2.setFont(new Font("Courier", Font.BOLD,12));
	        
	        favoritmovelb.setForeground(white);
	        favoritmovelb.setOpaque(true);
	        favoritmovelb.setBackground(grey);
	        favoritmovelb.setText("Favorite Movie:");
	        favoritmovelb.setBounds(280+2*width/5, height/2-100, 120, 40);
	        favoritmovelb.setFont(new Font("Courier", Font.BOLD,12));
	        
	        
	        
	       
	        JScrollPane likedmovies2 = new JScrollPane(listliked2);
	        likedmovies2.setBounds(40+2*width/5,height/2-50,100,200);
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
	        
	        JScrollPane favoritmovies = new JScrollPane(listfavoritmovie);
	        favoritmovies.setBounds(280+2*width/5,height/2-50,100,100);
	        
	        
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
	        		
	        		
	        		if(true) {
	        			inputframe.remove(UserNamefld);
		        		inputframe.remove(Passwordfld);
		        		inputframe.remove(UserNameLb);
		        		inputframe.remove(PasswordLb);
		        		inputframe.remove(loginbtn);
		        		inputframe.remove(newuserbtn);
		        		inputframe.remove(canvas);
		        		loginhome(inputframe);
	        		}
	        		
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
			inputframe.setTitle("Log in start screen");
			inputframe.setVisible(true);
	        Canvas canvas = new Drawing();
	        canvas.setSize(width, height);
	        
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
