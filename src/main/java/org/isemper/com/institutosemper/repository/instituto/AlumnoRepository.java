package org.isemper.com.institutosemper.repository.instituto;

import org.isemper.com.institutosemper.model.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, String> {

    Optional<Alumno> findByCodigo(String codigo);

}
