package org.dbs.biblio.dataprovider.lignecmd;

import lombok.extern.slf4j.Slf4j;
import org.dbs.biblio.dataprovider.application.port.in.query.DataDescription;
import org.dbs.biblio.dataprovider.application.port.in.query.DataProviderDescriptionQuery;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Set;

@ShellComponent
@ShellCommandGroup("Isbn")
@Slf4j
public class IsbnCmd {
    public static final String SEPARATOR_LINE = "-------------------------------------------------\n";
    private final DataProviderDescriptionQuery dataProviderDescriptionQuery;

    public IsbnCmd(DataProviderDescriptionQuery dataProviderDescriptionQuery) {
        this.dataProviderDescriptionQuery = dataProviderDescriptionQuery;
    }

    @ShellMethod(value = "List statitistiques of isbn.", key = "isbn")
    public String isbnStats() {
        return buildStats();
    }

    private String buildStats() {
        StringBuilder stats = new StringBuilder(SEPARATOR_LINE);
        Set<DataDescription> dataDescriptions = this.dataProviderDescriptionQuery.descriptionOfDataProvide();
        for (DataDescription dataDescription : dataDescriptions) {
            stats.append(String.format("Data: %s, size: %d\n", dataDescription.getDataName(), dataDescription.getSizeOf()));
            stats.append(SEPARATOR_LINE);
        }
        return stats.toString();
    }
}
