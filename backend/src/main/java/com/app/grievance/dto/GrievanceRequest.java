package com.app.grievance.dto;

import jakarta.validation.constraints.NotBlank;

public class GrievanceRequest {

  private Long id;

  @NotBlank(message = "Comment cannot be blank")
  private String comment;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
