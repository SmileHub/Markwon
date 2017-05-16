package ru.noties.markwon.renderer;

import android.content.Context;
import android.support.annotation.NonNull;

import ru.noties.markwon.spans.AsyncDrawable;
import ru.noties.markwon.spans.LinkSpan;
import ru.noties.markwon.spans.SpannableTheme;

public class SpannableConfiguration {

    // creates default configuration
    public static SpannableConfiguration create(@NonNull Context context) {
        return new Builder(context).build();
    }

    public static Builder builder(@NonNull Context context) {
        return new Builder(context);
    }

    private final SpannableTheme theme;
    private final AsyncDrawable.Loader asyncDrawableLoader;
    private final SyntaxHighlight syntaxHighlight;
    private final LinkSpan.Resolver linkResolver;

    private SpannableConfiguration(Builder builder) {
        this.theme = builder.theme;
        this.asyncDrawableLoader = builder.asyncDrawableLoader;
        this.syntaxHighlight = builder.syntaxHighlight;
        this.linkResolver = builder.linkResolver;
    }

    public SpannableTheme theme() {
        return theme;
    }

    public AsyncDrawable.Loader asyncDrawableLoader() {
        return asyncDrawableLoader;
    }

    public SyntaxHighlight syntaxHighlight() {
        return syntaxHighlight;
    }

    public LinkSpan.Resolver linkResolver() {
        return linkResolver;
    }

    public static class Builder {

        private final Context context;
        private SpannableTheme theme;
        private AsyncDrawable.Loader asyncDrawableLoader;
        private SyntaxHighlight syntaxHighlight;
        private LinkSpan.Resolver linkResolver;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder theme(SpannableTheme theme) {
            this.theme = theme;
            return this;
        }

        public Builder asyncDrawableLoader(AsyncDrawable.Loader asyncDrawableLoader) {
            this.asyncDrawableLoader = asyncDrawableLoader;
            return this;
        }

        public Builder syntaxHighlight(SyntaxHighlight syntaxHighlight) {
            this.syntaxHighlight = syntaxHighlight;
            return this;
        }

        public Builder linkResolver(LinkSpan.Resolver linkResolver) {
            this.linkResolver = linkResolver;
            return this;
        }

        public SpannableConfiguration build() {
            if (theme == null) {
                theme = SpannableTheme.create(context);
            }
            if (asyncDrawableLoader == null) {
                asyncDrawableLoader = new AsyncDrawableLoaderNoOp();
            }
            if (syntaxHighlight == null) {
                syntaxHighlight = new SyntaxHighlightNoOp();
            }
            if (linkResolver == null) {
                linkResolver = new LinkResolverDef();
            }
            return new SpannableConfiguration(this);
        }
    }

}
