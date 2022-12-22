package com.invillia.poc01.exceptions.details;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EmailExceptionDetails {

    private String title;
    private int status;
    private LocalDateTime timestamp;
    private String message;

    private EmailExceptionDetails() {

    }


    public static final class EmailExceptionDetailsBuilder {
        private String title;
        private int status;
        private LocalDateTime timestamp;
        private String message;

        private EmailExceptionDetailsBuilder() {
        }

        public static EmailExceptionDetailsBuilder newBuilder() {
            return new EmailExceptionDetailsBuilder();
        }

        public EmailExceptionDetailsBuilder title(String title) {
            this.title = title;
            return this;
        }

        public EmailExceptionDetailsBuilder status(int status) {
            this.status = status;
            return this;
        }

        public EmailExceptionDetailsBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public EmailExceptionDetailsBuilder message(String message) {
            this.message = message;
            return this;
        }

        public EmailExceptionDetails build() {
            EmailExceptionDetails emailExceptionDetails = new EmailExceptionDetails();
            emailExceptionDetails.timestamp = this.timestamp;
            emailExceptionDetails.message = this.message;
            emailExceptionDetails.title = this.title;
            emailExceptionDetails.status = this.status;
            return emailExceptionDetails;
        }
    }
}
