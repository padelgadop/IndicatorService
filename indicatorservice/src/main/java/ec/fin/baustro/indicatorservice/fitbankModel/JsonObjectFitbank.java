package ec.fin.baustro.indicatorservice.fitbankModel;

import java.util.HashMap;
import java.util.Map;

public class JsonObjectFitbank {
    private String usr;
    private String terminal;
    private String fcn;
    private Map<String, Tbl> tbl = new HashMap<>();

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }


    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
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

    public void setTbl(Map<String, Tbl> tbl) { this.tbl = tbl; }



}
