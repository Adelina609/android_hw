package com.example.hw3_b;

import java.util.Objects;

public class SongOls {

    private String name;
    private int raw;

    public SongOls(String name, int raw) {
        this.name = name;
        this.raw = raw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRaw() {
        return raw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongOls song = (SongOls) o;
        return Objects.equals(name, song.name) &&
                Objects.equals(raw, song.raw);
    }
}
