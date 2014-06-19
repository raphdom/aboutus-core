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

	public static final ImageSize DATA_TYPE_SMALL_0 = new ImageSize(50,40,0);
	public static final ImageSize DATA_TYPE_SMALL_1 = new ImageSize(80,60,1);
	public static final ImageSize DATA_TYPE_SMALL_2 = new ImageSize(150,120,2);
	public static final ImageSize DATA_TYPE_MEDIUM_1 = new ImageSize(600,480,3);
	public static final ImageSize DATA_TYPE_MEDIUM_2 = new ImageSize(1050,800,4);
	public static final ImageSize DATA_TYPE_LARGE_1 = new ImageSize(1650,1200,5);
	public static final ImageSize DATA_TYPE_LARGE_2 = new ImageSize(1920,1080,6);
	
	private static final Logger logger = Logger.getLogger(ImageTransformHelper.class);
	
	private HashMap<ImageSize,byte[]> result = new HashMap<ImageSize,byte[]>();
	
	private byte[] resizeImage(BufferedImage bufferImage, int width, int height, boolean exactlySize){
		ImageResizeRequest request;
		ImageResizeService handler = new ImageResizeService();

		request = new ImageResizeRequest();

		try {
			request.setSourceImage(bufferImage);
			request.setTargetWidth(width);
			request.setTargetHeight(height);
			request.setResizeAction(ImageResizeAction.ALWAYS);
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
		ImageSize imageSize;
		boolean exactlySize;
		byte[] resultData;

		public ScaleImage(BufferedImage bufferImage, ImageSize dataType, boolean exactlySize){
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
	
	public HashMap<ImageSize,byte[]> transformImages(InputStream stream, ImageSize... dataTypes){

		long start = System.currentTimeMillis();

		List<Thread> threads = new ArrayList<Thread>();

		BufferedImage bufferImage;
		try {
			
			bufferImage = ImageIO.read(stream);
			for (ImageSize dataType : dataTypes){
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
	

}
