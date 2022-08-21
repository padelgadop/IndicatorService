package ec.fin.baustro.indicatorservice.generalModel;

public class ResponseModel {
    String codResponse;
    String msgUsr;
    String trama;

    String technicalMsg;

    public String getCodResponse() {
        return codResponse;
    }


    public void setCodResponse(String codResponse) {
        this.codResponse = codResponse;
    }

    public String getMsgUsr() {
        return msgUsr;
    }

    public void setMsgUsr(String msgUsr) {
        this.msgUsr = msgUsr;
    }

    public String getTrama() {
        return trama;
    }

    public void setTrama(String trama) {
        this.trama = trama;
    }

    public String getTechnicalMsg() {
        return technicalMsg;
    }

    public void setTechnicalMsg(String technicalMsg) {
        this.technicalMsg = technicalMsg;
    }
}
