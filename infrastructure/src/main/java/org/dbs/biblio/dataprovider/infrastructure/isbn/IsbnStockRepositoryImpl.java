package org.dbs.biblio.dataprovider.infrastructure.isbn;

import org.dbs.biblio.dataprovider.application.port.out.IsbnStockRepository;
import org.dbs.biblio.dataprovider.domain.IsbnBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class IsbnStockRepositoryImpl implements IsbnStockRepository {

    private final IsbnEntityRepository isbnEntityRepository;
    private final IsbnMapper isbnMapper;

    @Autowired
    public IsbnStockRepositoryImpl(IsbnEntityRepository isbnEntityRepository, IsbnMapper isbnMapper) {
        this.isbnEntityRepository = isbnEntityRepository;
        this.isbnMapper = isbnMapper;
    }

    @Override
    public long sizeOfStock() {
        return this.isbnEntityRepository.count();
    }

    @Override
    public Set<IsbnBook> findIsbnsByPage(int pos, int sizeOfPage) {
        Pageable pageRequest = PageRequest.of(pos, sizeOfPage);
        return isbnMapper.buildIsbnBookSetFromIsbnEntitySet(this.isbnEntityRepository.findAll(pageRequest).toSet());
    }

    @Override
    public IsbnBook findIsbnsByPosition(int position) {
        Pageable pageRequest = PageRequest.of(position, 1);
        List<IsbnEntity> isbnEntities = isbnEntityRepository.findAll(pageRequest).toList();
        return isbnMapper.buildIsbnBookFromIsbnEntity(isbnEntities.get(0));
    }

}
