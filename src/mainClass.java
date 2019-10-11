import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner; 
import javax.swing.JOptionPane;

// Course: CS4306
//Student name: Matthew Prindle
//Student ID: 000-775-6858
//Assignment #:1 #1
//Due Date: 1/24/2017
//Signature: mattP
//(The signature means that the program is your own work)
//Score: ______________

public class mainClass {

	public static void main(String[] args)
	{
		//a boolean we use to flag an array to be sorted
		boolean needsSorting = false;
		//an empty starting array with a size of 1
		int[]inputArray = new int[1];
		//create an object that lets the user browser for our input .txt
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		//display the input browser window
		int userReturnValue = fileChooser.showOpenDialog(null);
		//Create a scanner object to read our input with
		Scanner sc;
		try {
			//an integer we will use to count as we iterate through our input
			int c=0;
			//tell the scanner to read the selected user input
			sc = new Scanner(fileChooser.getSelectedFile());
			//iterate through all the input lines
			while (sc.hasNextLine()) 
		       {
				//update our counter
				c++;
				//store the next integer as a variable to test and be given to the array.
		        int i = sc.nextInt();
		        //check to see if the input is unsorted by comparing our current value with our previous value, if it is, flag the array to be sorted when we are done
		        if(inputArray[c-1] > i) {needsSorting = true;}
		        //expand the array if needed.
		        if(inputArray.length == c) {inputArray = expand(inputArray);}
		        //store the input into our array
		        inputArray[c] = i;
		       }
			sc.close();
			//have the java class sort the array for us because we are lazy
			if(needsSorting == true) {Arrays.sort(inputArray);}
			} 
		catch (FileNotFoundException e) {}
		
		//Ask the user for which value they want us to find
		
		int key = Integer.parseInt(JOptionPane.showInputDialog("Please enter a value that you would like to search for"));
		//search the array, if nothing is found we will be given a value of -1
		int result = binarySearch(inputArray,0,inputArray.length,key);
		//if we did not find the value, print out a special message, otherwise print the value and where it was found
		if(result == -1) {JOptionPane.showMessageDialog(null, "The Value was not found!");}
		else JOptionPane.showMessageDialog(null, "Found the value: "+key+" at position: "+result);
		
	}
	
	//A method that searches for a value in an array using the Binary insertion sort algorithm, 
	//if it does not find the value it will return -1
	//the input array must be sorted for accurate results!
	public static int binarySearch(int inputarray[], int lowpoint, int highpoint, int targetvalue)
	{
		//check to see if the value was not found and break the recursion
		if(lowpoint == highpoint) {return -1;}
		//a variable that will be the pivot in which we split the array.
		int midpoint;
		midpoint = lowpoint + ((highpoint - lowpoint) / 2);
		//since we know the array is sorted, we check to see if the midpoint value is great or less than our target value, 
		//and call the proper recursive method on the greater or lesser half of the array.
		if (targetvalue > inputarray[midpoint])
			 return binarySearch (inputarray, midpoint + 1, highpoint, targetvalue);
		else if (targetvalue < inputarray[midpoint])
			 return binarySearch (inputarray, lowpoint, midpoint, targetvalue);
		//if our value is found, we return it's location
		 return midpoint;
	}
	//a simple and super inefficient method that makes an array 1 slot bigger
	public static int[] expand(int[] inputArray) {
		//make a new array with one extra memory slot
	    int[] newArray = new int[inputArray.length + 1];
	    //tell java to copy our old array into the new larger array
	    System.arraycopy(inputArray, 0, newArray, 0, inputArray.length);
	    //set the old array to the new, larger array and let java's garbage bot take care of it
	    inputArray = newArray;
	    //return a new, larger array with all our old data
	    return inputArray;
	}
}
