package ec3.idat.ec3.controller;


import ec3.idat.ec3.model.VendedorModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VendedorController {

    @GetMapping("/comision")
    public String formularioComision(Model model) {
        model.addAttribute("vendedorModel", new VendedorModel());
        return "comision";
    }

    @PostMapping("/calcularcomision")
    public String calcularComision(@ModelAttribute("vendedorModel") VendedorModel vendedor, Model model) {
        Double ventas = vendedor.getVentasMensuales();
        Double comision;

        if (ventas < 1000) comision = ventas * 0.03;
        else if (ventas < 5000) comision = ventas * 0.05;
        else if (ventas < 10000) comision = ventas * 0.07;
        else comision = ventas * 0.10;

        model.addAttribute("resultado", "La comisión es: S/" + comision);
        return "comision";
    }
}
