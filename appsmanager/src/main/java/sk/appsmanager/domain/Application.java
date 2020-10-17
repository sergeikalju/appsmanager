package sk.appsmanager.domain;

import java.sql.Timestamp;

public class Application {
    private final Integer appCode;
    private final String name;
    private final Integer appGroup;
    private final AppType appType;
    private final String description;
    private final Double appCost;
    private final Timestamp lastModified;

    private Application (Builder builder) {
        this.appCode = builder.appCode;
        this.name = builder.name;
        this.appGroup = builder.appGroup;
        this.appType = builder.appType;
        this.description = builder.description;
        this.appCost = builder.appCost;
        this.lastModified = builder.lastModified;
    }
    
    public Integer getAppCode() {
        return appCode;
    }

    public String getName() {
        return name;
    }

    public Integer getAppGroup() {
        return appGroup;
    }

    public AppType getAppType() {
        return appType;
    }

    public String getDescription() {
        return description;
    }

    public Double getAppCost() {
        return appCost;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public static Builder builder() {
        return new Builder();
    }
    
    @Override
    public String toString() {
        return "Application{"
                + "app code=" + appCode
                + ", app name=" + name
                + ", app group=" + appGroup
                + ", app type=" + appType
                + ", app description=" + description
                + ", app cost=" + appCost
                + ", app last modified=" + lastModified
                + "}";
    }

    public static class Builder {
        private Integer appCode;
        private String name;
        private Integer appGroup;
        private AppType appType;
        private String description;
        private Double appCost;
        private Timestamp lastModified;

        private Builder() {
        }

        public Application build() {
            return new Application(this);
        }
        
        public Builder withAppCode(Integer appCode) {
            this.appCode = appCode;
            return this;
        }
        
        public Builder withName(String name) {
            this.name = name;
            return this;
        }
        
        public Builder withAppGroup(Integer appGroup) {
            this.appGroup = appGroup;
            return this;
        }
        
        public Builder withAppType(AppType appType) {
            this.appType = appType;
            return this;
        }
        
        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }
        
        public Builder withAppCost(Double appCost) {
            this.appCost = appCost;
            return this;
        }
        
        public Builder withLastModified(Timestamp lastModified) {
            this.lastModified = lastModified;
            return this;
        }
    }
}
