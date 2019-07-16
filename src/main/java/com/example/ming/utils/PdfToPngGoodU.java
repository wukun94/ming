package com.example.ming.utils;


import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class PdfToPngGoodU {
    public static void pdftopng(String name) {

        String filePath = "/upload" + name;
        Document document = new Document();
        document.setFile(filePath);
        float scale = 2.5f;
        // 缩放比例
        float rotation = 0f;// 旋转角度
        System.out.println("正在转换...");
        File dirFile = new File("/upload/" + name);

        if (!dirFile.exists()) {
            dirFile.mkdir();
        } else {
            System.out.println("目录己存在");
            return;
        }
        for (int i = 0; i < document.getNumberOfPages(); i++) {
            BufferedImage image = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN, org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
            RenderedImage rendImage = image;
            try {
                File file = new File("/download" + name + "/" + name + "_" + (i + 1) + ".png");
                ImageIO.write(rendImage, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.flush();
        } document.dispose();
        System.out.println("finish");
    }
}
