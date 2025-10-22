package com.ptit.ticketing.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.nio.file.*;

public class QRService {
    public void generate(String text, String filePath) {
        try {
            BitMatrix matrix = new MultiFormatWriter()
                    .encode(text, BarcodeFormat.QR_CODE, 200, 200);
            Path path = Paths.get(filePath);
            Files.createDirectories(path.getParent());
            MatrixToImageWriter.writeToPath(matrix, "PNG", path);
        } catch (Exception e) {
            throw new RuntimeException("QR generation failed", e);
        }
    }
}
