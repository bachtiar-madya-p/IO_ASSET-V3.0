/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import id.io.asset.model.AssetModel;
import id.io.asset.util.database.AssetDatabaseHelper;
import id.io.asset.util.database.UUIDGeneratorHelper;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import javax.imageio.ImageIO;


/**
 *
 * @author user
 */
public class GenerateQRCode extends BaseController{
    
    private AssetDatabaseHelper assetDbHelper;
    private UUIDGeneratorHelper uuidGenerator;
    
    public GenerateQRCode() {
        assetDbHelper = new AssetDatabaseHelper();
        this.uuidGenerator = new UUIDGeneratorHelper();
    }
    
    //list
    public List<AssetModel> assetList() {
        List<AssetModel> assetList = assetDbHelper.getList();
        return assetList;
    }
    //findById
    public AssetModel getAsset(String assetId) {
        AssetModel asset = assetDbHelper.findById(assetId);
        return asset;
    }

	public static void main(String[] args) throws WriterException, IOException {
		String qrCodeText = "https://www.journaldev.com";
		String filePath = "JD.png";
		int size = 125;
		String fileType = "png";
		File qrFile = new File(filePath);
		createQRImage(qrFile, qrCodeText, size, fileType);
		System.out.println("DONE");
	}

	private static void createQRImage(File qrFile, String qrCodeText, int size, String fileType)
			throws WriterException, IOException {
		// Create the ByteMatrix for the QR-Code that encodes the given String
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
                
		BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// Paint and save the image using the ByteMatrix
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		ImageIO.write(image, fileType, qrFile);
	}

}
