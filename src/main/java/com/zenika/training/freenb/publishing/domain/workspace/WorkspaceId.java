package com.zenika.training.freenb.publishing.domain.workspace;

import java.util.UUID;

public record WorkspaceId(String value) {
    public static WorkspaceId create() {
        return new WorkspaceId(UUID.randomUUID().toString());
    }
}
