package com.example.Apifestivos.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Apifestivos.Entity.Festivo;
import com.example.Apifestivos.Service.FestivoService;

@RestController
@RequestMapping("/api/festivos")
public class FestivoController {
    @Autowired
    private FestivoService festivoService;

    @GetMapping("/es-festivo")
    public boolean esFestivo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return festivoService.esFestivo(fecha);
    }

    @GetMapping("/lista")
    public List<Festivo> obtenerFestivos(@RequestParam int año) {
        return festivoService.obtenerFestivos(año);
    }
}
