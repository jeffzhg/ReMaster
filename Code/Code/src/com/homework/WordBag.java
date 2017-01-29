package com.homework;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// I'm a class who can load a bag of words from any file on my file system.
public class WordBag {

	private static final String defaultWordBagFilename = "data/words.txt";
	private String wordBagFilename;
	private static BufferedReader br;
	
		
	public WordBag() {
		super();
		wordBagFilename = defaultWordBagFilename;
		try {
			setBr(new BufferedReader(new FileReader(wordBagFilename)));
		} catch (FileNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public WordBag(String wordBagFilename, BufferedReader br) {
		super();
		this.wordBagFilename = wordBagFilename;
		this.setBr(br);
	}

	public long findWordInMyBag(String word)	{
		long matchingLineNumber = -1;
		
		//Enough bad memory, let's iterate through the file line by line instead of loading it all in
		String line = null;
		long lineCount = 0;
		
		try {
			while (null != (line = br.readLine())) {
				lineCount ++;
				if (word.equalsIgnoreCase(line.toString())){
					matchingLineNumber = lineCount;
					break;
				}
			}				
			return matchingLineNumber;
			
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	public BufferedReader getBr() {
		return br;
	}

	public void setBr(BufferedReader br) {
		this.br = br;
	}
	
}
