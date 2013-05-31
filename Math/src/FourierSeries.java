public class FourierSeries {
	Function f;
	String functionF;
	double leftbound;
	double[] Bn;
	double Ao;
	double[] An;
	char CosOrSin;
	double stepsize;
	double numOfTerms;
	double L;
	double coefficient;
	public FourierSeries(String function, char co, int precision,
			double interval) {
		f = new Function(function);
		functionF = function;
		System.out.println(f.blank);
		CosOrSin = co;
		this.numOfTerms = precision;
		this.stepsize = interval / precision;
		L = interval;
		coefficient = 2 / L;
		if (co == 's') {
			An = new double[(int) numOfTerms];
			An[0] = 0;
			leftbound = 0;
			findAn();

		}
		if (CosOrSin == 's') {
			Bn = new double[(int) numOfTerms];
			Bn[0] = 0;
			findBn();
		}
		if (CosOrSin == 'c') {
			An = new double[(int) numOfTerms];
			An[0] = 0;
			findAn();
		}
		if (CosOrSin == 'f') {
			leftbound = (-1) * L;
			An = new double[(int) numOfTerms];
			Bn = new double[(int) numOfTerms];
			An[0] = 0;
			Bn[0] = 0;
			coefficient = coefficient / 2;

			findBn();
			findAn();
		}

	}

	public void sayInt() {
		for (int i = 0; i < Bn.length; i++) {
			say(Bn[i]);
		}

	}

	public double testFourier(double i) {
		if (Math.abs(i) >= L) {
			if (CosOrSin != 'f') {
				say("X is only defined from 0 to " + L);
				return 0;
			}

			if (CosOrSin == 'f') {
				say("X is only defined from -" + L + " to " + L);
				return 0;
			}

		}

		double sum = 0;
		if (CosOrSin == 's') {

			for (int a = 0; a < Bn.length; a++) {

				sum += Bn[a] * Math.sin((a * i * Math.PI) / L);
			}
			// say(sum);
		} else if (CosOrSin == 'c') {
			for (int a = 0; a < An.length; a++) {
				if (a == 0) {
					sum += Ao;
				} else {
					sum += An[a] * Math.cos(((a) * i * Math.PI) / L);
				}
			}
			// say(sum);
		} else if (CosOrSin == 'f') {
			sum = 0;
			for (int a = 0; a < numOfTerms; a++) {
				if (a == 0) {
					sum += Ao;
				} else {
					sum += An[a] * Math.cos(((a) * i * Math.PI) / L) + Bn[a]
							* Math.sin(((a) * i * Math.PI) / L);
				}
			}
			// say(sum);

		}
		return sum;
	}

	public void say(Object O) {
		System.out.println(O);
	}

	public void findBn() {
		double h = 0;
		double integral = 0;
		double cur = 0;
		for (int i = 0; i < numOfTerms; i++) {
			h = leftbound;
			integral = 0;

			while (h < L) {

				cur = f.Evaluation(h) * (Math.sin((h * i * Math.PI) / L));
				integral += cur * stepsize;

				h = h + stepsize;
			}

			Bn[i] = integral * coefficient;
		}
		Ao = 0;

	}

	public void findAn() {
		double h = 0;
		double integral = 0;
		double cur = 0;
		Ao = 0;
		An[0] = 0;
		for (int i = 1; i < An.length; i++) {

			h = leftbound;
			integral = 0;

			while (h < L) {

				cur = f.Evaluation(h) * (Math.cos((h * i * Math.PI) / L));
				integral += cur * stepsize;

				h = h + stepsize;
			}

			An[i] = integral * coefficient;
		}
		h = leftbound;
		while (h < L) {
			this.Ao += f.Evaluation(h) * stepsize;
			h = h + stepsize;
		}
		Ao = (Ao * coefficient / 2);
	}

	public String toString() {
		String series = "";

		if (CosOrSin == 's' || CosOrSin == 'f') {
			series = Bn[1] + "sin(pi*x/" + L + ")";
			for (int i = 2; i < Bn.length; i++) {
				series = series + " + " + Bn[i] + "sin(" + i + "*pi*x/" + L
						+ ")";

			}

		}

		return series;
	}
}
