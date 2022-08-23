package com.surjomukhi.taskmanagement.service;

import com.surjomukhi.taskmanagement.utils.CustomCodeGenerator;
import com.surjomukhi.taskmanagement.utils.ImagePath;
import lombok.extern.slf4j.Slf4j;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Transactional
@Slf4j
public class MediaService {

	@Autowired
	ServletContext servletContext;

	@Autowired
	private ImagePath imagePath;
	@Autowired
	private CustomCodeGenerator customCodeGenerator;
	
	public String saveFile(MultipartFile file) {
		try {
			String fileName = customCodeGenerator.getGeneratedUuid() + "." +  StringUtils.getFilenameExtension(file.getOriginalFilename());
			BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
			BufferedImage outputImage = Scalr.resize(bufferedImage, 400, 400);
			Path path = Paths.get(imagePath.getBasePath(), fileName).toAbsolutePath().normalize();
			File newImageFile = path.toFile();
			String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());

			if (fileExtension != null) {
				ImageIO.write(outputImage, fileExtension, newImageFile);
			}
			outputImage.flush();
			return fileName;
		} catch (Exception e) {
		return "No file Selected";
			// TODO: handle exception
		}
	}

}