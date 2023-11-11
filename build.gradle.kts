// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugin.application) version Plugin.Version.application apply false
    id(Plugin.library) version Plugin.Version.library apply false
    id(Plugin.kotlin) version Plugin.Version.kotlin apply false
    id(Plugin.hilt) version Plugin.Version.hilt apply false
}