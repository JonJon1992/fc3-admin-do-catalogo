package com.fullcycle.admin.catalogo.domain.category;

import com.fullcycle.admin.catalogo.domain.pagination.Pagination;
import com.fullcycle.admin.catalogo.domain.pagination.SearchQuery;
import java.util.Optional;
import java.util.List;

public interface CategoryGateway {
    Category create(Category aCategory);

    void deleteById(CategoryID aCategoryID);

    Optional<Category> findById(CategoryID aCategoryID);

    Category update(Category aCategory);

    Pagination<Category> findAll(SearchQuery query);

    List<CategoryID> existsByIds(Iterable<CategoryID> ids);
}
