package bonus;

import java.util.ArrayList;

public class History {
	ArrayList<String[]> history;

	// every history item will have a guess and a response
	public History() {
		// TODO Auto-generated constructor stub
		history = new ArrayList<String[]>();
	}

	public String returnFullHistory() {
		String result = "";
		for (String[] item : history) {
			result += "Request: " + item[0] + "\t\t\t\t\tResponse: " + item[1] + "\n";
		}
		return result;
	}
	
	public void add(String guess, String response){
		String[] newItem = new String[2];
		newItem[0] = guess;
		newItem[1] = response;
		history.add(newItem);
	}
	
	public String[] get(int index){
		if (index < 0 || index >= history.size()){
			return null;
		}
		return history.get(index);
	}

	public void printHistory(){
		System.out.println(returnFullHistory());
	}
}
