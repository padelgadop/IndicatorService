package ec.fin.baustro.indicatorservice.common;


public enum AnswerCode {
    SUCCESS("000","PROCESO EJECUTADO CORRECTAMENTE", "EJECUCION DEL PROCESO EXITOSAMENTE"),
    ERROR_INPUT("899","NO EXISTE UN PROCESO A EJECUTAR CON LOS PARAMETROS DE ENTRADA","EN LA TABLA TPROCESO ENDPOINT NO EXISTE UNA PARAMETRIZACION PARA EL SERVICIO"),
    ERROR_INPUT_JSON("898","EXISTE UN ERROR INTERNO EN LA EJECUCION DEL PROCESO","ERROR AL EJECUTAR EL PROCESO DE LA CONSULTA DE PROCESO"),
    ERROR_NULL("895","EXISTE UN ERROR DE EJECUCION, DATOS NULOS","EXISTEN DATOS NULOS"),
    ERROR_GENERAL("894","EL PROCESO NO FUE EJECUTADO EXITOSAMENTE",""),

    ERROR_DATA("999","NOSE ENCOTRO DATOS PARA  SER PROCESADOS","") ,
    INVALID_ECNRYPT("998","ERROR DE ENCRYPTACION","") ;

    private String code;
    private String userInformation;
    private String technicalInformation;

    AnswerCode(String code, String userInformation, String technicalInformation) {
        this.code=code;
        this.userInformation=userInformation;
        this.technicalInformation=technicalInformation;
    }

    public String getCode() {
        return code;
    }

    void setCode(String code) {
        this.code=code;
    }

    public String getUserInformation() {
        return userInformation;
    }

    void setUserInformation(String userInformation) {
        this.userInformation=userInformation;
    }

    public String getTechnicalInformation() {
        return technicalInformation;
    }

    void setTechnicalInformation(String technicalInformation) {
        this.technicalInformation=technicalInformation;
    }
}
