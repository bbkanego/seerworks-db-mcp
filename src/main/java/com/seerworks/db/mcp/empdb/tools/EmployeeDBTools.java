package com.seerworks.db.mcp.empdb.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seerworks.db.mcp.empdb.service.EmployeeDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springaicommunity.mcp.context.McpSyncRequestContext;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDBTools {
    private final EmployeeDBService employeeDBService;
    private final ObjectMapper objectMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(EmployeeDBTools.class);

    public EmployeeDBTools(EmployeeDBService employeeDBService, ObjectMapper objectMapper1) {
        this.employeeDBService = employeeDBService;
        this.objectMapper = objectMapper1;
    }

    /**
     * https://docs.spring.io/spring-ai/reference/api/mcp/mcp-server-boot-starter-docs.html#_mcp_server_annotations
     * @param context
     * @param query
     * @return
     */
    @McpTool(name = "employee-query-runner", description = "This will run queries against the " +
            "Employee DB. This takes a 'SQL query' as an argument and will return a JSON representation of results")
    public String runQueryOnEmployeesDB(McpSyncRequestContext context, @McpToolParam(description = "This is query that you want to run",
            required = true)String query) {
        try {
            return this.objectMapper.writeValueAsString(this.employeeDBService.runQueryAndReturnListOfMaps(query));
        } catch (JsonProcessingException e) {
            LOGGER.error("Error occurred while converting results", e);
            throw new RuntimeException(e);
        }
    }
}
