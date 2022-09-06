package com.example.demo.controller;


import com.example.demo.model.Productos;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Controller
public class SubirFichero {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload-csv-file")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {

        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // create csv bean reader
                CsvToBean<Productos> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Productos.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .withIgnoreQuotations(true)
                        .withThrowExceptions(false) //1
                        .withSeparator(';')
                        .build();

                // convert `CsvToBean` object to list of users
                List<Productos> productos = csvToBean.parse();
                List<Productos> productosDespues = productos;




                // TODO: save users in DB?
             //   productosDespues.clear();

                String descripcion = "";
                String formato = "";
                String codigo = "";
                for (int i=0; i < productos.size(); i++) {
                    descripcion = productos.get(i).descripcion;
                    formato = productos.get(i).formato;
                    codigo = productos.get(i).codigo;
                    formato = comprueba(descripcion);
                   // checkformato
                //    productosDespues.add(new Productos(codigo, descripcion, formato));
                    productos.set(i,new Productos(codigo, descripcion, formato) );

                }

                // save users list on model
                model.addAttribute("productos", productos);
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }
     /*   Boolean status;
        status = true;*/
        return "file-upload-status";
    }
    public String comprueba(String checkformato){
        String substring = " gramos";
        if(checkformato.contains(substring)){
            return "Polvo";
        }
        substring = "250 G";
        if(checkformato.contains(substring)){
            return "Polvo";
        }
        substring = "150 G";
        if(checkformato.contains(substring)){
            return "Polvo";
        }
        substring = "1 kg";
        if(checkformato.contains(substring)){
            return "Polvo";
        }
        substring = "150 g";
        if(checkformato.contains(substring)){
            return "Polvo";
        }
        substring = "180 g";
        if(checkformato.contains(substring)){
            return "Polvo";
        }
        substring = "500 G";
        if(checkformato.contains(substring)){
            return "Polvo";
        }
        substring = "250 g";
        if(checkformato.contains(substring)){
            return "Polvo";
        }
        substring = "500 g";
        if(checkformato.contains(substring)){
            return "Polvo";
        }
        substring = "250 comp";
        if(checkformato.contains(substring)){
            return "Comprimido";
        }
        substring = "75 ml";
        if(checkformato.contains(substring)){
            return "Liquido";
        }
        substring = "Vcaps";
        if(checkformato.contains(substring)){
            return "Capsula";
        }
        substring = "vcaps";
        if(checkformato.contains(substring)){
            return "Capsula";
        }
        substring = "ml";
        if(checkformato.contains(substring)){
            return "Liquido";
        }
        substring = "1 L";
        if(checkformato.contains(substring)){
            return "Liquido";
        }
        substring = "Perlas";
        if(checkformato.contains(substring)){
            return "Perla";
        }
        substring = "Comp";
        if(checkformato.contains(substring)){
            return "Comprimido";
        }
        substring = "Sobres";
        if(checkformato.contains(substring)){
            return "Stick";
        }
        substring = "Stick";
        if(checkformato.contains(substring)){
            return "Stick";
        }
        substring = "CAPSULAS";
        if(checkformato.contains(substring)){
            return "Capsula";
        }
        substring = "Caps";
        if(checkformato.contains(substring)){
            return "Capsula";
        }
        substring = "VCaps";
        if(checkformato.contains(substring)){
            return "Capsula";
        }
        return "";

    }
}
