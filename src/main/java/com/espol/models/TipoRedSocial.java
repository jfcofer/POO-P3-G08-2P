package com.espol.models;

public enum TipoRedSocial {
    // Parametros
    Twitter,
    Facebook,
    Instagram,
    Youtube,
    TikTok,
    LinkedIn,
    Pinterest;

    // Metodo toString
    @Override
    public String toString() {
        switch (this) {
            case Twitter:
                return "Twitter";
            case Facebook:
                return "Facebook";
            case Instagram:
                return "Instagram";
            case Youtube:
                return "Youtube";
            case TikTok:
                return "TikTok";
            case LinkedIn:
                return "LinkedIn";
            case Pinterest:
                return "Pinterest";
            default:
                return "NULL";
        }
    }
}
