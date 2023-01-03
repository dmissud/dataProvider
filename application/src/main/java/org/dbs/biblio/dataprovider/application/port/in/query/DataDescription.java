package org.dbs.biblio.dataprovider.application.port.in.query;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DataDescription {
    String dataName;
    long sizeOf;
}
