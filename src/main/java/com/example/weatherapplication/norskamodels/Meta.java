
package com.example.weatherapplication.norskamodels;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.example.weatherapplication.norskamodels.Units;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "updated_at",
    "units"
})
@Generated("jsonschema2pojo")
public class Meta {

    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("units")
    private Units units;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("units")
    public Units getUnits() {
        return units;
    }

    @JsonProperty("units")
    public void setUnits(Units units) {
        this.units = units;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
