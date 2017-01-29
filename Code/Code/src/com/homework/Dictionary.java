package com.homework;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary {

	private static final String defaultDictFilename = "data/dictionary.txt";
	private String dictFilename;
	private static BufferedReader dictionaryReader;
	
	
	public Dictionary() {
		super();
		dictFilename = defaultDictFilename;
		try {
			setBr(new BufferedReader(new FileReader(dictFilename)));
		} catch (FileNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Dictionary(String dictFilename, BufferedReader br) {
		super();
		this.dictFilename = dictFilename;
		this.setBr(br);
	}

	protected String findDefinitionInDict(String word)	{
		String definition = "";
		StringBuilder sb = new StringBuilder();
		
		//Enough bad memory, let's iterate through the file line by line instead of loading it all in
		String line = null;
		long lineCount = 0;
		
		try {
			while (null != (line = dictionaryReader.readLine())) {
				lineCount ++;
				
				// Find line that matches exactly the word
				// Ideally line contains only one word && is in ALL CAP
				if (isWordLine(line)
						&& 0 == line.compareToIgnoreCase(word)){

					System.out.println("Found definition for:" + word);

					// Capture the following paragraph starting with Defn:
					String nextLine = ";";
					Boolean recordDef = false;
					
					while (null != (nextLine = dictionaryReader.readLine())) {
						if (nextLine.startsWith("Defn:")) {
							System.out.println("Start recording definition for " + word);
							recordDef = true;							
						}
						if (recordDef && nextLine.isEmpty()) {
							System.out.println("Finished recording definition for " + word);
							recordDef = false;
							return sb.toString();
						}
						if (recordDef) {
							sb.append(nextLine);							
						}
					}
					
					// matchingLineNumber = lineCount;
					
					// Other optimizations: Capture all meanings of this word.
					
					System.out.println("Recording dictionary line#" + lineCount);
				}
				System.out.println("Searching dictionary line#" + lineCount);
			}				
			
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return definition;
	}
	
	private Boolean isWordLine(String line) {
		String trimmedLine = line.trim();
		String[] segments = trimmedLine.split(" ");
		return (1 == segments.length
				&& trimmedLine.equals(trimmedLine.toUpperCase()));
	}

	public BufferedReader getBr() {
		return dictionaryReader;
	}

	public void setBr(BufferedReader br) {
		this.dictionaryReader = br;
	}


}
