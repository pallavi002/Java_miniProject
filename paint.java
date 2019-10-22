import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class paint{
	public static void main(String[] args){
		Icon iconB = new ImageIcon("images/blue.png");			//the blue image icon
		Icon iconM = new ImageIcon("images/pink.png");		//magenta image icon
		Icon iconR = new ImageIcon("images/red.png");			//red image icon
		Icon iconBl = new ImageIcon("images/black.png");       //black image icon
		Icon iconG = new ImageIcon("images/green.png");		//green image icon
		Icon iconW = new ImageIcon("images/white.png");
		//These will be the images for our colors.
		
		JFrame frame = new JFrame("Paint It...   By Pallavi Lodhi");			//Creates a frame with a title of "Paint it"
		
		Container content = frame.getContentPane();		//Creates a new container
		content.setLayout(new BorderLayout());			//sets the layout
		
		final PadDraw drawPad = new PadDraw();			//creates a new padDraw, which is pretty much the paint program
		
		content.add(drawPad, BorderLayout.CENTER);		//sets the padDraw in the center
		
		JPanel panel = new JPanel();				//creates a JPanel
		panel.setPreferredSize(new Dimension(60, 68));
		panel.setMinimumSize(new Dimension(60, 68));
		panel.setMaximumSize(new Dimension(60, 68));		//This sets the size of the panel
		
		JButton clearButton = new JButton("WHITE");		//creates the clear button and sets the text as "Clear"
		clearButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				drawPad.clear();
			}
		});
		
		JButton clearrButton = new JButton("BLACK");		//creates the clear button and sets the text as "Clear"
		clearrButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				drawPad.clearr();
			}
		});
									//this is the clear button, which clears the screen.  This pretty
									//much attaches an action listener to the button and when the
									//button is pressed it calls the clear() method
		
		JButton redButton = new JButton(iconR);			//creates the red button and sets the icon we created for red
		redButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				drawPad.red();
			}

		});							//when pressed it will call the red() method.  
		
		JButton blackButton = new JButton(iconBl);		//when pressed it will call the black() method.
		blackButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				drawPad.black();
			}
		});
        
		
		JButton magentaButton = new JButton(iconM);		//when pressed it will call the magenta() method.
		magentaButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				drawPad.magenta();
			}
		});
		
		JButton blueButton = new JButton(iconB);		//when pressed it will call the blue() method.
		blueButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				drawPad.blue();
			}
		});
		
		JButton greenButton = new JButton(iconG);		//when pressed it will call the green() method.
		greenButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				drawPad.green();
			}
		});
		
		JButton whiteButton = new JButton(iconW);		//when pressed it will call the white() method.
		whiteButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				drawPad.white();
			}
		});
		blackButton.setPreferredSize(new Dimension(16, 16));
		magentaButton.setPreferredSize(new Dimension(16, 16));
		redButton.setPreferredSize(new Dimension(16, 16));
		blueButton.setPreferredSize(new Dimension(16, 16));
		greenButton.setPreferredSize(new Dimension(16,16));	//sets the sizes of the buttons
		whiteButton.setPreferredSize(new Dimension(16,16));

		
		panel.add(greenButton);
		panel.add(blueButton);
		panel.add(magentaButton);
		panel.add(blackButton);
		panel.add(redButton);
		panel.add(whiteButton);
		panel.add(clearButton);					//adds the buttons to the panel
		panel.add(clearrButton);
		
		content.add(panel, BorderLayout.WEST);			//sets the panel to the left
		
		frame.setSize(3000, 3000);				//sets the size of the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//makes it so you can close
		frame.setVisible(true);					//makes it so you can see it
	}
}


class PadDraw extends JComponent{
	Image image;
	//this is gonna be your image that you draw on
	Graphics2D graphics2D;
	//this is what we'll be using to draw on
	int currentX, currentY, oldX, oldY;
	//these are gonna hold our mouse coordinates

	//Now for the constructors
	public PadDraw(){
		setDoubleBuffered(false);
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				oldX = e.getX();
				oldY = e.getY();
			}
		});
		//if the mouse is pressed it sets the oldX & oldY
		//coordinates as the mouses x & y coordinates
		addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){
				currentX = e.getX();
				currentY = e.getY();
				if(graphics2D != null)
				graphics2D.drawLine(oldX, oldY, currentX, currentY);
				repaint();
				oldX = currentX;
				oldY = currentY;
			}

		});
		//while the mouse is dragged it sets currentX & currentY as the mouses x and y
		//then it draws a line at the coordinates
		//it repaints it and sets oldX and oldY as currentX and currentY
	}

	public void paintComponent(Graphics g){
		if(image == null){
			image = createImage(getSize().width, getSize().height);
			graphics2D = (Graphics2D)image.getGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			clear();

		}
		g.drawImage(image, 0, 0, null);
	}
	//this is the painting bit
	//if it has nothing on it then
	//it creates an image the size of the window
	//sets the value of Graphics as the image
	//sets the rendering
	//runs the clear() method
	//then it draws the image


	public void clear(){
		graphics2D.setPaint(Color.white);
		graphics2D.fillRect(0, 0, getSize().width, getSize().height);
		graphics2D.setPaint(Color.black);
		repaint();
	}
	
	
	public void clearr(){
		graphics2D.setPaint(Color.black);
		graphics2D.fillRect(0, 0, getSize().width, getSize().height);
		graphics2D.setPaint(Color.white);
		repaint();
	}
	//this is the clear
	//it sets the colors as white
	//then it fills the window with white
	//thin it sets the color back to black
	public void red(){
		graphics2D.setPaint(Color.red);
		repaint();
	}
	//this is the red paint
	public void black(){
		graphics2D.setPaint(Color.black);
		repaint();
	}
	//black paint
	public void magenta(){
		graphics2D.setPaint(Color.magenta);
		repaint();
	}
	//magenta paint
	public void blue(){
		graphics2D.setPaint(Color.blue);
		repaint();
	}
	//blue paint
	public void green(){
		graphics2D.setPaint(Color.green);
		repaint();
	}
	//green paint
	public void white(){
		graphics2D.setPaint(Color.white);
		repaint();
	}

}
