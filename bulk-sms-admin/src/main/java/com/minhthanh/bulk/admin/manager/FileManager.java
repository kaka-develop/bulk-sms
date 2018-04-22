package com.minhthanh.bulk.admin.manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {

	private final static String AVATAR_FILE_PATH = "src/main/webapp/resources/img/";

	public static boolean storeFile(MultipartFile file) {
		File convFile = new File(AVATAR_FILE_PATH + file.getOriginalFilename());
		try {
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
