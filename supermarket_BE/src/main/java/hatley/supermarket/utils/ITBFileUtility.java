package hatley.supermarket.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

/**
 * A utility that downloads a file from a URL.
 * 
 * @author Mohamed Mohsen
 * 
 */
public class ITBFileUtility {
	private static final int BUFFER_SIZE = 8 * 1024;

	/**
	 * Downloads a file from a URL
	 * 
	 * @param fileURL
	 *            HTTP URL of the file to be downloaded
	 * @throws IOException
	 */
	public static byte[] getByteContentHttpFile(String fileURL) {
		byte[] buffer = null;
		try {

			URL url = new URL(fileURL);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			int responseCode = httpConn.getResponseCode();

			// always check HTTP response code first
			if (responseCode == HttpURLConnection.HTTP_OK) {
				String fileName = "";
				String disposition = httpConn.getHeaderField("Content-Disposition");
				String contentType = httpConn.getContentType();
				int contentLength = httpConn.getContentLength();

				if (disposition != null) {
					// extracts file name from header field
					int index = disposition.indexOf("filename=");
					if (index > 0) {
						fileName = disposition.substring(index + 10, disposition.length() - 1);
						System.out.println("file name from header field = " + fileName);
					}
				} else {
					// extracts file name from URL
					fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
					System.out.println("file name from URL = " + fileName);
				}

				System.out.println("Content-Type = " + contentType);
				System.out.println("Content-Disposition = " + disposition);
				System.out.println("Content-Length = " + contentLength);
				System.out.println("fileName = " + fileName);

				// opens input stream from the HTTP connection
				InputStream inputStream = httpConn.getInputStream();
				// String saveFilePath = saveDir + File.separator + fileName;

				// opens an output stream to save into file
				FileOutputStream outputStream = new FileOutputStream(fileName);

				int bytesRead = -1;
				buffer = new byte[BUFFER_SIZE];
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}

				outputStream.close();
				inputStream.close();

				// for (byte b : buffer) {
				// System.out.println(b);
				// }

				System.out.println("File downloaded");
			} else {
				System.out.println("No file to download. Server replied HTTP code: " + responseCode);
			}
			httpConn.disconnect();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return buffer;
	}

	public static byte[] getByteContentFile(String fileURL) throws IOException, ConnectException {
		URL link = new URL(fileURL); // The
										// file
		// download

		// Code to download
		InputStream in = new BufferedInputStream(link.openStream());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[BUFFER_SIZE];
		int n = 0;
		while (-1 != (n = in.read(buf))) {
			out.write(buf, 0, n);
		}
		out.close();
		in.close();
		byte[] response = out.toByteArray();

		System.out.println("Finished");
		// FileOutputStream fos = new FileOutputStream(fileName);
		// fos.write(response);
		// fos.close();
		// End download code
		return response;

	}

	/**
	 * Downloads a file from a URL
	 * 
	 * @param fileURL
	 *            HTTP URL of the file to be downloaded
	 * @param saveDir
	 *            path of the directory to save the file
	 * @throws IOException
	 */
	public static void downloadHttpFile(String fileURL, String saveDir) throws IOException {
		URL url = new URL(fileURL);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		int responseCode = httpConn.getResponseCode();

		// always check HTTP response code first
		if (responseCode == HttpURLConnection.HTTP_OK) {
			String fileName = "";
			String disposition = httpConn.getHeaderField("Content-Disposition");
			String contentType = httpConn.getContentType();
			int contentLength = httpConn.getContentLength();

			if (disposition != null) {
				// extracts file name from header field
				int index = disposition.indexOf("filename=");
				if (index > 0) {
					fileName = disposition.substring(index + 10, disposition.length() - 1);
					System.out.println("file name from header field = " + fileName);
				}
			} else {
				// extracts file name from URL
				fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
				System.out.println("file name from URL = " + fileName);
			}

			System.out.println("Content-Type = " + contentType);
			System.out.println("Content-Disposition = " + disposition);
			System.out.println("Content-Length = " + contentLength);
			System.out.println("fileName = " + fileName);

			// opens input stream from the HTTP connection
			InputStream inputStream = httpConn.getInputStream();
			String saveFilePath = saveDir + File.separator + "test.pdf";

			// opens an output stream to save into file
			FileOutputStream outputStream = new FileOutputStream(saveFilePath);

			int bytesRead = -1;
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			outputStream.close();
			inputStream.close();

			for (byte b : buffer) {
				System.out.println(b);
			}

			System.out.println("File downloaded");
		} else {
			System.out.println("No file to download. Server replied HTTP code: " + responseCode);
		}
		httpConn.disconnect();
	}

