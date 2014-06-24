package com.jrdevel.aboutus.core.cloud;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.jrdevel.aboutus.core.util.images.ImageResizeAction;
import com.jrdevel.aboutus.core.util.images.ImageResizeRequest;
import com.jrdevel.aboutus.core.util.images.ImageResizeService;


/**
 * @author Raphael Domingues
 *
 */
public class ImageTransformHelper {

	/*public static final ImageSize DATA_TYPE_1  = new ImageSize(50,50,1);
	public static final ImageSize DATA_TYPE_2  = new ImageSize(80,80,2);
	public static final ImageSize DATA_TYPE_3  = new ImageSize(100,100,3);
	public static final ImageSize DATA_TYPE_4  = new ImageSize(150,150,4);
	public static final ImageSize DATA_TYPE_5  = new ImageSize(280,200,5);
	public static final ImageSize DATA_TYPE_6  = new ImageSize(300,300,6);
	public static final ImageSize DATA_TYPE_7  = new ImageSize(400,400,7);
	public static final ImageSize DATA_TYPE_8  = new ImageSize(500,500,8);
	public static final ImageSize DATA_TYPE_9  = new ImageSize(720,480,9);
	public static final ImageSize DATA_TYPE_10 = new ImageSize(1920,1080,10);*/
	
	private static final Logger logger = Logger.getLogger(ImageTransformHelper.class);
	
	private HashMap<ImageSizeEnum,byte[]> result = new HashMap<ImageSizeEnum,byte[]>();
	
	private byte[] resizeImage(BufferedImage bufferImage, int width, int height, boolean exactlySize){
		ImageResizeRequest request;
		ImageResizeService handler = new ImageResizeService();

		request = new ImageResizeRequest();

		try {
			request.setSourceImage(bufferImage);
			request.setTargetWidth(width);
			request.setTargetHeight(height);
			request.setResizeAction(ImageResizeAction.IF_LARGER);
			request.setCropToAspect(exactlySize);
			handler.resize(request);
			return request.getDestinationByteArrayOutputStream().toByteArray();
		}catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	private class ScaleImage extends Thread {

		BufferedImage bufferImage;
		ImageSizeEnum imageSize;
		boolean exactlySize;
		byte[] resultData;

		public ScaleImage(BufferedImage bufferImage, ImageSizeEnum dataType, boolean exactlySize){
			this.bufferImage=bufferImage;
			this.imageSize=dataType;
			this.exactlySize=exactlySize;
		}

		public void run() {

			try {

				resultData = resizeImage(bufferImage, imageSize.getWidth(), 
						imageSize.getHeight(),exactlySize);
				
				result.put(imageSize, resultData);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
	
	public HashMap<ImageSizeEnum,byte[]> transformImages(InputStream stream, ImageSizeEnum... dataTypes){

		long start = System.currentTimeMillis();

		List<Thread> threads = new ArrayList<Thread>();

		BufferedImage bufferImage;
		try {
			
			bufferImage = ImageIO.read(stream);
			for (ImageSizeEnum dataType : dataTypes){
				Thread thread0 = new Thread(new ScaleImage(bufferImage, dataType, true));
				thread0.start();
				threads.add(thread0);
			}

			int running = 0;
			do {
				running = 0;
				for (Thread thread : threads) {
					if (thread.isAlive()) {
						running++;
					}
				}
			} while (running > 0);

			long end = System.currentTimeMillis();
			logger.info("Demorou " + ((end - start) / 1000) + " segundos");
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		return result;

	}
	
	public byte[] transformImage(InputStream stream, ImageSizeEnum imageSize, boolean exactlySize){
		
		BufferedImage bufferImage;
		
		byte[] resultData = null;
		
		try {
			bufferImage = ImageIO.read(stream);
			resultData = resizeImage(bufferImage, imageSize.getWidth(), 
					imageSize.getHeight(),exactlySize);
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		return resultData;
		
	}
	
	public byte[] transformImage(InputStream stream, Integer width, Integer height, boolean exactlySize){
		
		BufferedImage bufferImage;
		
		byte[] resultData = null;
		
		try {
			bufferImage = ImageIO.read(stream);
			resultData = resizeImage(bufferImage, width,height,exactlySize);
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		return resultData;
		
	}
	

}
