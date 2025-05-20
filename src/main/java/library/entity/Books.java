package library.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Books {

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
