package com.easyapps.teleprompter.query.model.lyric;

/**
 * Query model for Lyric.
 * Created by daniel on 04/10/2016.
 */

public class LyricQueryModel {
    private String name;
    private ConfigurationQueryModel configuration;

    public LyricQueryModel(String name, ConfigurationQueryModel configuration) {
        this.name = name;
        this.configuration = configuration;
    }

    public String getName() {
        return name;
    }

    public ConfigurationQueryModel getConfiguration() {
        return configuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LyricQueryModel that = (LyricQueryModel) o;

        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}