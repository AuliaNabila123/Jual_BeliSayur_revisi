/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toko.sayuran;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author LENOVO
 */

@Controller
public class ProjectController {

    
    @RequestMapping("/input")
    public String getData(HttpServletRequest data, Model kurir) {
        
        String nama_sayur = data.getParameter("namasayur");
        String harga_perkilo = data.getParameter("hargaperkilo");
        String jumlah_beli = data.getParameter("jumlahbeli");
        String uang_bayar = data.getParameter("uangbayar");
        
        Double hargaSayur = Double.valueOf(harga_perkilo);
        Double JumlahBeli = Double.valueOf(jumlah_beli);
        Double JumlahBayar = hargaSayur * JumlahBeli;
        Double totalBayar = null;
        Double uang = Double.valueOf(uang_bayar);
        
        String discount = "";
        
        if (JumlahBayar < 16000)
        {
            totalBayar = JumlahBayar - (JumlahBayar * 0/100);
            discount = "0%";
        }
        else if (JumlahBayar >= 16000 && JumlahBayar < 25000)
        {
            totalBayar = JumlahBayar - (JumlahBayar * 10/100);
            discount = "10%";
        }
        else if (JumlahBayar >= 25000)
        {
            totalBayar = JumlahBayar - (JumlahBayar * 15/100);
            discount = "15%";
        }
        
        String Massage = null;
        Double sisa = uang - totalBayar;
        
        if (uang < totalBayar)
        {
            Massage = "UANG ANDA KURANG " + sisa;
        }
        else if (Objects.equals(uang, totalBayar))
        {
            Massage = "UANG ANDA PAS ";
        }
        else if (uang > totalBayar)
        {
            Massage = "KEMBALIAN ANDA " + (uang - totalBayar);
        }
        
        kurir.addAttribute("nama", nama_sayur);
        kurir.addAttribute("harga", harga_perkilo);
        kurir.addAttribute("Jmlbeli", jumlah_beli);
        kurir.addAttribute("Jmlbayar", JumlahBayar);
        kurir.addAttribute("disc", discount);
        kurir.addAttribute("total", totalBayar);
        kurir.addAttribute("money", Massage);
        kurir.addAttribute("uang", uang_bayar);
        kurir.addAttribute("kembalian", sisa);
        
        return "view";
    }
}

