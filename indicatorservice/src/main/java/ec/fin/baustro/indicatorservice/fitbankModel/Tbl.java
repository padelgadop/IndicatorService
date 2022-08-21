package ec.fin.baustro.indicatorservice.fitbankModel;

import java.util.List;
import java.util.Map;

public class Tbl {

    private List<Map<String, Object>> reg;

    public Tbl() {
    }

    public Tbl(List<Map<String, Object>> reg) {
        this.reg = reg;
    }

    public List<Map<String, Object>> getReg() {
        return reg;
    }

    public void setReg(List<Map<String, Object>> reg) {
        this.reg = reg;
    }
}
