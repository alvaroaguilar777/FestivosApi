package com.example.Apifestivos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Apifestivos.Entity.Tipo;

public interface TipoRepository extends JpaRepository<Tipo, Integer> {
}
