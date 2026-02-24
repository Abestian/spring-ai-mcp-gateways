package com.sebastian.spring_ai_mcp;

import io.modelcontextprotocol.client.McpSyncClient;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.List;

@Configuration
public class McpConfig {

    private final List<McpSyncClient> mcpClients;

    public McpConfig(List<McpSyncClient> mcpClients) {
        this.mcpClients = mcpClients;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void listConnectedServers() {

        if (mcpClients.isEmpty()) {
            System.out.println("INFO: No MCP Servers connected.");
        } else {
            System.out.println("INFO: Connected MCP Servers:");
            mcpClients.forEach(client -> {
                var serverInfo = client.getServerInfo();
                System.out.printf("- Server: %s | Version: %s%n",
                        serverInfo.name(),
                        serverInfo.version());
            });
        }
    }
}
