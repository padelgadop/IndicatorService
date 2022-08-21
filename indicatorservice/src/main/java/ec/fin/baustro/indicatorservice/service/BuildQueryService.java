package ec.fin.baustro.indicatorservice.service;

import ec.fin.baustro.indicatorservice.common.QueryCallback;
import ec.fin.baustro.indicatorservice.fitbankModel.JsonObjectFitbank;
import ec.fin.baustro.indicatorservice.fitbankModel.Tbl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class BuildQueryService {

    @Value("${service.columns.allowed}")
    String columns;
    @Value("${service.table.name}")
    String tableName;

    private final EntityManager entityManager;

    public BuildQueryService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Async
    public void insertData(JsonObjectFitbank tabla) {
        List<Map<String, Object>> reg = tabla.getTbl().get("TCONTACTIBILIDAD").getReg();
        String values;
        for (Map<String, Object> regElm : reg) {
            values = parseArrayToString(buildRecords(regElm));
            this.insertRecords(columns, values);
        }
    }

    /**
     * Genera los valores de cada columna para insertar en la tabla
     *
     * @param regElm tbl
     * @return list string
     */
    ArrayList<String> buildRecords(Map<String, Object> regElm) {
        String[] col = !columns.isEmpty() ? columns.split(",") : null;
        ArrayList<String> response = new ArrayList<>();
        for (String elm : col) {
            if (regElm.containsKey(elm)) {
                response.add((String) regElm.get(elm));
            }
        }
        return response;
    }

    /**
     * Convierte un array string en un string separado por comas.
     *
     * @param geekcourses array
     * @return string separado por comas
     */
    private String parseArrayToString(ArrayList<String> geekcourses) {
        String commaseparatedlist = geekcourses.toString();
        commaseparatedlist
                = commaseparatedlist.replace("[", "")
                .replace("]", "")
                .replace(" ", "");
        return commaseparatedlist;
    }

    public <T> List<T> insert(QueryCallback<List<T>> callback) {
        return callback.doWithEntityManager(this.entityManager);
    }


    public List insertRecords(String criteria, String values) {
        return this.insert(entityManager -> {
            log.info("GENERATING QUERY..... ");
            // genera el insert  segun las columnas parametrizadas en el properties
            StringBuilder builder = new StringBuilder();
            // INSERT
            builder.append("INSERT INTO " + tableName + " " + System.lineSeparator());
            builder.append(" (" + criteria + ")");
            builder.append(" VALUES " + System.lineSeparator());
            builder.append(" (" + values + " ) ");

            log.info("QUERY -----> " + builder.toString());
            // Create query
            String nativeQuery = builder.toString();
            Query query = entityManager.createNativeQuery(nativeQuery);

            return query.getResultList();
        });
    }

    /**
     * Procesa la respuesta del sp y lo parsea a JsonObjectFitbank
     * @param criteria criterio busqueda
     * @return  JsonObjectFitbank
     */
    public JsonObjectFitbank getIndicators(String criteria) {
        List<Object[]> responselist =  new ArrayList<>();

        JsonObjectFitbank response  = new JsonObjectFitbank();
        response.setUsr("");
        response.setFcn("");
        response.setTerminal("");
        Map<String, Tbl> tbl = new HashMap<>();
        Tbl tbl2 = new Tbl();
        List<Map<String, Object>> records = new ArrayList<>();
        responselist = this.getList(criteria);

        for (Object[] elm: responselist) {
            Map<String, Object> field = new HashMap<>();
            field.put("id",elm[0]);
            field.put("first_name",elm[1]);
            field.put("last_name",elm[2]);
            field.put("phone",elm[3]);
            field.put("email",elm[4]);
            field.put("street",elm[5]);
            field.put("city",elm[6]);
            field.put("state",elm[7]);
            field.put("zip_code",elm[8]);
            records.add(field);
        }
        tbl2.setReg(records);
        tbl.put("TCONTACTIBILIDAD", tbl2);
        response.setTbl(tbl);
        return response;
    }

    /**
     * Ejecuta el procedimiento almacenado y devuelve la lista
     * @param criteria criterio busqueda
     * @return List<Object[]>
     */
    public List<Object[]> getList(String criteria) {
        return this.insert(entityManager -> {
            log.info("GENERATING QUERY..... ");

            // ejecuta procedimiento almacenado
            StringBuilder builder = new StringBuilder();
            // exec
            builder.append("GET_TOTAL_CUSTOMERS_BY_CITY  @model_in  = " + criteria);

            log.info("QUERY -----> " + builder.toString());
            // Create query
            String nativeQuery = builder.toString();
            Query query = entityManager.createNativeQuery(nativeQuery);

            return query.getResultList();
        });
    }


}
