package com.edufound.test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class GenerateQRCode {

		public static void main(String[] args) throws WriterException,
				FileNotFoundException, IOException {
			String url = "http://www.edufound.com.cn";
			int width, height;
			width = height = 350;
			Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			BitMatrix matrix = new MultiFormatWriter().encode(url,
					BarcodeFormat.QR_CODE, width, height, hints);
			File imgFile = new File("i:\\qrcode.jpg");
			MatrixToImageWriter.writeToStream(matrix, "jpg", new FileOutputStream(
					imgFile));
			System.out.println("done..");
		}
	}

