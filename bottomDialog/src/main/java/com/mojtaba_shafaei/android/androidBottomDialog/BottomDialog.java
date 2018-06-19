package com.mojtaba_shafaei.android.androidBottomDialog;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.text.SpannableString;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by mojtaba on 8/15/17.
 */

public final class BottomDialog extends android.support.design.widget.BottomSheetDialogFragment {

  private Builder builder;
  private final String TAG = "BottomDialog";

  public static final int LTR = 0;
  public static final int RTL = 1;

  @Retention(RetentionPolicy.SOURCE)
  @Documented
  @IntDef({LTR, RTL})
  public @interface Direction {

  }

  public static final int TRANSPARENT = 0;
  public static final int BLUE = 1;
  public static final int GREY = 2;
  public static final int GREEN = 3;
  public static final int ORANGE = 4;
  public static final int RED = 5;

  @IntDef({TRANSPARENT, BLUE, GREY, GREEN, ORANGE, RED})
  @Retention(RetentionPolicy.SOURCE)
  @Documented
  public @interface ButtonColor {

  }

  public BottomDialog() {

  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View root = inflater
        .inflate(builder.direction == RTL ? R.layout.bottom_dialog_rtl : R.layout.bottom_dialog_ltr,
            container, false);
    TextView title, content;
    Button positiveBtn;
    Button negativeBtn;
    ImageView icon;

    ViewGroup header = root.findViewById(R.id.headerLayout);
    title = root.findViewById(android.R.id.title);
    content = root.findViewById(android.R.id.message);

    positiveBtn = root.findViewById(R.id.btn_positive);
    negativeBtn = root.findViewById(R.id.btn_cancel);
    icon = root.findViewById(R.id.icon);

    if (builder.hiddenHeader) {
      header.setVisibility(View.GONE);
    } else {
      header.setVisibility(View.VISIBLE);

      if (builder.hiddenIcon) {
        icon.setVisibility(View.GONE);
      } else {
        icon.setVisibility(View.VISIBLE);
        if (builder.iconResId != null) {
          icon.setImageDrawable(ContextCompat.getDrawable(getContext(), builder.iconResId));
        } else if (builder.icon != null) {
          icon.setImageDrawable(builder.icon);
        }
      }

      if (builder.titleTextResId != null) {
        title.setText(builder.titleTextResId);
      } else if (builder.titleText != null) {
        title.setText(builder.titleText);
      }

      if (builder.titleTypeFace != null) {
        title.setTypeface(builder.titleTypeFace);
      } else if (builder.defaultTypeface != null) {
        title.setTypeface(builder.defaultTypeface);
      }

      if (builder.titleTextSize != null) {
        title.setTextSize(TypedValue.COMPLEX_UNIT_PX,
            getResources().getDimension(builder.titleTextSize));
      }
    }

    if (builder.contentTypeface != null) {
      content.setTypeface(builder.contentTypeface);
    } else if (builder.defaultTypeface != null) {
      content.setTypeface(builder.defaultTypeface);
    }

    if (builder.contentTextSize != null) {
      content.setTextSize(TypedValue.COMPLEX_UNIT_PX,
          getResources().getDimension(builder.contentTextSize));
    }

    if (builder.contentTextResId != null) {
      content.setText(builder.contentTextResId);
    } else if (builder.contentText != null) {
      content.setText(builder.contentText);
    }

    //<editor-fold desc="positive Button">
    if (builder.positiveTextResId != null) {
      positiveBtn.setText(builder.positiveTextResId);
    } else if (builder.positiveText != null) {
      positiveBtn.setText(builder.positiveText);
    }

    if (builder.positiveTextColorResId != null) {
      positiveBtn
          .setTextColor(ContextCompat.getColor(getContext(), builder.positiveTextColorResId));
    }

    if (builder.positiveButtonBackgroundType != null) {
      positiveBtn.setBackgroundResource(getResourceFor(builder.positiveButtonBackgroundType));
    } else if (builder.positiveBackgroundResId != null) {
      positiveBtn.setBackgroundResource(builder.positiveBackgroundResId);
    }

    positiveBtn.setOnClickListener(view -> {
      dismiss();
      if (builder.positiveButtonCallback != null) {
        builder.positiveButtonCallback.onClick(BottomDialog.this);
      }
    });
    //</editor-fold>

    //<editor-fold desc="Negative Button">
    if (builder.hiddenNegativeButton) {
      negativeBtn.setVisibility(View.GONE);
    } else {
      negativeBtn.setVisibility(View.VISIBLE);

      if (builder.negativeTextResId != null) {
        negativeBtn.setText(builder.negativeTextResId);
      } else if (builder.negativeText != null) {
        negativeBtn.setText(builder.negativeText);
      }

      if (builder.negativeButtonBackgroundType != null) {
        negativeBtn.setBackgroundResource(getResourceFor(builder.negativeButtonBackgroundType));
      } else if (builder.negativeBackgroundResId != null) {
        negativeBtn.setBackgroundResource(builder.negativeBackgroundResId);
      }

      if (builder.negativeTextColorResId != null) {
        negativeBtn
            .setTextColor(ContextCompat.getColor(getContext(), builder.negativeTextColorResId));
      }

      negativeBtn.setOnClickListener(view -> {
        dismiss();
        if (builder.negativeButtonCallback != null) {
          builder.negativeButtonCallback.onClick(BottomDialog.this);
        }
      });
    }
    //</editor-fold>

    setCancelable(builder.cancelable);
    return root;
  }

