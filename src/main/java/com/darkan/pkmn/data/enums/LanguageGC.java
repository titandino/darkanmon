package com.darkan.pkmn.data.enums;

public enum LanguageGC {
    /// Undefined Language ID, usually indicative of a value not being set.
    /// Gen5 Japanese In-game Trades happen to not have their Language value set, and express Language=0.
    Hacked,

    /// Japanese
    Japanese,

    /// English (US/UK/AU)
    English,

    /// German (Deutsch)
    German,

    /// French (Français)
    French,

    /// Italian (Italiano)
    Italian,

    /// Spanish (Español)
    Spanish,

    /// Unused Language ID
    /// Was reserved for Korean in Gen3 but never utilized.
    UNUSED_6;
}
