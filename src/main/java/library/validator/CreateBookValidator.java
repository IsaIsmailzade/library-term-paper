package library.validator;

import library.dto.CreateBookDto;
import lombok.Getter;

public class CreateBookValidator implements Validator<CreateBookDto> {

    @Getter
    private static final CreateBookValidator INSTANCE = new CreateBookValidator();
    private static final String LINK_PATTERN = "^\\d+$";

    @Override
    public ValidationResult isValid(CreateBookDto object) {
        ValidationResult validationResult = new ValidationResult();

        if (object.getTitle() == null || object.getTitle().isBlank()) {
            validationResult.add(Error.of("invalid.title", "Title cannot be empty."));
        }
        if (object.getAuthor() == null || object.getAuthor().isBlank()) {
            validationResult.add(Error.of("invalid.author", "Author cannot be empty."));
        }

        if (object.getDownloadFb2().matches(LINK_PATTERN)) {
            validationResult.add(Error.of("invalid.url", "Invalid FB2 download link."));
        }
        if (object.getDownloadEpub().matches(LINK_PATTERN)) {
            validationResult.add(Error.of("invalid.url", "Invalid EPUB download link."));
        }
        if (object.getDownloadPdf().matches(LINK_PATTERN)) {
            validationResult.add(Error.of("invalid.url", "Invalid PDF download link."));
        }
        if (object.getDownloadDocx().matches(LINK_PATTERN)) {
            validationResult.add(Error.of("invalid.url", "Invalid Word download link."));
        }
        if (object.getDownloadMobi().matches(LINK_PATTERN)) {
            validationResult.add(Error.of("invalid.url", "Invalid MOBI download link."));
        }

        if (object.getDownloadMobi().matches(LINK_PATTERN)) {
            validationResult.add(Error.of("invalid.url", "Invalid link for reading."));
        }
        return validationResult;
    }
}
