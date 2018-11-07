package com.petergeras.digitalclock;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class DigitView extends ConstraintLayout {

    View segmentA;
    ImageView segmentB;
    ImageView segmentC;
    ImageView segmentD;
    ImageView segmentE;
    ImageView segmentF;
    ImageView segmentG;

    int selectedColor;

    @SuppressLint("WrongViewCast")
    void init(Context context){

        View view = inflate(context, R.layout.custom_layout, this);

        segmentA = (ImageView) view.findViewById(R.id.line_horizontal_one);
        segmentB = (ImageView) view.findViewById(R.id.line_horizontal_two);
        segmentC = (ImageView) view.findViewById(R.id.line_horizontal_three);
        segmentD = (ImageView) view.findViewById(R.id.line_vertical_one);
        segmentE = (ImageView) view.findViewById(R.id.line_vertical_two);
        segmentF = (ImageView) view.findViewById(R.id.line_vertical_three);
        segmentG = (ImageView) view.findViewById(R.id.line_vertical_four);

        // Gets variables from SharedPreferences
        SharedPreferences pref =  context.getSharedPreferences("MyPref", 0);
        selectedColor = pref.getInt("selectedColor", Color.BLACK);

    }


    public DigitView(Context context) {
        super(context);
        init(context);
    }

    public DigitView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DigitView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void makeNumber(int no){

        // Assigns backgroundColor using the variable "selectedColor" to change all the segments
        // color this is done in this loop so we don't have to repeat each segment color in the
        // switch loop
        segmentA.setBackgroundColor(selectedColor);
        segmentB.setBackgroundColor(selectedColor);
        segmentC.setBackgroundColor(selectedColor);
        segmentD.setBackgroundColor(selectedColor);
        segmentE.setBackgroundColor(selectedColor);
        segmentF.setBackgroundColor(selectedColor);
        segmentG.setBackgroundColor(selectedColor);

        // Switch cases to represent the number being used setAplha changes the shade color of the
        // segment based on the number in question
        switch (no){
            case 0:
                segmentA.setAlpha(1f);
                segmentB.setAlpha(0.07f);
                segmentC.setAlpha(1f);
                segmentD.setAlpha(1f);
                segmentE.setAlpha(1f);
                segmentF.setAlpha(1f);
                segmentG.setAlpha(1f);
                break;
            case 1:
                segmentA.setAlpha(0.07f);
                segmentB.setAlpha(0.07f);
                segmentC.setAlpha(0.07f);
                segmentD.setAlpha(0.07f);
                segmentE.setAlpha(0.07f);
                segmentF.setAlpha(1f);
                segmentG.setAlpha(1f);
                break;
            case 2:
                segmentA.setAlpha(1f);
                segmentB.setAlpha(1f);
                segmentC.setAlpha(1f);
                segmentD.setAlpha(0.07f);
                segmentE.setAlpha(1f);
                segmentF.setAlpha(1f);
                segmentG.setAlpha(0.07f);
                break;
            case 3:
                segmentA.setAlpha(1f);
                segmentB.setAlpha(1f);
                segmentC.setAlpha(1f);
                segmentD.setAlpha(0.07f);
                segmentE.setAlpha(0.07f);
                segmentF.setAlpha(1f);
                segmentG.setAlpha(1f);
                break;
            case 4:
                segmentA.setAlpha(0.07f);
                segmentB.setAlpha(1f);
                segmentC.setAlpha(0.07f);
                segmentD.setAlpha(1f);
                segmentE.setAlpha(0.07f);
                segmentF.setAlpha(1f);
                segmentG.setAlpha(1f);
                break;
            case 5:
                segmentA.setAlpha(1f);
                segmentB.setAlpha(1f);
                segmentC.setAlpha(1f);
                segmentD.setAlpha(1f);
                segmentE.setAlpha(0.07f);
                segmentF.setAlpha(0.07f);
                segmentG.setAlpha(1f);
                break;
            case 6:
                segmentA.setAlpha(1f);
                segmentB.setAlpha(1f);
                segmentC.setAlpha(1f);
                segmentD.setAlpha(1f);
                segmentE.setAlpha(1f);
                segmentF.setAlpha(0.07f);
                segmentG.setAlpha(1f);
                break;
            case 7:
                segmentA.setAlpha(1f);
                segmentB.setAlpha(0.07f);
                segmentC.setAlpha(0.07f);
                segmentD.setAlpha(0.07f);
                segmentE.setAlpha(0.07f);
                segmentF.setAlpha(1f);
                segmentG.setAlpha(1f);
                break;
            case 8:
                segmentA.setAlpha(1f);
                segmentB.setAlpha(1f);
                segmentC.setAlpha(1f);
                segmentD.setAlpha(1f);
                segmentE.setAlpha(1f);
                segmentF.setAlpha(1f);
                segmentG.setAlpha(1f);
                break;
            case 9:
                segmentA.setAlpha(1f);
                segmentB.setAlpha(1f);
                segmentC.setAlpha(0.07f);
                segmentD.setAlpha(1f);
                segmentE.setAlpha(1f);
                segmentE.setAlpha(0.07f);
                segmentF.setAlpha(1f);
                segmentG.setAlpha(1f);
                break;
        }
    }
}

