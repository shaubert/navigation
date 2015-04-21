# Jumper

Simplify intent creation, passing extras, activity animations and provides abstraction over screen flow.

## Gradle
    
    repositories {
        maven{url "https://github.com/shaubert/maven-repo/raw/master/releases"}
    }
    dependencies {
        compile 'com.shaubert.ui.jumper:library:1.0.1'
    }

## How-to

### Step 1

Extend `Jumper` interface and add methods for your activities, for example:
    
    public interface Jumper extends com.shaubert.ui.jumper.Jumper {
        Jump<ImageViewActivity.Args> toFullScreenImage();
    }
    
Where `ImageViewActivity.Args` is activity arguments:
    
    public static class Args extends com.shaubert.ui.jumper.Args {
        private String url;

        public Args(String url) {
            this.url = url;
        }
    }
    
`Args` helps you to convert your non-transient fields into android `Bundle` and vice versa. List of supported types: `Args.Type`. If your jump has no arguments use `VoidArgs`.

### Step 2

Extend from `AbstractJumper` and your version of `Jumper` interface and implement methods, for example:

    public class OpenNewJumper extends AbstractJumper implements MyJumper {
        @Override
        public Jump<ImageViewActivity.Args> toFullScreenImage() {
            return to(ImageViewActivity.class);
        }

        @Override
        <T extends Args> Jump<T> to(Class<?> actClass) {
            return new StartNewActivityJump<>(getStarter(), actClass);
        }
    }
    
`StartNewActivityJump` is a default implementation of `Jump`. You can also check `SingleTopJump`, `ClearTopJump`, `CloseAppJump` and others. You can easily create your own jumps.

### Step 3

Look at `BaseActivity` and copy implementation to your root activity class, or use `BaseActivity` as root. That's all. In your activities and fragments create `Jumper` and use it to navigate from one screen to another, not `Intent`s.

If you like factories also implement `JumperFactory<JUMPER>`.