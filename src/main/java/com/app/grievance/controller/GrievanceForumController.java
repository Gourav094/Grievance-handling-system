package com.app.grievance.controller;

import com.app.grievance.dto.GrievanceRequest;
import com.app.grievance.model.Grievance;
import com.app.grievance.service.GrievanceForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/forum")
public class GrievanceForumController {

  @Autowired
  private GrievanceForumService grievanceForumService;

  // Endpoint to view grievances (could be filtered by status, etc.)
  @GetMapping("/grievances")
  public ResponseEntity<?> getAllGrievances(@RequestParam(required = false) String status) {
    return ResponseEntity.ok(grievanceForumService.getAllGrievances(status));
  }

  //test Endpoint to view grievances
  @GetMapping("/grievances/test")
  public ResponseEntity<?> getAllGrievancesTest() {
    //return endpoint working
    return ResponseEntity.ok("Endpoint working");
  }

  // Endpoint to add a comment to a grievance
  @PostMapping("/add-comment")
  public ResponseEntity<?> addComment(@RequestBody GrievanceRequest grievanceRequest) {
    return ResponseEntity.ok(grievanceForumService.addComment(grievanceRequest));
  }

  // Endpoint to search grievances by title or description
  @GetMapping("/search")
  public ResponseEntity<?> searchGrievances(@RequestParam String query) {
    return ResponseEntity.ok(grievanceForumService.searchGrievances(query));
  }

  //Endpoint to add grievances
  @PostMapping("/grievances")
  public ResponseEntity<Grievance> createGrievance(@RequestBody Grievance grievance) {
    Grievance saved = grievanceForumService.createGrievance(grievance);
    return ResponseEntity.ok(saved);
  }

  // Endpoint to view grievancesby id
  @GetMapping("/grievances/{id}")
  public ResponseEntity<Grievance> getGrievanceById(@PathVariable Long id) {
    return ResponseEntity.ok(grievanceForumService.getGrievanceById(id));
  }
  //endpoint for sorting
  @GetMapping("/grievances/sorted")
  public Page<Grievance> getSortedGrievances(@RequestParam int page, @RequestParam int size) {
    return grievanceForumService.getAllGrievancesSorted(page, size);
  }

}
