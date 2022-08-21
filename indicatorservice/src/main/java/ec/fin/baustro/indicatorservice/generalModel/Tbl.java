package ec.fin.baustro.indicatorservice.generalModel;

import java.util.List;
import java.util.Map;

public class Tbl {

    private Map<String, Object> cri;
    private List<Map<String, Object>> reg;

    public Tbl() {
    }

    public Tbl(Map<String, Object> cri, List<Map<String, Object>> reg) {
        this.cri = cri;
        this.reg = reg;
    }

    public Map<String, Object> getCri() {
        return cri;
    }

    public void setCri(Map<String, Object> cri) {
        this.cri = cri;
    }

    public List<Map<String, Object>> getReg() {
        return reg;
    }

    public void setReg(List<Map<String, Object>> reg) {
        this.reg = reg;
    }
}
