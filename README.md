# Navigation

Simplify intent creation, passing extras, activity animations and provides abstraction over screen flow.

## How-to

### Step 1

Extend `NavigationController` interface and add methods for your activities, for example:
    
    NavigationMethod<ImageViewActivity.ImageActivityBundle> getImageActivity();
    
Where `ImageViewActivity.ImageActivityBundle` is activity arguments:
    
    public static class ImageActivityBundle extends Bundler {
        private String url;

        public ImageActivityBundle(String url) {
            this.url = url;
        }
    }
    
`Bundler` helps you to convert your non-transient fields into android `Bundle` and vice versa. List of supported types: `Bundler.Type`.

### Step 2

Extend from `OpenNewNavigationController` and your version of `NavigationController` and implement methods, for example:
    
    @Override
    public NavigationMethod<ImageViewActivity.ImageActivityBundle> getImageActivity() {
        return new OpenNewNavigationMethod<>(activityStarter, ImageViewActivity.class);
    }
    
`OpenNewNavigationMethod` is a default implementation of `NavigationMethod`. You can also check `SingleTopNavigationMethod`, `ClearTopNavigationMethod`, `CloseAppNavigationMethod` and others. You can easily create your own methods.

### Step 3

Look at `BaseActivity` and copy implementation to your root activity class, or use `BaseActivity` as root. That's all. In your acitivities and fragments create `NavigationController` and use it to navigate from one screen to another, not `Intents`. 

If you like factories also implement `NavigationControllerFactory`.