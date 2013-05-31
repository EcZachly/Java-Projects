
public class EstimatingE {

	
	public static void main(String[] args){
		double h = 2.00;
		double iterate = 0;
		double temp = 0;
		double checker = 1000; 
		while(h < 3){
			iterate = ((Math.pow(h, .0000001)) - 1)/.0000001;
			if(Math.abs(iterate - 1) < checker){
				checker = Math.abs(iterate - 1);
				System.out.println(Math.abs(iterate - 1) + " " + temp);
				temp = h;
				System.out.println(temp);
			}
			h = h + .00001;
			}
			System.out.println(temp);
			System.out.println(Math.E);
		}
		
	}
