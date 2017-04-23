package com.detailservice.model;

import java.io.Serializable;

public class RandomProfile implements Serializable {
    public class Results {
        public class Name {
            private String title;
            private String first;
            private String last;

            public String getTitle() {
                return title;
            }

            public String getFirst() {
                return first;
            }

            public String getLast() {
                return last;
            }
        }

        private Name name;
        private String email;

        public Name getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }

    private Results[] results;

    public Results[] getResults() {
        return results;
    }

    class Info {
        private String seed;
        private String results;
        private String page;
        private String version;
    }

    RandomProfile.Info info;
}

