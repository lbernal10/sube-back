package com.sube.plus.apaseo.sube_back.model.constant;

import java.util.regex.Pattern;

public final class EmailConstants {

    public static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

}
