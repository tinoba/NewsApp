package eu.fiveminutes.newsapp.util;

import android.content.res.Resources;
import android.support.annotation.StringRes;

public final class ResourceUtilsImpl implements ResourceUtils {

    private final Resources resources;

    public ResourceUtilsImpl(Resources resources) {
        this.resources = resources;
    }

    @Override
    public String getString(@StringRes int resourceId) {
        return resources.getString(resourceId);
    }
}
