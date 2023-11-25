package com.zenika.training.freenb.publishing.infra;

import com.zenika.training.freenb.publishing.domain.workspace.IdWorkspace;
import com.zenika.training.freenb.publishing.domain.workspace.Workspace;
import com.zenika.training.freenb.publishing.domain.workspace.Workspaces;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class WorkspacesInMemory implements Workspaces {

    private final Map<IdWorkspace, Workspace> map = new HashMap<>();
    @Override
    public Workspace findBy(IdWorkspace idWorkspace) {
        return map.get(idWorkspace);
    }

    @Override
    public IdWorkspace create(Workspace newWorkspace) {
        map.put(newWorkspace.getId(), newWorkspace);
        return newWorkspace.getId();
    }

}
