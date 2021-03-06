package com.easyapps.singerpro.query.model.lyric;

import java.util.Comparator;

/**
 * Query model for Lyric.
 * Created by daniel on 04/10/2016.
 */

public class LyricQueryModel {
    private int order;
    private String name;
    private ConfigurationQueryModel configuration;

    public LyricQueryModel(String name, ConfigurationQueryModel configuration) {
        this.name = name;
        this.configuration = configuration;
        this.order = configuration != null ? configuration.getSongNumber() : 0;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
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

    @Override
    public String toString(){
        return name != null ? name.toLowerCase() : "";
    }
}