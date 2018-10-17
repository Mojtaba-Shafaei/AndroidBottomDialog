package com.mojtaba_shafaei.android.android_bottom_dialog_example;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import com.mojtaba_shafaei.android.androidBottomDialog.BottomDialog;

public class MainActivity extends AppCompatActivity{

private String TAG = MainActivity.class.getSimpleName();

@Override
protected void onCreate(Bundle savedInstanceState){
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  final Typeface typeface = Typeface.createFromAsset(getAssets(), "AlfaSlabOne-Regular.ttf");
  findViewById(R.id.btn_menu_more).setOnClickListener(view -> {
    Intent intent = new Intent(MainActivity.this, InfoActivity.class);
    startActivity(intent);
  });

  findViewById(R.id.btn_show_menu_rtl)
      .setOnClickListener(view -> {
        try{
//            SpannableString spannableString = new SpannableString("آیا کاربر … را حذف میکنید؟");
          SpannableString spannableString = new SpannableString(
              "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ac tincidunt vitae semper quis lectus nulla. Massa sapien faucibus et molestie ac feugiat sed lectus. Volutpat ac tincidunt vitae semper quis lectus nulla at. Nulla malesuada pellentesque elit eget gravida cum sociis natoque penatibus.");
          spannableString
              .setSpan(new CustomTypefaceSpan(typeface), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
          spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, 7,
                                  Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

          BottomDialog.builder()
              .withDirection(BottomDialog.RTL)
              .withHiddenHeader(false)
              .withCancelable(true)
              .withTitle("هشدار", typeface)
              .withIcon(R.drawable.ic_error_outline_red_700_24dp)
              .withContent(spannableString)
              .withHiddenNegativeButton(false)
              .withNegativeText("انصراف")
              .withPositiveText("بله.حذف میکنم.")
              .withDefaultTypeface(typeface)
              .withDialogInterface(new DialogInterface(){
                @Override
                public void cancel(){
                  Log.d(TAG, "cancelled: ");
                }

                @Override
                public void dismiss(){
                  Log.d(TAG, "dismissed: ");
                }
              })
              .build()
              .show(MainActivity.this);
        } catch(Exception e){
          Log.e(TAG, "onCreate: ", e);
        }
      });

  findViewById(R.id.btn_show_menu_rtl2)
      .setOnClickListener(view -> {
        try{
//            SpannableString spannableString = new SpannableString("آیا کاربر … را حذف میکنید؟");
          SpannableString spannableString = new SpannableString(
              "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ac tincidunt vitae semper quis lectus nulla. Massa sapien faucibus et molestie ac feugiat sed lectus. Volutpat ac tincidunt vitae semper quis lectus nulla at. Nulla malesuada pellentesque elit eget gravida cum sociis natoque penatibus.");
          spannableString
              .setSpan(new CustomTypefaceSpan(typeface), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
          spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, 7,
                                  Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

          SpannableString spanBuilderCancel = SpannableString.valueOf("انصراف");
          spanBuilderCancel.setSpan(new ForegroundColorSpan(0xFFD32F2F), 0, 5,
                                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

          SpannableString spanBuilderPositive = new SpannableString(
              "بله.حذف میکنم.");
          spanBuilderPositive.setSpan(new ForegroundColorSpan(0xFF00FF00), 0, 14,
                                      Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

          BottomDialog.builder()
              .withDirection(BottomDialog.RTL)
              .withHiddenHeader(false)
              .withCancelable(true)
              .withTitle("هشدار", typeface)
              .withIcon(R.drawable.ic_error_outline_red_700_24dp)
              .withContent(spannableString)
              .withHiddenNegativeButton(false)
              .withNegativeText(spanBuilderCancel)
              .withPositiveText(spanBuilderPositive)
              .withDefaultTypeface(typeface)
              .withDialogInterface(new DialogInterface(){
                @Override
                public void cancel(){
                  Log.d(TAG, "cancelled: ");
                }

                @Override
                public void dismiss(){
                  Log.d(TAG, "dismissed: ");
                }
              })
              .build()
              .show(MainActivity.this);
        } catch(Exception e){
          Log.e(TAG, "onCreate: ", e);
        }
      });

  findViewById(R.id.btn_show_menu_ltr)
      .setOnClickListener(view -> {
        try{
          BottomDialog bd = BottomDialog.builder()
              .withIcon(R.drawable.ic_error_outline_red_700_24dp)

              .withTitle("Warning", typeface, R.dimen.titleTextSize)
              .withContent("Do you remove this ... ?", typeface, R.dimen.contentTextSize)

              .withPositiveText("Yes.Remove it.", android.R.color.white)

              .withNegativeText("Cancel", R.color.bottom_dialog_textSecondaryColor)
              .build()
              .show(MainActivity.this);
        } catch(Exception e){
          Log.e(TAG, "onCreate: ", e);
        }
      });
}

}
