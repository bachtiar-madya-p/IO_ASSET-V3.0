/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import net.glxn.qrgen.javase.QRCode;

/**
 *
 * @author user
 */
class BarbecueBarcodeGenerator {
    
    public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
    ByteArrayOutputStream stream = QRCode
      .from(barcodeText)
      .withSize(250, 250)
      .stream();
    ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());
 
    return ImageIO.read(bis);
}
}
