package org.dbs.biblio.dataprovider.infrastructure.isbn;

import org.dbs.biblio.dataprovider.domain.IsbnBook;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class IsbnMapper {
    public IsbnBook buildIsbnBookFromIsbnEntity(IsbnEntity isbnEntities) {
        IsbnBook isbnBook = new IsbnBook();
        isbnBook.setIsbn13(isbnEntities.getIsbn13());
        isbnBook.setTitre(isbnEntities.getTitre());
        isbnBook.setAuteur(isbnEntities.getAuteur());
        isbnBook.setEditeur(isbnEntities.getEditeur());
        isbnBook.setDate(isbnEntities.getDate());
        isbnBook.setLangue(isbnEntities.getLangue());
        isbnBook.setPrix(isbnEntities.getPrix());
        return isbnBook;
    }

    public Set<IsbnBook> buildIsbnBookSetFromIsbnEntitySet(Set<IsbnEntity> isbnEntities) {
        return isbnEntities.stream().map(this::buildIsbnBookFromIsbnEntity).collect(Collectors.toSet());
    }
}