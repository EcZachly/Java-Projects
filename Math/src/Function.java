import java.util.ArrayList;

public class Function {
	static char[] symbols = { '*', '-', '+', '/' };
	static String[] operations = { "cos", "sin", "x", "tan", "^" };
	char[] logic;
	double[] values;
	String FofX;
	String FofXP;
	int times = 1;
	ArrayList<String> blank;
	ArrayList<String> permHolder;

	public static void main(String[] args) {
		Function a = new Function("(((cos(x))^2)))+(2*x)+(7)");
		a.Parsing("", 0);
		System.out.println(a.blank);
		for (int i = 7; i < 10; i++) {
			System.out.println(a.Evaluation(i));

		}

	}

	public double Evaluation(double value) {
		int search = 0;
		int place = 0;
		int place2 = 0;

		double temp2 = value;

		while (blank.size() > 1) {
			while (0 < blank.size()) {
				while (!blank.isEmpty()) {
					// System.out.println(blank.get(search) + "{");
					temp2 = mathEval(blank.get(search), temp2);
					blank.remove(0);
					if (blank.size() == 0) {
						values[place] = temp2;
					}
					// System.out.println(blank);
					if (!blank.isEmpty()) {
						values[place] = temp2;
						if (blank.get(0).length() < 2) {
							logic[place2] = blank.get(0).charAt(0);
							// System.out.println(temp2 + "{{");
							values[place] = temp2;

							temp2 = value;

							place++;
							place2++;

						}
					}
				}

				// System.out.println(temp2 + "kkk" + place);
			}

		}
		for (int i = 0; i < permHolder.size(); i++) {
			blank.add(permHolder.get(i));
		}

		return (normalOps());

	}

	public Function(String input) {
		FofX = "(" + input + ")";
		FofXP = "(" + input + ")F";
		blank = new ArrayList<String>();
		permHolder = new ArrayList<String>();

		Parsing("", 0);
	}

	public void Parsing(String input, int outside) {
		int index;
		logic = new char[blank.size()];
		values = new double[blank.size()];
		if (outside >= FofXP.length()) {
			return;
		}
		input = input + String.valueOf(FofXP.charAt(outside));
		System.out.println(input);
		if (input.endsWith(")") || input.startsWith("+")
				|| input.startsWith("-") || input.startsWith("/")
				|| input.startsWith("*")) {
			index = input.lastIndexOf("(");
			if (!input.equals("()") && input.length() > 1)
				input = input.substring(index);

			if (input.length() > 2 || input.startsWith("+")
					|| input.startsWith("-") || input.startsWith("/")
					|| input.startsWith("*")) {
				blank.add(input);
				permHolder.add(input);
			}
			String temp = FofXP
					.substring(FofXP.indexOf(input) + input.length());

			String temp2 = FofXP.substring(0, FofXP.indexOf(input));
			FofXP = temp2 + temp;
			outside = -1;
			input = "";
		}
		Parsing(input, outside + 1);
	}

	public boolean containsSymbol() {
		String temp = FofXP.substring(0, 2);
		int i = 0;
		while (i < symbols.length) {
			if (temp.indexOf(symbols[i]) > 0) {
				return true;
			}
			i++;
		}

		return false;
	}

	public boolean containsSymbol(int i) {
		for (int l = 0; l < symbols.length; l++) {
			if (!blank.isEmpty())
				if (blank.get(i) == String.valueOf(symbols[l])) {
					return true;
				}

		}
		return false;
	}

	public boolean containsSymbol(String m) {
		for (int l = 0; l < symbols.length; l++) {
			if (m.length() > 1)
				if (m.charAt(1) == symbols[l] || m.charAt(0) == symbols[l]) {
					System.out.println("TRUE");
					return true;
				}

		}
		System.out.println("FALSE");

		return false;
	}

	public char getSymbol() {
		String temp = FofXP.substring(0, 2);
		int i = 0;
		while (i < symbols.length) {
			if (temp.indexOf(symbols[i]) > 0) {
				return symbols[i];
			}
			i++;
		}
		return (Character) null;
	}

	public double mathEval(String input, double num) {
		// System.out.println(num);
		// System.out.println(input);

		String temp = input.replaceAll("[(]", "");
		temp = temp.replaceAll("[)]", "");

		if (temp.indexOf('x') == -1) {

			if (temp.indexOf('^') == -1) {
				switch (temp) {
				case "cos":
					// System.out.println(Math.cos(num));
					return Math.cos(num);
				case "sin":
					return Math.sin(num);
				case "tan":
					return Math.tan(num);
				case "^":
					String temp2 = temp.substring(temp.indexOf('^'));
					return Math.pow(num, Double.parseDouble(temp2));
				default:
					for (int i = 0; i < temp.length(); i++) {
						if (!Character.isDigit(temp.charAt(i))) {
							return num;
						}
					}
					return Double.parseDouble(temp);

				}
			} else {
				double temp3 = num;
				String temp2 = temp.substring(1);
				double power = Double.parseDouble(temp2);
				return Math.pow(temp3, power);

			}

		} else {
			temp = temp.replace("x", "");
			temp = temp.replaceAll("[*]", "");
			if (temp.length() == 0) {
				// System.out.println(num);
				return num;
			} else if (temp.charAt(0) != '^') {
				// System.out.println( Double.parseDouble(temp)*num + "PPPx");
				return Double.parseDouble(temp) * num;
			} else if (temp.charAt(0) == '^') {
				temp = temp.substring(0);
				return Math.pow(num, num);
			} else {
				return 0;
			}
		}
	}

	public double normalOps() {
		// displayValues();
		double value = values[0];

		boolean symbolic = false;
		for (int l = 0; l < logic.length; l++) {
			symbolic = false;
			for (int p = 0; p < symbols.length; p++) {
				if (logic[l] == symbols[p]) {
					symbolic = true;
				}

			}
			if (!symbolic) {
				logic[l] = '@';
			}
		}

		for (int i = 0; i < values.length - 1; i++) {

			// System.out.println(logic[i]);
			// System.out.println(value);
			// //System.out.println(values[i]);
			// System.out.println(value);
			if (logic[i] != '@') {
				// System.out.println(values[i] + " " + logic[i] + " " +
				// values[i + 1]);

				if (i < values.length - 1) {
					switch (logic[i]) {
					case '*':

						if (i != 0) {

							value = values[0] * values[i + 1];
							break;
						} else {
							value = values[0] * values[1];
							break;
						}

					case '+':
						if (i != 0) {

							value = value + values[i + 1];
							values[i + 1] = value;

							break;
						} else {
							value = values[0] + values[1];
							break;
						}
					case '-':
						if (i != 0) {

							value = value - values[i + 1];
							values[i + 1] = value;

							break;
						} else {
							value = values[0] - values[1];
							break;
						}
					case '/':
						if (i != 0) {

							value = value / values[i + 1];

							values[i + 1] = value;

							break;
						} else {
							value = values[0] / values[1];
							break;
						}
					}

				} else {

					return value;

				}
			}

		}

		return value;

	}

	public void displayValues() {
		for (int i = 0; i < values.length; i++) {
			System.out.println(values[i] + "V");
			System.out.println(logic[i] + "C");
		}
	}
}
