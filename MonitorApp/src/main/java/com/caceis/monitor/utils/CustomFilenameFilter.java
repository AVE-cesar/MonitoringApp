package com.caceis.monitor.utils;

import java.io.File;
import java.io.FilenameFilter;

public class CustomFilenameFilter implements FilenameFilter {

	private String allowedExtension;
	private int nbFiles;
	private int count;

	public CustomFilenameFilter(String allowedExtension, int nbFiles) {
		this.allowedExtension = allowedExtension;
		this.nbFiles = nbFiles;
		this.count = 0;
	}

	@Override
	public boolean accept(File dir, String name) {
		boolean result = true;

		if (allowedExtension != null) {
			result = name.toLowerCase().endsWith(allowedExtension.toLowerCase());
		}

		if (result) {
			count++;
		}

		return result;
	}
}
