package com.malskyi.project.service.cloudinary;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {

	String uploadFile(MultipartFile file, int commodityId);
	
//	void uploadTempFile(MultipartFile file);
	
//	void deleteFile(String fileName, String folder);

	void deleteFile(String fileName);
	
//	void unsetTempFile();
}
