import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class BoggleDice extends JFrame implements ActionListener, InputMethodListener, KeyListener{
	boolean found = false;
	static ArrayList<String> wordListPlay = new ArrayList<String>();
	static String[] labels = new String[16];
	int score = 0;
	BoggleDice joe;
	Timer timer;
	static ArrayList<Button> buttons = new ArrayList<Button>(16);
	long startTime;
	int reset;
	Button one;
	Button two;
	Button three;
	Button four;
	Button five;
	Button six;
	Button seven;
	Button eight;
	Button nine;
	Button ten;
	Button eleven;
	Button twelve;
	Button thirteen;
	Button fourteen;
	Button fifteen;
	Button sixteen;
	JTextField input;
	Button startButton;
	Button timerButton;
	Button scoreButton;



	String[][] diceArray = new String[4][4];
	int random;
	
	public BoggleDice(){
		startTime = System.currentTimeMillis();
		timerButton = new Button();
		startButton = new Button();
		Button timerButton1 =new Button();

		reset = 0;
	setSize(1000,800);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setVisible(false);
	Random generator = new Random();
	random = generator.nextInt(6);
	switch(random){
	case 1:
	one = new Button("E");
	break;
	case 2:
	one = new Button("E");
	break;
	case 3:
	one = new Button("N");
	break;
	case 4:
	one = new Button("U");
	break;
	case 5:
	one = new Button("S");
	break;
	default:
		one = new Button("I");
		break;
	}//end switch
	random = generator.nextInt(6);
	switch(random){
	case 1:
	two = new Button("A");
	break;
	case 2:
	two = new Button("J");
	break;
	case 3:
	two = new Button("B");
	break;
	case 4:
	two = new Button("B");
	break;
	case 5:
	two = new Button("O");
	break;
	default:
		two = new Button("O");
		break;
	}//end switch
	
	random = generator.nextInt(6);
	switch(random){
	case 1:
	three = new Button("E");
	break;
	case 2:
	three = new Button("R");
	break;
	case 3:
	three = new Button("H");
	break;
	case 4:
	three = new Button("T");
	break;
	case 5:
	three = new Button("W");
	break;
	default:
		three = new Button("V");
		break;
	}//end switch
	random = generator.nextInt(6);
	switch(random){
	case 1:
	four = new Button("E");
	break;
	case 2:
	four = new Button("I");
	break;
	case 3:
	four = new Button("O");
	break;
	case 4:
	four = new Button("T");
	break;
	case 5:
	four = new Button("S");
	break;
	default:
		four = new Button("S");
		break;
	}//end switch
	random = generator.nextInt(6);
	switch(random){
	case 1:
	five = new Button("E");
	break;
	case 2:
	five = new Button("E");
	break;
	case 3:
	five = new Button("G");
	break;
	case 4:
	five = new Button("N");
	break;
	case 5:
	five = new Button("H");
	break;
	default:
		five = new Button("W");
		break;
	}//end switch
	random = generator.nextInt(6);
	switch(random){
	case 1:
	six = new Button("E");
	break;
	case 2:
	six = new Button("D");
	break;
	case 3:
	six = new Button("R");
	break;
	case 4:
	six = new Button("L");
	break;
	case 5:
	six = new Button("X");
	break;
	default:
		six = new Button("I");
		break;
	}//end switch
	random = generator.nextInt(6);
	switch(random){
	case 1:
	seven = new Button("E");
	break;
	case 2:
	seven = new Button("D");
	break;
	case 3:
	seven = new Button("V");
	break;
	case 4:
	seven = new Button("L");
	break;
	case 5:
	seven = new Button("R");
	break;
	default:
		seven = new Button("Y");
		break;
	}//end switch
	random = generator.nextInt(6);
	switch(random){
	case 1:
	eight = new Button("T");
	break;
	case 2:
	eight = new Button("U");
	break;
	case 3:
	eight = new Button("M");
	break;
	case 4:
	eight = new Button("I");
	break;
	case 5:
	eight = new Button("O");
	break;
	default:
		eight = new Button("C");
		break;
	}//end switch
	random = generator.nextInt(6);
	switch(random){
	case 1:
	nine = new Button("T");
	break;
	case 2:
	nine = new Button("L");
	break;
	case 3:
	nine = new Button("R");
	break;
	case 4:
	nine = new Button("Y");
	break;
	case 5:
	nine = new Button("E");
	break;
	default:
		nine = new Button("T");
		break;
	}//end switch
	random = generator.nextInt(6);
	switch(random){
	case 1:
	ten = new Button("T");
	break;
	case 2:
	ten = new Button("T");
	break;
	case 3:
	ten = new Button("W");
	break;
	case 4:
	ten = new Button("O");
	break;
	case 5:
	ten = new Button("O");
	break;
	default:
		ten = new Button("A");
		break;
	}//end switch
	random = generator.nextInt(6);
	switch(random){
	case 1:
	eleven = new Button("T");
	break;
	case 2:
	eleven = new Button("D");
	break;
	case 3:
	eleven = new Button("S");
	break;
	case 4:
	eleven = new Button("T");
	break;
	case 5:
	eleven = new Button("Y");
	break;
	default:
		eleven = new Button("I");
		break;
	}//end switch
	random = generator.nextInt(6);
	switch(random){
	
	case 1:
	twelve = new Button("Z");
	break;
	case 2:
	twelve = new Button("N");
	break;
	case 3:
	twelve = new Button("H");
	break;
	case 4:
	twelve = new Button("R");
	break;
	case 5:
	twelve = new Button("L");
	break;
	default:
		twelve = new Button("N");
		break;
	}//end switch
	random = generator.nextInt(6);
	switch(random){
	case 1:
	thirteen = new Button("Qu");
	break;
	case 2:
	thirteen = new Button("N");
	break;
	case 3:
	thirteen = new Button("U");
	break;
	case 4:
	thirteen = new Button("M");
	break;
	case 5:
	thirteen = new Button("H");
	break;
	default:
	thirteen = new Button("I");
	break;
	}//end switch


	random = generator.nextInt(6);
	switch(random){
	case 1:
	fourteen = new Button("A");
	break;
	case 2:
	fourteen= new Button("P");
	break;
	case 3:
	fourteen = new Button("S");
	break;
	case 4:
	fourteen = new Button("H");
	break;
	case 5:
	fourteen = new Button("C");
	break;
	default:
	fourteen = new Button("O");
	break;
	}//end switch
	random = generator.nextInt(6);
	switch(random){
	case 1:
	fifteen = new Button("A");
	break;
	case 2:
	fifteen = new Button("F");
	break;
	case 3:
	fifteen = new Button("F");
	break;
	case 4:
	fifteen = new Button("P");
	break;
	case 5:
	fifteen = new Button("S");
	break;
	default:
	fifteen = new Button("K");
	break;
	}//end switch
	random = generator.nextInt(6);
	switch(random){
	case 1:
	sixteen = new Button("A");
	break;
	case 2:
	sixteen = new Button("N");
	break;
	case 3:
	sixteen = new Button("E");
	break;
	case 4:
	sixteen = new Button("A");
	break;
	case 5:
	sixteen = new Button("G");
	break;	
	default:
	sixteen = new Button("E");
	break;
	}//end switch
	//ADD ,"","APSHCO","QuNUMHI","ZNHRLN","TDSTYI","TTWOOA","TLRYET",
	//"TUMIOC","EDVLRY","EDRLXI","EEGNHW","EIOTSS","ERHTWV", "EENUSI". These are the cubes in the US version of Boggle
	scoreButton = new Button("Current Score: \n");
	scoreButton.setSize(200, 100);
	scoreButton.setLocation(650, 350);
	input = new JTextField();
	input.addActionListener(this);
	input.setActionCommand("Text");
	input.addInputMethodListener(this);
	input.setBounds(650, 450, 200, 100);
	one.setSize(150,150);
	two.setSize(150,150);
	three.setSize(150,150);
	four.setSize(150,150);
	five.setSize(150,150);
	six.setSize(150,150);
	seven.setSize(150,150);
	eight.setSize(150,150);
	nine.setSize(150,150);
	ten.setSize(150,150);
	eleven.setSize(150,150);
	twelve.setSize(150,150);
	thirteen.setSize(150,150);
	fourteen.setSize(150,150);
	fifteen.setSize(150,150);
	sixteen.setSize(150,150);
	startButton.setSize(150,150);
	timerButton.setSize(150,150);

	buttons.add(0,one);
	buttons.add(1,two);
	buttons.add(2,three);
	buttons.add(3,four);
	buttons.add(4,five);
	buttons.add(5,six);
	buttons.add(6,seven);
	buttons.add(7,eight);
	buttons.add(8,nine);
	buttons.add(9,ten);
	buttons.add(10,eleven);
	buttons.add(11,twelve);
	buttons.add(12,thirteen);
	buttons.add(13,fourteen);
	buttons.add(14,fifteen);
	buttons.add(15,sixteen);
	
	
	
	
	Collections.shuffle(buttons);
	for(int i = 0; i < buttons.size(); i++){
		labels[i] = buttons.get(i).getLabel();
	}
	buttons.get(0).setLocation(0, 0);
	buttons.get(1).setLocation(150,0);
	buttons.get(2).setLocation(300,0);
	buttons.get(3).setLocation(450,0);
	buttons.get(4).setLocation(0,150);
	buttons.get(5).setLocation(150,150);
	buttons.get(6).setLocation(300,150);
	buttons.get(7).setLocation(450,150);
	buttons.get(8).setLocation(0,300);
	buttons.get(9).setLocation(150,300);
	buttons.get(10).setLocation(300,300);
	buttons.get(11).setLocation(450,300);
	buttons.get(12).setLocation(0,450);
	buttons.get(13).setLocation(150,450);
	buttons.get(14).setLocation(300,450);
	buttons.get(15).setLocation(450,450);
	timerButton.setSize(150,150);
	timerButton.setLocation(625, 25);
	startButton.setLabel("New Game");
	startButton.addActionListener(this);
	startButton.setLocation(625,200);

	diceArray[0][0] = buttons.get(0).getLabel();
	diceArray[1][0] = buttons.get(1).getLabel();
	diceArray[2][0] = buttons.get(2).getLabel();
	diceArray[3][0] = buttons.get(3).getLabel();
	diceArray[0][1] = buttons.get(4).getLabel();
	diceArray[1][1] = buttons.get(5).getLabel();
	diceArray[2][1] = buttons.get(6).getLabel();
	diceArray[3][1] = buttons.get(7).getLabel();
	diceArray[0][2] = buttons.get(8).getLabel();
	diceArray[1][2] = buttons.get(9).getLabel();
	diceArray[2][2] = buttons.get(10).getLabel();
	diceArray[3][2] = buttons.get(11).getLabel();
	diceArray[0][3] = buttons.get(12).getLabel();
	diceArray[1][3] = buttons.get(13).getLabel();
	diceArray[2][3] = buttons.get(14).getLabel();
	diceArray[3][3] = buttons.get(15).getLabel();
	
	
	
		

	
	
	one.setFont(new Font("Serif", Font.BOLD, 50));
	two.setFont(new Font("Serif", Font.BOLD, 50));
	three.setFont(new Font("Serif", Font.BOLD, 50));
	four.setFont(new Font("Serif", Font.BOLD, 50));
	five.setFont(new Font("Serif", Font.BOLD, 50));
	six.setFont(new Font("Serif", Font.BOLD, 50));
	seven.setFont(new Font("Serif", Font.BOLD, 50));
	eight.setFont(new Font("Serif", Font.BOLD, 50));
	nine.setFont(new Font("Serif", Font.BOLD, 50));
	ten.setFont(new Font("Serif", Font.BOLD, 50));
	eleven.setFont(new Font("Serif", Font.BOLD, 50));
	twelve.setFont(new Font("Serif", Font.BOLD, 50));
	thirteen.setFont(new Font("Serif", Font.BOLD, 50));
	fourteen.setFont(new Font("Serif", Font.BOLD, 50));
	fifteen.setFont(new Font("Serif", Font.BOLD, 50));
	sixteen.setFont(new Font("Serif", Font.BOLD, 50));
	timerButton.setFont(new Font("Serif", Font.BOLD, 20));
	input.addKeyListener(this);
	add(scoreButton);
	add(input);
	add(one);
	add(two);
	add(three);
	add(four);
	add(five);
	add(six);
	add(seven);
	add(eight);
	add(nine);
	add(ten);
	add(eleven);
	add(twelve);
	add(thirteen);
	add(fourteen);
	add(fifteen);
	add(sixteen);
	add(startButton);
	timerButton.setLabel("Click to start time");
	timerButton.setActionCommand("A");
	timerButton.addActionListener(this);
	startButton.setActionCommand("B");
	add(timerButton);
	add(timerButton1);
	
	}//end BoggleDICE

	@Override
	public void actionPerformed(ActionEvent e) {
		String ActionCommand = e.getActionCommand();
		String text;
		if(ActionCommand == "B"){
		JOptionPane.showMessageDialog(null, "You found " + wordListPlay.size() + " of " + BoggleSolver.wordList.size() + " words.");
		wordListPlay.clear();
		this.dispose();
		buttons.clear();
		new BoggleSolver();
		}
		if(ActionCommand.equals("Text")){
		scoreButton.setLabel("Current Score:\n" + score);
		text = input.getText();
		text = text.toUpperCase();

		input.setText("");
			if(text.length() < 3){
				scoreButton.setLabel("Too short");
			}
			if(wordListPlay.contains(text)){
				scoreButton.setLabel("Already entered");
			}
			if(!BoggleSolver.wordList.contains(text)){
				scoreButton.setLabel("Invalid entry");
			}
			if(!wordListPlay.contains(text)){
			if(BoggleSolver.wordList.contains(text)){
				wordListPlay.add(text);
			switch(text.length()){
			case 3: case 4:
				score++;
				break;
			case 5: 
				score = score + 2;
				break;
			case 6:
				score = score + 3;
				break;
			case 7:
				score = score + 4;
				break;
			case 8:
				score = score + 11;
				break;
			}
			scoreButton.setLabel("Current Score:\n" + score);

			}
			
			}
		}	
	}


	public static ArrayList<Button> getButtons() {
		// TODO Auto-generated method stub
		return buttons;
	}

	@Override
	public void caretPositionChanged(InputMethodEvent arg0) {
		System.out.println("A");
	}

	@Override
	public void inputMethodTextChanged(InputMethodEvent arg0) {
		System.out.println("A");
	}

	public int checkStringArray(String text){
		int count = 0;
		found = false;
		for(int i = 0; i < buttons.size(); i++){
			labels[i] = buttons.get(i).getLabel();
		}
		while(!found && count <= 15  ){
			
			if(text.equals( labels[count])){
				
				found = true;
				buttons.get(count).setBackground(Color.RED);
			}
			
			count++;
		}
		return (count - 1);
		
	}//end checkArray
	
	public void checkNext(String text, int index){
		String subString;
		if(text.charAt(0) != 'Q'){
		subString = String.valueOf(text.charAt(1));
		}
		else{
		subString = String.valueOf(text.charAt(2));

		}
		for(int i = 0; i <= 15; i++){
				if(subString.equals(labels[i])){
			
					buttons.get(i).setBackground(Color.RED);
				}
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

		String text = String.valueOf(arg0.getKeyChar());
		text.toUpperCase();
		if(text.length() > 0){
			checkStringArray(text);
			text = input.getText();
			if(text.length() > 1){
				checkNext(text, 0);
			}
		}
	}

	private char[] getKeyChar(KeyEvent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
		

}//end BoggleDiceClass
