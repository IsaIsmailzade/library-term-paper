package library.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BookDto {

    Long id;
    String title;
    String author;
    String description;
    String downloadFb2;
    String downloadEpub;
    String downloadPdf;
    String downloadDocx;
    String downloadMobi;
    String read;
}
