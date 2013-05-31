import java.awt.Button;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class BoggleSolver {
	static BoggleDice joe2;
	static HashSet<String> dictionary = new HashSet<String>();
	static ArrayList<String> wordList = new ArrayList<String>();
	static int[][] spotArray = new int[4][4];
	static String[][] board = new String[4][4];
	static boolean[][] visitedArray = new boolean[4][4];
	static long startTime;
	static long endTime;
	public BoggleSolver() {
		wordList.clear();
		dictionary.clear();
		joe2 = new BoggleDice();
		ArrayList<Button> buttons = BoggleDice.getButtons();

		spotArray[0][0] = 0;
		spotArray[1][0] = 1;
		spotArray[2][0] = 2;
		spotArray[3][0] = 3;
		spotArray[0][1] = 4;
		spotArray[1][1] = 5;
		spotArray[2][1] = 6;
		spotArray[3][1] = 7;
		spotArray[0][2] = 8;
		spotArray[1][2] = 9;
		spotArray[2][2] = 10;
		spotArray[3][2] = 11;
		spotArray[0][3] = 12;
		spotArray[1][3] = 13;
		spotArray[2][3] = 14;
		spotArray[3][3] = 15;


		board[0][0] = buttons.get(0).getLabel();
		board[1][0] = buttons.get(1).getLabel();
		board[2][0] = buttons.get(2).getLabel();
		board[3][0] = buttons.get(3).getLabel();
		board[0][1] = buttons.get(4).getLabel();
		board[1][1] = buttons.get(5).getLabel();
		board[2][1] = buttons.get(6).getLabel();
		board[3][1] = buttons.get(7).getLabel();
		board[0][2] = buttons.get(8).getLabel();
		board[1][2] = buttons.get(9).getLabel();
		board[2][2] = buttons.get(10).getLabel();
		board[3][2] = buttons.get(11).getLabel();
		board[0][3] = buttons.get(12).getLabel();
		board[1][3] = buttons.get(13).getLabel();
		board[2][3] = buttons.get(14).getLabel();
		board[3][3] = buttons.get(15).getLabel();
		isInDictionary();
		
		System.out.println(dictionary.size());
		for (int i = 0; i <= 3; i++) {
			for (int j = 0; j <= 3; j++) {
				visitedArray = clearVisited();
				BoggleSolver.RecursiveChecker("", i, j);

			}
		}
	}

	public static void main(String[] args) {
		new BoggleSolver();
		startTime = System.currentTimeMillis();
		for (int i = 0; i <= 3; i++) {
			for (int j = 0; j <= 3; j++) {
				visitedArray = clearVisited();
				BoggleSolver.RecursiveChecker("", i, j);

			}
		}
		System.out.print("\n");
		System.out.print(board[0][0] + "" + board[1][0] + "" + board[2][0] + ""
				+ board[3][0] + "\n" + board[0][1] + "" + board[1][1] + ""
				+ board[2][1] + "" + board[3][1] + "\n" + board[0][2] + ""
				+ board[1][2] + "" + board[2][2] + "" + board[3][2] + "\n"
				+ board[0][3] + "" + board[1][3] + "" + board[2][3] + ""
				+ board[3][3] + "\n");
		System.out.println(wordList);
		System.out.println(wordList.size());
		endTime = System.currentTimeMillis();
		long time = endTime - startTime;
		System.out.println(time);
		joe2.setVisible(true);
	}

	public static void RecursiveChecker(String a, int i, int j) {
		int spot;
		if (i < 0 || i > 3 || j < 0 || j > 3 || visitedArray[i][j] == true) {
			return;
		}
		a = a.toUpperCase() + board[i][j];
		if (a.length() <= 8 && !wordList.contains(a) && a.length() > 2) {
			if (dictionary.contains(a)) {
				wordList.add(a);
			}
		}
		spot = spotArray[i][j];
		// if(a.length() <=4 && !wordList.contains(a)){
		// if(isInDictionary(a.toUpperCase())){
		// wordList.add(a);
		// }
		// }
		visitedArray[i][j] = true;
		if(a.length() <= 7){
		switch(spot){
		case 0:
			RecursiveChecker(a, i + 1, j);
			RecursiveChecker(a, i + 1, j + 1);
			RecursiveChecker(a, i, j + 1);
			break;
		case 1:		case 2:
			RecursiveChecker(a, i - 1, j);
			RecursiveChecker(a, i + 1, j);
			RecursiveChecker(a, i, j + 1);
			RecursiveChecker(a, i - 1, j + 1);
			RecursiveChecker(a, i + 1, j + 1);
			break;
		case 3:
			RecursiveChecker(a, i - 1, j);
			RecursiveChecker(a, i, j + 1);
			RecursiveChecker(a, i - 1, j + 1);
			break;
		case 4: case 8:
			RecursiveChecker(a, i, j - 1);
			RecursiveChecker(a, i + 1, j - 1);
			RecursiveChecker(a, i + 1, j);
			RecursiveChecker(a, i + 1, j + 1);
			RecursiveChecker(a, i, j + 1);
			break;
		case 5: case 6: case 9: case 10:
			//right
			RecursiveChecker(a, i + 1, j);
			//left
			RecursiveChecker(a, i - 1, j);
			//down
			RecursiveChecker(a, i, j + 1);
			//up
			RecursiveChecker(a, i, j - 1);
			//right & down
			RecursiveChecker(a, i + 1, j + 1);
			//left & up
			RecursiveChecker(a, i - 1, j - 1);
			//left & down
			RecursiveChecker(a, i - 1, j + 1);
			//right & up
			RecursiveChecker(a, i + 1, j - 1);
			break;
		case 7:		case 11:
			RecursiveChecker(a, i, j - 1);
			RecursiveChecker(a, i - 1, j - 1);
			RecursiveChecker(a, i - 1, j);
			RecursiveChecker(a, i - 1, j + 1);
			RecursiveChecker(a, i, j + 1);
			break;
	
		case 12:
			RecursiveChecker(a, i + 1, j);
			RecursiveChecker(a, i, j - 1);
			RecursiveChecker(a, i + 1, j - 1);
			break;
		case 13: case 14:
			RecursiveChecker(a, i - 1, j);
			RecursiveChecker(a, i + 1, j);
			RecursiveChecker(a, i, j - 1);
			RecursiveChecker(a, i + 1, j - 1);
			RecursiveChecker(a, i - 1, j - 1);
			break;
		case 15:
			RecursiveChecker(a, i - 1, j);
			RecursiveChecker(a, i - 1, j - 1);
			RecursiveChecker(a, i, j - 1);
			break;
		default:
			return;
		}
		}



		visitedArray[i][j] = false;

	}

	public static void isInDictionary() {
		try {

			Scanner search = new Scanner(new FileReader("Dictionary.txt"));
			while (search.hasNext()) {
				dictionary.add(search.next());
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static boolean[][] clearVisited() {
		for (int i = 0; i <= 3; i++) {
			for (int j = 0; j <= 3; j++) {
				visitedArray[i][j] = false;

			}
		}
		return visitedArray;
	}
}