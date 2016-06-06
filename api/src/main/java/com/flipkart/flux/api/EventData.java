/*
 * Copyright 2012-2016, the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.flipkart.flux.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <code>EventData</code> represents the event which would be submitted to flux runtime from inside/outside world.
 * This is useful for data transfer purpose only.
 * @author shyam.akirala
 */
public class EventData {

    /** Name of the event */
    private String name;

    /** Type of the event */
    private String type;

    /** Serialised Data for the event */
    private String data;

    /** Source who generated this event, might be state name or external */
    private String eventSource;

    /** Used by jackson */
    EventData() {}

    /** constructor */
    public EventData(String name, String type, String data, String eventSource) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.eventSource = eventSource;
    }

    /** Accessor/Mutator methods*/
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getEventSource() {
        return eventSource;
    }
    public void setEventSource(String eventSource) {
        this.eventSource = eventSource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventData eventData = (EventData) o;

        if (!name.equals(eventData.name)) return false;
        if (!type.equals(eventData.type)) return false;
        if (data != null ? !data.equals(eventData.data) : eventData.data != null) return false;
        return eventSource.equals(eventData.eventSource);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + eventSource.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "EventData{" +
            "data=" + data +
            ", name='" + name + '\'' +
            ", type='" + type + '\'' +
            ", eventSource='" + eventSource + '\'' +
            '}';
    }

    /**
     * This event data object validates if it is carrying data for the given event definition
     * We should ideally change eventData objects to have eventDefinitions instead of redundant name & type.
     * When we do, only the impl of this method changes
     * @param eventDefinition the event definition we want to check for
     * @return true if this data is corresponding to the given definition, false if not
     */
    @JsonIgnore
    public boolean isFor(EventDefinition eventDefinition) {
        return this.name.equals(eventDefinition.getName()) && this.type.equals(eventDefinition.getType());
    }
}
