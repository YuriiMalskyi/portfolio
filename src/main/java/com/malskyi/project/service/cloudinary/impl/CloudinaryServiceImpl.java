package com.malskyi.project.service.cloudinary.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.cloudinary.utils.ObjectUtils;
import com.malskyi.project.service.cloudinary.CloudinaryService;

@Service
@PropertySource("classpath:cloudinary.properties")
public class CloudinaryServiceImpl implements CloudinaryService{

	private Cloudinary cloudinary;
	
	public CloudinaryServiceImpl(Environment env) {
		this.cloudinary = new Cloudinary(env.getProperty("cloudinary.env"));
	}
	
	@Override
	public String uploadFile(MultipartFile file, int commodityId) {
//	public String uploadFile(MultipartFile file) {
		Uploader uploader = cloudinary.uploader();
		
//		String originalName = file.getOriginalFilename();
//		String fileName = originalName.substring(0, originalName.lastIndexOf("."));
		
		Map<?, ?> options = ObjectUtils.asMap(
				"public_id", "image-"+commodityId,
//				"public_id", "image-"+commodityId.toString(),
//				"folder", folder,
				"overwrite", true
				);
		
		Map<?, ?> result = null;
		
		try {
			InputStream inputStream = file.getInputStream();
			result = uploader.uploadLarge(inputStream, options);
			inputStream.close();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return (String) result.get("url");
	}
	
//	@Override
//	public void uploadTempFile(MultipartFile file) {
//		Uploader uploader = cloudinary.uploader();
//				
//		Map<?, ?> options = ObjectUtils.asMap(
//				"public_id", "image-temp",
//				"folder", "temp",
//				"overwrite", true
//				);
//				
//		try {
//			InputStream inputStream = file.getInputStream();
//			uploader.uploadLarge(inputStream, options);
//			inputStream.close();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	@Override
//	public void deleteFile(String fileName, String folder) {
//			Uploader uploader = cloudinary.uploader();
//			try {
//				uploader.destroy("image-temp", ObjectUtils.emptyMap());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//	}

	@Override
	public void deleteFile(String fileName) {
			Uploader uploader = cloudinary.uploader();
			try {
				uploader.destroy(fileName, ObjectUtils.emptyMap());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	}
}





