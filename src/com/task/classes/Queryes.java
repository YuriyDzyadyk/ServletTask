package com.task.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

public class Queryes {

	private int charCounter = 0;

	public List<String> findIdentity(String filePath, String textToSearch, Integer length, Integer limit) {
		List<String> result = new ArrayList<>();
		double countBuffer = 0;

		BufferedReader br;
		String line = "";

		try {
			br = new BufferedReader(new FileReader(filePath));
			try {
				while ((line = br.readLine()) != null) {
					// countLine++;
					// System.out.println(line);
					String[] words = line.split(" ");

					for (String word : words) {
					
							if (word.contains(textToSearch)) {
								countBuffer++;
								// System.out.print(word.substring(0, length) +
								// " ");
								if (length != null) {
									if (charCounter < limit || charCounter == limit) {
										
										if (word.length() < length || word.length() == length) {
											// System.out.println(word);
											result.add(word);
											charCounter = charCounter + word.length();
										}
									}
								} else {
									if (charCounter < limit || charCounter == limit) {

										// System.out.println(word);
										result.add(word);
										charCounter = charCounter + word.length();
									}
							}
						}
					}

					if (countBuffer > 0) {
						countBuffer = 0;

					}

				}
				br.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		return result;

	}

	public Integer limitCheck(Integer limit) {
		if (limit == null) {
			limit = 10000;
			return limit;
		} else {
			return limit;
		}

	}

	public JSONObject getMeteData(String filePath) throws IOException {
		Path path = Paths.get(filePath);
		
		BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);

		JSONObject meteData = new JSONObject();
	
		meteData.put("file creation time:", attr.creationTime().toString());
		meteData.put("last modified time:", attr.lastModifiedTime().toString());
		meteData.put("file size:" , attr.size() + " KB");
		meteData.put("file name:" , path.toFile().getName());
				 
				
		return meteData;
	}

}
