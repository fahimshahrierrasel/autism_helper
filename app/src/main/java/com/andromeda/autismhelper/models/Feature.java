package com.andromeda.autismhelper.models;

import org.jetbrains.annotations.Nullable;

/**
 * Created by fahim on 22/11/17.
 */

public class Feature {
    @Nullable
    private String featureName;
    private int featureImage;
    @Nullable
    private Class activityClass;

    @Nullable
    public final String getFeatureName() {
        return this.featureName;
    }

    public final void setFeatureName(@Nullable String var1) {
        this.featureName = var1;
    }

    public final int getFeatureImage() {
        return this.featureImage;
    }

    public final void setFeatureImage(int var1) {
        this.featureImage = var1;
    }

    @Nullable
    public final Class getActivityClass() {
        return this.activityClass;
    }

    public final void setActivityClass(@Nullable Class var1) {
        this.activityClass = var1;
    }

    public Feature(@Nullable String featureName, int featureImage, @Nullable Class activityClass) {
        this.featureName = featureName;
        this.featureImage = featureImage;
        this.activityClass = activityClass;
    }
}