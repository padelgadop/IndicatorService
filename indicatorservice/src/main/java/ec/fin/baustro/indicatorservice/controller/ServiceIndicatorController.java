package ec.fin.baustro.indicatorservice.controller;


import com.google.gson.Gson;
import ec.fin.baustro.indicatorservice.common.AnswerCode;
import ec.fin.baustro.indicatorservice.common.EncryptDecrypt256;
import ec.fin.baustro.indicatorservice.fitbankModel.JsonObjectFitbank;
import ec.fin.baustro.indicatorservice.generalModel.ResponseModel;
import ec.fin.baustro.indicatorservice.service.BuildQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
public class ServiceIndicatorController {

    @Value("${service.encrypt.key}")
    String key;
    @Autowired
    BuildQueryService buildQueryService;
    private Gson gson;

    /**
     * recibe un string JSON con la lista para la carga masiva
     *
     * @param responseModel dto model
     * @return datos de indicadores
     */
    @PostMapping(value = "/load")
    public ResponseModel processDataIn(@RequestBody ResponseModel responseModel) {
        ResponseModel respuesta = new ResponseModel();
        gson = new Gson();
        try {
            log.info("******************************************************************");
            log.info("* =========  ENTRO A PROCESAR CARGA NASIVA ========= *");
            log.info("******************************************************************");
            log.info("* ===================== TRAMA JSON DE ENTRADA ================== *");
            log.info(responseModel.getTrama());
            log.info("******************************************************************");

            String trama = EncryptDecrypt256.decryptAES(responseModel.getTrama().getBytes(StandardCharsets.UTF_8), key);
            JsonObjectFitbank tabla = gson.fromJson(trama, JsonObjectFitbank.class);

            if (tabla.getTbl().size() > 0) {

                log.info("NUMERO REGITROS ENCONTRADOS " + tabla.getTbl().size() + "");
                buildQueryService.insertData(tabla);
                respuesta = this.responseProcess(AnswerCode.SUCCESS.getCode(), AnswerCode.SUCCESS.getUserInformation(), AnswerCode.SUCCESS.getTechnicalInformation());
            } else {
                log.info("******************************************************************");
                log.info("* ==== NO EXISTEN DATOS ==== *");
                log.info("******************************************************************");
                respuesta = this.responseProcess(AnswerCode.ERROR_DATA.getCode(), AnswerCode.ERROR_DATA.getUserInformation(), AnswerCode.ERROR_DATA.getTechnicalInformation());

            }

            this.logresponse(respuesta);
            return respuesta;
        } catch (Exception e) {
            final StringWriter errors = new StringWriter();
            String causedBy = e.getMessage();
            log.error(causedBy);
            e.printStackTrace(new PrintWriter(errors));
            final String error = errors.toString();
            if (error.contains("JsonSyntaxException")) {
                respuesta = this.responseProcess(AnswerCode.ERROR_INPUT_JSON.getCode(), AnswerCode.ERROR_INPUT_JSON.getUserInformation(), causedBy);
            } else if (error.contains("NullPointerException")) {
                respuesta = this.responseProcess(AnswerCode.ERROR_NULL.getCode(), AnswerCode.ERROR_NULL.getUserInformation(), causedBy);

            } else {
                respuesta = this.responseProcess(AnswerCode.ERROR_GENERAL.getCode(), AnswerCode.ERROR_GENERAL.getUserInformation(), causedBy);
            }

        }
        return respuesta;
    }


    /**
     * Metodo para generar una respuesta ante un evento generado en ejecucion
     *
     * @param code        error code
     * @param userMessage msg usuario
     * @param techmsg     trama ingreso
     */
    public ResponseModel responseProcess(String code, String userMessage, String techmsg) {
        ResponseModel responseProcess = new ResponseModel();
        responseProcess.setCodResponse(code);
        responseProcess.setMsgUsr(userMessage);
        responseProcess.setTechnicalMsg(techmsg);
        return responseProcess;
    }

    /**
     * Procesa el sp que genera lo resultados de inidicadores segun los datos cargados
     *
     * @param responseModel response model
     * @return list indicadores
     * @throws Exception ex
     */
    @PostMapping(value = "/indicators")
    public ResponseModel processDataOut(@RequestBody ResponseModel responseModel) {
        log.info("******************************************************************");
        log.info("* =========  ENTRO A PROCESAR INDICADORES  ========= *");
        log.info("******************************************************************");
        log.info("* ===================== TRAMA JSON DE ENTRADA ================== *");
        log.info(responseModel.getTrama());
        log.info("******************************************************************");

        ResponseModel respuesta = new ResponseModel();
        gson = new Gson();
        try {
            String tramaIn = EncryptDecrypt256.decryptAES(responseModel.getTrama().getBytes(StandardCharsets.UTF_8), key);
            String tramaOut =  gson.toJson(buildQueryService.getIndicators(tramaIn));
            respuesta.setCodResponse(AnswerCode.SUCCESS.getCode());
            respuesta.setTrama(EncryptDecrypt256.encryptAES(tramaOut, key));
            respuesta.setMsgUsr(AnswerCode.SUCCESS.getUserInformation());
            respuesta.setTechnicalMsg("");
        } catch (Exception e) {
            final StringWriter errors = new StringWriter();
            String causedBy = e.getMessage();
            log.error(causedBy);
            e.printStackTrace(new PrintWriter(errors));
            final String error = errors.toString();
            if (error.contains("JsonSyntaxException")) {
                respuesta = this.responseProcess(AnswerCode.ERROR_INPUT_JSON.getCode(), AnswerCode.ERROR_INPUT_JSON.getUserInformation(), causedBy);
            } else if (error.contains("NullPointerException")) {
                respuesta = this.responseProcess(AnswerCode.ERROR_NULL.getCode(), AnswerCode.ERROR_NULL.getUserInformation(), causedBy);

            } else {
                respuesta = this.responseProcess(AnswerCode.ERROR_GENERAL.getCode(), AnswerCode.ERROR_GENERAL.getUserInformation(), causedBy);
            }
        }

        this.logresponse(respuesta);
        return respuesta;
    }

    /**
     * LOG trama de salida
     * @param respuesta trama de salida
     */
    void logresponse(ResponseModel respuesta) {
        log.info("*****************************************************************************");
        log.info("* ======================== TRAMA JOSON DE SALIDA ========================== *");
        log.info(gson.toJson(respuesta));
        log.info("*****************************************************************************");
    }
}
