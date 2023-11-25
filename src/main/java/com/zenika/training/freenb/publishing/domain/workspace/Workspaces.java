package com.zenika.training.freenb.publishing.domain.workspace;

public interface Workspaces {

    Workspace findBy(WorkspaceId idWorkspace);

    WorkspaceId create(Workspace newWorkspace);

}
