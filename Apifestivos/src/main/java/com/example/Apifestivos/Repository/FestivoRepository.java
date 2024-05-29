package com.example.Apifestivos.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Apifestivos.Entity.Festivo;

public interface FestivoRepository extends JpaRepository<Festivo, Integer> {
    List<Festivo> findByDiaAndMes(int dia, int mes);

    List<Festivo> findByMes(int mes);
}