  @Override
  public void onActivityCreated(Bundle arg0) {
    super.onActivityCreated(arg0);
    Window window = getDialog().getWindow();
    if (window != null) {
      window.setWindowAnimations(R.style.bottom_dialog_DialogAnimation);
    }
  }

  private int getResourceFor(Integer type) {
    switch (type) {
      case TRANSPARENT:
        return R.drawable.bottom_dialog_button_background_transparent;

      case BLUE:
        return R.drawable.bottom_dialog_button_background_blue;

      case GREY:
        return R.drawable.bottom_dialog_button_background_gray;

      case GREEN:
        return R.drawable.bottom_dialog_button_background_green;

      case ORANGE:
        return R.drawable.bottom_dialog_button_background_orange;

      case RED:
        return R.drawable.bottom_dialog_button_background_red;

      default:
        return R.drawable.bottom_dialog_button_background_blue;
    }
  }

  public void setBuilder(Builder builder) {
    this.builder = builder;
  }

  /**
   * @deprecated By Mojtaba. <pre>Please Use {{@link #show(AppCompatActivity)}} Or {@link
   * #show(Fragment)}</pre>
   */
  @Deprecated
  @Override
  public void show(FragmentManager manager, String tag) {
    if (manager.findFragmentByTag(tag) == null) {
      super.show(manager, tag);
    }
  }

  public BottomDialog show(AppCompatActivity activity) {
    show(activity.getSupportFragmentManager(), TAG);
    return this;
  }

  public BottomDialog show(Fragment fragment) {
    if (fragment.getFragmentManager() != null) {
      show(fragment.getFragmentManager(), TAG);
    }
    return this;
  }

