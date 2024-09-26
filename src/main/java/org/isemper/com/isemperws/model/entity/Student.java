package org.isemper.com.isemperws.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@Table(name = "alumno", schema = "x031vm05_instituto")
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO", length = 8)
    private String codigo;

    @Column(name = "N_DOC", length = 8)
    private String nDoc;

    @Column(name = "T_DOC", length = 2)
    private String tDoc;

    @Column(name = "APELLIDO", length = 25)
    private String apellido;

    @Column(name = "NOMBRE", length = 50)
    private String nombre;

    @Column(name = "DOMICILIO", length = 40)
    private String domicilio;

    @Column(name = "F_INGRESO")
    private Date fIngreso;

    @Column(name = "PROVINCIA", length = 30)
    private String provincia;

    @Column(name = "LOCALIDAD", length = 30)
    private String localidad;

    @Column(name = "F_NAC")
    private Date fNac;

    @Column(name = "L_NAC", length = 30)
    private String lNac;

    @Column(name = "P_NAC", length = 30)
    private String pNac;

    @Column(name = "N_TEL", length = 40)
    private String nTel;

    @Column(name = "C_TEL", length = 5)
    private String cTel;

    @Column(name = "EDAD", length = 11)
    private String edad;

    @Column(name = "E_CIVIL", length = 2)
    private String eCivil;

    @Column(name = "NACION", length = 15)
    private String nacion;

    @Column(name = "SEXO", length = 1)
    private Character sexo;

    @Column(name = "C_POSTAL", length = 11)
    private String cPostal;

    @Column(name = "EMAIL", length = 150)
    private String email;

    @Column(name = "OBSERVA", columnDefinition = "longtext")
    private String observa;

    @Column(name = "FOTO", columnDefinition = "longtext")
    private String foto;

    @Column(name = "FOTO_FILE", length = 50)
    private String fotoFile;

    @Column(name = "FECHA")
    private Date fecha;

    @Column(name = "SANCION", length = 4)
    private String sancion;

    @Column(name = "PA_NAC", length = 30)
    private String paNac;

    @Column(name = "PAIS", length = 30)
    private String pais;

    @Column(name = "SEC_NOM", length = 30)
    private String secNom;

    @Column(name = "SEC_LUG", length = 30)
    private String secLug;

    @Column(name = "SEC_TIT", length = 30)
    private String secTit;

    @Column(name = "SEC_AÑO", length = 11)
    private String secAno;

    @Column(name = "UNI_CAR", length = 30)
    private String uniCar;

    @Column(name = "UNI_NOM", length = 30)
    private String uniNom;

    @Column(name = "UNI_LUG", length = 30)
    private String uniLug;

    @Column(name = "UNI_ING", length = 11)
    private String uniIng;

    @Column(name = "UNI_EST", length = 10)
    private String uniEst;

    @Column(name = "LAB_HORAS", length = 15)
    private String labHoras;

    @Column(name = "LAB_EMP", length = 30)
    private String labEmp;

    @Column(name = "LAB_CAT", length = 30)
    private String labCat;

    @Column(name = "LAB_REL", length = 7)
    private String labRel;

    @Column(name = "SOLVENCIA", length = 45)
    private String solvencia;

    @Column(name = "SOLV_OTRO", length = 30)
    private String solvOtro;

    @Column(name = "COBERTURA", length = 30)
    private String cobertura;

    @Column(name = "COB_CAT", length = 12)
    private String cobCat;

    @Column(name = "ENFERMEDAD", length = 30)
    private String enfermedad;

    @Column(name = "RELIGION", length = 30)
    private String religion;

    @Column(name = "ACCION", length = 30)
    private String accion;

    @Column(name = "COD_BAR", length = 20)
    private String codBar;

    @Column(name = "COD_BAR1", length = 20)
    private String codBar1;
}
