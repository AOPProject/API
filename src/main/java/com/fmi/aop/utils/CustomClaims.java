package com.fmi.aop.utils;

    public enum CustomClaims {
        EMAIL("email"), FIRST_NAME("firstName"), LAST_NAME("lastName");
        private final String text;

        CustomClaims(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

