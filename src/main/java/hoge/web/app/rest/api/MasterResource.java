package hoge.web.app.rest.api;

import hoge.web.app.model.Employee;
import hoge.web.app.model.Role;
import hoge.web.app.rest.api.data.Message;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


@Path("/{version}/master")
public class MasterResource {

    public static class ResponseList<T, K> {
        public List<T> list;
        public K nextKey;
    }

    @GET
    @Path("/employees")
    @Produces("application/json; charset=UTF-8")
    public ResponseList<Employee, Integer> getEmployees(
            @QueryParam("limit") @DefaultValue("3") Integer limit,
            @QueryParam("nextkey") Integer nextKey) throws SQLException {

        ResponseList<Employee, Integer> resp = new ResponseList<>();

        if (nextKey != null) {
            resp.list = Employee.find(limit + 1, "id >= ?", nextKey);
        } else {
            resp.list = Employee.findAll(limit + 1);
        }
        if (resp.list.size() > limit) {
            resp.nextKey = resp.list.get(limit).getId();
            resp.list.remove(limit.intValue());
        }

        return resp;
    }

//    @GET
//    @Path("/employees")
//    @Produces("application/json; charset=UTF-8")
//    public List<Employee> getEmployees(
//            @PathParam("version") String version,
//            @QueryParam("role") List<String> roles,
//            @QueryParam("limit") Integer limit,
//            @QueryParam("offset") Integer offset) throws SQLException {
//
//        return Employee.findByRoles(roles)
//                .from(offset)
//                .fetch(limit);
//    }

    @GET
    @Path("/employees/{id}")
    @Produces("application/json; charset=UTF-8")
    public Employee getEmployee(@PathParam("id") Integer id) throws SQLException {

        Optional<Employee> emp = Employee.findBy(id);
        if (emp.isPresent()) {
            return emp.get();
        } else {
            throw NOT_FOUND(String.format("No found id [%1$s]", id));
        }
    }

    @GET
    @Path("/employees/{id}/roles")
    @Produces("application/json; charset=UTF-8")
    public List<Role> getEmployeeRoles(
            @PathParam("id") Integer id,
            @QueryParam("role") String role) throws SQLException {

        Employee emp = getEmployee(id);
        return emp.getRoles(role);
    }

    private WebApplicationException NOT_FOUND(String userMsg) {

        Message msg = new Message.Builder()
                .setDeveloperMessage("データなし")
                .setErrorCode("I-404")
                .setUserMessage(userMsg)
                .setMoreInfo("http://developers.company.com/errors/I-404")
                .build();

        return new WebApplicationException(
                Response.status(Status.NOT_FOUND)
                        .entity(msg)
                        .build());
    }
}
