package com.invillia.poc01.exceptions.details;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MainAddressDetails {

    private String title;
    private int status;
    private LocalDateTime timestamp;
    private String message;

    private MainAddressDetails() {
    }


    public static final class MainAddressDetailsBuilder {
        private String title;
        private int status;
        private LocalDateTime timestamp;
        private String message;

        private MainAddressDetailsBuilder() {
        }

        public static MainAddressDetailsBuilder newBuilder() {
            return new MainAddressDetailsBuilder();
        }

        public MainAddressDetailsBuilder title(String title) {
            this.title = title;
            return this;
        }

        public MainAddressDetailsBuilder status(int status) {
            this.status = status;
            return this;
        }

        public MainAddressDetailsBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public MainAddressDetailsBuilder message(String message) {
            this.message = message;
            return this;
        }

        public MainAddressDetails build() {
            MainAddressDetails mainAddressDetails = new MainAddressDetails();
            mainAddressDetails.message = this.message;
            mainAddressDetails.timestamp = this.timestamp;
            mainAddressDetails.title = this.title;
            mainAddressDetails.status = this.status;
            return mainAddressDetails;
        }
    }
}
