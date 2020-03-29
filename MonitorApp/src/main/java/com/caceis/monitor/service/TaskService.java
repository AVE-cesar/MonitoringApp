package com.caceis.monitor.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

import com.caceis.monitor.utils.CustomFilenameFilter;
import com.caceis.monitor.utils.FileUtils;

@Service
public class TaskService {

	File[] oldFilesSnapshot = null;

	public boolean run(String folder) throws IOException {
		boolean result = true;

		if (folder == null) {
			return false;
		}

		if (!new File(folder).exists()) {
			System.out.println("Le Folder " + folder + " n'exite pas!");
			return false;
		}

		File[] files = new File(folder).listFiles(new CustomFilenameFilter(".java", -1));

		if (files != null) {
			if (files.length == 0) {
				// rien à lire
				System.out.println("Input folder " + new File(folder).getCanonicalPath() + " is empty !");
			} else {
				// à lire
				System.out.println("Input folder " + new File(folder).getCanonicalPath() + " is NOT empty!");
			}

			boolean identical = FileUtils.compare2FileArrays(oldFilesSnapshot, files);
			oldFilesSnapshot = files;

			if (!identical) {
				for (File file : files) {
					if (file.isFile()) {
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
						double bytes = file.length();
						double kilobytes = (bytes / 1024);
						double megabytes = (kilobytes / 1024);
						System.out.println(
								file.getAbsolutePath() + " " + kilobytes + " Kb " + sdf.format(file.lastModified()));
					}
				}
			}
		} else {
			oldFilesSnapshot = null;
		}

		return true;
	}
}