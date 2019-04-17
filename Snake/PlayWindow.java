import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.util.Random;

public class PlayWindow extends JFrame  implements KeyListener, ActionListener
{
	int ROWS = 20;
	int COLS = 20;
	private final int TIME_DELAY = 500;
	int key;
	int score=0;

	int timerDelayer = 0;
	int appleAmount, prevKey, rSize, cSize;
	boolean appleActive= false;
	boolean paused = false;

	boolean goingUp = true;
	boolean goingDown = false;
	boolean goingLeft = false;
	boolean goingRight = false;

	private JLabel [][] screen;
	private JLabel positionLabel;
	private JFrame frame;
	private JPanel basePanel, playAreaPanel, scorePanel;
	private JButton pauseButton;

	private Snake mySnake;
    private SnakeElement apple;
	private SnakeElement snakeHead;
	private SnakeElement oldSnakeHead;
	private javax.swing.Timer timer;

	PlayWindow(int rSize,int cSize)
	{
		super("Welcome to Snake");

		if (rSize/40<20)
		{
			ROWS =20;
		}
		else
		{
			ROWS = rSize/40;
		}
		if (cSize/40<20)
		{
			COLS =20;
		}
		else
		{
			COLS=cSize/40;
		}
		this.rSize=rSize;
		this.cSize=cSize;

		screen = new JLabel[ROWS][COLS];

		frame = this;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize (rSize,cSize);

		scorePanel = new JPanel();
		positionLabel = new JLabel("Score: "+ score );
		scorePanel.add(positionLabel);
		pauseButton = new JButton("Pause");
	    pauseButton.addActionListener(this);
		pauseButton.setFocusable(false);
		scorePanel.add(pauseButton);

		playAreaPanel = new JPanel();
	    playAreaPanel.setSize (rSize/50,cSize/50);
		GridLayout playLayout = new GridLayout(ROWS,COLS,0,0);
		
		playAreaPanel.setLayout(playLayout);
		playAreaPanel.addKeyListener(this);
		ImageIcon iconLogo = new ImageIcon("dot_logo.png");
		
		for (int row = 0; row < ROWS; row ++)
			for (int col = 0; col< COLS; col ++)
			{
				screen[row][col] = new JLabel();
				screen[row][col].setIcon(iconLogo);
				playAreaPanel.add(screen[row][col]);
				screen[row][col].setVisible(false);
			}

		oldSnakeHead = new SnakeElement();
		snakeHead = new SnakeElement();
		mySnake = new Snake();
		oldSnakeHead = mySnake.createInitialSnake(ROWS/2,COLS/2,5);
		basePanel =new JPanel();
		BorderLayout bLayout = new BorderLayout();
		basePanel.setLayout(bLayout);
		basePanel.add(scorePanel, BorderLayout.NORTH);
		basePanel.add(playAreaPanel, BorderLayout.CENTER);
		frame.add(basePanel);
		playAreaPanel.setFocusable(true);
		playAreaPanel.requestFocusInWindow();
		frame.setVisible(true);
		timer = new javax.swing.Timer(TIME_DELAY, new TimerListener());
        timer.start();
		addApple();
	}
	
