import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.JOptionPane;

public class GraphPanel extends Frame {
	Line2D line1 = new Line2D.Double(0, 0, 250, 250);
	static FourierSeries funct;
	Stroke drawingStroke = new BasicStroke(2);
	static int terms;
	int interval;
	static int count = 0;

	public void paint(Graphics g) {

		Graphics2D graph = (Graphics2D) g;
		graph.translate(400, 400);
		graph.setStroke(drawingStroke);
		graph.setPaint(Color.black);
		if (count == 0) {
			graph.drawLine(0, -400, 0, 400);
			graph.drawLine(-400, 0, 400, 0);
		}
		double i = 0;
		double x1 = 0;
		double x2 = 0;
		double y1 = funct.testFourier(x1);
		double y2 = funct.testFourier(x1);
		for (int z = (int) funct.numOfTerms; z < terms;z++ ) {

			funct = new FourierSeries(funct.functionF, funct.CosOrSin,
					(int) (funct.numOfTerms + z), funct.L);
			graph = (Graphics2D) g;
			i = -1 * funct.L + .01 * funct.L;
			while (i < funct.L) {

				x1 = x2;
				y1 = y2;
				x2 = i;
				y2 = funct.testFourier(i);

				if (Math.pow(-1, z) == 1) {
					graph.setPaint(Color.yellow);
				} else {
					graph.setPaint(Color.pink);
				}

				if (z == terms - 1) {
					graph.setPaint(Color.red);
				}
				if (i > -1 * funct.L + .01 * funct.L) {
					line1.setLine((800 / funct.L) * x2, -(800 / funct.L) * y2,
							(800 / funct.L) * x1, -(800 / funct.L) * y1);
					graph.draw(line1);
				}

				if (i + .01 * funct.L < funct.L) {
					i = i + .01 * funct.L;
				} else {
					i = funct.L;
				}
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String args[]) {
		for (int i = 0; i < 5; i++) {
			String function = JOptionPane
					.showInputDialog("Please enter in your function");
			char c = JOptionPane
					.showInputDialog(
							"Full Fourier Series(f), Fourier Cosine series(c), or Fourier Sine series(s)?")
					.charAt(0);
			double interval = Double.parseDouble(JOptionPane
					.showInputDialog("Interval Length?"));
			int precision = Integer.parseInt(JOptionPane
					.showInputDialog("Number of terms to start with?"));
			int ter = Integer.parseInt(JOptionPane
					.showInputDialog("Number of terms to end with"));

			GraphPanel.terms = ter;
			FourierSeries f = new FourierSeries(function, c, precision,
					interval);
			Frame frame = new GraphPanel();
			GraphPanel.funct = f;
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					System.exit(0);
				}
			});
			frame.setSize(800, 800);
			frame.setVisible(true);
		}
	}

	public double ScaleY() {
		double i = (-1 * funct.L);
		double holds;
		double largest = 0;
		while (i < funct.L) {
			holds = funct.testFourier(i);
			System.out.println(holds + " " + largest);
			if (holds > largest) {
				largest = holds;
			}
			i = (i + .01 * funct.L);
		}
		return largest;
	}
}