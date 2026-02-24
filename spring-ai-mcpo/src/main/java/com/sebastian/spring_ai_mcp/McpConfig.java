package com.sebastian.spring_ai_mcp;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.Arrays;

@Configuration
public class McpConfig {

    private final ToolCallbackProvider toolCallbackProvider;

    public McpConfig(ToolCallbackProvider toolCallbackProvider) {
        this.toolCallbackProvider = toolCallbackProvider;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void listConnectedServers() {
        var tools = toolCallbackProvider.getToolCallbacks();
        if (tools.length == 0) {
            System.out.println("INFO: No MCP tools available.");
        } else {
            System.out.println("INFO: Available MCP tools:");
            Arrays.stream(tools).forEach(tool ->
                    System.out.printf("  - %s%n", tool.getToolDefinition().name()));
        }
    }
}
