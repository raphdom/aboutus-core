package com.jrdevel.aboutus.core.cloud;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.log4j.Logger;

import com.jrdevel.aboutus.core.util.images.BufferedImageTranscoder;
import com.jrdevel.aboutus.core.util.images.ImageResizeAction;
import com.jrdevel.aboutus.core.util.images.ImageResizeRequest;
import com.jrdevel.aboutus.core.util.images.ImageResizeService;


/**
 * @author Raphael Domingues
 *
 */
public class ImageTransformHelper {

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

	public HashMap<ImageSizeEnum,byte[]> transformImages(InputStream stream, 
			String imageType, ImageSizeEnum... dataTypes){

		long start = System.currentTimeMillis();

		List<Thread> threads = new ArrayList<Thread>();

		BufferedImage bufferImage;

		bufferImage = loadImage(stream, imageType);
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

		return result;

	}

	public byte[] transformImage(InputStream stream, ImageSizeEnum imageSize, 
			boolean exactlySize, String imageType){

		return transformImage(stream, imageSize.getWidth(),imageSize.getHeight(), exactlySize, imageType);

	}

	public byte[] transformImage(InputStream stream, Integer width, Integer height, 
			boolean exactlySize,String imageType){

		BufferedImage bufferImage;

		byte[] resultData = null;

		bufferImage = loadImage(stream, imageType);

		resultData = resizeImage(bufferImage, width,height,exactlySize);

		return resultData;

	}


	public BufferedImage loadImage(InputStream stream, String imageType){

		BufferedImage bufferImage = null;

		if (imageType.contains("svg")){
			BufferedImageTranscoder imageTranscoder = new BufferedImageTranscoder();

			imageTranscoder.addTranscodingHint(PNGTranscoder.KEY_WIDTH, new Float(750));
			imageTranscoder.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, new Float(1050));

			TranscoderInput input = new TranscoderInput(stream);
			try {
				imageTranscoder.transcode(input, null);
			} catch (TranscoderException e) {
				logger.error(e.getMessage());
			}

			bufferImage = imageTranscoder.getBufferedImage();
		}else{
			try {
				bufferImage = ImageIO.read(stream);
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}

		return bufferImage;
	}


}
