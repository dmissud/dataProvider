package org.dbs.biblio.dataprovider.exposition;

import org.dbs.biblio.dataprovider.application.port.in.query.DataDescription;
import org.dbs.biblio.dataprovider.application.port.in.query.DataProviderDescriptionQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/dataProvider")
@CrossOrigin(origins = "*")
public class DataProviderRessource {
    private final DataProviderDescriptionQuery dataProviderDescriptionQuery;

    public DataProviderRessource(DataProviderDescriptionQuery dataProviderDescriptionQuery) {
        this.dataProviderDescriptionQuery = dataProviderDescriptionQuery;
    }

    @GetMapping(value = "provide", produces = "application/json")
    public ResponseEntity<Set<DataDescription>> descriptionOfIsbn() {
        return new ResponseEntity<>(this.dataProviderDescriptionQuery.descriptionOfDataProvide(), HttpStatus.OK);
    }
}
