package com.zenika.training.freenb.publishing.domain;

public interface Workspaces {
    Workspace findBy(IdWorkspace idWorkspace);

    IdWorkspace create(Workspace newWorkspace);

    boolean exist(IdWorkspace aWorkspaceId);
}
