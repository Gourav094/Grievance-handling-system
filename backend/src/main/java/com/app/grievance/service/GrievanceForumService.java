package com.app.grievance.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import com.app.grievance.dto.GrievanceRequest;
import com.app.grievance.model.Grievance;
import com.app.grievance.repository.GrievanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class GrievanceForumService {


@Autowired
private GrievanceRepository grievanceRepository;

// Fetch all grievances with optional filtering by status
public List<Grievance> getAllGrievances(String status) {
  if (status != null) {
    return grievanceRepository.findByStatus(status);  // assuming this method is defined
  } else {
    return grievanceRepository.findAll();
  }
}


public Grievance addComment(GrievanceRequest req) {
  Grievance g = grievanceRepository.findById(req.getId())
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));


      // Set the comment (no setComment method, so add to comments list if needed)
      // grievance.setComment(grievanceRequest.getComment());
      // Optionally, you can add a new Comment entity to the grievance's comments list here if needed
      grievance.setStatus("Commented"); // Update status if needed
      return grievanceRepository.save(grievance);
    }
    catch (Exception e) {
      e.printStackTrace();  // Or log.error("Error:", e);
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Grievance not found with ID: " + grievanceRequest.getId());
    }
  }


  g.getComments().add(c);
  g.setStatus("Commented");
  return grievanceRepository.save(g);
}

// Search for grievances by title or description
public List<Grievance> searchGrievances(String query) {
  return grievanceRepository.searchByQuery(query); // Assuming you add a method for search
}


//get for grievance by id
public Grievance getGrievanceById(Long id) {
  return grievanceRepository.findById(id)
          .orElseThrow(() -> new RuntimeException("Grievance not found"));
}

// public Grievance createGrievance(Grievance grievance) {
//   grievance.setCreatedAt(new Date()); // Automatically set the current timestamp
//   return grievanceRepository.save(grievance);
// }

public Page<Grievance> getAllGrievancesSorted(int page, int size) {
  Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
  return grievanceRepository.findAll(pageable);
}

//for filtering
public List<Grievance> filterGrievances(String status, String createdBy, String assignedTo) {
  return grievanceRepository.filterGrievances(status, createdBy, assignedTo);
}
  
  
  
  private Long generateUnique5DigitId() {
      Random random = new Random();
      long id = 10000 + random.nextInt(90000); // generates 5-digit number
    
      while (grievanceRepository.existsById(id)) {
          id = 10000 + random.nextInt(90000);
      }
    
      return id;
  }
    
  public Grievance createGrievance(Grievance grievance) {
      grievance.setId(generateUnique5DigitId());
      grievance.setCreatedAt(new Date()); // if not already set
      return grievanceRepository.save(grievance);
  }

  public Grievance updateGrievance(Long id, Grievance payload) {
      Grievance existing = grievanceRepository.findById(id)
          .orElseThrow(() ->
              new ResponseStatusException(HttpStatus.NOT_FOUND,
                  "Grievance not found with ID: " + id));

      // only update the three fields
      if (payload.getStatus() != null) {
          existing.setStatus(payload.getStatus());
      }
      if (payload.getDescription() != null) {
          existing.setDescription(payload.getDescription());
      }
      if (payload.getCategory() != null) {
          existing.setCategory(payload.getCategory());
      }
      if (payload.getAssignedTo() != null) {
          existing.setAssignedTo(payload.getAssignedTo());
      }

      return grievanceRepository.save(existing);
  }
}
