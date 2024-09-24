package org.isemper.com.isemperws.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlumnoDTO implements Serializable {

        @JsonProperty("CODIGO")
        private String codigo;

        @JsonProperty("N_DOC")
        private String nDoc;

        @JsonProperty("T_DOC")
        private String tDoc;

        @JsonProperty("APELLIDO")
        private String apellido;

        @JsonProperty("NOMBRE")
        private String nombre;

        @JsonProperty("DOMICILIO")
        private String domicilio;

        @JsonProperty("F_INGRESO")
        private Date fIngreso;

        @JsonProperty("PROVINCIA")
        private String provincia;

        @JsonProperty("LOCALIDAD")
        private String localidad;

        @JsonProperty("F_NAC")
        private Date fNac;

        @JsonProperty("L_NAC")
        private String lNac;

        @JsonProperty("P_NAC")
        private String pNac;

        @JsonProperty("N_TEL")
        private String nTel;

        @JsonProperty("C_TEL")
        private String cTel;

        @JsonProperty("EDAD")
        private String edad;

        @JsonProperty("E_CIVIL")
        private String eCivil;

        @JsonProperty("NACION")
        private String nacion;

        @JsonProperty("SEXO")
        private Character sexo;

        @JsonProperty("C_POSTAL")
        private String cPostal;

        @JsonProperty("EMAIL")
        private String email;

        @JsonProperty("OBSERVA")
        private String observa;

        @JsonProperty("FOTO")
        private String foto;

        @JsonProperty("FOTO_FILE")
        private String fotoFile;

        @JsonProperty("FECHA")
        private Date fecha;

        @JsonProperty("SANCION")
        private String sancion;

        @JsonProperty("PA_NAC")
        private String paNac;

        @JsonProperty("PAIS")
        private String pais;

        @JsonProperty("SEC_NOM")
        private String secNom;

        @JsonProperty("SEC_LUG")
        private String secLug;

        @JsonProperty("SEC_TIT")
        private String secTit;

        @JsonProperty("SEC_AÑO")
        private String secAño;

        @JsonProperty("UNI_CAR")
        private String uniCar;

        @JsonProperty("UNI_NOM")
        private String uniNom;

        @JsonProperty("UNI_LUG")
        private String uniLug;

        @JsonProperty("UNI_ING")
        private String uniIng;

        @JsonProperty("UNI_EST")
        private String uniEst;

        @JsonProperty("LAB_HORAS")
        private String labHoras;

        @JsonProperty("LAB_EMP")
        private String labEmp;

        @JsonProperty("LAB_CAT")
        private String labCat;

        @JsonProperty("LAB_REL")
        private String labRel;

        @JsonProperty("SOLVENCIA")
        private String solvencia;

        @JsonProperty("SOLV_OTRO")
        private String solvOtro;

        @JsonProperty("COBERTURA")
        private String cobertura;

        @JsonProperty("COB_CAT")
        private String cobCat;

        @JsonProperty("ENFERMEDAD")
        private String enfermedad;

        @JsonProperty("RELIGION")
        private String religion;

        @JsonProperty("ACCION")
        private String accion;

        @JsonProperty("COD_BAR")
        private String codBar;

        @JsonProperty("COD_BAR1")
        private String codBar1;

}
