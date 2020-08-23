import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;
//------------------------------------------------------------------------------------
//Assignment 3
//Part: 1
//COMP 249
//Written by: Antonio Reda (#40155615)
//Due date: Sunday, August 9th.
//Purpose: Create a program that reads a file and creates a new file with all the words in the original 
//file in alphabetical order just like a sub dictionary
//------------------------------------------------------------------------------------
/**
 * This class prompts the user for a file name and created a subDictionary.txt file which includes all the words in the original
 * file in alphabetical order in a sub dictionary fashion (duplicates are removed, punctuation is removed etc)
 * @author Antonio Reda
 *
 */
public class SubDictionary 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner keyIn=new Scanner(System.in);
		System.out.println("WELCOME to Antonio Reda's Sub-Dictionary file sorting program.\n");
		System.out.println("Please Enter the name of the file you want to read followed by ENTER: ");
		//The welcome message is displayed and the user is prompted to write the name  of the file (the .txt must be written by the user).
		String user=keyIn.next();
		keyIn.close();
		//Prompts the user to enter a file name and stores it as a string.
		File userFile1=new File (user);
		int entries=entriesInFile(userFile1);
		//Looks for the file and finds the number of entries.
		File userFile2=new File (user);
		Scanner reader=new Scanner(userFile2);
		//Looks for the same file but will now utilize the next() method.
		String[] allEntries=new String[entries];
		ArrayList<String>fileContents=new ArrayList<String>();
		//An array list is created to store the contents of the file.
		while (reader.hasNext())
		{
			try
			{
				fileContents.add(reader.next());
			}
			catch(Exception e)
			{
				System.out.println("\nError reading the file.");
			}
		}
		//This while loop fills the array list with every entry in the file.
		for (int i=0; i<fileContents.size(); i++)
		{
			allEntries[i]=fileContents.get(i);
		}
		//This for loop fills a standard String array with the contents of the arrayList.
		reader.close();
		boolean valid=false;
		while(valid==false)
		{
			valid = true;
		  for(int i=0;i<allEntries.length-1;i++)
		  {
			 int num=0;
		    if(allEntries[i]!=null&&allEntries[i+1]!=null)
		    {
		    	String word1=allEntries[i]; 
		      	String word2=allEntries[i+1];
		      	num=word1.compareToIgnoreCase(word2);
		    }
		    else if(allEntries[i]==null&&allEntries[i+1]==null)
		    {
		    	num=0;
		    }
		    else if(allEntries[i] == null)
		    {
		    	num=1;
		    }
		    else 
		    {
		    	num=-1;
		    }
		
		    if(num>0)
		    {
		    	String replacement=allEntries[i];
		    	allEntries[i]=allEntries[i+1];
		    	allEntries[i+1]=replacement;
		    	valid=false;
		    }
		  }
		}
		//This while loop sorts the array in alphabetical order.
		for (int i=0; i<allEntries.length; i++)
		{
			if (allEntries[i]!=null)
			{
				allEntries[i]=allEntries[i].toUpperCase();
				for (int j=0; j<allEntries.length-1; j++)
				{
					if (allEntries[i].length()>1)
					{
						if (allEntries[i].substring(allEntries[i].length()-1,allEntries[i].length()).equals(",")||allEntries[i].substring(allEntries[i].length()-1,allEntries[i].length()).equals(".")||allEntries[i].substring(allEntries[i].length()-1,allEntries[i].length()).equals("!")||allEntries[i].substring(allEntries[i].length()-1,allEntries[i].length()).equals(";")||allEntries[i].substring(allEntries[i].length()-1,allEntries[i].length()).equals(":")||allEntries[i].substring(allEntries[i].length()-1,allEntries[i].length()).equals("?")||allEntries[i].substring(allEntries[i].length()-1,allEntries[i].length()).equals("=")||allEntries[i].substring(allEntries[i].length()-1,allEntries[i].length()).equals("'"))/////////
						{
							allEntries[i]=allEntries[i].substring(0,allEntries[i].length()-1 );
						}
					}//checks for any strings that end with punctuation and removes it.
					else
					{
						if (!(allEntries[i].equalsIgnoreCase("A")||allEntries[i].equalsIgnoreCase("I")))
						{
							allEntries[i]="";
						}
						//checks for single characters and allows only I and A.
					}
					if (allEntries[i].contains("’"))
					{
						if (allEntries[i].length()>1)
						{
							allEntries[i]=allEntries[i].substring(0, allEntries[i].indexOf("’"));
						}
						else
						{
							allEntries[i]=allEntries[i].substring(0, 1);
						}
					}
					//checks for strings that contain a specific apostrophe suffix and adjusts the word.
					if (allEntries[i].contains("0")||allEntries[i].contains("1")||allEntries[i].contains("2")||allEntries[i].contains("3")||allEntries[i].contains("4")||allEntries[i].contains("5")||allEntries[i].contains("6")||allEntries[i].contains("7")||allEntries[i].contains("8")||allEntries[i].contains("9"))
					{
						allEntries[i]="";
					}
					//checks for strings with numbers in them and replaces them with the null string.
					if (i!=j&&allEntries[i].equalsIgnoreCase(allEntries[j]))
					{
						allEntries[j]="";
					}
					//checks for duplicate entries and replaces them with empty strings.
				}
			}
		}
		//this for loop makes sure that any duplicate is replaced with the empty string.
		int newArraySize=1;
		for (int i=0; i<allEntries.length-1; i++)
		{
			if (allEntries[i]!="")
			{
				newArraySize++;
			}
		}
		//This for loop calculates what the size of the corrected array is (without the duplicates).
		String[] newArr=new String [newArraySize+1];
		//created the new string array that has the correct amount of entries and one null string.
		int counter=0;
		for (int i=0; i<allEntries.length; i++)
		{
			try
			{
				if (!(allEntries[i].equals("")))
				{
					newArr[counter]=allEntries[i];
					counter++;
				}
			}
			catch(Exception e)
			{
				newArraySize--;
			}
			//Fills up a new string array without the empty strings.
		}
		PrintWriter outputFile=new PrintWriter(new FileOutputStream("SubDictionary.txt"));
		//creates the file output called SubDictionary.txt
		outputFile.println("The document produced this sub-dictionary, which includes "+newArraySize+" entries.");
		System.out.println("The document produced this sub-dictionary, which includes "+newArraySize+" entries.");
		int alphabet=0;
		if (newArr[alphabet].substring(0,1).equals("A"))
		{
			outputFile.println("\nA\n==");
			System.out.println("\nA\n==");
		}
		//if the String contains the letter A at the beginning, it prints the letter A pointer in the output file. 
		for (int i=0; i<newArr.length-1; i++)
		{	
			try
			{
				if (newArr[i].substring(0,1).equals("A")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
						
					}
					else
					alphabet++;
				}
				//As long as the String contains an A at the beginning of the word, it is printed in the output file and 
				//the nested if checks if there is a next entry in the string array or if it is the last member of the array.
			}
			catch (Exception e)
			{
				
			}
		}
		if (newArr[alphabet].substring(0,1).equals("B"))
		{
			outputFile.println("\nB\n==");
			System.out.println("\nB\n==");
		}
		//The algorithm used for the letter A is repeated 26 times for every letter in the alphabet.
		for (int i=0; i<newArr.length-1; i++)
		{		
			try
			{
				if (newArr[i].substring(0,1).equals("B")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
						
					}
					else
					alphabet++;
				}
			}
			catch (Exception e)
			{
				
			}
		}
		if (newArr[alphabet].substring(0,1).equals("C"))
		{
			outputFile.println("\nC\n==");
			System.out.println("\nC\n==");
		}
		for (int i=0; i<newArr.length-1; i++)
		{		
			try
			{
				if (newArr[i].substring(0,1).equals("C")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
						
					}
					else
					alphabet++;
				}
			}
			catch (Exception e)
			{
				
			}
		}
		if (newArr[alphabet].substring(0,1).equals("D"))
		{
			outputFile.println("\nD\n==");
			System.out.println("\nD\n==");
		}
		for (int i=0; i<newArr.length-1; i++)
		{	
			try
			{
				if (newArr[i].substring(0,1).equals("D")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
						
					}
					else
					alphabet++;
				}
			}
			catch (Exception e)
			{
				
			}
		}
		if (newArr[alphabet].substring(0,1).equals("E"))
		{
			outputFile.println("\nE\n==");
			System.out.println("\nE\n==");
		}
		for (int i=0; i<newArr.length-1; i++)
		{			
			try
			{
				if (newArr[i].substring(0,1).equals("E")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
						
					}
					else
					alphabet++;
				}
			}
			catch (Exception e)
			{
				
			}
		}
		if (newArr[alphabet].substring(0,1).equals("F"))
		{
			outputFile.println("\nF\n==");
			System.out.println("\nF\n==");
		}
		for (int i=0; i<newArr.length-1; i++)
		{		
			try
			{
				if (newArr[i].substring(0,1).equals("F")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
						
					}
					else
					alphabet++;
				}
			}
			catch (Exception e)
			{
				
			}
		}
		if (newArr[alphabet].substring(0,1).equals("G"))
		{
			outputFile.println("\nG\n==");
			System.out.println("\nG\n==");
		}
		for (int i=0; i<newArr.length-1; i++)
		{		
			try
			{
				if (newArr[i].substring(0,1).equals("G")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
						
					}
					else
					alphabet++;
				}
			}
			catch (Exception e)
			{
				
			}
		}
		if (newArr[alphabet].substring(0,1).equals("H"))
		{
			outputFile.println("\nH\n==");
			System.out.println("\nH\n==");
		}
		for (int i=0; i<newArr.length-1; i++)
		{		
			try
			{
				if (newArr[i].substring(0,1).equals("H")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
						
					}
					else
					alphabet++;
				}
			}
			catch (Exception e)
			{
				
			}
		}
		if (newArr[alphabet].substring(0,1).equals("I"))
		{
			outputFile.println("\nI\n==");
			System.out.println("\nI\n==");
		}
		for (int i=0; i<newArr.length-1; i++)
		{		
			try
			{
				if (newArr[i].substring(0,1).equals("I")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
						
					}
					else
					alphabet++;
				}
			}
			catch (Exception e)
			{
				
			}
		}
		if (newArr[alphabet].substring(0,1).equals("J"))
		{
			outputFile.println("\nJ\n==");
			System.out.println("\nJ\n==");
		}
		for (int i=0; i<newArr.length-1; i++)
		{			
			try
			{
				if (newArr[i].substring(0,1).equals("J")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
						
					}
					else
					alphabet++;
				}
			}
			catch (Exception e)
			{
				
			}
		}
		if (newArr[alphabet].substring(0,1).equals("K"))
		{
			outputFile.println("\nK\n==");
			System.out.println("\nK\n==");
		}
		for (int i=0; i<newArr.length-1; i++)
		{			
			try
			{
				if (newArr[i].substring(0,1).equals("K")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
						
					}
					else
					alphabet++;
				}
			}
			catch (Exception e)
			{
				
			}
		}
		if (newArr[alphabet].substring(0,1).equals("L"))
		{
			outputFile.println("\nL\n==");
			System.out.println("\nL\n==");
		}
		for (int i=0; i<newArr.length-1; i++)
		{		
			try
			{	
				if (newArr[i].substring(0,1).equals("L")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
						
					}
					else
					alphabet++;
				}
			}
			catch (Exception e)
			{
				
			}
		}
		if (newArr[alphabet].substring(0,1).equals("M"))
		{
			outputFile.println("\nM\n==");
			System.out.println("\nM\n==");
		}
		for (int i=0; i<newArr.length-1; i++)
		{		
			try
			{
				if (newArr[i].substring(0,1).equals("M")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
						
					}
					else
					alphabet++;
				}
			}
			catch (Exception e)
			{
				
			}
		}
		if (newArr[alphabet].substring(0,1).equals("N"))
		{
			outputFile.println("\nN\n==");
			System.out.println("\nN\n==");
		}
		for (int i=0; i<newArr.length-1; i++)
		{		
			try
			{
				if (newArr[i].substring(0,1).equals("N")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
						
					}
					else
					alphabet++;
				}
			}
			catch (Exception e)
			{
				
			}
		}
		if (newArr[alphabet].substring(0,1).equals("O"))
		{
			outputFile.println("\nO\n==");
			System.out.println("\nO\n==");
		}
		for (int i=0; i<newArr.length-1; i++)
		{		
			try
			{
				if (newArr[i].substring(0,1).equals("O")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
						
					}
					else
					alphabet++;
				}
			}
			catch (Exception e)
			{
				
			}
		}
		if (newArr[alphabet].substring(0,1).equals("P"))
		{
			outputFile.println("\nP\n==");
			System.out.println("\nP\n==");
		}
		for (int i=0; i<newArr.length-1; i++)
		{			
			try
			{
				if (newArr[i].substring(0,1).equals("P")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
						
					}
					else
					alphabet++;
				}
			}
			catch (Exception e)
			{
				
			}
		}
		if (newArr[alphabet].substring(0,1).equals("Q"))
		{
			outputFile.println("\nQ\n==");
			System.out.println("\nQ\n==");
		}
		for (int i=0; i<newArr.length-1; i++)
		{		
			try
			{
				if (newArr[i].substring(0,1).equals("Q")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
						
					}
					else
					alphabet++;
				}
			}
			catch (Exception e)
			{
				
			}
		}
		if (newArr[alphabet].substring(0,1).equals("R"))
		{
			outputFile.println("\nR\n==");
			System.out.println("\nR\n==");
		}
		for (int i=0; i<newArr.length-1; i++)
		{			
			try
			{
				if (newArr[i].substring(0,1).equals("R")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
						
					}
					else
					alphabet++;
				}
			}
			catch (Exception e)
			{
				
			}
		}
		if (newArr[alphabet].substring(0,1).equals("S"))
		{
			outputFile.println("\nS\n==");
			System.out.println("\nS\n==");
		}
		for (int i=0; i<newArr.length-1; i++)
		{		
			try
			{
				if (newArr[i].substring(0,1).equals("S")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
						
					}
					else
					alphabet++;
				}
			}
			catch (Exception e)
			{
				
			}
		}
		if (newArr[alphabet].substring(0,1).equals("T"))
		{
			outputFile.println("\nT\n==");
			System.out.println("\nT\n==");
		}
		for (int i=0; i<newArr.length-1; i++)
		{		
			try
			{
				if (newArr[i].substring(0,1).equals("T")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
						
					}
					else
					alphabet++;
				}
			}
			catch (Exception e)
			{
				
			}
		}
		if (newArr[alphabet].substring(0,1).equals("U"))
		{
			outputFile.println("\nU\n==");
			System.out.println("\nU\n==");
		}
		for (int i=0; i<newArr.length-1; i++)
		{		
			try
			{
				if (newArr[i].substring(0,1).equals("U")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
						
					}
					else
					alphabet++;
				}
			}
			catch (Exception e)
			{
				
			}
		}
		if (newArr[alphabet].substring(0,1).equals("V"))
		{
			outputFile.println("\nV\n==");
			System.out.println("\nV\n==");
		}
		for (int i=0; i<newArr.length-1; i++)
		{		
			try
			{
				if (newArr[i].substring(0,1).equals("V")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
						
					}
					else
					alphabet++;
				}
			}
			catch (Exception e)
			{
				
			}
		}
		if (newArr[alphabet].substring(0,1).equals("W"))
		{
			outputFile.println("\nW\n==");
			System.out.println("\nW\n==");
		}
		for (int i=0; i<newArr.length-1; i++)
		{			
			try
			{
				if (newArr[i].substring(0,1).equals("W")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
						
					}
					else
					alphabet++;
				}
			}
			catch (Exception e)
			{
				
			}
		}
		if (newArr[alphabet].substring(0,1).equals("X"))
		{
			outputFile.println("\nX\n==");
			System.out.println("\nX\n==");
		}
		for (int i=0; i<newArr.length-1; i++)
		{		
			try
			{
				if (newArr[i].substring(0,1).equals("X")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
						
					}
					else
					alphabet++;
				}
			}
			catch (Exception e)
			{
				
			}
		}
		if (newArr[alphabet].substring(0,1).equals("Y"))
		{
			outputFile.println("\nY\n==");
			System.out.println("\nY\n==");
		}
		for (int i=0; i<newArr.length-1; i++)
		{			
			try
			{
				if (newArr[i].substring(0,1).equals("Y")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
						
					}
					else
					alphabet++;
				}
			}
			catch (Exception e)
			{
				
			}
		}
		if (newArr[alphabet].substring(0,1).equals("Z"))
		{
			outputFile.println("\nZ\n==");
			System.out.println("\nZ\n==");
		}
		for (int i=0; i<newArr.length-1; i++)
		{		
			try
			{
				if (newArr[i].substring(0,1).equals("Z")||newArr[i].length()==0)
				{
					outputFile.println(newArr[alphabet]);
					System.out.println(newArr[alphabet]);
					if (newArr[i+1]==null)
					{
					}
					else
					alphabet++;
				}
			}
			catch (Exception e)
			{
				
			}
		}
		//These series of for, try, if statements creates the sub-dictionary by indexing every valid entry with its 
		//respected letter.
		outputFile.close();
		System.out.println("\nThe SubDictionary.txt file has been created and the contents can be previewed above.\n\nThank you for using the Sub-Dictionary file sorting program!");
	}
	/**
	 * This method takes in a file and returns an int that represents the number of valid words in the file.
	 * @param input represents the file you want to read from to make the subDictionary.txt file.
	 * @return an int that represents the number of valid words in the file.
	 * @throws FileNotFoundException
	 */
	public static int entriesInFile(File input) throws FileNotFoundException
	{
		Scanner inputReader=new Scanner(input);
		int counter=1;
		while (inputReader.hasNext())
		{
			String contents=inputReader.next();
			if (contents.contains(",")||contents.contains("=")||contents.contains("'")||contents.contains(";")||contents.contains(":")||contents.contains("?")||contents.contains("!")||contents.contains("."))
			{
				//checks for ","
				if (contents.contains(","))
				{
					if (contents.indexOf(",")==contents.length()-1)
					{
						counter++;
					}
				}
				//checks for " ' "
				if (contents.contains("'"))
				{
					if (contents.substring((contents.indexOf("'")+1)).equals("m")||contents.substring((contents.indexOf("'")+1)).equals("s"))
					{
						counter++;
					}
				}
				//checks for ":"
				if (contents.contains(":"))
				{
					if (contents.indexOf(":")==contents.length()-1)
					{
						counter++;
					}
				}
				//checks for ";"
				if (contents.contains(";"))
				{
					if (contents.indexOf(";")==contents.length()-1)
					{
						counter++;
					}
				}
				//checks for "?"
				if (contents.contains("?"))
				{
					if (contents.indexOf("?")==contents.length()-1)
					{
						counter++;
					}
				}
				//checks for "!"
				if (contents.contains("!"))
				{
					if (contents.indexOf("!")==contents.length()-1)
					{
						counter++;
					}
				}
				//checks for "."
				if (contents.contains("."))
				{
					if (contents.indexOf(".")==contents.length()-1)
					{
						counter++;
					}
				}
				//checks for "="
				if (contents.contains("="))
				{
					int equal=contents.indexOf("=");
					if (contents.charAt(equal)==0)
					{
						counter++;
					}
				}
			}
			else
			counter++;
		}
		inputReader.close();
		return counter;
	}
}
