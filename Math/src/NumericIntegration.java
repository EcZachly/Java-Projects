import java.util.ArrayList;


public class NumericIntegration {

	
	public static void main(String[] args){
		double x = .75;
		double h = 0;
		ArrayList<String> An = new ArrayList<String>();
		ArrayList<String> Bn = new ArrayList<String>();
		An.add("0");
		Bn.add("0");
		double interval = 10;
		double coefficient = 1/interval;
		double integral = 0;
		double cur = 0;
		double next = 0;
		double cosCur = 0;
		double cosNext = 0;
		double cosInt = 0;
		double Ao = 0;
		
		
		for(int i = 1; i <= 4000; i++){
			h = -1*interval;
		integral = 0;
		cosInt = 0;
		Ao = 0;
		
		while(h < interval){
			
			cur = h*h*(Math.sin((h*i*Math.PI)/interval));
			next = (h + .01)*(h + .01)*(Math.sin(((h+.01)*i*Math.PI)/interval));
			cosCur = (h+.01)*(h+.01)*(Math.cos(((h+.01)*i*Math.PI)/interval));
			cosNext = h*h*(Math.cos(((h)*i*Math.PI)/interval));
			cosInt += (cosCur + cosNext)*.01*.5;
			Ao += h*.01;
			integral += (cur + next)*.01*.5;
				h = h + .01;
		}
		integral = integral*coefficient;
		cosInt = cosInt*coefficient;
		Ao = Ao*coefficient*.5;
		An.add(String.valueOf(integral));
			Bn.add(String.valueOf(cosInt));
		}

		double answer = 0;
		for(int i = 1; i < An.size(); i++){
			answer += Double.parseDouble(An.get(i))*(Math.sin(((i)*Math.PI*x)/interval)) + Double.parseDouble(Bn.get(i))*(Math.cos(((i)*Math.PI*x)/interval)); 
		}
		
		System.out.println(answer);
		System.out.println(Math.pow(Math.E, x));
		System.out.println(x*x);
	}
	}