	private static void downloadNetwokFile(String saveDir, String fname) {
		File f = new File(fname);

		System.out.println(f.getName());
		// opens input stream from the HTTP connection
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(f);
			String saveFilePath = saveDir + File.separator + f.getName();

			// opens an output stream to save into file
			FileOutputStream outputStream;

			outputStream = new FileOutputStream(saveFilePath);

			int bytesRead = -1;
			byte[] buffer = new byte[BUFFER_SIZE];

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			outputStream.close();

			inputStream.close();

			for (byte b : buffer) {
				System.out.println(b);
			}

			// System.out.println("File downloaded");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static byte[] getByteContentNetwFile(String fileUrl) throws IOException {

		// Code to download
		InputStream in = new BufferedInputStream(new FileInputStream(new File(fileUrl)));
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[BUFFER_SIZE];
		int n = 0;
		while (-1 != (n = in.read(buf))) {
			out.write(buf, 0, n);
		}
		out.close();
		in.close();
		byte[] response = out.toByteArray();

		out.close();
		in.close();
		System.out.println("Finished");
		// FileOutputStream fos = new FileOutputStream(new
		// File(fileUrl).getName());
		// fos.write(response);
		// fos.close();
		// End download code
		// for (byte b : response) {
		// System.out.println(b);
		// }
		return response;

	}

	
	public static boolean deleteFileByFilePath(String filePath) {
		boolean status = false;
		
		File file = new File(filePath);
		try{
			status = file.delete();
		}
		catch(SecurityException e){
			e.printStackTrace();
			throw new SecurityException("SecurityException happened");
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				throw new Exception("Exception happened");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return status;
	}
	
	
	
	public static boolean deleteFolderWithSubDirByPath(String folderPath) {
		boolean status = false;
		
		File directory = new File(folderPath);
			//make sure directory exists
			if(!directory.exists()){

		       System.out.println("Directory does not exist.");

		    }else{

		       try{
		    	   
		           delete(directory);
		           status = true; 
		    	
		       }catch(IOException e){
		           e.printStackTrace();
		       }
		    }

		return status;
	}
	
	public static void delete(File file)
	    	throws IOException{
	 
	    	if(file.isDirectory()){
	 
	    		//directory is empty, then delete it
	    		if(file.list().length==0){
	    			
	    		   file.delete();
	    		   System.out.println("Directory is deleted : " 
	                                                 + file.getAbsolutePath());
	    			
	    		}else{
	    			
	    		   //list all the directory contents
	        	   String files[] = file.list();
	     
	        	   for (String temp : files) {
	        	      //construct the file structure
	        	      File fileDelete = new File(file, temp);
	        		 
	        	      //recursive delete
	        	     delete(fileDelete);
	        	   }
	        		
	        	   //check the directory again, if empty then delete it
	        	   if(file.list().length==0){
	           	     file.delete();
	        	     System.out.println("Directory is deleted : " 
	                                                  + file.getAbsolutePath());
	        	   }
	    		}
	    		
	    	}else{
	    		//if file, then delete it
	    		file.delete();
	    		System.out.println("File is deleted : " + file.getAbsolutePath());
	    	}
	    }
	
	public static boolean checkFolderDirPathFound(String folderPath) {
		boolean status = false;
		
		File directory = new File(folderPath);
			//make sure directory exists
			if(!directory.exists()){

		       System.out.println("Directory does not exist.");

		    }else{
		    	status = true; 
		    }

		return status;
	}

	public static void main(String[] args) {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
		int day = now.get(Calendar.DAY_OF_MONTH);
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE);
		int second = now.get(Calendar.SECOND);
		int millis = now.get(Calendar.MILLISECOND);

		System.out.printf("%d-%02d-%02d %02d:%02d:%02d.%03d", year, month, day, hour, minute, second, millis);

		// try {
		// ITBFileUtility.getByteContentNetwFile("C:\\Users\\ITBLOCKS\\Pictures\\test.tif");
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// String fileURL =
		// "http://10.10.11.135:9002/reports/rwservlet/getjobid34769?server=rptsvr_appsecourtn3_fmw31";
		// String saveDir = "E:/Download";
		// try {
		// ITBFileUtility.downloadHttpFile(fileURL, saveDir);
		// } catch (IOException ex) {
		// ex.printStackTrace();
		// }

		// HttpDownloadUtility.getByteContentHttpFile("http://www.naswdc.org/practice/naswstandards/supervisionstandards2013.pdf");
		// try {
		// HttpDownloadUtility.getByteContentFile("\\\\localhost\\D$\\personnel\\الرقية الشرعية لالاطفال.txt");
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// String saveDir = "E:/Download";
		// String fname =
		// "\\\\localhost\\D$\\personnel\\الرقية الشرعية لالاطفال.txt";
		// String fileURL =
		// "\\\\10.10.12.104\\bog_itb_archive_shared_folder\\FILE_0000004975.PDF";
		// String fileName = fileURL.substring(fileURL.lastIndexOf("\\") + 1,
		// fileURL.length());
		// System.out.println("file name from Network  = " + fileName);
		// downloadNetwokFile(saveDir, fname);

		// try {
		// getByteContentNetwFile(fname);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

}