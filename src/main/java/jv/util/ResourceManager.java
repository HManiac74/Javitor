package jv.util;

import javax.swing.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages loading and caching of application resources (icons, images, etc.).
 * Provides reliable resource loading with fallback mechanisms.
 */
public class ResourceManager {

    private static final Map<String, ImageIcon> iconCache = new HashMap<>();

    /**
     * Loads an icon from the classpath.
     * Returns null if the icon cannot be loaded.
     * 
     * @param path The path to the icon resource (relative to classpath)
     * @return The loaded ImageIcon, or null if loading failed
     */
    public static ImageIcon getIcon(String path) {
        // Check cache first
        if (iconCache.containsKey(path)) {
            return iconCache.get(path);
        }

        try {
            URL resourceUrl = ResourceManager.class.getClassLoader().getResource(path);
            if (resourceUrl != null) {
                ImageIcon icon = new ImageIcon(resourceUrl);
                iconCache.put(path, icon);
                return icon;
            } else {
                System.err.println("Warning: Could not find icon resource: " + path);
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error loading icon from " + path + ": " + e.getMessage());
            return null;
        }
    }

    /**
     * Loads an icon from the classpath with a fallback default.
     * 
     * @param path        The path to the icon resource
     * @param defaultIcon The default icon to return if loading fails
     * @return The loaded ImageIcon, or the default if loading failed
     */
    public static ImageIcon getIconOrDefault(String path, ImageIcon defaultIcon) {
        ImageIcon icon = getIcon(path);
        return icon != null ? icon : defaultIcon;
    }

    /**
     * Clears the icon cache.
     * Useful for freeing memory or forcing reload of resources.
     */
    public static void clearCache() {
        iconCache.clear();
    }

    /**
     * Pre-loads all application icons into the cache.
     * Call this during application startup for better performance.
     */
    public static void preloadIcons() {
        getIcon(Constants.ICON_NEW);
        getIcon(Constants.ICON_OPEN);
        getIcon(Constants.ICON_SAVE);
        getIcon(Constants.ICON_CLOSE);
        getIcon(Constants.ICON_CLEAR);
        getIcon(Constants.ICON_FIND);
        getIcon(Constants.ICON_ABOUT_ME);
        getIcon(Constants.ICON_ABOUT);
    }

    // Private constructor to prevent instantiation
    private ResourceManager() {
        throw new AssertionError("ResourceManager class should not be instantiated");
    }
}
