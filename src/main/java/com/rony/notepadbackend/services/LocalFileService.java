package com.rony.notepadbackend.services;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

@Profile ({"dev"})
@Service
public class LocalFileService implements FileService{

    @Override
    public String writeToDirectory(MultipartFile multipartFile) {
        try {
            var file = File.createTempFile( multipartFile.getName (), multipartFile.getOriginalFilename () );
            multipartFile.transferTo(file);
            Image img = ImageIO.read(file);

            var path = System.getProperty ("user.home") + "/" + "notepad-service" + "/" + file.getName ();
            writeImageToFile(img, path);
            return path;
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return null;
    }

    private void writeImageToFile (Image image, String path) throws IOException {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        ImageIO.write(toBufferedImage (image), "jpg", new File (path));
    }

    private BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = (Graphics2D) bimage.getGraphics();
        bGr.rotate(Math.toRadians(0), bimage.getWidth() / 2, bimage.getHeight() / 2);
        bGr.drawImage(img, 0, 0, null);

        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    @Override
    public byte[] readFromPath(String path) {
        System.out.println(path);
        try {
            var in = new FileInputStream (path);
            return IOUtils.toByteArray(in);
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return null;
    }
}