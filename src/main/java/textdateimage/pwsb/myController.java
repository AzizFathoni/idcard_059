/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textdateimage.pwsb;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Lenovo
 */

@Controller
public class myController {
    @RequestMapping("/KTP")
    public String getdata (@RequestParam("inputnama") String nama,
                          @RequestParam("inputdate")@DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                          @RequestParam("inputfile") MultipartFile gambar,
                          Model model) throws IOException{
        
        // for image
        String blob = Base64.encodeBase64String(gambar.getBytes());
        String shImage = "data:image/jpeg;base64,".concat(blob);
        
        // for date
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        String tanggal = sdf.format(date);
        
        // for thymleaf
        model.addAttribute("nm", nama);
        model.addAttribute("img", shImage);
        model.addAttribute("tgl", tanggal);
        
        return "ktp";
    }
}