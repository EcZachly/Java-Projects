import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Analyzer {
	BufferedImage IMG;
	Color[][] colors;
	int[][] inPenny;
	int[][] copyPenny;
	int[] pixels;
	int[] Rbounds;
	int[] Bbounds;
	int[] Gbounds;
	static int diam;
	ArrayList<int[]> centers = new ArrayList<int[]>();
	static int looper = 0;
	int count = 0;
	int lCol;
	int rCol;
	int uRow;
	int lRow;
	int avgR;
	int avgB;
	int avgG;
	static int fillFactor = 14;

	public Analyzer() {
		try {
			IMG = ImageIO.read(new File("Picture12.jpg"));
		} catch (Exception e) {
			System.out.println("File not Found");
		}
		pixels = IMG.getRGB(0, 0, IMG.getWidth(), IMG.getHeight(), null, 0,
				IMG.getWidth());
		colors = new Color[IMG.getHeight()][IMG.getWidth()];
		inPenny = new int[IMG.getHeight()][IMG.getWidth()];
		copyPenny = new int[IMG.getHeight()][IMG.getWidth()];
		int i = 0;
		int p = 0;
		for (int j = 0; j < pixels.length; j++) {
			colors[p][i] = new Color(pixels[j]);
			i++;
			if (i == IMG.getWidth()) {
				i = 0;
				p++;
			}
		}
		for (i = 0; i < IMG.getWidth(); i++) {
			for (int j = 0; j < IMG.getHeight(); j++) {
				avgR += colors[j][i].getRed();
				avgG += colors[j][i].getGreen();
				avgB += colors[j][i].getBlue();
			}
		}
		avgR = avgR / IMG.getWidth() / IMG.getHeight();
		avgG = avgG / IMG.getWidth() / IMG.getHeight();
		avgB = avgB / IMG.getWidth() / IMG.getHeight();
		say(avgR);
		say(avgG);
		say(avgB);

		this.Gbounds = new int[2];
		this.Rbounds = new int[2];
		this.Bbounds = new int[2];

		Gbounds[0] = 73;
		Gbounds[1] = 103;
		Rbounds[0] = 120;
		Rbounds[1] = 150;
		Bbounds[0] = 56;
		Bbounds[1] = 86;

	}

	// DIME
	// G 120 150
	// R 120 150
	// B 120 150

	// PENNY
	// G 73 103
	// B 56 86
	// R 120 150

	public static void main(String[] args) {
		Analyzer a = new Analyzer();
		a.loadPennies();
		a.printPenny();
		a.fillInPenny();

	//	a.printPenny();
//		a.findPennies();
//		a.findPennies();
		// a.findPennies();
//			 a.findPennies();
//	 a.findPennies();
//	 a.findPennies();
//	 a.findPennies();
//	 a.findPennies();
//	 a.findPennies();
		for (int i = 0; i < a.inPenny.length; i++)
			for (int j = 0; j < a.inPenny[i].length; j++)
				a.copyPenny[i][j] = a.inPenny[i][j];

		a.writeImage();
		int w = 0;
		// a.findPennies();
		do {
			a.findPennies();

		} while (a.morePennies());
		//say(diam);
		say(a.centers.size() + "NUM");
		//a.printCenters();

		// a.loadPennies();
		// a.fillInPenny();
		// a.printPenny();
		//

	}

	public void findPennies() {
		for (int i = 0; i < IMG.getWidth(); i++) {
			for (int w = 0; w < IMG.getHeight(); w++) {
				if (inPenny[w][i] == 1) {
					lCol = i;
					findDiam(w, i);
					clearColors();
					if (rCol - lCol > diam * .85)
						addCenters();
					return;
				}
			}
		}
	}

	public int findDiam(int row, int col) {
		int star = col;
		while (inPenny[row][star] == 1) {
			star++;
		}
		if (count == 0) {
			rCol = star;
			uRow = row - (star - col) / 2;
			lRow = row + (star - col) / 2;
		} else {
			rCol = star;
			uRow = row - (diam) / 2;
			lRow = row + (diam) / 2;

		}
		if (count == 0 || diam < 40) {
			diam = star - col;
			say(diam + "DIAM");
			count++;
		}

		// say(holds);
		// say("END COL" + star);
		return star - col;

	}

	public boolean morePennies() {
		int holdsBiggest = 0;
		int counter = 0;
		for (int i = 0; i < IMG.getHeight(); i++) {

			for (int j = 0; j < IMG.getWidth(); j++) {
				while (inPenny[i][j] == 1) {
					counter++;
					j++;
					if (holdsBiggest < counter) {
						holdsBiggest = counter;

					}

				}
				counter = 0;
			}
			counter = 0;
		}
		// say(holdsBiggest + "LONGEST ROW");
		if (holdsBiggest < diam * .8) {
			return false;

		}

		return true;

	}

	public boolean inBounds(int row, int col) {
		if (this.colors[row][col].getBlue() > Bbounds[0]
				&& this.colors[row][col].getBlue() < Bbounds[1]) {
			return true;
		}
		if (this.colors[row][col].getRed() > Rbounds[0]
				&& this.colors[row][col].getRed() < Rbounds[1]) {
			return true;
		}
		if (this.colors[row][col].getGreen() > Gbounds[0]
				&& this.colors[row][col].getGreen() < Gbounds[1]) {
			return true;
		}

		return false;
	}

	public void clearColors() {
		// say(lCol);
		// say(rCol);
		if (lCol < 5)
			lCol = lCol + 5;
		if (uRow < 5)
			uRow = uRow + 5;
		if (lRow > IMG.getHeight() - 5) {
			lRow = lRow - 5;
		}

		// say(lCol + " " + uRow);

		for (int i = lCol - 5; i < rCol; i++) {
			for (int j = uRow - 5; j < lRow + 5; j++) {
				if (j < IMG.getHeight() && i < IMG.getWidth())

					if (j > 0 && i > 0)
						inPenny[j][i] = 0;

			}

		}

	}

	public static void say(Object o) {
		System.out.println(o);

	}

	public void printRow(int i) {
		for (int l = i * IMG.getWidth(); l < i * IMG.getWidth()
				+ IMG.getWidth(); l++) {
			say(pixels[l] + "PIX" + l % IMG.getWidth());
		}

	}

	public void loadPennies() {
		int j = 0;
		for (int i = 0; i < pixels.length - IMG.getWidth(); i++) {
			j++;
			if (j < IMG.getWidth() - 1
					&& i / IMG.getHeight() < IMG.getHeight() - 1) {
				if (inBounds(i / IMG.getHeight(), j)
						|| inBounds(i / IMG.getHeight(), j + 1)
						|| inBounds(i / IMG.getHeight() + 1, j + 1)) {
					inPenny[i / IMG.getHeight()][j] = 1;
				} else {
					inPenny[i / IMG.getHeight()][j] = 0;
				}
			}
			if (j == IMG.getWidth()) {
				j = 0;
			}
		}

	}

	public void printPenny() {

		boolean flag = false;
		for (int i = 0; i < IMG.getHeight(); i++) {

			for (int j = 0; j < IMG.getWidth(); j++) {
				flag = false;
				if (j == 0) {
					say(i);
				}
				for (int k = 0; k < centers.size(); k++) {
					if (centers.get(k)[0] == i && centers.get(k)[1] == j) {
						System.out.print("C");
						flag = true;
					}

				}
				if (!flag)
					System.out.print(inPenny[i][j]);

			}

		}
	}

	public void fillInPenny() {
		for (int k = 0; k < fillFactor; k++) {
			for (int i = 0; i < IMG.getHeight() - 1; i++) {
				for (int j = 0; j < IMG.getWidth() - 1; j++) {
					if (k == fillFactor - 1 && inPenny[i][j + 1] == 1
							&& inPenny[i][j] == 0) {
						inPenny[i][j] = 7;
					} else if (k == fillFactor - 1 && inPenny[i + 1][j] == 1
							&& inPenny[i][j] == 0) {
						inPenny[i][j] = 7;

					} else if (k == fillFactor - 1) {
						if (i > 0)
							if (inPenny[i - 1][j] == 1 && inPenny[i][j] == 0)
								inPenny[i][j] = 7;
							else if ((inPenny[i][j + 1] == 1 || inPenny[i + 1][j] == 1))
								inPenny[i][j] = 1;
					} else if (inPenny[i][j + 1] == 1 || inPenny[i + 1][j] == 1
							&& inPenny[i][j] != 7) {
						inPenny[i][j] = 1;
						// }
					}
				}

			}
		}
		loopPennies();
		loopMap();
		Map2();
		checkOverlap();
	}

	public void loopPennies() {
		for (int i = 0; i < IMG.getHeight(); i++) {
			for (int j = 0; j < IMG.getWidth(); j++) {
				if (fixPennies(i, j) == 1 && looper < 150) {
					looper++;
					loopPennies();
				}
			}

		}

	}

	public int fixPennies(int row, int col) {
		int count = 0;
		if (row > 0 && col > 0 && row < IMG.getHeight() - 1
				&& col < IMG.getWidth() - 1)
			if (inPenny[row][col] != 1) {
				for (int i = -1; i < 2; i++) {
					for (int j = -1; j < 2; j++) {
						if (inPenny[row + i][col + j] == 1) {
							count++;
						}

					}
				}
			}
		if (count > 4) {
			inPenny[row][col] = 1;
			return 1;
		} else {
			return 0;
		}
	}

	public void addCenters() {
		int[] temp = new int[3];
		temp[0] = (lRow + uRow) / 2;
		temp[1] = (rCol + lCol) / 2;
		temp[2] = diam;

		if (diam > 20)
			centers.add(temp);

	}

	public void checkCenters() {
		for (int i = 0; i < centers.size(); i++) {
			if (centers.get(i)[2] < diam * .75) {
				centers.remove(i);
			}

		}

	}

	public void printCenters() {
		for (int i = 0; i < centers.size(); i++) {
			say("(" + centers.get(i)[0] + "," + centers.get(i)[1] + ") "
					+ centers.get(i)[2]);
		}

	}

	public void loopMap() {
		for (int i = 0; i < IMG.getHeight(); i++) {
			for (int j = 0; j < IMG.getWidth(); j++) {

				if (i < IMG.getHeight() - 1 && j < IMG.getWidth() - 1 && i > 0
						&& j > 0) {
					if (inPenny[i][j] == 0 && inPenny[i + 1][j + 1] == 0
							&& inPenny[i + 1][j] == 1) {
						inPenny[i][j] = 7;
					} else if (inPenny[i][j] == 0 && inPenny[i - 1][j - 1] == 0
							&& inPenny[i - 1][j] == 1) {
						inPenny[i][j] = 7;
					} else if (inPenny[i][j] == 0 && inPenny[i - 1][j + 1] == 0
							&& inPenny[i - 1][j] == 1) {
						inPenny[i][j] = 7;
					} else if (inPenny[i][j] == 0 && inPenny[i + 1][j - 1] == 0
							&& inPenny[i + 1][j] == 1) {
						inPenny[i][j] = 7;
					}

				}
			}
		}
		for (int i = 0; i < IMG.getHeight(); i++) {
			for (int j = 0; j < IMG.getWidth(); j++) {

				if (i < IMG.getHeight() - 1 && j < IMG.getWidth() - 1 && i > 0
						&& j > 0) {
					if (inPenny[i][j] == 0 && inPenny[i + 1][j + 1] == 7
							&& inPenny[i - 1][j - 1] == 7) {
						inPenny[i][j] = 7;
					} else if (inPenny[i][j] == 0 && inPenny[i - 1][j + 1] == 7
							&& inPenny[i + 1][j - 1] == 7) {
						inPenny[i][j] = 7;
					}
					// else if(inPenny[i][j] == 0 && inPenny[i - 1][j + 1] == 0
					// &&
					// inPenny[i - 1][j] == 1){
					// inPenny[i][j] = 7;
					// }
					// else if(inPenny[i][j] == 0 && inPenny[i + 1][j - 1] == 0
					// &&
					// inPenny[i + 1][j] == 1){
					// inPenny[i][j] = 7;
					// }

				}
			}
		}

	}

	public void Map2() {
		boolean more = false;
		do {
			for (int i = 0; i < IMG.getHeight(); i++) {
				for (int j = 0; j < IMG.getWidth(); j++) {
					// if(inNeighborHood(7, i, j) == 1 && inPenny[i][j] == 0 &&
					// (inPenny[i][j-1] == 1 || inPenny[i][j+1] == 1)){
					// inPenny[i][j] = 7;
					// }

					if (i < IMG.getHeight() - 1 && j < IMG.getWidth() - 1
							&& i > 0 && j > 0) {
						if (inPenny[i][j] == 0 && inPenny[i][j + 1] == 1) {
							inPenny[i][j] = 7;
							more = true;
						} else if (inPenny[i][j] == 0 && inPenny[i][j - 1] == 1) {
							inPenny[i][j] = 7;
							more = true;

						} else if (inPenny[i][j] == 0 && inPenny[i - 1][j] == 1) {
							inPenny[i][j] = 7;
							more = true;

						} else if (inPenny[i][j] == 0 && inPenny[i + 1][j] == 1) {
							inPenny[i][j] = 7;
							more = true;

						} else {
							more = false;
						}
					}
				}
			}

		} while (more);

		// }
	}

	public int inNeighborHood(int test, int row, int col) {
		int count = 0;

		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (row + i < IMG.getHeight() - 1
						&& col + j < IMG.getWidth() - 1 && i > 0 && j > 0)
					if (inPenny[row + i][col + j] == test) {
						count++;

						return 1;

					}

			}
		}
		return 0;
	}

	public void checkOverlap() {
		int leftCheck = 0;
		int rightCheck = 0;
		int rowStart = 0;
		int count = 0;
		for (int i = 1; i < IMG.getHeight() - 1; i++) {
			for (int j = 1; j < IMG.getWidth() - 1; j++) {
				if (inPenny[i][j] == 7 && inPenny[i][j + 1] == 7) {

					leftCheck = j;
					rightCheck = j + 1;
					rowStart = i;
					if (leftCheck < 2)
						leftCheck++;

					while ((inPenny[rowStart][leftCheck] == 7 || inPenny[rowStart][leftCheck - 1] == 7)
							&& ((inPenny[rowStart][rightCheck] == 7 || inPenny[rowStart][rightCheck + 1] == 7))) {
						if (rowStart == 0 || leftCheck == 0) {
							break;
						}

						// say(i + " " + j);
						if (inPenny[rowStart][leftCheck] != 7) {
							leftCheck--;
						}
						if (inPenny[rowStart][rightCheck] != 7) {
							rightCheck++;
						}
						count++;
						// say(count);
						rowStart--;
						// say("ADJACENT");
						// say(i + " " + j);
					}
					if (count > 15) {
						say(i + " " + j + " " + count);
						int w = i;
						while (inPenny[w][j] != 0) {
							inPenny[w][j] = 7;
							w++;
						}

					}
					count = 0;

				}
				// say(i + " " + j);
			}
		}

	}

	public void writeImage() {
		BufferedImage bla = new BufferedImage(IMG.getWidth(), IMG.getHeight(),
				IMG.TYPE_INT_ARGB);
		for (int i = 0; i < IMG.getHeight(); i++) {
			for (int j = 0; j < IMG.getWidth(); j++) {
				if (copyPenny[i][j] == 7) {
					colors[i][j] = Color.RED;
				}
//				if (copyPenny[i][j] == 1) {
//					colors[i][j] = Color.white;
//				}

				for (int w = 0; w < centers.size(); w++) {
					if (i == centers.get(w)[0] && j == centers.get(w)[1]) {
						// say("HI");
						colors[i + 1][j] = Color.BLACK;
						colors[i][j] = Color.BLACK;
						colors[i - 1][j] = Color.BLACK;
						colors[i + 1][j + 1] = Color.BLACK;
						colors[i][j + 1] = Color.BLACK;
						colors[i - 1][j + 1] = Color.BLACK;
						colors[i + 1][j - 1] = Color.BLACK;
						colors[i][j - 1] = Color.BLACK;
						colors[i - 1][j - 1] = Color.BLACK;
					}
				}
			}

		}

		for (int i = 0; i < IMG.getHeight(); i++) {
			for (int j = 0; j < IMG.getWidth(); j++) {
				bla.setRGB(j, i, colors[i][j].getRGB());

			}
		}
		try {
			System.out.println("HI");
			ImageIO.write(bla, "png", new File(
					"C:\\Users\\Zach\\Desktop\\WritenFile.png"));
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// }

	}

}
