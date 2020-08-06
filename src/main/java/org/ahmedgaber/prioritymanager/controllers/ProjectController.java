package org.ahmedgaber.prioritymanager.controllers;


import org.ahmedgaber.prioritymanager.domain.Project;
import org.ahmedgaber.prioritymanager.exceptions.MapValidationError;
import org.ahmedgaber.prioritymanager.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {

    private final ProjectService projectService;
    private final MapValidationError mapValidationError;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
        mapValidationError = new MapValidationError();
    }

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {

        ResponseEntity<?> errorMap = MapValidationError.getResponseEntity(result);
        if (errorMap != null) return errorMap;

        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }


    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
        Project project = projectService.findProjectByIdentifier(projectId);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects() {return projectService.findAllProjects();}


    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId) {
        projectService.deleteProjectByIdentifier(projectId);

        return new ResponseEntity<String>("Project with ID: '"+projectId+"' has been deleted", HttpStatus.OK);
    }

}
