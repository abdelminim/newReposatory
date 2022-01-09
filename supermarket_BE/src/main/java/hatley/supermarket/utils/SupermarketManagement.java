package hatley.supermarket.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class SupermarketManagement {
	
	public String updateImage(MultipartFile file, String fileImagePath) {

		try {

			String fileName = file.getOriginalFilename();
			String extention = fileName.substring(fileName.lastIndexOf("."));

			fileName = UUID.randomUUID().toString() + extention;

			String filePath = fileImagePath + "\\" + fileName;

			if (filePath != null && !filePath.equals("")) {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(fileImagePath + File.separator + fileName)));
				stream.write(bytes);
				stream.flush();
				stream.close();

				return fileName;
			}

		} catch (Exception ex) {

		}
		return null;

	}
	
public String getImageBase64(String iamgePathInDB, String fileImagePath) {
		String imageBase64 = null;
		try {
			
			String FileFullPath = fileImagePath + "\\" + iamgePathInDB;
			byte[] content = ITBFileUtility.getByteContentNetwFile(FileFullPath);
			
			imageBase64 = org.apache.commons.codec.binary.Base64.encodeBase64String(content);
		} catch(Exception ex) {
			
		}
		return imageBase64;
		
	}


}
