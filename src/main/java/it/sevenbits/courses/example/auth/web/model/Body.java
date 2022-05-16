package it.sevenbits.courses.example.auth.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Body {
    @JsonProperty("files")
    private final byte[] files;

    @JsonCreator
    public Body(byte[] files) {
        this.files = files;
    }

    public byte[] getFiles() {
        return files;
    }
}
