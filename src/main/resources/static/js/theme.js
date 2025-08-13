/* ===== THEME MANAGEMENT SCRIPT ===== */

(function () {
    'use strict';

    // Theme Management Functions
    function loadTheme() {
        const savedTheme = localStorage.getItem('theme') || 'light';
        applyTheme(savedTheme);
    }

    function applyTheme(theme) {
        document.documentElement.setAttribute('data-theme', theme);

        // Dispatch theme change event for other components
        window.dispatchEvent(new CustomEvent('themeChanged', {
            detail: { theme: theme }
        }));
    }

    function toggleTheme() {
        const currentTheme = document.documentElement.getAttribute('data-theme') || 'light';
        const newTheme = currentTheme === 'light' ? 'dark' : 'light';

        applyTheme(newTheme);
        localStorage.setItem('theme', newTheme);

        return newTheme;
    }

    // Load theme immediately to prevent flash
    loadTheme();

    // Load theme when DOM is ready
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', loadTheme);
    } else {
        loadTheme();
    }

    // Export functions to global scope
    window.ThemeManager = {
        loadTheme: loadTheme,
        applyTheme: applyTheme,
        toggleTheme: toggleTheme,
        getCurrentTheme: function () {
            return document.documentElement.getAttribute('data-theme') || 'light';
        }
    };

})();
