package com.mojtaba_shafaei.android.android_bottom_dialog_example;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mojtaba_shafaei.android.androidBottomDialog.BottomDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "AlfaSlabOne-Regular.ttf");
        findViewById(R.id.btn_menu_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_show_menu_rtl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomDialog.builder()
                        .withDirection(BottomDialog.RTL)
                        .withHiddenHeader(false)
                        .withCancelable(true)
                        .withTitle("هشدار", typeface)
                        .withIcon(R.drawable.ic_error_outline_red_700_24dp)
                        .withContent("آیا کاربر … را حذف میکنید؟")
                        .withHiddenNegativeButton(false)
                        .withNegativeText("انصراف")
                        .withPositiveText("بله.حذف میکنم.")
                        .withPositiveBackgroundType(BottomDialog.GREY)
                        .withNegativeBackgroundType(BottomDialog.TRANSPARENT)
                        .withDefaultTypeface(typeface)
                        .build()
                        .show(MainActivity.this);
            }
        });

        findViewById(R.id.btn_show_menu_ltr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomDialog.builder()
                        .withIcon(R.drawable.ic_error_outline_red_700_24dp)

                        .withTitle("Warning", typeface, R.dimen.titleTextSize)
                        .withContent("Do you remove this ... ?", typeface, R.dimen.contentTextSize)

                        .withPositiveText("Yes.Remove it.", android.R.color.white)
                        .withPositiveBackgroundType(BottomDialog.BLUE)

                        .withNegativeText("Cancel", R.color.textSecondaryColor)
                        .withNegativeBackgroundType(BottomDialog.TRANSPARENT)
                        .build()
                        .show(MainActivity.this);
            }
        });
    }

}
