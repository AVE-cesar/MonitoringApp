package com.caceis.monitor.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.caceis.monitor.utils.CustomFilenameFilter;

@Service
public class TaskService {

	public void run() throws IOException {
		String folder = "/Users/avebertrand";

		File[] files = new File(folder).listFiles(new CustomFilenameFilter(null, -1));

		if (files.length == 0) {
			// rien à lire
			System.out.println("Input folder " + new File(folder).getCanonicalPath() + " is empty !");
		} else {
			// à lire
			System.out.println("Input folder " + new File(folder).getCanonicalPath() + " is NOT empty!");
		}

		for (File file : files) {
			System.out.println(file.getAbsolutePath());
		}
	}
}
