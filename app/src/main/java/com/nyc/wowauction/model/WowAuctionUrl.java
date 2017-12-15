package com.nyc.wowauction.model;

/**
 * Created by Wayne Kellman on 12/14/17.
 */

public class WowAuctionUrl {

    private Files[] files;

    public Files[] getFiles() {
        return files;
    }

    public void setFiles(Files[] files) {
        this.files = files;
    }

    public class Files {
        private String url;
        private long lastModified;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public long getLastModified() {
            return lastModified;
        }

        public void setLastModified(long lastModified) {
            this.lastModified = lastModified;
        }
    }
}
