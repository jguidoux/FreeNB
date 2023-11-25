package com.zenika.training.freenb.publishing.application;

import com.zenika.training.freenb.publishing.domain.workspace.Workspace;
import com.zenika.training.freenb.publishing.domain.workspace.WorkspaceId;
import com.zenika.training.freenb.publishing.domain.workspace.Workspaces;

public class CreateWorkspaceService {
    private final Workspaces workspaceRepo;

    public CreateWorkspaceService(Workspaces workspaceRepo) {

        this.workspaceRepo = workspaceRepo;
    }

    public WorkspaceId execute(Workspace newWorkspace) {
        return workspaceRepo.create(newWorkspace);
    }
}
