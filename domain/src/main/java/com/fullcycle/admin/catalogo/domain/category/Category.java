package com.fullcycle.admin.catalogo.domain.category;

import com.fullcycle.admin.catalogo.domain.AggregateRoot;
import com.fullcycle.admin.catalogo.domain.validation.ValidationHandler;
import java.time.Instant;
import java.util.Objects;

public class Category extends AggregateRoot<CategoryID> implements Cloneable {

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
      final Instant anDeletedAt) {
    super(anId);
    this.name = anName;
    this.description = anDescription;
    this.active = anActive;
    this.createdAt = Objects.requireNonNull(anCreatedAt, "'createdAt' should not be null");
    this.updatedAt = Objects.requireNonNull(anCreatedAt, "'updatedAt' should not be null");
    this.deletedAt = anDeletedAt;
  }

  public static Category newCategory(
      final String aName,
      final String aDescription,
      final boolean aActive) {
    var id = CategoryID.unique();
    var now = Instant.now();
    var deletedAt = aActive ? null : now;
    return new Category(
        id,
        aName,
        aDescription,
        aActive,
        now,
        now,
        deletedAt);
  }

  public static Category with(
      final CategoryID anId,
      final String name,
      final String description,
      final boolean active,
      final Instant createdAt,
      final Instant updatedAt,
      final Instant deletedAt) {
    return new Category(
        anId,
        name,
        description,
        active,
        createdAt,
        updatedAt,
        deletedAt);

  }

  public static Category with(final Category category) {
    return with(
        category.getId(),
        category.name,
        category.description,
        category.isActive(),
        category.createdAt,
        category.updatedAt,
        category.deletedAt);
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

  public Category update(final String aName, final String aDescription, final boolean isActive) {
    if (isActive) {
      activate();
    } else {
      deactivate();
    }
    this.name = aName;
    this.description = aDescription;
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
