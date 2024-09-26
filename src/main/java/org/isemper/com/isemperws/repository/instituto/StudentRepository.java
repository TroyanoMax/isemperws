package org.isemper.com.isemperws.repository.instituto;

import org.isemper.com.isemperws.model.entity.Student;
import org.isemper.com.isemperws.model.projection.StudentDataProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    Optional<Student> findByCodigo(String codigo);

    @Query(
            value = """
                    SELECT
                        a.CODIGO AS codigo,
                        a.APELLIDO AS apellido,
                        a.NOMBRE AS nombre,
                        a.N_DOC AS nDoc,
                        a.SEXO AS sexo,
                        a.EMAIL AS email,
                        a.DOMICILIO AS domicilio,
                        a.PROVINCIA AS provincia,
                        a.PAIS AS pais,
                        a.LOCALIDAD AS localidad,
                        a.C_POSTAL AS cPostal,
                        a.C_TEL AS cTel,
                        c.CODIGO AS aluCarCod,
                        c.NOMBRE AS aluCarNom
                    FROM
                        x031vm05_instituto.alumno a
                    JOIN
                        x031vm05_instituto.alu_car ac
                            ON a.CODIGO = ac.COD_ALU
                    JOIN
                        x031vm05_instituto.carrera c 
                            ON ac.COD_CAR = c.CODIGO
                    LEFT JOIN
                        (SELECT codigo
                         FROM x031vm05_instituto.alu_sac
                         WHERE concepto IN ('INSCRIPTO', 'REINSCRIPTO')
                         AND AÑO BETWEEN (YEAR(CURRENT_DATE)-1) 
                         AND YEAR(CURRENT_DATE)) as sac
                    ON ac.codigo = sac.codigo
                    LEFT JOIN
                        (SELECT codigo
                         FROM x031vm05_instituto.alu_mov
                         WHERE concepto IN ('LICENCIA', 'BAJA')
                         AND hasta >= CURRENT_DATE) as mov1
                    ON ac.codigo = mov1.codigo
                    LEFT JOIN
                        (SELECT codigo
                         FROM x031vm05_instituto.alu_mov
                         WHERE concepto = 'PROMOCIONADO') as mov2
                    ON ac.codigo = mov2.codigo
                    WHERE
                        ac.LIBRETA = :codAlu
                        AND sac.codigo IS NOT NULL
                        AND mov1.codigo IS NULL
                        AND mov2.codigo IS NULL
                    """,
            nativeQuery = true
    )
    Optional<StudentDataProjection> findAlumnosByCodAluNative(@Param("codAlu") String codAlu);

}
