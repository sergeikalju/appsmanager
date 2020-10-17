package sk.appsmanager.controller.validator;

import java.util.Arrays;
import java.util.EnumSet;

import sk.appsmanager.domain.AppType;
import sk.appsmanager.domain.ServiceSubType;
import sk.appsmanager.domain.ServiceType;

@SuppressWarnings("serial")
public class ControllerValidator extends RuntimeException {

    public void validateGroup(String group) {
        if (!group.isEmpty()) {
            try {
                Integer.parseInt(group);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Group must be integer.");
            }
        }
    }

    public void validateCost(String cost) {
        if (!cost.isEmpty()) {
            try {
                Double.parseDouble(cost);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Cost must be double.");
            }
        }
    }

    public void validateAppCode(String appCode) {
        if (!appCode.isEmpty()) {
            try {
                Integer.parseInt(appCode);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("App code must be integer.");
            }
        }
    }

    public void validateType(String type) {
        try {
            EnumSet.allOf(AppType.class).contains(AppType.valueOf(type));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("App type must be equal to one of the values: " 
                    + Arrays.toString(AppType.values()));
        }
    }

    public void validateServiceType(String type) {
        try {
            EnumSet.allOf(ServiceType.class).contains(ServiceType.valueOf(type));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Service type must be equal to one of the values: " 
                    + Arrays.toString(ServiceType.values()));
        }
    }

    public void validateServiceSubType(String subType) {
        try {
            EnumSet.allOf(ServiceSubType.class).contains(ServiceSubType.valueOf(subType));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Service sub type must be equal to one of the values: " 
                    + Arrays.toString(ServiceSubType.values()));
        }
    }
}
