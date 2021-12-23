//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package com.xsjt.core.jackson;

import com.fasterxml.jackson.core.FormatFeature;
import com.fasterxml.jackson.core.JsonParser.Feature;

public enum JsonReadFeature implements FormatFeature {
    ALLOW_JAVA_COMMENTS(false, Feature.ALLOW_COMMENTS),
    ALLOW_YAML_COMMENTS(false, Feature.ALLOW_YAML_COMMENTS),
    ALLOW_SINGLE_QUOTES(false, Feature.ALLOW_SINGLE_QUOTES),
    ALLOW_UNQUOTED_FIELD_NAMES(false, Feature.ALLOW_UNQUOTED_FIELD_NAMES),
    ALLOW_UNESCAPED_CONTROL_CHARS(false, Feature.ALLOW_UNQUOTED_CONTROL_CHARS),
    ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER(false, Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER),
    ALLOW_LEADING_ZEROS_FOR_NUMBERS(false, Feature.ALLOW_NUMERIC_LEADING_ZEROS),
    ALLOW_NON_NUMERIC_NUMBERS(false, Feature.ALLOW_NON_NUMERIC_NUMBERS),
    ALLOW_MISSING_VALUES(false, Feature.ALLOW_MISSING_VALUES),
    ALLOW_TRAILING_COMMA(false, Feature.ALLOW_TRAILING_COMMA);

    private final boolean _defaultState;
    private final int _mask;
    private final Feature _mappedFeature;

    public static int collectDefaults() {
        int flags = 0;
        JsonReadFeature[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            JsonReadFeature f = var1[var3];
            if (f.enabledByDefault()) {
                flags |= f.getMask();
            }
        }

        return flags;
    }

    private JsonReadFeature(boolean defaultState, Feature mapTo) {
        this._defaultState = defaultState;
        this._mask = 1 << this.ordinal();
        this._mappedFeature = mapTo;
    }

    public boolean enabledByDefault() {
        return this._defaultState;
    }

    public int getMask() {
        return this._mask;
    }

    public boolean enabledIn(int flags) {
        return (flags & this._mask) != 0;
    }

    public Feature mappedFeature() {
        return this._mappedFeature;
    }
}
