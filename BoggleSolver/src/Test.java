import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Test {
	static ArrayList<String> dictionary = new ArrayList<String>();
	public static void main(String[] args){
		
		isInDictionary();
		
		
		String temp = "";
		for(int i = 0; i < dictionary.size(); i++){
			temp = dictionary.get(i);
			if(temp.length() == 7)
			if(temp.charAt(0) == 'S' && temp.charAt(3) == 'S'){
				if(temp.contains(String.valueOf('E'))){
					if(temp.contains(String.valueOf('L'))){
						if(temp.contains(String.valueOf('I'))){
				System.out.println(temp);
				}
				}
				}
			}
			
			
			
		}
		
		
		
		
	}
	public static void isInDictionary() {
		try {

			@SuppressWarnings("resource")
			Scanner search = new Scanner(new FileReader("Dictionary.txt"));
			while (search.hasNext()) {
				dictionary.add(search.next());
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
}
