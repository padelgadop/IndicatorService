package ec.fin.baustro.indicatorservice.generalModel;

import java.util.HashMap;
import java.util.Map;

public class JsonObject {
    private String usr;
    private String sid;
    private String ser;
    private String tip;
    private String ope;
    private String can;
    private String fcn;

    private Map<String, Tbl> tbl = new HashMap<>();
    private Map<String, Object> ctl = new HashMap<>();
    private String codRespuesta;
    private String msgUsuario;
    private String msgTecnico;

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getSer() {
        return ser;
    }

    public void setSer(String ser) {
        this.ser = ser;
    }

    public String getOpe() {
        return ope;
    }

    public void setOpe(String ope) {
        this.ope = ope;
    }

    public String getCan() {
        return can;
    }

    public void setCan(String can) {
        this.can = can;
    }

    public String getFcn() {
        return fcn;
    }

    public void setFcn(String fcn) {
        this.fcn = fcn;
    }

    public Map<String, Tbl> getTbl() {
        return tbl;
    }

    public void setTbl(Map<String, Tbl> tbl) {
        this.tbl = tbl;
    }

    public Map<String, Object> getCtl() {
        return ctl;
    }

    public void setCtl(Map<String, Object> ctl) {
        this.ctl = ctl;
    }

    public String getCodRespuesta() {
        return codRespuesta;
    }

    public void setCodRespuesta(String codRespuesta) {
        this.codRespuesta = codRespuesta;
    }

    public String getMsgUsuario() {
        return msgUsuario;
    }

    public void setMsgUsuario(String msgUsuario) {
        this.msgUsuario = msgUsuario;
    }

    public String getMsgTecnico() {
        return msgTecnico;
    }

    public void setMsgTecnico(String msgTecnico) {
        this.msgTecnico = msgTecnico;
    }
}
