package com.fullcycle.admin.catalogo.domain.category;

import com.fullcycle.admin.catalogo.domain.AggregateRoot;
import com.fullcycle.admin.catalogo.domain.validation.ValidationHandler;
import java.time.Instant;

public class Category extends AggregateRoot<CategoryID> {

  private String name;
  private String description;
  private boolean active;

  private Instant createdAt;
  private Instant updatedAt;
  private Instant deletedAt;

  private Category(
    final CategoryID anId,
    final String anName,
    final String anDescription,
    final boolean anActive,
    final Instant anCreatedAt,
    final Instant anUpdatedAt,
    final Instant anDeletedAt
  ) {
    super(anId);
    this.name = anName;
    this.description = anDescription;
    this.active = anActive;
    this.createdAt = anCreatedAt;
    this.updatedAt = anUpdatedAt;
    this.deletedAt = anDeletedAt;
  }

  public static Category newCategory(
    final String aName,
    final String aDescription,
    final boolean aActive
  ) {
    var id = CategoryID.unique();
    var now = Instant.now();
    return new Category(id, aName, aDescription, aActive, now, now, null);
  }

  @Override
  public void validate(ValidationHandler handler) {
    new CategoryValidator(this, handler).validate();
  }

  public Category activate() {
    this.deletedAt = null;
    this.active = true;
    this.updatedAt = Instant.now();
    return this;
  }

  public Category deactivate() {
    if (getDeletedAt() == null) {
      this.deletedAt = Instant.now();
    }
    this.active = false;
    this.updatedAt = Instant.now();
    return this;
  }

  public CategoryID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public boolean isActive() {
    return active;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public Instant getDeletedAt() {
    return deletedAt;
  }
}
