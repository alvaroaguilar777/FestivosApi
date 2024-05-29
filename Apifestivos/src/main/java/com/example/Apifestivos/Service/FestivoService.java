package com.example.Apifestivos.Service;

import com.example.Apifestivos.Entity.Festivo;
import com.example.Apifestivos.Repository.FestivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FestivoService {

    @Autowired
    private FestivoRepository festivoRepository;

    /**
     * Método para obtener la lista de festivos para un año dado.
     * 
     * @param año El año para el cual se desean obtener los festivos.
     * @return Una lista de festivos para el año especificado.
     */
    public List<Festivo> obtenerFestivos(int año) {
        LocalDate pascua = calcularPascua(año);
        List<Festivo> festivos = new ArrayList<>();

        // Obtener todos los festivos de la base de datos
        List<Festivo> festivosDb = festivoRepository.findAll();

        for (Festivo festivo : festivosDb) {
            LocalDate fechaFestivo;

            if (festivo.getIdTipo() == 3 || festivo.getIdTipo() == 4) { // Basado en Pascua
                fechaFestivo = pascua.plusDays(festivo.getDiasPascua());
            } else { // Festivos fijos y de Ley Puente Festivo
                fechaFestivo = LocalDate.of(año, festivo.getMes(), festivo.getDia());

                // Ajustar festivos de Ley Puente Festivo al lunes más cercano
                if (festivo.getIdTipo() == 2) {
                    switch (fechaFestivo.getDayOfWeek()) {
                        case SATURDAY:
                            fechaFestivo = fechaFestivo.plusDays(2);
                            break;
                        case SUNDAY:
                            fechaFestivo = fechaFestivo.plusDays(1);
                            break;
                    }
                }
            }

            festivos.add(new Festivo(festivo.getNombre(), fechaFestivo.getDayOfMonth(), fechaFestivo.getMonthValue(),
                    festivo.getIdTipo(), festivo.getDiasPascua()));
        }

        return festivos;
    }

    /**
     * Método para verificar si una fecha dada es un festivo.
     * 
     * @param fecha La fecha que se desea verificar.
     * @return True si la fecha es festivo, false en caso contrario.
     */
    public boolean esFestivo(LocalDate fecha) {
        int año = fecha.getYear();
        List<Festivo> festivos = obtenerFestivos(año);
        return festivos.stream()
                .anyMatch(f -> f.getDia() == fecha.getDayOfMonth() && f.getMes().getValue() == fecha.getMonthValue());
    }

    /**
     * Método para calcular la fecha de Pascua para un año dado.
     * 
     * @param año El año para el cual se desea calcular la fecha de Pascua.
     * @return La fecha de Pascua para el año especificado.
     */
    private LocalDate calcularPascua(int año) {
        int a = año % 19;
        int b = año / 100;
        int c = año % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * l) / 451;
        int mes = (h + l - 7 * m + 114) / 31;
        int día = ((h + l - 7 * m + 114) % 31) + 1;

        return LocalDate.of(año, mes, día);
    }
}
