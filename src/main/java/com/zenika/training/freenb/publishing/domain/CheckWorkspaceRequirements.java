package com.zenika.training.freenb.publishing.domain;

import com.zenika.training.freenb.publishing.applcation.OfferCapacityOverloadWorkspaceCapacity;

public class CheckWorkspaceRequirements {
    private  final Workspaces workspaces;

    public CheckWorkspaceRequirements(Workspaces workspaces) {
        this.workspaces = workspaces;
    }

    public void checkWorkspaceRequirements(IdWorkspace aWorkspaceId, Capacity capacity) {
        Workspace existingWorkspace = this.workspaces.findBy(aWorkspaceId);
        if (existingWorkspace == null) {
            throw new WorkspaceDoesNotExist();
        }
        if (existingWorkspace.isUpperToWorkspaceCapacity(capacity)) {
            throw new OfferCapacityOverloadWorkspaceCapacity();
        }
    }
}
