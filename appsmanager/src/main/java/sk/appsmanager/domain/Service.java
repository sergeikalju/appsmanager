package sk.appsmanager.domain;

import java.sql.Timestamp;

public class Service {
    private final Integer appCode;
    private final Integer serviceCode;
    private final String name;
    private final ServiceType type;
    private final ServiceSubType subType;
    private final String description;
    private final Timestamp lastModified;

    private Service (Builder builder) {
        this.appCode = builder.appCode;
        this.serviceCode = builder.serviceCode;
        this.name = builder.name;
        this.type = builder.type;
        this.subType = builder.subType;
        this.description = builder.description;
        this.lastModified = builder.lastModified;
    }

    public Integer getAppCode() {
        return appCode;
    }

    public Integer getServiceCode() {
        return serviceCode;
    }

    public String getName() {
        return name;
    }

    public ServiceType getType() {
        return type;
    }

    public ServiceSubType getSubType() {
        return subType;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public static Builder builder() {
        return new Builder();
    }
    
    @Override
    public String toString() {
        return "Service{"
                + "app code=" + appCode
                + ", service code=" + serviceCode
                + ", service name=" + name
                + ", service type=" + type
                + ", service sub type=" + subType
                + ", service description=" + description
                + ", service last modified=" + lastModified
                + "}";
    }

    public static class Builder {
        private Integer appCode;
        private Integer serviceCode;
        private String name;
        private ServiceType type;
        private ServiceSubType subType;
        private String description;
        private Timestamp lastModified;

        private Builder() {
        }

        public Service build() {
            return new Service(this);
        }
        
        public Builder withAppCode(Integer appCode) {
            this.appCode = appCode;
            return this;
        }
        
        public Builder withServiceCode(Integer serviceCode) {
            this.serviceCode = serviceCode;
            return this;
        }
        
        public Builder withName(String name) {
            this.name = name;
            return this;
        }
        
        public Builder withType(ServiceType type) {
            this.type = type;
            return this;
        }
        
        public Builder withSubType(ServiceSubType subType) {
            this.subType = subType;
            return this;
        }
        
        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }
        
        public Builder withLastModified(Timestamp lastModified) {
            this.lastModified = lastModified;
            return this;
        }
    }
}
