package com.isharipov.simplemediaapp.news.model.source;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 24.06.2018.
 */
public class SourceResponse implements Serializable {
    private String status;
    private List<Source> sources;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceResponse that = (SourceResponse) o;
        return Objects.equals(status, that.status) &&
                Objects.equals(sources, that.sources);
    }

    @Override
    public int hashCode() {

        return Objects.hash(status, sources);
    }

}
