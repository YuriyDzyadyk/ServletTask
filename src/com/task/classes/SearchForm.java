package com.task.classes;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

public class SearchForm {

	private boolean isMetaData = true;
	private String textToSearch = "int[]";
	String Path = new File("").getAbsolutePath();
	private String filePath = Path+"\\Workspace\\ServletTask\\WebContent\\Tests.txt";
	private Integer length;
	private Integer limit;
	private JSONObject metaDataContent = null;

	public SearchForm(String textToSearch, String getlength, String getlimit, String metaData) {
		this.isMetaData = isActive(metaData);
		this.textToSearch = textToSearch;
		this.length = getlength(getlength);
		this.limit = getlimit(getlimit);

	}

	private Integer getlength(String getlength) {
		if (getlength.equals("")) {
			
		}else{
			length = Integer.parseInt(getlength);
		}
		return length;
	}

	private Integer getlimit(String getlimit) {
		
		if (getlimit.isEmpty()) {
			limit = 10000;
		}else{
			limit = Integer.parseInt(getlimit);
		}
		return limit;
	}

	boolean isActive(String metaData) {
		if ("Yes".equals(metaData)) {
			return isMetaData = true;
		} else {
			return isMetaData = false;
		}
	}

	public JSONObject readResult() throws IOException {

		Queryes query = new Queryes();
		limit = query.limitCheck(limit);

		if (isMetaData==true) {
			metaDataContent = query.getMeteData(filePath);

		}else{
			metaDataContent = null;
		}

		List<String> q = query.findIdentity(filePath, textToSearch, length, limit);

		JSONObject obj = new JSONObject();
		obj.put("text", q);
		obj.put("limit", limit);
		obj.put("length", length);
		obj.put("Meta Data", metaDataContent);
		

		return obj;
	}

}
