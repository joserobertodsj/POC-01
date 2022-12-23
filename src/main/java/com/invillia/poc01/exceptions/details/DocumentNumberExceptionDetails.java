package com.invillia.poc01.exceptions.details;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DocumentNumberExceptionDetails {

    private String title;
    private int status;
    private LocalDateTime timestamp;
    private String message;

    private DocumentNumberExceptionDetails() {
    }


    public static final class DocumentNumberExceptionDetailsBuilder {
        private String title;
        private int status;
        private LocalDateTime timestamp;
        private String message;

        private DocumentNumberExceptionDetailsBuilder() {
        }

        public static DocumentNumberExceptionDetailsBuilder newBuilder() {
            return new DocumentNumberExceptionDetailsBuilder();
        }

        public DocumentNumberExceptionDetailsBuilder title(String title) {
            this.title = title;
            return this;
        }

        public DocumentNumberExceptionDetailsBuilder status(int status) {
            this.status = status;
            return this;
        }

        public DocumentNumberExceptionDetailsBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public DocumentNumberExceptionDetailsBuilder message(String message) {
            this.message = message;
            return this;
        }

        public DocumentNumberExceptionDetails build() {
            DocumentNumberExceptionDetails documentNumberExceptionDetails = new DocumentNumberExceptionDetails();
            documentNumberExceptionDetails.status = this.status;
            documentNumberExceptionDetails.message = this.message;
            documentNumberExceptionDetails.timestamp = this.timestamp;
            documentNumberExceptionDetails.title = this.title;
            return documentNumberExceptionDetails;
        }
    }
}
