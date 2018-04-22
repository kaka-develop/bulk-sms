package minhthanh.ws;

import com.clct.rest.models.ObjectResponseModel;
import com.minhthanh.bulk.jpa.entities.TableTest;
import com.minhthanh.bulk.models.TableTestModel;
import com.minhthanh.bulksms.api.core.TableTestService;
import com.minhthanh.bulksms.api.errors.RestError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 * Created by luyenchu on 7/28/16.
 */
@Path("/api-ws/test")
public class TableTestWs {
    static final Logger LOG = LoggerFactory.getLogger(TableTest.class);

    @Autowired
    private TableTestService tableTestService;

    /**
     * http://localhost:36003/bulk-sms-ws/rest/v1/api-ws/test/mock
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/mock")
    public Response mock() {
        //use can use any model to reply to client, for instance List of something etc.
        ObjectResponseModel<TableTestModel> result = new ObjectResponseModel<>();
        try {
            TableTestModel tableTestModel = new TableTestModel();
            tableTestModel.column = "Column N : " + new Date();
            tableTestService.save(new TableTestModel());
        } catch (Exception e) {
            result.setErrorCode(RestError.ERROR_INSERT_DB.code());
            LOG.error("DB ERROR: ", e);
        }
        return Response.ok(result).build();
    }

    /**
     * http://localhost:36003/bulk-sms-ws/rest/v1/api-ws/test/mocklist
     *
     * @return {"value":[{"id":1,"column":null,"createdTime":null}],"errorCode":0,"errorMessage":null}
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/mocklist")
    public Response mockList() {
        //use can use any model to reply to client, for instance List of something etc.
        ObjectResponseModel<List<TableTestModel>> result = new ObjectResponseModel<>();
        try {
            List<TableTestModel> allModels = tableTestService.getAllModels();
            result.setValue(allModels);
        } catch (Exception e) {
            result.setErrorCode(RestError.ERROR_INSERT_DB.code());
            LOG.error("DB ERROR: ", e);
        }
        return Response.ok(result).build();
    }
}
