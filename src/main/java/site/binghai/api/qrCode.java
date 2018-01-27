package site.binghai.api;

import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import net.glxn.qrgen.core.AbstractQRCode;
import net.glxn.qrgen.core.exception.QRGenerationException;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by binghai on 2018/1/27.
 *
 * @ api_debugger
 */
@RequestMapping("/")
@Controller
public class qrCode {

    @RequestMapping("qrCode")
    public void makeQrCode(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {

        String qrtext = request.getParameter("t");
        int w = 300;

        try {
            w = Integer.parseInt(request.getParameter("w"));
        } catch (Exception e) {
        }

        ByteArrayOutputStream out = new QrMaker(qrtext, w, w).to(ImageType.PNG).stream();
        response.setContentType("image/png");
        response.setContentLength(out.size());

        OutputStream outStream = response.getOutputStream();

        outStream.write(out.toByteArray());

        outStream.flush();
        outStream.close();
    }
}

class QrMaker extends AbstractQRCode {
    private String text;

    public QrMaker(String text, int w, int h) {
        this.text = text;
        qrWriter = new QRCodeWriter();
        height = h;
        width = w;
    }

    @Override
    public File file() {
        File file;
        try {
            file = createTempFile();
            MatrixToImageWriter.writeToPath(createMatrix(text), imageType.toString(), file.toPath());
        } catch (Exception e) {
            throw new QRGenerationException("Failed to create QR image from text due to underlying exception", e);
        }

        return file;
    }

    @Override
    public File file(String name) {
        File file;
        try {
            file = createTempFile(name);
            MatrixToImageWriter.writeToPath(createMatrix(text), imageType.toString(), file.toPath());
        } catch (Exception e) {
            throw new QRGenerationException("Failed to create QR image from text due to underlying exception", e);
        }

        return file;
    }

    @Override
    protected void writeToStream(OutputStream stream) throws IOException, WriterException {
        MatrixToImageWriter.writeToStream(createMatrix(text), imageType.toString(), stream);
    }
}
