package com.invillia.poc01.exceptions.details;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MaxLimitDetails {
    private String title;
    private int status;
    private LocalDateTime timestamp;
    private String message;


    private MaxLimitDetails() {
    }


    public static final class MaxLimitDetailsBuilder {
        private String title;
        private int status;
        private LocalDateTime timestamp;
        private String message;

        private MaxLimitDetailsBuilder() {
        }

        public static MaxLimitDetailsBuilder newBuilder() {
            return new MaxLimitDetailsBuilder();
        }

        public MaxLimitDetailsBuilder title(String title) {
            this.title = title;
            return this;
        }

        public MaxLimitDetailsBuilder status(int status) {
            this.status = status;
            return this;
        }

        public MaxLimitDetailsBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public MaxLimitDetailsBuilder message(String message) {
            this.message = message;
            return this;
        }

        public MaxLimitDetails build() {
            MaxLimitDetails maxLimitDetails = new MaxLimitDetails();
            maxLimitDetails.timestamp = this.timestamp;
            maxLimitDetails.title = this.title;
            maxLimitDetails.status = this.status;
            maxLimitDetails.message = this.message;
            return maxLimitDetails;
        }
    }
}
