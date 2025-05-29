package com.app.grievance.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "grievance")  // Ensure the table name matches the one in your MySQL database
public class Grievance {

    // Getters and Setters
    @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String description;
  private String status;       // OPEN, PENDING, RESOLVED
  private String comment;
  private String category;     //  Newly added field
  @Column(name = "created_by")
  private String createdBy;
  @Column(name = "assigned_to")
  private String assignedTo;

  @Column(name = "created_at", nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private java.util.Date createdAt;

  // Default constructor
  public Grievance() {}

  // Constructor with fields (including category)
  public Grievance(String title, String description, String status, String comment, String category,  String createdBy, String assignedTo,java.util.Date createdAt) {
    this.title = title;
    this.description = description;
    this.status = status;
    this.comment = comment;
    this.category = category;
    this.createdAt = createdAt;
    this.createdBy = createdBy;
    this.assignedTo = assignedTo;
  }



}
