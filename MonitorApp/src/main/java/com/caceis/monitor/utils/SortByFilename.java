package com.caceis.monitor.utils;

import java.io.File;
import java.util.Comparator;

public class SortByFilename implements Comparator<File> {
	// Used for sorting in ascending order of filename
	public int compare(File a, File b) {
		return a.getAbsolutePath().compareTo(b.getAbsolutePath());
	}
}
