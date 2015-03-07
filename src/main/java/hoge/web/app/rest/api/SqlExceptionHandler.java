package hoge.web.app.rest.api;

import hoge.web.app.rest.api.data.Message;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.net.HttpURLConnection;
import java.sql.SQLException;

@Provider
public class SqlExceptionHandler implements ExceptionMapper<SQLException> {

    @Override
    public Response toResponse(SQLException exception) {
        Message msg = new Message.Builder()
                .setDeveloperMessage("DBエラーだ、解析班急げ！")
                .setErrorCode("I-0401")
                .setUserMessage("通信が込み合ってますよ？")
                .setMoreInfo("http://developers.company.com/errors/I-0401")
                .build();

        return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR)
                .entity(msg)
                .build();
    }
}
