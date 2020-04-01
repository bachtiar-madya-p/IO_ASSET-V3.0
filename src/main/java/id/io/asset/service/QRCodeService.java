/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.service;

import id.io.asset.controller.GenerateQRCode;
import java.awt.image.BufferedImage;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author user
 */
@Path("barcode")
@Produces(MediaType.APPLICATION_JSON)
public class QRCodeService extends BaseService {

    private GenerateQRCode generateQRCode;

    public QRCodeService() {
        this.generateQRCode = new GenerateQRCode();
    }

    @GET
    public Response assetList() {
        return Response.ok(generateQRCode.assetList()).build();
    }

    @GET
    @Path("/{assetId}")
    public Response assetById(@PathParam("assetId") String assetId) {
        return Response.ok(generateQRCode.getAsset(assetId)).build();
    }
    
    @GET
    public Response.ResponseBuilder barbecueEAN13Barcode(@PathVariable("barcode") String barcode)
    throws Exception {
        return Response.ok(BarbecueBarcodeGenerator.generateQRCodeImage(barcode));
    }
    
    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }

    
}