  @Override
  public void onPause() {
    super.onPause();
    //dismiss dialog to prevent <<NullPointerException>>
    dismissAllowingStateLoss();
    //

  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private boolean cancelable = true;
    @Direction
    private int direction = LTR;
    private Typeface defaultTypeface;

    //<editor-fold desc="Header">
    private boolean hiddenHeader = false;
    private boolean hiddenIcon = false;
    @DrawableRes
    private Integer iconResId;
    private Drawable icon;
    private Typeface titleTypeFace;
    private String titleText;
    @StringRes
    private Integer titleTextResId;
    @DimenRes
    private Integer titleTextSize;
    //</editor-fold>


    //<editor-fold desc="Content">
    private Typeface contentTypeface;
    private CharSequence contentText;
    @StringRes
    private Integer contentTextResId;
    @DimenRes
    private Integer contentTextSize;

    //</editor-fold>

    private String positiveText, negativeText;
    @StringRes
    private Integer positiveTextResId, negativeTextResId;

    //<editor-fold desc="Background of Positive & Negative Buttons">
    @ColorRes
    private Integer positiveBackgroundResId, negativeBackgroundResId;

    private Integer positiveButtonBackgroundType, negativeButtonBackgroundType;
    //</editor-fold>

    //<editor-fold desc="Color of positive & Negative button text">
    @ColorRes
    private Integer positiveTextColorResId, negativeTextColorResId;
    //</editor-fold>

    private boolean hiddenNegativeButton = false;

    private ButtonCallback positiveButtonCallback, negativeButtonCallback;

    /////////////////////////////////////////////
    /////////// methods
    ////////////////////////////////////////////

    public Builder withDirection(@Direction int direction) {
      this.direction = direction;
      return this;
    }

    public Builder withCancelable(boolean b) {
      this.cancelable = b;
      return this;
    }

    public Builder withHiddenHeader(boolean b) {
      this.hiddenHeader = b;
      return this;
    }

    public Builder withHiddenIcon(boolean isHidden) {
      this.hiddenIcon = isHidden;
      return this;
    }

    public Builder withDefaultTypeface(Typeface typeface) {
      this.defaultTypeface = typeface;
      return this;
    }
    /**/

    //<editor-fold desc="Icon">
    public Builder withIcon(@DrawableRes int drawableRes) {
      this.iconResId = drawableRes;
      return this;
    }

    public Builder withIcon(Drawable drawable) {
      this.icon = drawable;
      return this;
    }
    //</editor-fold>

    /////////////////////////////////////////////

    //<editor-fold desc="TitleText">
    public Builder withTitle(@StringRes int title, Typeface typeface, @DimenRes int textSize) {
      this.titleTextResId = title;
      this.titleTypeFace = typeface;
      this.titleTextSize = textSize;
      return this;
    }

    public Builder withTitle(String title, Typeface typeface, @DimenRes int textSize) {
      this.titleText = title;
      this.titleTypeFace = typeface;
      this.titleTextSize = textSize;
      return this;
    }

    /**/

    public Builder withTitle(@StringRes int title, Typeface typeface) {
      this.titleTextResId = title;
      this.titleTypeFace = typeface;
      return this;
    }

    public Builder withTitle(String title, Typeface typeface) {
      this.titleText = title;
      this.titleTypeFace = typeface;
      return this;
    }

    /**/

    public Builder withTitle(@StringRes int title, @DimenRes int textSize) {
      this.titleTextResId = title;
      this.titleTextSize = textSize;
      return this;
    }

    public Builder withTitle(String title, @DimenRes int textSize) {
      this.titleText = title;
      this.titleTextSize = textSize;
      return this;
    }

    /**/

    public Builder withTitle(@StringRes int title) {
      this.titleTextResId = title;
      return this;
    }

    public Builder withTitle(String title) {
      this.titleText = title;
      return this;
    }
    //</editor-fold>

    /////////////////////////////////////////////

    //<editor-fold desc="ContentText">

    public Builder withContent(@StringRes int content, Typeface typeface, @DimenRes int textSize) {
      this.contentTextResId = content;
      this.contentTypeface = typeface;
      this.contentTextSize = textSize;
      return this;
    }

    public Builder withContent(CharSequence content, Typeface typeface, @DimenRes int textSize) {
      this.contentText = content;
      this.contentTypeface = typeface;
      this.contentTextSize = textSize;
      return this;
    }


    public Builder withContent(@StringRes int content, @DimenRes int textSize) {
      this.contentTextResId = content;
      this.contentTextSize = textSize;
      return this;
    }

    public Builder withContent(CharSequence content, @DimenRes int textSize) {
      this.contentText = content;
      this.contentTextSize = textSize;
      return this;
    }


    public Builder withContent(@StringRes int content, Typeface typeface) {
      this.contentTextResId = content;
      this.contentTypeface = typeface;
      return this;
    }

    public Builder withContent(CharSequence content, Typeface typeface) {
      this.contentText = content;
      this.contentTypeface = typeface;
      return this;
    }


    public Builder withContent(@StringRes int content) {
      this.contentTextResId = content;
      return this;
    }

    public Builder withContent(CharSequence content) {
      this.contentText = content;
      return this;
    }

    //</editor-fold>

    /////////////////////////////////////////////

    //<editor-fold desc="PositiveText">

    public Builder withPositiveText(@StringRes int positiveText, @ColorRes int colorRes) {
      this.positiveTextResId = positiveText;
      this.positiveTextColorResId = colorRes;
      return this;
    }

    public Builder withPositiveText(@StringRes int positiveText) {
      this.positiveTextResId = positiveText;
      return this;
    }

    public Builder withPositiveText(String positiveText, @ColorRes int colorRes) {
      this.positiveText = positiveText;
      this.positiveTextColorResId = colorRes;
      return this;
    }

    public Builder withPositiveText(String positiveText) {
      this.positiveText = positiveText;
      return this;
    }
    //</editor-fold>

    /////////////////////////////////////////////

    //<editor-fold desc="NegativeText">
    public Builder withNegativeText(@StringRes int negativeText, @ColorRes int colorRes) {
      this.negativeTextResId = negativeText;
      this.negativeTextColorResId = colorRes;
      return this;
    }

    public Builder withNegativeText(String negativeText, @ColorRes int colorRes) {
      this.negativeText = negativeText;
      this.negativeTextColorResId = colorRes;
      return this;
    }

    public Builder withNegativeText(@StringRes int negativeText) {
      this.negativeTextResId = negativeText;
      return this;
    }

    public Builder withNegativeText(String negativeText) {
      this.negativeText = negativeText;
      return this;
    }
    //</editor-fold>

    /////////////////////////////////////////////

    //<editor-fold desc="PositiveBackground">
    public Builder withPositiveBackgroundType(@ButtonColor int backgroundType) {
      this.positiveButtonBackgroundType = backgroundType;
      return this;
    }

    public Builder withPositiveBackground(@DrawableRes int drawableRes) {
      this.positiveBackgroundResId = drawableRes;
      return this;
    }

    //</editor-fold>

    /////////////////////////////////////////////

    //<editor-fold desc="NegativeBackground">
    public Builder withNegativeBackgroundType(@ButtonColor int backgroundType) {
      this.negativeButtonBackgroundType = backgroundType;
      return this;
    }

    public Builder withNegativeBackground(@DrawableRes int drawableRes) {
      this.negativeBackgroundResId = drawableRes;
      return this;
    }

    //</editor-fold>

    /////////////////////////////////////////////

    //</editor-fold>

    /////////////////////////////////////////////


    public Builder withOnPositive(ButtonCallback callback) {
      this.positiveButtonCallback = callback;
      return this;
    }

    public Builder withOnNegative(ButtonCallback callback) {
      this.negativeButtonCallback = callback;
      return this;
    }

    public Builder withHiddenNegativeButton(boolean isHidden) {
      this.hiddenNegativeButton = isHidden;
      return this;
    }


    public BottomDialog build() {
      BottomDialog d = new BottomDialog();
      d.setBuilder(this);
      return d;
    }
  }

  public interface ButtonCallback {

    void onClick(BottomDialog var1);
  }

}
