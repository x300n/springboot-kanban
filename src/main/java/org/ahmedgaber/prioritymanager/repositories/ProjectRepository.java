package org.ahmedgaber.prioritymanager.repositories;


import org.ahmedgaber.prioritymanager.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findProjectByProjectIdentifier(String projectId);

    @Override
    Iterable<Project> findAll();
}
