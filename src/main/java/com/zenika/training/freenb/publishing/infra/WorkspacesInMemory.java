package com.zenika.training.freenb.publishing.infra;

import com.zenika.training.freenb.publishing.domain.workspace.WorkspaceId;
import com.zenika.training.freenb.publishing.domain.workspace.Workspace;
import com.zenika.training.freenb.publishing.domain.workspace.Workspaces;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class WorkspacesInMemory implements Workspaces {

    private final Map<WorkspaceId, Workspace> map = new HashMap<>();
    @Override
    public Workspace findBy(WorkspaceId idWorkspace) {
        return map.get(idWorkspace);
    }

    @Override
    public WorkspaceId create(Workspace newWorkspace) {
        map.put(newWorkspace.getId(), newWorkspace);
        return newWorkspace.getId();
    }

}
