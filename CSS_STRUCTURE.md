# CSS Structure Documentation

This document describes the organized CSS structure for the Spring Boot Chat Server application.

## CSS File Organization

### 📁 `/src/main/resources/static/css/`

#### 🌐 **general.css** - Base styles used across all pages
- Reset and base HTML styles
- Typography (headings, paragraphs, links)
- Common components (buttons, forms, cards)
- Utility classes (margins, padding, text alignment)
- Status messages (success, error, info)
- Mobile responsive utilities
- Animation utilities

#### 🧭 **navigation.css** - Top navigation component styles
- Fixed navigation bar styling
- Brand/logo styling
- Navigation links and hover effects
- Mobile navigation responsive design

#### 🏠 **index.css** - Homepage specific styles
- Hero section with interactive background
- Animated canvas and particles
- Gradient wave animations
- CTA buttons styling
- Features section grid
- Interactive glow effects

#### 💬 **chat.css** - Chat page specific styles
- Chat container layout
- Message bubbles and styling
- Input area with mobile optimizations
- iOS Safari compatibility fixes
- Real-time chat interface

#### ⚙️ **settings.css** - Settings page specific styles
- Settings form containers
- Input field styling
- Button groups and actions
- Status message styling

#### ℹ️ **aboutus.css** - About page specific styles
- Content container layout
- Contact information styling
- GitHub link styling
- QR code section

#### 🧪 **test.css** - Test page specific styles
- Device information display
- Install button styling
- iframe grid layout
- Test status indicators

## HTML Template Updates

All HTML templates have been updated to:
1. Include proper viewport meta tags
2. Link to external CSS files in order: `general.css` → `navigation.css` → `page-specific.css`
3. Use semantic class names instead of inline styles
4. Include proper content wrappers for fixed navigation

## CSS Loading Order

```html
<link rel="stylesheet" href="/css/general.css" />     <!-- Base styles -->
<link rel="stylesheet" href="/css/navigation.css" />  <!-- Navigation -->
<link rel="stylesheet" href="/css/[page].css" />      <!-- Page-specific -->
```

## Benefits of This Structure

1. **Maintainability**: Each CSS file has a clear purpose
2. **Performance**: Shared styles are cached across pages
3. **Scalability**: Easy to add new pages with specific styles
4. **Organization**: Clear separation of concerns
5. **Reusability**: Common components can be reused
6. **Mobile-First**: Responsive design throughout

## CSS Class Naming Conventions

- **Component-based**: `.nav-link`, `.feature-card`, `.chat-container`
- **BEM-inspired**: `.setting-container`, `.qr-section`, `.hero-content`
- **Utility classes**: `.text-center`, `.mt-3`, `.btn-primary`
- **State classes**: `.success`, `.error`, `.fade-in`

## Responsive Design

All CSS files include mobile-responsive design with breakpoints:
- Desktop: > 768px
- Mobile: ≤ 768px
- Small mobile: ≤ 480px

## Browser Compatibility

- Modern browsers (Chrome, Firefox, Safari, Edge)
- iOS Safari specific fixes included
- CSS Grid and Flexbox used throughout
- Fallbacks for older browser support
