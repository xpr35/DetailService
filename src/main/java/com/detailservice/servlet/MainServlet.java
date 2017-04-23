package com.detailservice.servlet;

import com.detailservice.model.Profiles;
import com.detailservice.service.DetailService;
import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MainServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(MainServlet.class);

    @Override
    protected void doGet(final HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        DetailService service = new DetailService();
        Profiles profiles = new Profiles();


        String cellIdString = req.getParameter("cell_id");
        if (cellIdString == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "cell_id parameter is required");
            log.warn("cell_id parameter wasn't in request");
            return;
        }

        Long cellId = 0L;
        try {
            cellId = Long.valueOf(cellIdString);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "cell_id parameter must be a number");
            log.warn("cell_id parameter wasn't a number. " + e.getMessage());
        }


        try {
            profiles = service.getDetails(cellId);
        } catch (PersistenceException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal error");
            log.error("Error in database. " + e.getMessage());
            return;
        } catch (InterruptedException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal error");
            log.error("Caught interrupted exception:" + e.getMessage());
        }

        Gson gson = new Gson();
        String json = gson.toJson(profiles);
        out.print(json);
    }
}
