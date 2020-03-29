package com.caceis.monitor.utils;

import java.io.File;
import java.util.Arrays;

public class FileUtils {

	public static boolean compare2FileArrays(File[] oldFilesSnapshot, File[] newFilesSnapshot) {

		if (oldFilesSnapshot == null && newFilesSnapshot == null) {
			return true;
		}
		if (oldFilesSnapshot != null && newFilesSnapshot == null) {
			return false;
		}
		if (oldFilesSnapshot == null && newFilesSnapshot != null) {
			return false;
		}

		if (oldFilesSnapshot.length != newFilesSnapshot.length) {
			return false;
		}
		Arrays.sort(oldFilesSnapshot, new SortByFilename());
		Arrays.sort(newFilesSnapshot, new SortByFilename());

		// le contenu maintenant
		for (int i = 0; i < newFilesSnapshot.length; i++) {
			if (newFilesSnapshot[i].getAbsolutePath() != oldFilesSnapshot[i].getAbsolutePath()
					|| newFilesSnapshot[i].length() != oldFilesSnapshot[i].length()
					|| newFilesSnapshot[i].lastModified() != oldFilesSnapshot[i].lastModified()) {
				return false;
			}
		}
		return false;
	}
}
