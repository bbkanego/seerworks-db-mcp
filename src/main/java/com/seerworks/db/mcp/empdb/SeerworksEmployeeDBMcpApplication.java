package com.seerworks.db.mcp.empdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeerworksEmployeeDBMcpApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeerworksEmployeeDBMcpApplication.class, args);
	}

//    @Bean
//    public ToolCallbackProvider toolsList(EmployeeDBTools employeeDBTools) {
////        List<ToolCallback> toolCallbackList = List.of(ToolCallbacks.from(employeeDBTools));
////        return ToolCallbackProvider.from(toolCallbackList);
//        return MethodToolCallbackProvider.builder().toolObjects(employeeDBTools).build();
//
//    }
}