	public void addApple()
	{
		Random randomNumbers = new Random();
		int appleRow = 0;
		int appleCol = 0;
		
		if (appleAmount==1)
		{
			appleAmount=0;
		}
		
		boolean applePlanted = false;
		
		while (!applePlanted)
		{
			appleRow = randomNumbers.nextInt(ROWS);
			appleCol = randomNumbers.nextInt(COLS);
			apple = new SnakeElement(appleRow,appleCol);
			
			if (!mySnake.isMember(apple))
			{
				ImageIcon bait = new ImageIcon("bait.png");
				screen[apple.getRow()][apple.getCol()].setIcon(bait);
				screen[apple.getRow()][apple.getCol()].setVisible(true);
				applePlanted = true;
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (paused==false)
		{
		   paused = true;
		   pauseButton.setText("Unpause");
		}
		else
		{
		   paused = false;
		   pauseButton.setText("Pause");
		}
			playAreaPanel.requestFocusInWindow();
    }

	//Pause Button
	@Override
	public void keyPressed(KeyEvent e)
	{
		key = e.getKeyCode();
		
		if (key == KeyEvent.VK_LEFT)
		{
			if (paused == true)
			{}
			else
			{
				if (goingRight==true)
				{}
				else
				{
					prevKey=KeyEvent.VK_LEFT;
					goingUp = goingDown =  goingRight = false;
					goingLeft = true;
				}
			}
		}
		
		if (key == KeyEvent.VK_UP)
		{
			if (paused == true)
			{}
			else
			{
				if (goingDown==true)
				{}
				else
				{
					prevKey=KeyEvent.VK_UP;
					goingLeft = goingDown =  goingRight = false;
					goingUp = true;
				}
			}
		}
		
		if (key == KeyEvent.VK_RIGHT)
		{
			if (paused == true)
			{}
			else
			{
				if (goingLeft==true)
				{}
				else
				{
					prevKey=KeyEvent.VK_RIGHT;
					goingLeft = goingDown =  goingUp = false;
					goingRight = true;
				}
			}
		}
		
		if (key == KeyEvent.VK_DOWN)
		{
				if (paused == true)
				{}
			else
			{
				if (goingUp==true)
				{}
				else
				{
					prevKey=KeyEvent.VK_DOWN;
					goingLeft = goingUp =  goingRight = false;
					goingDown = true;
				}
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	private class TimerListener implements ActionListener
	{
        public void actionPerformed(ActionEvent e)
		{
			snakeHead= new SnakeElement();
			snakeHead.setRow(oldSnakeHead.getRow());
			snakeHead.setCol(oldSnakeHead.getCol());
			
            if (goingUp)
			{
				snakeHead.decrementRow();
			}
			if (goingDown)
			{
				snakeHead.incrementRow();
			}
			if (goingLeft)
			{
				snakeHead.decrementCol();
			}
			if (goingRight)
			{
				snakeHead.incrementCol();
			}
			if(paused)
			{
			}
			else
			{
				positionLabel.setText("Score: " + score);

				if  (snakeHead.compareTo(apple)==0)
				{
					if (snakeHead.compareTo(apple)==0)
					{
						appleActive=false;
						appleAmount++;
						mySnake.grow(snakeHead);
						score +=1;
						timerDelayer =score/5;
						
						switch (timerDelayer)
						{
							case 1: timer.setDelay(250);
							break;
							case 2: timer.setDelay(225);
							break;
							case 3: timer.setDelay(200);
							break;
							case 4: timer.setDelay(175);
							break;
							case 5: timer.setDelay(150);
							break;
							case 6: timer.setDelay(125);
							break;
							case 7: timer.setDelay(100);
							break;
							case 8: timer.setDelay(75);
							break;
							case 9: timer.setDelay(50);
							break;
							case 10: timer.setDelay(25);
							break;
						}
						
						ImageIcon iconLogo = new ImageIcon("dot_logo.png");
						screen[apple.getRow()][apple.getCol()].setIcon(iconLogo);
						screen[apple.getRow()][apple.getCol()].setVisible(true);
						addApple();
					}
				}
				else
					mySnake.moveForward(snakeHead);
				
				oldSnakeHead.setRow(snakeHead.getRow());
				oldSnakeHead.setCol(snakeHead.getCol());
			}
		}
	}
	
	public class Snake
	{
	    int maxLength= (ROWS * COLS)-1;
		QueueAsSLL <SnakeElement> theSnake = new QueueAsSLL <SnakeElement>();
		int snakeLength=0;
		
		public Snake()
		{
			snakeLength = 0;
		}
		
		public SnakeElement createInitialSnake(int row, int col, int size)
		{
			SnakeElement t1 = new SnakeElement();
			SnakeElement t2 = new SnakeElement();
			int i;
			
			for (i = (size-1); i>=0; i--)
			{
				t1 = null;
				t1 = new SnakeElement(row,col+i);
				theSnake.enqueue(t1);
				screen[row][col+i].setVisible(true);
			}
			
			snakeLength = size;
			t2.deepCopy(t1);
			return t2;
		}
		
		public void moveForward(SnakeElement newHead)
		{
			SnakeElement temp1,temp2;
			
			if (snakeLength < maxLength && snakeLength >0)
			{
				temp1=(SnakeElement) theSnake.dequeue();
				temp2=new SnakeElement(newHead.getRow(),newHead.getCol());
				
				screen[temp1.getRow()][temp1.getCol()].setVisible(false);
				temp2=new SnakeElement(newHead.getRow(),newHead.getCol());
				
				if (theSnake.isMember(temp2))
				{
					timer.stop();
					
					JButton reset = new JButton("Reset");
					int resetOption =JOptionPane.YES_NO_OPTION;
					int option = JOptionPane.showConfirmDialog(frame, "Play again?","Game Over",resetOption);
					String tmp1,tmp2;
					
					tmp1=Integer.toString(rSize);
					tmp2=Integer.toString(cSize);
					
					String[] arY ={tmp1,tmp2};
					
					if(option==0)
					{
						dispose();
						main(arY);
					}
					else
					{
						System.exit(1);
					}
				}
				else
				{
					if (theSnake.enqueue(temp2))
						screen[newHead.getRow()][newHead.getCol()].setVisible(true);
				}
			}
		}
		
		public void grow(SnakeElement newHead)
		{
			SnakeElement temp1,temp2;
			
			if (snakeLength < maxLength && snakeLength >0)
			{
				temp2=new SnakeElement(newHead.getRow(),newHead.getCol());
				
				if (theSnake.enqueue(temp2))
				    screen[newHead.getRow()][newHead.getCol()].setVisible(true);
			}
		}
		
		public boolean isMember(SnakeElement item)
		{
			return theSnake.isMember(item);
		}
		
		public int getSize()
		{
			return snakeLength;
		}
	}


	public class SnakeElement implements Comparable<SnakeElement>
	{
		int rowPos;
		int colPos;

		public SnakeElement()
		{
			this(0,0);
		}

		public SnakeElement(int r, int c)
		{
			if (r<ROWS)
				rowPos=r;
			else
				rowPos=0;
			if (c<COLS)
				colPos=c;
			else
				colPos=0;
		}

		void deepCopy(SnakeElement param)
		{
           rowPos=param.rowPos;
		   colPos=param.colPos;
		}

		void incrementRow()
		{
			if (rowPos < (ROWS-1))
			{
				rowPos++;
			}
			else
			{
				rowPos = 0;
			}
		}
		
		void decrementRow()
		{
			if (rowPos > 0)
			{
				rowPos--;
			}
			else
			{
				rowPos = ROWS-1;
			}
		}
		
		void incrementCol()
		{
			if (colPos < (COLS-1))
			{
				colPos++;
			}
			else
			{
				colPos = 0;
			}
		}
		
		void decrementCol()
		{
			if (colPos > 0)
			{
				colPos--;
			}
			else
			{
				colPos = COLS-1;
			}
		}
		
		int getRow()
		{
			return rowPos;
		}
		
		void setRow(int r)
		{
			rowPos = r;
		}
		
		int getCol()
		{
			return colPos;
		}
		
		void setCol(int c)
		{
			colPos =c;
		}

		@Override
		public String toString()
		{
			String s = "(" + rowPos + "," + colPos + ")";
			return s;
		}
		
		@Override
		public int compareTo(SnakeElement param)
		{
			if (param.colPos==colPos && param.rowPos==rowPos)
				return 0;
			else return -1;
		}
	}

	public static void main(String[] args)
	{
		int rSize,cSize;
		
		if (args.length >0)
		{
			try
			{
				rSize=Integer.parseInt(args[0]);
				cSize=Integer.parseInt(args[1]);
				SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run() {
				new PlayWindow(rSize,cSize).setVisible(true);
			}
		});
			}
			catch (NumberFormatException e)
			{
				System.err.println(e);
				System.exit(1);
			}
		}
	}
}
