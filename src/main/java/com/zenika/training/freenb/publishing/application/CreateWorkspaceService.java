package com.zenika.training.freenb.publishing.application;

import com.zenika.training.freenb.publishing.domain.IdWorkspace;
import com.zenika.training.freenb.publishing.domain.Workspace;
import com.zenika.training.freenb.publishing.domain.Workspaces;

public class CreateWorkspaceService {
    private final Workspaces workspaceRepo;

    public CreateWorkspaceService(Workspaces workspaceRepo) {

        this.workspaceRepo = workspaceRepo;
    }

    public IdWorkspace execute(Workspace newWorkspace) {
        return workspaceRepo.create(newWorkspace);
    }
}
